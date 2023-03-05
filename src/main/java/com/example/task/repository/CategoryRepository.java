package com.example.task.repository;

import com.example.task.entity.CategoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Long> {
}
