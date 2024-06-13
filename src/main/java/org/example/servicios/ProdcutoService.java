package org.example.servicios;

import org.example.entidades.Categoria;
import org.example.entidades.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProdcutoService {

    //Buscar producto
    List<Producto> findAll();

    //Buscar por ID
    Optional<Producto> findById(Long id);

    //Añadir producto
    Producto create (Producto producto);

    //Actualizar producto
    Optional<Producto> update(Long id, Producto producto);

    //Borrar producto
    Optional<Producto> delete(Long id);
}
