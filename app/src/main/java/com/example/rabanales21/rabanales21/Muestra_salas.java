package com.example.rabanales21.rabanales21;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Muestra_salas extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Sala> items = new ArrayList<>();

        items.add(new Sala("Sala Centauro Grande", 01, "Capacidad: 14"));
        items.add(new Sala( "Sala Centauro Pequeña", 02, "Capacidad: 8"));
        items.add(new Sala("Sala Silos", 03, "Capacidad: 10"));
        items.add(new Sala( "Sala de Formación", 04, "Capacidad: 50"));
        items.add(new Sala("Sala Aldebarán", 05, "Capacidad: 30"));

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

// Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

// Crear un nuevo adaptador
        adapter = new MyAdapter(items);
        recycler.setAdapter(adapter);
    }


}