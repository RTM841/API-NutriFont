package org.example.entidades;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "receta")
@Data //Genera lo getter and setter
@AllArgsConstructor //Genera un constructor con parámetros
@NoArgsConstructor //Genera un constructor sin parámetros
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera que la entidad sea auto incremental
    @Schema(example = "1", description = "Es el identificador de la receta")
    private int Id;

    @Schema(example = "Rissoto", description = "Nombre de la receta")
    @NotBlank
    private String nombre;

    @Schema(example = "2 Vasos de Agua, 1 Paquete de Queso, 450g de Arroz, Sal, Pimienta, 200g de Champioñones ", description = "Recursos de la receta")
    @NotBlank
    private String descripcion;

    @Schema(example = "1 h 20 min", description = "Tiempo de preparación de la receta")
    @NotBlank
    private String tiempoPreparacion;

    @Schema(example = "Buena", description = "Valoración de la receta (Dificultad)")
    @NotBlank
    private String valoracion;

    @Schema(example = "1.Ponemos a hevir el arroz" +
            "2.Salpimentamos" +
            "3.Reogamos todo junto con champiñones" +
            "4.une vez hecho hechar el queso por lo alto del plato", description = "Preparación de la receta")
    @NotBlank
    private String preparacion;

    @Schema(example = "1,2,3...", description = "Valoración de la receta (Dificultad)")
    @NotNull
    private int usuario;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name ="receta_usu",
            joinColumns = @JoinColumn(name = "receta_Id"),
            inverseJoinColumns = @JoinColumn(name ="usuario_Id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"receta_Id", "usuario_Id"})}
    )
    private List<Usuario> usuarios;
}
