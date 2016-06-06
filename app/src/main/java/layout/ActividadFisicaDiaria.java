package layout;


import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.media.TransportPerformer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import ec.edu.epn.doctorfit.R;
import ec.edu.epn.doctorfit.sqlite.db.ConsejoDao;
import ec.edu.epn.doctorfit.sqlite.db.DaoMaster;
import ec.edu.epn.doctorfit.sqlite.db.DaoSession;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActividadFisicaDiaria extends Fragment {
    private OnFragmentInteractionListener mListener;
    private FloatingActionButton floatingActionButton;
    private Button buttonNoMuyActivo, buttonMedianamenteActivo, buttonActivo, buttonMuyActivo;
    private View fragmentActividadFisica;


    private String databaseName;
    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
//    private ConsejoDao consejoDao;
    public ActividadFisicaDiaria() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentActividadFisica = inflater.inflate(R.layout.fragment_actividad_fisica_diaria, container, false);

        doAnActionOnClickButton();


        return fragmentActividadFisica;
    }

    public void doAnActionOnClickButton() {
        /**
         * Esta es la  accion que se realiza cuando el FloatingActionButton del fragment_actividad_fisica_diaria es presionado
         */
        floatingActionButton = (FloatingActionButton) fragmentActividadFisica.findViewById(R.id.fabContinuar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarInformacionActividaFisicaDiaria();
                //llamar a la siguiente interfaz

                mostrarSiguienteInterfaz();

//                Activity activity = getActivity();
            }
        });

        /**
         * Esta es la  accion que se realiza cuando el Button btnPocaActividad del fragment_actividad_fisica_diaria es presionado
         */
        buttonNoMuyActivo = (Button) fragmentActividadFisica.findViewById(R.id.btnNoMuyActivo);
        buttonNoMuyActivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNoMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_checked_genre, 0);
                buttonMedianamenteActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                buttonActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                buttonMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                despues de enviar esta informacion se debe settear la variable checked_genre a 0
            }
        });

        /**
         * Esta es la  accion que se realiza cuando el Button btnPocaActividad del fragment_actividad_fisica_diaria es presionado
         */
        buttonMedianamenteActivo = (Button) fragmentActividadFisica.findViewById(R.id.btnMedianamenteActivo);
        buttonMedianamenteActivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonMedianamenteActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_checked_genre, 0);
                buttonNoMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                buttonActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                buttonMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                despues de enviar esta informacion se debe settear la variable checked_genre a 0
            }
        });

        /**
         * Esta es la  accion que se realiza cuando el Button btnPocaActividad del fragment_actividad_fisica_diaria es presionado
         */
        buttonActivo = (Button) fragmentActividadFisica.findViewById(R.id.btnActivo);
        buttonActivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_checked_genre, 0);
                buttonMedianamenteActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                buttonNoMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                buttonMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                despues de enviar esta informacion se debe settear la variable checked_genre a 0
            }
        });

        /**
         * Esta es la  accion que se realiza cuando el Button btnPocaActividad del fragment_actividad_fisica_diaria es presionado
         */
        buttonMuyActivo = (Button) fragmentActividadFisica.findViewById(R.id.btnMuyActivo);
        buttonMuyActivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_checked_genre, 0);
                buttonMedianamenteActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                buttonActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                buttonNoMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                despues de enviar esta informacion se debe settear la variable checked_genre a 0
            }
        });

    }

    private void insertarInformacionActividaFisicaDiaria() {

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

        //este metodo permite pasar los valores del anio actual al MainActivity
        void onFragmentInteraction(int uri, int anio, int mes, int dia);
    }

    public void mostrarSiguienteInterfaz() {

        mListener.onFragmentInteraction(R.layout.fragment_dieta_actual);

    }
    public void mostrarInterfazAnterior() {

        mListener.onFragmentInteraction(R.layout.fragment_peso_actual_espectativa_usuario);

    }

}
