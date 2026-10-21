package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.EstadoBeneficiario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoBeneficiarioJpaRepository extends JpaRepository<EstadoBeneficiario, Long>, EstadoBeneficiarioJpaRepositoryCustom {
}
