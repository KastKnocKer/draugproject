import java.io.Serializable;
/** 
 * Classe per la gestione degli albums nella libreria;
 * ogni oggetto ha un nome, un genere
 *  un anno 
 */

public class Album implements Serializable {
	private String Nome;
	// Genere album
	private String Genre;
	// Anno album
	private String Year;
	
	/**
	 * Costruttore senza parametri, azzera gli attributi.
	 */
	public Album(){
		this.Nome = "";
		this.Genre = "";
		this.Year = "";
	}
	
	/**
	 * Procede a settare i parametri nei relativi attributi.
	 */
	public Album(String Nome, String Genre, String Year)
	{
		if ( Nome == "" )
			Nome = "Sconosciuto";
		this.Nome = Nome;
		if ( Genre == "" )
			Genre = "Sconosciuto";
		this.Genre = Genre;
		this.Year = Year;
	}
	/**
	 * Setta il nome dell'album.
	 * 
	 * @param Name Nome dell'album
	 */
	public void setName(String Nome)
	{
		this.Nome = Nome;
	}
	
	
	/**
	 * Setta il genere dell'album.
	 * 
	 * @parama Genre Genere dell'album.
	 */
	public void setGenre(String Genre)
	{
		this.Genre = Genre;
	}
	/**
	 * Setta l'anno dell'album.
	 * 
	 * @param Year Anno dell'album.
	 */
	public void setYear(String Year)
	{
		this.Year = Year;
	}
	
	/**
	 * Restituisce il nome dell'album.
	 */
	public String getName()
	{
		return this.Nome;
	}
	
	/**
	 * Restituisce il genere dell'album.
	 */
	public String getGenre()
	{
		return this.Genre;	
	}
	/**
	 * Restituisce l'anno.
	 */
	public String getYear()
	{
		return this.Year;	
	}
}
