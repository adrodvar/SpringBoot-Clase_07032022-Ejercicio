package com.example.dao;

import com.example.entities.Laptop;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class LaptopDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Laptop> findAll(){
        String jpql = "select l from Laptop l";
        return this.entityManager.createQuery(jpql, Laptop.class).getResultList();
    }

    public Optional<Laptop> findById(Long id){
        Laptop laptop = this.entityManager.find(Laptop.class, id);
        return Optional.ofNullable(laptop);
    }



}
