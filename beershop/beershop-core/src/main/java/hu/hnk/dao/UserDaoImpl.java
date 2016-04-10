package hu.hnk.dao;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import hu.hnk.beershop.exception.EmailNotFound;
import hu.hnk.beershop.exception.UsernameNotFound;
import hu.hnk.beershop.model.Role;
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
	public User findByUsername(String username) throws UsernameNotFound {
		TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
		query.setParameter("name", username);
		User user;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			throw new UsernameNotFound("There is no user with this username.");
		}
		return user;
	}

	/**
	 * Felhaszn�l� keres�se e-mail c�m alapj�n.
	 * 
	 * @param email
	 *            a keresend� felhaszn�l� e-mail c�me.
	 * @return a megtal�lt felhaszn�l�
	 */
	@Override
	public User findByEmail(String email) throws EmailNotFound {
		TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}

	@Override
	public void remove(User user) {
		em.remove(em.contains(user) ? user : em.merge(user));
	}

	@Override
	public User findByRole(List<Role> roleName) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		cq.select(user).where(user.get("roles")).from(User.class).in(roleName);
		return (User) em.createQuery(cq).getResultList().get(0);
	}

	@Override
	public String findUsername(String username) throws UsernameNotFound {
		TypedQuery<String> query = em.createNamedQuery("User.findUsername", String.class);
		query.setParameter("name", username);
		String user;
		try {
			return user = query.getSingleResult();
		} catch (Exception e) {
			throw new UsernameNotFound("There is no user with this username.");
		}
	}

	@Override
	public String findEmail(String email) throws EmailNotFound {
		TypedQuery<String> query = em.createNamedQuery("User.findEmail", String.class);
		query.setParameter("email", email);
		String user;
		try {
			return user = query.getSingleResult();
		} catch (Exception e) {
			throw new EmailNotFound("There is no user with this email.");
		}
	}

}
