package com.gus.jwt;

import com.gus.entity.UsuariosModel;
import com.gus.repository.UsuarioRepository;
import com.gus.services.EstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService  implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EstadosService estadosService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<UsuariosModel>userDetail=this.repository.findByCorreoAndEstadosId(username,this.estadosService.buscarPorId(1L));

        return userDetail.map(UserInfoDetails:: new).orElseThrow(()-> new UsernameNotFoundException("User not found" + username));
    }
}

