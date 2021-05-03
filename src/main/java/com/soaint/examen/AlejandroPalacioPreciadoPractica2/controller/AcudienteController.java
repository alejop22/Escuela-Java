package com.soaint.examen.AlejandroPalacioPreciadoPractica2.controller;

import com.paas.swagger.annotations.ApiOperation;
import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Acudiente;
//import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Estudiante;
import com.soaint.examen.AlejandroPalacioPreciadoPractica2.service.AcudienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

import java.util.List;
import java.util.Optional;

@Api
@RestController
public class AcudienteController {
    @Autowired
    AcudienteService acudienteService;

    @ApiOperation(value = "Crea un acudiente, sin estudiante")
    @PostMapping(value = "/acudiente")
    private ResponseEntity<Acudiente> agregarAcudiente(@RequestBody Acudiente acudiente) {
        Acudiente fact = acudienteService.agregarAcudiente(acudiente);
        return new ResponseEntity<>(fact, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Muesta la lista de acudientes")
    @GetMapping(value = "/acudiente")
    private ResponseEntity<List<Acudiente>> listarAcudientes() {
        List<Acudiente> fact = acudienteService.listarAcudientes();
        return new ResponseEntity<>(fact,HttpStatus.OK);
    }

    @ApiOperation(value = "Muestra acudiente por codigo")
    @GetMapping(value = "/acudiente/{acudienteId}")
    private ResponseEntity<Optional<Acudiente>> mostrarAcudiente(@PathVariable Long acudienteId) {
        Optional<Acudiente> fact = acudienteService.mostrarAcudientes(acudienteId);
        if (fact.isPresent()) {
            return new ResponseEntity<>(fact,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Elimina acudiente por id")
    @DeleteMapping(value = "/acudiente/{acudienteId}")
    private ResponseEntity<String> eliminarAcudiente(@PathVariable Long acudienteId) {
        Optional<Acudiente> facts = acudienteService.mostrarAcudientes(acudienteId);
        if (facts.isPresent()) {
            acudienteService.eliminarAcudiente(acudienteId);
            return new ResponseEntity<>("Estudiante con id: "+acudienteId+" se le ha cambiado de estado",HttpStatus.OK);
        }
        return new ResponseEntity<>("El estudiante con id: "+acudienteId+" no existe",HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Actualiza los acudientes por id")
    @PutMapping(value = "/acudiente/{acudienteId}")
    private ResponseEntity<Acudiente> actualizarAcudiente(@RequestBody Acudiente acudiente, @PathVariable Long acudienteId) {
    	Acudiente acu = acudienteService.actualizarAcudiente(acudienteId,acudiente);
        return new ResponseEntity<>(acu,HttpStatus.OK);
    }
}
