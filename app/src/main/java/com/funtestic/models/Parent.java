package com.funtestic.models;

import java.util.*;


public class Parent extends Person {


    private String email;
    private List<String> childId;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email ;
    }

    public Parent(String id , String name , String email , String password) throws Exception  {
        super(id,name);
        setEmail(email);
        this.password=password;
        this.childId= new ArrayList<String>();

    }

    public void addChild(String id) throws Exception {
        if(id.matches("[0-9]+") || id.length()==8) {
            this.childId.add(id);
        }
        else {
            throw new Exception("The value of the child id is invalid");
        }

    }

    public void removeChild(String id) {
        //TODO
    }

}
