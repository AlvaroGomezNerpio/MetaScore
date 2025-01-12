package com.MetaScore.MetaScore.mapper;

import com.MetaScore.MetaScore.dto.*;
import com.MetaScore.MetaScore.model.*;

public class ReseñaMapper {

    public static ReseñaDTO toDto(Reseña reseña) {
        ReseñaDTO dto = new ReseñaDTO();
        dto.setId(reseña.getId());
        dto.setPuntuacion(reseña.getPuntuacion());
        dto.setComentario(reseña.getComentario());

        // Verificar si usuario no es nulo
        if (reseña.getUsuario() != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(reseña.getUsuario().getId());
            usuarioDTO.setUsername(reseña.getUsuario().getUsername());
            usuarioDTO.setEmail(reseña.getUsuario().getEmail());

            // Verificar si role no es nulo
            if (reseña.getUsuario().getRole() != null) {
                usuarioDTO.setRole(reseña.getUsuario().getRole().name());
            } else {
                usuarioDTO.setRole("USER"); // Valor por defecto si role es nulo
            }

            dto.setUsuario(usuarioDTO);
        }

        // Verificar si contenido no es nulo
        if (reseña.getContenido() != null) {
            ContenidoDTO contenidoDTO = new ContenidoDTO();
            contenidoDTO.setId(reseña.getContenido().getId());
            contenidoDTO.setTitulo(reseña.getContenido().getTitulo());
            contenidoDTO.setTipo(reseña.getContenido().getTipo());
            contenidoDTO.setDescripcion(reseña.getContenido().getDescripcion());
            contenidoDTO.setCreador(reseña.getContenido().getCreador());
            dto.setContenido(contenidoDTO);
        }

        return dto;
    }

}
