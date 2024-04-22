package org.example.servicios;

import org.example.entidades.Categoria;
import org.example.entidades.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProdcutoService {

    //Buscar categoria
    List<Producto> findAll();

    //Buscar por ID
    Optional<Producto> findById(Long id);

    //Añadir categoria
    Producto create (Producto producto);

    //Actualizar categoria
    Optional<Producto> update(Long id, Producto producto);

    //Borrar categoria
    Optional<Producto> delete(Long id);
}
