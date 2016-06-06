package layout;


import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import ec.edu.epn.doctorfit.MainActivity;
import ec.edu.epn.doctorfit.R;
import ec.edu.epn.doctorfit.sqlite.db.DaoMaster;
import ec.edu.epn.doctorfit.sqlite.db.DaoMaster.DevOpenHelper;
import ec.edu.epn.doctorfit.sqlite.db.DaoSession;
import ec.edu.epn.doctorfit.sqlite.db.Usuario;
import ec.edu.epn.doctorfit.sqlite.db.UsuarioDao;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroUsuario extends Fragment {


    // TODO Elementos de la vista

    //    declaramos un objeto de tipo INTERFAZ
    private OnFragmentInteractionListener mListener;
    private FloatingActionButton fabContinuar;

    private Activity actividadPadre;

    // Objetos del ORM
    private String databaseName;
    private DevOpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private UsuarioDao usuarioDao;

    public RegistroUsuario() {

    }

    public void onCreate(Bundle objeto) {
        this.onCreate(objeto);

        // Instanciando recursos de la bd
        databaseName = getResources().getString(R.string.database_name);
        helper = new DevOpenHelper(getActivity().getApplicationContext(), databaseName, null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        usuarioDao = daoSession.getUsuarioDao();

        // TODO Instanciando view
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Aniado un objeto Tipo vista en donde voy a almacenar un inflater
        // que contenfa el fragement, es la forma de comunicar a la clase con su fragment

        View viewFragmentRegistro = inflater.inflate(R.layout.fragment_registro_usuario, container, false);
        //  busco al objeto(fabContinuar) dentro del fragment(fragment_registro_usuario) a travez de la vista creada(viewFragmentRegistro)
        fabContinuar = (FloatingActionButton) viewFragmentRegistro.findViewById(R.id.fabContinuar);

        fabContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarSiguienteInterfaz();
            }
        });
        return viewFragmentRegistro;

    }

    /**
     * Metodo que permite mostrar la siguiente interfaz del usuario que es
     * un objeto tipo fragment
     */
    public void mostrarSiguienteInterfaz() {

        //  aqui obtenemos la actividad sobre la cual se esta ejecutando el fragment

        actividadPadre = getActivity();

        // TODO obtener los valores de la interfaz y establecerlos en el objeto usuario, o para mejorar
        // legibilidad mejor crear un metodo aparte, ustedes decidan
        /*
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario();
        nuevoUsuario.setEdad();
        nuevoUsuario.setEstatura();
        nuevoUsuario.setSexo();

        usuarioDao.insert(nuevoUsuario);

        //Verificar que se haya ingresado el usuario, solo por test, luego comentar
        //Consultar todos
        List<Usuario> listaUsuarios = usuarioDao.queryBuilder().list();
        System.out.println("OBTENER TODOS LOS USUARIOS");
        printList(listaUsuarios);
        */

        //  El Toast se ejecuta en una actividad, por tanto se debe establecer como contexto a la actividad padre del fragment
        Toast.makeText(actividadPadre, "se muestra la siguiente interfaz", Toast.LENGTH_SHORT).show();
        //EN LUGAR DEL TOAST AGREGAR EL CODIGO PARA MOSTRAR LA SIGUIENTE INTERFAZ


        //ya que el MainActivity implementa la INTERFAZ onFragmentInteraction, entonces podemos interactuar con ella
        //        mediante el metodo de implementacion al cual le pasamos el layout de la siguiente interfaz como entero
        mListener.onFragmentInteraction(R.layout.fragment_peso_actual_espectativa_usuario);

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
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     */
    /**
     * Modificacion personal para que resiva un entero correspondiente al fragment layout que se va
     * a pasar al ActivityMain
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int uri);
    }

    private void printList(List list){

        Iterator iter = list.iterator();
        while (iter.hasNext()){
            Usuario usuario = (Usuario)iter.next();
            System.out.println(usuario.getNombreUsuario() + " " + usuario.getSexo());
        }
    }
}
