package com.example.rabanales21.rabanales21;

import android.content.Context;
import android.widget.Toast;

public class FuncionesGenerales {

    public boolean badUser(String user){

        Boolean error = false;

        String[] valuesUser = user.split("");

        if(valuesUser.length == 1){

            error = true;

        } else {

            for (String data: valuesUser) {

                if(data.equals(" ") || data.equals(",") || data.equals(";") || data.equals(".") || data.equals(":")){

                    error = true;

                }

            }

        }

        return error;

    }

    public Integer badPass(String pass) {

        Integer error = 0;

        String[] valuesPass = pass.split("");

        if (valuesPass.length < 9) {

            error = 1;

        }

        if (error == 0){

            for (String data: valuesPass) {

                if(data.equals(" ") || data.equals(",") || data.equals(";") || data.equals(".") || data.equals(":")){

                    error = 2;

                }

            }

        }

        return error;
    }

    public void WarningMessages (Context view, String message){

        Toast.makeText(view, message, Toast.LENGTH_SHORT).show();

    }

    public String datosLlamada(String m, String n){

        return m+n;

    }

    public String formatoFecha (String fecha) {

        return fecha.substring(8,10) + "/" + fecha.substring(5,7) + "/" + fecha.substring(0,4);
    }


}
