package layout;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.media.TransportPerformer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ec.edu.epn.doctorfit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActividadFisicaDiaria extends Fragment {

    private FloatingActionButton floatingActionButton;

    public ActividadFisicaDiaria() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentActividadFisica = inflater.inflate(R.layout.fragment_actividad_fisica_diaria, container, false);
        floatingActionButton = (FloatingActionButton) fragmentActividadFisica.findViewById(R.id.fabContinuar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return fragmentActividadFisica;
    }

    public void mostrarSiguienteInterfaz() {
        Activity activity = new Activity();
        Toast.makeText(activity, "siguiente Interfaz", Toast.LENGTH_SHORT).show();
    }
}
