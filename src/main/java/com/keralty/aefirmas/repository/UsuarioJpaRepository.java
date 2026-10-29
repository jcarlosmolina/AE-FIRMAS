package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.oid.UsuarioOid;
import com.keralty.aefirmas.persistence.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, UsuarioOid>, UsuarioJpaRepositoryCustom {
}
