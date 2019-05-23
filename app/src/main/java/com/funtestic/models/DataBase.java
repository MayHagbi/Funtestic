package com.funtestic.models;

import android.util.Log;

import com.funtestic.interfaces.IDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    String res;
    JSONObject jsonParam;

    // private ctor
    private static void DataBase(){}

    private JSONObject parseJson(String json){
        JSONObject obj = null;
        try {
            obj = new JSONObject(json);
        } catch (Throwable t) {
            Log.e("Funtestic", "Could not parse malformed JSON: \"" + json + "\"");
        }
        return obj;
    }

    // get instance
   public static DataBase getInstance(){
        if(ins == null){
            ins = new DataBase();
        }
        return ins;
    }

    @Override
    public User getUserByPhone(String phone){
        jsonParam = new JSONObject();
        try {
            jsonParam.put("phone_number", phone);
            new Thread(new Runnable() {
                public void run() {
                    res = Send_HTTP_Request.send(jsonParam,"/users/get/");
                }
            }).start();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(!res.equals("")){
            JSONObject json = parseJson(res);
            try {
                return new User(json.get("first_name").toString(), json.get("last_name").toString(),
                        json.get("phone_number").toString(),
                        json.get("email").toString(), json.get("password").toString());
            }
            catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public Child getChildById(String id) {


        return null;
    }

    @Override
    public ArrayList<Child> getUserChildren(String phone) {
        return null;
    }

    @Override
    public boolean addUserToDb(User user) {
        try {
            jsonParam = new JSONObject(user.toString());

            new Thread(new Runnable() {
                public void run() {
                    try {
                        res = Send_HTTP_Request.send(jsonParam,"/register");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;

    }

    @Override
    public boolean addChildToDb(Child child) {
        try {
            jsonParam = new JSONObject(child.toString());
            new Thread(new Runnable() {
                public void run() {
                    try {
                        res = Send_HTTP_Request.send(jsonParam,"/children/add");
                    } catch (Exception e) {
                       e.printStackTrace();
                    }
                }
            }).start();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean addQuizToDb(Quiz quiz) {
        try {
            jsonParam = new JSONObject(quiz.toString());
            new Thread(new Runnable() {
                public void run() {
                    try {
                        res = Send_HTTP_Request.send(jsonParam,"/quiz/add");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public ArrayList<Quiz> getChildQuizGradesFromDb(String id) {
        return null;
    }

    @Override
    public boolean sendReportToEmail(String childId) {
        return false;
    }
}
