package com.example.rabanales21.rabanales21;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

public class Reservar extends AppCompatActivity implements View.OnClickListener {

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);

        final Button btnDate = (Button) findViewById(R.id.btnDate);
        btnDate.setOnClickListener(this);

        // TextView tvDate = (TextView) findViewById(R.id.tvDate);
        final TextView tvStart = (TextView) findViewById(R.id.tvStart);
        final TextView tvEnd = (TextView) findViewById(R.id.tvEnd);

        tvStart.setVisibility(View.GONE);
        tvEnd.setVisibility(View.GONE);

        Spinner spSalas = (Spinner) findViewById(R.id.spSalas);
        final Spinner spStart = (Spinner) findViewById(R.id.spStart);
        final Spinner spEnd = (Spinner) findViewById(R.id.spEnd);

        String[] salas = {"SALA CENTAURO GRANDE", "SALA CENTAURO PEQUEÑA", "SALA SILOS", "SALA DE FORMACION", "SALA ALDEBARAN"};
        final String[] horasStart = {"7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"};
        final String[] horasEnd = {"8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"};

        calendarView = (CalendarView) findViewById(R.id.calendarView);

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

        spSalas.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, salas));

        spStart.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, horasStart));
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


        spEnd.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, horasEnd));
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
        }
    }
}


