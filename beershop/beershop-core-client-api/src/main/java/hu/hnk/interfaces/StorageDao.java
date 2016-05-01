package hu.hnk.interfaces;

import java.util.List;

import hu.hnk.beershop.model.Beer;
import hu.hnk.beershop.model.StorageItem;

/**
 * A rakt�rt kezel� adathozz�f�r�i oszt�ly interf�sze.
 * 
 * @author Nandi
 *
 */
public interface StorageDao extends BaseDao<StorageItem> {

	/**
	 * A rakt�r inform�ci�it lek�rdez� met�dus.
	 * 
	 * @return a rakt�r inform�ci�i.
	 */
	public List<StorageItem> findAll();

	/**
	 * A param�terk�nt kapott rakt�rbeli elemeken v�gzett m�dos�t�sok ment�se az
	 * adatb�zisba.
	 * 
	 * @param storage
	 *            a mentend� rakt�rbeli elemek list�ja.
	 */
	public void saveAllChanges(List<StorageItem> storage);

	/**
	 * Rakt�rbeli elem keres�se s�r alapj�n.
	 * 
	 * @param beer
	 *            a keresend� s�r.
	 * @return a megtal�lt rakt�rbeli elem.
	 */
	public StorageItem findByBeer(Beer beer);
}
