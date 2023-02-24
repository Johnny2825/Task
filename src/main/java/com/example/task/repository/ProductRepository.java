package com.example.task.repository;

import com.example.task.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    List<Product> findAllByCountAfter(Long count);

    @Query(value = "SELECT p from Product p WHERE p.count < ?1")
    List<Product> findAllWhereCountLessThan(Long count);

    @Query(value = "SELECT * from Product WHERE Product.count > ?1", nativeQuery = true)
    List<Product> findAllWhereCountMoreThan(Long count);

}
