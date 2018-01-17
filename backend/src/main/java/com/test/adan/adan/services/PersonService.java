/**
 * 
 */
package com.test.adan.adan.services;

import java.util.List;

import com.test.adan.adan.jpa.Person;

/**
 * @author Aramis
 *
 */
public interface PersonService {

	void save(Person person);
	
	Person get(int id);
	
	List<Person> getByName(String name);
	
	List<Person> getByLastName(String lastName);
	
	List<Person> getAll();
	
	
}
