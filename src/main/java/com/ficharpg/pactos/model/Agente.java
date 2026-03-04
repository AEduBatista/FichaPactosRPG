package com.ficharpg.pactos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "agentes")
public class Agente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Informações Básicas
    private String nome = "";
    private String jogador = "";
    private String origem = "";
    private int nivel = 0;

    // Status Vitais
    private int vidaMaxima = 10;
    private int vidaAtual = 10;
    private int sanidadeMaxima = 70;
    private int sanidadeAtual = 70;
    private int energiaAtual = 1;
    private int energiaMaxima = 1;

    @Column(columnDefinition = "TEXT")
    private String periciasDados;

    @Column(columnDefinition = "TEXT")
    private String pactosDados;

    @Column(columnDefinition = "TEXT")
    private String inventarioDados;


    // Atributos Base
    private int forca = 0;
    private int agilidade = 0;
    private int inteligencia = 0;
    private int presenca = 0;
    private int vigor = 0;
    private int will = 0;

    // O Vínculo com o Jogador
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Agente() {
    }

    // --- GETTERS E SETTERS ---
    // (Pode gerar automaticamente no IntelliJ com Alt + Insert -> Getter and Setter, ou usar estes abaixo)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getJogador() { return jogador; }
    public void setJogador(String jogador) { this.jogador = jogador; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    public int getVidaMaxima() { return vidaMaxima; }
    public void setVidaMaxima(int vidaMaxima) { this.vidaMaxima = vidaMaxima; }

    public int getVidaAtual() { return vidaAtual; }
    public void setVidaAtual(int vidaAtual) { this.vidaAtual = vidaAtual; }

    public int getSanidadeMaxima() { return sanidadeMaxima; }
    public void setSanidadeMaxima(int sanidadeMaxima) { this.sanidadeMaxima = sanidadeMaxima; }

    public int getSanidadeAtual() { return sanidadeAtual; }
    public void setSanidadeAtual(int sanidadeAtual) { this.sanidadeAtual = sanidadeAtual; }

    public int getForca() { return forca; }
    public void setForca(int forca) { this.forca = forca; }

    public int getAgilidade() { return agilidade; }
    public void setAgilidade(int agilidade) { this.agilidade = agilidade; }

    public int getInteligencia() { return inteligencia; }
    public void setInteligencia(int inteligencia) { this.inteligencia = inteligencia; }

    public int getPresenca() { return presenca; }
    public void setPresenca(int presenca) { this.presenca = presenca; }

    public int getVigor() { return vigor; }
    public void setVigor(int vigor) { this.vigor = vigor; }

    public int getWill() { return will; }
    public void setWill(int will) { this.will = will; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public int getEnergiaAtual() { return energiaAtual; }
    public void setEnergiaAtual(int energiaAtual) { this.energiaAtual = energiaAtual; }

    public int getEnergiaMaxima() { return energiaMaxima; }
    public void setEnergiaMaxima(int energiaMaxima) { this.energiaMaxima = energiaMaxima; }

    public String getPericiasDados() { return periciasDados; }
    public void setPericiasDados(String periciasDados) { this.periciasDados = periciasDados; }

    public String getPactosDados() { return pactosDados; }
    public void setPactosDados(String pactosDados) { this.pactosDados = pactosDados; }

    public String getInventarioDados() { return inventarioDados; }
    public void setInventarioDados(String inventarioDados) { this.inventarioDados = inventarioDados; }
}