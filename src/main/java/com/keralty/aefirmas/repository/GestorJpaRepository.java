package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestorJpaRepository extends JpaRepository<Gestor, Long>, GestorJpaRepositoryCustom {
}
