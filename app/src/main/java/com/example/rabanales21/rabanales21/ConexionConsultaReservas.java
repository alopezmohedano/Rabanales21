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
 * Created by Alvaro on 07/11/2017.
 */

class ConexionConsultaReservas extends AsyncTask<String, Void, Reserva[]> {

    @Override
    protected Reserva[] doInBackground(String... params) {

        StringBuilder resul;

        Reserva[] auxiliar = null;

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

            auxiliar = new Reserva[json.length()];

            if(json.length() > 0) {

                for (int i= 0; i < json.length(); i++) {
                    auxiliar[i] = new Reserva(json.getJSONObject(i).get("cod_s").toString(), json.getJSONObject(i).get("inicio").toString(), json.getJSONObject(i).get("fin").toString());
                }
            }

        } catch (Exception e) {


        }

        return auxiliar;

    }
}
