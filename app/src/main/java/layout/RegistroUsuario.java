package layout;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ec.edu.epn.doctorfit.MainActivity;
import ec.edu.epn.doctorfit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroUsuario extends Fragment {

    private FloatingActionButton fabContinuar;
    private Activity actividadPadre;


    public RegistroUsuario() {

    }
/*
    public void onCreate(Bundle objeto) {
        this.onCreate(objeto);

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Aniado un objeto Tipo vista en donde voy a almacenar un inflater
// que contenfa el fragement, es la forma de comunicar a la clase con su fragment
        View viewFragmentRegistro = inflater.inflate(R.layout.fragment_registro_usuario, container, false);
//        busco al objeto(fabContinuar) dentro del fragment(fragment_registro_usuario) a travez de la vista creada(viewFragmentRegistro)
        fabContinuar = (FloatingActionButton) viewFragmentRegistro.findViewById(R.id.fabContinuar);
        fabContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarSiguienteInterfaz();
            }
        });
        return viewFragmentRegistro;

    }

    public void mostrarSiguienteInterfaz() {
//        aqui obtenemos la actividad sobre la cual se esta ejecutando el fragment

        actividadPadre = getActivity();
//        El Toast se ejecuta en una actividad, por tanto se debe establecer como contexto a la actividad padre del fragment
        Toast.makeText(actividadPadre, "se muestra la siguiente interfaz", Toast.LENGTH_SHORT).show();
        //EN LUGAR DEL TOAST AGREGAR EL CODIGO PARA MOSTRAR LA SIGUIENTE INTERFAZ
    }
}
