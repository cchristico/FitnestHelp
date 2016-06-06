package ec.edu.epn.doctorfit;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import layout.ActividadFisicaDiaria;
import layout.EstadoUsuario;
import layout.ProgresoUsuario;
import layout.RegistroUsuario;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RegistroUsuario.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_inicio) {
            Toast.makeText(this, "INICIO", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_registro) {
            Toast.makeText(this, "REGISTRO", Toast.LENGTH_SHORT).show();
            RegistroUsuario registroUsuario = new RegistroUsuario();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_content_main_layout,
                    registroUsuario,
                    registroUsuario.getTag()
            ).commit();

        } else if (id == R.id.nav_progreso) {
            Toast.makeText(this, "PROGRESO", Toast.LENGTH_SHORT).show();
            ProgresoUsuario progresoUsuario = new ProgresoUsuario();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_content_main_layout,
                    progresoUsuario,
                    progresoUsuario.getTag()
            ).commit();

        } else if (id == R.id.nav_estado_usuario) {
            EstadoUsuario estadoUsuario = new EstadoUsuario();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_content_main_layout,
                    estadoUsuario,
                    estadoUsuario.getTag()).commit();
            Toast.makeText(this, "RECORDATORIOS", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Implementacion del metodo de la interfaz onFragmentInteraction creda en cada fragmentLayout para
     * la interaccion entre el MainActivity y los fragmentLayout
     * @param uri entero que corresponde al layout que se requiere abrir, permite la interaccion entre layouts
     */
    @Override
    public void onFragmentInteraction(int uri) {
        if (uri == R.layout.fragment_actividad_fisica_diaria) {
            ActividadFisicaDiaria actividadFisicaDiaria = new ActividadFisicaDiaria();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_content_main_layout,
                    actividadFisicaDiaria,
                    actividadFisicaDiaria.getTag()).commit();
        } else if (uri == R.layout.fragment_peso_actual_espectativa_usuario) {
            ProgresoUsuario progresoUsuario = new ProgresoUsuario();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_content_main_layout,
                    progresoUsuario,
                    progresoUsuario.getTag()).commit();
        }

    }
}
