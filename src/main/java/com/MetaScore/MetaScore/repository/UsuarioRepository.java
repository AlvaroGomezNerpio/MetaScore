package com.MetaScore.MetaScore.repository;


import com.MetaScore.MetaScore.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    // Método para verificar si existe un usuario con un email específico
    boolean existsByEmail(String email);
}
