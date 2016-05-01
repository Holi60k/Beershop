package hu.hnk.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import hu.hnk.beershop.exception.EmailNotFound;
import hu.hnk.beershop.exception.UsernameNotFound;
import hu.hnk.beershop.model.User;
import hu.hnk.interfaces.UserDao;
import hu.hnk.persistenceunit.PersistenceUnitDeclaration;

/**
 * A felhaszn�l�kat kezel� adathozz�f�r�si oszt�ly implement�ci�ja. Enterprise
 * Java Bean
 * 
 * @author Nandi
 *
 */
@Stateless
@Local(UserDao.class)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	/**
	 * Az oszt�ly konstuktora.
	 */
	public UserDaoImpl() {
		super(User.class);
	}

	/**
	 * Az oszt�ly Logger-e.
	 */
	public static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	/**
	 * Az oszt�ly entit�s menedzsere.
	 */
	@PersistenceContext(unitName = PersistenceUnitDeclaration.PERSISTENCE_UNIT)
	private EntityManager em;

	/**
	 * {@inheritDoc}
	 */
	public User save(User user) {
		logger.info("Felhaszn�l� ment�se.");
		// return em.merge(user);
		em.persist(user);
		return user;
	}

	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public User findByEmail(String email) throws EmailNotFound {
		TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("email", email);
		User user;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			throw new EmailNotFound("There is no user with this e-mail.");
		}
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(User user) {
		em.remove(em.contains(user) ? user : em.merge(user));
	}

	// @Override
	// public User findByRole(List<Role> roleName) {
	// CriteriaBuilder cb = em.getCriteriaBuilder();
	// CriteriaQuery cq = cb.createQuery(User.class);
	// Root<User> user = cq.from(User.class);
	// cq.select(user).where(user.get("roles")).from(User.class).in(roleName);
	// return (User) em.createQuery(cq).getResultList().get(0);
	// }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String findUsername(String username) throws UsernameNotFound {
		TypedQuery<String> query = em.createNamedQuery("User.findUsername", String.class);
		query.setParameter("name", username);
		String user;
		try {
			user = query.getSingleResult();
			return user;
		} catch (Exception e) {
			throw new UsernameNotFound("There is no user with this username.");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String findEmail(String email) throws EmailNotFound {
		TypedQuery<String> query = em.createNamedQuery("User.findEmail", String.class);
		query.setParameter("email", email);
		String user;
		try {
			user = query.getSingleResult();
			return user;
		} catch (Exception e) {
			throw new EmailNotFound("There is no user with this email.");
		}
	}

}
