package hu.hnk.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

import hu.hnk.beershop.exception.EmailNotFound;
import hu.hnk.beershop.exception.InvalidPinCode;
import hu.hnk.beershop.exception.UsernameNotFound;
import hu.hnk.beershop.model.Rank;
import hu.hnk.beershop.model.Role;
import hu.hnk.beershop.model.User;
import hu.hnk.beershop.service.interfaces.EventLogService;
import hu.hnk.beershop.service.interfaces.UserService;
import hu.hnk.beershop.service.logfactory.EventLogType;
import hu.hnk.interfaces.RoleDao;
import hu.hnk.interfaces.UserDao;
import hu.hnk.service.factory.EventLogFactory;

/**
 * A felhaszn�l�i szolg�lat�sokkal foglalkoz� oszt�ly. Enterprise Java Bean.
 * 
 * @author Nandi
 * 
 */
@Stateless(name = "userService")
@Local(UserService.class)
public class UserServiceImpl implements UserService {

	/**
	 * Az oszt�ly loggere.
	 */
	public static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	/**
	 * A felhaszn�l�kat kezel� adathozz�f�r�si objektum.
	 */
	@EJB
	private UserDao userDao;

	/**
	 * A jogk�r�ket kezel� adathozz�f�r�si objektum.
	 */
	@EJB
	private RoleDao roleDao;

	/**
	 * Az esem�nyeket kezel� szolg�ltat�s.
	 */
	@EJB
	private EventLogService eventLogService;

	/**
	 * A felhaszn�l� ment�se.
	 * 
	 * @param user
	 *            A mentend� felhaszn�l�.
	 */
	public void save(User user) {
		Role role = roleDao.findByName("ROLE_USER");

		if (role == null) {
			role = new Role();
			role.setName("ROLE_USER");
			try {
				role = roleDao.save(role);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		User userData = null;
		try {
			userData = userDao.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Role> userRoles = userData.getRoles();

		if (userRoles == null || userRoles.isEmpty()) {
			userRoles = new ArrayList<>();
		}

		userRoles.add(role);
		userData.setRoles(userRoles);
		userData.getCart()
				.setUser(userData);
		try {
			userDao.update(userData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eventLogService.save(EventLogFactory.createEventLog(EventLogType.Registration, userData));
	}

	/**
	 * Ellen�rzi hogy a megadott d�tum m�r "id�sebb" mint 18 �v.
	 * 
	 * @param dateOfBirth
	 *            a vizsg�land� d�tum.
	 * @return igaz ha id�sebb, hamis ha m�g nem.
	 */
	public boolean isOlderThanEighteen(Date dateOfBirth) {
		LocalDate now = LocalDate.now();
		Instant instant = Instant.ofEpochMilli(dateOfBirth.getTime());
		LocalDate birth = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
				.toLocalDate();
		return birth.until(now)
				.getYears() > 17;
	}

	/**
	 * Felhaszn�l� keres�se a felhaszn�l�neve alapj�n.
	 * 
	 * @param username
	 *            a keresend� felhaszn�l�n�v
	 * @return a megtal�lt felhaszn�l�, ha nincs ilyen akkor null.
	 */
	@Override
	public User findByUsername(String username) {
		User user = null;
		try {
			user = userDao.findByUsername(username);
		} catch (UsernameNotFound e) {
			logger.warn("Username:" + username + " is not found in our database.");
		}
		return user;
	}

	/**
	 * Felhaszn�l�n�v ellen�rz�s, a kapott felhaszn�l�nevet ellen�rzi hogy
	 * v�laszhat�-e m�g a regisztr�ci� sor�n.
	 * 
	 * @param username
	 *            az ellen�rizend� felhaszn�l�n�v.
	 * @return hamis ha szabad a felhaszn�l�n�v, igaz ha m�r nem.
	 */
	@Override
	public boolean isUsernameAlreadyTaken(String username) {
		try {
			userDao.findUsername(username);
			return true;
		} catch (UsernameNotFound e) {
			return false;
		}
	}

	/**
	 * E-mail c�m ellen�rz�s, a kapott e-mail c�met ellen�rzi hogy v�laszhat�-e
	 * m�g a regisztr�ci� sor�n.
	 * 
	 * @param email
	 *            az ellen�rizend� e-mail c�m.
	 * @return hamis ha szabad a email c�m, igaz ha m�r nem.
	 */
	@Override
	public boolean isEmailAlreadyTaken(String email) {
		try {
			userDao.findEmail(email);
			return true;
		} catch (EmailNotFound e) {
			return false;
		}
	}

	@Override
	public Rank countRankFromXp(User user) {
		Rank userRank = null;
		if (user.getExperiencePoints() > -1 && user.getExperiencePoints() <= 2500) {
			userRank = Rank.Amatuer;
		} else if (user.getExperiencePoints() > 2500 && user.getExperiencePoints() <= 5000) {
			userRank = Rank.Sorfelelos;
		} else if (user.getExperiencePoints() > 5000) {
			userRank = Rank.Ivobajnok;
		}
		return userRank;
	}

	@Override
	public Integer countExperiencePointsInPercentage(Double experiencePoints) {

		Integer result = 0;

		if (experiencePoints > -1 && experiencePoints <= 2500) {
			result = (int) ((experiencePoints / 2500) * 100);
		} else if (experiencePoints > 2500 && experiencePoints <= 5000) {
			experiencePoints -= 2500;
			result = (int) ((experiencePoints / 2500) * 100);
		} else if (experiencePoints > 5000) {
			result = 100;
		}

		return result;
	}

	@Override
	public void transferMoney(String userPin, String expectedPin, Integer money, User loggedInUser)
			throws InvalidPinCode {
		if (!userPin.equals(expectedPin)) {
			throw new InvalidPinCode("PINs are not the same.");
		}
		loggedInUser.setMoney(loggedInUser.getMoney() + money);
		try {
			userDao.update(loggedInUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eventLogService.save(EventLogFactory.createEventLog(EventLogType.MoneyTransfer, loggedInUser));
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setEventLogService(EventLogService eventLogService) {
		this.eventLogService = eventLogService;
	}
}
