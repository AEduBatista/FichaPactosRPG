package com.ficharpg.pactos.model; // O nome do seu pacote estará aqui

import jakarta.persistence.*; // Importa as ferramentas de Banco de Dados

// @Entity avisa ao Java: "Esta classe é uma Tabela no Banco de Dados!"
@Entity
// @Table permite escolher o nome exato da tabela lá no MySQL
@Table(name = "usuarios")
public class Usuario {

    // @Id diz que este campo é a "Chave Principal" (o identificador único)
    @Id
    // @GeneratedValue faz o MySQL contar sozinho (1, 2, 3...) para cada novo jogador
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Podemos usar @Column para dar regras aos campos (ex: não pode ser vazio, precisa ser único)
    @Column(nullable = false, unique = true)
    private String nomeUsuario;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    private String nivelInclinacao;

    // Construtor vazio
    public Usuario() {
    }

    // --- GETTERS E SETTERS ABAIXO ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getNivelInclinacao() { return nivelInclinacao; }
    public void setNivelInclinacao(String nivelInclinacao) { this.nivelInclinacao = nivelInclinacao; }
}