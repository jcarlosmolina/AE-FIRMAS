package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.BeneficiarioC;
import com.keralty.aefirmas.persistence.oid.BeneficiarioCOid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiarioCJpaRepository extends JpaRepository<BeneficiarioC, BeneficiarioCOid>, BeneficiarioCJpaRepositoryCustom {
}
