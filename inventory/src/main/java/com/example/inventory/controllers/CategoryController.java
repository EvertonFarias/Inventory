package com.example.inventory.controllers;


import com.example.inventory.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Controller
public class CategoryController {

    @Autowired
    ProductRepository productRepository;


}
