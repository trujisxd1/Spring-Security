package com.gus.services;

import com.gus.entity.EstadosModel;
import com.gus.entity.PerfilModel;
import com.gus.repository.EstadosRepository;
import com.gus.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;


    public List<PerfilModel> listar(){

        return this.perfilRepository.findAll();
    }


    public void guardar(PerfilModel model){

        this.perfilRepository.save(model);
    }

    public PerfilModel buscarPorId(Long id){

        Optional<PerfilModel> optional =this.perfilRepository.findById(id);

        if (optional.isPresent()){

            return  optional.get();
        }

        return null;
    }

    public void eliminar(Long id){

        this.perfilRepository.deleteById(id);
    }
}
