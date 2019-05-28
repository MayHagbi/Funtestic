package com.funtestic.models;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.*;


public class User implements Serializable {
	private String first_name ;
	private String last_name ;
	private String number; //out of json
	private String email;
	private String password;


	public User(String first_name , String last_name,String number,String email,String password)  {
		setFirst_name(first_name);
		setLast_name(last_name);
		setNumber(number);
		setEmail(email);
		setPassword(password);
	}

	public User(User usr)
    {
        setFirst_name(usr.getFirst_name());
        setLast_name(usr.getLast_name());
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    @Override
	public String toString() {
        try {
            JSONObject json = new JSONObject();
            JSONObject user = new JSONObject();
            user.put("first_name", getFirst_name());
            user.put("last_name", getLast_name());
            user.put("email", getEmail());
            user.put("password", getPassword());

            json.put("user",user);
            json.put("phone_number", getNumber());
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
