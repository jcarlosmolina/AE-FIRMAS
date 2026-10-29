package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.EstadoContrato;
import com.keralty.aefirmas.persistence.SolicitudNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoContratoJpaRepository extends JpaRepository<EstadoContrato, Long>, EstadoContratoJpaRepositoryCustom {

    List<EstadoContrato> findBySolicitudesNovedad(SolicitudNovedad solicitudesNovedad);
}
