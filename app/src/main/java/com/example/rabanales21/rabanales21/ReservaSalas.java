package com.example.rabanales21.rabanales21;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class ReservaSalas extends Fragment implements View.OnClickListener {
    CalendarView calendarView;
    ArrayList<String> horasStart = new ArrayList<>();
    ArrayList<String> horasEnd = new ArrayList<>();
    FuncionesGenerales myController = new FuncionesGenerales();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reserva_salas, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //setContentView(R.layout.activity_reservar);

        final Button btnDate = (Button) (getActivity().findViewById(R.id.btnDate));
        btnDate.setOnClickListener(this);
        final Button btnReservar = (Button) (getActivity().findViewById(R.id.btnReservar));
        btnReservar.setOnClickListener(this);

        // TextView tvDate = (TextView) findViewById(R.id.tvDate);
        final TextView tvStart = (TextView) (getActivity().findViewById(R.id.tvStart));
        final TextView tvEnd = (TextView) (getActivity().findViewById(R.id.tvEnd));

        tvStart.setVisibility(View.GONE);
        tvEnd.setVisibility(View.GONE);

        Spinner spSalas = (Spinner) (getActivity().findViewById(R.id.spSalas));
        final Spinner spStart = (Spinner) (getActivity().findViewById(R.id.spStart));
        final Spinner spEnd = (Spinner) (getActivity().findViewById(R.id.spEnd));

        String[] salas = {getString(R.string.salaCentauroG), getString(R.string.salaCentauroP), getString(R.string.salaSilos), getString(R.string.salaFormacion), getString(R.string.salaAldebaran)};


        for (int i=7; i<22; i++) {horasStart.add(i+":00");}
        for (int i=8; i<23; i++) {horasEnd.add(i+":00");}

        int[] testReserva = {9,14};

        eliminarIntervaloReserva(testReserva);


        calendarView = (CalendarView) (getActivity().findViewById(R.id.calendarView));

        calendarView.setVisibility(View.GONE);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                btnDate.setText(dayOfMonth + " - " + (month + 1) + " - " + year);
                calendarView.setVisibility(View.GONE);
                spStart.setVisibility(View.VISIBLE);
                tvStart.setVisibility(View.VISIBLE);
            }
        });

        spSalas.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, salas));
        int numeroSala = 0;
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            numeroSala = arguments.getInt("sala");

            spSalas.setSelection(numeroSala);
        }


        // int[] testReserva = {17, 20};

        String miPagina = "consultaReservas.php";

        if (getActivity().getIntent().hasExtra("respuestaLogin")) {
            String[] datosUsuario = getActivity().getIntent().getStringArrayExtra("respuestaLogin");
            int codUsuario = Integer.parseInt(datosUsuario[3]);

            String miWhere = "?cod_usuario=" + codUsuario + "&cod_sala=" + (numeroSala + 1);

            try {

                ConexionConsultaReservas miCon = new ConexionConsultaReservas();

                Reserva[] respuesta = miCon.execute(myController.datosLlamada(miPagina, miWhere)).get();

                if (respuesta[0] != null) {
                    for (int i=0;i<respuesta.length;i++) {
                        int[] intervalo = new int[2];

                        intervalo[0] = Integer.parseInt(respuesta[i].getInicio().substring(11,13));
                        intervalo[1] = Integer.parseInt(respuesta[i].getFin().substring(11,13));

                        eliminarIntervaloReserva(intervalo);
                    }
                }

            } catch (InterruptedException e) {

                e.printStackTrace();

            } catch (ExecutionException e) {

                e.printStackTrace();

            }
        }

        // eliminarIntervaloReserva(testReserva);

        spStart.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, horasStart));
        spStart.setVisibility(View.GONE);
        spStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spEnd.setVisibility(View.VISIBLE);
                tvEnd.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spEnd.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, horasEnd));
        spEnd.setVisibility(View.GONE);
        spEnd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void eliminarIntervaloReserva(int [] horarioReserva) {
        String[] stringReserva = {String.valueOf(horarioReserva[0] + ":00"), String.valueOf(horarioReserva[1] + ":00")};
        int intervaloReserva = horarioReserva[1] - horarioReserva[0];
        for (int i = 0; i < horasStart.size(); i++) {
            if (horasStart.get(i).equals(stringReserva[0])) {
                for (int j = i + intervaloReserva -1; j >= i; j--) {
                    horasStart.remove(j);
                    horasEnd.remove(j);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDate:
                if (calendarView.getVisibility() == View.GONE) {
                    calendarView.setVisibility(View.VISIBLE);
                } else {
                    calendarView.setVisibility(View.GONE);
                }
                break;
            case R.id.btnReservar:
                AlertDialog.Builder cuadro  = new AlertDialog.Builder(getActivity());
                cuadro.setMessage(getString(R.string.confReserva));
                //uno para aceptar que llamará a la actividad editarActivity pasandole el contacto a editar
                cuadro.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                //y otro para cancelar que no hace nada
                cuadro.setNegativeButton(android.R.string.no, null);

                cuadro.show();
                break;
        }
    }
}
