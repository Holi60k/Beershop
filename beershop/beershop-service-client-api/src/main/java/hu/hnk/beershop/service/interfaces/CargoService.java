package hu.hnk.beershop.service.interfaces;

import hu.hnk.beershop.model.Cargo;
import hu.hnk.beershop.model.User;

/**
 * @author Nandi
 *
 */
public interface CargoService {

	/**
	 * @param cargo
	 * @return
	 */
	public Cargo saveNewCargo(Cargo cargo) throws Exception;

	/**
	 * Ellen�rzi hogy a param�terk�nt megadott felhaszn�l�nak rendelkez�s�re
	 * �ll-e elegend� p�nz a fizet�sre.
	 * 
	 * @param user
	 *            az ellen�rizend� felhasz�nl�.
	 * @return igaz ha van el�g p�nze, hamis ha nem.
	 */
	public boolean isThereEnoughMoney(User user);
}
