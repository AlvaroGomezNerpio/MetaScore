package com.MetaScore.MetaScore.controller;

import com.MetaScore.MetaScore.model.Contenido;
import com.MetaScore.MetaScore.repository.ContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contenidos")
public class ContenidoController {

    @Autowired
    private ContenidoRepository contenidoRepository;

    // GET /api/contenidos - Obtener todos los contenidos
    @GetMapping
    public List<Contenido> getAllContenidos() {
        return contenidoRepository.findAll();
    }

    // POST /api/contenidos - Crear un nuevo contenido
    @PostMapping
    public Contenido createContenido(@RequestBody Contenido contenido) {
        return contenidoRepository.save(contenido);
    }

    // GET /api/contenidos/{id} - Obtener un contenido por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Contenido> getContenidoById(@PathVariable Long id) {
        Optional<Contenido> contenido = contenidoRepository.findById(id);
        return contenido.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/contenidos/{id} - Actualizar un contenido por su ID
    @PutMapping("/{id}")
    public ResponseEntity<Contenido> updateContenido(@PathVariable Long id, @RequestBody Contenido contenidoDetails) {
        Optional<Contenido> optionalContenido = contenidoRepository.findById(id);

        if (optionalContenido.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Contenido contenido = optionalContenido.get();
        contenido.setTitulo(contenidoDetails.getTitulo());
        contenido.setTipo(contenidoDetails.getTipo());
        contenido.setDescripcion(contenidoDetails.getDescripcion());
        contenido.setCreador(contenidoDetails.getCreador());

        Contenido updatedContenido = contenidoRepository.save(contenido);
        return ResponseEntity.ok(updatedContenido);
    }

    // DELETE /api/contenidos/{id} - Eliminar un contenido por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContenido(@PathVariable Long id) {
        if (!contenidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        contenidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
