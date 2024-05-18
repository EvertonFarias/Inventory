package com.example.inventory.controllers;


import com.example.inventory.dtos.ProductRecordDto;
import com.example.inventory.models.CategoryModel;
import com.example.inventory.models.ProductModel;
import com.example.inventory.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }


    @PostMapping("/products")
    public String saveProduct(@Valid ProductRecordDto productRecordDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "cadastrarProduto";
        }
//        System.out.println("CategoryId received: " + productRecordDto.getCategoryId());
//        System.out.println("DTO received: " + productRecordDto.toString());




        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        productModel.setCategory(productRecordDto.getCategoryId()) ;
        System.out.println("COPIADO COM SUCESSO\n");
        System.out.println(productModel.toString());
        // Define a data de criação como a data e hora atuais
        productModel.setCreatedAt(LocalDateTime.now());
        productModel.setCategory(productRecordDto.getCategoryId());
        productModel.setQuantity(productRecordDto.getQuantity());
        productRepository.save(productModel);

        return "redirect:/";
    }
    @PutMapping("/products/{id}")
    public String updateProduct(@PathVariable("id") long id, @Valid ProductRecordDto productRecordDto, BindingResult result, RedirectAttributes redirectAttributes) {


        ProductModel productModel = productRepository.findById(id).get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        CategoryModel category = productRecordDto.getCategoryId();
        productModel.setCategory(category);

        productRepository.save(productModel); // Salva as alterações no banco de dados
        System.out.println("atualizado");
        System.out.println(productModel.toString());
        return "redirect:/products"; // Redireciona para a página de listagem de produtos

    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        // Verifica se o produto existe antes de tentar deletá-lo
        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            // Produto não encontrado, redirecionar ou retornar erro
            return "redirect:/products"; // Ou outra página de sua escolha
        }

        // Produto encontrado, proceda com a exclusão
        ProductModel product = productOptional.get();
        productRepository.delete(product);

        // Redireciona para a página de lista de produtos após a exclusão
        return "redirect:/";
    }



}
