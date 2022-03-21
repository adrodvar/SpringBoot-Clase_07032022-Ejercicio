package com.example;

import com.example.entities.Laptop;
import com.example.repositories.LaptopRepository;
import com.example.service.LaptopSpecService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
1- Agregar dependencia: hibernate-jpamodelgen
2- Agregar plugin maven-compiler con configuración annotation processor hibernate-jpamodelgen
3- Comprobar al ejecutar el proyecto que se generan las clases abstractas en:
    target/classes/com/example/entities

 4- Settings > Build,Execution,deployment > Compiler > Annotation Processor > Seleccionar proyecto y comprobar
        que está marcada la opción check de Annotation processor

 5- Ya podemos construir Specification haciendo uso de los atributos de las clases especiales
 con la ventaja de no tener que poner los nombres de los atributos hardcoded en texto manualmente
 */
@SpringBootTest
public class SpecificationTest {
/*
    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private LaptopSpecService laptopSpecService;

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
    void findAllByModelLikeTest() {
        List<Laptop> laptops = laptopSpecService.findAllByModelLike("Modern");
        assertEquals(2,laptops.size());
        laptops.forEach( // itera sobre objetos Laptop
                laptop -> assertTrue(laptop.getModel().contains("Modern"))
        );
        laptops.stream()
                .map(Laptop::getModel) // itera sobre una lista de String que son los model
                .forEach(model -> assertTrue(model.contains("Modern")));
    }


    @Test
    void findAllByPricesBetweenTest() {

        List<Laptop> laptops = laptopSpecService.findAllByPricesBetween(1400.0, 1600.0);
        assertEquals(3,laptops.size());
        laptops.forEach( // itera sobre objetos Laptop
                laptop -> assertTrue(laptop.getPrice() >= 1400.0 && laptop.getPrice() <= 1600.0)
        );
    }

    @Test
    void findAllByIdInTest() {
        List<Laptop> laptops = laptopSpecService.findAllByIdIn(List.of(1L, 3L));
        assertEquals(2,laptops.size());
    }

    @Test
    void findAllByIsPremiumGaming() {
        List<Laptop> laptops = laptopSpecService.findAllByIsPremiumGaming();
        assertEquals(1,laptops.size());
        laptops.forEach(
                laptop -> assertEquals("Asus Gaming", laptop.getModel())
        );
    }*/
}
