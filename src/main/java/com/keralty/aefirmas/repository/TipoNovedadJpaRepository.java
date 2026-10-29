package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.TipoNovedad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoNovedadJpaRepository extends JpaRepository<TipoNovedad, Long>, TipoNovedadJpaRepositoryCustom {
}
