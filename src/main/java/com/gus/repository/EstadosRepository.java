package com.gus.repository;

import com.gus.entity.EstadosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadosRepository extends JpaRepository<EstadosModel,Long> {
}
