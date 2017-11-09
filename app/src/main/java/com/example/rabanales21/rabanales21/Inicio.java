package com.example.rabanales21.rabanales21;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;


public class Inicio extends Fragment {
    private static int NUM_PAGES = 0;
    private static int currentPage = 0;
    ViewPager viewPager;
    int images[] = {R.drawable.sala1, R.drawable.sala2, R.drawable.sala3, R.drawable.sala4, R.drawable.sala5};
    MyCustomPagerAdapter myCustomPagerAdapter;
    FuncionesGenerales myController = new FuncionesGenerales();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager = (ViewPager) (getActivity().findViewById(R.id.viewPager));

        myCustomPagerAdapter = new MyCustomPagerAdapter(getActivity(), images);
        viewPager.setAdapter(myCustomPagerAdapter);

        TextView tvProxima = (TextView) getActivity().findViewById(R.id.tvProxima);

        String miPagina = "proximaReserva.php";

        if (getActivity().getIntent().hasExtra("respuestaLogin")) {
            String[] nombreUsuario = getActivity().getIntent().getStringArrayExtra("respuestaLogin");

            String miWhere = "?nombre_usuario=" + nombreUsuario[0];

            try {

                ConexionProximaReserva miCon = new ConexionProximaReserva();

                String[] respuesta = miCon.execute(myController.datosLlamada(miPagina, miWhere)).get();

                if (respuesta[0] != null) {

                    tvProxima.setText("Tu próxima reserva:\n\n" + respuesta[0] + "\nDía: " + myController.formatoFecha(respuesta[1]) + "\nHora: de " + respuesta[1].substring(11, 16) + " a " + respuesta[2].substring(11,16));

                } else {
                    tvProxima.setText("No tienes ninguna reserva.");
                }


            } catch (InterruptedException e) {

                e.printStackTrace();

            } catch (ExecutionException e) {

                e.printStackTrace();

            }
        }
            NUM_PAGES = images.length;
            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == NUM_PAGES) {
                        currentPage = 0;
                    }
                    viewPager.setCurrentItem(currentPage++, true);
                }
            };
            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 3000, 3000);

            ((MenuActivity)getActivity()).setBoleano(true);
    }
}