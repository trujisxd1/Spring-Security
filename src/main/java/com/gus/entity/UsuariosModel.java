package com.gus.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "usuarios")
@Data

public class UsuariosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nombre;
    private String correo;
    private  String password;
    private  String token;

    private Date fecha;

    @ManyToOne()
    @JoinColumn(name="perfil_id")

    private PerfilModel perfilId;


    @ManyToOne()
    @JoinColumn(name="estados_id")

    private EstadosModel estadosId;

    public UsuariosModel(String nombre, String correo, String password, String token, Date fecha, PerfilModel perfilId, EstadosModel estadosId) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.token = token;
        this.fecha = fecha;
        this.perfilId = perfilId;
        this.estadosId = estadosId;
    }

    public UsuariosModel() {
    }
}
