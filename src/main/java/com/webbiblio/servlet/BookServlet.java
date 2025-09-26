package com.webbiblio.servlet;

import com.webbiblio.dao.AuthorDAO;
import com.webbiblio.dao.BookDAO;
import com.webbiblio.model.Author;
import com.webbiblio.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/books") // Définit l'URL pour accéder à ce servlet
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final BookDAO bookDAO = new BookDAO(); // Instance du DAO pour les livres
    private final AuthorDAO authorDAO = new AuthorDAO(); // Instance du DAO pour les auteurs

    // Gère les requêtes GET (affichage des livres, formulaire d'ajout/modification)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); // Récupère l'action à effectuer (add, edit, etc.)
        String bookId = req.getParameter("bookId"); // Récupère l'ID du livre si nécessaire
        try {
            switch (action != null ? action : "") {
                case "edit":
                    // Affiche le formulaire de modification d'un livre
                    if (notEmpty(bookId)) {
                        Book book = bookDAO.findById(Long.parseLong(bookId));
                        req.setAttribute("book", book);
                    }
                    loadAuthors(req); // Charge la liste des auteurs pour le formulaire
                    req.getRequestDispatcher("/book/form.jsp").forward(req, resp);
                    return;
                case "add":
                    // Affiche le formulaire d'ajout d'un livre
                    loadAuthors(req);
                    req.getRequestDispatcher("/book/form.jsp").forward(req, resp);
                    return;
                default:
                    // Affiche la liste des livres
                    List<Book> books = bookDAO.findAll();
                    req.setAttribute("books", books);
                    req.getRequestDispatcher("/book/list.jsp").forward(req, resp);
                    break;
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Erreur lors du chargement des données: " + e.getMessage());
            req.getRequestDispatcher("/book/list.jsp").forward(req, resp);
        }
    }

    // Gère les requêtes POST (ajout, modification, suppression d'un livre)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (action == null) {
            resp.sendRedirect(req.getContextPath() + "/books");
            return;
        }
        try {
            switch (action) {
                case "add":
                    handleAddBook(req); // Ajoute un nouveau livre
                    break;
                case "edit":
                    handleEditBook(req); // Modifie un livre existant
                    break;
                case "delete":
                    handleDeleteBook(req); // Supprime un livre
                    break;
            }
        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Erreur lors de l'opération: " + e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/books"); // Redirige vers la liste des livres
    }

    // Ajoute un nouveau livre
    private void handleAddBook(HttpServletRequest req) {
        String title = req.getParameter("title");
        String isbn = req.getParameter("isbn");
        String publicationDate = req.getParameter("publicationDate");
        String authorIdStr = req.getParameter("authorId");
        if (notEmpty(title) && notEmpty(isbn) && notEmpty(publicationDate) && notEmpty(authorIdStr)) {
            Author author = authorDAO.findById(Long.parseLong(authorIdStr));
            if (author != null) {
                Book book = new Book(title, isbn, publicationDate, author);
                bookDAO.save(book); // Sauvegarde le livre en base
            }
        }
    }

    // Modifie un livre existant
    private void handleEditBook(HttpServletRequest req) {
        String idStr = req.getParameter("bookId");
        if (notEmpty(idStr)) {
            Book book = bookDAO.findById(Long.parseLong(idStr));
            if (book != null) {
                book.setTitle(req.getParameter("title"));
                book.setIsbn(req.getParameter("isbn"));
                book.setPublicationDate(req.getParameter("publicationDate"));
                String authorIdStr = req.getParameter("authorId");
                if (notEmpty(authorIdStr)) {
                    Author author = authorDAO.findById(Long.parseLong(authorIdStr));
                    if (author != null) {
                        book.setAuthor(author);
                    }
                }
                bookDAO.update(book); // Met à jour le livre en base
            }
        }
    }

    // Supprime un livre
    private void handleDeleteBook(HttpServletRequest req) {
        String idParam = req.getParameter("bookId");
        if (notEmpty(idParam)) {
            bookDAO.deleteById(Long.parseLong(idParam)); // Supprime le livre en base
        }
    }

    // Charge la liste des auteurs pour le formulaire
    private void loadAuthors(HttpServletRequest req) {
        List<Author> authors = authorDAO.findAll();
        req.setAttribute("authors", authors);
    }

    // Vérifie si une chaîne n'est pas vide
    private boolean notEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
