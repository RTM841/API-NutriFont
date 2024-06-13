package org.example.servicios;


import org.example.entidades.Receta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RecetaService {

    //Buscar receta
    List<Receta> findAll();

    //Buscar por ID
    Optional<Receta> findById(Long id);

    //Añadir receta
    Receta create (Receta receta);

    //Actualizar receta
    Optional<Receta> update(Long id, Receta receta);

    //Borrar receta
    Optional<Receta> delete(Long id);
}
