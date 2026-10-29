package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.FamiliaNovedad;
import com.keralty.aefirmas.persistence.NovedadCT;
import com.keralty.aefirmas.persistence.SolicitudNovedad;
import com.keralty.aefirmas.persistence.TipoIdentificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoIdentificacionJpaRepository extends JpaRepository<TipoIdentificacion, Long>, TipoIdentificacionJpaRepositoryCustom {

    List<TipoIdentificacion> findBySolicitudesNovedad(SolicitudNovedad solicitudesNovedad);

    List<TipoIdentificacion> findByFamiliasNovedad(FamiliaNovedad familiasNovedad);

    List<TipoIdentificacion> findByNovedadesCT(NovedadCT novedadesCT);
}
