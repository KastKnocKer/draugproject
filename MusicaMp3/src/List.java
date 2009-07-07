
public interface List {
	/**
	 * Indica se la lista è vuota
	 * @return boolean
	 */
	public boolean isEmpty();
	/**
	 * Ritorna l'elemento in testa alla lista
	 * @return Object
	 */
	public Mp3 head();
	/**
	 * Ritorna la lista senza l'elemento in testa
	 * @return Lista
	 */
	public List tail();
	/**
	 * Ritorna l'ultimo elemento
	 * @return Object
	 */
	public Object last();
	/**
	 * Inserisce l'elemento dato nella lista
	 * @param Object
	 */
	public void insert(Mp3 b);
	/**
	 * Rimuove dalla lista l'elemento dato
	 * @param Object
	 */
	public void remove(Mp3 b);
	/**
	 * Ritorna il numero di elementi della lista
	 * @return Numero elementi lista
	 */
	public int size();
	/**
	 * Indica se la lista contiene l'oggetto passato
	 * @param Object
	 * @return boolean
	 */
	public boolean contains(Mp3 b);
	/**
	 * Restituisce l'elemento della posizione indicata
	 * @param row
	 * @return Object
	 */
	public Object getObjPos(int row);
	public void Svuota();
}
