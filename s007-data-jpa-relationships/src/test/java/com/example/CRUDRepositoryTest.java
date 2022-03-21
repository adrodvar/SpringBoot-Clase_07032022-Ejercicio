package com.example;

import com.example.entities.Direction;
import com.example.entities.Manufacturer;
import com.example.entities.Product;
import com.example.repositories.DirectionRepository;
import com.example.repositories.ManufacturerRepository;
import com.example.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CRUDRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Autowired
    DirectionRepository directionRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        productRepository.saveAll(List.of(
                new Product(null, "product 1", 1, 99.99),
                new Product(null, "product 2", 2, 19.99),
                new Product(null, "product 3", 3, 9.99)
        ));

        Direction direction = new Direction(null, "prueba", "prueba", "prueba", "prueba");
        directionRepository.save(direction);

        Manufacturer manufacturer = new Manufacturer(null, "manufacturer1", 200, 2015);
        manufacturer.setDirection(direction);
        manufacturerRepository.save(manufacturer);

    }

    @Test
    void delete() {

        long countBefore = productRepository.count();
        assertEquals(3, countBefore);
        productRepository.deleteByName("product 1");

        long countAfter = productRepository.count();
        assertEquals(2, countAfter);
    }

    @Test
    void productDeleteByPriceGreaterQueryTest() {
        long countBefore = productRepository.count();
        assertEquals(3, countBefore);

        productRepository.deleteByPriceGreaterQuery(15.0);

        long countAfter = productRepository.count();
        assertEquals(1, countAfter);
    }

    @Test
    void manufacturerDeleteByDirectionTest() {

        long countManufacturerBefore = manufacturerRepository.count();
        long countDirectionBefore = directionRepository.count();
        assertEquals(1, countManufacturerBefore);
        assertEquals(1, countDirectionBefore);

//        manufacturerRepository.deleteByDirectionCountryQuery("prueba");
        manufacturerRepository.deleteByDirectionCountry("prueba");

        long countAfter = manufacturerRepository.count();
        assertEquals(0, countAfter);
    }

    @Test
    void updateProductNameByPriceQuery() {

        productRepository.updateProductNameByPriceQuery("Producto en oferta", 20.0);
        productRepository.findAll().forEach(
                p -> System.out.println(p.getName()));
    }

    @Test
    void updateProductNameByPriceNativeQueryTest() {

        productRepository.updateProductNameByPriceNativeQuery("Product en oferta", 20.0);
        productRepository.findAll().forEach(
                p -> System.out.println(p.getName()));
    }

    @Test
    void insertProduct() {

        long countBefore = productRepository.count();
        assertEquals(3, countBefore);

        productRepository.insertProduct(null, "anotherProduct", 99.99, 1, 1L);

        long countAfter = productRepository.count();
        assertEquals(4, countAfter);
    }
}
