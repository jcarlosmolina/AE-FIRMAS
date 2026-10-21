package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.DireccionC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionCJpaRepository extends JpaRepository<DireccionC, Long>, DireccionCJpaRepositoryCustom {
}
