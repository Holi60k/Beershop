package hu.hnk.interfaces;

import hu.hnk.beershop.model.CartItem;

/**
 * @author Nandi
 *
 */
public interface CartItemDao extends BaseDao<CartItem> {

	/**
	 * Term�k logikai t�rl�se a kos�rb�l.
	 * 
	 * @param item
	 *            a t�rlend� term�k.
	 * @throws Exception
	 *             b�rmilyen hib�s adatb�zis m�velet eset�n.
	 */
	public void deleteItemLogically(CartItem item) throws Exception;
}
