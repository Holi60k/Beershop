package hu.hnk.beershop.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Egy felhaszn�l� bev�s�rl� kosar�nak a tarlama.
 * 
 * @author Nandi
 *
 */
@Entity
public class ShoppingCart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4761818681252091051L;

	/**
	 * A rendel�s egyedi azonos�t�ja.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	/**
	 * A kos�rban l�v� s�r�k list�ja.
	 */
	@ManyToMany
	private List<Beer> beers;

	/**
	 * A felhaszn�l� aki� a kos�r.
	 */
	@OneToOne
	private User user;

	/**
	 * A rendel�s teljes �sszege.
	 */
	@Column(name = "totalprice")
	private Double totalPrice;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the beers
	 */
	public List<Beer> getBeers() {
		return beers;
	}

	/**
	 * @param beers
	 *            the beers to set
	 */
	public void setBeers(List<Beer> beers) {
		this.beers = beers;
	}

	
	/**
	 * @return the totalPrice
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice
	 *            the totalPrice to set
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
