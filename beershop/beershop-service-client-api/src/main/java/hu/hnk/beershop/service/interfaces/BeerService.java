package hu.hnk.beershop.service.interfaces;

import java.util.List;

import hu.hnk.beershop.model.Beer;

public interface BeerService {
	/**
	 * Az �sszes s�r lek�rdez�se.
	 * 
	 * @return a s�r�k list�ja
	 */
	public List<Beer> findAll();

}
