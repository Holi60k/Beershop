package hu.hnk.persistenceunit;

/**
 * A persistence.xml f�jlhoz tartoz� Java deklar�ci�s f�jl, amiben �ll�that� a
 * PersistenceContext sz�m�ra haszn�land� perzisztens egys�g hivatkoz�sa.
 * 
 * @author Nandi
 *
 */
public class PersistenceUnitDeclaration {

	/**
	 * A MySQL-es be�ll�t�sra vonatkoz� konstans.
	 */
	public final static String MYSQL = "BeerShopUnit";

	/**
	 * Az Apache Derby-s be�ll�t�sra vonatkoz� konstans.
	 */
	public final static String DERBY = "BeerShopUnitDerby";
	
	/**
	 * 
	 */
	public final static String ORACLE = "BeerShopUnitOra";
	
	/**
	 * Az aktu�lisan haszn�land� perzisztens egys�g neve.
	 */
	public final static String PERSISTENCE_UNIT = MYSQL;

}
