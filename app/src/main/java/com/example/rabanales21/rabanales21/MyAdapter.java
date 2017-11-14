package com.example.rabanales21.rabanales21;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Adaptador que gestiona el CardView de la pantalla de salas. </p>
 */

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.SadapterViewHolder> {
    private List<Sala> items;

    private Context context;

    /**
     * Recoge la informacion de las salas
     * @param items Cada una de las salas existentes en la aplicacion
     */

    public MyAdapter(List<Sala> items) {
        this.items = items;
    }
    public MyAdapter(Context context, List<Sala> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * Declara los campos que forman cada sala.
     */

    public static class SadapterViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        //public ImageView imagen;
        public TextView nombre;
        public TextView visitas;
        //private CardView cardView;

        /**
         * Asigna los elementos a los campos declarados previamente.
         * @param v Cada uno de los elementos que forma la descripcion de las salas
         */

        public SadapterViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre);
            visitas = (TextView) v.findViewById(R.id.visitas);
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

    /**
     * Infla la vista que va a mostrar la descripcion de las salas
     * @param viewGroup
     * @param i indice que controla el numero de salas a mostrar
     * @return Devuelve la vista inflada
     */

    @Override
    public SadapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.my_cardview, viewGroup, false);
        return new SadapterViewHolder(v);
    }

    /**
     * Rellena los elementos con la informacion precargada en la aplicacion
     * @param viewHolder los elementos que forman la descripcion de las salas
     * @param i indice para rellenar la tarjeta de cada sala individualmente
     */

    @Override
    public void onBindViewHolder(SadapterViewHolder viewHolder, final int i) {
        //viewHolder.imagen.setImageResource(items.get(i).getImagen());
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.visitas.setText(String.valueOf(items.get(i).getDescripcion()));


    }
}
