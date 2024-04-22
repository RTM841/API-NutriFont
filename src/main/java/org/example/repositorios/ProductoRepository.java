package org.example.repositorios;

import org.example.entidades.Producto;
import org.example.entidades.Supermercado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
