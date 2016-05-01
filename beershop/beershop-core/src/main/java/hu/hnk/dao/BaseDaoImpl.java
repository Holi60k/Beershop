package hu.hnk.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import hu.hnk.beershop.model.BaseEntity;
import hu.hnk.interfaces.BaseDao;
import hu.hnk.persistenceunit.PersistenceUnitDeclaration;

/**
 * Egy b�zis adathozz�f�r�si oszt�ly amely az alapvet� adatb�zis m�veletek fogja
 * nek�nk szolg�ltatni egy abstract oszt�lyk�nt. A n�gy darab alap m�veletet itt
 * �rjuk le, kihaszn�lva az �r�kl�d�st, ezzel gyors�tva a fejleszt�s menet�t,
 * minden egyes DAO meg�r�sakor csak ezt az oszt�lyt kell kiterjeszteni �s
 * azonnal haszn�lhat�v� v�lik a n�gy darab m�velet.
 * {@link BaseDaoImpl#save(BaseEntity)} - egy �j entit�s ment�se az adatb�zisba
 * {@link BaseDaoImpl#update(BaseEntity)} - m�r egy megl�v� entit�s friss�t�se
 * {@link BaseDaoImpl#delete(Long)} - egy megl�v� entit�s t�rl�s, param�tere az
 * entit�s azonos�t�ja {@link BaseDaoImpl#find(Long)} - entit�s keres�se az
 * azonos�t�ja alapj�n.
 * 
 * @author Nandi
 * @param <E>
 *            a DAO �ltal kezelt entit�s
 *
 */
public abstract class BaseDaoImpl<E extends BaseEntity> implements BaseDao<E> {

	/**
	 * Az entit�sokat kezel� entit�s menedzser objektum.
	 */
	@PersistenceContext(unitName = PersistenceUnitDeclaration.PERSISTENCE_UNIT)
	protected EntityManager entityManager;

	protected Class<E> entityClass;

	/**
	 * Az oszt�ly konstuktora.
	 * 
	 * @param entityClass
	 *            a kezelend� entit�s.
	 */
	public BaseDaoImpl(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E save(E entity) throws Exception {
		entityManager.persist(entity);
		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(E entity) throws Exception {
		this.entityManager.merge(entity);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) throws Exception {
		E e = this.entityManager.find(entityClass, id);
		this.entityManager.remove(e);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E find(Long id) throws Exception {
		return this.entityManager.find(entityClass, id);
	}

}
