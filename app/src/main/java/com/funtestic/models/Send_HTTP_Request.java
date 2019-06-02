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
    static final String URL = "http://10.0.2.2:8002";

    public static String send(JSONObject jsonParam, String path,String methodType) {
        try {
            // SSL
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // URL && Start Connection
            URL obj = new URL(URL + path);
            //URL obj = new URL("http://www.mocky.io/v2/5ce697b033000098017316e6");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // Create Request
            con.setRequestMethod(methodType);
            con.setRequestProperty("Content-Type", "application/json");

            // Add Parameters
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(jsonParam.toString());
            out.flush();
            out.close();

            // in case of bad request code
            if (con.getResponseCode() != 200 && con.getResponseCode() != 201) {
                return "";
            }

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
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
