package org.example.servicios;

import org.example.entidades.Categoria;
import org.example.entidades.Supermercado;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SupermercadoService {

    //Buscar categoria
    List<Supermercado> findAll();

    //Añadir categoria
    Supermercado create (Supermercado supermercado);

    //Actualizar categoria
    Optional<Supermercado> update(Long id, Supermercado supermercado);

    //Borrar categoria
    Optional<Supermercado> delete(Long id);
}
