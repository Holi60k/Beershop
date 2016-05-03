package hu.hnk.service.tools;

import java.util.ArrayList;
import java.util.List;

import hu.hnk.beershop.model.Rank;

/**
 * Az egyenlegfelt�lt�sn�l val� korl�toz�sokat megad� oszt�ly. A korl�toz�sokat
 * egy List�ban t�roljuk, {@value MoneyTransferRestrictions#moneyRestrictions}.
 * 
 * @author Nandi
 *
 */
public class MoneyTransferRestrictions {

	/**
	 * A szab�ly rangja.
	 */
	private Rank rank;

	/**
	 * A korl�tozott mennyis�g.
	 */
	private Integer restrictedValue;

	/**
	 * A szab�lyokat tartalmaz� statikus lista.
	 */
	static List<MoneyTransferRestrictions> moneyRestrictions;

	static {
		moneyRestrictions = new ArrayList<>();
		moneyRestrictions.add(new MoneyTransferRestrictions(Rank.Amatuer, 3));
		moneyRestrictions.add(new MoneyTransferRestrictions(Rank.Sorfelelos, 4));
		moneyRestrictions.add(new MoneyTransferRestrictions(Rank.Ivobajnok, 4));
		moneyRestrictions.add(new MoneyTransferRestrictions(Rank.Sormester, 5));
		moneyRestrictions.add(new MoneyTransferRestrictions(Rank.Sordoktor, 8));
	}

	/**
	 * A szab�lyt l�trehoz� oszt�ly konstuktora.
	 * 
	 * @param rank
	 *            a rank amihez k�tj�k a szab�lyt.
	 * @param restrictedValue
	 *            a szab�ly korl�tozott sz�ma.
	 */
	public MoneyTransferRestrictions(Rank rank, Integer restrictedValue) {
		super();
		this.rank = rank;
		this.restrictedValue = restrictedValue;
	}

	/**
	 * Visszaadja a rankot.
	 * 
	 * @return a rank.
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * Visszaadja a korl�tozott �rt�ket.
	 * 
	 * @return a korl�tozott �rt�k.
	 */
	public Integer getRestrictedValue() {
		return restrictedValue;
	}

	/**
	 * Visszaadja a korl�toz�sokat tartalmaz� list�t.
	 * 
	 * @return a korl�toz�sokat tartalmaz� lista.
	 */
	public static List<MoneyTransferRestrictions> getMoneyRestrictions() {
		return moneyRestrictions;
	}

}
