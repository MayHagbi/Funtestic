package com.funtestic.models.test;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.sql.Date;

public class Child extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private String gender;
	private String age;



	public Child() throws Exception{
		super();
		setAge("0");
		setGender("other");
    }

	public Child (String name, String gender, String age, String id) throws Exception {
		super(id,name);
		setAge(age);
		setGender(gender);
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

    //get/set for id and name are in person


	@Override
    @NonNull
    public String toString() {
        return  "Child [ Name: " + this.getName() +
                      " Age: "  + this.getAge()  +
                      " Gender: " + this.getGender() +
                      " ID: "  + this.getId()+ " ]";
    }

}
