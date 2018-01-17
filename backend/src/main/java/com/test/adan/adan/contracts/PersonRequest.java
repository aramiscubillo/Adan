/**
 * 
 */
package com.test.adan.adan.contracts;

import com.test.adan.adan.pojos.PersonPOJO;

/**
 * @author Aramis
 *
 */
public class PersonRequest {
	
	private int id;
	
	private PersonPOJO person;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PersonPOJO getPerson() {
		return person;
	}

	public void setPerson(PersonPOJO person) {
		this.person = person;
	}
	
	
	
}
