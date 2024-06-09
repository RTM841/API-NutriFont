package org.example.repositorios;

import jakarta.transaction.Transactional;
import org.example.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM produc_super WHERE codigo_barras = :codigoDeBarras", nativeQuery = true)
    void deleteSupermercadoRelations(Long codigoDeBarras);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM prodcu_categoria WHERE codigo_barras = :codigoDeBarras", nativeQuery = true)
    void deleteCategoriaRelations(Long codigoDeBarras);
}
