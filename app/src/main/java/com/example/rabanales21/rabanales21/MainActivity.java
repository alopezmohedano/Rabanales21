package com.example.rabanales21.rabanales21;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FuncionesGenerales myController = new FuncionesGenerales();

    EditText edtUser, edtPass;

    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edtUser = (EditText) findViewById(R.id.edtusuario);

        edtPass = (EditText) findViewById(R.id.edtpass);

        imageButton=(ImageButton) findViewById(R.id.btnlogin);

        imageButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        Boolean userError = myController.badUser(edtUser.getText().toString());

        Integer passError = myController.badPass(edtPass.getText().toString());

        if (userError){

            myController.WarningMessages(this, "Nombre de usuario incorrecto o vacio");

        } else {

            switch (passError){

                case 0:

                    Intent intent = new Intent(this, MenuActivity.class);

                    startActivity(intent);

                    break;

                case 1:

                    myController.WarningMessages(this, "Contraseña incompleta. Debe contener 6 caracteres");

                    break;

                case 2:

                    myController.WarningMessages(this, "Contraseña de usuario incorrecta");

                    break;

            }

        }

    }

}
