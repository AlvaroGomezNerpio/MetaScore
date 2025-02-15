package com.MetaScore.MetaScore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;


@Entity
@Table(name = "resenas", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "contenido_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reseña {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Min(1)
    @Max(100)
    @Column(nullable = false)
    private int puntuacion; // Rango de 1 a 10

    @Size(max = 1000, message = "El comentario no puede superar los 1000 caracteres")
    @Column(length = 1000)
    private String comentario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull(message = "El usuario no puede ser nulo")
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "contenido_id", nullable = false)
    @NotNull(message = "El contenido no puede ser nulo")
    private Contenido contenido;

}
