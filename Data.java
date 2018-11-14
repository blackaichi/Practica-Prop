package classes;

public class Data {
	/////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////
	
	/**
	 * Dia de la data 0..7
	 */
	int dia;
	
	/**
	 * Hora de la data 0..23
	 */
	int hora;
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de Data amb dia i hora com a paràmetres
	 * @param dia dia de la data
	 * @param hora hora de la data
	 * @throws Exception
	 */
	public Data(int dia, int hora) throws Exception {
		ExceptionManager.thrower(setDia(dia));
		ExceptionManager.thrower(setHora(hora));
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna a la data un dia
	 * @param dia int que diu el dia de la data
	 * @return 0 si s'ha fet correctament, altrament error
	 */
	public int setDia(int dia) {
		if (dia < 0 || dia > 7) return 220;
		this.dia = dia;
		return 0;
	}
	
	/**
	 * Assigna a la data una hora
	 * @param hora int que diu l'hora de la data
	 * @return 0 si s'ha fet correctament, altrament error
	 */
	public int setHora(int hora) {
		if (hora < 0 || hora > 7) return 221;
		this.hora = hora;
		return 0;
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna el dia de la data
	 * @return el dia de la data
	 */
	public int getDia() {
		return dia;
	}
	
	/**
	 * Retorna l'hora de la data
	 * @return l'hora de la data
	 */
	public int getHora() {
		return hora;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
	
}
