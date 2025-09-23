// Servlet BookServlet servant de contrôleur pour gérer les requêtes HTTP provenant des pages JSP
// Elle communique avec la couche DAO pour manipuler les objets Book
// et transmet les données aux JSP pour affichage ou saisie.

package com.webbiblio.servlet;

import com.webbiblio.dao.AuthorDAO;
import com.webbiblio.dao.BookDAO;
import com.webbiblio.model.Book;

import com.webbiblio.model.Author;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

// Servlet contrôleur MVC
@WebServlet("/books")
public class BookServlet extends HttpServlet {

    // Identifiant de version de sérialisation généré manuellement.
    // Recommandé pour toutes les classes qui héritent de HttpServlet (implémente Serializable).
    // Cela permet d’éviter des incompatibilités lors de la désérialisation si la classe évolue.
    private static final long serialVersionUID = 1L;

    private final BookDAO bookDAO = new BookDAO();
    private final AuthorDAO authorDAO = new AuthorDAO();

    // GET = afficher la liste
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookDAO.findAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/book/list.jsp").forward(req, resp);
    }

    /* Modifier un livre
    // @Override
    // protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }*/

    // POST = ajouter un livre
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String isbn = req.getParameter("isbn");
        String publicationDate = req.getParameter("publicationDate");
        String authorIdStr = req.getParameter("authorId"); // On suppose que le formulaire envoie l'ID de l'auteur

        if (title != null && !title.isEmpty() &&
                isbn != null && !isbn.isEmpty() &&
                publicationDate != null && !publicationDate.isEmpty() &&
                authorIdStr != null && !authorIdStr.isEmpty()) {

            Long authorId = Long.parseLong(authorIdStr);
            Author author = authorDAO.findById(authorId); // Méthode à implémenter dans AuthorDAO

            if (author != null) {
                Book book = new Book(title, isbn, publicationDate, author);
                bookDAO.save(book);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}
