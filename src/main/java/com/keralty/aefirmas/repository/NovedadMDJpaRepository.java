package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.FamiliaNovedad;
import com.keralty.aefirmas.persistence.NovedadMD;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovedadMDJpaRepository extends JpaRepository<NovedadMD, Long>, NovedadMDJpaRepositoryCustom {

    List<NovedadMD> findByFamiliaNovedad(FamiliaNovedad familiaNovedad);
}
