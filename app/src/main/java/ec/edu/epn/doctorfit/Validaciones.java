package ec.edu.epn.doctorfit;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Christian on 6/6/2016.
 */
public class Validaciones {
    private EditText txtPesoActual;
    private EditText txtPesoDeseado;
    private int objetivoDeseado;
    private Button btnObjetivodeado;
    private Activity actividad;

    public void ValidadPesoDeseado()
    {
        double pesoActual;
        double pesoDeseado;
        pesoActual = Double.parseDouble (txtPesoActual.findViewById(R.id.txtPesoActual).toString());
        pesoDeseado = Double.parseDouble (txtPesoDeseado.findViewById(R.id.txtPesoIdeal).toString());
        actividad = new Activity();
        switch (objetivoDeseado)
        {

            case 1:


                if (pesoDeseado>pesoActual)
                {

                    Toast.makeText(actividad,"Usted Selecciono perder peso",Toast.LENGTH_SHORT);
                }
                break;

            case 2:
                if(pesoDeseado<pesoActual)
                {

                    Toast.makeText(actividad,"Usted Selecciono ganar peso",Toast.LENGTH_SHORT);
                }

                break;
            case 3:

                if(pesoActual!=pesoDeseado)
                {

                    Toast.makeText(actividad,"Usted Selecciono mantener peso",Toast.LENGTH_SHORT);
                }

                break;

        }


    }

public void ObjetivoDeseado(View view){

    Button btnTipoDieta = (Button)view;
    if(btnTipoDieta.getText().equals("Pierde peso de forma sana \n reduciendo calorías"))
    {
        objetivoDeseado = 1;
    }
    else if(btnTipoDieta.getText().equals("Mantén tu peso \n disfrutando de una dieta sana"))
    {
        objetivoDeseado = 2;
    }
    else if(btnTipoDieta.getText().equals("Gana peso de forma sana \n ganando masa muscular"))
    {
        objetivoDeseado = 3;
    }
}


}
