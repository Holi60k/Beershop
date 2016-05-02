package hu.hnk.beershop.model;

/**
 * A rank enumer�ci� amely az egyes felhaszn�l�k rangjai lehetnek.
 * 
 * @author Nandi
 *
 */
public enum Rank {
	/**
	 * Amat�r.
	 */
	Amatuer(10L),

	/**
	 * S�rfelel�s.
	 */
	Sorfelelos(20L),

	/**
	 * S�rmester.
	 */
	Sormester(30L),

	/**
	 * Ivobajnok.
	 */
	Ivobajnok(40L),

	/**
	 * S�rdoktor.
	 */
	Sordoktor(50L),

	/**
	 * Legenda.
	 */
	Legenda(60L);

	/**
	 * A rank �rt�ke.
	 */
	private Long value;

	/**
	 * Konstuktor, mely l�trehoz egy Rang objektumot a megadott �rt�kkel.
	 * 
	 * @param value
	 *            a rank �rt�ke
	 */
	Rank(Long value) {
		this.value = value;
	}

	/**
	 * Megadja egy v�lasztott �rt�k rangj�t.
	 * 
	 * @param value
	 *            a megadott �rt�k
	 * @return a hozz� tartoz� Rank
	 */
	public static Rank getValue(Long value) {
		for (Rank item : Rank.values()) {
			if (item.value == value) {
				return item;
			}
		}
		return null;
	}

}
