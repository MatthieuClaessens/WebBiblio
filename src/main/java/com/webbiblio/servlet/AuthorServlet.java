// Servlet UserServlet servant de contrôleur pour gérer les requêtes HTTP provenant des pages JSP
// Elle communique avec la couche DAO pour manipuler les objets Book
// et transmet les données aux JSP pour affichage ou saisie.
package com.webbiblio.servlet;

import com.webbiblio.dao.AuthorDAO;
import com.webbiblio.model.Author;
import com.webbiblio.model.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

// Servlet contrôleur MVC
@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {

	// Identifiant de version de sérialisation généré manuellement.
	// Recommandé pour toutes les classes qui héritent de HttpServlet (implémente Serializable).
	// Cela permet d’éviter des incompatibilités lors de la désérialisation si la classe évolue.
	private static final long serialVersionUID = 1L;

    private final AuthorDAO authorDAO = new AuthorDAO();

    // GET = afficher la liste
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorDAO.findAll();
        System.out.println("ok");
        //String authors = "message";
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/author/list.jsp").forward(req, resp);
    }

    // POST = ajouter un utilisateur
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstname = req.getParameter("firstname");
        String name = req.getParameter("name");
        String nationality = req.getParameter("nationality");
        String booksParam = req.getParameter("books");


       if (firstname != null && !firstname.isEmpty()
        && name != null && !name.isEmpty()
        && nationality != null && !nationality.isEmpty()) {

            Author author = new Author(firstname, name, nationality);

            if (booksParam != null && !booksParam.trim().isEmpty()) {
                String[] titles = booksParam.split(",");
                for (String title : titles) {
                    Book book = new Book(title.trim(), "ISBN", "Date de publication", author);
                    author.addBook(book);
                }
                authorDAO.save(author);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/author/list.jsp");
    }
}
