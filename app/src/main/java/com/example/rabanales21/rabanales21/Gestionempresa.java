package com.example.rabanales21.rabanales21;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

/**
 * CRUD de gestion de usuarios para el administrador. </p>
 */

public class Gestionempresa extends Fragment implements View.OnClickListener{

    private Button btnAnadir, btnModificar, btnEliminar, btnBorrar, btnBuscar, btnGuardar;
    private TextView txtUsuario, txtPassword, txtEmpresa, txtBuscar;
    private EditText edtUsuario, edtPassword, edtEmpresa;
    private Integer bandera = 0;
    private FuncionesGenerales misFunciones = new FuncionesGenerales();

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
        btnAnadir.setOnClickListener(this);
        btnModificar = getActivity().findViewById(R.id.btnModificarEmpresa);
        btnModificar.setOnClickListener(this);
        btnEliminar = getActivity().findViewById(R.id.btnEliminarEmpresa);
        btnEliminar.setOnClickListener(this);
        btnBorrar = getActivity().findViewById(R.id.btnBorrarCampos);
        btnBorrar.setOnClickListener(this);
        btnBuscar = getActivity().findViewById(R.id.btnBuscarDatos);
        btnBuscar.setOnClickListener(this);
        btnGuardar = getActivity().findViewById(R.id.btnHacerOperacion);
        btnGuardar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String miPagina = "", miWhere = "";
        switch (view.getId()) {
            case R.id.btnAnadirEmpresa:
                bandera = 1;
                camposUsuario(View.VISIBLE);
                camposPassword(View.VISIBLE);
                camposEmpresa(View.VISIBLE);
                camposBuscar(View.GONE);
                botonesAccion(View.VISIBLE);
                nuevoClick();
                break;
            case R.id.btnModificarEmpresa:
                bandera = 2;
                camposUsuario(View.VISIBLE);
                camposPassword(View.GONE);
                camposEmpresa(View.GONE);
                camposBuscar(View.VISIBLE);
                botonesAccion(View.GONE);
                nuevoClick();
                txtBuscar.setText(getString(R.string.infoBotonBuscar));
                break;
            case R.id.btnEliminarEmpresa:
                bandera = 3;
                camposUsuario(View.VISIBLE);
                camposPassword(View.GONE);
                camposEmpresa(View.GONE);
                camposBuscar(View.GONE);
                botonesAccion(View.VISIBLE);
                nuevoClick();
                break;
            case R.id.btnBorrarCampos:
                nuevoClick();
                break;
            case R.id.btnBuscarDatos:
                bandera = 4;
                camposPassword(View.VISIBLE);
                camposEmpresa(View.VISIBLE);
                txtBuscar.setText(getString(R.string.infoBotonBuscado));
                btnBuscar.setVisibility(View.GONE);
                botonesAccion(View.VISIBLE);
                edtUsuario.setEnabled(false);
                if (edtUsuario.isFocusable()) {
                    edtPassword.requestFocus();
                }
                break;

            case R.id.btnHacerOperacion:
                switch (bandera){
                    case 1:
                        if(!camposVacios()){
                            //misFunciones.WarningMessages(getActivity(), "hola");
                            miPagina = "GestionEmpresas.php";
                            miWhere = "?nombre_usuario=" + edtUsuario.getText().toString() + "&password=" + edtPassword.getText().toString() + "&nombre_empresa=" + edtEmpresa.getText().toString() + "&bandera=" + bandera;
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                        misFunciones.WarningMessages(getActivity(), getString(R.string.wrongOption));
                        break;
                }
                try {
                    ConexionGestionEmpresas miCon = new ConexionGestionEmpresas();
                    String[] respuesta = miCon.execute(misFunciones.datosLlamada(miPagina, miWhere)).get();
                    if(respuesta[0].toString().equals("false")){
                        misFunciones.WarningMessages(getActivity(), "baia baia");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
        }
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

    private Boolean camposVacios(){
        Boolean error = false;
        if (misFunciones.badUser(edtUsuario.getText().toString())) {
            misFunciones.WarningMessages(getActivity(), getString(R.string.emptyUser));
            error = true;
        } else if(misFunciones.badUser(edtEmpresa.getText().toString())){
            misFunciones.WarningMessages(getActivity(), getString(R.string.nodataEmpresa));
            error = true;
        } else {
            switch (misFunciones.badPass(edtPassword.getText().toString())){
                case 0:
                    //misFunciones.WarningMessages(getActivity(), "TA-CHAN MADAFAKA");
                    nuevoClick();
                    break;
                case 1:
                    misFunciones.WarningMessages(getActivity(), getString(R.string.emptyPass));
                    error = true;
                    break;
                case 2:
                    misFunciones.WarningMessages(getActivity(), getString(R.string.wrongPass));
                    error = true;
                    break;
            }
        }
        return error;
    }
}
