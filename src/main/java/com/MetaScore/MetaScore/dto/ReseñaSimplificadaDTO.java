package com.MetaScore.MetaScore.dto;

import lombok.Data;

@Data
public class ReseñaSimplificadaDTO {
    private Long id;
    private int puntuacion;
    private String comentario;
    private Long contenidoId;
}
