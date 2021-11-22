package com.example.appcompanypets.Api;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonHandler
{
    public static String getJson(String textoUrl)
    {
        String textoJson = "";

        try
        {
            URL url = new URL(textoUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String linha = "";
            while((linha = bufferedReader.readLine())!=null) {
                textoJson += linha;
            }
        } catch (MalformedURLException e) {
            Log.d("URLExceptionPt", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("IOExceptionPt", e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("ExceptionGenericaPt", e.toString()); }

        return textoJson;
    }
}
