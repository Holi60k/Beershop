package hu.hnk.interfaces;

import java.util.List;

import hu.hnk.beershop.model.Storage;

/**
 * @author Nandi
 *
 */
public interface StorageDao {
	
	/**
	 * A rakt�r inform�ci�it lek�rdez� met�dus.
	 * 
	 * @return a rakt�r inform�ci�i.
	 */
	public List<Storage> findAll();
}
