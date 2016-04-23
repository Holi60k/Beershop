package hu.hnk.managedbeans;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.hnk.beershop.model.Cart;
import hu.hnk.beershop.model.User;
import hu.hnk.beershop.service.interfaces.UserService;
import hu.hnk.tool.FacesMessageTool;

/**
 * A regisztr�ci�s szolg�ltat�st megval�s�t� managed bean.
 * 
 * @author Nandi
 *
 */
@ManagedBean(name = "registrationManagerBean")
@ViewScoped
public class RegistrationManagerBean implements Serializable {

	/**
	 * Az oszt�ly loggere.
	 */
	public static final Logger logger = Logger.getLogger(RegistrationManagerBean.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 2856400278569714670L;

	/**
	 * A felhaszn�l�kat kezel� szolg�ltat�s.
	 */
	@EJB
	private UserService userService;

	private FacesMessage msg;

	/**
	 * A jelszavak titkos�t�s�hoz haszn�lt BCryptPasswordEncoder.
	 */
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	/**
	 * A v�lasztott felhaszn�l�n�v.
	 */
	private String username;

	/**
	 * A v�lasztott jelsz�.
	 */
	private String password;

	/**
	 * A v�lasztott jelsz� �jabb megad�sa elg�pel�si c�lok miatt.
	 */
	private String rePassword;

	/**
	 * A v�lasztott e-mail c�m.
	 */
	private String email;

	/**
	 * A felhaszn�l� sz�let�si d�tuma.
	 */
	private Date dateOfBirth;

	/**
	 * Szabad-e m�g a felhaszn�l�n�v.
	 */
	private Boolean isUsernameFree = true;

	/**
	 * Szabad-e m�g az e-mail c�m.
	 */
	private Boolean isEmailFree = true;

	/**
	 * Bet�lt�tte-e m�r a 18. �let�v�t.
	 */
	private Boolean isOlderThanEighteen = true;

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the rePassword
	 */
	public String getRePassword() {
		return rePassword;
	}

	/**
	 * @param rePassword
	 *            the rePassword to set
	 */
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Felhaszn�l� ment�se gomb esem�nye.
	 * 
	 * @param actionEvent
	 *            maga az esem�ny.
	 */
	public void saveUser(ActionEvent actionEvent) {
		Boolean canRegister = isOlderThanEighteen && isEmailFree && isUsernameFree;
		if (canRegister) {
			User newUser = new User();
			logger.info("Ment�s gomb lenyomva.");
			newUser = new User();
			newUser.setPassword(encoder.encode(password));
			newUser.setUsername(username);
			newUser.setEmail(email);
			newUser.setPoints((double) 0);
			newUser.setDateOfBirth(dateOfBirth);
			newUser.setExperiencePoints((double) 0);
			newUser.setMoney((long) 0);
			newUser.setCart(new Cart());
			if (newUser != null) {
				try {
					userService.save(newUser);
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres regisztr�ci�.", "");
					FacesContext.getCurrentInstance()
							.getExternalContext()
							.redirect("index.xhtml");
				} catch (Exception e) {
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba regisztr�ci� k�zben.", "Hiba!");
				}
			}
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Regisztr�ci� nem lehets�ges.", "Hiba!");
		}
		FacesMessageTool.publishMessage(msg);
	}

	/**
	 * A regisztr�ci� sor�n a felhaszn�l�i n�v foglalt�s�g�t vizsg�l� met�dus.
	 */
	public void usernameListener() {
		if (userService.isUsernameAlreadyTaken(username)) {
			logger.info("Felhaszn�l�n�v m�r foglalt!");
			FacesContext.getCurrentInstance()
					.addMessage("registration:username", new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Ez a felhaszn�l�n�v m�r foglalt!", "Ez a felhaszn�l�n�v m�r foglalt!"));
			isUsernameFree = false;
		} else {
			isUsernameFree = true;
		}
	}

	/**
	 * A regisztr�ci� sor�n az e-mail c�m foglalt�s�g�t vizsg�l� met�dus.
	 */
	public void emailListener() {
		if (userService.isEmailAlreadyTaken(email)) {
			logger.info("E-mail c�m m�r foglalt!");
			FacesContext.getCurrentInstance()
					.addMessage("registration:email", new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Ez az e-mail c�m m�r foglalt!", "Ez az e-mail c�m m�r foglalt!"));
			isEmailFree = false;
		} else {
			isEmailFree = true;
		}
	}

	/**
	 * A regisztr�ci� sor�n a 18. �let�v�t bet�lt�tt regisztr�l� valid�l�s��rt
	 * felel� met�dus.
	 */
	public void ageListener() {
		if (!userService.isOlderThanEighteen(dateOfBirth)) {
			logger.info("Csak 18 �v f�l�tt lehets�ges a regisztr�ci�.");
			FacesContext.getCurrentInstance()
					.addMessage("registration:dateOfBirth", new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Csak 18 f�l�tt lehets�ges a regisztr�ci�.", "Csak 18 f�l�tt lehets�ges a regisztr�ci�."));
			isOlderThanEighteen = false;
		} else {
			isOlderThanEighteen = true;
		}
	}

}
