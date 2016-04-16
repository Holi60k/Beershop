package hu.hnk.beershop.service.interfaces;

import java.util.List;

import hu.hnk.beershop.exception.NegativeQuantityNumber;
import hu.hnk.beershop.exception.StorageItemQuantityExceeded;
import hu.hnk.beershop.model.Beer;
import hu.hnk.beershop.model.StorageItem;

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
	public List<StorageItem> findAll();
	
	/**
	 * Megh�vja a rakt�r adathozz�f�r�si objektum�nak a ment�s�t, amely a list�ban szerepl� �sszes m�dos�t�st menti.
	 * @param storage a rakt�rban szerepl� elemek list�ja.
	 * @throws NegativeQuantityNumber ha valamelyik elem darabsz�ma negat�v.
	 */
	public void saveAllChanges(List<StorageItem> storage) throws NegativeQuantityNumber;
	
	public void checkStorageItemQuantityLimit(List<StorageItem> storage, Beer beer, Integer quantity) throws StorageItemQuantityExceeded, NegativeQuantityNumber;
	
}
