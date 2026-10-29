package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalJpaRepository extends JpaRepository<Sucursal, Long>, SucursalJpaRepositoryCustom {
}
