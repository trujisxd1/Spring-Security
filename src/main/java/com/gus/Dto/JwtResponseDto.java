package com.gus.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponseDto {

    private Long id;
    private String nombre;
    private String perfil;
    private Long perfil_id;
    private String token;


}
