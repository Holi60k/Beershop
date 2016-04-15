package hu.hnk.beershop.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * A s�r rakt�r, amelyben tal�lhat� egy s�r illetve egy eg�sz sz�m attrib�tum
 * ami a s�r mennyis�g�t hivatott mutatni.
 * 
 * @author Nandi
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Storage.findAll", query = "SELECT s FROM Storage s") })
public class Storage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4761818681252091051L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	/**
	 * A s�r.
	 */
	@OneToOne(cascade = CascadeType.MERGE)
	private Beer beer;

	/**
	 * A s�r aktu�lis darabsz�ma a rakt�rban.
	 */
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Beer getBeer() {
		return beer;
	}

	public void setBeer(Beer beer) {
		this.beer = beer;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
