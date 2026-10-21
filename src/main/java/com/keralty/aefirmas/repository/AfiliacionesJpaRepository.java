package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Afiliaciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AfiliacionesJpaRepository extends JpaRepository<Afiliaciones, Long>, AfiliacionesJpaRepositoryCustom {
}
