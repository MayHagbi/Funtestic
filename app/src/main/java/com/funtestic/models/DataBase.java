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
            // jsonParam.put("email", email); CHANGE THIS @#%#%@#%@#$

            // START TEST
            Child c =new Child("male","123","gilad",new User("asdas","Asd","asdasd","asd","asd"));

            JSONObject js = new JSONObject();
            js.put("gender",c.getGender());
            js.put("age",c.getAge());
            js.put("name",c.getName());
            JSONObject father = new JSONObject();
            father.put("firstName","FaTHER NAMEEE");
            js.put("father",father);


            Log.d("RRRRR",js.toString());
            //Log.d("RRRRRR",)
            //// END TEST
            jsonParam.put("id","1111");
            new Thread(new Runnable() {
                public void run() {
                    Log.d("TTTTT","IS RUNNING\n");
                    // a potentially time consuming task
                    try {
                        res = Send_HTTP_Request.send(jsonParam,"/children/get/all/");
                        Log.d("TTT::: ",res);
                        // JSONObject json = parseJson(res);
                        JSONArray json = new JSONArray(res);
                        JSONObject  j = new  JSONObject(json.get(0).toString());
                        Log.d("PARSED1:",j.toString());
                        //Log.d("PARSED: ",json.get("id").toString());
                        Log.d("PARSED2:",json.toString());
                    } catch (Exception e) {
                        Log.d("FFFF", String.valueOf(e));
                    }
                }
            }).start();
        } catch (JSONException e) {
            e.printStackTrace();
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
        jsonParam = new JSONObject();



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
    public boolean sendReportToEmail(String childId) {
        return false;
    }
}
