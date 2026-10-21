package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.oid.ReportesDominioOid;
import com.keralty.aefirmas.persistence.ReportesDominio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportesDominioJpaRepository extends JpaRepository<ReportesDominio, ReportesDominioOid>, ReportesDominioJpaRepositoryCustom {
}
