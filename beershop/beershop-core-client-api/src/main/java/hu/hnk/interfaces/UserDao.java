package hu.hnk.interfaces;

import java.util.List;

import hu.hnk.beershop.exception.EmailNotFound;
import hu.hnk.beershop.exception.UsernameNotFound;
import hu.hnk.beershop.model.Role;
import hu.hnk.beershop.model.User;

/**
 * @author Nandi
 *
 */
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
	 * @param username a keresend� felhaszn�l� felhaszn�l�neve.
	 * @return a megtal�lt felhaszn�l�
	 * @throws UsernameNotFound ha a keresett felhaszn�l�n�vvel nem tal�lhat� felhaszn�l�.
	 */
	public User findByUsername(String username) throws UsernameNotFound;

	/**
	 * Felhaszn�l� keres�se e-mail c�m alapj�n.
	 * 
	 * @param email a keresend� felhaszn�l� e-mail c�me.
	 * @return a megtal�lt felhaszn�l�
	 * @throws EmailNotFound ha a keresett e-mail c�mmel felhaszn�l� nem tal�lhat�.
	 */
	public User findByEmail(String email) throws EmailNotFound;

	/**
	 * Egy adott felhaszn�l� t�rl�se.
	 * 
	 * @param user
	 *            a t�rlend� felhaszn�l�
	 */
	public void remove(User user);

	
	/**
	 * Felhaszn�l� keres�se felhaszn�l�n�v alapj�n.
	 * @param username a keresend� felhaszn�l�n�v.
	 * @return a kapott felhaszn�l�n�v.
	 * @throws UsernameNotFound ha a keresett felhaszn�l�n�vvel nem l�tezik felhaszn�l�.
	 */
	public String findUsername(String username) throws UsernameNotFound;
	
	/**
	 * Felhaszn�l� keres�se e-mail c�m alapj�n.
	 * @param email a keresend� e-mail c�m.
	 * @return a kapott e-mail c�m.
	 * @throws EmailNotFound ha a keresett e-mail c�mmel nem l�tezik felhaszn�l�.
	 */
	public String findEmail(String email) throws EmailNotFound;
}
