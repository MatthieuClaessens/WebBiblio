package com.webbiblio.model;

import jakarta.persistence.*;
import java.lang.String;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // auto-incrémenté par la BDD

    private String title;
    private String isbn;
    private String publicationDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    // Constructeur vide obligatoire pour JPA
    public Book() {}

    // Constructeur pour créer un Book avec auteurs
    public Book(String title, String isbn, String publicationDate, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.author = (Author) author;
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
