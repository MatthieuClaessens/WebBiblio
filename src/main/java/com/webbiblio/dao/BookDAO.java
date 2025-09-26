package com.webbiblio.dao;

import com.webbiblio.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class BookDAO {
    // Factory pour créer des EntityManager
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    // Sauvegarde un nouveau livre
    public void save(Book book) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde du livre", e);
        } finally {
            em.close();
        }
    }

    // Met à jour un livre existant
    public void update(Book book) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour du livre", e);
        } finally {
            em.close();
        }
    }

    // Supprime un livre par son ID
    public void deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Book book = em.find(Book.class, id);
            if (book != null) {
                em.remove(book);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression du livre", e);
        } finally {
            em.close();
        }
    }

    // Récupère tous les livres avec leurs auteurs
    public List<Book> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            // Requête JPQL pour récupérer les livres et leurs auteurs en une seule requête
            return em.createQuery("SELECT b FROM Book b LEFT JOIN FETCH b.author", Book.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Récupère un livre par son ID
    public Book findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }
}
