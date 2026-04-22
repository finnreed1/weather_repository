package com.project.weatherApp.repository;

import com.project.weatherApp.model.Session;
import com.project.weatherApp.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SessionRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Session session) { em.persist(session); }

    public Session findById(UUID id) {
        return em.find(Session.class, id);
    }

    public Optional<Session> findByUserId(int userId) {
        List<Session> sessions = em.createQuery("select s from Session s where s.userId = :userId")
                .setParameter("userId", userId)
                .getResultList();
        return sessions.stream().findFirst();
    }

    public void delete(Session session) {
        em.remove(session);
    }
}
