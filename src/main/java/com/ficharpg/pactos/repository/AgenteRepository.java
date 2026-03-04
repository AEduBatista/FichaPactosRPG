package com.ficharpg.pactos.repository;

import com.ficharpg.pactos.model.Agente;
import com.ficharpg.pactos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AgenteRepository extends JpaRepository<Agente, Long> {

    // Busca todas as fichas que pertencem a um usuário específico
    List<Agente> findByUsuario(Usuario usuario);
}