package com.example.inventory.controllers;


import com.example.inventory.dtos.ProductRecordDto;
import com.example.inventory.models.ProductModel;
import com.example.inventory.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;


    @PostMapping("/products")
    public String saveProduct(@Valid ProductRecordDto productRecordDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "cadastrarProduto";
        }

        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);

        // Define a data de criação como a data e hora atuais
        productModel.setCreatedAt(LocalDateTime.now());

        productRepository.save(productModel);

        return "redirect:/products";
    }

}
