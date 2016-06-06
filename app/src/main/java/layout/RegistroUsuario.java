package layout;


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

    protected FloatingActionButton fabContinuar;


    public RegistroUsuario() {

    }
/*
    public void onCreate(Bundle objeto) {
        this.onCreate(objeto);

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_usuario, container, false);

    }

}
