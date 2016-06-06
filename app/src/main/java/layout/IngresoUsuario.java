package layout;

import android.support.v4.app.Fragment;
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
public class IngresoUsuario extends Fragment{


    private View fragmentIngresoUsuario;
    private FloatingActionButton floatingActionButton;
    OnFragmentInteractionListener mListener;

    public IngresoUsuario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentIngresoUsuario = inflater.inflate(R.layout.fragment_ingreso_usuario, container, false);
        floatingActionButton = (FloatingActionButton) fragmentIngresoUsuario.findViewById(R.id.fabContinuar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarSiguienteInterfaz();
            }
        });
        return fragmentIngresoUsuario;

    }

    /**
     * Metodo que permite mostrar la siguiente interfaz del usuario que es
     * un objeto tipo fragment
     */
    public void mostrarSiguienteInterfaz() {
//ya que el MainActivity implementa la INTERFAZ onFragmentInteraction, entonces podemos interactuar con ella
//        mediante el metodo de implementacion al cual le pasamos el layout de la siguiente interfaz como entero
        mListener.onFragmentInteraction(R.layout.fragment_registro_usuario);

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
