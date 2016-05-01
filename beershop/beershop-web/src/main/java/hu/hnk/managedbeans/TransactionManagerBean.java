package hu.hnk.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import hu.hnk.beershop.model.Cargo;
import hu.hnk.beershop.service.interfaces.CargoService;
import hu.hnk.beershop.service.interfaces.CartService;
import hu.hnk.loginservices.SessionManager;
import hu.hnk.tool.FacesMessageTool;

/**
 * A felhaszn�l�i tranzakci�kat kezel� managed bean, amely a felhaszn�l�
 * kosar�ban lev� dolgokat helyezi �j rendel�sre.
 * 
 * @author Nandi
 *
 */
@ManagedBean(name = "transactionManagerBean")
@ViewScoped
public class TransactionManagerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Az oszt�ly loggere.
	 */
	public static final Logger logger = Logger.getLogger(TransactionManagerBean.class);

	/**
	 * A kosarat kezel� szolg�ltat�s.
	 */
	@EJB
	private CartService cartService;

	/**
	 * 
	 */
	@EJB
	private CargoService cargoService;

	private FacesMessage msg;

	/**
	 * A sessiont kezel� managed bean.
	 */
	@ManagedProperty(value = "#{sessionManagerBean}")
	private SessionManager sessionManager;

	/**
	 * A v�s�rl� c�me, ahov� a term�keket sz�ll�tjuk majd.
	 */
	private String address;

	/**
	 * Fizet�si m�d, utal�ssal vagy b�nuszpontokkal.
	 */
	private String payMode;

	private Double totalCost;

	private Double moneyAfterPayment;

	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		totalCost = countTotalCost();
	}

	/**
	 * @return
	 */
	public Double countTotalCost() {
		Double totalCost = cartService.countTotalCost(sessionManager.getLoggedInUser()
				.getCart()
				.getItems());
		logger.info("Total cost:" + totalCost);
		return totalCost;
	}

	/**
	 * @return
	 */
	public boolean isThereEnoughMoney() {
		return cargoService.isThereEnoughMoney(sessionManager.getLoggedInUser());
	}

	/**
	 * 
	 */
	public void doTransaction() {
		if (isThereEnoughMoney()) {
			Cargo cargo = new Cargo();
			cargo.setItems(sessionManager.getLoggedInUser()
					.getCart()
					.getItems()
					.stream()
					.filter(p -> p.getActive())
					.collect(Collectors.toList()));
			cargo.setAddress(address);
			cargo.setOrderDate(new Date());
			cargo.setUser(sessionManager.getLoggedInUser());
			cargo.setTotalPrice(totalCost);
			cargo.setPaymentMode(payMode);
			try {
				cargoService.saveNewCargo(cargo);
				FacesMessageTool.createInfoMessage("Sikeres v�s�rl�s.");
			} catch (Exception e) {
				FacesMessageTool.createWarnMessage("Hiba t�rt�nt a fizet�s k�zben.");
			}

		} else {
			FacesMessageTool.createWarnMessage("Nem �ll rendelkez�s�re elegend� p�nz vagy pont.");

		}
	}

	public void countMoneyAfterPayment() {

	}

	/**
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param addres
	 */
	public void setAddress(String addres) {
		this.address = addres;
	}

	/**
	 * @return
	 */
	public String getPayMode() {
		return payMode;
	}

	/**
	 * @param payMode
	 */
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public SessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public CargoService getCargoService() {
		return cargoService;
	}

	public void setCargoService(CargoService cargoService) {
		this.cargoService = cargoService;
	}

	public FacesMessage getMsg() {
		return msg;
	}

	public void setMsg(FacesMessage msg) {
		this.msg = msg;
	}

	public Double getMoneyAfterPayment() {
		return moneyAfterPayment;
	}

	public void setMoneyAfterPayment(Double moneyAfterPayment) {
		this.moneyAfterPayment = moneyAfterPayment;
	}

}
