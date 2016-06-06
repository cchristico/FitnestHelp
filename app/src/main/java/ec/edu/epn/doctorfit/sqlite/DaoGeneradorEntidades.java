package ec.edu.epn.doctorfit.sqlite;

/**
 * Created by jonathan on 5/6/16.
 */
import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
/**
 * Created by jonathan on 4/6/16.
 */
public class DaoGeneradorEntidades {

    public static void main(String[] args) throws Exception {
        //En este ejemplo hay un unico esquema de base de datos si hubiera mas deberian agregarse
        Schema schema = new Schema(1, "ec.edu.epn.doctorfit.sqlite.db");

        //Este metodo se encargara de crear la estructura de las tablas y las relaciones entre ellas
        addTables(schema);

        //Se encargara de generar el codigo correspondiente en la ruta que se le especifico
        DaoGenerator daoGenerator = new DaoGenerator();
        daoGenerator.generateAll(schema, "./app/src/main/java");
    }
    private static void addTables(Schema schema) {

        // TABLA USUARIO
        Entity usuario = schema.addEntity("Usuario");
        usuario.addIdProperty().primaryKey().autoincrement();
        usuario.addStringProperty("nombreUsuario").notNull();
        usuario.addIntProperty("edad").notNull();
        usuario.addStringProperty("sexo").notNull();
        usuario.addFloatProperty("estatura").notNull();

        // TABLA ESTADO
        Entity estado = schema.addEntity("Estado");
        estado.addIdProperty().primaryKey().autoincrement();
        estado.addFloatProperty("peso").notNull();
        estado.addFloatProperty("longitudAbdominal").notNull();
        estado.addFloatProperty("longitudCadera").notNull();
        estado.addDateProperty("fechaEstado").notNull();

        // TABLA ESTADODESEADO
        Entity estadoDeseado = schema.addEntity("EstadoDeseado");
        estadoDeseado.addIdProperty().primaryKey().autoincrement();
        estadoDeseado.addStringProperty("estadoFuturo").notNull();

        // TABLA ALIMENTACIONSEDENTARISMO
        Entity alimentacionSedentarismo = schema.addEntity("AlimentacionSedentarismo");
        alimentacionSedentarismo.addIdProperty().primaryKey().autoincrement();
        alimentacionSedentarismo.addFloatProperty("calorias").notNull();
        alimentacionSedentarismo.addFloatProperty("grasas").notNull();
        alimentacionSedentarismo.addFloatProperty("proteinas").notNull();
        alimentacionSedentarismo.addFloatProperty("porcentajeSedentarismo").notNull();

        // TABLA CONSEJO
        Entity consejo = schema.addEntity("Consejo");
        consejo.addIdProperty().primaryKey().autoincrement();
        consejo.addStringProperty("textoInformativo").notNull();

        //TABLA DIETA
        //Entity dieta = schema.addEntity("Dieta");
        //dieta.addIdProperty().primaryKey().autoincrement();
        //dieta.addStringProperty("tipoDieta").notNull();

        // TABLA PLATILLO
        Entity platillo = schema.addEntity("Platillo");
        platillo.addIdProperty().primaryKey().autoincrement();
        platillo.addStringProperty("nombrePlatillo").notNull();

        // TABLA ALIMENTO
        Entity alimento = schema.addEntity("Alimento");
        alimento.addIdProperty().primaryKey().autoincrement();
        alimento.addStringProperty("nombreAlimento").notNull();
        alimento.addStringProperty("aporteNutricional").notNull();
        alimento.addFloatProperty("porcentajeNutricional").notNull();
        alimento.addStringProperty("tipoAlimento").notNull();

        // RELACIONES ENTRE TABLAS
        Property idUsuarioEstado = estado.addLongProperty("idUsuario").notNull().getProperty();
        usuario.addToMany(estado, idUsuarioEstado);
        estado.addToOne(usuario,idUsuarioEstado);

        Property idUsuarioEstadoDeseado = estadoDeseado.addLongProperty("idUsuario").notNull().getProperty();
        usuario.addToMany(estadoDeseado, idUsuarioEstadoDeseado);
        estadoDeseado.addToOne(usuario,idUsuarioEstadoDeseado);

        Property idUsuarioAlimentacionSedentarismo = alimentacionSedentarismo.addLongProperty("idUsuario").notNull().getProperty();
        usuario.addToMany(alimentacionSedentarismo, idUsuarioAlimentacionSedentarismo);
        alimentacionSedentarismo.addToOne(usuario,idUsuarioAlimentacionSedentarismo);

        /*
        Property idEstadoDeseado = dieta.addLongProperty("idEstadoDeseado").notNull().getProperty();
        estadoDeseado.addToMany(dieta, idEstadoDeseado);
        dieta.addToOne(estadoDeseado,idEstadoDeseado);

        Property idDieta = platillo.addLongProperty("idDieta").notNull().getProperty();
        dieta.addToMany(platillo, idDieta);
        platillo.addToOne(dieta,idDieta);
        */

        Property idPlatillo = alimento.addLongProperty("idPlatillo").notNull().getProperty();
        platillo.addToMany(alimento, idPlatillo);
        alimento.addToOne(usuario,idPlatillo);

    }
}