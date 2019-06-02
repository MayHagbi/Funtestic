package com.funtestic.interfaces;

import com.funtestic.models.Child;
import com.funtestic.models.Quiz;
import com.funtestic.models.Report;
import com.funtestic.models.User;

import java.util.ArrayList;

public interface IDatabase {

    //Get User by email
    public User getUserByPhone(String phone)throws Exception;

    //Get child by ID
    public Child getChildById(String id);

    //Get all User children
    public ArrayList<Child> getUserChildren(String phone);

    //Add new user to the database
    public boolean addUserToDb(User user);

    //Add new child to the database
    public boolean addChildToDb(Child child);

    //Add quiz to the database
    public boolean addQuizToDb(Quiz quiz);

    //Get child quiz grades from database
    public ArrayList<Report> getChildQuizGradesFromDb(String id);

    //Send Report To Email
    public boolean sendReportToEmail(String childId);

    //Check if user is valid for login purpose
    public boolean login(String phone,String password);




}
