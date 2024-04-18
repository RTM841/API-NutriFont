package org.example.entidades;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private double calorias;

    @Schema(example = "10", description = "Grasas saturadas del producto")
    @NotBlank
    private double grasaSaturada;

    @Schema(example = "0,4", description = "Hidratos de Carbono del producto")
    @NotBlank
    private double hidratos;

    @Schema(example = "0,3", description = "Azúcares del producto")
    @NotBlank
    private double azucares;

    @Schema(example = "10", description = "Proteínas del producto")
    @NotBlank
    private double proteinas;

    @Schema(example = "0,5", description = "Sal del prodcuto")
    @NotBlank
    private double sal;



    @Schema(example = "Ingredientes: Leche de Avena(80%),Cacao(20%)", description = "Ingredientes del producto")
    @NotBlank
    private String descripcion;

    @Schema(example = "True o False", description = "Si está disponible o el prodcuto o no")
    @NotBlank
    private boolean disponibilidad;


    //Información sobre los alérgenos

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean gluten;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean crustaceo;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean huevo;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean pescado;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean cacahuetes;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean soja;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean leche;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean proteinaLeche;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean lactosa;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean frutosCascara;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean apio;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean mostaza;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean sesamo;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean sulfitos;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean altramuces;

    @Schema(example = "True o False", description = "Si lleva ese alérgeno o no")
    @NotBlank
    private boolean moluscos;

    //Relaciones

    @ManyToOne
    @JoinColumn(name = "supermercado_Id")
    private Supermercado supermercado;

    @ManyToOne
    @JoinColumn(name = "categoria_Id")
    private Categoria categoria;


}
