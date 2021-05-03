package com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@Table(name = "tbl_estudiates")
public class Estudiante {
    @Column(name = "nombre_estudiante" ,length = 20,nullable = false)
    private String nombre;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_estudiante")
    private long id;

    @Column(name = "email_estudiante",length = 30)
    private String email;

    @Column(name = "edad_estudiante")
    private int edad;

    @Column(name = "estado_estudiante")
    private boolean estado;

    @Column(name = "genero_estudiante")
    @Enumerated(value = EnumType.STRING)
    private Genero genero;
    
    //@OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL, /*orphanRemoval = true,*/ fetch = FetchType.LAZY)
    //private Acudiente acudiente;
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante", orphanRemoval = true)
    //private List<Matricula> matriculas = new ArrayList<>();
    
    
    /* @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "fk_acudiente")
    private Acudiente acudiente;
    
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "fk_matricula")
    private Matricula matricula; */

    @OneToOne(mappedBy = "estudiante", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Acudiente acudiente;

    public void addAcudiente(Acudiente acudiente) {
        acudiente.setEstudiante(this);
        this.acudiente = acudiente;
    }

    public void removeAcudiente() {
        if (acudiente != null) {
            acudiente.setEstudiante(null);
            this.acudiente = null;
        }
    }

    @OneToOne(mappedBy = "estudiante", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Matricula matricula;

    public void addMatricula(Matricula matricula) {
        matricula.setEstudiante(this);
        this.matricula = matricula;
    }

    public void removeMatricula() {
        if (matricula != null) {
            matricula.setEstudiante(null);
            this.matricula = null;
        }
    }
}
