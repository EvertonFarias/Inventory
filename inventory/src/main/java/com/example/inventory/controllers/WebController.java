package com.example.inventory.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index"; // Retorna o nome do arquivo HTML (index.html)
    }

    @GetMapping("/cadastrar")
    public String cadastrar() {
        return "cadastrarProduto"; // Retorna o nome do arquivo HTML (cadastrarProduto.html)
    }
}

