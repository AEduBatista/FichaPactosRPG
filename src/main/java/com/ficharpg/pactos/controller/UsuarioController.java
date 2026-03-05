package com.ficharpg.pactos.controller;

import com.ficharpg.pactos.model.Usuario;
import com.ficharpg.pactos.repository.UsuarioRepository;
import com.ficharpg.pactos.service.RecaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RecaptchaService recaptchaService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(
            @RequestBody Usuario novoUsuario,
            @RequestParam("g-recaptcha-response") String tokenCaptcha) {

        boolean isHumano = recaptchaService.verificarCaptcha(tokenCaptcha);

        if (!isHumano) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Falha na verificação do reCAPTCHA. Você é um robô?");
        }

        try {
            usuarioRepository.save(novoUsuario);
            return ResponseEntity.ok("Pacto firmado! Seus dados foram salvos nos registros.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: O nome de usuário ou e-mail já estão em uso.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> fazerLogin(@RequestBody Usuario dadosLogin) {
        Usuario usuarioSalvo = usuarioRepository.findByNomeUsuario(dadosLogin.getNomeUsuario());

        if (usuarioSalvo != null && usuarioSalvo.getSenha().equals(dadosLogin.getSenha())) {
            return ResponseEntity.ok("Acesso permitido!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro: Nome de usuário ou senha incorretos.");
        }
    }

    @GetMapping("/{nomeUsuario}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable String nomeUsuario) {
        Usuario usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/atualizar/{nomeUsuarioAtual}")
    public ResponseEntity<String> atualizarPerfil(@PathVariable String nomeUsuarioAtual, @RequestBody Usuario dadosAtualizados) {
        Usuario usuarioBanco = usuarioRepository.findByNomeUsuario(nomeUsuarioAtual);

        if (usuarioBanco != null) {
            if (!usuarioBanco.getNomeUsuario().equals(dadosAtualizados.getNomeUsuario()) &&
                    usuarioRepository.findByNomeUsuario(dadosAtualizados.getNomeUsuario()) != null) {
                return ResponseEntity.badRequest().body("Erro: Este nome de usuário já pertence a outro agente.");
            }

            usuarioBanco.setNomeUsuario(dadosAtualizados.getNomeUsuario());
            usuarioBanco.setEmail(dadosAtualizados.getEmail());

            usuarioRepository.save(usuarioBanco);
            return ResponseEntity.ok("Perfil atualizado com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }
}