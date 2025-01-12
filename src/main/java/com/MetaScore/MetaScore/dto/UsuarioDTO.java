package com.MetaScore.MetaScore.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String username;
    private String email;
    private String role; // Usamos String para simplificar la respuesta del enum
}
