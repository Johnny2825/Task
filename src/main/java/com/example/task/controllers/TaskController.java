package com.example.task.controllers;

import com.example.task.dto.CategoryInput;
import com.example.task.dto.CommentInput;
import com.example.task.dto.ProductWithRating;
import com.example.task.dto.PurchasedProductInput;
import com.example.task.entity.Comment;
import com.example.task.dto.mapper.CommentMapper;
import com.example.task.service.CommentService;
import com.example.task.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

/**
 * Класс контроллер.
 */

@Controller
public class TaskController {
    private final CommentService commentService;
    private final ProductService productService;

    public TaskController(CommentService commentService, ProductService productService) {
        this.commentService = commentService;
        this.productService = productService;
    }

    /**
     * Сохранение комментария.
     *
     * @param id - индекс продукта из адреса запроса.
     * @param input - объект класса CommentInput с полем "name", "content", "rating".
     */

    @PostMapping("/product/{id}/comments")
    @ResponseBody
    public void saveComment(@PathVariable("id") UUID id, @RequestBody CommentInput input) {
        Comment comment = CommentMapper.MAPPER.toComment(input);
        comment.setProduct(productService.findById(id));
        commentService.save(comment);
    }

    /**
     * Получение списка товара по указанной категории и с пересчетом указанную в валюте.
     *
     * @param input - объект класса CategoryInput с полем "name" и "currency".
     * @returns List<ProductWithRating> - лист продуктов с рейтингом.
     */

    @GetMapping("/category")
    @ResponseBody
    public List<ProductWithRating> getAllProductsByCategoryAndCurrency(@RequestBody CategoryInput input) {
        return productService.findByCategoryAndCurrency(input.getName(), input.getCurrency());
    }

    /**
     * Изменение количества товара (покупка).
     *
     * @param products - лист купленных продуктов.
     */

    @PostMapping("/cart")
    @ResponseBody
    public void createOrder(@RequestBody List<PurchasedProductInput> products) {
        productService.changeCountOfProduct(products);
    }


}
