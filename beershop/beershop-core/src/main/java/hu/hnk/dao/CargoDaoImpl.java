/**
 * 
 */
package hu.hnk.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import hu.hnk.beershop.model.Cargo;
import hu.hnk.interfaces.CargoDao;

/**
 * A kosarakat kezel� adathozz�f�r�si oszt�ly implement�ci�ja.
 * 
 * @author Nandi
 *
 */
@Stateless
@Local(CargoDao.class)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CargoDaoImpl extends BaseDaoImpl<Cargo> implements CargoDao {

	/**
	 * Az oszt�ly konstuktora.
	 */
	public CargoDaoImpl() {
		super(Cargo.class);
	}

}
