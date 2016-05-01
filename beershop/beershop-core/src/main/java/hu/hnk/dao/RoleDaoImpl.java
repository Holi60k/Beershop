package hu.hnk.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;

import hu.hnk.beershop.model.Role;
import hu.hnk.interfaces.RoleDao;

/**
 * A jogk�r�ket kezel� adathozz�f�r�si oszt�ly megval�s�t�sa.
 * 
 * @author Nandi
 *
 */
@Stateless
@Local(RoleDao.class)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	/**
	 * Az oszt�ly konstuktora.
	 */
	public RoleDaoImpl() {
		super(Role.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public Role findByName(String name) {
		TypedQuery<Role> role = entityManager.createNamedQuery("Role.findByName", Role.class);
		role.setParameter("name", name);
		try {
			return role.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
