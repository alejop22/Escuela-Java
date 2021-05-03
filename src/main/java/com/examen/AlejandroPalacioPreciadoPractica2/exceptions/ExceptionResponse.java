package com.examen.AlejandroPalacioPreciadoPractica2.exceptions;

import java.util.Date;

import lombok.*;

@Data
@AllArgsConstructor
public class ExceptionResponse {
	private Date timestamp;
	private String mensaje;
	private String detalles;
	private String httpCodeMessage;
	
/* 	public ExceptionResponse(Date timestamp, String mensaje, String detalles, String httpCodeMessage) {
		super();
		this.timestamp = timestamp;
		this.mensaje = mensaje;
		this.detalles = detalles;
		this.httpCodeMessage = httpCodeMessage;
	} */
	
	
}
