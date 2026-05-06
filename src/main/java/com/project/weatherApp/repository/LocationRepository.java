package com.project.weatherApp.repository;

import com.project.weatherApp.model.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository {
    @PersistenceContext
    private EntityManager em;

    public List findByUserId(int userId) {
        return em.createQuery("select l from Location l where l.userId = :userId")
                .setParameter("userId", userId)
                .getResultList();
    }

    public Optional<Location> findByNameAndUserId(String locationName, int userId) {
        return em.createQuery("SELECT l FROM Location l WHERE l.name = :name AND l.userId = :userId")
                .setParameter("name", locationName).setParameter("userId", userId)
                .getResultList().stream().findFirst();
    }

    public void save(Location location) {
        em.persist(location);
    }

    public void delete(Location location) {
        em.remove(location);
    }
}
