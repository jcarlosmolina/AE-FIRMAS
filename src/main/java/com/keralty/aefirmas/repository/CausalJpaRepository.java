package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Causal;
import com.keralty.aefirmas.persistence.SolicitudNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CausalJpaRepository extends JpaRepository<Causal, Long>, CausalJpaRepositoryCustom {

    List<Causal> findBySolicitudes(SolicitudNovedad solicitudes);
}
