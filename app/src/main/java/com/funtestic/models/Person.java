package com.funtestic.models;

public abstract class Person {

    private String id ;
    private String name ;

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


    public Person() {
        setName(null);

    }


    public Person(String id , String name) throws Exception {
        setName(name);
        setId(id);
    }





}