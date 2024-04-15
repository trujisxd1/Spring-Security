package com.gus.entity;

import com.gus.Dto.JwtResponseDto;
import com.gus.Dto.LoginDto;
import com.gus.jwt.JwtService;
import com.gus.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @Autowired
    private UsuariosService usuarioService;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/auth/login")
    public ResponseEntity<?>login(@RequestBody LoginDto dto){

        UsuariosModel usuario= this.usuarioService.buscarPorCorreoActivo(dto.getCorreo());
            if (usuario==null){

            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<>(){
                {
                    put("mensaje", "Las credenciales ingresadas no son válidas");
                }
            });
        }else{
            if (this.passwordEncode.matches(dto.getPassword(), usuario.getPassword())){
                String token=this.jwtService.generarToken(usuario.getCorreo());

                return ResponseEntity.status(HttpStatus.OK).body(
                        new JwtResponseDto(
                                usuario.getId(), usuario.getNombre(), usuario.getPerfilId().getNombre(), usuario.getPerfilId().getId(),  token
                        )
                );
            }else{
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<>(){
                    {
                        put("mensaje", "Las credenciales ingresadas no son válidas");
                    }
                });
            }
        }
    }

    @GetMapping("/auth/refresh/{id}")

    public ResponseEntity<?>refresh (@PathVariable ("id") Long id){

        UsuariosModel usuario=this.usuarioService.buscarPorId(id);

        if (usuario==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<>(){
                {
                    put("mensaje","Ocurrio un error inesparado");
                }
            });
        }else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new JwtResponseDto( usuario.getId(), usuario.getNombre(), usuario.getPerfilId().getNombre(), usuario.getPerfilId().getId(), this.jwtService.generarToken(usuario.getCorreo())));
        }

    }
}
