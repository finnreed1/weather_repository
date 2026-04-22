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

    public Optional findByName(String city) {
        return em.createQuery("SELECT l FROM Location l WHERE l.name = :city")
                .setParameter("city", city)
                .getResultList().stream().findFirst();
    }

    public Optional findByLatAndLon(double lat, double lon) {
        return em.createQuery("SELECT l FROM Location l WHERE l.latitude = :lat AND l.longitude = :lon")
                .setParameter("lat", lat).setParameter("lon", lon)
                .getResultList().stream().findFirst();
    }

    public Optional findByNameAndUserId(String locationName, int userId) {
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
