package com.example.task.service;

import com.example.task.dao.ProductDAO;
import com.example.task.entity.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.UUID;


@SpringBootTest
class ProductServiceTest {

    @MockBean
    private ProductDAO productDAO;

    @Test
    void findById_Should_Return_Product() {
        UUID id = UUID.randomUUID();
        Mockito.when(productDAO.findById(id)).thenReturn(new Product().setId(id));

    }

    @Test
    void findByCategoryName_Should_Return_List() {
        Mockito.when(productDAO.findByCategoryName("")).thenReturn(new ArrayList<>());
    }

}