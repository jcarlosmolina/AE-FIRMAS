package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.FamiliaNovedad;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.NovedadCT;
import com.keralty.aefirmas.persistence.SolicitudNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamiliaNovedadJpaRepository extends JpaRepository<FamiliaNovedad, Long>, FamiliaNovedadJpaRepositoryCustom {

    List<FamiliaNovedad> findByNovedadCT(NovedadCT novedadCT);

    List<FamiliaNovedad> findBySolicitud(SolicitudNovedad solicitud);

    List<FamiliaNovedad> findByFirma(Firma firma);
}
