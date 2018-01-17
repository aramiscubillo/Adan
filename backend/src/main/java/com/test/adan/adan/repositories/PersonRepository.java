/**
 * 
 */
package com.test.adan.adan.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.adan.adan.jpa.Person;

/**
 * @author Aramis
 *
 */
@Repository
public interface PersonRepository extends CrudRepository<Person,Integer>{
	
	List<Person> findAll();
	
	List<Person> findByName(String name);
	
	List<Person> findByLastName(String lastName);
}
