package hu.hnk.beershop.service.interfaces;

import java.util.List;

import hu.hnk.beershop.exception.NegativeCountNumber;
import hu.hnk.beershop.model.Storage;

/**
 * @author Nandi
 *
 */
public interface StorageService {
	
	/**
	 * A rakt�r inform�ci�it lek�rdez� met�dus.
	 * 
	 * @return a rakt�r inform�ci�i.
	 */
	public List<Storage> findAll();
	
	public void saveAllChanges(List<Storage> storage) throws NegativeCountNumber;
}
