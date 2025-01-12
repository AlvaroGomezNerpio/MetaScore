package com.MetaScore.MetaScore.controller;

import com.MetaScore.MetaScore.dto.UsuarioDTO;
import com.MetaScore.MetaScore.mapper.UsuarioMapper;
import com.MetaScore.MetaScore.model.Usuario;
import com.MetaScore.MetaScore.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    // GET /api/usuarios - Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDTOs = usuarioMapper.toDtoList(usuarios);
        return ResponseEntity.ok(usuarioDTOs);
    }

    // GET /api/usuarios/{id} - Obtener un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/usuarios - Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict si el email ya existe
        }
        Usuario savedUsuario = usuarioRepository.save(usuario);
        UsuarioDTO usuarioDTO = usuarioMapper.toDto(savedUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    // PUT /api/usuarios/{id} - Actualizar un usuario por su ID
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuarioDetails) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setUsername(usuarioDetails.getUsername());
                    usuario.setEmail(usuarioDetails.getEmail());
                    usuario.setPassword(usuarioDetails.getPassword());
                    usuario.setRole(usuarioDetails.getRole());
                    Usuario updatedUsuario = usuarioRepository.save(usuario);
                    UsuarioDTO usuarioDTO = usuarioMapper.toDto(updatedUsuario);
                    return ResponseEntity.ok(usuarioDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/usuarios/{id} - Eliminar un usuario por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}