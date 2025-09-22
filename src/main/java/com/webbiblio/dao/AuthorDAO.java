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

    public void save(Author author) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(author); // INSERT INTO authors
        em.getTransaction().commit();
        em.close();
    }

    public List<Author> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Author> author = em.createQuery("from Author", Author.class).getResultList();
        em.close();
        return author;
    }

    public Author findById(Long authorId) {
        EntityManager em = emf.createEntityManager();
        Author author = em.find(Author.class, authorId);
        return author;
    }
}