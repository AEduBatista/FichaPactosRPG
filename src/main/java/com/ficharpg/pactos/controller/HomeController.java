package com.ficharpg.pactos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Quando alguém acessar a raiz do site ("/")
    @GetMapping("/")
    public String redirecionarParaHome() {
        // Redireciona automaticamente para o home.html
        return "redirect:/home.html";
    };
}