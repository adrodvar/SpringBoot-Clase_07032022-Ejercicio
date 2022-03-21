package com.example.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "manufacturers")
public class Manufacturer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "num_employees")
    private Integer numEmployees;

    private Integer year;

    // @OneToOne(cascade = CascadeType.ALL) // cascade transmite la operación a la otra entidad
    @OneToOne // EAGER
    @JoinColumn(name = "id_direction", unique = true, foreignKey = @ForeignKey(name = "fk_manufacturer_direction")) // customizar la columna y fk
    private Direction direction;

    /*
    Va asociado al @ManyToOne del otro lado de la relación
    mappedBy indica que esta clase no es owner de la relación, el owner será la otra entidad
     */
    @OneToMany(mappedBy = "manufacturer")
    private List<Product> products = new ArrayList<>();

    public Manufacturer() {
    }

    public Manufacturer(Long id, String name, Integer numEmployees, Integer year) {
        this.id = id;
        this.name = name;
        this.numEmployees = numEmployees;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumEmployees() {
        return numEmployees;
    }

    public void setNumEmployees(Integer numEmployees) {
        this.numEmployees = numEmployees;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numEmployees=" + numEmployees +
                ", year=" + year +
                '}';
    }
}
