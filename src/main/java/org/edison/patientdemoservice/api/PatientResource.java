package org.edison.patientdemoservice.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.edison.patientdemoservice.exception.PatientNotFoundException;
import org.edison.patientdemoservice.filter.PatientSpecs;
import org.edison.patientdemoservice.model.Patient;
import org.edison.patientdemoservice.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class PatientResource {
	@Autowired
	private PatientRepository patientRepository;
	
	@PostMapping("/patients")
	public ResponseEntity<Object> createPatient(@RequestBody Patient patient) {
		Patient _patient = patientRepository.save(patient);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(_patient.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/patients")
	public Page<Patient> retrievePatients(Pageable pageable,
										  @RequestParam(value = "firstname", required = false) String firstname,
										  @RequestParam(value = "lastname", required = false) String lastname,
										  @RequestParam(value = "country", required = false) String country,
										  @RequestParam(value = "status", required = false) String status) {
		Specification<Patient> specs =  new PatientSpecs(firstname, lastname, country, status);
		Pageable _pageable= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("firstname"));
		
		Page<Patient> page = null;
		if (firstname == null && lastname == null &&  country == null && status == null) {
			page = patientRepository.findAll(_pageable);
		} else {
			page = patientRepository.findAll(specs, _pageable);
		}
		return page;
	}
	
	@GetMapping("/patients/{id}")
	public Patient retrievePatient(@PathVariable long id) {
		Optional<Patient> patient = patientRepository.findById(id);

		if (!patient.isPresent())
			throw new PatientNotFoundException("id_" + id);

		return patient.get();
	}
	
	@DeleteMapping("/patients/{id}")
	public void deletPatient(@PathVariable long id) {
		patientRepository.deleteById(id);
	}
	
	
	@PutMapping("/patients/{id}")
	public ResponseEntity<Object> updatePatient(@RequestBody Patient Patient, @PathVariable long id) {

		Optional<Patient> PatientOptional = patientRepository.findById(id);

		if (!PatientOptional.isPresent())
			return ResponseEntity.notFound().build();
		Patient.setId(id);
		
		patientRepository.save(Patient);

		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Empty method just to verify the token
	 * 
	 * @return
	 */
	@PostMapping("/patients/verifytoken")
	public ResponseEntity<Object> verifyToken() {
		return ResponseEntity.noContent().build();
	}
	
}
