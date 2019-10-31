package org.edison.patientdemoservice.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.edison.patientdemoservice.model.Address;
import org.edison.patientdemoservice.model.Patient;
import org.springframework.data.jpa.domain.Specification;

public class PatientSpecs implements Specification<Patient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fistname;
	private String lastname;
	private String country;
	private String status;
	
	public PatientSpecs(String firstname, String lastname, String country, String status) {
		this.fistname = firstname;
		this.lastname = lastname;
		this.country = country;
		this.status = status;
	}

	@Override
	public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		Predicate predicate = null;
		if (fistname != null) {
			predicate = criteriaBuilder.or(criteriaBuilder.equal(root.<String>get("firstname"), fistname));
		}
		if (lastname != null) {
			if (predicate != null) {
				predicate = criteriaBuilder.or(predicate, criteriaBuilder.equal(root.<String>get("lastname"), lastname));
			} else {
				predicate = criteriaBuilder.or(criteriaBuilder.equal(root.<String>get("lastname"), lastname));
			}
		}
		if (country != null) {
			Join<Patient, Address> address = root.join("addresses", JoinType.LEFT);
			criteriaBuilder.equal(address.get("country"), country);
			if (predicate != null) {
				predicate = criteriaBuilder.or(predicate, criteriaBuilder.equal(address.get("country"), country));
			} else {
				predicate = criteriaBuilder.or(criteriaBuilder.equal(address.get("country"), country));
			}
			
		}
		if (status != null) {
			if (predicate != null) {
				predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.<String>get("status"), status));
			} else {
				predicate = criteriaBuilder.and(criteriaBuilder.like(root.<String>get("status"), status));
			}
		}
		query.distinct(true);
		
		return predicate;
	}

}
