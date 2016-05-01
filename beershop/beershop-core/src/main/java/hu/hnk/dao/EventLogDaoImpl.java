package hu.hnk.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;

import hu.hnk.beershop.model.EventLog;
import hu.hnk.beershop.model.User;
import hu.hnk.interfaces.EventLogDao;

/**
 * Az esem�nyeket kezel� adathozz�f�r�si oszt�ly implement�ci�ja.
 * 
 * @author Nandi
 *
 */
@Stateless
@Local(EventLogDao.class)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EventLogDaoImpl extends BaseDaoImpl<EventLog> implements EventLogDao {

	/**
	 * Az oszt�ly konstuktora.
	 */
	public EventLogDaoImpl() {
		super(EventLog.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EventLog> findByUser(User user) {
		TypedQuery<EventLog> query = entityManager.createNamedQuery("EventLog.findByUser", EventLog.class);
		query.setParameter("user", user);
		return query.getResultList();
	}

}
