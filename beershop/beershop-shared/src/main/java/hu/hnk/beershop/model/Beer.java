package hu.hnk.beershop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A s�r oszt�ly ami tartalmazza a s�r adatait.
 * @author Nandi
 *
 */
@Table(name = "beer")
@Entity
public class Beer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4761818681252091051L;
	/**
	 * A felhaszn�l� egyedi azonos�t�ja.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

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

}
