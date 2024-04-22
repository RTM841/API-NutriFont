package org.example.servicios;

import org.example.entidades.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoriaService {

    //Buscar categoria
    List<Categoria> findAll();

    //Añadir categoria
    Categoria create (Categoria categoria);

    //Actualizar categoria
    Optional<Categoria> update(Long id, Categoria categoria);

    //Borrar categoria
    Optional<Categoria> delete(Long id);
}
