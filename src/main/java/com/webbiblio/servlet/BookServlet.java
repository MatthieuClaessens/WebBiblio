// Servlet BookServlet servant de contrôleur pour gérer les requêtes HTTP provenant des pages JSP
// Elle communique avec la couche DAO pour manipuler les objets Book
// et transmet les données aux JSP pour affichage ou saisie.

package com.webbiblio.servlet;

import com.webbiblio.dao.BookDAO;
import com.webbiblio.model.Book;
import com.webbiblio.model.Book;

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

    // GET = afficher la liste
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookDAO.findAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("livre/list.jsp").forward(req, resp);
    }

    // POST = ajouter un livre
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        if (name != null && !name.isEmpty()) {
            bookDAO.save(new Book(name));
        }
        resp.sendRedirect(req.getContextPath() + "livre/form.jsp");
    }
}
