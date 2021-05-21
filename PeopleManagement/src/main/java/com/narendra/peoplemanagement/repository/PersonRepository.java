package com.narendra.peoplemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.narendra.peoplemanagement.domain.Person;

/**
 * JPA implementation of {@link Person} Repository
 * @author Narendra Korrapati
 *
 */
public interface PersonRepository extends JpaRepository<Person, Integer>{

}
