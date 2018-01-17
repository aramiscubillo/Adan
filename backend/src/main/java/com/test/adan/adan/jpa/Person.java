/**
 * 
 */
package com.test.adan.adan.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * @author Aramis
 *
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT r FROM Person r")
public class Person  implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String lastName;
	
	private int petsOwn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPetsOwn() {
		return petsOwn;
	}

	public void setPetsOwn(int petsOwn) {
		this.petsOwn = petsOwn;
	}

	
	
	
	
}
