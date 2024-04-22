package org.example.repositorios;

import org.example.entidades.Categoria;
import org.example.entidades.Supermercado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupermercadoRepository extends JpaRepository<Supermercado, Long> {
}
