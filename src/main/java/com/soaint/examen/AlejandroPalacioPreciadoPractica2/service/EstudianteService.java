package com.soaint.examen.AlejandroPalacioPreciadoPractica2.service;

import com.examen.AlejandroPalacioPreciadoPractica2.exceptions.BeanNotFoundException;
//import com.soaint.examen.AlejandroPalacioPreciadoPractica2.DTO.EstudianteDTO;
//import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Acudiente;
import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Estudiante;
//import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Matricula;
//import com.soaint.examen.AlejandroPalacioPreciadoPractica2.entity.Matricula;
import com.soaint.examen.AlejandroPalacioPreciadoPractica2.repository.EstudianteRepositoty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import javax.websocket.server.PathParam;
@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepositoty estudianteRepository;
    
    ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();

    public Estudiante agregarEstudiante(Estudiante estudiante) throws BeanNotFoundException{
        if (estudiante.getEdad() >= 18) {
        	throw new BeanNotFoundException("El estudiante no puede ser mayor de edad");
        }

		estudiante.addAcudiente(estudiante.getAcudiente());
		estudiante.addMatricula(estudiante.getMatricula());
        //estudiantes.add(estudiante);
        return estudianteRepository.save(estudiante);
    }

    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> mostrarEstudiante(Long id) throws BeanNotFoundException {
    	Optional<Estudiante> est = estudianteRepository.findById(id);
    	if(est.isPresent()) {
    		return est;
    	} else {
    		throw new BeanNotFoundException("El estudiante identificado: "+id+" no existe");
    	}
    }

    public Estudiante eliminarEstudiante(Long id)  throws BeanNotFoundException {
    	if(!estudianteRepository.existsById(id)) {
			//return null;
			throw new BeanNotFoundException("El estudiante identificado: "+id+" no existe");
		}
		Estudiante est = estudianteRepository.findById(id).get();
		
		if(!est.isEstado()) {
			//return null;
			throw new BeanNotFoundException("El estudiante ya esta inactivo en la institucion");
		}	
		est.setEstado(false);
		est.removeMatricula();
		return estudianteRepository.save(est);
    }

    public Estudiante actualizarEstudiante(long id,Estudiante estudiante) throws BeanNotFoundException{
    	if (!estudianteRepository.existsById(id)) {
			//return null;
			throw new BeanNotFoundException("El estudiante identificado: "+id+" no existe");
		}
    	
    	Estudiante est = estudianteRepository.findById(id).get();
    	
    	est.setNombre(estudiante.getNombre());
    	est.setEmail(estudiante.getEmail());
    	est.setEdad(estudiante.getEdad());
    	est.setEstado(estudiante.isEstado());
    	est.setGenero(estudiante.getGenero());
    	
    	return estudianteRepository.save(est);
    }

    public List<Estudiante> findByEstadoGenero(char genero) {
        return estudianteRepository.findByEstadoTrueAndGenero(genero);
    }
    
    /*public Estudiante crearMatricula(long idEstudiante, Matricula matricula) {
    	if (!estudianteRepository.existsById(idEstudiante)) {
			return null;
		}
    	
    	Estudiante est = estudianteRepository.findById(idEstudiante).get();
    	
    	matricula.setEstudiante(est);
    	
    	est.getMatriculas().add(matricula);
    	
    	return estudianteRepository.save(est);
    }*/
    
    /*public Estudiante crearAcudiente(long idEstudiante, Acudiente acudiente) {
    	if(!estudianteRepository.existsById(idEstudiante)) {
    		return null;
    	}
    	
    	Estudiante est = estudianteRepository.findById(idEstudiante).get();
    	
    	if(est.getAcudiente() != null) {
    		return null;
    	}
    	
    	acudiente.setEstudiante(est);
    	
    	est.setAcudiente(acudiente);
    	
    	return estudianteRepository.save(est);
    }*/

	/* @GET
	@PathParam("{estudianteId}")
	private Response findEstudiante(@PathParam("estudianteId") Long estudianteId) {
		Estudiante estudiante = EstudianteDTO.findEstudianteById(estudianteId);
		Matricula matricula = EstudianteDTO.findById(estudiante.get)
	} */
}
