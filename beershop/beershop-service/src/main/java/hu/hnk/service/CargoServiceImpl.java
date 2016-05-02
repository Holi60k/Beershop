package hu.hnk.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.hnk.beershop.model.Cargo;
import hu.hnk.beershop.model.CartItem;
import hu.hnk.beershop.model.User;
import hu.hnk.beershop.service.interfaces.CargoService;
import hu.hnk.beershop.service.logfactory.EventLogType;
import hu.hnk.interfaces.CargoDao;
import hu.hnk.interfaces.CartDao;
import hu.hnk.interfaces.CartItemDao;
import hu.hnk.interfaces.EventLogDao;
import hu.hnk.interfaces.UserDao;
import hu.hnk.service.factory.EventLogFactory;

/**
 * @author Nandi
 *
 */
@Stateless
@Local(CargoService.class)
public class CargoServiceImpl implements CargoService {
	/**
	 * Az oszt�ly loggere.
	 */
	public static final Logger logger = Logger.getLogger(CargoServiceImpl.class);

	/**
	 * A sz�ll�t�sokat kezel� adathozz�f�r�si objektum.
	 */
	@EJB
	private CargoDao cargoDao;

	/**
	 * A kosarat kezel� adathozz�f�r�si oszt�ly.
	 */
	@EJB
	private CartDao cartDao;

	/**
	 * A kosarat kezel� adathozz�f�r�si oszt�ly.
	 */
	@EJB
	private CartItemDao cartItemDao;

	/**
	 * A felhaszn�l�kat kezel� adathozz�f�r�si oszt�ly.
	 */
	@EJB
	private UserDao userDao;

	/**
	 * Az esem�nyeket kezel� adathozz�f�r�si oszt�ly.
	 */
	@EJB
	private EventLogDao eventLogDao;

	@Override
	public Cargo saveNewCargo(Cargo cargo, List<CartItem> items) {
		// User user = cargo.getUser();
		// Be�ll�tjuk a sz�ll�tm�ny term�keit, de csak azokat amelyek akt�vak.

		Cargo savedCargo = null;
		try {
			savedCargo = cargoDao.save(cargo);
		} catch (Exception e1) {
			logger.warn(e1);
		}
		savedCargo.setItems(items);
		try {
			cargoDao.update(savedCargo);
		} catch (Exception e1) {
			logger.warn(e1);
		}
		logger.info("Cargo saved.");
		// Miut�n mentett�k a sz�ll�t�st ut�na t�r�lj�k a felhaszn�l� kosar�b�l.
		cargo.getItems()
				.stream()
				.forEach(p -> {
					try {
						cartItemDao.deleteItemLogically(p);
					} catch (Exception e) {
						logger.warn("Exception while trying to remove items from user's cart.");
						logger.warn(e);
					}
				});

		// Levonjuk a felhaszn�l�tol a megrendel�s �r�t.
		cargo.getUser()
				.setMoney(cargo.getUser()
						.getMoney() - cargo.getTotalPrice());
		try {
			userDao.update(cargo.getUser());
		} catch (Exception e) {
			logger.warn(e);
		}

		try {
			eventLogDao.save(EventLogFactory.createEventLog(EventLogType.Buy, cargo.getUser()));
		} catch (Exception e) {
			logger.warn(e);
		}
		return savedCargo;

	}

	private void filterActiveItemsFromUsersCart(Cargo cargo) {

	}

	private void deleteItemsFromUserCartAfterSuccesfulPayment(Cargo cargo) {

	}

	private void createEventLogForUser(User user) {

	}

	private void setUserMoneyAfterCargoSetup(Cargo cargo, User user) {

	}

	@Override
	public boolean isThereEnoughMoney(User user) {
		return true;
	}

}
