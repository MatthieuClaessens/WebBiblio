package com.webbiblio.servlet;

import com.webbiblio.dao.AuthorDAO;
import com.webbiblio.model.Author;
import com.webbiblio.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet qui gère toutes les opérations sur les auteurs :
 * - afficher la liste
 * - ajouter un auteur
 * - modifier un auteur
 * - supprimer un auteur
 */
@WebServlet("/authors") // URL pour accéder à cette servlet
public class AuthorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // DAO pour interagir avec la base de données des auteurs
    private final AuthorDAO authorDAO = new AuthorDAO();

    // =====================
    // GESTION DES REQUÊTES GET
    // =====================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");      // "add", "edit" ou null
        String authorId = req.getParameter("authorId");  // id de l'auteur à modifier ou supprimer

        switch (action != null ? action : "") {

            case "edit": {
                // Si on veut modifier un auteur
                if (notEmpty(authorId)) {
                    Author author = authorDAO.findById(Long.parseLong(authorId));
                    req.setAttribute("author", author);

                    // Crée une chaîne des titres des livres séparés par des virgules pour le formulaire
                    String booksStr = author.getBooks().stream()
                            .map(Book::getTitle)
                            .reduce((a, b) -> a + ", " + b)
                            .orElse("");
                    req.setAttribute("booksStr", booksStr);

                    // On redirige vers le formulaire avec les infos de l'auteur
                    req.getRequestDispatcher("/author/form.jsp").forward(req, resp);
                    return;
                }
                break;
            }

            case "add": {
                // Redirige vers le formulaire vide pour ajouter un auteur
                req.getRequestDispatcher("/author/form.jsp").forward(req, resp);
                return;
            }

            default: {
                // Par défaut, on affiche la liste des auteurs
                List<Author> authors = authorDAO.findAll();
                req.setAttribute("authors", authors);
                req.getRequestDispatcher("/author/list.jsp").forward(req, resp);
                break;
            }
        }
    }

    // =====================
    // GESTION DES REQUÊTES POST
    // =====================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action"); // "add", "edit" ou "delete"

        if (action == null) {
            resp.sendRedirect(req.getContextPath() + "/authors");
            return;
        }

        switch (action) {

            case "add": {
                // Récupère les valeurs du formulaire
                String firstname = req.getParameter("firstname");
                String name = req.getParameter("name");
                String nationality = req.getParameter("nationality");
                String booksParam = req.getParameter("books"); // titres séparés par des virgules

                // Vérifie que les champs essentiels sont remplis
                if (notEmpty(firstname) && notEmpty(name) && notEmpty(nationality)) {
                    Author author = new Author(firstname, name, nationality);

                    // Si des livres sont fournis, on les ajoute à l'auteur
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
                    // Sauvegarde de l'auteur en base
                    authorDAO.save(author);
                }
                break;
            }

            case "edit": {
                // Modification d'un auteur existant
                String authorId = req.getParameter("authorId");
                if (notEmpty(authorId)) {
                    Author author = authorDAO.findById(Long.parseLong(authorId));
                    if (author != null) {
                        // Met à jour les informations de base
                        author.setFirstName(req.getParameter("firstname"));
                        author.setName(req.getParameter("name"));
                        author.setNationality(req.getParameter("nationality"));

                        // Supprime tous les livres existants (orphanRemoval=true supprime de la DB)
                        author.getBooks().clear();

                        // Ajoute les nouveaux livres depuis le formulaire
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
                        // Met à jour l'auteur en base
                        authorDAO.update(author);
                    }
                }
                break;
            }

            case "delete": {
                // Suppression d'un auteur
                String idParam = req.getParameter("authorId");
                if (notEmpty(idParam)) {
                    Long id = Long.parseLong(idParam);
                    authorDAO.deleteById(id);
                }
                break;
            }

            default:
                // Aucun traitement pour d'autres actions
        }

        // PRG (Post-Redirect-Get) : évite de renvoyer le formulaire si on rafraîchit
        resp.sendRedirect(req.getContextPath() + "/authors");
    }

    // Petite méthode utilitaire pour vérifier si une chaîne est vide ou nulle
    private boolean notEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
