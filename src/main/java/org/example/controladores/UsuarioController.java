package org.example.controladores;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.aspectj.bridge.Message;
import org.example.entidades.Usuario;
import org.example.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "usuario", description = "Este es el controlador de usuarios como su nombre indica , aqui se haran las operaciones CRUD de usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Deuelve una lista de los usuarios",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "findAll", description = "Devuelve la lista de los usuarios que hay")
    @GetMapping
    public List<Usuario> list(){
        return usuarioService.findAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Creamos un usuario",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "create", description = "Acción mediante la cuál insertamos un usuario")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Nos resgistramos con el usuario",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))}),

            @ApiResponse(responseCode = "401",
                    description = "Error cuando la contraseña o el usuario no cumplen con los requisitos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "resgister", description = "Para resgitrar a un usuario")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Usuario usuario, BindingResult result){
        return create(usuario, result);
    }



    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Para validar el token",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))}),
    })
    @Operation(summary = "validation", description = "Es el método que nos valida el token")
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo "+ err.getField()+ " "+ err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Nos devuelve el usuario mediante su nombre",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "findUserByUsername", description = "Buscamos a un usuario para ver si existe mediante su nombre")
    @GetMapping("/{nombre}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String nombre) {
        UserDetails userDetails;
        try {
            userDetails = usuarioService.loadUserByNombre(nombre);
        } catch (UsernameNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún usuario con ese nombre");
        }

        // Aquí puedes devolver los detalles del usuario como una respuesta
        // Por ejemplo, puedes devolver solo el nombre de usuario por ahora
        Map<String, String> userResponse = new HashMap<>();
        userResponse.put("username", userDetails.getUsername());
        return ResponseEntity.ok(userResponse);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha actualizado de forma correcta el usuario",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "No se ha actualizado de forma correcta el usuario",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "update", description = "Actualiza la usuario")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario){
        Optional<Usuario> productOptional = usuarioService.update(id, usuario);
        if(productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha borrado de forma correcta un usuario",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "No se ha borrado de forma correcta el usuario",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "delete", description = "Borra un usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            Optional<Usuario> productOptional = usuarioService.delete(id);
            if(productOptional.isPresent()){
                return ResponseEntity.ok(productOptional.orElseThrow());
            }
            //return ResponseEntity.notFound().build();
        }catch (EmptyResultDataAccessException e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el usuario");
        }
        return null;
    }

    @Operation(summary = "get", description = "Obetener los roles del usuario ID")
    @GetMapping("/{userId}/roles")
    public String getRoleNamesByUserId(@PathVariable int userId) {
        return usuarioService.getRoleNamesByUserId(userId);
    }

    @Operation(summary = "get", description = "Obtener los correos de la BD")
    @GetMapping("/obt/correos")
    public List<String> getCorreos(){
        return usuarioService.findAllCorreos();
    }

    @Operation(summary = "get", description = "Obtener los nombres de los usuarios de la BD")
    @GetMapping("/obt/nombres")
    public List<String> getNombres(){
        return usuarioService.findAllNombres();
    }

    @Operation(summary = "get", description = "Obtener el id del usaurio introducido por su nombre de la BD")
    @GetMapping("/obt/id/{nombre}")
    public int getId(@PathVariable String nombre){
        return usuarioService.getIdUsuario(nombre);
    }

    @Operation(summary = "post", description = "Enviar el correo de verificiación de la cuenta")
    @PostMapping("/enviarEmail-cuenta")
    public boolean enviarEmailConfriación(@RequestBody Usuario usuario) {
        if ( usuarioService.sendVerificationEmail(usuario)){
            return true;
        }else
        {
            return false;
        }
    }

    @Operation(summary = "post", description = "Verificar que el usuairo dice quien ser mediante el código enviado y el introducido")
    @PostMapping("/verificar-cuenta")
    public boolean verificarCuenta(@RequestBody Usuario usuario, String codigoIngresado) {
        boolean verified = usuarioService.verifyUser(usuario, codigoIngresado);

        if (verified) {
            return true;
        } else {
            return false;
        }
    }



}
