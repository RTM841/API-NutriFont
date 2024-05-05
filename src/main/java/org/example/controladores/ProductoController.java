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
import org.example.servicios.ProdcutoService;
import org.example.servicios.SupermercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/producto")
@Tag(name = "producto", description = "Este es el controlador de producto , en el cual definimos las opreaciones básicas del CRUD")
public class ProductoController {

    @Autowired
    private ProdcutoService prodcutoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtene una lista de productos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Producto.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "findAll", description = "Devuelve una lista de los productos")
    @GetMapping
    public List<Producto> list(){
        return prodcutoService.findAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtene el prodcuto, filtradas por el Id",
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
        Optional<Producto> productoOptional = prodcutoService.findById(id);
        if(productoOptional.isPresent()){
            return ResponseEntity.ok(productoOptional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado ninguna cancion con ese Id");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "El prodcuto se ha creado correctamente",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Producto.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "No se ha creado de forma correcta la categoria",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "create", description = "Crea los productos")
    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(prodcutoService.create(producto));
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "El prodcuto se ha actualizado correctamente",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Producto.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "No se ha actualizado de forma correcta la categoria",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "update", description = "Actualiza el producto")
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @RequestBody Producto producto){
        Optional<Producto> productoOptional = prodcutoService.update(id, producto);
        if(productoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "El prodcuto se ha borrado correctamente",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Producto.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "No se ha borrado de forma correcta la categoria",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "delete", description = "Borra el producto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> delete(@PathVariable Long id){
        Optional<Producto> productoOptional = prodcutoService.delete(id);
        if(productoOptional.isPresent()){
            return ResponseEntity.ok(productoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
