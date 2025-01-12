package com.MetaScore.MetaScore.dto;

import lombok.Data;
import java.util.List;

@Data
public class ContenidoDTO {
    private Long id;
    private String titulo;
    private String tipo;
    private String descripcion;
    private String creador;
    private List<ReseñaSimplificadaDTO> reseñas;
}
