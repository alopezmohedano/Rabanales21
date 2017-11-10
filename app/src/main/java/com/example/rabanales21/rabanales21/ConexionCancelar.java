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
 * Gestiona la conexion a la BBDD para cancelar una reserva. </p>
 * Envia los datos de cancelacion a un webservice </br>
 * Recibe un JSON si la conexion es correcta y confirma la cancelacion </br>
 */

public class ConexionCancelar extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {

        StringBuilder resul;

        String auxiliar = "";

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

            auxiliar = (String) json.getJSONObject(0).get("resultado");

        } catch (Exception e) {


        }

        return auxiliar;

    }

}