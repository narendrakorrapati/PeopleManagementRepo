package com.narendra.peoplemanagement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * PersonDTO is used to receive the data from request body and validation.<br>
 * Successful processing converts the DTO to Domain object and stores in DB.
 * 
 * @author Narendra Korrapati
 *
 */
public class PersonDTO {

	private Integer personId;
	
	@NotBlank
	@Size(min = 3, max = 25)
	private String firstName;
	
	@NotBlank
	@Size(min = 3, max = 25)
	private String surname;
	
	
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}
