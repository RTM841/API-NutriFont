package org.example.repositorios;

import org.example.entidades.Categoria;
import org.example.entidades.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
}
