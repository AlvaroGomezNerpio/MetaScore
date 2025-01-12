package com.MetaScore.MetaScore.controller;

import com.MetaScore.MetaScore.dto.ReseñaDTO;
import com.MetaScore.MetaScore.mapper.ReseñaMapper;
import com.MetaScore.MetaScore.model.Contenido;
import com.MetaScore.MetaScore.model.Reseña;
import com.MetaScore.MetaScore.model.Usuario;
import com.MetaScore.MetaScore.repository.ContenidoRepository;
import com.MetaScore.MetaScore.repository.ReseñaRepository;
import com.MetaScore.MetaScore.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/resenas")
public class ReseñaController {

    @Autowired
    private ReseñaRepository reseñaRepository;

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // GET /api/resenas - Obtener todas las reseñas
    @GetMapping
    public ResponseEntity<List<ReseñaDTO>> getAllResenas() {
        List<Reseña> resenas = reseñaRepository.findAll();
        List<ReseñaDTO> resenaDTOs = resenas.stream()
                .map(ReseñaMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resenaDTOs);
    }

    @PostMapping
    public ResponseEntity<ReseñaDTO> createResena(@Valid @RequestBody Reseña reseña) {

        // Verificar existencia de contenido y usuario
        Contenido contenido = contenidoRepository.findById(reseña.getContenido().getId())
                .orElseThrow(() -> new IllegalArgumentException("Contenido no encontrado"));

        Usuario usuario = usuarioRepository.findById(reseña.getUsuario().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Verificar si ya existe una reseña del usuario para el contenido
        if (reseñaRepository.existsByUsuarioIdAndContenidoId(usuario.getId(), contenido.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
        }

        // Asignar entidades existentes a la nueva reseña
        reseña.setContenido(contenido);
        reseña.setUsuario(usuario);

        // Guardar y convertir a DTO
        Reseña savedResena = reseñaRepository.save(reseña);
        return ResponseEntity.ok(ReseñaMapper.toDto(savedResena));
    }


    // GET /api/resenas/{id} - Obtener una reseña por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ReseñaDTO> getResenaById(@PathVariable Long id) {
        Optional<Reseña> reseñaOpt = reseñaRepository.findById(id);
        if (reseñaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ReseñaDTO reseñaDTO = ReseñaMapper.toDto(reseñaOpt.get());
        return ResponseEntity.ok(reseñaDTO);
    }

    // DELETE /api/resenas/{id} - Eliminar una reseña por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResena(@PathVariable Long id) {
        if (!reseñaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        reseñaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
