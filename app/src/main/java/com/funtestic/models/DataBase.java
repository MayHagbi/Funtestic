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
    String res="";
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
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    res = Send_HTTP_Request.send(jsonParam,"/users/get/");
                }
            });
            t1.start();
            try {
                t1.join();
            }
            catch (Exception e){
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(!res.equals("")){
            JSONObject json = parseJson(res);
            try {
                JSONObject user_json = (JSONObject)json.get("user");
                 User usr = new User(user_json.get("first_name").toString(), user_json.get("last_name").toString(),
                         json.get("phone_number").toString(),
                         user_json.get("email").toString(), user_json.get("password").toString());
                 return usr;
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
        jsonParam = new JSONObject();
        try {
            jsonParam.put("id_number", id);
            //--------------------------------
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    res = Send_HTTP_Request.send(jsonParam,"/users/get/");
                }
            });
            t1.start();
            try {
                t1.join();
            }
            catch (Exception e){
                return null;
            }
            //--------------------------
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(!res.equals("")){
            JSONObject json = parseJson(res);

            try {
                JSONObject parent = parseJson(json.get("parent").toString());
                JSONObject user = parseJson(parent.get("user").toString());

                User father = new User(user.get("first_name").toString(), user.get("last_name").toString(),
                        parent.get("phone_number").toString(),
                        user.get("email").toString(), user.get("password").toString());
                Child newChile =new Child(json.get("gender").toString(),json.get("age").toString()
                        ,json.get("name").toString(),json.get("id_number").toString(),father);

                return   newChile;
                }
            catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }
        return null;
    }

    @Override
    public ArrayList<Child> getUserChildren(String phone) {
        ArrayList<Child> childs = new ArrayList<Child>();
        jsonParam = new JSONObject();
        try {
            jsonParam.put("phone_number", phone);
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    res = Send_HTTP_Request.send(jsonParam,"/children/get/all");
                }
            });
            t1.start();
            try {
                t1.join();
            }
            catch (Exception e){
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(!res.equals("")){
            try {
                JSONArray child_json = new JSONArray(res);
                int size = child_json.length();
                for(int i=0;i<size;i++){
                    JSONObject j = (JSONObject)child_json.get(i);
                    JSONObject parent = (JSONObject)j.get("parent");
                    JSONObject user = (JSONObject)parent.get("user");
                    Child child = new Child(j.get("id").toString(),
                            j.get("age").toString(),
                            j.get("gender").toString(),
                            j.get("name").toString(),
                            new User(user.get("first_name").toString(),
                                    user.get("last_name").toString(),
                                    user.get("email").toString(),
                                    parent.get("phone_number").toString(),
                                    user.get("password").toString() ));
                    childs.add(child);
                }
                return childs;
            }
            catch (Exception e){
                e.printStackTrace();
                Log.d("TTT:T","EXCEPTION");
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean addUserToDb(User user) {
        try {
            jsonParam = new JSONObject(user.toString());

            Thread t1 = new Thread(new Runnable() {
                public void run() {

                        res = Send_HTTP_Request.send(jsonParam,"/register/");
                        Log.d("TTTT","res"+res);
                }
            });
            t1.start();
            try {
                t1.join();
            }
            catch (Exception e)
            {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        if(res.equals(""))
        {
            return false;
        }
        return true;

    }

    @Override
    public boolean addChildToDb(Child child) {
        try {
            jsonParam = new JSONObject(child.toString());

            Thread t1 = new Thread(new Runnable() {
                public void run() {

                    res = Send_HTTP_Request.send(jsonParam,"/children/add");
                }
            });
            t1.start();
            try {
                t1.join();
            }
            catch (Exception e)
            {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        if(res.equals(""))
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean addQuizToDb(Quiz quiz) {
        try {
            jsonParam = new JSONObject(quiz.toString());

            Thread t1 = new Thread(new Runnable() {
                public void run() {

                    res = Send_HTTP_Request.send(jsonParam,"/quiz/add");
                }
            });
            t1.start();
            try {
                t1.join();
            }
            catch (Exception e)
            {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        if(res.equals(""))
        {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Report> getChildQuizGradesFromDb(String id) {
        ArrayList<Report> reports = new ArrayList<Report>();
        jsonParam = new JSONObject();
        try {
            jsonParam.put("child", id);
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    res = Send_HTTP_Request.send(jsonParam,"/reports/reports_list");
                }
            });
            t1.start();
            try {
                t1.join();
            }
            catch (Exception e){
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(!res.equals("")){
            try {
                JSONArray report_list = new JSONArray(res);
                int size = report_list.length();
                for(int i=0;i<size;i++){
                    JSONObject j = (JSONObject)report_list.get(i);
                    Report repo = new Report(j.get("id").toString(),j.get("create_at").toString());
                    reports.add(repo);
                }
                return reports;
            }
            catch (Exception e){
                e.printStackTrace();
                Log.d("TTT:T","EXCEPTION");
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean sendReportToEmail(String childId) {
        return false;
    }
}
