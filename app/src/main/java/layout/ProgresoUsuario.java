package layout;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ec.edu.epn.doctorfit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgresoUsuario extends Fragment {
private FloatingActionButton floatingActionButton;
OnFragmentInteractionListener mListener;
    public ProgresoUsuario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentPesoActualExpectativa = inflater.inflate(R.layout.fragment_peso_actual_espectativa_usuario,container,false);
        floatingActionButton = (FloatingActionButton) fragmentPesoActualExpectativa.findViewById(R.id.fabContinuar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarSiguienteInterfaz();
            }
        });
        return fragmentPesoActualExpectativa;

    }
    /**
     * Metodo que permite mostrar la siguiente interfaz del usuario que es
     * un objeto tipo fragment
     */
    public void mostrarSiguienteInterfaz() {
//ya que el MainActivity implementa la INTERFAZ onFragmentInteraction, entonces podemos interactuar con ella
//        mediante el metodo de implementacion al cual le pasamos el layout de la siguiente interfaz como entero
        mListener.onFragmentInteraction(R.layout.fragment_actividad_fisica_diaria);

    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int uri) {
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int uri);
    }
}
