package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entidades.Producto;

import java.util.List;


@Builder
@Data //Genera lo getter and setter
@AllArgsConstructor //Genera un constructor con parámetros
@NoArgsConstructor //Genera un constructor sin parámetros
public class CategoriaDTO {

    private String nomrbe;

    private String imgPath;

}
