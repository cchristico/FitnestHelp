package ec.edu.epn.doctorfit.sqlite.db;

import ec.edu.epn.doctorfit.sqlite.db.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "ALIMENTACION_SEDENTARISMO".
 */
public class AlimentacionSedentarismo {

    private Long id;
    private float calorias;
    private float grasas;
    private float proteinas;
    private float porcentajeSedentarismo;
    private long idUsuario;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient AlimentacionSedentarismoDao myDao;

    private Usuario usuario;
    private Long usuario__resolvedKey;


    public AlimentacionSedentarismo() {
    }

    public AlimentacionSedentarismo(Long id) {
        this.id = id;
    }

    public AlimentacionSedentarismo(Long id, float calorias, float grasas, float proteinas, float porcentajeSedentarismo, long idUsuario) {
        this.id = id;
        this.calorias = calorias;
        this.grasas = grasas;
        this.proteinas = proteinas;
        this.porcentajeSedentarismo = porcentajeSedentarismo;
        this.idUsuario = idUsuario;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAlimentacionSedentarismoDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getCalorias() {
        return calorias;
    }

    public void setCalorias(float calorias) {
        this.calorias = calorias;
    }

    public float getGrasas() {
        return grasas;
    }

    public void setGrasas(float grasas) {
        this.grasas = grasas;
    }

    public float getProteinas() {
        return proteinas;
    }

    public void setProteinas(float proteinas) {
        this.proteinas = proteinas;
    }

    public float getPorcentajeSedentarismo() {
        return porcentajeSedentarismo;
    }

    public void setPorcentajeSedentarismo(float porcentajeSedentarismo) {
        this.porcentajeSedentarismo = porcentajeSedentarismo;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /** To-one relationship, resolved on first access. */
    public Usuario getUsuario() {
        long __key = this.idUsuario;
        if (usuario__resolvedKey == null || !usuario__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UsuarioDao targetDao = daoSession.getUsuarioDao();
            Usuario usuarioNew = targetDao.load(__key);
            synchronized (this) {
                usuario = usuarioNew;
            	usuario__resolvedKey = __key;
            }
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new DaoException("To-one property 'idUsuario' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.usuario = usuario;
            idUsuario = usuario.getId();
            usuario__resolvedKey = idUsuario;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
