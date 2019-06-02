package com.funtestic.interfaces;

import com.funtestic.models.Child;
import com.funtestic.models.Quiz;
import com.funtestic.models.Report;
import com.funtestic.models.User;

import java.util.ArrayList;

public interface IDatabase {

    //Get User by email
    public User getUserByPhone(String phone ,final String token)throws Exception;

    //Get child by ID
    public Child getChildById(String id ,final String token);

    //Get all User children
    public ArrayList<Child> getUserChildren(String phone ,final String token);

    //Add new user to the database
    public boolean addUserToDb(User user);

    //Add new child to the database
    public boolean addChildToDb(Child child,final String token);

    //Add quiz to the database
    public boolean addQuizToDb(Quiz quiz ,final String token);

    //Send Report To Email
    public boolean sendReportToEmail(String childId ,final String token);

    //Check if user is valid for login purpose
    public boolean login(String phone,String password);




}
