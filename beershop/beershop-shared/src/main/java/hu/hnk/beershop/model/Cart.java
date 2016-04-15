package hu.hnk.beershop.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 * Egy kos�r adatait tartalmaz� oszt�ly.
 * 
 * @author Nandi
 *
 */
@Entity
public class Cart implements Serializable {

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
	 * A rendelt s�r�k list�ja.
	 */
	@ManyToMany
	private List<Beer> beers;

	/**
	 * A felhaszn�l� aki rendelkezik az aktu�lis kos�rral.
	 */
	@OneToOne
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
