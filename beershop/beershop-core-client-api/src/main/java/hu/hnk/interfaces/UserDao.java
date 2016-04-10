package hu.hnk.interfaces;

import java.util.List;

import hu.hnk.beershop.exception.UsernameNotFound;
import hu.hnk.beershop.model.Role;
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
	public User findByUsername(String username) throws UsernameNotFound;

	/**
	 * Felhaszn�l� keres�se e-mail c�m alapj�n.
	 * 
	 * @param email
	 *            a keresend� felhaszn�l� e-mail c�me.
	 * @return a megtal�lt felhaszn�l�
	 */
	public User findByEmail(String email);
	/**
	 * Egy adott felhaszn�l� t�rl�se.
	 * @param user a t�rlend� felhaszn�l�
	 */
	public void remove(User user);
	
	public User findByRole(List<Role> roleName);
}
