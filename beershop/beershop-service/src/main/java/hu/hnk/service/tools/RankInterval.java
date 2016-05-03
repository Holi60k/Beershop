package hu.hnk.service.tools;

import java.util.ArrayList;
import java.util.List;

import hu.hnk.beershop.model.Rank;

/**
 * A rangok tapasztalatponthoz k�t�tt inform�ci�it le�r� oszt�ly.
 * 
 * @author Nandi
 *
 */
public class RankInterval {

	/**
	 * A rang amit a minimum illetve maximum tapasztalatpont hat�roz meg.
	 */
	private Rank rank;
	/**
	 * A minim�lis tapasztalatpont.
	 */
	private Integer minimumXP;
	/**
	 * A maxim�lis tapasztalatpont.
	 */
	private Integer maximumXP;

	/**
	 * A rangokat tartalmaz� statikus lista.
	 */
	private static List<RankInterval> rankIntverals;

	/**
	 * Az oszt�ly konstuktora.
	 * 
	 * @param rank
	 *            a szab�lyozand� rang.
	 * @param minimumXP
	 *            a minimum tapasztalatpont.
	 * @param maximumXP
	 *            a maximum tapasztalatpont.
	 */
	public RankInterval(Rank rank, Integer minimumXP, Integer maximumXP) {
		super();
		this.rank = rank;
		this.minimumXP = minimumXP;
		this.maximumXP = maximumXP;
	}

	static {
		rankIntverals = new ArrayList<>();
		rankIntverals.add(new RankInterval(Rank.Amatuer, 0, 2500));
		rankIntverals.add(new RankInterval(Rank.Sorfelelos, 2500, 5000));
		rankIntverals.add(new RankInterval(Rank.Ivobajnok, 5000, 7500));
		rankIntverals.add(new RankInterval(Rank.Sormester, 7500, 10000));
		rankIntverals.add(new RankInterval(Rank.Sordoktor, 10000, 12500));
		rankIntverals.add(new RankInterval(Rank.Legenda, 12500, 15000));
	}

	/**
	 * Visszadja a rangot;
	 * 
	 * @return a rang.
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * Visszaadja a minim�lis tapasztalatpontot.
	 * 
	 * @return a minim�lis tapasztalatpont.
	 */
	public Integer getMinimumXP() {
		return minimumXP;
	}

	/**
	 * Visszaadja a maxim�lis tapasztalatpontot.
	 * 
	 * @return a maxim�lis tapasztalatpont.
	 */
	public Integer getMaximumXP() {
		return maximumXP;
	}

	/**
	 * Visszaadja a m�r meghat�rozott rangok list�j�t.
	 * 
	 * @return a meghat�rozott rangok list�ja.
	 */
	public static List<RankInterval> getRankIntverals() {
		return rankIntverals;
	}

}
