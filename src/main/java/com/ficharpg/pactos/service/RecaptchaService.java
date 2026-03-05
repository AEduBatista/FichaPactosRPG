package com.ficharpg.pactos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class RecaptchaService {

    // Puxa a chave secreta que guardamos no application.properties
    @Value("${google.recaptcha.secret}")
    private String recaptchaSecret;

    private static final String GOOGLE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public boolean verificarCaptcha(String captchaResponseToken) {
        // Se o jogador nem clicou na caixinha, já barra na hora
        if (captchaResponseToken == null || captchaResponseToken.isEmpty()) {
            return false;
        }

        // Prepara a ligação para o Google
        RestTemplate restTemplate = new RestTemplate();
        String urlCompleta = GOOGLE_VERIFY_URL + "?secret=" + recaptchaSecret + "&response=" + captchaResponseToken;

        try {
            // Faz a requisição e lê a resposta do Google (que vem em formato JSON)
            Map<String, Object> respostaDoGoogle = restTemplate.postForObject(urlCompleta, null, Map.class);

            // O Google devolve "success: true" se for humano, e "false" se for robô
            return (Boolean) respostaDoGoogle.get("success");

        } catch (Exception e) {
            System.out.println("Erro ao validar reCAPTCHA: " + e.getMessage());
            return false; // Na dúvida, barra o acesso.
        }
    }
}
