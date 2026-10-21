package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.RespRegional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespRegionalJpaRepository extends JpaRepository<RespRegional, Long>, RespRegionalJpaRepositoryCustom {

    List<RespRegional> findByFirma(Firma firma);
}
