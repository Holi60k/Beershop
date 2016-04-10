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

import hu.hnk.beershop.exception.EmailNotFound;
import hu.hnk.beershop.exception.UsernameNotFound;
import hu.hnk.beershop.model.Role;
import hu.hnk.beershop.model.User;
import hu.hnk.beershop.service.interfaces.UserService;
import hu.hnk.interfaces.RoleDao;
import hu.hnk.interfaces.UserDao;

/**
 * A felhaszn�l�i szolg�lat�sokkal foglalkoz� oszt�ly. Enterprise Java Bean.
 * 
 * @author Nandi
 * 
 */
@Stateless(name = "userService")
@Local(UserService.class)
public class UserServiceImpl implements UserService {

	@EJB
	private UserDao userDao;

	@EJB
	RoleDao roleDao;

	/**
	 * A felhaszn�l� ment�se.
	 * 
	 * @param user
	 *            A mentend� felhaszn�l�.
	 */
	public void save(User user) {
		Role role = roleDao.findByName("ROLE_USER");

		if (role == null) {
			System.out.println("Role null->ROLE_USER");
			role.setName("ROLE_USER");
			role = roleDao.save(role);
		}

		User userData = getUserDao().save(user);
		List<Role> userRoles = userData.getRoles();

		if (userRoles == null || userRoles.isEmpty()) {
			userRoles = new ArrayList<>();
		}

		userRoles.add(role);
		userData.setRoles(userRoles);

		getUserDao().save(userData);
		System.out.println("UserService after save!");
	}

	/**
	 * Ellen�rzi hogy a regisztr�land� felhaszn�l� sz�let�si d�tuma alapj�n
	 * elm�lt-e m�r 18 �ves.
	 * 
	 */
	public boolean isOlderThanEighteen(Date dateOfBirth) {
		LocalDate now = LocalDate.now();
		Instant instant = Instant.ofEpochMilli(dateOfBirth.getTime());
		LocalDate birth = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		return birth.until(now).getYears() > 17;
	}

	@Override
	public User findByUsername(String username) {
		User user = null;
		try {
			user = getUserDao().findByUsername(username);
		} catch (UsernameNotFound e) {

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
			String user = getUserDao().findUsername(username);
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
			String user = getUserDao().findEmail(email);
			return true;
		} catch (EmailNotFound e) {
			return false;
		}
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
