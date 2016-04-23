package hu.hnk.beershop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

/**
 * A jogk�r le�r�s�t tartalmaz� oszt�ly.
 * 
 * @author Nandi
 *
 */
@Entity
@NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name")
public class Role extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;

	/**
	 * A jogk�r neve.
	 */
	@Column(name = "name")
	private String name;


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

}
