package com.ficharpg.pactos.controller;

import com.ficharpg.pactos.model.Usuario;
import com.ficharpg.pactos.repository.UsuarioRepository;
import com.ficharpg.pactos.service.RecaptchaService; // Import do nosso novo guardião
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    // O @Autowired "injeta" o nosso Guardião do Banco de Dados
    @Autowired
    private UsuarioRepository usuarioRepository;

    // O @Autowired "injeta" o nosso Interfone com o Google
    @Autowired
    private RecaptchaService recaptchaService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(
            @RequestBody Usuario novoUsuario,
            @RequestParam("g-recaptcha-response") String tokenCaptcha) {

        // 1. A PRIMEIRA COISA É PERGUNTAR PRO GOOGLE SE É HUMANO
        boolean isHumano = recaptchaService.verificarCaptcha(tokenCaptcha);

        if (!isHumano) {
            // Se o Google barrar, devolvemos erro 403 (Proibido)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Falha na verificação do reCAPTCHA. Você é um robô?");
        }

        // 2. Se for humano, tenta salvar no banco normalmente
        try {
            usuarioRepository.save(novoUsuario);
            return ResponseEntity.ok("Pacto firmado! Seus dados foram salvos nos registros.");
        } catch (Exception e) {
            // Se o email ou usuário já existir (regra unique = true)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: O nome de usuário ou e-mail já estão em uso.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> fazerLogin(@RequestBody Usuario dadosLogin) {

        // 1. Pede para o banco de dados procurar se existe alguém com este nome
        Usuario usuarioSalvo = usuarioRepository.findByNomeUsuario(dadosLogin.getNomeUsuario());

        // 2. Verifica se o usuário existe E se a senha digitada é igual à senha salva no banco
        if (usuarioSalvo != null && usuarioSalvo.getSenha().equals(dadosLogin.getSenha())) {
            // Sucesso!
            return ResponseEntity.ok("Acesso permitido!");
        } else {
            // Falha! (Usuário não existe ou senha errada)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro: Nome de usuário ou senha incorretos.");
        }
    }

    // 1. Busca os dados do jogador para mostrar na tela de Perfil
    @GetMapping("/{nomeUsuario}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable String nomeUsuario) {
        Usuario usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if (usuario != null) {
            return ResponseEntity.ok(usuario); // Devolve todos os dados do jogador
        }
        return ResponseEntity.notFound().build();
    }

    // 2. Atualiza os dados no banco quando o jogador clica em "Salvar Perfil"
    @PutMapping("/atualizar/{nomeUsuarioAtual}")
    public ResponseEntity<String> atualizarPerfil(@PathVariable String nomeUsuarioAtual, @RequestBody Usuario dadosAtualizados) {
        Usuario usuarioBanco = usuarioRepository.findByNomeUsuario(nomeUsuarioAtual);

        if (usuarioBanco != null) {
            // Verifica se o novo nome que ele escolheu já existe no banco (para não dar erro)
            if (!usuarioBanco.getNomeUsuario().equals(dadosAtualizados.getNomeUsuario()) &&
                    usuarioRepository.findByNomeUsuario(dadosAtualizados.getNomeUsuario()) != null) {
                return ResponseEntity.badRequest().body("Erro: Este nome de usuário já pertence a outro agente.");
            }

            // Atualiza os dados
            usuarioBanco.setNomeUsuario(dadosAtualizados.getNomeUsuario());
            usuarioBanco.setEmail(dadosAtualizados.getEmail());

            usuarioRepository.save(usuarioBanco); // Salva as alterações no MySQL
            return ResponseEntity.ok("Perfil atualizado com sucesso!");
        }
        return ResponseEntity.notFound().