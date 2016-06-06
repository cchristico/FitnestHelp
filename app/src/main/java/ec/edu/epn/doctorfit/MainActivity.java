package ec.edu.epn.doctorfit;

import android.app.Activity;
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
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import layout.DietaActual;
import layout.EstadoUsuario;
import layout.HomeFragment;
import layout.IngresoUsuario;
import layout.ProgresoUsuario;
import layout.RegistroUsuario;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        RegistroUsuario.OnFragmentInteractionListener,
        ProgresoUsuario.OnFragmentInteractionListener,
        ActividadFisicaDiaria.OnFragmentInteractionListener,
        DietaActual.OnFragmentInteractionListener,
        IngresoUsuario.OnFragmentInteractionListener {
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
    private UsuarioDao usuarioDao;
    private EditText txtPesoActual;
    private EditText txtPesoDeseado;
    private int objetivoDeseado;
    private Button btnObjetivodeado;
    private Activity actividad;

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

        databaseName = getResources().getString(R.string.database_name);
        //aqui se genera el esquema o se obtiene un objoSQLiteDatabase
        helper = new DaoMaster.DevOpenHelper(this.getApplicationContext(), databaseName, null);
        //se habilita a la BDD para ser escrita o leida
 //       db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        consejoDao = daoSession.getConsejoDao();
        alimentoDao = daoSession.getAlimentoDao();
        platilloDao = daoSession.getPlatilloDao();
        usuarioDao = daoSession.getUsuarioDao();
        // Verificar si existen los datos en la aplicacion, sino generarlos
        //usuarioDao.deleteAll();
        generarDatosAplicacion();
        if (!existeUsuario()) {
            IngresoUsuario ingresoUsuario = new IngresoUsuario();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_content_main_layout,
                    ingresoUsuario,
                    ingresoUsuario.getTag()
            ).commit();
        } else {
            Toast.makeText(this, "YA ESTAS REGISTRADO", Toast.LENGTH_SHORT).show();

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
            if (!existeUsuario()) {
                RegistroUsuario registroUsuario = new RegistroUsuario();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(
                        R.id.fragment_content_main_layout,
                        registroUsuario,
                        registroUsuario.getTag()
                ).commit();
            } else {
                Toast.makeText(this, "YA ESTAS REGISTRADO", Toast.LENGTH_SHORT).show();

            }
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
            Toast.makeText(this, "ESTADO", Toast.LENGTH_SHORT).show();
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
        } else if (uri == R.layout.fragment_dieta_actual) {
            DietaActual dietaActual = new DietaActual();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_content_main_layout,
                    dietaActual,
                    dietaActual.getTag()).commit();
        } else if (uri == R.layout.fragment_registro_usuario) {
            RegistroUsuario registroUsuario = new RegistroUsuario();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_content_main_layout,
                    registroUsuario,
                    registroUsuario.getTag()).commit();
        }else if (uri == R.layout.fragment_estado_usuario) {
            EstadoUsuario estadoUsuario = new EstadoUsuario();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_content_main_layout,
                    estadoUsuario,
                    estadoUsuario.getTag()).commit();
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
//                    Button buttonFechaNacimientoUsuario = (Button) findViewById(R.id.btnFechaNacimientoUsuario);
//                    buttonFechaNacimientoUsuario.setText(year_x+" / "+month_x +" / "+day_x);
                }
            };

    /**
     * Metodo para generar algunos datos de la aplicacion
     */
    private void generarDatosAplicacion() {

        List<Consejo> listaConsejos = consejoDao.queryBuilder().list();
        List<Alimento> listaAlimentos = alimentoDao.queryBuilder().list();
        List<Platillo> listaPlatillos = platilloDao.queryBuilder().list();

        if (listaConsejos.isEmpty() && listaAlimentos.isEmpty() && listaPlatillos.isEmpty()) {

            // generar datos

            // CONSEJO
            consejoDao.insert(new Consejo((long) 1, "Necesitamos más de 40 nutrientes diferentes y ningún alimento por sí solo puede proporcionarlos todos. El suministro de alimentos que existe hoy en día facilita tomar una amplia variedad de alimentos, tanto comprando alimentos frescos para cocinar como comprando comidas preparadas o comida para llevar. ¡Elija los alimentos siempre de manera equilibrada! Si toma un almuerzo rico en grasa, tome una cena con poca grasa. Y si un día toma carne en la cena, intente escoger pescado al día siguiente."));
            consejoDao.insert(new Consejo((long) 2, "La mayoría de la gente no toma suficientes alimentos ricos en hidratos de carbono como el pan, la pasta, el arroz, las patatas y otros cereales. Más de la mitad de las calorías de su dieta deben venir de estos alimentos. Pruebe con el pan integral, la pasta y otros cereales para aumentar su ingesta de fibra."));
            consejoDao.insert(new Consejo((long) 3, "La mayor parte de la gente no toma la suficiente cantidad de estos alimentos que proporcionan importantes nutrientes protectores. Intente comer al menos cinco raciones al día. Pruebe nuevas recetas o vea qué platos preparados están disponibles en el supermercado."));
            consejoDao.insert(new Consejo((long) 4, "El peso adecuado depende de muchos factores tales como el sexo, la altura, la edad y la genética. El sobrepeso aumenta el riesgo de padecer varias enfermedades tales como los problemas cardiacos y el cáncer. El exceso de grasa aparece al ingerir más calorías de las que se necesitan. Estas calorías suplementarias pueden provenir de cualquier nutriente que contenga calorías (las proteínas, las grasas, los hidratos de carbono o el alcohol) pero la grasa es la fuente más concentrada de calorías. La actividad física es un buen método para quemar calorías y puede hacerle sentirse bien. El mensaje es simple: si está ganando peso, tiene que comer menos y ser más activo."));
            consejoDao.insert(new Consejo((long) 5, "Si ingiere las raciones adecuadas de cada alimento, es más fácil comer de todos los grupos de alimentos sin necesidad de eliminar ninguno. Por ejemplo, algunas raciones razonables son: 100g de carne, media pieza de fruta, media taza de pasta cruda o 50ml de helado. Las comidas preparadas pueden ofrecer un medio práctico para controlar las raciones y a menudo aparecen las calorías que contienen en el envase, lo cual ayuda a su recuento. Si come fuera, podría compartir parte de su comida con un amigo."));
            consejoDao.insert(new Consejo((long) 6, "Saltarse las comidas, sobre todo el desayuno, puede conducir a una sensación de hambre descontrolada, causando a menudo una sobre ingesta. Realizar una media mañana o una merienda puede ayudar a contener el hambre, pero no coma demasiado para no sustituir las comidas principales. No olvide contar estas tomas como parte de su consumo total de calorías."));
            consejoDao.insert(new Consejo((long) 7, "¡Los adultos necesitamos beber por lo menos 1,5 litros de líquidos al día! Y necesitamos más cantidad si hace calor o si realizamos mucho deporte. El agua es obviamente una buena fuente de líquidos pero la variedad puede ser tanto agradable como saludable. Otras opciones son los zumos, los refrescos, el té, el café, la leche, etc."));
            consejoDao.insert(new Consejo((long) 8, "Como hemos visto, la ingesta de demasiadas calorías y no hacer suficiente ejercicio pueden dar lugar a un aumento de peso. La actividad física moderada ayuda a quemar las calorías que nos sobran. También es bueno para el corazón y para el sistema circulatorio, y para la salud en general y el bienestar. Así que haga de la actividad física una rutina diaria. ¡Use la escalera en vez del ascensor (tanto como para subir como para bajar)!. Dé un paseo en su descanso para comer. ¡No hace falta ser un atleta para moverse!"));
            consejoDao.insert(new Consejo((long) 9, "Realizar los cambios de su estilo de vida gradualmente es mucho más fácil que hacerlos de repente. Durante tres días, anote los alimentos y bebidas que consume entre las comidas y en las comidas. ¿Toma muy pocas raciones de fruta y verdura? Para comenzar, trate de comer solamente una ración más de fruta y verdura al día. ¿Sus alimentos preferidos son ricos en grasa y le hacen ganar peso? No elimine estos alimentos y se sienta mal, en cambio intente escoger comidas bajas en grasas o comer menos cantidad de éstos. ¡Y comience a usar la escalera en el trabajo!"));
            consejoDao.insert(new Consejo((long) 10, "No hay alimentos “buenos” o “malos”, sólo dietas buenas o malas. No se sienta culpable de los alimentos que le gustan, simplemente tómelos con moderación y escoja otros alimentos que le proporcionen el equilibrio y la variedad que necesita para conseguir una buena salud."));

            // PLATILLO
            platilloDao.insert(new Platillo((long) 1, "Consomé"));
            platilloDao.insert(new Platillo((long) 2, "Patatas rellenas con salmón"));
            platilloDao.insert(new Platillo((long) 3, "Ensalada mixta con atún"));
            platilloDao.insert(new Platillo((long) 4, "Macarrones con tomate"));
            platilloDao.insert(new Platillo((long) 5, "Empanada de carne"));

            // ALIMENTO
            alimentoDao.insert(new Alimento((long) 1, "Pollo", null, 130, null, (long) 1));
            alimentoDao.insert(new Alimento((long) 2, "Zanahoria", null, 37, null, (long) 1));
            alimentoDao.insert(new Alimento((long) 3, "Pollo", null, 24, null, (long) 1));

            alimentoDao.insert(new Alimento((long) 4, "Papa", null, 90, null, (long) 2));
            alimentoDao.insert(new Alimento((long) 5, "Salmón", null, 172, null, (long) 2));

            alimentoDao.insert(new Alimento((long) 6, "Atún", null, 144, null, (long) 3));
            alimentoDao.insert(new Alimento((long) 7, "Tomate", null, 22, null, (long) 3));
            alimentoDao.insert(new Alimento((long) 8, "lechuga", null, 18, null, (long) 3));
            alimentoDao.insert(new Alimento((long) 9, "Choclo", null, 96, null, (long) 3));

            alimentoDao.insert(new Alimento((long) 10, "Macarrones", null, 107, null, (long) 4));
            alimentoDao.insert(new Alimento((long) 11, "Salsa de Tomate", null, 86, null, (long) 4));

            alimentoDao.insert(new Alimento((long) 12, "Carne de res", null, 92, null, (long) 5));
            alimentoDao.insert(new Alimento((long) 13, "Arveja", null, 81, null, (long) 5));
            alimentoDao.insert(new Alimento((long) 13, "Harina", null, 364, null, (long) 5));
        }
    }

    public boolean existeUsuario() {
        List usuarioList = usuarioDao.queryBuilder().list();
        if (usuarioList.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public void ValidadPesoDeseado()
    {
        double pesoActual;
        double pesoDeseado;
        pesoActual = Double.parseDouble (txtPesoActual.findViewById(R.id.txtPesoActual).toString());
        pesoDeseado = Double.parseDouble (txtPesoDeseado.findViewById(R.id.txtPesoIdeal).toString());
        actividad = new Activity();
        switch (objetivoDeseado)
        {
            case 1:
                if (pesoDeseado>pesoActual)
                {

                    Toast.makeText(actividad,"Usted Selecciono perder peso",Toast.LENGTH_SHORT);
                }
                break;
            case 2:
                if(pesoDeseado<pesoActual)
                {
                    Toast.makeText(actividad,"Usted Selecciono ganar peso",Toast.LENGTH_SHORT);
                }
                break;
            case 3:
                if(pesoActual!=pesoDeseado)
                {

                    Toast.makeText(actividad,"Usted Selecciono mantener peso",Toast.LENGTH_SHORT);
                }
                break;
        }
    }

    public void ObjetivoDeseado(View view){

        Button btnTipoDieta = (Button)view;
        if(btnTipoDieta.getText().equals("Pierde peso de forma sana \n reduciendo calorías"))
        {
            objetivoDeseado = 1;
        }
        else if(btnTipoDieta.getText().equals("Mantén tu peso \n disfrutando de una dieta sana"))
        {
            objetivoDeseado = 2;
        }
        else if(btnTipoDieta.getText().equals("Gana peso de forma sana \n ganando masa muscular"))
        {
            objetivoDeseado = 3;
        }
    }



}