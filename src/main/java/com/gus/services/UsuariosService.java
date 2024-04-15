package com.gus.services;


import com.gus.entity.PerfilModel;
import com.gus.entity.UsuariosModel;
import com.gus.repository.EstadosRepository;
import com.gus.repository.PerfilRepository;
import com.gus.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UsuariosService {



    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadosService estadosService;

    public List<UsuariosModel> listar(){

        return this.usuarioRepository.findAll();
    }


    public void guardar(UsuariosModel model){

        this.usuarioRepository.save(model);
    }

    public UsuariosModel buscarPorId(Long id){

        Optional<UsuariosModel> optional =this.usuarioRepository.findById(id);

        if (optional.isPresent()){

            return  optional.get();
        }

        return null;
    }

    public void eliminar(Long id){

        this.usuarioRepository.deleteById(id);
    }

    public UsuariosModel buscarPorCorreo(String correo){

        return this.usuarioRepository.findByCorreo(correo);
    }

    public UsuariosModel buscarPorCorreoActivo(String correo){

        Optional<UsuariosModel> optional = this.usuarioRepository.findByCorreoAndEstadosId(correo,this.estadosService.buscarPorId(1L));


        if (optional.isPresent()){

            return optional.get();
        }
        return null;
    }

}
