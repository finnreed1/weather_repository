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

    public Optional<Location> findByCoordAndUserId(double locationLat, double locationLon, int userId) {
        return em.createQuery("SELECT l FROM Location l WHERE ROUND(l.latitude, 4) = ROUND(:lat, 4)" +
                        "AND ROUND(l.longitude, 4) = ROUND(:lon, 4)" +
                        "AND l.userId = :userId")
                .setParameter("lat", locationLat)
                .setParameter("lon", locationLon)
                .setParameter("userId", userId)
                .getResultList().stream().findFirst();
    }

    public void save(Location location) {
        em.persist(location);
    }

    public void delete(Location location) {
        em.remove(location);
    }
}
