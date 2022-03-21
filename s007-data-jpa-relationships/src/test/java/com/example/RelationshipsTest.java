package com.example;

import com.example.entities.Category;
import com.example.entities.Direction;
import com.example.entities.Manufacturer;
import com.example.entities.Product;
import com.example.repositories.CategoryRepository;
import com.example.repositories.DirectionRepository;
import com.example.repositories.ManufacturerRepository;
import com.example.repositories.ProductRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RelationshipsTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DirectionRepository directionRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void oneToOneTest() {

        Direction direction = new Direction(null, "Calle falsa", "89876", "Madrid", "Spain");
        Manufacturer manufacturer1 = new Manufacturer(null, "Honda", 3000, 1980);
        Manufacturer manufacturer2 = new Manufacturer(null, "Ford", 3000, 1980);

        directionRepository.save(direction);

        // 1. One To One
        manufacturer1.setDirection(direction);
        manufacturerRepository.save(manufacturer1);

        /*
        ConstraintViolationException. Al ser OneToOne no puede haber más de un
        Manufacturer con la misma Direction
         */
        manufacturer2.setDirection(direction);
        assertThrows(DataIntegrityViolationException.class,
                () -> manufacturerRepository.save(manufacturer2));

    }


    @Test
    void oneToManyTest() {

        Manufacturer manufacturer1 = new Manufacturer(null, "Honda", 3000, 1980);
        manufacturerRepository.save(manufacturer1);

        List<Product> products = List.of(
                new Product(null, "product 1", 1, 9.99),
                new Product(null, "product 2", 5, 29.99),
                new Product(null, "product 3", 10, 99.99)
        );

        /*
        FORMA INCORRECTA DE GUARDAR:
        no guarda los productos porque no es el owner de la relación
         */
//        productRepository.saveAll(products);
//        manufacturer1.setProducts(products);
//        manufacturerRepository.save(manufacturer1); // no owner
        /*
        FORMA CORRECTA DE GUARDAR:
         */
        products.forEach(product -> product.setManufacturer(manufacturer1));
        productRepository.saveAll(products);

        products.forEach(p -> assertNotNull(p.getId()));
    }

    @Test
    void manyToMany() {

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
        Optional<Product> productOpt = productRepository.findById(1L);
        assertTrue(productOpt.isPresent());
        assertEquals(2, productOpt.get().getCategories().size());

        Optional<Category> categoryOpt = categoryRepository.findById(1L);
        assertTrue(categoryOpt.isPresent());
        assertEquals(3, categoryOpt.get().getProducts().size());

        // Lado no owner category: NO GUARDA
//        categoryA.getProducts().add(productA);
//        categoryA.getProducts().add(productB);
//
//        categoryB.getProducts().add(productA);
//        categoryB.getProducts().add(productB);
//
//        categoryC.getProducts().add(productA);
//        categoryC.getProducts().add(productC);
//
//        categoryRepository.saveAll(List.of(categoryA, categoryB, categoryC));
    }
}
