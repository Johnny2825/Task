package com.example.task.repository;

import com.example.task.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {

    @Query(value = "SELECT p from ProductEntity p WHERE p.count < ?1")
    List<ProductEntity> findAllWhereCountLessThan(Long count);

    @Query(value = "SELECT * from Product WHERE Product.count > ?1", nativeQuery = true)
    List<ProductEntity> findAllWhereCountMoreThan(Long count);

}
