package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.FamiliaNovedad;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.NovedadCT;
import com.keralty.aefirmas.persistence.SolicitudNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmaJpaRepository extends JpaRepository<Firma, Long>, FirmaJpaRepositoryCustom {

    List<Firma> findBySolicitudNovedad(SolicitudNovedad solicitudNovedad);

    List<Firma> findByFamiliaNovedad(FamiliaNovedad familiaNovedad);

    List<Firma> findByNovedadesCT(NovedadCT novedadesCT);
}
