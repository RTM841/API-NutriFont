package org.example.servicios;

import org.example.entidades.Categoria;
import org.example.entidades.Producto;
import org.example.entidades.Supermercado;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SupermercadoService {

    //Buscar supermercado
    List<Supermercado> findAll();

    //Buscar por ID
    Optional<Supermercado> findById(Long id);

    //Añadir supermrecado
    Supermercado create (Supermercado supermercado);

    //Actualizar supermercado
    Optional<Supermercado> update(Long id, Supermercado supermercado);

    //Borrar supermrecado
    Optional<Supermercado> delete(Long id);
}
