package com.example.inventory.controllers;


import com.example.inventory.dtos.ProductRecordDto;
import com.example.inventory.entities.MyLinkedList;
import com.example.inventory.entities.SplayTree;
import com.example.inventory.models.ProductModel;
import com.example.inventory.repositories.CategoryRepository;
import com.example.inventory.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final SplayTree splayTree;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ProductController(SplayTree splayTree) {
        this.splayTree = splayTree;
    }
        @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }


    @PostMapping("/")
    public String saveProduct(@Valid ProductRecordDto productRecordDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "redirect:/cadastrar";
        }
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
        splayTree.insert(productModel);
        return "redirect:/";
    }
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable("id") long id, @Valid ProductRecordDto productRecordDto, BindingResult result, RedirectAttributes redirectAttributes) {

        ProductModel productModel = productRepository.findById(id).get();
        splayTree.remove(productModel.getIdProduct(), productModel);
        System.out.println(productModel.toString());
        BeanUtils.copyProperties(productRecordDto, productModel);
        productModel.setCategory(productRecordDto.getCategoryId());
        productModel.setQuantity(productRecordDto.getQuantity());
        System.out.println(productRecordDto.toString());

        splayTree.insert(productModel);
        productRepository.save(productModel); // Salva as alterações no banco de dados

        System.out.println("atualizado");
        System.out.println(productModel.toString());

        return "redirect:/"; // Redireciona para a página de listagem de produtos
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        // Verifica se o produto existe antes de tentar deletá-lo
        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            // Produto não encontrao
            return "redirect:/products";
        }

        ProductModel product = productOptional.get();
        splayTree.remove(product.getIdProduct(), product);
        productRepository.delete(product);


        return "redirect:/";
    }
    @PostMapping("/produto")
    public String searchProduct(@RequestParam("productName") String productName, Model model) {
        List<ProductModel> searchProducts = new ArrayList<>();
        for (ProductModel productModel : splayTree.searchByName(productName)){
            searchProducts.add(productModel);
        }
        model.addAttribute("searchProducts", searchProducts);
        return "buscarProduto";
    }



}
