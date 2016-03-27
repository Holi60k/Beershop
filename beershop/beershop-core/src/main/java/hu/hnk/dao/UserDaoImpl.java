package hu.hnk.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import hu.hnk.beershop.model.User;
import hu.hnk.interfaces.UserDao;

/**
 * A felhaszn�l�kat kezel� adathozz�f�r�si oszt�ly implement�ci�ja. Enterprise
 * Java Bean
 * 
 * @author Nandi
 *
 */
@Stateless
@Local(UserDao.class)
public class UserDaoImpl implements UserDao {
	/**
	 * Az oszt�ly Logger-e.
	 */
	public static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	/**
	 * Az oszt�ly entit�s menedzsere.
	 */
	@PersistenceContext
	EntityManager em;

	/**
	 * �j felhaszn�l� ment�se.
	 * 
	 * @param user
	 *            az �j felhaszn�l�
	 * @return a mentett felhaszn�l�
	 */
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

	/**
	 * Felhaszn�l� keres�se felhaszn�l�n�v alapj�n.
	 * 
	 * @param username
	 *            a keresend� felhaszn�l� felhaszn�l�neve.
	 * @return a megtal�lt felhaszn�l�
	 */
	@Override
	public User findByUsername(String username) {
		TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
		query.setParameter("name", username);
		return query.getSingleResult();
	}

	/**
	 * Felhaszn�l� keres�se e-mail c�m alapj�n.
	 * 
	 * @param email
	 *            a keresend� felhaszn�l� e-mail c�me.
	 * @return a megtal�lt felhaszn�l�
	 */
	@Override
	public User findByEmail(String email) {
		TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}
	
	@Override
	public void remove(User user) {
		em.remove(em.contains(user) ? user : em.merge(user));	
	}

}
