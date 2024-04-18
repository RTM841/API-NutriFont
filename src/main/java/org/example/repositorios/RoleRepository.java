package org.example.repositorios;


import org.example.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByName(String name);
}
