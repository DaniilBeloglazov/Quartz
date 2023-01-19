package com.example.quartz.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Getter @Setter
public class TelegramSender {
    //TODO Add configuration properties
    private final String INSTANCE_ID = "";
    private final String CLIENT_ID = "";
    private final String CLIENT_SECRET = "";
    private final String TG_GATEWAY_URL = "";
    public void sendMessage(String number, String message) throws JSONException, IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", number);
        jsonObject.put("message", message);

        URL url = new URL(TG_GATEWAY_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
        connection.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
        connection.setRequestProperty("Content-Type", "application/json");

        OutputStream os = connection.getOutputStream();
        os.write(jsonObject.toString().getBytes());
        os.flush();
        os.close();

        int statusCode = connection.getResponseCode();
        System.out.println("Response from WA Gateway: \n");
        System.out.println("Status Code: " + statusCode);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (statusCode == 200) ? connection.getInputStream() : connection.getErrorStream()
        ));
        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        connection.disconnect();
    }
    }
}
