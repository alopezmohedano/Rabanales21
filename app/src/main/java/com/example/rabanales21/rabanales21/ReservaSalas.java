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
import java.util.Objects;
import java.util.concurrent.ExecutionException;


public class ReservaSalas extends Fragment implements View.OnClickListener {
    CalendarView calendarView;
    ArrayList<String> horasStart = new ArrayList<>();
    ArrayList<String> horasEnd = new ArrayList<>();
    FuncionesGenerales myController = new FuncionesGenerales();
    String diaEscogido;
    int numeroSala = 0;
    Spinner spSalas;
    Spinner spStart;
    Spinner spEnd;
    Button btnDate;
    String fechaEscogida;

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

        btnDate = (Button) (getActivity().findViewById(R.id.btnDate));
        btnDate.setOnClickListener(this);
        final Button btnReservar = (Button) (getActivity().findViewById(R.id.btnReservar));
        btnReservar.setOnClickListener(this);

        // TextView tvDate = (TextView) findViewById(R.id.tvDate);
        final TextView tvStart = (TextView) (getActivity().findViewById(R.id.tvStart));
        final TextView tvEnd = (TextView) (getActivity().findViewById(R.id.tvEnd));

        tvStart.setVisibility(View.GONE);
        tvEnd.setVisibility(View.GONE);

        spSalas = (Spinner) (getActivity().findViewById(R.id.spSalas));
        spStart = (Spinner) (getActivity().findViewById(R.id.spStart));
        spEnd = (Spinner) (getActivity().findViewById(R.id.spEnd));

        String[] salas = {getString(R.string.salaCentauroG), getString(R.string.salaCentauroP), getString(R.string.salaSilos), getString(R.string.salaFormacion), getString(R.string.salaAldebaran)};

        spSalas.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, salas));


        if (getArguments() != null) {
            Bundle arguments = getArguments();
            numeroSala = arguments.getInt("sala");

            spSalas.setSelection(numeroSala);
        }

        spSalas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tvStart.setVisibility(View.GONE);
                tvEnd.setVisibility(View.GONE);
                spStart.setVisibility(View.GONE);
                spEnd.setVisibility(View.GONE);
                btnDate.setText("FECHA");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        for (int i=7; i<22; i++) {horasStart.add(i+":00");}
        for (int i=8; i<23; i++) {horasEnd.add(i+":00");}

        // int[] testReserva = {9,14};

        // eliminarIntervaloReserva(testReserva);

        calendarView = (CalendarView) (getActivity().findViewById(R.id.calendarView));

        calendarView.setVisibility(View.GONE);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                fechaEscogida = year + "-";
                if (month < 10) {
                    fechaEscogida += "0" + (month + 1) + "-";
                } else {
                    fechaEscogida += (month + 1) + "-";
                }
                if (dayOfMonth < 10) {
                    fechaEscogida += "0" + dayOfMonth;
                } else {
                    fechaEscogida += dayOfMonth;
                }

                btnDate.setText(dayOfMonth + " - " + (month + 1) + " - " + year);
                calendarView.setVisibility(View.GONE);
                spStart.setVisibility(View.VISIBLE);
                tvStart.setVisibility(View.VISIBLE);
                spEnd.setVisibility(View.VISIBLE);
                tvEnd.setVisibility(View.VISIBLE);
                spStart.setSelection(0);
                spEnd.setSelection(0);

                horasStart.clear();
                for (int i=7; i<22; i++) {horasStart.add(i+":00");}

                horasEnd.clear();
                for (int i=8; i<23; i++) {horasEnd.add(i+":00");}

                diaEscogido = year + "-";
                if (month +1 <10) {
                    diaEscogido += "0" + (month + 1);
                } else {
                    diaEscogido += (month + 1);
                }
                if (dayOfMonth < 10) {
                    diaEscogido += "-0" + dayOfMonth;
                } else {
                    diaEscogido += "-" + dayOfMonth;
                }

                String miPagina = "consultaReservasSala.php";

                if (getActivity().getIntent().hasExtra("respuestaLogin")) {
                    String[] datosUsuario = getActivity().getIntent().getStringArrayExtra("respuestaLogin");
                    int codUsuario = Integer.parseInt(datosUsuario[3]);
                    if (getArguments() != null) {
                        Bundle arguments = getArguments();
                        numeroSala = arguments.getInt("sala");
                    }

                    String miWhere = "?cod_usuario=" + codUsuario + "&cod_sala=" + (numeroSala + 1);

                    try {

                        ConexionConsultaReservas miCon = new ConexionConsultaReservas();

                        Reserva[] respuesta = miCon.execute(myController.datosLlamada(miPagina, miWhere)).get();

                        if (respuesta[0] != null) {
                            for (int i=0;i<respuesta.length;i++) {
                                if (diaEscogido.equals(respuesta[i].getInicio().substring(0, 10))) {
                                    int[] intervalo = new int[2];

                                    intervalo[0] = Integer.parseInt(respuesta[i].getInicio().substring(11, 13));
                                    intervalo[1] = Integer.parseInt(respuesta[i].getFin().substring(11, 13));

                                    eliminarIntervaloReserva(intervalo);
                                }
                            }
                        }

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    } catch (ExecutionException e) {

                        e.printStackTrace();

                    }
                }
            }
        });

        // int[] testReserva = {17, 20};

        // eliminarIntervaloReserva(testReserva);

        spStart.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, horasStart));
        spStart.setVisibility(View.GONE);
        spStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //si la hora de fin es inferior a la de inicio(+1), se pone la misma(+1)
                if (spEnd.getSelectedItemPosition() < spStart.getSelectedItemPosition()) {
                    spEnd.setSelection(spStart.getSelectedItemPosition());
                }
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
                if (spEnd.getSelectedItemPosition() < spStart.getSelectedItemPosition()) {
                    spStart.setSelection(spEnd.getSelectedItemPosition());
                }
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
                cuadro.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String miPagina = "realizarReserva.php";

                        if (getActivity().getIntent().hasExtra("respuestaLogin")) {
                            String[] datosUsuario = getActivity().getIntent().getStringArrayExtra("respuestaLogin");
                            int codUsuario = Integer.parseInt(datosUsuario[3]);
                            String fechaInicio = fechaEscogida + " " + spStart.getSelectedItem().toString() + ":00";
                            String fechaFin = fechaEscogida + " " + spEnd.getSelectedItem().toString() + ":00";

                            Toast.makeText(getContext(), "--" + fechaInicio + "--", Toast.LENGTH_LONG).show();
                            String miWhere = "?cod_usuario=" + codUsuario + "&cod_sala=" + (spSalas.getSelectedItemId() + 1)
                                    + "&fecha_inicio=" + fechaInicio + "&fecha_fin=" + fechaFin;

                            try {

                                ConexionConsultaReservas miCon = new ConexionConsultaReservas();

                                Reserva[] respuesta = miCon.execute(myController.datosLlamada(miPagina, miWhere)).get();

                                if (respuesta[0] != null) {

                                }

                            } catch (InterruptedException e) {

                                e.printStackTrace();

                            } catch (ExecutionException e) {

                                e.printStackTrace();

                            }
                        }
                    }
                });
                cuadro.setNegativeButton(android.R.string.no, null);

                cuadro.show();
                break;
        }
    }
}
