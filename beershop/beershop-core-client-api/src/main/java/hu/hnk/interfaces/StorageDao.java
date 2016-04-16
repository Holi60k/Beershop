package hu.hnk.interfaces;

import java.util.List;

import hu.hnk.beershop.model.StorageItem;

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
	public List<StorageItem> findAll();
	
	public void saveAllChanges(List<StorageItem> storage);
}
