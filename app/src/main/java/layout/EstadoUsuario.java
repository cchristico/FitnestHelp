package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ec.edu.epn.doctorfit.R;


public class EstadoUsuario extends Fragment {

private Button buttonComidaChatarra;
private Button buttonDietaBalanceada;
    private View fragmentEstadoActual;
    public EstadoUsuario() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        fragmentEstadoActual=inflater.inflate(R.layout.fragment_estado_usuario, container, false);
        // Inflate the layout for this fragment
        return fragmentEstadoActual;
    }



}
