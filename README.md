# 📜 PACTOS - Sistema de RPG de Mesa 🎲

![Status](https://img.shields.io/badge/Status-Em_Desenvolvimento-purple?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E)

> Um sistema web completo para gerenciamento de fichas de personagens de RPG de mesa, focado em investigação, sobrevivência e horror sobrenatural.

---

## 📖 Sinopse do Universo

O mundo como conhecemos é apenas uma fina camada de normalidade que esconde verdades perturbadoras. Entidades espreitam nas sombras, e o conhecimento do oculto cobra um preço alto da mente humana. 

No universo de **PACTOS**, os jogadores assumem o papel de **Agentes** — indivíduos que, por escolha ou destino, tiveram suas vidas entrelaçadas com o sobrenatural. Para combater as ameaças que espreitam a humanidade, muitas vezes é necessário recorrer às mesmas forças que tentam destruir o mundo, selando acordos perigosos. 

Aqui, a **Vontade (WILL)** é a linha tênue entre a sanidade e a loucura. Cada confronto drena a energia vital, cada descoberta consome a sanidade, e cada pacto assinado pode ser a salvação da equipe... ou a perdição do próprio agente.

---

## ⚙️ Funcionalidades do Sistema

O sistema foi desenhado para ser uma plataforma imersiva e responsiva durante as sessões de RPG, permitindo que os jogadores e o mestre acompanhem o progresso em tempo real.

- 🛡️ **Fichas Dinâmicas:** Gerenciamento em tempo real de Vida (PV), Sanidade (SAN) e Energia Vital (EV) com barras de status visuais.
- 📊 **Atributos Personalizados:** Sistema de rolagem baseado em 6 atributos centrais (WILL, AGI, INT, RIG, CAR, CON).
- 🎲 **Sistema de Perícias:** Cálculo automático de Treino + Bônus para 27 perícias diferentes.
- 📜 **Pactos e Inventário:** Listas dinâmicas estilo *accordion* (sanfona) empacotadas de forma inteligente via JSON no banco de dados.
- 🔐 **Autenticação:** Sistema de registro e login individual para cada jogador gerenciar apenas seus próprios agentes.

---

## 🛠️ Tecnologias Utilizadas

Este projeto é uma aplicação *Full-Stack* construída com arquitetura moderna para garantir persistência de dados segura e uma interface fluida.

### Front-end
* HTML5, CSS3 & JavaScript (ES6+)
* Bootstrap 5 (Layout responsivo e estilização de grids)
* Fetch API (Comunicação assíncrona com o servidor sem recarregar a página)

### Back-end
* **Java 17+**
* **Spring Boot** (Criação de APIs RESTful)
* **Spring Data JPA / Hibernate** (Mapeamento Objeto-Relacional)

### Banco de Dados
* **MySQL** (Persistência segura de usuários, agentes, perícias empacotadas em JSON, pactos e itens de inventário).

---

## 🚀 Como Executar o Projeto Localmente (FUTURAMENTE ABERTO AO PÚBLICO)

Para rodar este projeto na sua máquina para testes ou para mestrar uma sessão:

1. Clone este repositório:
   ```bash
   git clone [https://github.com/SEU_USUARIO/pactos.git](https://github.com/SEU_USUARIO/pactos.git)
