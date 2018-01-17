/**
 * 
 */
package com.test.adan.adan.controllers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.adan.adan.contracts.BaseResponse;
import com.test.adan.adan.jpa.Person;
import com.test.adan.adan.pojos.PersonPOJO;
import com.test.adan.adan.services.PersonService;



/**
 * @author Aramis
 *
 */
@RestController
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@RequestMapping(value = "/api/person/{id}", method = RequestMethod.GET)
	public PersonPOJO getPerson(@PathVariable int id) {

		Person person = null;
		PersonPOJO pojo = new PersonPOJO();
		
		try{
			
			person = personService.get(id);
			
			pojo.setId(person.getId());
			pojo.setName(person.getName());
			pojo.setLastName(person.getLastName());
			pojo.setPetsOwn(person.getPetsOwn());
			
			return pojo;
		
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			throw e;
		}

	}
	
	
	@RequestMapping(value = "/api/person", method = RequestMethod.GET)
	public List<PersonPOJO> getPersons() {

		PersonPOJO pojo;
		List<PersonPOJO> list = new ArrayList<PersonPOJO>();
		
		try{
			
			List<Person> persons = personService.getAll();
			
			for(Person p:persons){
				pojo = new PersonPOJO();
				pojo.setId(p.getId());
				pojo.setName(p.getName());
				pojo.setLastName(p.getLastName());
				pojo.setPetsOwn(p.getPetsOwn());
				list.add(pojo);
			}
			
			return list;
		
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			throw e;
		}

	}
	
	

	@RequestMapping(value = "/api/person", method = RequestMethod.PUT)
	public PersonPOJO editPerson(@RequestBody PersonPOJO pojo) {
	
		try{
			
			Person person = personService.get(pojo.getId());
			
			person.setName(pojo.getName());
			person.setLastName(pojo.getLastName());
			person.setPetsOwn(pojo.getPetsOwn());
			
			personService.save(person);
			
			return pojo;
		
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			throw e;
		}

	}
	
	@RequestMapping(value = "/api/person", method = RequestMethod.POST)
	public PersonPOJO createPerson(@RequestBody PersonPOJO pojo) {
	
		try{
			
			Person person = new Person();
			
			person.setName(pojo.getName());
			person.setLastName(pojo.getLastName());
			person.setPetsOwn(pojo.getPetsOwn());
			
			personService.save(person);
			
			return pojo;
		
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			throw e;
		}

	}
	
	
	
}
