package com.soaint.examen.AlejandroPalacioPreciadoPractica2.repository;

import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//import java.util.Optional;

public interface EstudianteRepositoty extends JpaRepository<Estudiante,Long> {
	
    public List<Estudiante> findByEstadoTrueAndGenero(char genero);
}
