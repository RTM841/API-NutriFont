package org.example.repositorios;

import jakarta.transaction.Transactional;
import org.example.entidades.Categoria;
import org.example.entidades.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM receta_usu WHERE receta_id = :id", nativeQuery = true)
    void deleteRecetasRelacion(Long id);
}
