package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Dominio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DominioJpaRepository extends JpaRepository<Dominio, Long>, DominioJpaRepositoryCustom {
}
