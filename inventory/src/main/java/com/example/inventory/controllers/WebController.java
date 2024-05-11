package com.example.inventory.controllers;
import com.example.inventory.models.ProductModel;
import com.example.inventory.repositories.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WebController {


    private final ProductRepository productRepository;

    public WebController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/cadastrar")
    public String cadastrar() {
        return "cadastrarProduto"; // Retorna o nome do arquivo HTML (cadastrarProduto.html)
    }

    @GetMapping("/products")
    public String showAllProducts(Model model) {
        // Ordena os produtos do mais recente para o mais antigo pela data de criação
        List<ProductModel> produtos = productRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        model.addAttribute("produtos", produtos);
        return "produtos";
    }
}



