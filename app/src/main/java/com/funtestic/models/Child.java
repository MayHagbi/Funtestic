package com.funtestic.models;

import android.support.annotation.NonNull;


public class Child{

	private static final long serialVersionUID = 1L;

	private String gender;
	private String age;
	private String name;
	private User user;



	public Child (String gender,String age,String name,User usr) throws Exception {
        setName(name);
		setAge(age);
		setGender(gender);
		user = new User(usr.getFirst_name(),usr.getLast_name(),usr.getId(),usr.getNumber(),usr.getPassword());
	}

	public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }
}
