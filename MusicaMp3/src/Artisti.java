import java.io.Serializable;

/**
 * Classe che gestisce gli Artisti; implementa
 * l'interfaccia serializable per poter essere 
 * salvata sul file della libreria.
 */

public class Artisti implements Serializable{
	// Nome artista
	private String Nome;
	// Biografia
	private String Bio;
	// Data d'esordio
	private String Esordio;
	
	/**
	 * Costruttore che inizializza i campi;
	 * ovviamente tutti 'Sconosciuti'.
	 */
	public Artisti()
	{
		Nome = "";
		Bio = "";
		Esordio = "";
	}
	/**
	 * Restituisce il nome dell'artista.
	 */
	public String getName()
	{
		return this.Nome;
	}
	/**
	 * Restituisce la sua biografia.
	 */
	public String getBio()
	{
		return this.Bio;	
	}
	/**
	 * Restituisce il suo esordio.
	 */
	public String getEsordio()
	{
		return this.Esordio;
	}
	
	/**
	 * Setta il nome all'artista.
	 * 
	 * @param Name Nome artista.
	 */
	public void setName(String Nome)
	{
		this.Nome = Nome;
	}
	/**
	 * Imposta la sua biografia.
	 * 
	 * @param Bio Biografia.
	 */
	public void setBio(String Bio)
	{
		this.Bio = Bio;
	}
	/**
	 * Imposta il suo esordio.
	 * 
	 * @param Esord Esordio artista.
	 */
	public void setEsord(String Esordio)
	{
		this.Esordio = Esordio;
	}
	
}
