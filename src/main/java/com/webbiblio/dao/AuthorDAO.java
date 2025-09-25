// Communication de données Author sur la base de données

package com.webbiblio.dao;

import com.webbiblio.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

// DAO = couche d'accès à la base de données
public class AuthorDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    public void save(Author author) { // Ajouter un auteur
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(author); // INSERT INTO authors
        em.getTransaction().commit();
        em.close();
    }

    public void deleteById(Long authorId) { // Suppression d'auteur
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Author author = em.find(Author.class, authorId);
        if (author != null) {
            em.remove(author);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Author> findAll() { //  Chercher tous les auteurs
        EntityManager em = emf.createEntityManager();
        List<Author> author = em.createQuery("from Author", Author.class).getResultList();
        em.close();
        return author;
    }

    public Author findById(Long authorId) { // Chercher par l'ID
        EntityManager em = emf.createEntityManager();
        Author author = em.find(Author.class, authorId);
        em.close();
        return author;
    }

    public void update(Author author) { // Modifier un auteur
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(author); // UPDATE
        em.getTransaction().commit();
        em.close();
    }
}