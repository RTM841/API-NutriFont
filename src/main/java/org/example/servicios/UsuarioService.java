package org.example.servicios;


import org.example.entidades.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findAll();

    Usuario save(Usuario usuario);

    UserDetails loadUserByNombre(String nombre);

    //Actualizar Usuario
    Optional<Usuario> update(Long id, Usuario usuario);

    //Borrar cancion
    Optional<Usuario> delete(Long id);

    String getRoleNamesByUserId(int userId);



}

