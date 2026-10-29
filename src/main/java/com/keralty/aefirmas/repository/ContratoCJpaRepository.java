package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.ContratoC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoCJpaRepository extends JpaRepository<ContratoC, Long>, ContratoCJpaRepositoryCustom {
}
