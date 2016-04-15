/**
 * 
 */
package hu.hnk.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hu.hnk.beershop.model.Storage;
import hu.hnk.interfaces.StorageDao;

/**
 * @author Nandi
 *
 */
@Stateless
@Local(StorageDao.class)
public class StorageDaoImpl implements StorageDao {

	/**
	 * JPA Entity Manager.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * A rakt�r inform�ci�it lek�rdez� met�dus.
	 * 
	 * @return a rakt�r inform�ci�i.
	 */
	@Override
	public List<Storage> findAll() {
		Query query = em.createQuery("SELECT s FROM Storage s");
		return query.getResultList();
	}

}
