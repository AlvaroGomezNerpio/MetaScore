package com.MetaScore.MetaScore.dto;

import lombok.Data;
import java.util.List;

@Data
public class UsuarioDTO {
    private Long id;
    private String username;
    private String email;
    private String role; // Usamos String para simplificar el enum Role
    private List<ReseñaSimplificadaDTO> reseñas;
}
