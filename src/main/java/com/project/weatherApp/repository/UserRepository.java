package com.project.weatherApp.repository;

import com.project.weatherApp.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findById(int id) {
        return em.find(User.class, id);
    }

    public User findByLogin(String login) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.login = :login");
        query.setParameter("login", login);
        return (User) query.getSingleResult();
    }

    public void delete(User user) {
        em.remove(user);
    }
}
