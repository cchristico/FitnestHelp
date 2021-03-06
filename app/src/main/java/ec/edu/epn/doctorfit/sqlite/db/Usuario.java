package ec.edu.epn.doctorfit.sqlite.db;

import java.util.List;
import ec.edu.epn.doctorfit.sqlite.db.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "USUARIO".
 */
public class Usuario {

    private Long id;
    /** Not-null value. */
    private String nombreUsuario;
    private int edad;
    /** Not-null value. */
    private String sexo;
    private float estatura;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient UsuarioDao myDao;

    private List<Estado> estadoList;
    private List<EstadoDeseado> estadoDeseadoList;
    private List<AlimentacionSedentarismo> alimentacionSedentarismoList;

    public Usuario() {
    }

    public Usuario(Long id) {
        this.id = id;
    }

    public Usuario(Long id, String nombreUsuario, int edad, String sexo, float estatura) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.edad = edad;
        this.sexo = sexo;
        this.estatura = estatura;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUsuarioDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    /** Not-null value. */
    public String getSexo() {
        return sexo;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Estado> getEstadoList() {
        if (estadoList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EstadoDao targetDao = daoSession.getEstadoDao();
            List<Estado> estadoListNew = targetDao._queryUsuario_EstadoList(id);
            synchronized (this) {
                if(estadoList == null) {
                    estadoList = estadoListNew;
                }
            }
        }
        return estadoList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetEstadoList() {
        estadoList = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<EstadoDeseado> getEstadoDeseadoList() {
        if (estadoDeseadoList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EstadoDeseadoDao targetDao = daoSession.getEstadoDeseadoDao();
            List<EstadoDeseado> estadoDeseadoListNew = targetDao._queryUsuario_EstadoDeseadoList(id);
            synchronized (this) {
                if(estadoDeseadoList == null) {
                    estadoDeseadoList = estadoDeseadoListNew;
                }
            }
        }
        return estadoDeseadoList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetEstadoDeseadoList() {
        estadoDeseadoList = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<AlimentacionSedentarismo> getAlimentacionSedentarismoList() {
        if (alimentacionSedentarismoList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AlimentacionSedentarismoDao targetDao = daoSession.getAlimentacionSedentarismoDao();
            List<AlimentacionSedentarismo> alimentacionSedentarismoListNew = targetDao._queryUsuario_AlimentacionSedentarismoList(id);
            synchronized (this) {
                if(alimentacionSedentarismoList == null) {
                    alimentacionSedentarismoList = alimentacionSedentarismoListNew;
                }
            }
        }
        return alimentacionSedentarismoList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetAlimentacionSedentarismoList() {
        alimentacionSedentarismoList = null;
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
