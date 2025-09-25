package com.webbiblio.dao;

import com.webbiblio.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class AuthorDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    // Ajouter un auteur
    public void save(Author author) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
        em.close();
    }

    // Mettre à jour un auteur
    public void update(Author author) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(author);
        em.getTransaction().commit();
        em.close();
    }

    // Supprimer un auteur par ID
    public void deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Author author = em.find(Author.class, id);
        if (author != null) {
            em.remove(author);
        }
        em.getTransaction().commit();
        em.close();
    }

    // Trouver tous les auteurs
    public List<Author> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Author> authors = em.createQuery("from Author", Author.class).getResultList();
        em.close();
        return authors;
    }

    // Trouver un auteur par ID
    public Author findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Author author = em.find(Author.class, id);
        em.close();
        return author;
    }
}
