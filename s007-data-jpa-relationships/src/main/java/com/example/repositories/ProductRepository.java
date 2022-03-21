package com.example.repositories;

import com.example.entities.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Métodos especiales: findBy....And....

    @Transactional
        // lo normal es añadir el transactional desde un service
    void deleteByName(String name);

    @Transactional
    @Modifying
    @Query("delete from Product p where p.price > :price")
    void deleteByPriceGreaterQuery(Double price);

//    @Query("select p from Product p join p.categories c where c.color = :color")
//    void findOneWithCategories();

    @Transactional
    @Modifying
    @Query("update Product p set p.name = :updatedName where p.price <= :price")
    void updateProductNameByPriceQuery(String updatedName, Double price);

    @Transactional
    @Modifying
    @Query(value = "update products set name = ? where price <= ?", nativeQuery = true)
    void updateProductNameByPriceNativeQuery(String updatedName, Double price);

    @Transactional
    @Modifying
    @Query(value = """
            insert into products (name, price, quantity, id_manufacturer)
            values (:name, :price, :quantity, :idManufacturer);
                    """, nativeQuery = true)
    void insertProduct(Long id, String name, Double price, Integer quantity, Long idManufacturer);


    @EntityGraph(attributePaths = "categories")
    @Query("select p from Product p where p.id = :id")
    Product findByIdWithCategories(Long id);


    @Query(value = "select p from Product p join fetch p.categories where p.id = :id")
    Product findByIdWithCategoriesJQPL(Long id);

}