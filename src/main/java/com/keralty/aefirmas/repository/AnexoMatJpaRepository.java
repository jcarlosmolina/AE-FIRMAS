package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.AnexoMat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnexoMatJpaRepository extends JpaRepository<AnexoMat, Long>, AnexoMatJpaRepositoryCustom {
}
