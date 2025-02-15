package com.MetaScore.MetaScore.dto;

import lombok.Data;

@Data
public class ReseñaDTO {
    private Long id;
    private int puntuacion;
    private String comentario;
    private UsuarioSimplificadoDTO usuario;
    private ContenidoSimplificadoDTO contenido;
}
