package com.example.rabanales21.rabanales21;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;


public class Cambiarpass extends Fragment {

    FuncionesGenerales misFunciones = new FuncionesGenerales();

    EditText edtAntiguo, edtNuevo, edtRepetido;

    Button btnCambio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cambiarpass, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        edtAntiguo = getActivity().findViewById(R.id.passvieja);

        edtNuevo = getActivity().findViewById(R.id.passnueva1);

        edtRepetido = getActivity().findViewById(R.id.passnueva2);

        btnCambio = getActivity().findViewById(R.id.bt_cambiar);

        btnCambio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (edtAntiguo.getText().toString().equals("") || edtNuevo.getText().toString().equals("") || edtRepetido.getText().toString().equals("")) {

                    misFunciones.WarningMessages(getActivity(), getString(R.string.emptyPassWarning));

                } else {

                    if (edtAntiguo.getText().toString().equals(edtNuevo.getText().toString())) {

                        misFunciones.WarningMessages(getActivity(), getString(R.string.samePassWarning));

                    } else {

                        if (edtNuevo.getText().toString().length() < 8) {

                            misFunciones.WarningMessages(getActivity(), getString(R.string.shortPassWarning));

                        } else {

                            if (edtNuevo.getText().toString().equals(edtRepetido.getText().toString())) {

                                String[] datos = getActivity().getIntent().getStringArrayExtra("respuestaLogin");

                                String miPagina = "UpdatePass.php";

                                String miWhere = "?nombre_usuario=" + datos[0].toString() + "&pass=" + edtAntiguo.getText().toString() + "&passN=" + edtNuevo.getText().toString();

                                try {

                                    Integer resultado;

                                    ConexionUpdate miCon = new ConexionUpdate();

                                    resultado = miCon.execute(misFunciones.datosLlamada(miPagina, miWhere)).get();

                                    if (resultado == 1) {

                                        misFunciones.WarningMessages(getActivity(), getString(R.string.changeWarning));

                                    } else {

                                        misFunciones.WarningMessages(getActivity(), getString(R.string.wrongPassWarning));

                                    }

                                } catch (InterruptedException e) {

                                    e.printStackTrace();

                                } catch (ExecutionException e) {

                                    e.printStackTrace();

                                }

                            } else {

                                misFunciones.WarningMessages(getActivity(), getString(R.string.difPassWarning));

                            }
                        }

                    }

                }
            }

        });

    }
}