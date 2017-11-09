package com.example.rabanales21.rabanales21;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Gestionempresa extends Fragment {
    
    Button btnAnadir, btnModificar, btnEliminar, btnBorrar, btnBuscar, btnGuardar;

    TextView txtUsuario, txtPassword, txtEmpresa, txtBuscar;

    EditText edtUsuario, edtPassword, edtEmpresa;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gestionempresa, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        txtUsuario = getActivity().findViewById(R.id.txtNuevoUsusario);

        txtPassword = getActivity().findViewById(R.id.txtNuevoPassword);

        txtEmpresa = getActivity().findViewById(R.id.txtNuevaEmpresa);

        txtBuscar = getActivity().findViewById(R.id.txtInfoModificar);

        edtUsuario = getActivity().findViewById(R.id.edtNuevoUsuario);

        edtPassword = getActivity().findViewById(R.id.edtNuevoPassword);

        edtEmpresa = getActivity().findViewById(R.id.edtNuevaEmpresa);

        btnAnadir = getActivity().findViewById(R.id.btnAnadirEmpresa);

        btnAnadir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                camposUsuario(View.VISIBLE);

                camposPassword(View.VISIBLE);

                camposEmpresa(View.VISIBLE);

                camposBuscar(View.GONE);

                botonesAccion(View.VISIBLE);

                nuevoClick();

            }

        });

        btnModificar = getActivity().findViewById(R.id.btnModificarEmpresa);

        btnModificar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                camposUsuario(View.VISIBLE);

                camposPassword(View.GONE);

                camposEmpresa(View.GONE);

                camposBuscar(View.VISIBLE);

                botonesAccion(View.GONE);

                nuevoClick();

                txtBuscar.setText("Para obtener los datos completos de una empresa, introduzca el usuario y pulse el botón Buscar");

            }

        });

        btnEliminar = getActivity().findViewById(R.id.btnEliminarEmpresa);

        btnEliminar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                camposUsuario(View.VISIBLE);

                camposPassword(View.GONE);

                camposEmpresa(View.GONE);

                camposBuscar(View.GONE);

                botonesAccion(View.VISIBLE);

                nuevoClick();

            }

        });

        btnBorrar = getActivity().findViewById(R.id.btnBorrarCampos);

        btnBorrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                nuevoClick();

            }

        });

        btnBuscar = getActivity().findViewById(R.id.btnBuscarDatos);

        btnBuscar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                camposPassword(View.VISIBLE);

                camposEmpresa(View.VISIBLE);

                txtBuscar.setText("Para realizar una nueva busqueda, pulse el botón Modificar");

                btnBuscar.setVisibility(View.GONE);

                botonesAccion(View.VISIBLE);

                edtUsuario.setEnabled(false);

                if (edtUsuario.isFocusable()) {

                   edtPassword.requestFocus();

                }

            }

        });

        btnGuardar = getActivity().findViewById(R.id.btnHacerOperacion);

    }

    private void camposUsuario(Integer estado) {

        txtUsuario.setVisibility(estado);

        edtUsuario.setVisibility(estado);

        edtUsuario.setEnabled(true);

    }

    private void camposPassword(Integer estado){

        txtPassword.setVisibility(estado);

        edtPassword.setVisibility(estado);

    }

    private void camposEmpresa(Integer estado){

        txtEmpresa.setVisibility(estado);

        edtEmpresa.setVisibility(estado);

    }

    private void camposBuscar(Integer estado){

        txtBuscar.setVisibility(estado);

        btnBuscar.setVisibility(estado);

    }

    private void botonesAccion(Integer estado){

        btnBorrar.setVisibility(estado);

        btnGuardar.setVisibility(estado);

    }

    private void nuevoClick(){

        edtUsuario.setText("");

        edtPassword.setText("");

        edtEmpresa.setText("");

    }

}
