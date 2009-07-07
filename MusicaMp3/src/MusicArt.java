/**
 * Classe musicista che estende il concetto
 * di artista; aggiunge un array di albums che
 * contiene, appunto, tutti gli albums con i relativi
 * mp3 e il genere. 
 * 
*/

public class MusicArt extends Artisti{
	// Discografica musicista
	private Album Albums[];
	// Numero Album
	private int NAlbums;
	// Genere
	private String Genre;
	
	public MusicArt()
	{
		super();
		Albums = new Album[30];
		NAlbums = 0;
		this.Genre = "";
	}
	
	public MusicArt(String Name, String Album, String Genre, String Year)
	{
		super();
		super.setName(Name);
		this.Albums = new Album[30];
		this.Albums[0] = new Album(Album, Genre, Year);
		NAlbums++;
		this.Genre = "";
	}
	
	/**
	 * Setta un nuovo album per l'artista musicale.
	 * 
	 * @param Name Nome dell'album
	 * @param Genre Genere album.
	 * @param Year Anno dell'album.
	*/
	public void setNewAlbum(String Name, String Genre, String Year)
	{
	
			this.Albums[NAlbums] = new Album(Name, Genre, Year);
			NAlbums++;
		
	}
	
	
	
	/**
	 * Restitusice il nome d'arte dell'artista.
	 */
	public String getName()
	{
		return super.getName();
	}
	/**
	 * Restituisce il genere dell'artista musicale.
	 */
	public String getGenre()
	{
		return this.Genre;
	}
	/**
	 * Restituisce il numero di albums dell'artista musicale.
	 */
	public int getNAlbums()
	{
		return this.NAlbums;
	}
	/**
	 * Restituisce il primo album.
	 */
	public Album getFirstAlbum()
	{
		return this.Albums[0];
	}
	/**
	 * Restituisce l'album corrispondente all'indice.
	 * 
	 * @param i Indice dell'album.
	 */
	public Album getAlbum(int i)
	{
		return this.Albums[i];
	}
	
	/**
	 * Setta il genere all'artista musicale.
	 * 
	 * @param Genre Genere.
	 */
	public void setGenre(String Genre)
	{
		this.Genre = Genre;
	}
	
	/**
	 * Setta un nuovo album alla posizione i.
	 * 
	 * @param Name Nome album.
	 * @param i Posizione album.
	 */
	public void setAlbumName(String Name, int i)
	{
		this.Albums[i].setName(Name);
	}
	/**
	 * Cerca un album (partendo dal nome) all'interno
	 * dell'artista e ne resituisce, in caso positivo,
	 * la posizione.
	 * 
	 * @param	Name Nome dell'album da ricercare.
	 * @return 	Posizione dell'album; se non lo trova restituisce
	 * 		   	-1;
	 */
	public int searchAlbum(String Name)
	{
		for(int i=0; i < this.NAlbums; i++)
		{
			if ( this.Albums[i].getName().equals(Name) )
				return i;
		}
		return -1;
	}
}
