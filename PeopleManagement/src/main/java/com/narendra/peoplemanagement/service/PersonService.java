package com.narendra.peoplemanagement.service;

import java.util.List;

import com.narendra.peoplemanagement.domain.Person;
import com.narendra.peoplemanagement.dto.PersonDTO;

public interface PersonService {

	/**
	 * Saves the Person in DB and sets the generated personId to personDTO.
	 * @param personDTO
	 * @return {@link Person}
	 */
	Person savePerson(PersonDTO personDTO);

	/**
	 * Updates the Person in DB, throws {@link ResourceNotFoundException}  when the id is not found in DB
	 * @param personDTO
	 * @return {@link Person}
	 */
	Person updatePerson(PersonDTO personDTO);

	/**
	 * Deletes the Given personId in DB, throws {@link ResourceNotFoundException} when the id is not found in DB
	 * @param personDTO
	 */
	void deletePerson(Integer personId);

	/**
	 * Gets the Person count from DB.
	 * @return count
	 */
	Long getPersonCount();

	/**
	 * Gets all Persons from DB.
	 * @return count
	 */
	List<Person> getAllPersons();

	/**
	 * Gets a person using personId, throws {@link ResourceNotFoundException} when the id is not found in DB
	 * @return count
	 */
	Person getPersonById(Integer personId);

}
