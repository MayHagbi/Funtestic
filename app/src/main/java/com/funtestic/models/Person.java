package com.funtestic.models;

import java.io.Serializable;

public abstract class Person implements Serializable {
	
	private String id ;
	private String name ;


	public Person(String id , String name) throws Exception {
		setName(name);
		setId(id);
	}


	public String getId() {
		return this.id ;
	}
	
	
	public void setId(String id) throws Exception {
		if(id.matches("[0-9]+") || id.length()==8)
			this.id = id;
		else {
			throw new Exception("The value of the person id is invalid");
		}
	}
	
	
	public String getName() {
		return this.name ;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
}
