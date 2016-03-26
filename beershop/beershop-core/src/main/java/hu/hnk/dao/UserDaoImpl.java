package hu.hnk.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import hu.hnk.beershop.model.User;
import hu.hnk.interfaces.UserDao;
/**
 * A felhaszn�l�kat kezel� adathozz�f�r�si oszt�ly implement�ci�ja.
 * Enterprise Java Bean
 * @author Nandi
 *
 */
@Stateless
@Local(UserDao.class)
public class UserDaoImpl implements UserDao {
	
	public static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	@PersistenceContext(unitName = "BeerShopUnit")
	EntityManager em;

	public User save(User user) {
		logger.info("Felhaszn�l� ment�se.");
		return em.merge(user);
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
