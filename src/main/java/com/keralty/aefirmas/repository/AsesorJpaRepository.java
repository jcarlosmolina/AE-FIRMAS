package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Asesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsesorJpaRepository extends JpaRepository<Asesor, Long>, AsesorJpaRepositoryCustom {
}
