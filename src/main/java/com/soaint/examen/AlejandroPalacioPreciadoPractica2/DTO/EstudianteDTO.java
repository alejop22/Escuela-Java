package com.soaint.examen.AlejandroPalacioPreciadoPractica2.DTO;
import java.io.Serializable;
import java.time.LocalDate;

import lombok.*;

@Data
@AllArgsConstructor
public class EstudianteDTO  implements Serializable{

    private Long idEstudiante;
    private String nombreEstudiante;
    private int edadEstudiante;

    private Long idAcudiente;
    private String nombreAcudiente;
    private String numeroAcudiente;
    private String direccionAcudiente;

    private Long idMatricula;
    private LocalDate fechaMatricula;
}
