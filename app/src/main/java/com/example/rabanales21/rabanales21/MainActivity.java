package com.example.rabanales21.rabanales21;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FuncionesGenerales myController = new FuncionesGenerales();

    EditText edtUser, edtPass;

    TextView tv_mensaje;

    ImageButton imageButton;

    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

        edtUser = (EditText) findViewById(R.id.edtusuario);

        edtPass = (EditText) findViewById(R.id.edtpass);

        imageButton=(ImageButton) findViewById(R.id.btnlogin);

        tv_mensaje =(TextView)findViewById(R.id.tv_mensaje);

        tv_mensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.show();
            }
        });

        imageButton.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Por favor para consultar el usuario y contraseña puede ponerse en contacto con nosotros vía teléfono:  957 340 116 o vía email: nfo@rabanales21.com. Gracias por su atención.")
        .setTitle("¿Ha perdido los datos de su cuenta?")
                .setCancelable(false)
                .setNeutralButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        alert = builder.create();


    }


    @Override
    public void onClick(View v) {

            Boolean userError = myController.badUser(edtUser.getText().toString());

            Integer passError = myController.badPass(edtPass.getText().toString());


            if (userError){

                myController.WarningMessages(this, getString(R.string.emptyUser));

            } else {

                switch (passError){

                    case 0:

                        String miPagina = "login.php";

                        String miWhere = "?nombre_usuario=" + edtUser.getText().toString() + "&pass=" + edtPass.getText().toString();

                        try {

                            ConexionAsincrona miCon = new ConexionAsincrona();

                            String[] respuesta = miCon.execute(myController.datosLlamada(miPagina, miWhere)).get();

                            if (respuesta[0] != null){

                                Intent intent = new Intent(this, MenuActivity.class);

                                intent.putExtra("respuestaLogin", respuesta);

                                startActivity(intent);

                            } else {

                                myController.WarningMessages(this, getString(R.string.wrongUser));

                            }


                        } catch (InterruptedException e) {

                            e.printStackTrace();

                        } catch (ExecutionException e) {

                            e.printStackTrace();

                        }

                        break;

                    case 1:

                        myController.WarningMessages(this, getString(R.string.emptyPass));

                        break;

                    case 2:

                        myController.WarningMessages(this, getString(R.string.wrongPass));

                        break;





            }


        }

    }

}
