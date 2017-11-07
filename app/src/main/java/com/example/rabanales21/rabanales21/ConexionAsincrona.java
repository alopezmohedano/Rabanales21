package com.example.rabanales21.rabanales21;

import android.os.AsyncTask;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Telefonica on 06/11/2017.
 */

class ConexionAsincrona extends AsyncTask<String, Void, String[]> {

    @Override
    protected String[] doInBackground(String... params) {

        StringBuilder resul;

        String[] auxiliar = new String[3];

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

            if(json.length() > 0) {

                auxiliar[0] = json.getJSONObject(0).get("nombre_usuario").toString();

                auxiliar[1] = json.getJSONObject(0).get("nombre_empresa").toString();

                auxiliar[2] = json.getJSONObject(0).get("tipo").toString();

            }

        } catch (Exception e) {


        }

        return auxiliar;

    }

}
