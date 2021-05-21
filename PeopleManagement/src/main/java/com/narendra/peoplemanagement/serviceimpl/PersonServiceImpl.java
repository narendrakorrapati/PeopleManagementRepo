package com.narendra.peoplemanagement.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narendra.peoplemanagement.domain.Person;
import com.narendra.peoplemanagement.dto.PersonDTO;
import com.narendra.peoplemanagement.exception.ResourceNotFoundException;
import com.narendra.peoplemanagement.repository.PersonRepository;
import com.narendra.peoplemanagement.service.PersonService;

/**
 * Implementation of {@link PersonService} 
 * @author Narendra Korrapati
 *
 */
@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Person savePerson(PersonDTO personDTO) {
		Person person = modelMapper.map(personDTO, Person.class);
		return repository.save(person);
	}
	
	@Override
	public Person updatePerson(PersonDTO personDTO) {
		Optional<Person> person = repository.findById(personDTO.getPersonId());
		
		if(person.isPresent()) {
			person.get().setFirstName(personDTO.getFirstName());
			person.get().setSurname(personDTO.getSurname());
			repository.save(person.get());
		} else {
			throw new ResourceNotFoundException("Person with id: " + personDTO.getPersonId() + " is not found");
		}
		
		return person.get();
	}
	
	@Override
	public void deletePerson(Integer personId) {
		
		Optional<Person> person = repository.findById(personId);
		
		if(person.isPresent()) {
			repository.delete(person.get());
		} else {
			throw new ResourceNotFoundException("Person with id: " + personId + " is not found");
		}
	}
	
	@Override
	public Long getPersonCount() {
		return repository.count();
	}
	
	@Override
	public List<Person> getAllPersons() {
		return repository.findAll();
	}
	
	@Override
	public Person getPersonById(Integer personId) {
		Optional<Person> person = repository.findById(personId);
		
		if(!person.isPresent()) {
			throw new ResourceNotFoundException("Person with id: " + personId + " is not found");
		}
		return person.get();
	}
}
