package com.example.inventory.controllers;
import com.example.inventory.models.CategoryModel;
import com.example.inventory.models.ProductModel;
import com.example.inventory.repositories.CategoryRepository;
import com.example.inventory.repositories.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
        //ordena os produtos do mais recente para o mais antigo pela data de criação
        List<ProductModel> produtos = productRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        List<CategoryModel> categories = categoryRepository.findAll();

        model.addAttribute("produtos", produtos);
        //to passando esse aqui agora, porque provavelmente também vou usar ele futuramente aqui.
        model.addAttribute("categories", categories);
        return "produtos";
    }
}



