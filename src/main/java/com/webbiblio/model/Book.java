package com.webbiblio.model;

import jakarta.persistence.*;

@Entity // Indique que cette classe est une entité JPA
@Table(name = "books") // Mappe la classe à la table "books" en base de données
public class Book {
    @Id // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private Long id;

    private String title; // Titre du livre
    private String isbn; // Numéro ISBN du livre
    private String publicationDate; // Date de publication du livre

    // Relation ManyToOne : un livre appartient à un auteur
    @ManyToOne(fetch = FetchType.LAZY) // Chargement paresseux (l'auteur n'est chargé que si nécessaire)
    @JoinColumn(name = "author_id") // Clé étrangère vers la table "authors"
    private Author author;

    // Constructeurs
    public Book() {}

    public Book(String title, String isbn, String publicationDate, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.author = author;
    }

    // Getters et setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getPublicationDate() { return publicationDate; }
    public void setPublicationDate(String publicationDate) { this.publicationDate = publicationDate; }
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
}
