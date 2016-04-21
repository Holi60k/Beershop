package hu.hnk.interfaces;

import java.util.List;

import hu.hnk.beershop.model.Cart;
import hu.hnk.beershop.model.CartItem;
import hu.hnk.beershop.model.User;

/**
 * @author Nandi
 *
 */
public interface CartDao {

	/**
	 * Egy kos�r inform�ci�it lek�rdez� met�dus.
	 * 
	 * @return a kos�r inform�ci�i.
	 */
	public List<Cart> findAll();

	/**
	 * A kos�r tartalm�nak ment�se adatb�zisba.
	 * 
	 * @param cart
	 *            a mentend� kos�r.
	 * @return a mentett kos�r.
	 */
	public Cart save(Cart cart);

	public Cart findByUser(User user);

	public void deleteItem(CartItem item) throws Exception;

	public CartItem updateItem(CartItem item);
	

}
