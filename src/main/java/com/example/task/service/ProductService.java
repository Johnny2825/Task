package com.example.task.service;

import com.example.task.dao.ProductDAO;
import com.example.task.dto.ProductWithRating;
import com.example.task.dto.PurchasedProductInput;
import com.example.task.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс сервис для работы с продуктами.
 */

@Service
public class ProductService {
    private final ProductDAO productDAO;
    private final Map<String, BigDecimal> currencies;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
        currencies = Map.of("USD", new BigDecimal("71.5"),
                "EUR", new BigDecimal("82.2"),
                "YUAN", new BigDecimal("11.4"));
    }

    /**
     * Поиск продукта по его индексу.
     *
     * @param id - индекс продукта.
     * @returns Product - продукт из базы.
     */

    public Product findById(UUID id) {
        return productDAO.findById(id);
    }

    /**
     * Поиск всех продуктов по названию категории.
     *
     * @param categoryName - название категории.
     * @returns List<Product> - список продуктов из одной категории.
     */

    public List<Product> findByCategoryName(String categoryName) {
        return productDAO.findByCategoryName(categoryName);
    }

    /**
     * Преобразование списка продуктов из одной категории в объект ProductWithRating
     * с полем averageRating и заполнение этого поля.
     * Пересчет стоимости продуктов в заданную валюту.
     *
     * @param categoryName - название категории.
     * @param currency - аббревиатура валюты в виде 3 символов.
     * @returns List<ProductWithRating> - список продуктов из одной категории.
     */

    public List<ProductWithRating> findByCategoryAndCurrency(String categoryName, String currency) {
        List<ProductWithRating> list = findByCategoryName(categoryName)
                .stream()
                .map(p -> new ProductWithRating()
                        .setAverageRating(new BigDecimal(BigInteger.ZERO))
                        .setId(p.getId())
                        .setName(p.getName())
                        .setDescription(p.getDescription())
                        .setCount(p.getCount())
                        .setPrice(p.getPrice()))
                .collect(Collectors.toList());
        if (currencies.containsKey(currency)) {
            for(ProductWithRating product : list) {
                product.setPrice(product.getPrice().divide(currencies.get(currency), 2, RoundingMode.HALF_UP));
                product.setAverageRating(BigDecimal.valueOf(productDAO.avgRating(product.getId())));
            }
        }
         return list;
    }

    /**
     * Изменение количества продуктов в базе на основе списка купленных.
     *
     * @param purchasedProductInputs - лист купленных продуктов.
     */

    public void changeCountOfProduct(List<PurchasedProductInput> purchasedProductInputs) {
        for (PurchasedProductInput product : purchasedProductInputs) {
            productDAO.updateCountById(product.getId(), product.getCount());
        }
    }
}
