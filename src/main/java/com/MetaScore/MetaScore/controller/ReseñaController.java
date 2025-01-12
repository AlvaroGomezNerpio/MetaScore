package com.MetaScore.MetaScore.controller;

import com.MetaScore.MetaScore.dto.ReseñaDTO;
import com.MetaScore.MetaScore.mapper.ReseñaMapper;
import com.MetaScore.MetaScore.model.Contenido;
import com.MetaScore.MetaScore.model.Reseña;
import com.MetaScore.MetaScore.model.Usuario;
import com.MetaScore.MetaScore.repository.ContenidoRepository;
import com.MetaScore.MetaScore.repository.ReseñaRepository;
import com.MetaScore.MetaScore.repository.UsuarioRepository;
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

//    // POST /api/resenas - Crear una nueva reseña
//    @PostMapping
//    public ResponseEntity<ReseñaDTO> createResena(@RequestBody Reseña reseña) {
//        // Verificar que el contenido no es nulo y existe en la base de datos
//        if (reseña.getContenido() == null || reseña.getContenido().getId() == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        Optional<Contenido> contenidoOpt = contenidoRepository.findById(reseña.getContenido().getId());
//        if (contenidoOpt.isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        // Asignar el contenido encontrado a la reseña
//        reseña.setContenido(contenidoOpt.get());
//
//        // Guardar la reseña
//        Reseña savedResena = reseñaRepository.save(reseña);
//
//        // Convertir la entidad guardada a DTO
//        ReseñaDTO reseñaDTO = ReseñaMapper.toDto(savedResena);
//
//        return ResponseEntity.ok(reseñaDTO);
//    }

    @PostMapping
    public ResponseEntity<ReseñaDTO> createResena(@RequestBody Reseña reseña) {
        // Verificar que el contenido no es nulo y existe en la base de datos
        if (reseña.getContenido() == null || reseña.getContenido().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Contenido> contenidoOpt = contenidoRepository.findById(reseña.getContenido().getId());
        if (contenidoOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Verificar que el usuario no es nulo y existe en la base de datos
        if (reseña.getUsuario() == null || reseña.getUsuario().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(reseña.getUsuario().getId());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Verificar si ya existe una reseña del usuario para el mismo contenido
        Optional<Reseña> existingResena = reseñaRepository.findByUsuarioIdAndContenidoId(
                reseña.getUsuario().getId(),
                reseña.getContenido().getId()
        );
        if (existingResena.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Devolver 409 Conflict si ya existe
        }

        // Asignar el contenido y el usuario encontrados a la reseña
        reseña.setContenido(contenidoOpt.get());
        reseña.setUsuario(usuarioOpt.get());

        // Guardar la reseña
        Reseña savedResena = reseñaRepository.save(reseña);

        // Convertir la entidad guardada a DTO
        ReseñaDTO reseñaDTO = ReseñaMapper.toDto(savedResena);

        return ResponseEntity.ok(reseñaDTO);
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
