package hu.hnk.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hnk.beershop.model.EventLog;
import hu.hnk.beershop.model.User;
import hu.hnk.beershop.service.interfaces.EventLogService;
import hu.hnk.interfaces.EventLogDao;
import hu.hnk.service.cobertura.annotation.CoverageIgnore;
import hu.hnk.service.factory.EventLogFactory;

/**
 * Az eseményeket kezelő szolgáltatás.
 * 
 * A szolgáltatás segítséget nyújt egy már előre elkészített esemény mentésére
 * (amit a {@link EventLogFactory} osztállyal készíthetünk el), illetve az egyes
 * felhasználókhoz tartozó eseményeket tudjuk lekérdezni.
 * 
 * @author Nandi
 *
 */
@Stateless
@Local(EventLogService.class)
public class EventLogServiceImpl implements EventLogService {

	/**
	 * Az osztály loggere.
	 */
	public static final Logger logger = LoggerFactory.getLogger(EventLogServiceImpl.class);

	/**
	 * Az eseményeket kezelő adathozzáférési objektum.
	 */
	@EJB
	private EventLogDao eventLogDao;

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	@CoverageIgnore
	public List<EventLog> findByUser(User user) throws Exception {
		return eventLogDao.findByUser(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@CoverageIgnore
	public EventLog save(EventLog event) {
		try {
			return eventLogDao.save(event);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return event;
	}

}
