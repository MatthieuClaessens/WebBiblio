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
@WebServlet("/books/*")
public class BookServlet extends HttpServlet {
    private final BookDAO bookDAO = new BookDAO();
    private final AuthorDAO authorDAO = new AuthorDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo(); // peut être null

        if ("/new".equals(path)) {
            List<Author> authors = authorDAO.findAll();
            System.out.println("[/books/new] authors size = " + authors.size()); // debug
            req.setAttribute("authors", authors);
            req.getRequestDispatcher("/book/form.jsp").forward(req, resp);
            return;
        }

        List<Book> books = bookDAO.findAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/book/list.jsp").forward(req, resp);
    }

    // POST = ajouter un livre (auteur choisi dans le <select name="authorId">)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String isbn = req.getParameter("isbn");
        String publicationDate = req.getParameter("publicationDate");
        String authorIdStr = req.getParameter("authorId");

        if (title != null && !title.isEmpty()
                && isbn != null && !isbn.isEmpty()
                && publicationDate != null && !publicationDate.isEmpty()
                && authorIdStr != null && !authorIdStr.isEmpty()) {

            Long authorId = Long.parseLong(authorIdStr);
            Author author = authorDAO.findById(authorId);

            if (author != null) {
                Book book = new Book(title, isbn, publicationDate, author);
                bookDAO.save(book);
            }
        }

        // PRG
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}
