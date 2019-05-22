package com.funtestic.models;

import java.util.*; 


public class User {
	private String first_name ;
	private String last_name ;
	private String id;
	private String number;
	private String password;


	public User(String first_name , String last_name,String id,String number,String password)  {
		setFirst_name(first_name);
		setLast_name(last_name);
		setId(id);
		setNumber(number);
		setPassword(password);
	}

	public User(User usr)
    {
        setFirst_name(usr.getFirst_name());
        setLast_name(usr.getLast_name());
        setId(usr.getId());
        setNumber(usr.getNumber());
        setPassword(usr.getPassword());
    }


	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
