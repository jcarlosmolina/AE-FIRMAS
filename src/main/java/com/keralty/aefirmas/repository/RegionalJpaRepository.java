package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Regional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionalJpaRepository extends JpaRepository<Regional, Long>, RegionalJpaRepositoryCustom {
}
