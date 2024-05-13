package com.example.inventory.controllers;
import com.example.inventory.entities.BinaryTree;
import com.example.inventory.models.CategoryModel;
import com.example.inventory.models.ProductModel;
import com.example.inventory.repositories.CategoryRepository;
import com.example.inventory.repositories.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class WebController {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public WebController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Model model) {
        List<CategoryModel> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "cadastrarProduto";
    }


    @GetMapping("/products")
    public String showAllProducts(Model model) {
        List<ProductModel> produtos = productRepository.findAll();
        BinaryTree binaryTree = new BinaryTree();

        for (ProductModel produto : produtos) {
            binaryTree.insert(produto);
        }

        List<CategoryModel> categories = categoryRepository.findAll();

        model.addAttribute("binaryTree", binaryTree);
        model.addAttribute("categories", categories);
        return "produtos";
    }

    @GetMapping("/products/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model) {
        // Busca o produto pelo ID
        System.out.println("aqui é o ID: "+ id);
        Optional<ProductModel> produtoOptional = productRepository.findById(id);
        ProductModel produto = produtoOptional.get();
        model.addAttribute("produto", produto);

        return "detalhesProduto";
    }


}



