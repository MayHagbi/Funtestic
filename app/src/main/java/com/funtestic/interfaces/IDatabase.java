package com.funtestic.interfaces;

import com.funtestic.models.Child;

import java.util.ArrayList;

public interface IDatabase {

    //Get Parent by ID
    public Parent getParentById(int id);

    //Get child by ID
    public Child getChildById(int id);

    //Get all parent children
    public ArrayList<Child> getParentChildren(int id);

    //Add new user to the database
    public boolean addUserToDb(User user);

    //Add new child to the database
    public boolean addChildToDb(Child child);




}
