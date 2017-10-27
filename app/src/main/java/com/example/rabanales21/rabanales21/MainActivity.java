package com.example.rabanales21.rabanales21;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton=(ImageButton) findViewById(R.id.btnlogin);
        imageButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,MenuActivity.class);
        startActivity(intent);
    }
}
