package com.webbiblio.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe représentant un auteur dans la bibliothèque.
 * Chaque auteur peut avoir plusieurs livres (relation OneToMany).
 */
@Entity // On dit à JPA que cette classe correspond à une table dans la base
@Table(name = "authors") // Le nom de la table dans la base sera "authors"
public class Author {

    // Identifiant unique de l'auteur, auto-généré par la base de données
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Informations personnelles de l'auteur
    private String firstname;   // Prénom
    private String name;        // Nom
    private String nationality; // Nationalité

    /**
     * Liste des livres de l'auteur
     * mappedBy="author" : la relation est gérée côté Book
     * cascade = toutes les opérations sur l'auteur sont répercutées sur ses livres
     * fetch = EAGER signifie que les livres sont chargés en même temps que l'auteur
     * orphanRemoval = true supprime les livres orphelins si on les retire de la liste
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private final List<Book> books = new ArrayList<>();

    // Constructeur par défaut nécessaire pour JPA
    protected Author() {}

    // Constructeur pour créer un auteur facilement
    public Author(String firstname, String name, String nationality) {
        this.firstname = firstname;
        this.name = name;
        this.nationality = nationality;
    }

    // ========================
    // Getters et setters
    // ========================

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    // Retourne la liste des livres associés à cet auteur
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Méthode pratique pour obtenir le nombre de livres
     * associés à cet auteur
     */
    public int getBookAmount() {
        return books.size();
    }

    /**
     * Ajoute un livre à l'auteur et lie le livre à cet auteur
     */
    public void addBook(Book book) {
        if (books != null) {
            books.add(book);
        }
        book.setAuthor(this); // On s'assure que la relation est bidirectionnelle
    }
}
