package hu.hnk.interfaces;

import java.util.List;

import hu.hnk.beershop.model.Cart;
import hu.hnk.beershop.model.CartItem;
import hu.hnk.beershop.model.User;

/**
 * A kosarat kezel� adathozz�f�r�si oszt�ly interf�sze.
 * 
 * @author Nandi
 *
 */
public interface CartDao extends BaseDao<Cart> {

	/**
	 * Egy kos�r inform�ci�it lek�rdez� met�dus.
	 * 
	 * @return a kos�r inform�ci�i.
	 */
	public List<Cart> findAll();

	/**
	 * Kos�r keres�se felhaszn�l� alapj�n.
	 * 
	 * @param user
	 *            a keresend� kos�r birtokosa.
	 * @return a megtal�lt kos�r.
	 */
	public Cart findByUser(User user);

}
