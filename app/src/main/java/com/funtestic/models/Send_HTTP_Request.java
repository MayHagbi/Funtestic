package com.funtestic.models;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

        String url = "https://www.mocky.io/v2/5185415ba171ea3a00704eed";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setDoOutput(true);
        con.setRequestProperty("Accept", "application/xml,text/xml,application/xhtml+xml");        // optional default is GET
        //con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", "Mozilla/5.0");


        //mack buffered reader--------------------------------
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        //----------------------------------------------------

        //Enters all information from the site to buffer
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        //----------------------------------------------

        in.close();

        return response.toString();

    }


}
