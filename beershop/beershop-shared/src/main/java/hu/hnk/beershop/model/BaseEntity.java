package hu.hnk.beershop.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Az entit�sok f�oszt�lya, ami tartalmazza az egyedi azonos�t�jukat.
 * 
 * @author Nandi
 *
 */
@MappedSuperclass
public class BaseEntity {

	/**
	 * Az entit�sok egyedi azonos�t�ja.
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
	protected Long id;

	/**
	 * Visszaadja az entit�s egyedi azonos�t�j�t.
	 * 
	 * @return az egyedi azonos�t�.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Be�ll�tja az entit�s egyedi azonos�t�j�t.
	 * 
	 * @param id
	 *            az azonos�t� amit be kell �ll�tani.
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
