/**
 * 
 */
package com.test.adan.adan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.adan.adan.jpa.Person;
import com.test.adan.adan.repositories.PersonRepository;

/**
 * @author Aramis
 *
 */
@Service
public class PersonServiceImp implements PersonService{
	
	@Autowired
	PersonRepository personRepo;
	
	/* (non-Javadoc)
	 * @see com.test.adan.adan.services.PersonService#save(com.test.adan.adan.jpa.Person)
	 */
	@Override
	public void save(Person person) {
		personRepo.save(person);
		
	}

	/* (non-Javadoc)
	 * @see com.test.adan.adan.services.PersonService#get(int)
	 */
	@Override
	public Person get(int id) {
		return personRepo.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.test.adan.adan.services.PersonService#getByName(java.lang.String)
	 */
	@Override
	public List<Person> getByName(String name) {
		return personRepo.findByName(name);
	}

	/* (non-Javadoc)
	 * @see com.test.adan.adan.services.PersonService#getByLastName(java.lang.String)
	 */
	@Override
	public List<Person> getByLastName(String lastName) {
		return personRepo.findByLastName(lastName);
	}

	/* (non-Javadoc)
	 * @see com.test.adan.adan.services.PersonService#getAll()
	 */
	@Override
	public List<Person> getAll() {
		return personRepo.findAll();
	}

}
