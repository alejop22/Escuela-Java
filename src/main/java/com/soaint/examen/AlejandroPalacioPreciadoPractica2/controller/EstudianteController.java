package com.soaint.examen.AlejandroPalacioPreciadoPractica2.controller;

import com.examen.AlejandroPalacioPreciadoPractica2.exceptions.BeanNotFoundException;
//import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Acudiente;
import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Estudiante;
//import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Matricula;
import com.soaint.examen.AlejandroPalacioPreciadoPractica2.service.EstudianteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import net.bytebuddy.implementation.bytecode.Throw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api
@RestController
public class EstudianteController {
    @Autowired
    EstudianteService estudianteService;

    @ApiOperation(value = "Crea estudiante menores de 18 años")
    @PostMapping(value = "/estudiante")
    private ResponseEntity<Estudiante> agregarEstudiantes(@RequestBody Estudiante estudiante) throws BeanNotFoundException {
        Estudiante est = estudianteService.agregarEstudiante(estudiante);
        if (est != null) {
            return new ResponseEntity<>(est, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Muestra la lista de estudiantes")
    @GetMapping(value = "/estudiante")
    private ResponseEntity<List<Estudiante>> listarEstudiantes() {
        List<Estudiante> fact = estudianteService.listarEstudiantes();
        return new ResponseEntity<>(fact,HttpStatus.OK);
    }

    @ApiOperation(value = "Muestra estudiantes por su numero de identificacion") 
    @GetMapping(value = "/estudiante/{estudianteId}")
    private ResponseEntity<Optional<Estudiante>> mostrarEstudiante(@PathVariable Long estudianteId) throws BeanNotFoundException {
        Optional<Estudiante> fact = estudianteService.mostrarEstudiante(estudianteId);
            return new ResponseEntity<>(fact,HttpStatus.OK);
    }
    
    @ApiOperation(value = "Elimina los estudiantes")
    @DeleteMapping(value = "/estudiante/{estudianteId}")
    private ResponseEntity<Estudiante> eliminarEstudiante(@PathVariable Long estudianteId) {
        Estudiante est = estudianteService.eliminarEstudiante(estudianteId);
        
        if (est == null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
        
        return new ResponseEntity<>(est,HttpStatus.OK);
    }

    @ApiOperation(value = "Actualiza los estudiantes")
    @PutMapping(value = "/estudiante/{estudianteId}")
    private ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable Long estudianteId, @RequestBody Estudiante estudiante) {
        Estudiante est = estudianteService.actualizarEstudiante(estudianteId,estudiante);
        return new ResponseEntity<>(est,HttpStatus.OK);
    }

    @ApiOperation(value = "Muestra los estudiantes por estado y por genero")
    @GetMapping(value = "/estudiante/activo/{genero}")
    private ResponseEntity<List<Estudiante>> mostrarEstudianteEstadoGenero(@PathVariable char genero) {
        List<Estudiante> est = estudianteService.findByEstadoGenero(genero);
        return new ResponseEntity<>(est,HttpStatus.OK);
    }
    
    /*@ApiOperation(value = "Crea y asigna las matriculas de los estudiantes")
    @PutMapping(value = "estudiante/{id}/matricula")
    private ResponseEntity<Estudiante> crearMatricula(@PathVariable long id, @RequestBody Matricula matricula) {
    	
    	Estudiante est = estudianteService.crearMatricula(id,matricula);
    	
    	if(est == null) {
    		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    	}
    	
    	return new ResponseEntity<>(est, HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "Crea y asigna el acudiente para cada estudiante")
    @PutMapping(value = "estudiante/{id}/acudiente")
    private ResponseEntity<Estudiante> crearAcudiente(@PathVariable long id, @RequestBody Acudiente acudiente) {
    	Estudiante est = estudianteService.crearAcudiente(id,acudiente);
    	
    	if(est == null) {
    		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    	}
    	
    	return new ResponseEntity<>(est, HttpStatus.CREATED);
    } */
}
