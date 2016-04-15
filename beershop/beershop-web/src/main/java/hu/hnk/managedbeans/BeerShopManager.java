package hu.hnk.managedbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import hu.hnk.beershop.model.Beer;
import hu.hnk.beershop.service.interfaces.BeerService;

/**
 * @author Nandi
 *
 */
@ManagedBean(name = "beerShopManager")
@ViewScoped
public class BeerShopManager implements Serializable {
	/**
	 * A s�r�ket kezel� szolg�ltat�s.
	 */
	@EJB
	private BeerService beerService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A s�r�k list�ja.
	 */
	private List<Beer> beersInShop;

	private Beer beerCounterSelector;

	/**
	 * A kiv�lasztott s�r.
	 */
	private Beer selectedBeer;

	private Map<Beer, Integer> beersInCart;

	/**
	 * Inicializ�l� met�dus, a managed bean l�trej�ttekor.
	 */
	@PostConstruct
	public void init() {
		setBeersInShop(beerService.findAll());
		beersInCart = new HashMap<>();
		for (Beer b : beersInShop) {
			beersInCart.put(b, 0);
		}
	}

	public void updateBeerCounter(AjaxBehaviorEvent event) {
		for (Beer b : beersInShop) {
			System.out.println(beersInCart.get(b));
		}
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
		return beersInCart;
	}

	public void setBeersInCart(Map<Beer, Integer> beersInCart) {
		this.beersInCart = beersInCart;
	}

	public Beer getBeerCounterSelector() {
		return beerCounterSelector;
	}

	public void setBeerCounterSelector(Beer beerCounterSelector) {
		this.beerCounterSelector = beerCounterSelector;
	}

}
