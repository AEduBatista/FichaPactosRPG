package com.ficharpg.pactos.repository;

import com.ficharpg.pactos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

// Ao herdar de JpaRepository, o Java já cria todos os comandos de salvar e buscar automaticamente
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Podemos criar buscas personalizadas só dando o nome para elas!
    // Por exemplo, uma função que busca o usuário pelo email na hora do Login:
    Usuario findByEmail(String email);

    // Adicione esta linha: O Spring Boot vai criar a busca automática só por causa desse nome!
    Usuario findByNomeUsuario(String nomeUsuario);
}