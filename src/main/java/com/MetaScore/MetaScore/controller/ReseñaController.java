package com.MetaScore.MetaScore.controller;

import com.MetaScore.MetaScore.model.Contenido;
import com.MetaScore.MetaScore.model.Reseña;
import com.MetaScore.MetaScore.repository.ContenidoRepository;
import com.MetaScore.MetaScore.repository.ReseñaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resenas")
public class ReseñaController {

    @Autowired
    private ReseñaRepository reseñaRepository;

    @Autowired
    private ContenidoRepository contenidoRepository;

    // GET /api/resenas - Obtener todas las reseñas
    @GetMapping
    public List<Reseña> getAllResenas() {
        return reseñaRepository.findAll();
    }

    // POST /api/resenas - Crear una nueva reseña
    @PostMapping
    public ResponseEntity<Reseña> createResena(@RequestBody Reseña reseña) {
        // Verificar que el contenido no es nulo y existe en la base de datos
        if (reseña.getContenido() == null || reseña.getContenido().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Contenido> contenidoOpt = contenidoRepository.findById(reseña.getContenido().getId());
        if (contenidoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Asignar el contenido encontrado a la reseña
        reseña.setContenido(contenidoOpt.get());

        // Guardar la reseña
        Reseña savedResena = reseñaRepository.save(reseña);

        // Devolver la reseña guardada con código de estado 200 (OK)
        return ResponseEntity.ok(savedResena);
    }


    // GET /api/resenas/{id} - Obtener una reseña por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Reseña> getResenaById(@PathVariable Long id) {
        Optional<Reseña> reseña = reseñaRepository.findById(id);
        return reseña.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
