package com.ficharpg.pactos.controller;

import com.ficharpg.pactos.model.Agente;
import com.ficharpg.pactos.model.Usuario;
import com.ficharpg.pactos.repository.AgenteRepository;
import com.ficharpg.pactos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Agente criarAgente(@PathVariable String nomeUsuario) {
        Usuario dono = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if (dono != null) {
            Agente novoAgente = new Agente();
            novoAgente.setUsuario(dono);
            return agenteRepository.save(novoAgente); // Salva no MySQL e devolve os dados
        }
        return null;
    }

    // Listar todas as fichas do jogador quando ele abre a página
    @GetMapping("/listar/{nomeUsuario}")
    public List<Agente> listarAgentes(@PathVariable String nomeUsuario) {
        Usuario dono = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if (dono != null) {
            return agenteRepository.findByUsuario(dono);
        }
        return null;
    }

    // Excluir a ficha
    @DeleteMapping("/excluir/{id}")
    public void excluirAgente(@PathVariable Long id) {
        agenteRepository.deleteById(id);
    }

    // 1. Buscar UMA ficha específica pelo ID (Para abrir na página index.html)
    @GetMapping("/{id}")
    public Agente buscarAgentePorId(@PathVariable Long id) {
        return agenteRepository.findById(id).orElse(null);
    }

    // 2. Guardar as alterações feitas na ficha (Dano, Sanidade, Atributos)
    @PutMapping("/atualizar/{id}")
    public Agente atualizarFicha(@PathVariable Long id, @RequestBody Agente dadosAtualizados) {
        Agente fichaExistente = agenteRepository.findById(id).orElse(null);

        if (fichaExistente != null) {
            // Atualiza os dados básicos
            fichaExistente.setNome(dadosAtualizados.getNome());
            fichaExistente.setJogador(dadosAtualizados.getJogador());
            fichaExistente.setOrigem(dadosAtualizados.getOrigem());
            fichaExistente.setNivel(dadosAtualizados.getNivel());

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
            // Atualiza o pacote de perícias
            fichaExistente.setPericiasDados(dadosAtualizados.getPericiasDados());
            // Atualiza as listas dinâmicas
            fichaExistente.setPactosDados(dadosAtualizados.getPactosDados());
            fichaExistente.setInventarioDados(dadosAtualizados.getInventarioDados());
            // Guarda tudo no MySQL
            return agenteRepository.save(fichaExistente);
        }


        return null;
    }
}