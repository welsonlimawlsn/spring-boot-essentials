package br.com.welson.springboot.javaclient;


import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class JavaClientTest {
    public static void main(String[] args) {
        //System.out.println(encodeUsernameAndPassword("welsonlimawlsn", "123456789"));
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String username = "welsonlimawlsn";
        String password = "123456789";
        try {
            URL url = new URL("http://localhost:8080/v1/protected/students/33");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Authorization", "Basic " + encodeUsernameAndPassword(username, password));
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonSB = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonSB.append(line);
            }
            System.out.println(jsonSB);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
            if (connection != null) connection.disconnect();
        }
    }

    public static String encodeUsernameAndPassword(String username, String password) {
        String userPassword = username + ":" + password;
        return new String(Base64.encodeBase64(userPassword.getBytes()));
    }
}
