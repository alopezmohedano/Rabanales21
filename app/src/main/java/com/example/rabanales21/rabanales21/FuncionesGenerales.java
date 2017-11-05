package com.example.rabanales21.rabanales21;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Eduardo on 05/11/2017.
 */

public class FuncionesGenerales {

    public boolean badUser(String user){

        Boolean error = false;

        String[] valuesUser = user.split("");

        if(valuesUser.length == 1){

            error = true;

        } else {

            for (String data: valuesUser) {

                if(data.equals(" ") || data.equals("&") || data.equals(";")){

                    error = true;

                }

            }

        }

        return error;

    }

    public Integer badPass(String pass) {

        Integer error = 0;

        String[] valuesPass = pass.split("");

        if (valuesPass.length != 7) {

            error = 1;

        }

        if (error == 0){

            for (String data: valuesPass) {

                if(data.equals(" ") || data.equals("&") || data.equals(";")){

                    error = 2;

                }

            }

        }

        return error;
    }

    public void WarningMessages (Context view, String message){

        Toast.makeText(view, message, Toast.LENGTH_SHORT).show();

    }
}
