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
    public User getUserByPhone(String phone, final String token){
        jsonParam = new JSONObject();
        try {
            jsonParam.put("phone_number", phone);
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    res = Send_HTTP_Request.send(jsonParam,"/users/get/","POST",token);
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
                         user_json.get("email").toString(), "");
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
    public boolean login(String phone,String password ){
        jsonParam = new JSONObject();
        try {
            jsonParam.put("phone_number", phone);
            jsonParam.put("password",password);
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    res = Send_HTTP_Request.send(jsonParam,"/login/","POST" ,null);
                    Log.d("TTTT res",res);
                }
            });
            t1.start();
            try {
                t1.join();
            }
            catch (Exception e){
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        if(!res.equals("")){
            return true; //user was found
        }
        return false;
    }

    public String TwoStepVerification(String phone,String password , String TwoFA_pass ){
        jsonParam = new JSONObject();
        try {
            jsonParam.put("phone_number", phone);
            jsonParam.put("password",password);
            jsonParam.put("2fa_pass",TwoFA_pass);
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    res = Send_HTTP_Request.send(jsonParam,"/2fa/","POST" ,null);
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
                return json.get("token").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
    @Override
    public Child getChildById(String id ,final String token ) {
        jsonParam = new JSONObject();
        try {
            jsonParam.put("id_number", id);
            //--------------------------------
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    res = Send_HTTP_Request.send(jsonParam,"/users/get/","POST",token);
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

                User father = new User(
                        user.get("first_name").toString(),
                        user.get("last_name").toString(),
                        parent.get("phone_number").toString(),
                        user.get("email").toString(),
                        "");

                return  new Child(
                                json.get("gender").toString(),
                                json.get("age").toString(),
                                json.get("name").toString(),
                                json.get("id_number").toString(),
                                father);
                }
            catch (Exception e){
                e.printStackTrace();
                Log.d("ERROR Exception",e.toString());
                return null;
            }

        }
        return null;
    }

    @Override
    public ArrayList<Child> getUserChildren(String phone ,final String token) {
        ArrayList<Child> childs = new ArrayList<Child>();
        jsonParam = new JSONObject();
        try {
            jsonParam.put("parent_id", phone);
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    res = Send_HTTP_Request.send(jsonParam,"/children/get/all/","POST" ,token);
                }
            });
            t1.start();
            try {
                t1.join();
            }
            catch (Exception e){
                e.printStackTrace();
                Log.d("ERROR Exception",e.toString());
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("ERROR Exception",e.toString());
        }
        if(!res.equals("")){
            try {
                JSONArray child_json = new JSONArray(res);
                int size = child_json.length();
                for(int i=0;i<size;i++){
                    JSONObject j = (JSONObject)child_json.get(i);
                    JSONObject parent = (JSONObject)j.get("parent");
                    JSONObject user = (JSONObject)parent.get("user");
                    Child child = new Child(
                            j.get("gender").toString(),
                            j.get("age").toString(),
                            j.get("name").toString(),
                            j.get("id_number").toString(),
                            new User(user.get("first_name").toString(),
                                    user.get("last_name").toString(),
                                    parent.get("phone_number").toString(),
                                    user.get("email").toString(),
                                    "" ));
                    childs.add(child);
                }
                return childs;
            }
            catch (Exception e){
                e.printStackTrace();
                Log.d("ERROR Exception",e.toString());
                return null;
            }
        }
        return new ArrayList<Child>();//empty array
    }

    @Override
    public boolean addUserToDb(User user ) {
        try {
            jsonParam = new JSONObject(user.toString());

            Thread t1 = new Thread(new Runnable() {
                public void run() {

                        res = Send_HTTP_Request.send(jsonParam,"/register/","PUT" ,null);
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
            Log.d("ERROR ","False to add new user to Data base");
            return false;
        }
        return true;

    }

    @Override
    public boolean addChildToDb(Child child ,final String token) {
        try {
            jsonParam = new JSONObject(child.toString());

            Thread t1 = new Thread(new Runnable() {
                public void run() {

                    res = Send_HTTP_Request.send(jsonParam,"/children/add/","PUT" ,token);
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
    public boolean addQuizToDb(Quiz quiz ,final String token) {
        try {
            jsonParam = new JSONObject(quiz.toString());

            Thread t1 = new Thread(new Runnable() {
                public void run() {

                    res = Send_HTTP_Request.send(jsonParam,"/quiz/add/","PUT" ,token);
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
    public boolean sendReportToEmail(String childId ,final String token) {
        Log.d("TTTTcccc",childId);
        jsonParam = new JSONObject();
        try {
            jsonParam.put("id_number", childId);
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    res = Send_HTTP_Request.send(jsonParam,"/reports/send/","PUT" ,token);
                    Log.d("TTTTRES",res);
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
}
