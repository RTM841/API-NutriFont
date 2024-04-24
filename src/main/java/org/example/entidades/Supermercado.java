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
@Table(name = "supermercado")
@Data //Genera lo getter and setter
@AllArgsConstructor //Genera un constructor con parámetros
@NoArgsConstructor //Genera un constructor sin parámetros
public class Supermercado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera que la entidad sea auto incremental
    @Schema(example = "1", description = "Es el identificador del supermercado que en este caso solo hay uno , pero abierto a un ampliación en un futuro")
    private int Id;

    @Schema(example = "MarketCash", description = "Nombre del supermercado")
    @NotBlank
    private String nombre;

    @Schema(example = "C/Gran Capitan", description = "Es la dirección del supermercado")
    @NotBlank
    private String direccion;

    @Schema(example = "639754546", description = "Es el número del supermercado")
    @NotNull
    private int telefono;

    @OneToMany(mappedBy = "supermercado")
    private List<Producto> prodcutos;

    @OneToMany(mappedBy = "supermercado")
    private List<Usuario> usuarios;

}
