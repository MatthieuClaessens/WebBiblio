// Objet Book

package com.webbiblio.model;

import jakarta.persistence.*;

// Entité JPA représentant un livre
@Entity
@Table(name = "books")
public class Book{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;

    private String name;

    public Book() {}

    public Book(String name) {
        this.name = name;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
