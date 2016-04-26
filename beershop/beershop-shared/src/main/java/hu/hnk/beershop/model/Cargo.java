package hu.hnk.beershop.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 * Egy rendel�s adatait tartalmaz� oszt�ly.
 * 
 * @author Nandi
 *
 */
// @Table(name = "cargo")
@Entity
public class Cargo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4761818681252091051L;

	/**
	 * A rendelt s�r�k list�ja.
	 */
	@ManyToMany
	private List<CartItem> items;

	/**
	 * A felhaszn�l� aki leadta a rendel�st.
	 */
	@OneToOne
	private User user;

	/**
	 * A rendel�s lead�s�nak d�tuma.
	 */
	@Column(name = "dateOfOrder")
	private Date orderDate;

	/**
	 * A rendel�s teljes �sszege.
	 */
	@Column(name = "totalprice")
	private Double totalPrice;

	/**
	 * A rendel� vend�g sz�ll�t�si c�me.
	 */
	@Column(name = "address")
	private String address;

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate
	 *            the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the totalPrice
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice
	 *            the totalPrice to set
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
