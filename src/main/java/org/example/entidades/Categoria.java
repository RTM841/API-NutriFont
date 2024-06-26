package org.example.entidades;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "categoria")
@Data //Genera lo getter and setter
@AllArgsConstructor //Genera un constructor con parámetros
@NoArgsConstructor //Genera un constructor sin parámetros
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera que la entidad sea auto incremental
    @Schema(example = "1", description = "Es el identificador de la categoría")
    private int Id;

    @Schema(example = "Pescado", description = "Nombre de la categoría de la categoria")
    @NotBlank
    private String nombre;

    @Schema(example = "pescado", description = "Nombre de la imgane de la categoria")
    @NotBlank
    private String imgPath;
}
