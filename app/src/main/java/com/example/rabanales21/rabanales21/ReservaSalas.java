package com.example.rabanales21.rabanales21;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

/**
 * Gestiona la seleccion de datos y realizacion de la reserva de salas </p>
 */

public class ReservaSalas extends Fragment implements View.OnClickListener {

    Boolean principal=false;
    private CalendarView calendarView;
    private ArrayList<String> horasStart = new ArrayList<>();
    private ArrayList<String> horasEnd = new ArrayList<>();
    private FuncionesGenerales myController = new FuncionesGenerales();
    private String diaEscogido;
    int numeroSala = 0;
    Spinner spSalas;
    Spinner spStart;
    Spinner spEnd;
    Button btnDate;
    String fechaEscogida;
    TextView tvStart;
    TextView tvEnd;
    TextView tvProyector;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reserva_salas, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        btnDate = (Button) (getActivity().findViewById(R.id.btnDate));
        btnDate.setOnClickListener(this);
        final Button btnReservar = (Button) (getActivity().findViewById(R.id.btnReservar));
        btnReservar.setOnClickListener(this);

        btnReservar.setEnabled(false);

        tvStart = (TextView) (getActivity().findViewById(R.id.tvStart));
        tvEnd = (TextView) (getActivity().findViewById(R.id.tvEnd));
        tvProyector = (TextView) (getActivity().findViewById(R.id.tvProyector));
        tvProyector.setVisibility(View.GONE);

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
                btnReservar.setVisibility(View.GONE);
                tvProyector.setVisibility(View.GONE);
                btnDate.setText("FECHA");
                calendarView.setDate(System.currentTimeMillis());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        for (int i=7; i<22; i++) {horasStart.add(i+":00");}
        for (int i=8; i<23; i++) {horasEnd.add(i+":00");}

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

                    String miWhere = "?cod_usuario=" + codUsuario + "&cod_sala=" + (spSalas.getSelectedItemId() + 1);

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

                    } catch (IllegalStateException e) {
                        Toast.makeText(getContext(), ""+e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                if (horasStart.isEmpty()) {
                    tvStart.setText("NO HAY HORAS DISPONIBLES");
                    tvStart.setVisibility(View.VISIBLE);
                    tvEnd.setVisibility(View.GONE);
                    btnReservar.setVisibility(View.GONE);
                    spStart.setVisibility(View.GONE);
                    spEnd.setVisibility(View.GONE);
                } else  {
                    tvStart.setText("HORA INICIO");
                    tvStart.setVisibility(View.VISIBLE);
                    tvEnd.setVisibility(View.VISIBLE);
                    btnReservar.setVisibility(View.VISIBLE);
                    btnReservar.setEnabled(true);
                    spStart.setVisibility(View.VISIBLE);
                    spEnd.setVisibility(View.VISIBLE);
                    spStart.setSelection(0);
                    spEnd.setSelection(0);
                    spStart.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, horasStart));
                    spEnd.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, horasEnd));
                    if (spSalas.getSelectedItemId() == 1 || spSalas.getSelectedItemId() == 4) {
                        tvProyector.setVisibility(View.VISIBLE);
                    } else {
                        tvProyector.setVisibility(View.GONE);
                    }
                }
            }
        });

        if (!horasStart.isEmpty()) {
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
        ((MenuActivity)getActivity()).setBoleano(false);
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
                    tvStart.setVisibility(View.GONE);
                    spStart.setVisibility(View.GONE);
                    tvEnd.setVisibility(View.GONE);
                    spEnd.setVisibility(View.GONE);
                } else {
                    calendarView.setVisibility(View.GONE);
                    if(!btnDate.getText().toString().equals("FECHA")){
                        tvStart.setVisibility(View.VISIBLE);
                        spStart.setVisibility(View.VISIBLE);
                        tvEnd.setVisibility(View.VISIBLE);
                        spEnd.setVisibility(View.VISIBLE);
                    }
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
                            String fechaInicio = fechaEscogida + "%20" + spStart.getSelectedItem().toString() + ":00";
                            String fechaFin = fechaEscogida + "%20" + spEnd.getSelectedItem().toString() + ":00";

                            String miWhere = "?cod_usuario=" + codUsuario + "&cod_sala=" + (spSalas.getSelectedItemId() + 1)
                                    + "&fecha_inicio=" + fechaInicio + "&fecha_fin=" + fechaFin;

                            try {

                                ConexionReservar miCon = new ConexionReservar();

                                String respuesta = miCon.execute(myController.datosLlamada(miPagina, miWhere)).get();

                                if (respuesta.equals("false")) {
                                    Toast.makeText(getContext(), getString(R.string.falloReserva), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), getString(R.string.exitoReserva), Toast.LENGTH_SHORT).show();
                                    FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.contenedor1,new Inicio()).commit();
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
