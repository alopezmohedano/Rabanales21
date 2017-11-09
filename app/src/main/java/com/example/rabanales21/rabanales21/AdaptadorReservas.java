package com.example.rabanales21.rabanales21;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
        public TextView txtidreserva;
        public TextView txthorainicio;
        public TextView txthorafin;
        public ImageButton btneliminar;



        public SadapterViewHolder(View v) {
            super(v);

            txtsala = (TextView) v.findViewById(R.id.nombresala);
            txtfechainicio = (TextView) v.findViewById(R.id.finicio);
            txtidreserva = (TextView) v.findViewById(R.id.idreserva);
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
        viewHolder.txtidreserva.setText(String.valueOf(items.get(i).getId_reserva()));
        viewHolder.txthorainicio.setText(String.valueOf(items.get(i).getHora_inio()));
        viewHolder.txthorafin.setText(String.valueOf(items.get(i).getHora_fin()));
        viewHolder.btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idreserva;
                idreserva = items.get(i).getId_reserva();
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder((Activity) view.getContext());
                dialogo1.setTitle("Atención");
                dialogo1.setMessage("¿ Cancelar esta reserva ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {


                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();

            }
        });

    }


}