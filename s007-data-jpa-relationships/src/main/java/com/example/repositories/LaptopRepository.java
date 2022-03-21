package com.example.repositories;

import com.example.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long>, JpaSpecificationExecutor<Laptop> {

    List<Laptop> findAllByModelLikeAndPriceGreaterThanAndCoresLessThanAndOfferTrueAndReleaseYearBetween(
            String model, Double price, Integer cores, Integer yearMin, Integer yearMax);
}