package layout;


import android.app.Activity;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class ActividadFisicaDiaria extends Fragment {

    private FloatingActionButton floatingActionButton;
    private Button buttonNoMuyActivo, buttonMedianamenteActivo, buttonActivo, buttonMuyActivo;
    private View fragmentActividadFisica;

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

    public void mostrarSiguienteInterfaz() {
        Activity activity = new Activity();
        Toast.makeText(activity, "siguiente Interfaz", Toast.LENGTH_SHORT).show();
    }

    public void doAnActionOnClickButton() {
        /**
         * Esta es la  accion que se realiza cuando el FloatingActionButton del fragment_actividad_fisica_diaria es presionado
         */
        floatingActionButton = (FloatingActionButton) fragmentActividadFisica.findViewById(R.id.fabContinuar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /**
         * Esta es la  accion que se realiza cuando el Button btnPocaActividad del fragment_actividad_fisica_diaria es presionado
         */
        buttonNoMuyActivo = (Button) fragmentActividadFisica.findViewById(R.id.btnNoMuyActivo);
        buttonNoMuyActivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNoMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_checked_genre,0);
                buttonMedianamenteActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                buttonActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                buttonMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
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
                buttonMedianamenteActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_checked_genre,0);
                buttonNoMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                buttonActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                buttonMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
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
                buttonActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_checked_genre,0);
                buttonMedianamenteActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                buttonNoMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                buttonMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
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
                buttonMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_checked_genre,0);
                buttonMedianamenteActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                buttonActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                buttonNoMuyActivo.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
//                despues de enviar esta informacion se debe settear la variable checked_genre a 0
            }
        });

    }
}
