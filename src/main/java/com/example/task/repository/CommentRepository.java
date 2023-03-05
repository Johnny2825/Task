package com.example.task.repository;

import com.example.task.entity.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByProductId(Long productId, Pageable pageable);

}
