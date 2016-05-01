/**
 * 
 */
package hu.hnk.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import hu.hnk.beershop.model.Beer;
import hu.hnk.beershop.model.StorageItem;
import hu.hnk.interfaces.StorageDao;

/**
 * @author Nandi
 *
 */
@Stateless
@Local(StorageDao.class)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class StorageDaoImpl extends BaseDaoImpl<StorageItem> implements StorageDao {
	
	/**
	 * Az oszt�ly konstuktora.
	 */
	public StorageDaoImpl() {
		super(StorageItem.class);
	}

	/**
	 * Az oszt�ly loggere.
	 */
	public static final Logger logger = Logger.getLogger(StorageDaoImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StorageItem> findAll() {
		Query query = entityManager.createQuery("SELECT s FROM StorageItem s");
		return query.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveAllChanges(List<StorageItem> storage) {
		storage.stream()
				.forEach(entity -> {
					try {
						save(entity);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.hnk.interfaces.StorageDao#findByBeer(hu.hnk.beershop.model.Beer)
	 */
	@Override
	public StorageItem findByBeer(Beer beer) {
		return entityManager.createNamedQuery("StorageItem.findByBeer", StorageItem.class)
				.setParameter("beer", beer)
				.getSingleResult();
	}

}
