package com.soaint.examen.AlejandroPalacioPreciadoPractica2.service;

import com.examen.AlejandroPalacioPreciadoPractica2.exceptions.BeanNotFoundException;
import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Acudiente;
//import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Estudiante;
import com.soaint.examen.AlejandroPalacioPreciadoPractica2.repository.AcudienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcudienteService {
    @Autowired
    private AcudienteRepository acudienteRepository;

    public Acudiente agregarAcudiente(Acudiente acudiente) {
        return acudienteRepository.save(acudiente);
    }

    public List<Acudiente> listarAcudientes() {
        return acudienteRepository.findAll();
    }

    public Optional<Acudiente> mostrarAcudientes(Long id) throws BeanNotFoundException{
        if (id != null) {
            return acudienteRepository.findById(id);
        } else {
            throw new BeanNotFoundException("El acudiente identificado: "+id+" no existe");
        }
    }

    public void eliminarAcudiente(Long id) {
        acudienteRepository.deleteById(id);
    }

    public Acudiente actualizarAcudiente(Long id,Acudiente acudiente) throws BeanNotFoundException {
        if (!acudienteRepository.existsById(id)) {
			throw new BeanNotFoundException("El acudiente identificado: "+id+" no existe");
		}
        
        Acudiente acu = acudienteRepository.findById(id).get();
        
        acu.setNombre(acudiente.getNombre());
        acu.setCelular(acudiente.getCelular());
        acu.setDireccion(acudiente.getDireccion());
        
        return acudienteRepository.save(acu);
    }
}
