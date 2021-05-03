package com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;


@Entity
@Data
@Table(name = "tbl_acudiente")
public class Acudiente {

    @Column(name = "nombre_acudiente" ,length = 20,nullable = false)
    private String nombre;

    @Id
    @Column(name = "id_acudiente")
    private long id;

    @Column(name = "celular_acudiente",nullable = false,length = 20)
    private String celular;

    @Column(name = "direccion_acudiente",nullable = false,length = 40)
    private String direccion;
    
    //@OneToOne(fetch = FetchType.LAZY)
    //@OneToOne(cascade = {CascadeType.ALL})
    //@JoinColumn(name = "id_estudiante")
    //private Estudiante estudiante;
    
    /* @JsonIgnore
    @OneToOne(mappedBy = "acudiente", fetch = FetchType.LAZY)
    private Estudiante estudiante; */

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_estudiante")
    private Estudiante estudiante;
}
