package com.example.rabanales21.rabanales21;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.SadapterViewHolder> {
    private List<Sala> items;

    private Context context;

    public MyAdapter(List<Sala> items) {
        this.items = items;
    }
    public MyAdapter(Context context, List<Sala> items) {
        this.context = context;
        this.items = items;
    }



    public static class SadapterViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        //public ImageView imagen;
        public TextView nombre;
        public TextView visitas;
        //private CardView cardView;


        public SadapterViewHolder(View v) {
            super(v);
            //imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre);
            visitas = (TextView) v.findViewById(R.id.visitas);
            //cardView = (CardView) v.findViewById(R.id.card);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position= getAdapterPosition();
                    FragmentManager fragmentManager = ((FragmentActivity)v.getContext()).getSupportFragmentManager();
                    Fragment fragment = new ReservaSalas();
                    Bundle arguments = new Bundle();
                    arguments.putInt( "sala" , position);
                    fragment.setArguments(arguments);
                    fragmentManager.beginTransaction().replace(R.id.contenedor1,fragment).addToBackStack(null).commit();

                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public SadapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.my_cardview, viewGroup, false);
        return new SadapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SadapterViewHolder viewHolder, final int i) {
        //viewHolder.imagen.setImageResource(items.get(i).getImagen());
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.visitas.setText(String.valueOf(items.get(i).getDescripcion()));


    }





}
