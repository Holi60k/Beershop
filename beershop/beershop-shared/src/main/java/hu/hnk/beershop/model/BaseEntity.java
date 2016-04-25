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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
