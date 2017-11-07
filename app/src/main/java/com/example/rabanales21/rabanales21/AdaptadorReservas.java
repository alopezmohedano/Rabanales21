package com.example.rabanales21.rabanales21;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by usuario on 06/11/2017.
 */

public class AdaptadorReservas extends RecyclerView.Adapter<AdaptadorReservas.SadapterViewHolder> {
    private List<ConsultaReserva> items;

    public AdaptadorReservas(List<ConsultaReserva> items) {
        this.items = items;
    }

    public static class SadapterViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item

        public TextView txtsala;
        public TextView txtfechainicio;
        public TextView txtfechafin;
        public TextView txthorainicio;
        public TextView txthorafin;
        public ImageButton btneliminar;



        public SadapterViewHolder(View v) {
            super(v);

            txtsala = (TextView) v.findViewById(R.id.nombresala);
            txtfechainicio = (TextView) v.findViewById(R.id.finicio);
            txtfechafin = (TextView) v.findViewById(R.id.ffin);
            txthorainicio = (TextView) v.findViewById(R.id.hinicio);
            txthorafin = (TextView) v.findViewById(R.id.hfin);
            btneliminar = (ImageButton) v.findViewById(R.id.botoneliminar);

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public AdaptadorReservas.SadapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardreservas, viewGroup, false);
        return new AdaptadorReservas.SadapterViewHolder(v);
    }


    @Override
    public void onBindViewHolder(AdaptadorReservas.SadapterViewHolder viewHolder, final int i) {
        viewHolder.txtsala.setText(items.get(i).getSala());
        viewHolder.txtfechainicio.setText(String.valueOf(items.get(i).getFecha_inicio()));
        viewHolder.txtfechafin.setText(String.valueOf(items.get(i).getFecha_fin()));
        viewHolder.txthorainicio.setText(String.valueOf(items.get(i).getHora_inio()));
        viewHolder.txthorafin.setText(String.valueOf(items.get(i).getHora_fin()));
        //viewHolder.btneliminar.setOnClickListener((View.OnClickListener)items.remove(i));

    }
}
