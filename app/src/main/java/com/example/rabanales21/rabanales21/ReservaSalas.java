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


public class ReservaSalas extends Fragment implements View.OnClickListener {
    CalendarView calendarView;
    ArrayList<String> horasStart = new ArrayList<>();
    ArrayList<String> horasEnd = new ArrayList<>();

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
                btnDate.setText(dayOfMonth+" - "+(month+1)+" - "+year);
                calendarView.setVisibility(View.GONE);
                spStart.setVisibility(View.VISIBLE);
                tvStart.setVisibility(View.VISIBLE);
            }
        });

        spSalas.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, salas));

        if (getArguments() != null) {
            Bundle arguments = getArguments();
            int numeroSala = arguments.getInt("sala");

            spSalas.setSelection(numeroSala);
        }

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
                for (int j = i + intervaloReserva; j >= i; j--) {
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
