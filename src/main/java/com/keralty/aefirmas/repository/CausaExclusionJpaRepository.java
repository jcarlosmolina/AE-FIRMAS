package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.CausaExclusion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CausaExclusionJpaRepository extends JpaRepository<CausaExclusion, Long>, CausaExclusionJpaRepositoryCustom {
}
