package com.example.rabanales21.rabanales21;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import static com.example.rabanales21.rabanales21.R.id.contenedor1;
import static com.example.rabanales21.rabanales21.R.id.viewPager;

public class MenuActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor1,new Inicio()).commit();


        TabLayout tabLayout = (TabLayout) findViewById(R.id.wtabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Inicio"));
        tabLayout.addTab(tabLayout.newTab().setText("Reservar"));
        tabLayout.addTab(tabLayout.newTab().setText("Consultar"));
        tabLayout.addTab(tabLayout.newTab().setText("Ajustes"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment retorno = null;
                switch (tab.getPosition()){
                    case 0:
                        retorno = new Inicio();
                    break;
                    case 1:
                        retorno= new Muestra_salas();
                        break;
                    case 2:
                        retorno= new Consultar();
                        break;
                    case 3:
                        retorno= new Cambiarpass();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.contenedor1, retorno).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                fragmentManager.beginTransaction().replace(R.id.contenedor1, new Inicio()).commit();

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Fragment retorno = null;
                switch (tab.getPosition()){
                    case 0:
                        retorno = new Inicio();
                        break;
                    case 1:
                        retorno= new Muestra_salas();
                        break;
                    case 2:
                        retorno= new Consultar();
                        break;
                    case 3:
                        retorno= new Cambiarpass();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.contenedor1, retorno).commit();
            }


        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Si el usuario es admin, la barra sera visible
        navigationView.getMenu().setGroupVisible(R.id.grupoadmin, false);
        if(getIntent().hasExtra("respuestaLogin")) {
            String[] tipousuario = getIntent().getStringArrayExtra("respuestaLogin");

            if (tipousuario[2].equals("1")) {
                navigationView.getMenu().setGroupVisible(R.id.grupoadmin, true);
            }
        }
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager2=getSupportFragmentManager();
        ReservaSalas fragment = new ReservaSalas();
        Consultar fragment2 = new Consultar();

        Bundle arguments = new Bundle();
        if (id == R.id.inicio) {
            fragmentManager2.beginTransaction().replace(R.id.contenedor1,new Inicio()).commit();
        }else if (id == R.id.salas) {
            fragmentManager2.beginTransaction().replace(R.id.contenedor1, new Muestra_salas()).addToBackStack(null).commit();
        }else if (id == R.id.salacentgrande) {

            arguments.putInt( "sala" , 0);
            fragment.setArguments(arguments);
            fragmentManager2.beginTransaction().replace(R.id.contenedor1,fragment).addToBackStack(null).commit();
        } else if (id == R.id.salacentpeque) {
            arguments.putInt( "sala" , 1);
            fragment.setArguments(arguments);
            fragmentManager2.beginTransaction().replace(R.id.contenedor1,fragment).addToBackStack(null).commit();
        } else if (id == R.id.salasilos) {
            arguments.putInt( "sala" , 2);
            fragment.setArguments(arguments);
            fragmentManager2.beginTransaction().replace(R.id.contenedor1,fragment).addToBackStack(null).commit();
        } else if (id == R.id.salaformacion) {
            arguments.putInt( "sala" , 3);
            fragment.setArguments(arguments);
            fragmentManager2.beginTransaction().replace(R.id.contenedor1,fragment).addToBackStack(null).commit();
        } else if (id == R.id.salaaldebaran) {
            arguments.putInt( "sala" , 4);
            fragment.setArguments(arguments);
            fragmentManager2.beginTransaction().replace(R.id.contenedor1,fragment).addToBackStack(null).commit();
        } else if (id == R.id.gestionreservas) {
            arguments.putString("reservas" , "todas");
            fragment2.setArguments(arguments);
            fragmentManager2.beginTransaction().replace(R.id.contenedor1,fragment2).addToBackStack(null).commit();
        } else if (id == R.id.gestionempresas) {
            fragmentManager2.beginTransaction().replace(R.id.contenedor1,new Gestionempresa()).addToBackStack(null).commit();
        } else if (id == R.id.cambiarpass) {
            fragmentManager2.beginTransaction().replace(R.id.contenedor1,new Cambiarpass()).addToBackStack(null).commit();
        } else if (id == R.id.cerrarsesion) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.closeConf))
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                    finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
