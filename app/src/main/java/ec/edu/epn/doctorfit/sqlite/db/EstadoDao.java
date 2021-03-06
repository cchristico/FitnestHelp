package ec.edu.epn.doctorfit.sqlite.db;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import ec.edu.epn.doctorfit.sqlite.db.Estado;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ESTADO".
*/
public class EstadoDao extends AbstractDao<Estado, Long> {

    public static final String TABLENAME = "ESTADO";

    /**
     * Properties of entity Estado.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Peso = new Property(1, float.class, "peso", false, "PESO");
        public final static Property LongitudAbdominal = new Property(2, float.class, "longitudAbdominal", false, "LONGITUD_ABDOMINAL");
        public final static Property LongitudCadera = new Property(3, float.class, "longitudCadera", false, "LONGITUD_CADERA");
        public final static Property FechaEstado = new Property(4, java.util.Date.class, "fechaEstado", false, "FECHA_ESTADO");
        public final static Property IdUsuario = new Property(5, long.class, "idUsuario", false, "ID_USUARIO");
    };

    private DaoSession daoSession;

    private Query<Estado> usuario_EstadoListQuery;

    public EstadoDao(DaoConfig config) {
        super(config);
    }
    
    public EstadoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ESTADO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PESO\" REAL NOT NULL ," + // 1: peso
                "\"LONGITUD_ABDOMINAL\" REAL NOT NULL ," + // 2: longitudAbdominal
                "\"LONGITUD_CADERA\" REAL NOT NULL ," + // 3: longitudCadera
                "\"FECHA_ESTADO\" INTEGER NOT NULL ," + // 4: fechaEstado
                "\"ID_USUARIO\" INTEGER NOT NULL );"); // 5: idUsuario
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ESTADO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Estado entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindDouble(2, entity.getPeso());
        stmt.bindDouble(3, entity.getLongitudAbdominal());
        stmt.bindDouble(4, entity.getLongitudCadera());
        stmt.bindLong(5, entity.getFechaEstado().getTime());
        stmt.bindLong(6, entity.getIdUsuario());
    }

    @Override
    protected void attachEntity(Estado entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Estado readEntity(Cursor cursor, int offset) {
        Estado entity = new Estado( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getFloat(offset + 1), // peso
            cursor.getFloat(offset + 2), // longitudAbdominal
            cursor.getFloat(offset + 3), // longitudCadera
            new java.util.Date(cursor.getLong(offset + 4)), // fechaEstado
            cursor.getLong(offset + 5) // idUsuario
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Estado entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPeso(cursor.getFloat(offset + 1));
        entity.setLongitudAbdominal(cursor.getFloat(offset + 2));
        entity.setLongitudCadera(cursor.getFloat(offset + 3));
        entity.setFechaEstado(new java.util.Date(cursor.getLong(offset + 4)));
        entity.setIdUsuario(cursor.getLong(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Estado entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Estado entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "estadoList" to-many relationship of Usuario. */
    public List<Estado> _queryUsuario_EstadoList(long idUsuario) {
        synchronized (this) {
            if (usuario_EstadoListQuery == null) {
                QueryBuilder<Estado> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.IdUsuario.eq(null));
                usuario_EstadoListQuery = queryBuilder.build();
            }
        }
        Query<Estado> query = usuario_EstadoListQuery.forCurrentThread();
        query.setParameter(0, idUsuario);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getUsuarioDao().getAllColumns());
            builder.append(" FROM ESTADO T");
            builder.append(" LEFT JOIN USUARIO T0 ON T.\"ID_USUARIO\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Estado loadCurrentDeep(Cursor cursor, boolean lock) {
        Estado entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Usuario usuario = loadCurrentOther(daoSession.getUsuarioDao(), cursor, offset);
         if(usuario != null) {
            entity.setUsuario(usuario);
        }

        return entity;    
    }

    public Estado loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Estado> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Estado> list = new ArrayList<Estado>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Estado> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Estado> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
