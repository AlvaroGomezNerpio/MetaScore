package com.MetaScore.MetaScore.repository;

import com.MetaScore.MetaScore.model.Reseña;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReseñaRepository extends JpaRepository<Reseña, Long> {

    Optional<Reseña> findByUsuarioIdAndContenidoId(Long usuarioId, Long contenidoId);


}
