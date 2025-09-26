package com.webbiblio.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // Indique que cette classe est une entité JPA
@Table(name = "authors") // Mappe la classe à la table "authors" en base de données
public class Author {
    @Id // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private Long id;

    private String firstname; // Prénom de l'auteur
    private String name; // Nom de l'auteur
    private String nationality; // Nationalité de l'auteur

    // Relation OneToMany : un auteur peut avoir plusieurs livres
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    // Constructeurs
    public Author() {}

    public Author(String firstname, String name, String nationality) {
        this.firstname = firstname;
        this.name = name;
        this.nationality = nationality;
    }

    // Getters et setters
    public Long getId() { return id; }
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }

    // Retourne le nombre de livres de l'auteur
    public int getBookAmount() {
        return books.size();
    }

    // Ajoute un livre à la liste des livres de l'auteur
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this); // Définit l'auteur du livre
    }

    // Supprime un livre de la liste des livres de l'auteur
    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null); // Dissocie le livre de l'auteur
    }
}
