package hu.hnk.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import hu.hnk.beershop.model.EventLog;
import hu.hnk.beershop.model.Rank;
import hu.hnk.beershop.model.User;
import hu.hnk.beershop.service.interfaces.RestrictionCheckerService;
import hu.hnk.interfaces.EventLogDao;

@Stateless
@Local(RestrictionCheckerService.class)
public class RestrictionCheckerServiceImpl extends UserServiceImpl implements RestrictionCheckerService {

	@EJB
	private EventLogDao eventLogDao;

	/**
	 * Amat�r felhaszn�l� csak napi 3-szor t�ltheti fel a k�rty�j�t. S�rfelel�s
	 * napi 4-szor t�ltheti fel a k�rty�j�t.
	 *  Iv�bajnok napi 5-sz�r t�ltheti fel
	 * a k�rty�j�t �s kap minden felt�lt�s ut�n b�nusz 5%-ot.
	 */
	@Override
	public boolean checkIfUserCanTransferMoney(User user) {

		List<EventLog> userEvents = eventLogDao.findByUser(user)
				.stream()
				.filter(p -> p.getAction()
						.equals("Money transfer."))
				.filter(p -> p.getDate()
						.toLocalDate()
						.equals(LocalDate.now()))
				.collect(Collectors.toList());

		if (userEvents.stream()
				.count() > 3 && countRankFromXp(user).equals(Rank.Amatuer)) {
			return false;
		} else if (userEvents.stream()
				.count() > 4 && countRankFromXp(user).equals(Rank.Sorfelelos)) {
			return false;
		} else if (userEvents.stream()
				.count() > 5 && countRankFromXp(user).equals(Rank.Ivobajnok)) {
			return false;
		}

		return true;

	}

	@Override
	public boolean checkIfUserCanBuyMoreBeer(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkIfUserCanBuyLegendBeer(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkIfUserCanPayBeers(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setEventLogDao(EventLogDao eventLogDao) {
		this.eventLogDao = eventLogDao;
	}

}
