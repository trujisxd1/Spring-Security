package com.gus.repository;

import com.gus.entity.EstadosModel;
import com.gus.entity.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuariosModel,Long> {


    UsuariosModel findByCorreo(String correo);

    Optional<UsuariosModel>findByCorreoAndEstadosId(String correo,EstadosModel estado);
}
