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

    public List<String> findAllTitles() {
        EntityManager em = emf.createEntityManager();
        List<String> titles = em.createQuery("SELECT b.title FROM Book b", String.class).getResultList();
        em.close();
        return titles;
    }

    public List<String> findAllFirstLetters() {
        EntityManager em = emf.createEntityManager();
        List<String> letters = em.createQuery("SELECT DISTINCT SUBSTRING(b.title, 1, 1) FROM Book b", String.class).getResultList();
        em.close();
        return letters;
    }

    public List<Book> findByFirstLetter(String letter) {
        EntityManager em = emf.createEntityManager();
        List<Book> books;
        if (letter == null || letter.isEmpty()) {
            books = em.createQuery("FROM Book", Book.class).getResultList();
        } else {
            books = em.createQuery("FROM Book b WHERE b.title LIKE :letter", Book.class)
                    .setParameter("letter", letter + "%")
                    .getResultList();
        }
        em.close();
        return books;
    }
}
