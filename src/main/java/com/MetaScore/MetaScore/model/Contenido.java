package com.MetaScore.MetaScore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contenidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @Column(nullable = false)
    @NotNull(message = "El tipo es obligatorio")
    private String tipo; // Puede ser "PELÍCULA", "SERIE", "VIDEOJUEGO", etc.

    @Column(length = 1000)
    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcion;

    @Column(nullable = false)
    @NotBlank(message = "El creador es obligatorio")
    private String creador; // Director, autor, desarrollador, etc.

    @OneToMany(mappedBy = "contenido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reseña> reseñas = new ArrayList<>();

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public List<Reseña> getReseñas() {
        return reseñas;
    }

    public void setReseñas(List<Reseña> reseñas) {
        this.reseñas = reseñas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
