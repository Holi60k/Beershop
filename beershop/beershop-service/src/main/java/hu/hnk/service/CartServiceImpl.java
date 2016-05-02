/**
 * 
 */
package hu.hnk.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.hnk.beershop.model.Beer;
import hu.hnk.beershop.model.Cart;
import hu.hnk.beershop.model.CartItem;
import hu.hnk.beershop.model.StorageItem;
import hu.hnk.beershop.model.User;
import hu.hnk.beershop.service.interfaces.CartService;
import hu.hnk.interfaces.CartDao;
import hu.hnk.interfaces.CartItemDao;
import hu.hnk.interfaces.StorageDao;

/**
 * A felhaszn�l�k kosarait kezel� szolg�ltat�s. Amikor a felhaszn�l� hozz�ad egy
 * elemet a kosar�hoz a {@link CartService#saveItemsToCart(Map, Cart)} met�dus
 * fogja ezt a kosar�ba pakolni.
 * 
 * @author Nandi
 *
 */
@Stateless
@Local(CartService.class)
public class CartServiceImpl implements CartService {

	/**
	 * Az oszt�ly loggere.
	 */
	public static final Logger logger = Logger.getLogger(CartServiceImpl.class);

	/**
	 * A kos�rm�veletek v�gz� adathozz�f�r�si objektumn.
	 */
	@EJB
	private CartDao cartDao;

	/**
	 * A kosarat kezel� adathozz�f�r�si oszt�ly.
	 */
	@EJB
	private CartItemDao cartItemDao;

	/**
	 * A rakt�rt kezel� adathozz�f�r�si objektum.
	 */
	@EJB
	private StorageDao storageDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hu.hnk.beershop.service.interfaces.CartService#save(hu.hnk.beershop.model
	 * .Cart)
	 */
	@Override
	public Cart save(Cart cart) {
		try {
			return cartDao.save(cart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hu.hnk.beershop.service.interfaces.CartService#findByUser(hu.hnk.beershop
	 * .model.User)
	 */
	@Override
	public Cart findByUser(User user) {
		return cartDao.findByUser(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hu.hnk.beershop.service.interfaces.CartService#saveItemsToCart(java.util.
	 * Map, hu.hnk.beershop.model.Cart)
	 */
	@Override
	public void saveItemsToCart(Map<Beer, Integer> beersToCart, Cart cart) {
		logger.info("Trying save items to user's cart.");
		List<CartItem> cartItems = cart.getItems();
		List<StorageItem> storageItems = storageDao.findAll();
		for (Beer beer : beersToCart.keySet()) {
			addBeerToCartItemList(beersToCart, cartItems, storageItems, beer);
		}

		cart.setItems(cartItems);
		try {
			cartDao.update(cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Items saved succesfuly to the user's cart.");
	}

	/**
	 * A kiv�lasztott s�r kos�rba val� helyez�se. A met�dus ellen�rzi hogy a
	 * kiv�laszott s�r szerepel-e a rakt�rban a
	 * {@link CartServiceImpl#findBeerInStorage(List, Beer)} met�dus
	 * seg�ts�g�vel, amint ez megt�rt�nt ellen�rzi hogy a v�lasztott s�r
	 * szerepel-e m�r a felhaszn�l� kosar�ban a
	 * {@link CartServiceImpl#findBeerInUsersCart(List, Beer)} met�dus
	 * megh�v�s�val. Amint adottak a felt�telek megn�zz�k mennyi s�rt kell
	 * berakni a kos�rba, megt�rt�nik a tranzakci�.
	 * 
	 * @param beersToCart
	 *            a s�r�k Map-je darabsz�mmal egy�tt.
	 * @param cartItems
	 *            a m�r kos�rban lev� term�kek list�ja.
	 * @param storageItems
	 *            a rakt�rban szerepl� term�kek list�ja.
	 * @param beer
	 *            a kiv�lasztott s�r.
	 */
	private void addBeerToCartItemList(Map<Beer, Integer> beersToCart, List<CartItem> cartItems,
			List<StorageItem> storageItems, Beer beer) {

		StorageItem beerInStorage = null;

		// El�bb megkeress�k a s�rt a rakt�rb�l.
		try {
			beerInStorage = findBeerInStorage(storageItems, beer);
		} catch (NoSuchElementException e) {
			logger.warn("Beer has not been found in the storage.");
		}

		CartItem item;
		CartItem foundItem;

		// Megn�zz�k hogy a v�lasztott s�r szerepel-e m�r a felhaszn�l�
		// kosar�ban.
		try {
			foundItem = findBeerInUsersCart(cartItems, beer);
		} catch (NoSuchElementException e) {
			foundItem = null;
			logger.info("Beer has not found in the user's cart.");
		}

		// Leellen�rizz�k hogy a s�r l�tezik-e rakt�rban.
		if (beerInStorage != null) {
			if (beersToCart.get(beer) > 0 && beerInStorage.getQuantity() > 0) {
				if (foundItem == null) {
					item = new CartItem();
					item.setAddedToCart(LocalDateTime.now());
					item.setBeer(beer);
					item.setQuantity(beersToCart.get(beer));
					item.setActive(true);
					try {
						cartItemDao.save(item);
					} catch (Exception e) {
						logger.warn(e);
					}
					cartItems.add(item);
					logger.info("New item added to user's cart list.");
				} else {
					cartItems.remove(foundItem);
					foundItem.setQuantity(foundItem.getQuantity() + beersToCart.get(beer));
					foundItem.setAddedToCart(LocalDateTime.now());
					try {
						cartItemDao.update(foundItem);
					} catch (Exception e) {
						logger.warn(e);
					}
					cartItems.add(foundItem);
					logger.info("Found item updated in user's cart.");
				}

				beerInStorage.setQuantity(beerInStorage.getQuantity() - beersToCart.get(beer));
				try {
					storageDao.save(beerInStorage);
				} catch (Exception e) {
					logger.warn(e);
				}

			}
		}
	}

	/**
	 * Ellen�rzi hogy a s�r (<code>beer</code>) szerepel-e m�r a felhaszn�l�
	 * kosar�ban.
	 * 
	 * @param cartItems
	 *            a felhaszn�l� kosar�ban megl�v� term�kek.
	 * @param beer
	 *            a kiv�lasztott s�r.
	 * @return visszaadja a term�ket ha m�r szerepel a felhaszn�l� kosar�ban.
	 * @throws NoSuchElementException
	 *             ha nem szerepel a term�k a felhaszn�l� kosar�ban.
	 */
	private CartItem findBeerInUsersCart(List<CartItem> cartItems, Beer beer) throws NoSuchElementException {
		return cartItems.stream()
				.filter(p -> p.getBeer()
						.equals(beer) && p.getActive() == true)
				.findFirst()
				.get();
	}

	/**
	 * Ellen�rzi hogy a s�r (<code>beer</code>) l�tezik-e a rakt�rban.
	 * 
	 * @param storageItems
	 *            a rakt�rban szerepl� term�kek list�ja.
	 * @param beer
	 *            az ellen�rizend� s�r.
	 * @return a megtal�lt term�k.
	 * @throws NoSuchElementException
	 *             ha a term�k nem szerepel a rakt�rban.
	 */
	private StorageItem findBeerInStorage(List<StorageItem> storageItems, Beer beer) throws NoSuchElementException {
		return storageItems.stream()
				.filter(e -> e.getBeer()
						.equals(beer))
				.findFirst()
				.get();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hu.hnk.beershop.service.interfaces.CartService#countTotalCost(java.util.
	 * List)
	 */
	@Override
	public Double countTotalCost(List<CartItem> cartItems) {
		return cartItems.stream()
				.filter(p -> p.getActive())
				.mapToDouble(e -> e.getBeer()
						.getPrice() * e.getQuantity()
						* (100 - e.getBeer()
								.getDiscountAmount())
						/ 100)
				.sum();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hu.hnk.beershop.service.interfaces.CartService#deletItemFromCart(hu.hnk.
	 * beershop.model.CartItem)
	 */
	@Override
	public void deletItemFromCart(CartItem item) throws Exception {
		try {
			logger.info("Trying to delete item from cart.");
			StorageItem stItem = storageDao.findByBeer(item.getBeer());
			stItem.setQuantity(stItem.getQuantity() + item.getQuantity());
			cartItemDao.deleteItemLogically(item);
		} catch (Exception e) {
			logger.warn("Error while trying to delete item from user's cart.", e);
			throw new Exception("We were not able to delete the item from the user's cart.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hu.hnk.beershop.service.interfaces.CartService#countBonusPoints(java.util
	 * .List)
	 */
	@Override
	public Double countBonusPoints(List<CartItem> cartItems) {
		return cartItems.stream()
				.mapToDouble(e -> (e.getQuantity() * e.getBeer()
						.getPrice()) / 100
						+ (e.getBeer()
								.getAlcoholLevel()
								+ e.getBeer()
										.getCapacity()
								+ e.getBeer()
										.getDiscountAmount()))
				.sum();
	}

	@Override
	public Double countMoneyAfterPayment(User user, Double cost, String paymentMode) {
		Double result = (double) 0;
		if (paymentMode.equals("money")) {
			result = user.getMoney() - cost;
		} else if (paymentMode.equals("bonusPoint")) {
			result = user.getPoints() - cost;
		}
		return result;
	}

	/**
	 * @param cartDao
	 */
	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	/**
	 * @param storageDao
	 */
	public void setStorageDao(StorageDao storageDao) {
		this.storageDao = storageDao;
	}

	/**
	 * @param cartItemDao
	 */
	public void setCartItemDao(CartItemDao cartItemDao) {
		this.cartItemDao = cartItemDao;
	}

}
