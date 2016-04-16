package hu.hnk.beershop.service.interfaces;

import java.util.Map;

import hu.hnk.beershop.model.Beer;
import hu.hnk.beershop.model.Cart;
import hu.hnk.beershop.model.User;

/**
 * @author Nandi
 *
 */
public interface CartService {

	/**
	 * A kos�r tartalm�nak ment�se adatb�zisba az adatel�r�si objektumon kereszt�l.
	 * 
	 * @param cart
	 *            a mentend� kos�r.
	 * @return a mentett kos�r.
	 */
	public Cart save(Cart cart);

	public Cart findByUser(User user);
	
	public void saveItemsToCart(Map<Beer,Integer> beersToCart, Cart cart);

}
