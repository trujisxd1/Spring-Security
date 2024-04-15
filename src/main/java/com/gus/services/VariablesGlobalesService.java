package com.gus.services;

import com.gus.entity.PerfilModel;
import com.gus.entity.VariablesGlobales;
import com.gus.repository.PerfilRepository;
import com.gus.repository.VariablesGlobalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class VariablesGlobalesService {

    @Autowired
    private VariablesGlobalesRepository variablesGlobalesRepository;


    public List<VariablesGlobales> listar(){

        return this.variablesGlobalesRepository.findAll();
    }


    public void guardar(VariablesGlobales model){

        this.variablesGlobalesRepository.save(model);
    }

    public VariablesGlobales buscarPorId(Long id){

        Optional<VariablesGlobales> optional =this.variablesGlobalesRepository.findById(id);

        if (optional.isPresent()){

            return  optional.get();
        }

        return null;
    }

    public void eliminar(Long id){

        this.variablesGlobalesRepository.deleteById(id);
    }
}
