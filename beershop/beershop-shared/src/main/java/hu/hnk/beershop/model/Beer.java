package hu.hnk.beershop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * A s�r oszt�ly ami tartalmazza a s�r adatait.
 * 
 * @author Nandi
 *
 */

// @Table(name = "beer")
@Entity
@NamedQueries({ @NamedQuery(name = "Beer.findAll", query = "SELECT b FROM Beer b") })
public class Beer extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4761818681252091051L;

	/**
	 * A s�r neve.
	 */
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	/**
	 * A s�r alkoholtartalma.
	 */
	@Column(name = "alcoholLevel", nullable = false)
	private Double alcoholLevel;

	/**
	 * A s�r �ra.
	 */
	@Column(name = "price", nullable = false)
	private Double price;

	/**
	 * A s�rh�z tartoz� megjegyz�s.
	 */
	@Column(name = "comment", length = 255)
	private String comment;

	/**
	 * A s�r �rtartalma.
	 */
	@Column(name = "capacity")
	private Double capacity;

	/**
	 * A s�r akci� sor�n be�ll�tott kedvezm�nye.
	 */
	@Column(name = "discountAmount", nullable = false, columnDefinition = "int(5) default 0")
	private Integer discountAmount;


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the alcoholLevel
	 */
	public Double getAlcoholLevel() {
		return alcoholLevel;
	}

	/**
	 * @param alcoholLevel
	 *            the alcoholLevel to set
	 */
	public void setAlcoholLevel(Double alcoholLevel) {
		this.alcoholLevel = alcoholLevel;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the capacity
	 */
	public Double getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the discountAmount
	 */
	public Integer getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @param discountAmount
	 *            the discountAmount to set
	 */
	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}

}
