package org.example.repositorios;


import org.example.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombre(String nombre);

    @Query(value = "SELECT r.name FROM usuario u " +
            "JOIN usu_rol ur ON u.id = ur.user_id " +
            "JOIN rol r ON ur.rol_id = r.id " +
            "WHERE u.id = :userId", nativeQuery = true)
    String findRoleNamesByUserId(@Param("userId") int userId);


}
