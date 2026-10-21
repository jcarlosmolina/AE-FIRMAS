package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.AreaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaMedicaJpaRepository extends JpaRepository<AreaMedica, Long>, AreaMedicaJpaRepositoryCustom {
}
