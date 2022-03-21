package com.example;

import com.example.entities.Category;
import com.example.entities.Direction;
import com.example.entities.Manufacturer;
import com.example.entities.Product;
import com.example.repositories.CategoryRepository;
import com.example.repositories.DirectionRepository;
import com.example.repositories.ManufacturerRepository;
import com.example.repositories.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
    }

}
