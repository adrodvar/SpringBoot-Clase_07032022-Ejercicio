package com.example.service;
/*
import com.example.entities.Laptop;
import com.example.entities.Laptop_;
import com.example.repositories.LaptopRepository;
import org.springframework.data.jpa.domain.Specification;*/
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopSpecService {
/*
    private static final String PREMIUM_MODEL_PATTERN = "%Gaming%";
    private static final Double PREMIUM_PRICE = 1500.0;

    private final LaptopRepository laptopRepository;

    public LaptopSpecService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    public List<Laptop> findAllByModelLike(String model){

        Specification<Laptop> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get(Laptop_.MODEL),
                        "%" + model + "%"
                );

        return this.laptopRepository.findAll(spec);
    }

    public List<Laptop> findAllByPricesBetween(Double min, Double max){

        Specification<Laptop> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.between(
                        root.get(Laptop_.PRICE), min, max
                );

        return this.laptopRepository.findAll(spec);

    }

    public List<Laptop> findAllByIdIn(List<Long> ids){
        Specification<Laptop> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.in(root.get(Laptop_.ID)).value(ids);

        return this.laptopRepository.findAll(spec);
    }

    public List<Laptop> findAllByIsPremiumGaming(){
        Specification<Laptop> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.like(root.get(Laptop_.MODEL), PREMIUM_MODEL_PATTERN),
                        criteriaBuilder.greaterThanOrEqualTo(root.get(Laptop_.PRICE), PREMIUM_PRICE)
                );

        return this.laptopRepository.findAll(spec);
    }


*/


}
