package com.webbiblio.dao;

import com.webbiblio.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class AuthorDAO {
    // Factory pour créer des EntityManager (gère la connexion à la base de données)
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    // Sauvegarde un nouvel auteur dans la base
    public void save(Author author) {
        EntityManager em = emf.createEntityManager(); // Crée un gestionnaire d'entités
        try {
            em.getTransaction().begin(); // Démarre une transaction
            em.persist(author); // Sauvegarde l'auteur
            em.getTransaction().commit(); // Valide la transaction
        } catch (Exception e) {
            // En cas d'erreur, annule la transaction
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde de l'auteur", e);
        } finally {
            em.close(); // Ferme le gestionnaire d'entités
        }
    }

    // Met à jour un auteur existant
    public void update(Author author) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(author); // Met à jour l'auteur
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour de l'auteur", e);
        } finally {
            em.close();
        }
    }

    // Supprime un auteur par son ID
    public void deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Author author = em.find(Author.class, id); // Cherche l'auteur par ID
            if (author != null) {
                em.remove(author); // Supprime l'auteur
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression de l'auteur", e);
        } finally {
            em.close();
        }
    }

    // Récupère tous les auteurs avec leurs livres (pour éviter le problème de lazy loading)
    public List<Author> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            // Requête JPQL pour récupérer les auteurs et leurs livres en une seule requête
            return em.createQuery("SELECT a FROM Author a LEFT JOIN FETCH a.books", Author.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Récupère un auteur par son ID
    public Author findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Author.class, id);
        } finally {
            em.close();
        }
    }
}
