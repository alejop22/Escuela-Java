package com.soaint.examen.AlejandroPalacioPreciadoPractica2.service;

import com.examen.AlejandroPalacioPreciadoPractica2.exceptions.BeanNotFoundException;
//import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Estudiante;
import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Matricula;
import com.soaint.examen.AlejandroPalacioPreciadoPractica2.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {
    @Autowired
    private MatriculaRepository matriculaRepository;

    public Matricula agregarMatricula(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    public List<Matricula> listarMatriulas() {
        return matriculaRepository.findAll();
    }

    public Optional<Matricula> mostrarMatricula(Long id)  throws BeanNotFoundException {
        if (id != null) {
            return matriculaRepository.findById(id);
        } else {
            throw new BeanNotFoundException("la matricula: "+id+" no existe");
        }
    }

    public void eliminarMatricula(Long id) {
        matriculaRepository.deleteById(id);
    }

    public Matricula actualizarMatricula(Long id, Matricula matricula) throws BeanNotFoundException {
    	if (!matriculaRepository.existsById(id)) {
			throw new BeanNotFoundException("la matricula: "+id+" no existe");
		}
    	
    	Matricula mat = matriculaRepository.findById(id).get();
    	
    	mat.setFechaMatricula(matricula.getFechaMatricula());
    	
    	return matriculaRepository.save(mat);
    }
}
