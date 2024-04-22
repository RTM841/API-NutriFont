package org.example.servicios;


import org.example.entidades.Receta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RecetaService {

    //Buscar categoria
    List<Receta> findAll();

    //Buscar por ID
    Optional<Receta> findById(Long id);

    //Añadir categoria
    Receta create (Receta receta);

    //Actualizar categoria
    Optional<Receta> update(Long id, Receta receta);

    //Borrar categoria
    Optional<Receta> delete(Long id);
}
