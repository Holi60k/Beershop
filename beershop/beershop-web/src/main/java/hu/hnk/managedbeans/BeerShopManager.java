package hu.hnk.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	BeerService beerService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A s�r�k list�ja.
	 */
	private List<Beer> beersInShop;

	/**
	 * A kiv�lasztott s�r.
	 */
	private Beer selectedBeer;

	/**
	 * Inicializ�l� met�dus, a managed bean l�trej�ttekor.
	 */
	@PostConstruct
	public void init() {
		setBeersInShop(beerService.findAll());
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

}
