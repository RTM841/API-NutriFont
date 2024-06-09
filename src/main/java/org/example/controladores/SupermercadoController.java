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
import org.example.entidades.Producto;
import org.example.entidades.Supermercado;
import org.example.servicios.CategoriaService;
import org.example.servicios.SupermercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supermercado")
@Tag(name = "supermercado", description = "Este es el controlador de supermercado , en el cual definimos las opreaciones básicas del CRUD")
public class SupermercadoController {

    @Autowired
    private SupermercadoService supermercadoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtene una lista de supermercados(En este caso solo esta disponible el nuestro)",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Supermercado.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "findAll", description = "Devuelve una lista de supermercados(En este caso solo esta disponible el nuestro)")
    @GetMapping
    public List<Supermercado> list(){
        return supermercadoService.findAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtene el supermercado, filtrado por el Id",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Producto.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "vista", description = "Devuelve una lista de las canciones por su id")
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Supermercado> supermercadoOptional = supermercadoService.findById(id);
        if(supermercadoOptional.isPresent()){
            return ResponseEntity.ok(supermercadoOptional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado ninguna cancion con ese Id");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado el supermercado de forma correcta",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Supermercado.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "No se ha creado de forma correcta la categoria",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "create", description = "Crea el supermercado")
    @PostMapping
    public ResponseEntity<Supermercado> create(@RequestBody Supermercado supermercado){
        return ResponseEntity.status(HttpStatus.CREATED).body(supermercadoService.create(supermercado));
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha actualizado de forma correcta el supermercado",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Supermercado.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "No se ha actualizado de forma correcta la categoria",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "update", description = "Actualiza el supermercado")
    @PutMapping("/{id}")
    public ResponseEntity<Supermercado> update(@PathVariable Long id, @RequestBody Supermercado supermercado){
        Optional<Supermercado> supermercadoOptional = supermercadoService.update(id, supermercado);
        if(supermercadoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(supermercadoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha borrado de forma correcta el supermercado",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Supermercado.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "No se ha borrado de forma correcta la categoria",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "delete", description = "Borra el supermercado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Supermercado> delete(@PathVariable Long id){
        Optional<Supermercado> supermercadoOptional = supermercadoService.delete(id);
        if(supermercadoOptional.isPresent()){
            return ResponseEntity.ok(supermercadoOptional.orElseThrow());
        }else {
            return ResponseEntity.notFound().build();
        }

    }

}
