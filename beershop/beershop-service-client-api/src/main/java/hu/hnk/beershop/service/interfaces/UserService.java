package hu.hnk.beershop.service.interfaces;

import java.util.Date;

import hu.hnk.beershop.model.User;

public interface UserService {

	public void save(User user);

	public boolean isOlderThanEighteen(Date dateOfBirth);

	public User findByUsername(String username);
	
	/**
	 * Felhaszn�l�n�v ellen�rz�s, a kapott felhaszn�l�nevet ellen�rzi hogy v�laszhat�-e m�g a regisztr�ci� sor�n.
	 * @param username az ellen�rizend� felhaszn�l�n�v.
	 * @return igaz ha szabad a felhaszn�l�n�v, hamis ha m�r nem.
	 */
	public boolean isUsernameAlreadyTaken(String username);
	
	/**
	 * E-mail c�m ellen�rz�s, a kapott e-mail c�met ellen�rzi hogy v�laszhat�-e m�g a regisztr�ci� sor�n.
	 * @param email az ellen�rizend� e-mail c�m.
	 * @return igaz ha szabad a email c�m, hamis ha m�r nem.
	 */
	public boolean isEmailAlreadyTaken(String email);
	
}
