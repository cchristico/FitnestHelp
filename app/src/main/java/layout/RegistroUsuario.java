package layout;


import android.app.Activity;
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

import ec.edu.epn.doctorfit.MainActivity;
import ec.edu.epn.doctorfit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroUsuario extends Fragment {
//    declaramos un objeto de tipo INTERFAZ
    OnFragmentInteractionListener mListener;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int uri);
    }
}
