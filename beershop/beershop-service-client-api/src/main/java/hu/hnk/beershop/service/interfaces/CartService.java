package hu.hnk.beershop.service.interfaces;

import java.util.List;
import java.util.Map;

import hu.hnk.beershop.model.Beer;
import hu.hnk.beershop.model.Cart;
import hu.hnk.beershop.model.CartItem;
import hu.hnk.beershop.model.User;

/**
 * @author Nandi
 *
 */
public interface CartService {

	/**
	 * A kos�r tartalm�nak ment�se adatb�zisba az adatel�r�si objektumon
	 * kereszt�l.
	 * 
	 * @param cart
	 *            a mentend� kos�r.
	 * @return a mentett kos�r.
	 */
	public Cart save(Cart cart);

	/**
	 * Kos�r megkeres�se felhaszn�l� alapj�n.
	 * 
	 * @param user
	 *            a keresett felhaszn�l�.
	 * @return a keresett kos�r.
	 */
	public Cart findByUser(User user);

	/**
	 * Term�k logikai t�rl�se a kos�rb�l.
	 * 
	 * @param item
	 *            a t�rlend� elem.
	 * @throws Exception
	 *             b�rmilyen adatb�zis hiba eset�n.
	 */
	public void deletItemFromCart(CartItem item) throws Exception;

	/**
	 * Elemek kos�rba t�rt�n� ment�se.
	 * 
	 * @param beersToCart
	 *            a mentend� s�r�k darabsz�mmal.
	 * @param cart
	 *            a felhaszn�l� kosara.
	 */
	public void saveItemsToCart(Map<Beer, Integer> beersToCart, Cart cart);

	/**
	 * A v�s�rl�s sor�n fizetend� �sszeg sz�m�t�sa.
	 * 
	 * @param cartItems
	 *            a felhaszn�l� kosar�ban szerepl� term�kek list�ja.
	 * @return a fizetend� �sszeg.
	 */
	public Double countTotalCost(List<CartItem> cartItems);

	/**
	 * A b�nusz pontok sz�m�t�sa, egy v�s�rl�s sor�n. A b�nusz a s�r
	 * alkoholtartalm�nak, a megrendelt darabsz�mb�l, a s�r �r�b�l illetve a
	 * kedvezm�ny szorzatak�nt sz�mol�dik.
	 * 
	 * @param cartItems
	 *            a kos�rban lev� term�kek.
	 * @return a kisz�m�tott b�nusz pontok.
	 */
	public Double countBonusPoints(List<CartItem> cartItems);

	/**
	 * @param user
	 * @param paymentMode
	 * @return
	 */
	public Double countMoneyAfterPayment(User user,Double cost, String paymentMode);

}
