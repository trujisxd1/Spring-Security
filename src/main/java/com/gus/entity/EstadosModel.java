package com.gus.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estados")
@Data
public class EstadosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nombre;
}
