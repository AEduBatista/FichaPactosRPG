package com.ficharpg.pactos.controller;

import com.ficharpg.pactos.model.Agente;
import com.ficharpg.pactos.model.Usuario;
import com.ficharpg.pactos.repository.AgenteRepository;
import com.ficharpg.pactos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/agentes")
public class AgenteController {

    @Autowired
    private AgenteRepository agenteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar uma ficha nova em branco para o jogador
    @PostMapping("/criar/{nomeUsuario}")
    public ResponseEntity<Agente> criarAgente(@PathVariable String nomeUsuario) {
        Usuario dono = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if (dono != null) {
            Agente novoAgente = new Agente();
            novoAgente.setUsuario(dono);
            novoAgente.setNome("Novo Agente");
            novoAgente.setNivel(0);
            novoAgente.setOrigem("Desconhecida");
            return ResponseEntity.ok(agenteRepository.save(novoAgente));
        }
        return ResponseEntity.badRequest().build();
    }

    // Listar todas as fichas do jogador quando ele abre a página
    @GetMapping("/listar/{nomeUsuario}")
    public ResponseEntity<List<Agente>> listarAgentes(@PathVariable String nomeUsuario) {
        Usuario dono = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if (dono != null) {
            return ResponseEntity.ok(agenteRepository.findByUsuario(dono));
        }
        return ResponseEntity.ok(new ArrayList<>()); // Devolve uma lista vazia se não achar o dono, evitando o erro JSON
    }

    // Excluir a ficha
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirAgente(@PathVariable Long id) {
        agenteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // 1. Buscar UMA ficha específica pelo ID (Para abrir na página index.html)
    @GetMapping("/{id}")
    public ResponseEntity<Agente> buscarAgentePorId(@PathVariable Long id) {
        Agente agente = agenteRepository.findById(id).orElse(null);
        if (agente != null) {
            return ResponseEntity.ok(agente);
        }
        return ResponseEntity.notFound().build();
    }

    // 2. Guardar as alterações feitas na ficha (Dano, Sanidade, Atributos)
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Agente> atualizarFicha(@PathVariable Long id, @RequestBody Agente dadosAtualizados) {
        Agente fichaExistente = agenteRepository.findById(id).orElse(null);

        if (fichaExistente != null) {
            // Atualiza os dados básicos
            fichaExistente.setNome(dadosAtualizados.getNome());
            fichaExistente.setJogador(dadosAtualizados.getJogador());
            fichaExistente.setOrigem(dadosAtualizados.getOrigem());
            fichaExistente.setNivel(dadosAtualizados.getNivel());

            // AQUI ESTÁ! Ensinando o Java a guardar o link do ImgBB
            fichaExistente.setFotoUrl(dadosAtualizados.getFotoUrl());

            // Atualiza os Status Vitais
            fichaExistente.setVidaAtual(dadosAtualizados.getVidaAtual());
            fichaExistente.setVidaMaxima(dadosAtualizados.getVidaMaxima());
            fichaExistente.setSanidadeAtual(dadosAtualizados.getSanidadeAtual());
            fichaExistente.setSanidadeMaxima(dadosAtualizados.getSanidadeMaxima());
            fichaExistente.setEnergiaAtual(dadosAtualizados.getEnergiaAtual());
            fichaExistente.setEnergiaMaxima(dadosAtualizados.getEnergiaMaxima());

            // Atualiza os Atributos
            fichaExistente.setForca(dadosAtualizados.getForca());
            fichaExistente.setAgilidade(dadosAtualizados.getAgilidade());
            fichaExistente.setInteligencia(dadosAtualizados.getInteligencia());
            fichaExistente.setPresenca(dadosAtualizados.getPresenca());
            fichaExistente.setVigor(dadosAtualizados.getVigor());
            fichaExistente.setWill(dadosAtualizados.getWill());

            // Atualiza o pacote de perícias e listas
            fichaExistente.setPericiasDados(dadosAtualizados.getPericiasDados());
            fichaExistente.setPactosDados(dadosAtualizados.getPactosDados());
            fichaExistente.setInventarioDados(dadosAtualizados.getInventarioDados());

            // Guarda tudo no MySQL
            return ResponseEntity.ok(agenteRepository.save(fichaExistente));
        }

        return ResponseEntity.notFound().build();
    }
}