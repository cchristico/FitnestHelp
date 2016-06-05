package ec.edu.epn.doctorfit;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ec.edu.epn.doctorfit.sqlite.db.AlimentacionSedentarismoDao;
import ec.edu.epn.doctorfit.sqlite.db.DaoMaster;
import ec.edu.epn.doctorfit.sqlite.db.DaoMaster.DevOpenHelper;
import ec.edu.epn.doctorfit.sqlite.db.DaoSession;
import ec.edu.epn.doctorfit.sqlite.db.Usuario;
import ec.edu.epn.doctorfit.sqlite.db.UsuarioDao;
import ec.edu.epn.doctorfit.sqlite.db.AlimentacionSedentarismo;

public class Nombre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre);

        ejemploManejoORM();
    }

    private void ejemploManejoORM() {

        // IMPORTANTE:
        // LEER ESTO, CONTIENE ALGUNAS FUNCIONES
        // http://www.javahispano.org/android/2012/5/9/greendao-un-orm-ligero-y-simple-para-android.html

        //Debe reemplazarse el "databaseName" por el nombre de la base de datos
        DevOpenHelper helper = new DevOpenHelper(getApplicationContext(), "ec.edu.epn.doctorfit.sqlite.db", null);

        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        //Se debe obtener una instancia del dao por medio del daoSession
        UsuarioDao usuarioDao = daoSession.getUsuarioDao();

        // Borrar LOS REGISTROS DE LA TABLA (POR CUESTIONES DE TEST UNICAMENTE)
        usuarioDao.deleteAll();

        // Instancia de Cliente
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("Pepito");
        usuario.setEdad(30);
        usuario.setSexo("H");
        usuario.setEstatura(1.84f);

        // guardar en la BD
        usuarioDao.insert(usuario);

        //Consultar por propiedad
        /*
        List<Usuario> listaUsuarios = usuarioDao.queryBuilder()
                                        .where(UsuarioDao.Properties.NombreUsuario.eq("Pepito"))
                                        .list();
        */

        //Consultar todos
        List<Usuario> listaUsuarios = usuarioDao.queryBuilder().list();
        System.out.println("OBTENER TODOS LOS USUARIOS");
        printList(listaUsuarios);

        //Actualizar
        Usuario usuarioActualizar = (Usuario)listaUsuarios.get(0);
        usuarioActualizar.setNombreUsuario("Maria");
        usuarioDao.update(usuarioActualizar);

        listaUsuarios = usuarioDao.queryBuilder().list();
        System.out.println("Consulta actualizacion");
        printList(listaUsuarios);

        /*
        //Obtener lista
        System.out.println("Lista");
        List<AlimentacionSedentarismo> librosClienteUno = listaUsuarios.get(0).getAlimentacionSedentarismoList();

        // se puede obtener en el otro sentido (este es el que deberia ser realmente)
        AlimentacionSedentarismoDao alimentacionSedentarismoDao = daoSession.getAlimentacionSedentarismoDao();

        AlimentacionSedentarismo itemAS = alimentacionSedentarismoDao.queryBuilder().list().get(0);
        // obtener el usuario asociado a ese registro
        itemAS.getUsuario();
        */

    }

    private void printList(List list){

        Iterator iter = list.iterator();
        while (iter.hasNext()){
            Usuario client = (Usuario)iter.next();
            System.out.println(client.getNombreUsuario() + " " + client.getSexo());
        }
    }
}
