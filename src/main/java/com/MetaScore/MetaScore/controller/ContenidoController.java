package com.MetaScore.MetaScore.controller;

import com.MetaScore.MetaScore.dto.ContenidoDTO;
import com.MetaScore.MetaScore.mapper.ContenidoMapper;
import com.MetaScore.MetaScore.model.Contenido;
import com.MetaScore.MetaScore.repository.ContenidoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contenidos")
public class ContenidoController {

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Autowired
    private ContenidoMapper contenidoMapper;

    // GET /api/contenidos - Obtener todos los contenidos
    @GetMapping
    public ResponseEntity<List<ContenidoDTO>> getAllContenidos() {
        List<Contenido> contenidos = contenidoRepository.findAll();
        List<ContenidoDTO> contenidoDTOs = contenidos.stream()
                .map(contenidoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(contenidoDTOs);
    }

    // POST /api/contenidos - Crear un nuevo contenido
    @PostMapping
    public ResponseEntity<ContenidoDTO> createContenido(@Valid @RequestBody Contenido contenido) {
        Contenido savedContenido = contenidoRepository.save(contenido);
        ContenidoDTO contenidoDTO = contenidoMapper.toDto(savedContenido);
        return ResponseEntity.ok(contenidoDTO);
    }

    // GET /api/contenidos/{id} - Obtener un contenido por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ContenidoDTO> getContenidoById(@PathVariable Long id) {
        return contenidoRepository.findById(id)
                .map(contenidoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/contenidos/{id} - Actualizar un contenido por su ID
    @PutMapping("/{id}")
    public ResponseEntity<ContenidoDTO> updateContenido(@PathVariable Long id, @Valid @RequestBody Contenido contenidoDetails) {
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
        ContenidoDTO contenidoDTO = contenidoMapper.toDto(updatedContenido);
        return ResponseEntity.ok(contenidoDTO);
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
