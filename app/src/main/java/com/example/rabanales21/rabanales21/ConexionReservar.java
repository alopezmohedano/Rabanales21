package com.example.rabanales21.rabanales21;

import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class ConexionReservar extends AsyncTask<String, Void, Boolean> {

    @Override
    protected Boolean doInBackground(String... params) {

        StringBuilder resul;

        Boolean auxiliar = false;

        try {

            URL url = new URL("https://www.rabanales21.com/modules_WebServices/" + params[0]);

            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

            int respuesta = conexion.getResponseCode();

            resul = new StringBuilder();

            if (respuesta == HttpURLConnection.HTTP_OK) {

                InputStream in = new BufferedInputStream(conexion.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String linea;

                while ((linea = reader.readLine()) != null) {

                    resul.append(linea);

                }

            }

            JSONArray json = new JSONArray(resul.toString());

            auxiliar = (Boolean) json.getJSONObject(0).get("resultado");

        } catch (Exception e) {


        }

        return auxiliar;

    }

}