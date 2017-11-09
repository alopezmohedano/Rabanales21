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
 * Gestiona la conexion a la BBDD para modificar usuarios. </p>
 * Envia los datos modificados a un webservice </br>
 * Recibe un JSON si la conexion es correcta confirmando el update </br>
 */

public class ConexionUpdate extends AsyncTask<String, Void, Integer> {

    @Override
    protected Integer doInBackground(String... params) {

        StringBuilder resul;

        Integer auxiliar = 0;

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

            auxiliar = (Integer) json.getJSONObject(0).get("resultado");

        } catch (Exception e) {


        }

        return auxiliar;

    }

}