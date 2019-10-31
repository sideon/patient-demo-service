package org.edison.patientdemoservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class PatientNotFoundException  extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	public PatientNotFoundException(String id) {
		super(String.format("Patient not found : '%s'",id));
		
		this.id=id;
	}

	public String getId() {
		return this.id;
	}
}
