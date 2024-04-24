package org.example.entidades;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data //Genera lo getter and setter
@AllArgsConstructor //Genera un constructor con parámetros
@NoArgsConstructor //Genera un constructor sin parámetros
public class Producto {

    @Id
    @Schema(example = "1", description = "Es el identificador del prodcuto, es el código resultante del escaneo")
    private int codigoBarras;


    @Schema(example = "Batido Avena Chocolate 250ml", description = "Nombre del producto")
    @NotBlank
    private String nombre;

    //Información del producto

    @Schema(example = "145", description = "Calorías del producto")
    @NotNull
    private double calorias;

    @Schema(example = "10", description = "Grasas saturadas del producto")
    @NotNull
    private double grasaSaturada;

    @Schema(example = "0,4", description = "Hidratos de Carbono del producto")
    @NotNull
    private double hidratos;

    @Schema(example = "0,3", description = "Azúcares del producto")
    @NotNull
    private double azucares;

    @Schema(example = "10", description = "Proteínas del producto")
    @NotNull
    private double proteinas;

    @Schema(example = "0,5", description = "Sal del prodcuto")
    @NotNull
    private double sal;



    @Schema(example = "Ingredientes: Leche de Avena(80%),Cacao(20%)", description = "Ingredientes del producto")
    @NotBlank
    private String descripcion;

    @Schema(example = "True o False", description = "Si está disponible o el prodcuto o no")
    @NotNull
    private boolean disponibilidad;


    //Información sobre los alérgenos

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean gluten;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean crustaceo;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean huevo;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean pescado;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean cacahuetes;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean soja;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean leche;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean proteinaLeche;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean lactosa;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean frutosCascara;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean apio;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean mostaza;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean sesamo;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean sulfitos;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean altramuces;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotNull
    private boolean moluscos;

    //Relaciones

    @ManyToOne
    @JoinColumn(name = "supermercado_Id")
    private Supermercado supermercado;

    @ManyToOne
    @JoinColumn(name = "categoria_Id")
    private Categoria categoria;


}
