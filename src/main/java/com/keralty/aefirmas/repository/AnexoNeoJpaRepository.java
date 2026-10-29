package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.AnexoNeo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnexoNeoJpaRepository extends JpaRepository<AnexoNeo, Long>, AnexoNeoJpaRepositoryCustom {
}
