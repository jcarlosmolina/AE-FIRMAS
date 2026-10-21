package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosJpaRepository extends JpaRepository<Productos, Long>, ProductosJpaRepositoryCustom {
}
