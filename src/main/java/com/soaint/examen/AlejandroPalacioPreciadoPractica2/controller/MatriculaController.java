package com.soaint.examen.AlejandroPalacioPreciadoPractica2.controller;

import com.paas.swagger.annotations.ApiOperation;
//import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Estudiante;
import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Matricula;
import com.soaint.examen.AlejandroPalacioPreciadoPractica2.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

import java.util.List;
import java.util.Optional;

@Api
@RestController
public class MatriculaController {
    @Autowired
    MatriculaService matriculaService;

    @ApiOperation(value = "Crea matricula, sin estudiante")
    @PostMapping(value = "/matricula")
    private ResponseEntity<Matricula> agregarMatricula(@RequestBody Matricula matricula) {
        Matricula fact = matriculaService.agregarMatricula(matricula);
        return new ResponseEntity<>(fact, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Muestra la lista de matriculas")
    @GetMapping(value = "/matricula")
    private ResponseEntity<List<Matricula>> listarMatriculas() {
        List<Matricula> fact = matriculaService.listarMatriulas();
        return new ResponseEntity<>(fact,HttpStatus.OK);
    }

    @ApiOperation(value = "Muestra matricula por codigo")
    @GetMapping(value = "/matricula/{matriculaId}")
    private ResponseEntity<Optional<Matricula>> mostrarMatricula(@PathVariable Long matriculaId) {
        Optional<Matricula> fact = matriculaService.mostrarMatricula(matriculaId);
        if (fact.isPresent()) {
            return new ResponseEntity<>(fact,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Elimina matricula por codigo")
    @DeleteMapping(value = "/matricula/{matriculaId}")
    private ResponseEntity<String> eliminarMatricula(@PathVariable Long matriculaId) {
        Optional<Matricula> facts = matriculaService.mostrarMatricula(matriculaId);
        if (facts.isPresent()) {
            matriculaService.eliminarMatricula(matriculaId);
            return new ResponseEntity<>("Estudiante con id: "+matriculaId+" se le ha cambiado de estado",HttpStatus.OK);
        }
        return new ResponseEntity<>("El estudiante con id: "+matriculaId+" no existe",HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Actualiza la matricula")
    @PutMapping(value = "/matricula/{matriculaId}")
    private ResponseEntity<Matricula> actualizarMatricula(@RequestBody Matricula matricula, @PathVariable Long id) {
        Matricula mat = matriculaService.actualizarMatricula(id,matricula);
        return new ResponseEntity<>(mat,HttpStatus.OK);
    }
}
