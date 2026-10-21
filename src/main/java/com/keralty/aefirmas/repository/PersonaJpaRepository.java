package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaJpaRepository extends JpaRepository<Persona, Long>, PersonaJpaRepositoryCustom {
}
