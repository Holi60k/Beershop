package hu.hnk.interfaces;

import java.util.List;

import hu.hnk.beershop.model.EventLog;
import hu.hnk.beershop.model.User;

/**
 * Az esem�nyeket kezel� adathozz�f�r�si oszt�ly interf�sze.
 * @author Nandi
 *
 */
public interface EventLogDao {
	
	/**
	 * Esem�nylista keres�se felhaszn�l� alapj�n.
	 * @param user a keresend� esem�nyek felhaszn�l�ja.
	 * @return a keresett felhaszn�l� esem�nylist�ja.
	 */
	public List<EventLog> findByUser(User user);
	
	/**
	 * Esem�ny ment�se.
	 * @param event a mentend� esem�ny.
	 * @return a mentett esem�ny.
	 */
	public EventLog save(EventLog event);
	
}
