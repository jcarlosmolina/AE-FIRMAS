package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Novedad;
import com.keralty.aefirmas.persistence.SolicitudNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovedadJpaRepository extends JpaRepository<Novedad, Long>, NovedadJpaRepositoryCustom {

    List<Novedad> findBySolicitudes(SolicitudNovedad solicitudes);
}
