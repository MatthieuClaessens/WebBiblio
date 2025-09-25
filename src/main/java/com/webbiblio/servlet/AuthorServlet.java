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

@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final AuthorDAO authorDAO = new AuthorDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String authorId = req.getParameter("authorId");
        BookDAO bookDAO = new BookDAO();

        if ("edit".equals(action) && authorId != null) {
            Author author = authorDAO.findById(Long.parseLong(authorId));
            req.setAttribute("author", author);
            // Suggestions pour autocomplétion
            req.setAttribute("allBookTitles", bookDAO.findAllTitles());
            req.setAttribute("bookLetters", bookDAO.findAllFirstLetters());
            req.setAttribute("filteredBooks", bookDAO.findByFirstLetter(""));
            return;
        }

        // Récupère tous les auteurs et les envoie à la JSP pour affichage
        List<Author> authors = authorDAO.findAll();
        req.setAttribute("authors", authors);
        req.setAttribute("allBookTitles", bookDAO.findAllTitles());
        req.setAttribute("bookLetters", bookDAO.findAllFirstLetters());
        req.setAttribute("filteredBooks", bookDAO.findByFirstLetter(""));
        req.getRequestDispatcher("/author/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        BookDAO bookDAO = new BookDAO();

        if ("delete".equals(action)) {
            // Suppression d'un auteur par id
            String idParam = req.getParameter("authorId");
            if (idParam != null && !idParam.isEmpty()) {
                Long authorId = Long.parseLong(idParam);
                authorDAO.deleteById(authorId);
            }
        } else if ("edit".equals(action)) {
            String authorId = req.getParameter("authorId");
            if (authorId != null) {
                Author author = authorDAO.findById(Long.parseLong(authorId));
                author.setFirstName(req.getParameter("firstname"));
                author.setName(req.getParameter("name"));
                author.setNationality(req.getParameter("nationality"));
                String booksParam = req.getParameter("books");
                author.getBooks().clear();
                if (booksParam != null && !booksParam.trim().isEmpty()) {
                    String[] titles = booksParam.split(",");
                    for (String title : titles) {
                        if (!title.trim().isEmpty()) {
                            Book book = new Book(title.trim(), "ISBN", "Date de publication", author);
                            author.addBook(book);
                        }
                    }
                }
                authorDAO.update(author);
            }
        } else {
            // Ajout d'un auteur
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
                }

                authorDAO.save(author);
            }
        }
        // Redirige vers la liste des auteurs après ajout ou suppression
        resp.sendRedirect(req.getContextPath() + "/authors");
    }
}