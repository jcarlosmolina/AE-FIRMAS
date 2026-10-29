package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Planes;
import com.keralty.aefirmas.persistence.SolicitudNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanesJpaRepository extends JpaRepository<Planes, Long>, PlanesJpaRepositoryCustom {

    List<Planes> findBySolicitudesNovedad(SolicitudNovedad solicitudesNovedad);
}
