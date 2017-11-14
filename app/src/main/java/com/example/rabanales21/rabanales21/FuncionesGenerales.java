package com.example.rabanales21.rabanales21;

import android.content.Context;
import android.widget.Toast;

/**
 * Controlador de metodos para su uso variado en toda la aplicacion. </p>
 */

public class FuncionesGenerales {

    /**
     * Gestiona las comprobaciones de seguridad de la introduccion de usuarios. </p>
     * @param user el usuario que va a ser comprobado.
     * @return Devuelve error si no supera las comprobaciones.
     */

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

    public boolean badCompany(String company){

        Boolean error = false;

        String[] valuesCompany = company.split("");

        if(valuesCompany.length == 1){

            error = true;

        } else {

            for (String data:valuesCompany){

                if(data.equals(",") || data.equals(";") || data.equals(":")){

                    error = true;

                }

            }

        }

        return error;

    }

    /**
     * Gestiona las comprobaciones de seguridad de las passwords. </p>
     * @param pass la password que va a ser comprobada.
     * @return Devuelve error si no supera las comprobaciones.
     */

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

    /**
     * Constructor de mensajes de alarma para las distintas situaciones. </p>
     * @param view Contexto en el que sera mostrado el mensaje de alarma.
     * @param message Contenido del mensaje de alarma.
     */

    public void WarningMessages (Context view, String message){

        Toast.makeText(view, message, Toast.LENGTH_SHORT).show();

    }

    /**
     * Combina el archivo PHP con la url del webservice para realizar las consultas a la BBDD. </p>
     * @param m archivo PHP para cada tipo de conexion.
     * @param n url del webservice.
     * @return Devuelve la combinacion de ambos parametros.
     */

    public String datosLlamada(String m, String n){

        return m+n;

    }

    /**
     * Transforma el datetime recibido de la BBDD a un formato de fecha custom. </p>
     * @param fecha datetime en formato string recibido de la BBDD.
     * @return fecha formateada para su uso en la app.
     */

    public String formatoFecha (String fecha) {

        return fecha.substring(8,10) + "/" + fecha.substring(5,7) + "/" + fecha.substring(0,4);
    }


}
