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
 * Gestiona la conexion a la BBDD para realizar el login de usuario
 */

class ConexionAsincrona extends AsyncTask<String, Void, String[]> {

    @Override
    protected String[] doInBackground(String... params) {

        StringBuilder resul;

        String[] auxiliar = new String[4];

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

                auxiliar[3] = json.getJSONObject(0).get("cod_u").toString();

            }

        } catch (Exception e) {


        }

        return auxiliar;

    }

}
