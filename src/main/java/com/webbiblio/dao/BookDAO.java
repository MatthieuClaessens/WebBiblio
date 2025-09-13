package com.webbiblio.dao;

import com.webbiblio.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

// DAO = couche d'accès à la base de données
public class BookDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    public void save(Book book) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(book); // INSERT INTO books
        em.getTransaction().commit();
        em.close();
    }

    public List<Book> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Book> books = em.createQuery("from Book", Book.class).getResultList();
        em.close();
        return books;
    }
}
