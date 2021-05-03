package com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import lombok.*;

@Entity
@Data
@Table(name = "tbl_matricula")
public class Matricula {

    @Column(name = "fecha_matricula",nullable = false)
    private LocalDate fechaMatricula;

    @Id
    @Column(name = "codRadicado_matricula")
    private long codRadicado;
    
    //@ManyToOne
    //@JoinColumn(name = "id_estudiante")
    //private Estudiante estudiante;
    
    /* @JsonIgnore
    @OneToOne(mappedBy = "matricula", fetch = FetchType.LAZY)
    private Estudiante estudiante; */

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_estudiante")
    private Estudiante estudiante;
}
