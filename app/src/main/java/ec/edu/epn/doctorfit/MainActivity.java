package ec.edu.epn.doctorfit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import ec.edu.epn.doctorfit.sqlite.db.Alimento;
import ec.edu.epn.doctorfit.sqlite.db.AlimentoDao;
import ec.edu.epn.doctorfit.sqlite.db.Consejo;
import ec.edu.epn.doctorfit.sqlite.db.ConsejoDao;
import ec.edu.epn.doctorfit.sqlite.db.DaoMaster;
import ec.edu.epn.doctorfit.sqlite.db.DaoMaster.DevOpenHelper;
import ec.edu.epn.doctorfit.sqlite.db.DaoSession;
import ec.edu.epn.doctorfit.sqlite.db.Platillo;
import ec.edu.epn.doctorfit.sqlite.db.PlatilloDao;
import ec.edu.epn.doctorfit.sqlite.db.UsuarioDao;
import layout.ActividadFisicaDiaria;
import layout.EstadoUsuario;
import layout.HomeFragment;
import layout.ProgresoUsuario;
import layout.RegistroUsuario;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        RegistroUsuario.OnFragmentInteractionListener,
        ProgresoUsuario.OnFragmentInteractionListener {
    private int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;

    // Objetos del ORM
    private String databaseName;
    private DevOpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private PlatilloDao platilloDao;
    private AlimentoDao alimentoDao;
    private ConsejoDao consejoDao;

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
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_content_main_layout,
                    homeFragment,
                    homeFragment.getTag()).commit();
        } else if (id == R.id.nav_registro) {

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
     *
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

    /**
     * Se define la implementacion del constructor OnFragmentInteraccion de la inerfaz que MainAtivity Implementa
     *
     * @param uri  R.layout.fragmento, es el valor entero que corresponde a cada fragmento
     * @param anio el anio actual establecido en el fragmento
     * @param mes  el mes actual establecido en el fragmento
     * @param dia  el dia actual establecido en el fragmento
     */
    public void onFragmentInteraction(int uri, int anio, int mes, int dia) {

        if (uri == R.layout.fragment_registro_usuario) {
            this.year_x = anio;
            this.month_x = mes;
            this.day_x = dia;
            //creamos el Dialogo que contendra el DatePicker
            showDialog(0);

        }
    }

    /**
     * Sobreescritrua del metodo OnCreteDialog del objeto Dialog
     * que sera invocado mediante showDialog
     *
     * @param id el Id del dialogo
     * @return
     */
    @Override
    public Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            //retornamos un DatePickerDialog en el contexto MainActivity
            return new DatePickerDialog(this, dpDialogOnDataSetListener, year_x, month_x, day_x);
        return null;
    }


    /**
     * Se establece que va a hacer el DataPickerDialog cuando este sea invocado
     * en este caso vamos a mostrar un Toast con la fecha actual
     */
    DatePickerDialog.OnDateSetListener dpDialogOnDataSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    year_x = year;
                    month_x = monthOfYear + 1;
                    day_x = dayOfMonth;
                    //se establece estos valores en el texto del botton de escoger la fecha del usuario
                    Button buttonFechaNacimientoUsuario = (Button) findViewById(R.id.btnFechaNacimientoUsuario);
                    buttonFechaNacimientoUsuario.setText(year_x+" / "+month_x +" / "+day_x);
                }
            };


    private void generarDatosAplicacion(){

        List<Consejo> listaConsejos = consejoDao.queryBuilder().list();
        List<Alimento> listaAlimentos = alimentoDao.queryBuilder().list();
        List<Platillo> listaPlatillos = platilloDao.queryBuilder().list();

        if(listaConsejos.isEmpty() && listaAlimentos.isEmpty() && listaPlatillos.isEmpty()){

            
        }
    }
}
