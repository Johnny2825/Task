package com.example.task.service.product;

import com.example.task.entity.Product;
import com.example.task.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.task.utils.Utils.parseLongSafety;
import static java.util.Objects.isNull;

/**
 * Класс сервис для работы с продуктами.
 */

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements  ProductService {

    private final ProductRepository productRepository;

    /**
     * Поиск продукта по его индексу.
     *
     * @param idStr - индекс продукта.
     * @returns Product - продукт из базы.
     */

    public Product findById(String idStr) {
        if (isNull(idStr)) {
            return null;
        }
        Long id = parseLongSafety(idStr);
        if (isNull(id)) {
            return null;
        }
        return productRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    @Override
    public List<Product> findAllMoreThan(String value) {
        if (isNull(value)) {
            return null;
        }
        Long count = parseLongSafety(value);
        if (isNull(count)) {
            return null;
        }
        return productRepository.findAllWhereCountMoreThan(count);
    }

    @Override
    @Transactional
    public List<Product> test() {
        List<Product> productList = productRepository.findAll();
        Product productFromDb = productRepository.findById(1L).orElse(null);
        System.out.println(productFromDb);
//        productFromDb.setCount(3L);
//        productRepository.save(productFromDb);
        Product productFromDb2 = productRepository.findById(1L).orElse(null);
        System.out.println(productFromDb2);
        return productList;
    }

    //
//    /**
//     * Поиск всех продуктов по названию категории.
//     *
//     * @param categoryName - название категории.
//     * @returns List<Product> - список продуктов из одной категории.
//     */
//
//    public List<Product> findByCategoryName(String categoryName) {
//        return null;
////        return productRepository.findByCategoryName(categoryName);
//    }
//
//    public List<String> findAllByIds(List<String> ids) {
//        return productRepository.findAllByIds(ids, ids);
//    }
//
//    /**
//     * Преобразование списка продуктов из одной категории в объект ProductWithRating
//     * с полем averageRating и заполнение этого поля.
//     * Пересчет стоимости продуктов в заданную валюту.
//     *
//     * @param categoryName - название категории.
//     * @param currency     - аббревиатура валюты в виде 3 символов.
//     * @returns List<ProductWithRating> - список продуктов из одной категории.
//     */
//
//    public List<ProductWithRating> findByCategoryAndCurrency(String categoryName, String currency) {
////        List<ProductWithRating> list = findByCategoryName(categoryName)
////                .stream()
////                .map(ProductWithRating::new)
////                .collect(Collectors.toList());
////        if (currencies.containsKey(currency)) {
////            for(ProductWithRating product : list) {
////                product.setPrice(product.getPrice().divide(currencies.get(currency), 2, RoundingMode.HALF_UP));
////                product.setAverageRating(BigDecimal.valueOf(productRepository.avgRating(product.getId())));
////            }
////        }
////         return list;
//        return null;
//    }
//
//    /**
//     * Изменение количества продуктов в базе на основе списка купленных.
//     *
//     * @param purchasedProductInputs - лист купленных продуктов.
//     */
//
//    public void changeCountOfProduct(List<PurchasedProductInput> purchasedProductInputs) {
////        for (PurchasedProductInput product : purchasedProductInputs) {
////            productRepository.updateCountById(product.getId(), product.getCount());
////        }
//    }
//
//    public List<Product> findCategoryByComment(String comment) {
//        return productRepository.findCategoryByComment(comment);
//    }

}
