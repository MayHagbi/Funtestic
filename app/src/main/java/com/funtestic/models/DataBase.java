package com.funtestic.models;

import com.funtestic.interfaces.IDatabase;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBase implements IDatabase {

    static DataBase ins;
    static final String URL = "giladg.tk";

    // private ctor
    private static void DataBase(){}

    // get instance
    static DataBase getInstance(){
        if(ins == null){
            ins = new DataBase();
        }
        return ins;
    }

    @Override
    public User getUserByEmail(String email){
        URL url = null;
        try {
            url = new URL(URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        Map<String, String> parameters = new HashMap<>();
        parameters.put("username", "giladgu");

        con.setDoOutput(true);
//        DataOutputStream out = null;
//        try {
//            out = new DataOutputStream(con.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
//        out.flush();
//        out.close();


        return null;
    }

    @Override
    public Child getChildById(String id) {
        return null;
    }

    @Override
    public ArrayList<Child> getUserChildren(String email) {
        return null;
    }

    @Override
    public boolean addUserToDb(User user) {
        return false;
    }

    @Override
    public boolean addChildToDb(Child child) {
        return false;
    }

    @Override
    public boolean addQuizToDb(Quiz quiz) {
        return false;
    }

    @Override
    public ArrayList<Quiz> getChildQuizGradesFromDb(String id) {
        return null;
    }

    @Override
    public boolean sendReportToEmail(String parentEmail, String childId) {
        return false;
    }
}
