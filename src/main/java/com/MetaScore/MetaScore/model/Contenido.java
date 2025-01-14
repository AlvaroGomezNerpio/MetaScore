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
@Getter
@Setter
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

}
