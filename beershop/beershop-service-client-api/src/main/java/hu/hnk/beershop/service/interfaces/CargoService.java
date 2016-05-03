package hu.hnk.beershop.service.interfaces;

import java.util.List;

import hu.hnk.beershop.exception.DailyBuyActionLimitExceeded;
import hu.hnk.beershop.model.Cargo;
import hu.hnk.beershop.model.CartItem;
import hu.hnk.beershop.model.User;

/**
 * @author Nandi
 *
 */
public interface CargoService {

	/**
	 * @param cargo
	 * @return
	 * @throws DailyBuyActionLimitExceeded
	 */
	public Cargo saveNewCargo(Cargo cargo, List<CartItem> items) throws DailyBuyActionLimitExceeded;

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
