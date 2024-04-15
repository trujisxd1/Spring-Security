package com.gus.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "variables_globales")
@Data
public class VariablesGlobales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private  String valor;
}
