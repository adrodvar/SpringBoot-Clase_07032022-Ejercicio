package com.example;

import com.example.entities.Category;
import com.example.entities.Product;
import com.example.repositories.CategoryRepository;
import com.example.repositories.DirectionRepository;
import com.example.repositories.ManufacturerRepository;
import com.example.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EntityGraphTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DirectionRepository directionRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product productA = new Product(null, "product A", 1, 99.0);
        Product productB = new Product(null, "product B", 10, 99.0);
        Product productC = new Product(null, "product C", 50, 99.0);
        productRepository.saveAll(List.of(productA, productB, productC));

        Category categoryA = new Category(null, "category A", "black");
        Category categoryB = new Category(null, "category B", "blue");
        Category categoryC = new Category(null, "category C", "green");
        categoryRepository.saveAll(List.of(categoryA, categoryB, categoryC));

        // Owner product
        productA.getCategories().add(categoryA);
        productA.getCategories().add(categoryB);

        productB.getCategories().add(categoryA);
        productB.getCategories().add(categoryB);

        productC.getCategories().add(categoryA);
        productC.getCategories().add(categoryC);

        productRepository.saveAll(List.of(productA, productB, productC));

    }

//    @Transactional
    @Test
    void name() {

        // No hay join a la tabla categorías
        Optional<Product> productOpt = productRepository.findById(1L);
         assertTrue(productOpt.isPresent());
        System.out.println(productOpt.get());

    }

    @Test
    void findByIdEntityGraphQuery() {

        /*
        Para recuperar las colecciones lazy hay varias opciones:
        1- Cambiar Fetch a EAGER: (No recomendado, es poco óptimo recuperar siempre cuando igual no lo necesitamos)
            Problema: es que siempre se van a recuperar todos los datos, incluso cuando no los necesites

        2- Crear query JPQL con join fetch:
            - @Query
            - EntityManager

        3- EntityGraph:
            @EntityGraph indica que cargue una colección


         El enfoque óptimo es que por defecto sea LAZY y crear consultas aparte con JPQL o EntityGraph para cuando se
         necesite cargar esos datos de manera EAGER.

         Se recomienda no cargar más de un atributo colección a la vez
         */

        // Por defecto recupera el producto incluyendo sus categorías
        Product product = productRepository.findByIdWithCategories(1L);
        System.out.println(product.getCategories());
    }

    @Test
    void findByIdWithCategoriesJQPL() {

        Product product = productRepository.findByIdWithCategoriesJQPL(1L);
        System.out.println(product.getCategories());
    }
}
