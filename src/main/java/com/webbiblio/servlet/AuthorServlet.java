package com.webbiblio.servlet;

import com.webbiblio.dao.AuthorDAO;
import com.webbiblio.model.Author;
import com.webbiblio.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/authors") // Définit l'URL pour accéder à ce servlet
public class AuthorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AuthorDAO authorDAO = new AuthorDAO(); // Instance du DAO pour les auteurs

    // Gère les requêtes GET (affichage des auteurs, formulaire d'ajout/modification)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); // Récupère l'action à effectuer (add, edit, etc.)
        String authorId = req.getParameter("authorId"); // Récupère l'ID de l'auteur si nécessaire
        try {
            switch (action != null ? action : "") {
                case "edit":
                    // Affiche le formulaire de modification d'un auteur
                    if (notEmpty(authorId)) {
                        Author author = authorDAO.findById(Long.parseLong(authorId));
                        if (author != null) {
                            req.setAttribute("author", author);
                            // Crée une chaîne des titres des livres pour le formulaire
                            String booksStr = author.getBooks().stream()
                                    .map(Book::getTitle)
                                    .reduce((a, b) -> a + ", " + b)
                                    .orElse("");
                            req.setAttribute("booksStr", booksStr);
                        }
                    }
                    req.getRequestDispatcher("/author/form.jsp").forward(req, resp);
                    return;
                case "add":
                    // Affiche le formulaire d'ajout d'un auteur
                    req.getRequestDispatcher("/author/form.jsp").forward(req, resp);
                    return;
                default:
                    // Affiche la liste des auteurs
                    List<Author> authors = authorDAO.findAll();
                    req.setAttribute("authors", authors);
                    req.getRequestDispatcher("/author/list.jsp").forward(req, resp);
                    break;
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Erreur lors du chargement des données: " + e.getMessage());
            req.getRequestDispatcher("/author/list.jsp").forward(req, resp);
        }
    }

    // Gère les requêtes POST (ajout, modification, suppression d'un auteur)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            resp.sendRedirect(req.getContextPath() + "/authors");
            return;
        }
        try {
            switch (action) {
                case "add":
                    handleAddAuthor(req); // Ajoute un nouvel auteur
                    break;
                case "edit":
                    handleEditAuthor(req); // Modifie un auteur existant
                    break;
                case "delete":
                    handleDeleteAuthor(req); // Supprime un auteur
                    break;
            }
        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Erreur lors de l'opération: " + e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/authors"); // Redirige vers la liste des auteurs
    }

    // Ajoute un nouvel auteur
    private void handleAddAuthor(HttpServletRequest req) {
        String firstname = req.getParameter("firstname");
        String name = req.getParameter("name");
        String nationality = req.getParameter("nationality");
        String booksParam = req.getParameter("books");
        if (notEmpty(firstname) && notEmpty(name) && notEmpty(nationality)) {
            Author author = new Author(firstname, name, nationality);
            if (notEmpty(booksParam)) {
                // Ajoute les livres à l'auteur
                String[] titles = booksParam.split(",");
                for (String title : titles) {
                    String t = title.trim();
                    if (!t.isEmpty()) {
                        Book book = new Book(t, "ISBN", "Date de publication", author);
                        author.addBook(book);
                    }
                }
            }
            authorDAO.save(author); // Sauvegarde l'auteur en base
        }
    }

    // Modifie un auteur existant
    private void handleEditAuthor(HttpServletRequest req) {
        String authorId = req.getParameter("authorId");
        if (notEmpty(authorId)) {
            Author author = authorDAO.findById(Long.parseLong(authorId));
            if (author != null) {
                author.setFirstname(req.getParameter("firstname"));
                author.setName(req.getParameter("name"));
                author.setNationality(req.getParameter("nationality"));
                // Met à jour les livres de l'auteur
                author.getBooks().clear();
                String booksParam = req.getParameter("books");
                if (notEmpty(booksParam)) {
                    String[] titles = booksParam.split(",");
                    for (String title : titles) {
                        String t = title.trim();
                        if (!t.isEmpty()) {
                            Book book = new Book(t, "ISBN", "Date de publication", author);
                            author.addBook(book);
                        }
                    }
                }
                authorDAO.update(author); // Met à jour l'auteur en base
            }
        }
    }

    // Supprime un auteur
    private void handleDeleteAuthor(HttpServletRequest req) {
        String idParam = req.getParameter("authorId");
        if (notEmpty(idParam)) {
            authorDAO.deleteById(Long.parseLong(idParam)); // Supprime l'auteur en base
        }
    }

    // Vérifie si une chaîne n'est pas vide
    private boolean notEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
