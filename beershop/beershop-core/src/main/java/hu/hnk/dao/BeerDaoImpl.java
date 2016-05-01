/**
 * 
 */
package hu.hnk.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;

import hu.hnk.beershop.model.Beer;
import hu.hnk.interfaces.BeerDao;

/**
 * A s�r�ket kezel� adathozz�f�r�si oszt�ly implement�ci�ja.
 * 
 * @author Nandi
 *
 */
@Stateless
@Local(BeerDao.class)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class BeerDaoImpl extends BaseDaoImpl<Beer> implements BeerDao {

	/**
	 * A s�r�ket kezel� oszt�ly konstuktora.
	 */
	public BeerDaoImpl() {
		super(Beer.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Beer> findAll() {
		TypedQuery<Beer> query = entityManager.createNamedQuery("Beer.findAll", Beer.class);
		return query.getResultList();
	}

}
