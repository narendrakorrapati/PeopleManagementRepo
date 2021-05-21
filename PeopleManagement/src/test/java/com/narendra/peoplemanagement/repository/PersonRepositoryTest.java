package com.narendra.peoplemanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.narendra.peoplemanagement.domain.Person;

@DataJpaTest
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository repository;
	
	@DisplayName("Create Person Test")
	@Test
	public void testCreatePerson() {
		
		Person person = new Person();
		person.setFirstName("Narendra");
		person.setSurname("Korrapati");
		
		repository.save(person);
		
		assertNotNull(person.getPersonId(), "Create Person Test Failed");	
	}
	
	@DisplayName("Update Person Test")
	@Test
	public void testUpdatePerson() {
		
		//Since the data is lost after the server restart, creating a new Person to test the Update functionality
		Person person = createNewPerson();
		
		person.setPersonId(2);
		person.setFirstName("Narendra Update");
		person.setSurname("Korrapati");
		
		Person updatePerson = repository.save(person);
		
		assertEquals("Narendra Update", updatePerson.getFirstName());
	}

	@DisplayName("Count Persons")
	@Test
	public void testCountPersons() {
		
		//Since the data is lost after the server restart, creating a new Person to test the Count functionality
		createNewPerson();
		
		Long count = 1L;
		
		Long dbCount = repository.count();
		
		assertEquals(count, dbCount);
	}
	
	@DisplayName("Find all Persons")
	@Test
	public void testFindAllPersons() {
		
		//Since the data is lost after the server restart, creating a new Person to test the Count functionality
		createNewPerson();
		
		List<Person> allPersons = repository.findAll();
		
		assertThat(allPersons).size().isGreaterThan(0);
	}
	
	@DisplayName("Find by Id Persons")
	@Test
	public void testPersonFindById() {
		
		//Since the data is lost after the server restart, creating a new Person to test the Count functionality
		Person person = createNewPerson();
		
		Person fromDB = repository.findById(person.getPersonId()).get();
		
		assertNotNull(fromDB);
	}
	
	@DisplayName("Delete Person Test")
	@Test
	public void testDeletePerson() {
		
		//Since the data is lost after the server restart, creating a new Person to test the Delete functionality
		Person person = createNewPerson();
		Integer personId = person.getPersonId();
		
		boolean beforeDelete =  repository.findById(personId).isPresent();
		
		repository.delete(person);
		
		boolean afterDelete = repository.findById(personId).isPresent();
		
		assertTrue(beforeDelete);
		assertFalse(afterDelete);
	}
	
	/**
	 * Create one new Person for testing the functionality. This is not required while working with actual DB instead of H2
	 * @return
	 */
	private Person createNewPerson() {
		
		Person person = new Person();
		person.setFirstName("Narendra");
		person.setSurname("Korrapati");
		
		return repository.save(person);
		
	}
}
