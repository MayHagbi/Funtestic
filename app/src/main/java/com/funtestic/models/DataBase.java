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
                 User usr = new User(json.get("first_name").toString(), json.get("last_name").toString(),
                        json.get("phone_number").toString(),
                        json.get("email").toString(), json.get("password").toString());
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
        try {
            jsonParam= new JSONObject();
            jsonParam.put("id_number", id);
            //--------------------------------
            new Thread(new Runnable() {
                public void run() {
                    try {
                        res = Send_HTTP_Request.send(jsonParam,"/children/get");
                        Log.d("YYYYYY",res.toString());
                        JSONObject json = new JSONObject(res);

                        Log.d("YYYYYY",json.toString());
                        //"parent":{"first_name":"Haviv","last_name":"Eyal","email":"yoel@gmail.com","phone_number":"089922222","password":"12345678"}}
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            //--------------------------

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Child> getUserChildren(String phone) {
        ArrayList<Child> childs= new ArrayList<Child>();
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
                    Child child = new Child(j.get("id").toString(),
                            j.get("age").toString(),
                            j.get("gender").toString(),
                            j.get("name").toString(),
                            new User(parent.get("first_name").toString(),
                                    parent.get("last_name").toString(),
                                    parent.get("email").toString(),
                                    parent.get("phone_number").toString(),
                                    parent.get("password").toString() ));
                    childs.add(child);
                    Log.d("TTTT$$$:",child.toString());
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
