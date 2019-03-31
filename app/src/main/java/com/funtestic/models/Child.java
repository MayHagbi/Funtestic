package com.funtestic.models;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Child implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String gender;
    private int age;
    private int id;

    public Child() {
        this.name = "unknow";
        this.gender = "other";
        this.age = 0;
        this.id = 0;
    }

    public Child(String name, String gender, int age, int id) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    @NonNull
    public String toString() {
        return  "Child [ Name: " + name +
                      " Age: "  + age  +
                      " Gender: " + gender  +
                      " ID: "  + id + " ]";
    }
}
