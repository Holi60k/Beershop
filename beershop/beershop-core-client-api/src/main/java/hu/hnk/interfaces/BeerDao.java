package hu.hnk.interfaces;

import java.util.List;

import hu.hnk.beershop.model.Beer;


/**
 * @author Nandi
 *
 */
public interface BeerDao extends BaseDao<Beer> {
	/**
	 * Az összes sör lekérdezése az adatbázisból.
	 * 
	 * @return a sörök listája
	 */
	public List<Beer> findAll() throws Exception;
}
