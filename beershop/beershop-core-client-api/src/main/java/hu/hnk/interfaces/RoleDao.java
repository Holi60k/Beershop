package hu.hnk.interfaces;

import hu.hnk.beershop.model.Role;

/**
 * A jogk�r�k adatb�ziskezel�s�t le�r� interf�sz.
 * @author Nandi
 *
 */
public interface RoleDao extends BaseDao<Role> {
	
	/**
	 * Jogk�r keres�se n�v alapj�n.
	 * @param name a keresend� jogk�r neve.
	 * @return a tal�lt jogk�r.
	 */
	public Role findByName(String name);
	
}
