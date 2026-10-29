package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Parentesco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentescoJpaRepository extends JpaRepository<Parentesco, Long>, ParentescoJpaRepositoryCustom {
}
