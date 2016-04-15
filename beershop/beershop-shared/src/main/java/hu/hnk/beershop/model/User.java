package hu.hnk.beershop.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * A felhaszn�l� oszt�ly tartalmazza egy felhaszn�l� adatait.
 * 
 * @author Nandi
 *
 */
// @Table(name = "user")
@Entity
@NamedQueries({ @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :name"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
		@NamedQuery(name = "User.findUsername", query = "SELECT u.username FROM User u WHERE u.username = :name"),
		@NamedQuery(name = "User.findEmail", query = "SELECT u.email FROM User u WHERE u.email = :email") })
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8256608378786882228L;

	/**
	 * A felhaszn�l� egyedi azonos�t�ja.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/**
	 * A felhaszn�l� felhaszn�l�neve.
	 */
	@Column(name = "username", nullable = false)
	private String username;

	/**
	 * A felhaszn�l� jelszava.
	 */
	@Column(name = "password", nullable = false)
	private String password;

	/**
	 * A felhaszn�l� jogk�rei.
	 */
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<Role> roles;

	/**
	 * A felhaszn�l� e-mail c�me.
	 */
	@Column(name = "email")
	private String email;

	/**
	 * A felhaszn�l� pontjai.
	 */
	@Column(name = "points")
	private Double points;

	/**
	 * A felhaszn�l� sz�let�si d�tuma.
	 */
	@Column(name = "dateOfBirth")
	private Date dateOfBirth;

	/**
	 * A felhaszn�l� tapasztalati pontja, amivel t�bb kedvezm�nyt illetve a
	 * rangj�t ez alapaj�n sz�moljuk majd ki.
	 */
	@Column(name = "experiencePoints", nullable = false, columnDefinition = "Decimal(10,2) default '0.0'")
	private Double experiencePoints;

	/**
	 * A felhaszn�l� saj�t kosara amelybe term�keket tud majd elhelyezni.
	 */
	@OneToOne
	private Cart cart;

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
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the points
	 */
	public Double getPoints() {
		return points;
	}

	/**
	 * @param points
	 *            the points to set
	 */
	public void setPoints(Double points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + ", email="
				+ email + ", points=" + points + ", dateOfBirth=" + dateOfBirth + "]";
	}

	public Double getExperiencePoints() {
		return experiencePoints;
	}

	public void setExperiencePoints(Double experiencePoints) {
		this.experiencePoints = experiencePoints;
	}

	/**
	 * @return the cart
	 */
	public Cart getCart() {
		return cart;
	}

	/**
	 * @param cart
	 *            the cart to set
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	// /**
	// * @return the rank
	// */
	// public Rank getRank() {
	// return rank;
	// }
	//
	// /**
	// * @param rank
	// * the rank to set
	// */
	// public void setRank(Rank rank) {
	// this.rank = rank;
	// }

}
