package org.example.entidades;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1", description = "Es el identificador del rol, numerico , autoincremental")
    private Long id;


    @Column(unique = true)
    @Schema(example = "ROLE_ARTISTA", description = "Es el nombre del rol, IMPORTANTE que empiece por ROLE ya que así lo hemos definido")
    private String name;

}
