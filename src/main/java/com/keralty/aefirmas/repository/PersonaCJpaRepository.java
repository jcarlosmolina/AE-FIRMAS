package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.PersonaC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaCJpaRepository extends JpaRepository<PersonaC, Long>, PersonaCJpaRepositoryCustom {
}
