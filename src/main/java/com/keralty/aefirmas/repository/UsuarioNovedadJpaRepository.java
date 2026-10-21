package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.FamiliaNovedad;
import com.keralty.aefirmas.persistence.UsuarioNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioNovedadJpaRepository extends JpaRepository<UsuarioNovedad, Long>, UsuarioNovedadJpaRepositoryCustom {

    List<UsuarioNovedad> findByFamiliaNovedad(FamiliaNovedad familiaNovedad);
}
