package org.example.servicios;

import org.example.entidades.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    //Buscar categoria
    List<Categoria> findAll();


    //Añadir categoria
    Categoria save (Categoria categoria);

    //Actualizar categoria
    Optional<Categoria> update(Long id, Categoria categoria);

    //Borrar categoria
    Optional<Categoria> delete(Long id);
}
