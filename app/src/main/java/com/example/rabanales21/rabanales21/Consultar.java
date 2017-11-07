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

import java.util.ArrayList;
import java.util.List;


public class Consultar extends Fragment {
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private List<ConsultaReserva> items;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_consultar, container, false);

    items = new ArrayList<>();

        items.add(new ConsultaReserva("Sala Aldebaran", "22 enero", "22 de enero", "10:00", "12:00"));
        items.add(new ConsultaReserva("Sala Formacion", "23 enero", "23 de enero", "12:00", "14:00"));


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


