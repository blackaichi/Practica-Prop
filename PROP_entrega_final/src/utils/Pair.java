package utils;

/**
 * 
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 * @param <F> Primer element del Pair.
 * @param <S> Segon element del Pair.
 */
public class Pair<F, S> {
	/**
	 * Conté el primer element del pair.
	 */
	public F first;
	/**
	 * Conté el segon element del pair.
	 */
	public S second;
	
	/**
	 * Constructora simple de la classe Pair.
	 */
	public Pair() {
		this.first = null;
		this.second = null;
	}
	
	/**
	 * Constructora de la classe Pair
	 * @param first És el valor que ha de pendre per defecte el primer element.
	 * @param second És el valor que ha de pendre per defecte el segon element.
	 */
	public Pair(F first, S second){
		this.first = first;
		this.second = second;
	}

	public boolean isNull() {
		return this.first == null && this.second == null;
	}
	
	public boolean fnull() {
		return this.first == null;
	}
	
	public boolean snull() {
		return this.second == null;
	}
}
