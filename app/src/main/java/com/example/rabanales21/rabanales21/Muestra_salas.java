package com.example.rabanales21.rabanales21;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Muestra_salas extends Fragment {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private List<Sala> items;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_muestra_salas, container, false);
       final View v = inflater.inflate(R.layout.fragment_muestra_salas, container, false);



        items = new ArrayList<>();

        items.add(new Sala(getString(R.string.centauroGrande), 01, getString(R.string.capacidadCG)));
        items.add(new Sala(getString(R.string.centauroPeq), 02, getString(R.string.capacidadCP)));
        items.add(new Sala(getString(R.string.silos), 03, getString(R.string.capacidadSil)));
        items.add(new Sala(getString(R.string.formacion), 04, getString(R.string.capacidadFor)));
        items.add(new Sala(getString(R.string.aldebaran), 05, getString(R.string.capacidadAld)));



        // Obtener el Recycler
        recycler = (RecyclerView)v.findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

// Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(inflater.getContext());
        recycler.setLayoutManager(lManager);

// Crear un nuevo adaptador
        adapter = new MyAdapter(items);
        recycler.setAdapter(adapter);
        /*recycler.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                try {
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                    if (child != null ) {

                        int position = recyclerView.getChildAdapterPosition(child);
                        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                        ReservaSalas fragment = new ReservaSalas();
                        Bundle arguments = new Bundle();
                        arguments.putInt( "sala" , position);
                        fragment.setArguments(arguments);
                        fragmentManager.beginTransaction().replace(R.id.contenedor1,fragment).commit();

                        return true;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        }
        );*/
        return  v;


    }
    }

