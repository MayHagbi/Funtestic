package com.funtestic.models;

import android.util.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Send_HTTP_Request {
    static final String URL = "http://10.0.2.2:7785";

    public static String send(JSONObject jsonParam, String path) throws Exception {
        // SSL
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
                }
        };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // URL && Start Connection
        //URL obj = new URL(URL + path);
        URL obj = new URL("http://www.mocky.io/v2/5ce667d33300004b3573150f");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // Create Request
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        // Add Parameters
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        Log.d("XXXX: ",jsonParam.toString());
        out.writeBytes(jsonParam.toString());
        out.flush();
        out.close();

        System.out.println("!!!!!!\n");
        Log.d("!!!!:::",String.valueOf(con.getResponseCode()));
        System.out.println("!!!!!2!\n");

        // create buffered reader
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        // Read The Response
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        con.disconnect();
        return response.toString();
    }
}
