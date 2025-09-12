package com.webbiblio.model;

import jakarta.persistence.*;

// Entité JPA représentant un utilisateur
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;

    private String name;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
