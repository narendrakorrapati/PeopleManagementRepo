package com.narendra.peoplemanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.narendra.peoplemanagement.domain.Person;
import com.narendra.peoplemanagement.dto.PersonDTO;
import com.narendra.peoplemanagement.serviceimpl.PersonServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PeopleManagementRestController.class)
@WithMockUser(username = "test", roles = "USER")
public class PeopleManagementRestControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@MockBean
	private PersonServiceImpl personService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}
	
	@Test
	public void testGetAllPersons() throws Exception {
		List<Person> persons = Arrays.asList(
				new Person("Narendra", "Korrapati"),
				new Person("Java", "Oracle")
		);
		
		Mockito.when(personService.getAllPersons()).thenReturn(persons);
		
		String url = "/api/persons";
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE); 
		
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andExpect(status().isOk()).andReturn();
		
		String actual = mvcResult.getResponse().getContentAsString();
		
		String expected = objectMapper.writeValueAsString(persons);
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testGetPersonById() throws Exception {
		List<Person> persons = new ArrayList<>();
		Person person1 = new Person("Narendra", "Korrapati");
		person1.setPersonId(1);
		
		Person person2 = new Person("Java", "Oracle");
		person2.setPersonId(2);
		
		persons.add(person1);
		persons.add(person2);
		
		Mockito.when(personService.getPersonById(1)).thenReturn(person1);
		
		String url = "/api/persons/1";
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE); 
		
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andExpect(status().isOk()).andReturn();
		
		String actual = mvcResult.getResponse().getContentAsString();
		
		String expected = objectMapper.writeValueAsString(persons.get(0));
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testCount() throws Exception {
		List<Person> persons = new ArrayList<>();
		Person person1 = new Person("Narendra", "Korrapati");
		person1.setPersonId(1);
		
		Person person2 = new Person("Java", "Oracle");
		person2.setPersonId(2);
		
		persons.add(person1);
		persons.add(person2);
		
		Mockito.when(personService.getPersonCount()).thenReturn(Long.valueOf(persons.size()));
		
		String url = "/api/persons/count";
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE); 
		
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andExpect(status().isOk()).andReturn();
		
		String actual = mvcResult.getResponse().getContentAsString();
		
		String expected = objectMapper.writeValueAsString(persons.size());
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testCreatePerson() throws Exception {

		PersonDTO personDTO = new PersonDTO();
		personDTO.setFirstName("Narendra");
		personDTO.setSurname("Korrapati");
		
		
		Person person = new Person("Narendra", "Korrapati");
		person.setPersonId(1);
		
		String url = "/api/persons/";
		
		Mockito.when(personService.savePerson(any(PersonDTO.class))).thenReturn(person);
		
		String personDTOJson = objectMapper.writeValueAsString(personDTO);
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post(url).content(personDTOJson).contentType(MediaType.APPLICATION_JSON_VALUE); 
		
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andExpect(status().isCreated()).andReturn();
		
		String actual = mvcResult.getResponse().getContentAsString();
		
		String expected = objectMapper.writeValueAsString(person);
	
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testUpdatePerson() throws Exception {

		PersonDTO personDTO = new PersonDTO();
		personDTO.setFirstName("Narendra Updated");
		personDTO.setSurname("Korrapati");
		
		
		Person person = new Person("Narendra Updated", "Korrapati");
		person.setPersonId(1);
		
		String url = "/api/persons/1";
		
		Mockito.when(personService.updatePerson(any(PersonDTO.class))).thenReturn(person);
		
		String personDTOJson = objectMapper.writeValueAsString(personDTO);
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.put(url).content(personDTOJson).contentType(MediaType.APPLICATION_JSON_VALUE); 
		
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andExpect(status().isOk()).andReturn();
		
		String actual = mvcResult.getResponse().getContentAsString();
		
		String expected = objectMapper.writeValueAsString(person);
		
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testDeletePerson() throws Exception {

		String url = "/api/persons/1";
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.delete(url); 
		
		mockMvc.perform(reqBuilder).andExpect(status().isNoContent()).andReturn();
		
	}
	
	
}
