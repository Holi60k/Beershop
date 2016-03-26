package hu.hnk.interfaces;

import javax.persistence.EntityManager;

import hu.hnk.beershop.model.User;

public interface UserDao {
	/**
	 * �j felhaszn�l� ment�se.
	 * 
	 * @param user
	 *            az �j felhaszn�l�
	 * @return a mentett felhaszn�l�
	 */
	public User save(User user);

	/**
	 * Felhaszn�l� keres�se felhaszn�l�n�v alapj�n.
	 * 
	 * @param username
	 *            a keresend� felhaszn�l� felhaszn�l�neve.
	 * @return a megtal�lt felhaszn�l�
	 */
	public User findByUsername(String username);

	/**
	 * Felhaszn�l� keres�se e-mail c�m alapj�n.
	 * 
	 * @param email
	 *            a keresend� felhaszn�l� e-mail c�me.
	 * @return a megtal�lt felhaszn�l�
	 */
	public User findByEmail(String email);
}
