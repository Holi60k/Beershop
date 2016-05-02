package hu.hnk.managedbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import hu.hnk.beershop.exception.NegativeQuantityNumber;
import hu.hnk.beershop.exception.StorageItemQuantityExceeded;
import hu.hnk.beershop.model.Beer;
import hu.hnk.beershop.model.StorageItem;
import hu.hnk.beershop.service.interfaces.BeerService;
import hu.hnk.beershop.service.interfaces.CartService;
import hu.hnk.beershop.service.interfaces.StorageService;
import hu.hnk.loginservices.SessionManager;
import hu.hnk.tool.FacesMessageTool;

/**
 * @author Nandi
 *
 */
@ManagedBean(name = "beerShopManager")
@ViewScoped
public class BeerShopManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Az oszt�ly loggere.
	 */
	public static final Logger logger = Logger.getLogger(BeerShopManager.class);

	/**
	 * A s�r�ket kezel� szolg�ltat�s.
	 */
	@EJB
	private BeerService beerService;

	/**
	 * A rakt�rt kezel� szolg�ltat�s.
	 */
	@EJB
	private StorageService storageService;

	/**
	 * A kosarat kezel� szolg�ltat�s.
	 */
	@EJB
	private CartService cartService;

	@ManagedProperty(value = "#{sessionManagerBean}")
	private SessionManager sessionManager;

	/**
	 * A s�r�k list�ja.
	 */
	private List<Beer> beersInShop;

	/**
	 * A kiv�lasztott s�r.
	 */
	private Beer selectedBeer;

	private Map<Beer, Integer> beersToCart;

	/**
	 * Inicializ�l� met�dus, a managed bean l�trej�ttekor.
	 */
	@PostConstruct
	public void init() {
		setBeersInShop(beerService.findAll());
		resetCart();
	}

	private void resetCart() {
		beersToCart = new HashMap<>();
		for (Beer b : beersInShop) {
			beersToCart.put(b, 0);
		}
	}

	public void incrementBeer(Beer beer) {
		Integer quantity = beersToCart.get(beer);
		List<StorageItem> items = storageService.findAll();

		try {
			storageService.checkStorageItemQuantityLimit(items, beer, ++quantity);
			beersToCart.put(beer, quantity);
		} catch (StorageItemQuantityExceeded e) {
			logger.warn(e.getMessage());

			beersToCart.put(beer, items.stream()
					.filter(p -> p.getBeer()
							.equals(beer))
					.findFirst()
					.get()
					.getQuantity());
			FacesMessageTool.createInfoMessage("A rakt�r maximum�t el�rte.");
		} catch (NegativeQuantityNumber e) {
			logger.warn(e.getMessage());
		}

	}

	public void decreaseBeer(Beer beer) {

		Integer quantity = beersToCart.get(beer);
		List<StorageItem> items = storageService.findAll();
		try {
			storageService.checkStorageItemQuantityLimit(storageService.findAll(), beer, --quantity);
			beersToCart.put(beer, quantity);
		} catch (StorageItemQuantityExceeded e) {
			logger.warn(e.getMessage());
			beersToCart.put(beer, items.stream()
					.filter(p -> p.getBeer()
							.equals(beer))
					.findFirst()
					.get()
					.getQuantity());
		} catch (NegativeQuantityNumber e) {
			logger.warn(e.getMessage());
			FacesMessageTool.createWarnMessage("A darabsz�m nem lehet negat�v �rt�k!");
		}

	}

	public void saveItemsToCart() {

		cartService.saveItemsToCart(beersToCart, cartService.findByUser(sessionManager.getLoggedInUser()));

		FacesMessageTool.createInfoMessage("Term�kek a kos�rba helyezve.");
		resetCart();
	}

	/**
	 * Visszaadja az adatb�zisban szerepl� s�r�k list�j�t.
	 * 
	 * @return az adatb�zisban szerepl� s�r�k list�ja.
	 */
	public List<Beer> getBeersInShop() {
		return beersInShop;
	}

	/**
	 * Be�ll�tja a megjelen�tend� s�r�k list�j�t.
	 * 
	 * @param beersInShop
	 *            a megjelen�tend� s�r�k.
	 */
	public void setBeersInShop(List<Beer> beersInShop) {
		this.beersInShop = beersInShop;
	}

	/**
	 * Visszaadja a kiv�lasztott s�rt a lis�tb�l.
	 * 
	 * @return a kiv�lasztott s�r.
	 */
	public Beer getSelectedBeer() {
		return selectedBeer;
	}

	/**
	 * Be�ll�tja a kiv�lasztott s�rt.
	 * 
	 * @param selectedBeer
	 *            a kiv�lsztott s�r.
	 */
	public void setSelectedBeer(Beer selectedBeer) {
		this.selectedBeer = selectedBeer;
	}

	public Map<Beer, Integer> getBeersInCart() {
		return beersToCart;
	}

	public void setBeersInCart(Map<Beer, Integer> beersInCart) {
		this.beersToCart = beersInCart;
	}

	/**
	 * @return the sessionManager
	 */
	public SessionManager getSessionManager() {
		return sessionManager;
	}

	/**
	 * @param sessionManager
	 *            the sessionManager to set
	 */
	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

}
