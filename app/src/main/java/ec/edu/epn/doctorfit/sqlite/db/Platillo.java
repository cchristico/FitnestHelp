package ec.edu.epn.doctorfit.sqlite.db;

import java.util.List;
import ec.edu.epn.doctorfit.sqlite.db.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "PLATILLO".
 */
public class Platillo {

    private Long id;
    /** Not-null value. */
    private String nombrePlatillo;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient PlatilloDao myDao;

    private List<Alimento> alimentoList;

    public Platillo() {
    }

    public Platillo(Long id) {
        this.id = id;
    }

    public Platillo(Long id, String nombrePlatillo) {
        this.id = id;
        this.nombrePlatillo = nombrePlatillo;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlatilloDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Alimento> getAlimentoList() {
        if (alimentoList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AlimentoDao targetDao = daoSession.getAlimentoDao();
            List<Alimento> alimentoListNew = targetDao._queryPlatillo_AlimentoList(id);
            synchronized (this) {
                if(alimentoList == null) {
                    alimentoList = alimentoListNew;
                }
            }
        }
        return alimentoList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetAlimentoList() {
        alimentoList = null;
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
