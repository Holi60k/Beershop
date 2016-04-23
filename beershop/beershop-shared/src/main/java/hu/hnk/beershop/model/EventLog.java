package hu.hnk.beershop.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Egy esem�nynek a logj�t le�r� entit�st.
 * 
 * @author Nandi
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = "EventLog.findByUser", query = "SELECT e FROM EventLog e WHERE user = :user") })
public class EventLog extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2757899869474041195L;

	/**
	 * Az esem�nynek az akci�ja, azaz mi t�rt�nt, egy esetleges v�s�rl�s vagy
	 * p�nzfelt�lt�s.
	 */
	@Column(name = "action")
	private String action;

	/**
	 * Az esem�nyben r�sztvev� felhaszn�l�.
	 */
	@ManyToOne
	private User user;

	/**
	 * Az esem�ny d�tuma.
	 */
	private LocalDateTime date;

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
