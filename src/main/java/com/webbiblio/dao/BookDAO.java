// Communication de données Book sur la base de données

package com.webbiblio.dao;

import com.webbiblio.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

// DAO = couche d'accès à la base de données
public class BookDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
    EntityManager em = emf.createEntityManager();

    public void save(Book book) { // Ajouter un livre
        em.getTransaction().begin();
        em.persist(book); // INSERT INTO books
        em.getTransaction().commit();
        em.close();
    }
    public void delete(Book book) { // Supprimer un livre
        em.createQuery("DELETE FROM Book");
    }

    public List<Book> findAll() { // Trouver un livre
        List<Book> books = em.createQuery("FROM Book", Book.class).getResultList();
        em.close();
        return books;
    }
}

