package com.funtestic.models;

import android.util.Log;

import com.funtestic.interfaces.IDatabase;

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
    public User getUserByEmail(String email){
        final JSONObject jsonParam = new JSONObject();
        try {
            // jsonParam.put("email", email); CHANGE THIS @#%#%@#%@#$
            jsonParam.put("id","1111");
            new Thread(new Runnable() {
                public void run() {
                    Log.d("TTTTT","IS RUNNING\n");
                    // a potentially time consuming task
                    try {
                        res = Send_HTTP_Request.send(jsonParam,"/children/get/all");
                        Log.d("TTT::: ",res);
                        JSONObject json = parseJson(res);
                        Log.d("PARSED: ",json.get("id").toString());
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
