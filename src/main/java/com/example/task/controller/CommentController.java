package com.example.task.controller;

import com.example.task.controller.dto.CommentDto;
import com.example.task.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Tarkhov Evgeniy
 */
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments/byproduct/{productId}")
    ResponseEntity<Page<CommentDto>> findAll(@PathVariable("productId") Long productId,
                                             @RequestParam(value = "size", required = false) Integer size,
                                             @RequestParam(value = "page", required = false) Integer page,
                                             Pageable pageable) {
        return ResponseEntity.ok(commentService.findAllCommentByProduct(productId, pageable));
    }

    @PostMapping("/comments/byproduct/{productId}")
    ResponseEntity<CommentDto> createComment(@PathVariable("productId") Long productId,
                                             @RequestBody @Valid  CommentDto commentDto) {
        return commentService.createComment(productId, commentDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/comments/{commentId}")
    ResponseEntity<CommentDto> findById(@PathVariable("commentId") Long commentId) {
        return commentService.findByCommentId(commentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping("/comments/{commentId}")
    ResponseEntity<CommentDto> updateComment(@PathVariable("commentId") Long commentId,
                                             @RequestBody @Valid CommentDto commentDto) {
        return commentService.updateComment(commentId, commentDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @DeleteMapping("/comments/{commentId}")
    ResponseEntity<CommentDto> deleteComment(@PathVariable("commentId") Long commentId) {
        return commentService.deleteComment(commentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
