package com.narendra.peoplemanagement.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.narendra.peoplemanagement.domain.Person;
import com.narendra.peoplemanagement.dto.PersonDTO;
import com.narendra.peoplemanagement.service.PersonService;

/**
 * People management Rest Controller to manage CURD operations of the {@link Person} Resource
 * @author Narendra Korrapati
 *
 */
@RestController
@RequestMapping(path = "/api")
public class PeopleManagementRestController {

	@Autowired
	private PersonService personService;
	
	/**
	 * Api method to get all the {@link Person}, handles the GET request
	 * @return ResponseEntity
	 */
	@GetMapping(path = "/persons")
	public ResponseEntity<List<Person>> getAllPersons() {
		List<Person> persons = personService.getAllPersons();
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}
	
	/**
	 * Api method to get a {@link Person} by personId, handles the GET request, requires personId in the path variable
	 * @return ResponseEntity
	 */
	@GetMapping(path = "/persons/{personId}")
	public ResponseEntity<Person> getPersonById(@PathVariable("personId") Integer personId) {
		Person person = personService.getPersonById(personId);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	
	/**
	 * Api method to get count of persons, handles the GET request
	 * @return ResponseEntity
	 */
	@GetMapping(path = "/persons/count")
	public ResponseEntity<Long> getPersonsCount() {
		Long count = personService.getPersonCount();
		return new ResponseEntity<Long>(count, HttpStatus.OK);
	}
	
	/**
	 * Api method to create a new {@link Person}, handles the POST request
	 * @return ResponseEntity
	 */
	@PostMapping(path = "/persons")
	public ResponseEntity<Person> createPerson(@Valid @RequestBody PersonDTO personDTO) {
		Person person = personService.savePerson(personDTO);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{personId}").buildAndExpand(person.getPersonId())
				.toUri();
		responseHeaders.setLocation(newPollUri);
		
		return new ResponseEntity<Person>(person, responseHeaders, HttpStatus.CREATED);
	}
	
	/**
	 * Api method to update a {@link Person}, handles the PUT request
	 * @return ResponseEntity
	 */
	@PutMapping(path = "/persons/{personId}")
	public ResponseEntity<Person> updatePerson(@Valid @RequestBody PersonDTO personDTO,
			@PathVariable("personId") Integer personId) {
		
		personDTO.setPersonId(personId);//Update the person present in the path variable only.
		
		Person person = personService.updatePerson(personDTO);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	
	/**
	 * Api method to delete a {@link Person}, handles the DELETE request
	 * @return ResponseEntity
	 */
	@DeleteMapping(path = "/persons/{personId}")
	public ResponseEntity<Person> deletePerson(@PathVariable("personId") Integer personId) {
		personService.deletePerson(personId);
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	}
}
