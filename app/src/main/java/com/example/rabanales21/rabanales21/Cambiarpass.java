package com.example.rabanales21.rabanales21;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.concurrent.ExecutionException;

/**
 * Gestiona el cambio de password de un usuario.</p>
 * Realiza las oomprobaciones de seguridad de forma secuencial:</br>
 * 1. Que los campos no esten vacios.</br>
 * 2. Que la nueva password introducida no coincida con la antigua.</br>
 * 3. Que la nueva password introducida cumpla los requisitos de longitud.</br>
 * 4. Que la nueva password introducida coincida en ambos campos.</br>
 * Posteriormente intenta realizar el update en la BBDD.</br>
 * Si tiene exito lo notifica con un Toast.</br>
 * En caso contrario notifica que la password antigua introducida no es correcta.</br>
 */

public class Cambiarpass extends Fragment {

    private FuncionesGenerales misFunciones = new FuncionesGenerales();
    private EditText edtAntiguo, edtNuevo, edtRepetido;
    private Button btnCambio;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_cambiarpass, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        ((MenuActivity)getActivity()).setBoleano(false);
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
