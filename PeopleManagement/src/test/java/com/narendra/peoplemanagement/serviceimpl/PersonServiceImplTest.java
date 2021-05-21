package com.narendra.peoplemanagement.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.narendra.peoplemanagement.domain.Person;
import com.narendra.peoplemanagement.dto.PersonDTO;
import com.narendra.peoplemanagement.exception.ResourceNotFoundException;
import com.narendra.peoplemanagement.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class PersonServiceImplTest {

	@MockBean
	private PersonRepository repository;
	
	@MockBean
	private ModelMapper modelMapper;
	
	@InjectMocks
	private PersonServiceImpl personService;
	
	@DisplayName("Test Save Person")
	@Test
	public void testSavePerson() {
		
		PersonDTO personDTO = new PersonDTO();
		personDTO.setFirstName("Narendra");
		personDTO.setSurname("Korrapati");
		
		Person personBefore = new Person("Narendra", "Korrapati");
		
		Person personAfter = new Person("Narendra", "Korrapati");
		personAfter.setPersonId(1);
		
		Mockito.when(modelMapper.map(personDTO, Person.class)).thenReturn(personBefore);
		Mockito.when(repository.save(personBefore)).thenReturn(personAfter);
		
		Person person = personService.savePerson(personDTO);
		
		assertNotNull(person.getPersonId());
	}
	
	@DisplayName("Test Update Person")
	@Test
	public void testUpdatePerson() {
		
		PersonDTO personDTO = new PersonDTO();
		personDTO.setPersonId(1);
		personDTO.setFirstName("Narendra Update");
		personDTO.setSurname("Korrapati");
		
		Person personBefore = new Person("Narendra", "Korrapati");
		personBefore.setPersonId(1);
		
		Optional<Person> personBeforeOptional = Optional.of(personBefore);
		
		Mockito.when(repository.findById(personDTO.getPersonId())).thenReturn(personBeforeOptional);
		
		Person person = personService.updatePerson(personDTO);
		
		assertEquals("Narendra Update", person.getFirstName());
	}
	
	@DisplayName("Test Update Personc throws exception when Person is not found")
	@Test
	public void testUpdatePersonException() {
		
		PersonDTO personDTO = new PersonDTO();
		personDTO.setFirstName("Narendra Update");
		personDTO.setSurname("Korrapati");
		
		Optional<Person> personBeforeOptional = Optional.empty();
		
		Mockito.when(repository.findById(personDTO.getPersonId())).thenReturn(personBeforeOptional);
		
		assertThrows(ResourceNotFoundException.class, () -> {
			 personService.updatePerson(personDTO);
		});
	}
	
	@DisplayName("Test Delete Person")
	@Test
	public void testDeletePerson() {
		
		Integer personId = 1;
		
		Person personBefore = new Person("Narendra", "Korrapati");
		personBefore.setPersonId(1);
		
		Optional<Person> personBeforeOptional = Optional.of(personBefore);
		
		Mockito.when(repository.findById(personId)).thenReturn(personBeforeOptional);
		
		personService.deletePerson(personId);
		
	}
	
	@DisplayName("Test Delete Person throws exception when id is not found")
	@Test
	public void testDeletePersonException() {
		
		Integer personId = 1;
		
		Optional<Person> personBeforeOptional = Optional.empty();
		
		Mockito.when(repository.findById(personId)).thenReturn(personBeforeOptional);
		
		assertThrows(ResourceNotFoundException.class, () -> {
			personService.deletePerson(personId);
		});
	}
	
	@DisplayName("Get Person by Id")
	@Test
	public void testGetPersonById() {
		
		Integer personId = 1;
		
		Person personBefore = new Person("Narendra", "Korrapati");
		personBefore.setPersonId(1);
		
		Optional<Person> personBeforeOptional = Optional.of(personBefore);
		
		Mockito.when(repository.findById(personId)).thenReturn(personBeforeOptional);
		
		Person person = personService.getPersonById(personId);
		
		assertNotNull(person);
	}
	
	@DisplayName("Get Person by Id throws exception when resource not found")
	@Test
	public void testGetPersonByIdException() {
		
		Integer personId = 1;
		
		Optional<Person> personBeforeOptional = Optional.empty();
		
		Mockito.when(repository.findById(personId)).thenReturn(personBeforeOptional);
		
		assertThrows(ResourceNotFoundException.class, () -> {
			personService.getPersonById(personId);
		});
	}
}
