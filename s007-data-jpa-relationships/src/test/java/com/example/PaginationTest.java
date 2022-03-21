package com.example;

import com.example.entities.Laptop;
import com.example.entities.Product;
import com.example.repositories.LaptopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PaginationTest {

    @Autowired
    LaptopRepository laptopRepository;

    @BeforeEach
    void setUp() {
        laptopRepository.saveAll(List.of(
                new Laptop(null, "MSI Modern 14", 1200.0, 8, true, 2018),
                new Laptop(null, "MSI Modern 15", 1300.0, 8, false, 2019),
                new Laptop(null, "Acer Gaming", 1400.0, 8, true, 2020),
                new Laptop(null, "Asus Gaming", 1500.0, 8, false, 2021),
                new Laptop(null, "Lenovo Professional", 1600.0, 8, true, 2021),
                new Laptop(null, "Lenovo Launchpad", 1700.0, 8, false, 2022)

        ));
    }

    @Test
    void defaultSortTest() {
        List<Laptop> laptops = laptopRepository.findAll();
        assertTrue(laptops.get(0).getModel().startsWith("MSI"));
    }

    @Test
    void sortByModelAscTest() {
        Sort sortModel = Sort.by("model");// Por defecto la dirección es ASCENDENTE
        List<Laptop> laptops = laptopRepository.findAll(sortModel);
        assertTrue(laptops.get(0).getModel().startsWith("Acer"));
    }

    @Test
    void sortByModelDescTest() {
        Sort sortModel = Sort.by("model").descending();// Por defecto la dirección es ASCENDENTE
        List<Laptop> laptops = laptopRepository.findAll(sortModel);
        assertTrue(laptops.get(0).getModel().startsWith("MSI"));
    }

    @Test
    void sortByOfferAndPriceTest() {
        Sort sortByOfferAndPrice = Sort.by("offer").descending().and(Sort.by("price").ascending());
        List<Laptop> laptops = laptopRepository.findAll(sortByOfferAndPrice);
        laptops.forEach(System.out::println);
    }

    @Test
    void pagination1Test() {
        Pageable page1 = PageRequest.of(0, 2);
        Page<Laptop> laptopPage1 = laptopRepository.findAll(page1);
        List<Laptop> laptops = laptopPage1.getContent();
        assertEquals(2, laptops.size());
        laptops.forEach(System.out::println);

        Pageable page2 = PageRequest.of(1, 2);
        Page<Laptop> laptopPage2 = laptopRepository.findAll(page2);
        List<Laptop> laptops2 = laptopPage2.getContent();
        assertEquals(2, laptops2.size());
        laptops2.forEach(System.out::println);

        Pageable page3 = PageRequest.of(2, 2);
        Page<Laptop> laptopPage3 = laptopRepository.findAll(page3);
        List<Laptop> laptops3 = laptopPage3.getContent();
        assertEquals(2, laptops3.size());
        laptops3.forEach(System.out::println);
    }

    @Test
    void paginationErrorTest() {

        /*
        Es normal tener una clase extra llamada PaginationUtils que permita
        realizar el cálculo de páginas en base al número de elementos y el tamaño de página

        Y que también nos pueda ayudar a devolver cabeceras http en los controladores
        indicando cuántas páginas quedan y la página actual etc para que el frontend pueda mostrar
        correctamente cuántas páginas hay y quedan

         */
        Pageable page1 = PageRequest.of(99, 2);
        Page<Laptop> laptopPage1 = laptopRepository.findAll(page1);
        List<Laptop> laptops = laptopPage1.getContent();
        assertEquals(0, laptops.size());
    }

    @Test
    void sortAndPagingTest() {

        Pageable pagingAndSort = PageRequest.of(0,4, Sort.by("model"));
        Page<Laptop> laptopPage1 = laptopRepository.findAll(pagingAndSort);
        List<Laptop> laptops = laptopPage1.getContent();
        laptops.forEach(System.out::println);
        assertEquals(4, laptops.size());
    }
}
