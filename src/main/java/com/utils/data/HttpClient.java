package com.utils.data;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;


import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class HttpClient {


    private final static CloseableHttpClient httpClient = HttpClients.createDefault();
    private static final Logger LOGGER = Logger.getLogger(HttpClient.class);


    private void close() throws IOException {
        httpClient.close();
    }

    public static JSONObject sendRemoteGet(String url, Map<String,String> head) throws Exception {
        String result = "";

        JSONObject obj = new JSONObject();
        try {
            URL r = new URL(url);
            InputStream trustStream = new FileInputStream("./ssl-server.jks");
            char[] trustPassword = "Ks327!dfj0".toCharArray();
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(trustStream, trustPassword);

            TrustManagerFactory trustFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustFactory.init(trustStore);
            TrustManager[] trustManagers = trustFactory.getTrustManagers();

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManagers, null);
            SSLContext.setDefault(sslContext);

            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8098));

            Authenticator.setDefault(new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("data-server1", "Ks327!dfj0".toCharArray());
                }
            });



            HttpsURLConnection conn = (HttpsURLConnection)r.openConnection();
            conn.setAllowUserInteraction(true);
            //  conn.setDoOutput(true);
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestMethod("GET");
            head.forEach((key,value) -> conn.setRequestProperty(key,value));
//            OutputStream os = conn.getOutputStream();
//            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//            osw.write(body.toString());
//
//
//            osw.flush();
//            osw.close();
//            os.close();
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder response = new StringBuilder();
            String line;
            while((line = br.readLine())!=null){
                response.append(line);
            }
            obj = new JSONObject(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }



    public static JSONObject sendGet(String url, Map<String,String> head) throws Exception {
        String result = "";

        JSONObject obj = new JSONObject();
        try {
            URL r = new URL(url);



            HttpsURLConnection conn = (HttpsURLConnection)r.openConnection();
            conn.setAllowUserInteraction(true);
            //  conn.setDoOutput(true);
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestMethod("GET");
            head.forEach((key,value) -> conn.setRequestProperty(key,value));
//            OutputStream os = conn.getOutputStream();
//            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//            osw.write(body.toString());
//
//
//            osw.flush();
//            osw.close();
//            os.close();
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder response = new StringBuilder();
            String line;
            while((line = br.readLine())!=null){
                response.append(line);
            }
            obj = new JSONObject(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static String getString(String url) throws Exception {

        HttpGet request = new HttpGet(url);
        String result = "";

        try (CloseableHttpResponse response = httpClient.execute(request)) {


            System.out.println(response.getStatusLine().toString());

            org.apache.http.HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {

                result = EntityUtils.toString(entity);

            }

        }

        return result;
    }


    public static JSONObject sendGet(String url) throws Exception {

        HttpGet request = new HttpGet(url);
        String result = "";

        try (CloseableHttpResponse response = httpClient.execute(request)) {


            System.out.println(response.getStatusLine().toString());

            org.apache.http.HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {

                result = EntityUtils.toString(entity);

            }

        }

        return new JSONObject(result.replaceAll("\n","").replaceAll("\r",""));
    }

    public static JSONObject sendRemotePost(JSONObject body, String url){
        JSONObject obj = new JSONObject();
        try {
            URL r = new URL(url);
            InputStream trustStream = new FileInputStream("./ssl-server.jks");
            char[] trustPassword = "Ks327!dfj0".toCharArray();
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(trustStream, trustPassword);

            TrustManagerFactory trustFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustFactory.init(trustStore);
            TrustManager[] trustManagers = trustFactory.getTrustManagers();

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManagers, null);
            SSLContext.setDefault(sslContext);

            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8098));

            Authenticator.setDefault(new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("data-server1", "Ks327!dfj0".toCharArray());
                }
            });



            HttpsURLConnection conn = (HttpsURLConnection)r.openConnection();
            conn.setAllowUserInteraction(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(body.toString());


            osw.flush();
            osw.close();
            os.close();
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder response = new StringBuilder();
            String line;
            while((line = br.readLine())!=null){
                response.append(line);
            }
            obj = new JSONObject(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }



    public static JSONObject sendHttpPost(JSONObject body, String url){
        JSONObject obj = new JSONObject();
        try {
            URL r = new URL(url);

            HttpURLConnection conn = (HttpURLConnection)r.openConnection();
            conn.setAllowUserInteraction(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(body.toString());


            osw.flush();
            osw.close();
            os.close();
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder response = new StringBuilder();
            String line;
            while((line = br.readLine())!=null){
                response.append(line);
            }
            obj = new JSONObject(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static JSONObject sendHttpsPost(JSONObject body, String url){
        JSONObject obj = new JSONObject();
        try {
            URL r = new URL(url);

            HttpsURLConnection conn = (HttpsURLConnection)r.openConnection();
            conn.setAllowUserInteraction(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(body.toString());


            osw.flush();
            osw.close();
            os.close();
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder response = new StringBuilder();
            String line;
            while((line = br.readLine())!=null){
                response.append(line);
            }
            obj = new JSONObject(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }






}