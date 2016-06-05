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

import ec.edu.epn.doctorfit.sqlite.db.EstadoDeseado;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ESTADO_DESEADO".
*/
public class EstadoDeseadoDao extends AbstractDao<EstadoDeseado, Long> {

    public static final String TABLENAME = "ESTADO_DESEADO";

    /**
     * Properties of entity EstadoDeseado.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property EstadoFuturo = new Property(1, String.class, "estadoFuturo", false, "ESTADO_FUTURO");
        public final static Property IdUsuario = new Property(2, long.class, "idUsuario", false, "ID_USUARIO");
    };

    private DaoSession daoSession;

    private Query<EstadoDeseado> usuario_EstadoDeseadoListQuery;

    public EstadoDeseadoDao(DaoConfig config) {
        super(config);
    }
    
    public EstadoDeseadoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ESTADO_DESEADO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ESTADO_FUTURO\" TEXT NOT NULL ," + // 1: estadoFuturo
                "\"ID_USUARIO\" INTEGER NOT NULL );"); // 2: idUsuario
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ESTADO_DESEADO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, EstadoDeseado entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getEstadoFuturo());
        stmt.bindLong(3, entity.getIdUsuario());
    }

    @Override
    protected void attachEntity(EstadoDeseado entity) {
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
    public EstadoDeseado readEntity(Cursor cursor, int offset) {
        EstadoDeseado entity = new EstadoDeseado( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // estadoFuturo
            cursor.getLong(offset + 2) // idUsuario
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, EstadoDeseado entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setEstadoFuturo(cursor.getString(offset + 1));
        entity.setIdUsuario(cursor.getLong(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(EstadoDeseado entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(EstadoDeseado entity) {
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
    
    /** Internal query to resolve the "estadoDeseadoList" to-many relationship of Usuario. */
    public List<EstadoDeseado> _queryUsuario_EstadoDeseadoList(long idUsuario) {
        synchronized (this) {
            if (usuario_EstadoDeseadoListQuery == null) {
                QueryBuilder<EstadoDeseado> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.IdUsuario.eq(null));
                usuario_EstadoDeseadoListQuery = queryBuilder.build();
            }
        }
        Query<EstadoDeseado> query = usuario_EstadoDeseadoListQuery.forCurrentThread();
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
            builder.append(" FROM ESTADO_DESEADO T");
            builder.append(" LEFT JOIN USUARIO T0 ON T.\"ID_USUARIO\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected EstadoDeseado loadCurrentDeep(Cursor cursor, boolean lock) {
        EstadoDeseado entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Usuario usuario = loadCurrentOther(daoSession.getUsuarioDao(), cursor, offset);
         if(usuario != null) {
            entity.setUsuario(usuario);
        }

        return entity;    
    }

    public EstadoDeseado loadDeep(Long key) {
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
    public List<EstadoDeseado> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<EstadoDeseado> list = new ArrayList<EstadoDeseado>(count);
        
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
    
    protected List<EstadoDeseado> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<EstadoDeseado> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}