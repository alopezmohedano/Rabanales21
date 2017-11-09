package com.example.rabanales21.rabanales21;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class Consultar extends Fragment {
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private List<ConsultaReserva> items;
    FuncionesGenerales myController = new FuncionesGenerales();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_consultar, container, false);

    items = new ArrayList<>();

        String miPagina = "consultaReservas.php";

        if (getActivity().getIntent().hasExtra("respuestaLogin")) {
            String[] datosUsuario = getActivity().getIntent().getStringArrayExtra("respuestaLogin");
            int codUsuario = Integer.parseInt(datosUsuario[3]);


            String miWhere = "?cod_usuario=" + codUsuario;

            try {

                ConexionConsultaReservas miCon = new ConexionConsultaReservas();

                Reserva[] respuesta = miCon.execute(myController.datosLlamada(miPagina, miWhere)).get();

                if (respuesta[0] != null) {
                    for (int i=0;i<respuesta.length;i++) {
                        String sala = "";
                        switch (Integer.parseInt(respuesta[i].getCod_s())){
                            case 0:
                                sala = "Sala Centauro Grande";
                                break;
                            case 1:
                                sala = "Sala Centauro Pequeña";
                                break;
                            case 2:
                                sala = "Sala Silos";
                                break;
                            case 3:
                                sala = "Sala de Formación";
                                break;
                            case 4:
                                sala = "Sala Aldebarán";
                                break;
                        }
                        String mes = "";
                        switch (Integer.parseInt(respuesta[i].getInicio().substring(5,7))) {
                            case 1:
                                mes = "Enero";
                                break;
                            case 2:
                                mes = "Febrero";
                                break;
                            case 3:
                                mes = "Marzo";
                                break;
                            case 4:
                                mes = "Abril";
                                break;
                            case 5:
                                mes = "Mayo";
                                break;
                            case 6:
                                mes = "Junio";
                                break;
                            case 7:
                                mes = "Julio";
                                break;
                            case 8:
                                mes = "Agosto";
                                break;
                            case 9:
                                mes = "Septiembre";
                                break;
                            case 10:
                                mes = "Octubre";
                                break;
                            case 11:
                                mes = "Noviembre";
                                break;
                            case 121:
                                mes = "Diciembre";
                                break;
                        }
                        items.add(new ConsultaReserva(sala,
                                respuesta[i].getInicio().substring(8,10) + " de " + mes,
                                respuesta[i].getCod_r(),
                                respuesta[i].getInicio().substring(11,16),
                                respuesta[i].getFin().substring(11,16)));
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    // Obtener el Recycler
    recycler = (RecyclerView)v.findViewById(R.id.reservasreciclador);
        recycler.setHasFixedSize(true);

// Usar un administrador para LinearLayout
    lManager = new LinearLayoutManager(inflater.getContext());
        recycler.setLayoutManager(lManager);

// Crear un nuevo adaptador
    adapter = new AdaptadorReservas(items);
        recycler.setAdapter(adapter);

        return  v;


}


}


