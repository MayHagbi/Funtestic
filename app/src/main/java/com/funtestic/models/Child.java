package com.funtestic.models;

import android.support.annotation.NonNull;

import org.json.JSONObject;

import java.io.Serializable;


public class Child implements Serializable {

	private static final long serialVersionUID = 1L;

	private String gender;
	private String age;
	private String name;
    private String id;
	private User user;




	public Child (String gender,String age,String name,String id,User usr){
        setName(name);
		setAge(age);
		setGender(gender);
		setId(id);
		user = new User(usr);
	}

	public Child(Child chld)
    {
        setName(chld.getName());
        setAge(chld.getAge());
        setGender(chld.getGender());
        setUser(chld.getUser());

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

    public void setUser(User usr){
	    user = new User(usr);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        try {
            JSONObject child = new JSONObject();
            child.put("id_number", getId());
            child.put("age", getAge());
            child.put("gender", getGender());
            child.put("name", getName());
            child.put("parent_id", user.getNumber());
            return child.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "{}";
        }

    }
}
