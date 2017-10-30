package com.example.rabanales21.rabanales21;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class Salacentaurogrande extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_salacentaurogrande, container, false);

        View v = inflater.inflate(R.layout.fragment_salacentaurogrande, container, false);

        //Nuevos parametros para el view del fragmento
        RelativeLayout.LayoutParams params =    new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        //Nueva Regla: EL fragmento estara debajo del boton add_fragment
        params.addRule(RelativeLayout.BELOW, R.id.barratop);
        //Margenes
        params.setMargins(0,150,0,0);
        //Setear los parametros al view
        v.setLayoutParams(params);
        return v;
    }
}
