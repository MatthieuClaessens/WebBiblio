package com.webbiblio.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Entité JPA représentant un auteur (User) dans la bibliothèque.
 * Chaque auteur peut avoir plusieurs livres (OneToMany).
 */
@Entity
@Table(name = "authors")
public class Author {

    // Identifiant unique de l'auteur (clé primaire auto-incrémentée)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Informations personnelles de l'auteur
    private String firstname;   // Prénom
    private String name;        // Nom
    private String nationality; // Nationalité

    /**
     * Relation OneToMany : un auteur peut avoir plusieurs livres.
     * - mappedBy = "auteur" indique que la relation est gérée côté Book
     * - cascade ALL = toutes les opérations sur l'auteur affectent ses livres
     * - fetch LAZY = les livres ne sont chargés qu'à la demande
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<Book> books = new ArrayList<>();

    // Constructeur par défaut requis par JPA
    protected Author() {}

    // Constructeur pratique pour créer un auteur
    public Author(String firstname, String name, String nationality, List<Book> books) {
        this.firstname = firstname;
        this.name = name;
        this.nationality = nationality;
    }

    // Constructeur pour créer un auteur sans id et sans livres
    public Author(String firstname, String name, String nationality) {
        this.firstname = firstname;
        this.name = name;
        this.nationality = nationality;
    }
    // ========================
    // Getters et Setters
    // ========================

    public Long getId() { return id; }

    public String getFirstName() { return firstname; }
    public void setFirstName(String firstname) { this.firstname = firstname; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    // Getter pour récupérer la liste des livres de l'auteur
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Méthode pratique pour obtenir directement le nombre de livres
     * associées à cet auteur. Utile pour l'affichage dans JSP.
     */
    public int getBookAmount() {
        return books.size();
    }

    public void addBook(Book book) {
        if (books != null) {
            books.add(book);
        }
        book.setAuthor(this);
    }
}
