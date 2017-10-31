package com.example.rabanales21.rabanales21;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor,new Inicio()).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager2=getSupportFragmentManager();
        if (id == R.id.inicio) {
            Intent intent = new Intent(this, Reservar.class);
            startActivity(intent);
            fragmentManager2.beginTransaction().replace(R.id.contenedor,new Inicio()).commit();
        }else if (id == R.id.salas) {
            fragmentManager2.beginTransaction().replace(R.id.contenedor, new Muestra_salas()).commit();
        }else if (id == R.id.salacentgrande) {
            fragmentManager2.beginTransaction().replace(R.id.contenedor,new Salacentaurogrande()).commit();
        } else if (id == R.id.salacentpeque) {
            fragmentManager2.beginTransaction().replace(R.id.contenedor,new Salacentauropeque()).commit();
        } else if (id == R.id.salasilos) {
            fragmentManager2.beginTransaction().replace(R.id.contenedor,new Salasilos()).commit();
        } else if (id == R.id.salaformacion) {
            fragmentManager2.beginTransaction().replace(R.id.contenedor,new Salaformacion()).commit();
        } else if (id == R.id.salaaldebaran) {
            fragmentManager2.beginTransaction().replace(R.id.contenedor,new SalaAldebaran()).commit();
        } else if (id == R.id.cambiarpass) {
            fragmentManager2.beginTransaction().replace(R.id.contenedor,new Cambiarpass()).commit();
        } else if (id == R.id.cerrarsesion) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
