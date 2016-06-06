package layout;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import ec.edu.epn.doctorfit.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DietaActual.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class DietaActual extends Fragment {

    private Button buttonComidaChatarra;
    private Button buttonDietaBalanceada;
    private View fragmentDietaActual;
    private OnFragmentInteractionListener mListener;

    public DietaActual() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        fragmentDietaActual = inflater.inflate(R.layout.fragment_dieta_actual, container, false);

        doAnActionOnClickButton();
        return fragmentDietaActual;

    }

    public void doAnActionOnClickButton() {
        /**
         * Esta es la  accion que se realiza cuando el FloatingActionButton del fragment_diesta_actual es presionado
         */
        FloatingActionButton fabSiguienteInterfaz = (FloatingActionButton) fragmentDietaActual.findViewById(R.id.fabContinuarDieta);
        fabSiguienteInterfaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarSiguienteInterfaz();
            }
        });

        /**
         * Esta es la  accion que se realiza cuando el Button btnPocaActividad del fragment_actividad_fisica_diaria es presionado
         */
        buttonComidaChatarra = (Button) fragmentDietaActual.findViewById(R.id.btnComidaChatarra);
        buttonComidaChatarra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonComidaChatarra.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_checked_genre, 0);
                buttonDietaBalanceada.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

//                despues de enviar esta informacion se debe settear la variable checked_genre a 0
            }
        });

        /**
         * Esta es la  accion que se realiza cuando el Button btnPocaActividad del fragment_actividad_fisica_diaria es presionado
         */
        buttonDietaBalanceada = (Button) fragmentDietaActual.findViewById(R.id.btnDietaBalanceada);
        buttonDietaBalanceada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonDietaBalanceada.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_checked_genre, 0);
                buttonComidaChatarra.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

//                despues de enviar esta informacion se debe settear la variable checked_genre a 0
            }
        });



}

    public void mostrarSiguienteInterfaz() {
        Activity activity = getActivity();
        mListener.onFragmentInteraction(R.layout.fragment_estado_usuario);
        Toast.makeText(activity, "INFORMACION ALMACENADA", Toast.LENGTH_SHORT).show();
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int uri);
    }
}
