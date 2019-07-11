package com.funtestic.interfaces;

import com.funtestic.models.Child;
import com.funtestic.models.Quiz;
import com.funtestic.models.User;
import java.util.ArrayList;

public interface IDatabase {

    //Get User by email
    User getUserByPhone(String phone ,final String token)throws Exception;

    //Get child by ID
    Child getChildById(String id ,final String token);

    //Get all User children
    ArrayList<Child> getUserChildren(String phone ,final String token);

    //Add new user to the database
    boolean addUserToDb(User user);

    //Add new child to the database
    boolean addChildToDb(Child child,final String token);

    //Add quiz to the database
    boolean addQuizToDb(Quiz quiz ,final String token);

    //Send Report To Email
    boolean sendReportToEmail(String childId ,final String token);

    //Check if user is valid for login purpose
    boolean login(String phone,String password);

    //Two factor authentication
    String TwoStepVerification(String phone,String password , String TwoFA_pass );

}
