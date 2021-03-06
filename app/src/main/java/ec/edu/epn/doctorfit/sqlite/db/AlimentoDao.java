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

import ec.edu.epn.doctorfit.sqlite.db.Alimento;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ALIMENTO".
*/
public class AlimentoDao extends AbstractDao<Alimento, Long> {

    public static final String TABLENAME = "ALIMENTO";

    /**
     * Properties of entity Alimento.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property NombreAlimento = new Property(1, String.class, "nombreAlimento", false, "NOMBRE_ALIMENTO");
        public final static Property AporteNutricional = new Property(2, String.class, "aporteNutricional", false, "APORTE_NUTRICIONAL");
        public final static Property PorcentajeNutricional = new Property(3, int.class, "porcentajeNutricional", false, "PORCENTAJE_NUTRICIONAL");
        public final static Property TipoAlimento = new Property(4, String.class, "tipoAlimento", false, "TIPO_ALIMENTO");
        public final static Property IdPlatillo = new Property(5, long.class, "idPlatillo", false, "ID_PLATILLO");
    };

    private DaoSession daoSession;

    private Query<Alimento> platillo_AlimentoListQuery;

    public AlimentoDao(DaoConfig config) {
        super(config);
    }
    
    public AlimentoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ALIMENTO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NOMBRE_ALIMENTO\" TEXT NOT NULL ," + // 1: nombreAlimento
                "\"APORTE_NUTRICIONAL\" TEXT," + // 2: aporteNutricional
                "\"PORCENTAJE_NUTRICIONAL\" INTEGER NOT NULL ," + // 3: porcentajeNutricional
                "\"TIPO_ALIMENTO\" TEXT," + // 4: tipoAlimento
                "\"ID_PLATILLO\" INTEGER NOT NULL );"); // 5: idPlatillo
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ALIMENTO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Alimento entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNombreAlimento());
 
        String aporteNutricional = entity.getAporteNutricional();
        if (aporteNutricional != null) {
            stmt.bindString(3, aporteNutricional);
        }
        stmt.bindLong(4, entity.getPorcentajeNutricional());
 
        String tipoAlimento = entity.getTipoAlimento();
        if (tipoAlimento != null) {
            stmt.bindString(5, tipoAlimento);
        }
        stmt.bindLong(6, entity.getIdPlatillo());
    }

    @Override
    protected void attachEntity(Alimento entity) {
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
    public Alimento readEntity(Cursor cursor, int offset) {
        Alimento entity = new Alimento( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // nombreAlimento
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // aporteNutricional
            cursor.getInt(offset + 3), // porcentajeNutricional
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // tipoAlimento
            cursor.getLong(offset + 5) // idPlatillo
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Alimento entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNombreAlimento(cursor.getString(offset + 1));
        entity.setAporteNutricional(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPorcentajeNutricional(cursor.getInt(offset + 3));
        entity.setTipoAlimento(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIdPlatillo(cursor.getLong(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Alimento entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Alimento entity) {
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
    
    /** Internal query to resolve the "alimentoList" to-many relationship of Platillo. */
    public List<Alimento> _queryPlatillo_AlimentoList(long idPlatillo) {
        synchronized (this) {
            if (platillo_AlimentoListQuery == null) {
                QueryBuilder<Alimento> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.IdPlatillo.eq(null));
                platillo_AlimentoListQuery = queryBuilder.build();
            }
        }
        Query<Alimento> query = platillo_AlimentoListQuery.forCurrentThread();
        query.setParameter(0, idPlatillo);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getUsuarioDao().getAllColumns());
            builder.append(" FROM ALIMENTO T");
            builder.append(" LEFT JOIN USUARIO T0 ON T.\"ID_PLATILLO\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Alimento loadCurrentDeep(Cursor cursor, boolean lock) {
        Alimento entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Usuario usuario = loadCurrentOther(daoSession.getUsuarioDao(), cursor, offset);
         if(usuario != null) {
            entity.setUsuario(usuario);
        }

        return entity;    
    }

    public Alimento loadDeep(Long key) {
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
    public List<Alimento> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Alimento> list = new ArrayList<Alimento>(count);
        
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
    
    protected List<Alimento> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Alimento> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
