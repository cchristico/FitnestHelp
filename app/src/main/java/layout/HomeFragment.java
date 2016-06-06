package layout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import ec.edu.epn.doctorfit.R;
import ec.edu.epn.doctorfit.sqlite.db.Consejo;
import ec.edu.epn.doctorfit.sqlite.db.ConsejoDao;
import ec.edu.epn.doctorfit.sqlite.db.DaoMaster;
import ec.edu.epn.doctorfit.sqlite.db.DaoSession;
import ec.edu.epn.doctorfit.sqlite.db.UsuarioDao;


public class HomeFragment extends Fragment {

    //private OnFragmentInteractionListener mListener;
    // Objetos del ORM
    private String databaseName;
    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private ConsejoDao consejoDao;

    private View viewFragmentHome;
    private List consejoList;

    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        viewFragmentHome = inflater.inflate(R.layout.fragment_home, container, false);
        // Instanciando recursos de la bd
        databaseName = getResources().getString(R.string.database_name);
        //aqui se genera el esquema o se obtiene un objoSQLiteDatabase
        helper = new DaoMaster.DevOpenHelper(getActivity().getApplicationContext(), databaseName, null);
        //se habilita a la BDD para ser escrita o leida
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        consejoDao = daoSession.getConsejoDao();
        //crear la informacion, este metodo solo se realizará una vez para almacenar la informacion
        //generarConsejos();
        //llamar al matodo para traer los datos de la BDD
        obtenerConsejosBdd();
        return viewFragmentHome;
    }

    /**
     * Metodo que llena la tabla con informacion solo si esta vacia
     */
    /*
    public void generarConsejos() {
        consejoList = consejoDao.queryBuilder().list();

        if (consejoList.isEmpty()) {
            System.out.println("MENSAJE: No existen registros en la tabla Consejo ");
            //AQUI SE CREAN LOS CONSEJOS
            consejoDao.insertOrReplace(new Consejo((long) 1, "Necesitamos más de 40 nutrientes diferentes y ningún alimento por sí solo puede proporcionarlos todos. El suministro de alimentos que existe hoy en día facilita tomar una amplia variedad de alimentos, tanto comprando alimentos frescos para cocinar como comprando comidas preparadas o comida para llevar. ¡Elija los alimentos siempre de manera equilibrada! Si toma un almuerzo rico en grasa, tome una cena con poca grasa. Y si un día toma carne en la cena, intente escoger pescado al día siguiente."));
            consejoDao.insertOrReplace(new Consejo((long) 2, "La mayoría de la gente no toma suficientes alimentos ricos en hidratos de carbono como el pan, la pasta, el arroz, las patatas y otros cereales. Más de la mitad de las calorías de su dieta deben venir de estos alimentos. Pruebe con el pan integral, la pasta y otros cereales para aumentar su ingesta de fibra."));
            consejoDao.insertOrReplace(new Consejo((long) 3, "La mayor parte de la gente no toma la suficiente cantidad de estos alimentos que proporcionan importantes nutrientes protectores. Intente comer al menos cinco raciones al día. Pruebe nuevas recetas o vea qué platos preparados están disponibles en el supermercado."));
            consejoDao.insertOrReplace(new Consejo((long) 4, "El peso adecuado depende de muchos factores tales como el sexo, la altura, la edad y la genética. El sobrepeso aumenta el riesgo de padecer varias enfermedades tales como los problemas cardiacos y el cáncer. El exceso de grasa aparece al ingerir más calorías de las que se necesitan. Estas calorías suplementarias pueden provenir de cualquier nutriente que contenga calorías (las proteínas, las grasas, los hidratos de carbono o el alcohol) pero la grasa es la fuente más concentrada de calorías. La actividad física es un buen método para quemar calorías y puede hacerle sentirse bien. El mensaje es simple: si está ganando peso, tiene que comer menos y ser más activo."));
            consejoDao.insertOrReplace(new Consejo((long) 5, "Si ingiere las raciones adecuadas de cada alimento, es más fácil comer de todos los grupos de alimentos sin necesidad de eliminar ninguno. Por ejemplo, algunas raciones razonables son: 100g de carne, media pieza de fruta, media taza de pasta cruda o 50ml de helado. Las comidas preparadas pueden ofrecer un medio práctico para controlar las raciones y a menudo aparecen las calorías que contienen en el envase, lo cual ayuda a su recuento. Si come fuera, podría compartir parte de su comida con un amigo."));
            consejoDao.insertOrReplace(new Consejo((long) 6, "Saltarse las comidas, sobre todo el desayuno, puede conducir a una sensación de hambre descontrolada, causando a menudo una sobre ingesta. Realizar una media mañana o una merienda puede ayudar a contener el hambre, pero no coma demasiado para no sustituir las comidas principales. No olvide contar estas tomas como parte de su consumo total de calorías."));
            consejoDao.insertOrReplace(new Consejo((long) 7, "¡Los adultos necesitamos beber por lo menos 1,5 litros de líquidos al día! Y necesitamos más cantidad si hace calor o si realizamos mucho deporte. El agua es obviamente una buena fuente de líquidos pero la variedad puede ser tanto agradable como saludable. Otras opciones son los zumos, los refrescos, el té, el café, la leche, etc."));
            consejoDao.insertOrReplace(new Consejo((long) 8, "Como hemos visto, la ingesta de demasiadas calorías y no hacer suficiente ejercicio pueden dar lugar a un aumento de peso. La actividad física moderada ayuda a quemar las calorías que nos sobran. También es bueno para el corazón y para el sistema circulatorio, y para la salud en general y el bienestar. Así que haga de la actividad física una rutina diaria. ¡Use la escalera en vez del ascensor (tanto como para subir como para bajar)!. Dé un paseo en su descanso para comer. ¡No hace falta ser un atleta para moverse!"));
            consejoDao.insertOrReplace(new Consejo((long) 9, "Realizar los cambios de su estilo de vida gradualmente es mucho más fácil que hacerlos de repente. Durante tres días, anote los alimentos y bebidas que consume entre las comidas y en las comidas. ¿Toma muy pocas raciones de fruta y verdura? Para comenzar, trate de comer solamente una ración más de fruta y verdura al día. ¿Sus alimentos preferidos son ricos en grasa y le hacen ganar peso? No elimine estos alimentos y se sienta mal, en cambio intente escoger comidas bajas en grasas o comer menos cantidad de éstos. ¡Y comience a usar la escalera en el trabajo!"));
            consejoDao.insertOrReplace(new Consejo((long) 10, "No hay alimentos “buenos” o “malos”, sólo dietas buenas o malas. No se sienta culpable de los alimentos que le gustan, simplemente tómelos con moderación y escoja otros alimentos que le proporcionen el equilibrio y la variedad que necesita para conseguir una buena salud."));
        } else {
            Iterator<Consejo> iterador = consejoList.iterator();
            while (iterador.hasNext()) {
                Consejo consejo = new Consejo();
                consejo= iterador.next();
                System.out.println(consejo.getId()+ " : "+ consejo.getTextoInformativo());
            }
        }
    }
    */
    /**
     * Metodo que devuelve los datos de la tabla Consejo
     */

    public void obtenerConsejosBdd() {

    }

//    DESCOMENTAR ESTE CODIGO PARA INTERACTUAR CON EL MAINACTIVITY



/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
