package com.example.rabanales21.rabanales21;

import android.app.Activity;
import android.content.DialogInterface;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Adaptador que gestiona el CardView con la  lista de todas las reservas del usuario, con opcion a eliminarlas.
 */

public class AdaptadorReservas extends RecyclerView.Adapter<AdaptadorReservas.SadapterViewHolder> {
    private List<ConsultaReserva> items;
    private FuncionesGenerales myController = new FuncionesGenerales();

    /**
     * Recoge las reservas obtenidas en la consulta a la BBDD. </br>
     * @param items Cada una de las reservas devueltas por la consulta a la BBDD
     */

    public AdaptadorReservas(List<ConsultaReserva> items) {

        this.items = items;
    }

    /**
     * Declara los campos que forma cada reserva
     */

    public static class SadapterViewHolder extends RecyclerView.ViewHolder {

        public TextView txtsala;
        public TextView txtfechainicio;
        public TextView txtnombreusuario;
        public TextView tvUsuario;
        public TextView txthorainicio;
        public Button btneliminar;

        /**
         * Asigna los elementos a los campos declarados previamente
         * @param v Cada uno de los elementos que contiene una reserva
         */

        public SadapterViewHolder(View v) {



            super(v);

            txtsala = (TextView) v.findViewById(R.id.nombresala);
            txtfechainicio = (TextView) v.findViewById(R.id.finicio);
            txtnombreusuario = (TextView) v.findViewById(R.id.idreserva);
            tvUsuario = (TextView)v.findViewById(R.id.tvUsuario);
            txthorainicio = (TextView) v.findViewById(R.id.hinicio);
            btneliminar = (Button) v.findViewById(R.id.botoneliminar);

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Infla la vista que va a mostrar las reservas obtenidas en la consulta.
     * @param viewGroup
     * @param i indice que controla el numero de reservas a mostrar.
     * @return Devuelve la vista inflada.
     */

    @Override
    public AdaptadorReservas.SadapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardreservas, viewGroup, false);
        return new AdaptadorReservas.SadapterViewHolder(v);
    }

    /**
     * Rellena los elementos con los datos obtenidos de la consulta a la BBDD.
     * @param viewHolder los elementos que conforman la reserva
     * @param i indice para rellenar la tarjeta de cada reserva individualmente
     */

    @Override
    public void onBindViewHolder(final AdaptadorReservas.SadapterViewHolder viewHolder, final int i) {

        viewHolder.txtsala.setText(items.get(i).getSala());
        viewHolder.txtfechainicio.setText(String.valueOf(items.get(i).getFecha_inicio()));
        viewHolder.txtnombreusuario.setText(String.valueOf(items.get(i).getNombre_usuario()));
        viewHolder.txtnombreusuario.setVisibility(View.GONE);
        viewHolder.tvUsuario.setVisibility(View.GONE);
        if (String.valueOf(items.get(i).getTipo_usuario()).equals("1")) {
            viewHolder.txtnombreusuario.setVisibility(View.VISIBLE);
            viewHolder.tvUsuario.setVisibility(View.VISIBLE);
        }
        viewHolder.txthorainicio.setText(String.valueOf(items.get(i).getHora_inio())+" a "+String.valueOf(items.get(i).getHora_fin()));
        viewHolder.btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String idreserva;
                idreserva = items.get(i).getId_reserva();
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder((Activity) view.getContext());
                dialogo1.setTitle(R.string.warning);
                dialogo1.setMessage(R.string.cancelReserva);
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                        String miPagina = "cancelarReserva.php";

                        String miWhere = "?cod_r=" + idreserva;

                        try {

                            ConexionCancelar miCon = new ConexionCancelar();

                            Integer respuesta = miCon.execute(myController.datosLlamada(miPagina, miWhere)).get();

                            if (respuesta == 1){
                                Toast.makeText(view.getContext(), R.string.confCancel, Toast.LENGTH_LONG).show();

                                Consultar fragment = new Consultar();
                                if (items.get(i).getTipo_usuario() == "1") {
                                    Bundle arguments = new Bundle();
                                    arguments.putString("reservas", "todas");
                                    fragment.setArguments(arguments);
                                }
                                FragmentManager fragmentManager = ((FragmentActivity)view.getContext()).getSupportFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.contenedor1, fragment).addToBackStack(null).commit();
                            } else {
                                Toast.makeText(view.getContext(), R.string.negCancel, Toast.LENGTH_LONG).show();

                            }


                        } catch (InterruptedException e) {

                            e.printStackTrace();

                        } catch (ExecutionException e) {

                            e.printStackTrace();

                        }
                    }
                });
                dialogo1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();

            }
        });

    }


}