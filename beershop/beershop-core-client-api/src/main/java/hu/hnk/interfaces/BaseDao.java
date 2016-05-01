package hu.hnk.interfaces;

import java.util.List;

/**
 * @author Nandi
 *
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * Entit�s ment�se.
	 * 
	 * @param entity
	 *            a mentend� entit�s.
	 * @return a mentett entit�s.
	 * @throws Exception
	 *             adatb�zis el�r�si hiba eset�n.
	 */
	public T save(T entity) throws Exception;

	/**
	 * Entit�s friss�t�se.
	 * 
	 * @param entity
	 *            a friss�tend� entit�s.
	 * @throws Exception
	 *             adatb�zis el�r�si hiba eset�n.
	 */
	public void update(T entity) throws Exception;

	/**
	 * Entit�s t�rl�se.
	 * 
	 * @param id
	 *            a t�rlend� entit�s azonos�t�ja.
	 * @throws Exception
	 *             adatb�zis el�r�si hiba eset�n.
	 */
	public void delete(Long id) throws Exception;

	/**
	 * Entit�s keres�se azonos�t� alapj�n.
	 * 
	 * @param id
	 *            a keresett entit�s azonos�t�ja.
	 * @return a megtal�lt entit�s.
	 * @throws Exception
	 *             adatb�zis el�r�si hiba eset�n.
	 */
	public T find(Long id) throws Exception;

}
