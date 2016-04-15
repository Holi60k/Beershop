package hu.hnk.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import hu.hnk.beershop.model.Role;
import hu.hnk.interfaces.RoleDao;

/**
 * A jogk�r�ket kezel� adathozz�f�r�si oszt�ly megval�s�t�sa.
 * @author Nandi
 *
 */
@Stateless
@Local(RoleDao.class)
public class RoleDaoImpl implements RoleDao {
	
	/**
	 * JPA Entity Manager.
	 */
	@PersistenceContext(unitName = "BeerShopUnit")
	private EntityManager em;
	
	/**
	 * Jogk�r keres�se n�v alapj�n.
	 * @param name a keresend� jogk�r neve.
	 * @return a tal�lt jogk�r.
	 */
	public Role findByName(String name) {
		TypedQuery<Role> role = em.createNamedQuery("Role.findByName", Role.class);
		role.setParameter("name", name);
		return role.getSingleResult();
	}
	
	/**
	 * Jogk�r ment�se az adatb�zisba.
	 * @param role a mentend� jogk�r.
	 * @return a mentett jogk�r.
	 */
	@Override
	public Role save(Role role) {
		return em.merge(role);
	}

}
