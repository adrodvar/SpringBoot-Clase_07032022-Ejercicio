package com.example.service;

import com.example.entities.TaskEntities.UserEntity;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("bootcamp-spring");

    public static void main(String[] args) {
        try {
            persistEntity();
            findEntity();
        } finally {
            entityManagerFactory.close();
        }
    }

    private static void findEntity() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityGraph graph = em.getEntityGraph("userEntity-graph");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", graph);
        UserEntity user = em.find(UserEntity.class, 1, properties);
        em.close();
        printInitializeStatus(user);
    }

    private static void printInitializeStatus(UserEntity user) {
        PersistenceUtil userPersistencepu = entityManagerFactory.getPersistenceUnitUtil();
        System.out.println("Usuario: " + userPersistencepu.isLoaded(user));
        System.out.println("Nombre: " + userPersistencepu.isLoaded(user, "name"));
        System.out.println("Direccion: " + userPersistencepu.isLoaded(user, "addresses"));
        System.out.println("Movil: " + userPersistencepu.isLoaded(user, "phones"));
    }

    public static void persistEntity() {
        UserEntity user = new UserEntity();
        user.setName("Andres");
        user.addPhone("123456", "movil");
        user.addPhone("123456", "fijo");
        user.addAddressEntity("Calle falsa 123", "Barcelona", "Spain");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }
}
