package com.MetaScore.MetaScore.dto;

import lombok.Data;

@Data
public class ReseñaDTO {
    private Long id;
    private int puntuacion;
    private String comentario;
    private UsuarioDTO usuario;
    private ContenidoDTO contenido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public ContenidoDTO getContenido() {
        return contenido;
    }

    public void setContenido(ContenidoDTO contenido) {
        this.contenido = contenido;
    }
}
