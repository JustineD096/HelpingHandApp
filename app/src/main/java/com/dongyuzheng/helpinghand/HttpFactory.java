package com.dongyuzheng.helpinghand;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by gary on 15-09-19.
 */
public class HttpFactory {

    private static HttpURLConnection initConnection(String requestURL) {
        try {
            URL url = new URL(requestURL);
            return (HttpURLConnection) url.openConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String mapToKVP(HashMap<String,String> data) {
        String str = "";
        for (String key : data.keySet()) {
            str += key + "=" + data.get(key) + "&";
        }
        if (!str.equals("")) {
            return str.substring(0, str.length() - 1);
        }
        else {
            return "";
        }
    }

    private static String wrapCurly(String json) {
        return "{data:" + json + "}";
    }

    public static JSONObject GET(String url, HashMap<String,String> get_parameters, HashMap<String,String> headers) {
        assert url.substring(url.length() - 1).equals("/");
        url += "?" + mapToKVP(get_parameters);
        HttpURLConnection connection = initConnection(url);
        try {
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonString = wrapCurly(in.readLine());
            in.close();
            return new JSONObject(jsonString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static JSONObject POST(String url, HashMap<String,String> content, HashMap<String,String> headers) {
        assert url.substring(url.length() - 1).equals("/");

        HttpURLConnection connection = initConnection(url);
        String content_str = mapToKVP(content);

        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", Integer.toString(content_str.getBytes().length));
        connection.setRequestProperty("Content-Language", "en-US");
        for (String key : headers.keySet()) {
            connection.setRequestProperty(key, headers.get(key));
        }

        connection.setDoOutput(true);

        try {

            connection.setRequestMethod("POST");
            // Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(content_str);
            wr.close();

            return new JSONObject(new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
