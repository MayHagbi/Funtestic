package com.funtestic.models;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Security;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Send_HTTP_Request {


    public static String call_me() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {

                    public java.security.cert.X509Certificate[] getAcceptedIssuers()
                    {
                        return null;
                    }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                    {
                        //No need to implement.
                    }
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                    {
                        //No need to implement.
                    }
                }
        };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        String url = "http://10.0.2.2:7780/children/get/all";
        System.out.println("HERE!!!!");
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Accept", "application/json");        // optional default is GET

        //add request header
        //con.setRequestProperty("User-Agent", "Mozilla/5.0");

        System.out.println("!!!!!!\n");
        // Log.d("!!!!:::",String.valueOf(con.getResponseCode()));
        System.out.println("!!!!!2!\n");

        ///////////////////////
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("id", "111111");
        String query = builder.build().getEncodedQuery();

        OutputStream os = con.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

       // con.connect();

        /////////////////////////
        //mack buffered reader--------------------------------
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        //----------------------------------------------------
        System.out.println("HERE2!!!!");

        //Enters all information from the site to buffer
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            System.out.println("TTTTTTTTqqqqqqqqTTTTTT");
        }
        //----------------------------------------------

        in.close();

        return response.toString();

    }


}
