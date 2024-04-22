package org.example.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuario")
@Data //Genera lo getter and setter
@AllArgsConstructor //Genera un constructor con parámetros
@NoArgsConstructor //Genera un constructor sin parámetros
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera que la entidad sea auto incremental
    @Schema(example = "1", description = "Es el identificador del usuario")
    private int Id;

    @Schema(example = "Rubén", description = "Nombre del Usuario")
    @NotBlank
    private String nombre;

    @Schema(example = "Tejedera", description = "Apellido del Usuario")
    @NotBlank
    private String apellido;

    @Schema(example = "ruben841@gmail.com", description = "Correo del Usuario")
    @NotBlank
    private String correo;

    @Schema(example = "123456", description = "Contraseña del Usuario")
    @NotBlank
    private String contrasenia;

    private boolean enabled;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;


    @ManyToMany
    @JoinTable(name ="usu_rol",
            joinColumns = @JoinColumn(name = "user_Id"),
            inverseJoinColumns = @JoinColumn(name ="rol_Id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_Id", "rol_Id"})}
    )
    private List<Rol> roles;

    @ManyToOne
    @JoinColumn(name = "supermercado_Id")
    private Supermercado supermercado;

    @OneToMany(mappedBy = "usuario")
    private List<Receta> recetas;
}
