package com.example.rabanales21.rabanales21;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


public class Inicio extends Fragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageButton reservar = (ImageButton) (getActivity().findViewById(R.id.btnreservar));
        reservar.setOnClickListener(this);
        ImageButton consultar = (ImageButton) (getActivity().findViewById(R.id.btnconsultar));
        consultar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnreservar:
                FragmentManager fragmentManager3=getActivity().getSupportFragmentManager();
                fragmentManager3.beginTransaction().replace(R.id.contenedor,new ReservaSalas());
                Toast.makeText(getContext(),"asd",Toast.LENGTH_LONG).show();

                break;
            case R.id.btnconsultar:

                break;
        }
    }
}
