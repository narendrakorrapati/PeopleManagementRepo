package com.narendra.peoplemanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Person domain object created using JPA<br>
 * Natural sorting using personId
 * @author Narendra Korrapati
 *
 */
@Entity
@Table(name = "PERSON")
public class Person implements Serializable, Comparable<Person>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2223099077892440091L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSON_ID", precision = 10)
	private Integer personId;
	
	@Column(name = "FIRST_NAME", nullable = false, length = 25)
	private String firstName;
	
	@Column(name = "SURNAME", nullable = false, length = 25)
	private String surname;

	public Person() {
		
	}
	
	public Person(String firstName, String surname) {
		
		this.firstName = firstName;
		this.surname = surname;
	}

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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		} 
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof Person)) {
			return false;
		} else {
			Person other = (Person) obj;
			
			if(personId == null) {
				if(other.getPersonId() != null) {
					return false;
				}
			}else if(personId.compareTo(other.getPersonId()) !=0) {
				return false;
			}
			return true;
		}
	}

	/**
	 * Default sorting of Person based on personId
	 */
	@Override
	public int compareTo(Person o) {
		return this.personId.compareTo(o.getPersonId());
	}
	
}
