package org.example.controladores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.aspectj.bridge.Message;
import org.example.entidades.Categoria;
import org.example.entidades.Receta;
import org.example.servicios.CategoriaService;
import org.example.servicios.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/receta")
@Tag(name = "receta", description = "Este es el controlador de receta , en el cual definimos las opreaciones básicas del CRUD")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtene una lista de recetas",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Receta.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "findAll", description = "Devuelve una lista de las canciones")
    @GetMapping
    public List<Receta> list(){
        return recetaService.findAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtene la receta, filtradas por el Id",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Receta.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "vista", description = "Devuelve una lista de las canciones por su id")
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Receta> recetaOptional = recetaService.findById(id);
        if(recetaOptional.isPresent()){
            return ResponseEntity.ok(recetaOptional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado ninguna cancion con ese Id");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado de forma correcta la receta",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Receta.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "No se ha creado de forma correcta la categoria",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "create", description = "Crea la categoria")
    @PostMapping
    public ResponseEntity<Receta> create(@RequestBody Receta receta){
        return ResponseEntity.status(HttpStatus.CREATED).body(recetaService.create(receta));
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha actualizado de forma correcta la receta",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Receta.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "No se ha actualizado de forma correcta la categoria",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "update", description = "Actualiza la canción")
    @PutMapping("/{id}")
    public ResponseEntity<Receta> update(@PathVariable Long id, @RequestBody Receta receta){
        Optional<Receta> recetaOptional = recetaService.update(id, receta);
        if(recetaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(recetaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha borrado de forma correcta la receta",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Receta.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "No se ha borrado de forma correcta la categoria",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "delete", description = "Borra la canción")
    @DeleteMapping("/{id}")
    public ResponseEntity<Receta> delete(@PathVariable Long id){
        Optional<Receta> recetaOptional = recetaService.delete(id);
        if(recetaOptional.isPresent()){
            return ResponseEntity.ok(
                    recetaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
