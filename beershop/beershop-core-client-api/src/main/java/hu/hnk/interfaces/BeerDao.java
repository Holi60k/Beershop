package hu.hnk.interfaces;

import java.util.List;

import hu.hnk.beershop.model.Beer;

/**
 * @author Nandi
 *
 */
public interface BeerDao {
	/**
	 * Az �sszes s�r lek�rdez�se az adatb�zisb�l.
	 * 
	 * @return a s�r�k list�ja
	 */
	public List<Beer> findAll();
}
