package com.example.rabanales21.rabanales21;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

    private TextView txtBuscar;

    private EditText edtUsuario, edtPassword, edtEmpresa;

    private View linear;

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

        txtBuscar = getActivity().findViewById(R.id.txtInfoModificar);

        linear=getActivity().findViewById(R.id.linearLayout4);

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

                bandera = 2;

                if (misFunciones.badUser(edtUsuario.getText().toString())) {

                    misFunciones.WarningMessages(getActivity(), getString(R.string.emptyUser));

                } else {

                    miPagina = "BusquedaUsuarios.php";

                    miWhere = "?nombre_usuario=" + edtUsuario.getText().toString();

                    try {

                        BusquedaUsuarios miBusqueda = new BusquedaUsuarios();

                        String[] respuesta2 = miBusqueda.execute(misFunciones.datosLlamada(miPagina, miWhere)).get();

                        if (respuesta2[0] == null) {

                            misFunciones.WarningMessages(getActivity(), "El usuario no existe");

                        } else {

                            if(respuesta2[2].toString().equals("1")){

                                misFunciones.WarningMessages(getActivity(), "Este usuario no se puede modificar");

                            } else {

                                camposPassword(View.VISIBLE);

                                camposEmpresa(View.VISIBLE);

                                txtBuscar.setText(getString(R.string.infoBotonBuscado));

                                btnBuscar.setVisibility(View.GONE);

                                botonesAccion(View.VISIBLE);

                                edtUsuario.setEnabled(false);

                                if (edtUsuario.isFocusable()) {

                                    edtPassword.requestFocus();

                                }

                                edtPassword.setText(respuesta2[0].toString());

                                edtEmpresa.setText(respuesta2[1].toString());

                            }

                        }

                    } catch (InterruptedException e) {

                    } catch (ExecutionException e) {

                    }

                }

                break;

            case R.id.btnHacerOperacion:

                Boolean exito = false;

                switch (bandera){

                    case 1:

                        if(!camposVacios()){

                            miPagina = "GestionEmpresas.php";

                            miWhere = "?nombre_usuario=" + edtUsuario.getText().toString() + "&password=" + edtPassword.getText().toString() + "&nombre_empresa=" + edtEmpresa.getText().toString() + "&bandera=" + bandera;

                            exito = true;

                        }

                        break;

                    case 2:

                        if(!camposVacios()){

                            miPagina = "GestionEmpresas.php";

                            miWhere = "?nombre_usuario=" + edtUsuario.getText().toString() + "&password=" + edtPassword.getText().toString() + "&nombre_empresa=" + edtEmpresa.getText().toString() + "&bandera=" + bandera;

                            exito = true;

                        }

                        break;

                    case 3:

                        if (misFunciones.badUser(edtUsuario.getText().toString())) {

                            misFunciones.WarningMessages(getActivity(), getString(R.string.emptyUser));

                        } else {

                            miPagina = "BusquedaUsuarios.php";

                            miWhere = "?nombre_usuario=" + edtUsuario.getText().toString();

                            try {

                                BusquedaUsuarios miBusqueda = new BusquedaUsuarios();

                                String[] respuesta2 = miBusqueda.execute(misFunciones.datosLlamada(miPagina, miWhere)).get();

                                if (respuesta2[0] == null) {

                                    misFunciones.WarningMessages(getActivity(), "El usuario no existe");

                                } else {

                                    if(respuesta2[2].toString().equals("1")){

                                        misFunciones.WarningMessages(getActivity(), "Este usuario no se puede eliminar");

                                    } else {

                                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                                        builder.setMessage("¿Está seguro de que desea borrar " + edtUsuario.getText().toString() + "?").setCancelable(false)
                                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {

                                                        String miPagina2 = "GestionEmpresas.php";

                                                        String miWhere2 = "?nombre_usuario=" + edtUsuario.getText().toString() + "&bandera=" + bandera;

                                                        try{
                                                            ConexionGestionEmpresas miCon = new ConexionGestionEmpresas();

                                                            Integer respuesta = miCon.execute(misFunciones.datosLlamada(miPagina2, miWhere2)).get();

                                                            if(respuesta == 1){

                                                                misFunciones.WarningMessages(getActivity(), "El usuario se ha eliminado correctamente");

                                                                nuevoClick();

                                                            } else {

                                                                misFunciones.WarningMessages(getActivity(), "El usuario no se encontraba en la base de datos");

                                                            }

                                                        } catch (InterruptedException e){

                                                        } catch (ExecutionException e){

                                                        }

                                                        nuevoClick();

                                                    }
                                                })
                                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {

                                                        dialog.cancel();

                                                    }
                                                });

                                        AlertDialog alert = builder.create();

                                        alert.show();

                                    }

                                }

                            } catch (InterruptedException e) {

                            } catch (ExecutionException e) {

                            }

                        }

                        break;

                    case 4:

                        break;

                    default:

                        misFunciones.WarningMessages(getActivity(), getString(R.string.wrongOption));

                        break;

                }

                try {

                    switch (bandera) {

                        case 1:

                            if (exito){

                                ConexionGestionEmpresas miCon = new ConexionGestionEmpresas();

                                Integer respuesta = miCon.execute(misFunciones.datosLlamada(miPagina, miWhere)).get();

                                if(respuesta == 1){
                                  
                                  misFunciones.WarningMessages(getActivity(), getString(R.string.confUserCreated));

                                    nuevoClick();

                                } else {

                                misFunciones.WarningMessages(getActivity(), getString(R.string.repetUser));

                                }

                                edtUsuario.setFocusable(true);

                                edtUsuario.selectAll();

                            }

                            break;

                        case 2:

                            if(exito){

                                ConexionGestionEmpresas miCon = new ConexionGestionEmpresas();

                                Integer respuesta = miCon.execute(misFunciones.datosLlamada(miPagina, miWhere)).get();

                                if(respuesta == 1){

                                    misFunciones.WarningMessages(getActivity(), "El usuario se ha actualizado correctamente");

                                    camposUsuario(View.VISIBLE);

                                    camposPassword(View.GONE);

                                    camposEmpresa(View.GONE);

                                    camposBuscar(View.VISIBLE);

                                    botonesAccion(View.GONE);

                                    nuevoClick();

                                    txtBuscar.setText(getString(R.string.infoBotonBuscar));

                                    edtUsuario.setText("");

                                } else {

                                    misFunciones.WarningMessages(getActivity(), "No se ha realizado ninguna modificación");

                                }

                            }

                            break;

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

        edtUsuario.setVisibility(estado);

        edtUsuario.setEnabled(true);

    }

    private void camposPassword(Integer estado){

        edtPassword.setVisibility(estado);

    }

    private void camposEmpresa(Integer estado){

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

        if(bandera != 2){

            edtUsuario.setText("");

        }

        edtPassword.setText("");

        edtEmpresa.setText("");

        edtUsuario.setFocusable(true);

        if(bandera == 3){

            btnGuardar.setText("Eliminar");

        } else {

            btnGuardar.setText("Guardar");

        }

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

                    //nuevoClick();

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