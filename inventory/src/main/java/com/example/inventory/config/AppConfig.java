package com.example.inventory.config;

import com.example.inventory.entities.SplayTree;
import com.example.inventory.models.ProductModel;
import com.example.inventory.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    private final ProductRepository productRepository;
    private SplayTree splayTreeInstance = null;

    @Autowired
    public AppConfig(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    public SplayTree splayTree() {
        if (splayTreeInstance == null) {
            List<ProductModel> produtos = productRepository.findAll();
            SplayTree splayTree = new SplayTree();
            for (ProductModel produto : produtos) {
                splayTree.insert(produto);
            }
            splayTreeInstance = splayTree;
        }
        return splayTreeInstance;
    }
}

