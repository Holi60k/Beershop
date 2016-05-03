package hu.hnk.beershop.service.interfaces;

import java.util.Date;

import hu.hnk.beershop.exception.InvalidPinCode;
import hu.hnk.beershop.exception.DailyMoneyTransferLimitExceeded;
import hu.hnk.beershop.model.Rank;
import hu.hnk.beershop.model.User;

/**
 * A felhaszn�l� entit�shoz kapcsol�d� szolg�ltat�sok interf�sze.
 * 
 * @author Nandi
 *
 */
public interface UserService {

	/**
	 * Felhaszn�l� ment�se.
	 * 
	 * @param user
	 *            a mentend� felhaszn�l�.
	 */
	public void save(User user);

	/**
	 * Ellen�rzi hogy a megadott d�tum m�r "id�sebb" mint 18 �v.
	 * 
	 * @param dateOfBirth
	 *            a vizsg�land� d�tum.
	 * @return igaz ha id�sebb, hamis ha m�g nem.
	 */
	public boolean isOlderThanEighteen(Date dateOfBirth);

	/**
	 * Felhaszn�l� keres�se a felhaszn�l�neve alapj�n.
	 * 
	 * @param username
	 *            a keresend� felhaszn�l�n�v
	 * @return a megtal�lt felhaszn�l�, ha nincs ilyen akkor null.
	 */
	public User findByUsername(String username);

	/**
	 * Felhaszn�l�n�v ellen�rz�s, a kapott felhaszn�l�nevet ellen�rzi hogy
	 * v�laszhat�-e m�g a regisztr�ci� sor�n.
	 * 
	 * @param username
	 *            az ellen�rizend� felhaszn�l�n�v.
	 * @return igaz ha szabad a felhaszn�l�n�v, hamis ha m�r nem.
	 */
	public boolean isUsernameAlreadyTaken(String username);

	/**
	 * E-mail c�m ellen�rz�s, a kapott e-mail c�met ellen�rzi hogy v�laszhat�-e
	 * m�g a regisztr�ci� sor�n.
	 * 
	 * @param email
	 *            az ellen�rizend� e-mail c�m.
	 * @return igaz ha szabad a email c�m, hamis ha m�r nem.
	 */
	public boolean isEmailAlreadyTaken(String email);

	public Rank countRankFromXp(User user);

	public Integer countExperiencePointsInPercentage(Double experiencePoints);

	public void transferMoney(String userPin, String expectedPin, Integer money, User loggedInUser)
			throws InvalidPinCode, DailyMoneyTransferLimitExceeded;
}
