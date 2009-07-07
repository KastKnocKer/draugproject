
public class MusicArtSol extends MusicArt{
	
private String Cognome, DataNasc, DataMorte,Nazione,Sito;
private String Nome,NomeArtista;
private Album Discografia;
/**
 * Costruttore che inizializza tutti i valori
 * della classe musicista solista al valore vuoto nella stringa
 */
public MusicArtSol(String Nome){
	this.Nome=Nome;
	NomeArtista="";
	Cognome="";
	DataNasc="";
	DataMorte="";
	Nazione="";
	Discografia=new Album();
	Sito="";
}

/** Costruttore che setta i dati iniziali del musicista
 * solista
 * 
 * @param Cognome
 * @param DataNasc
 * @param DataMorte
 * @param Nazione
 * @param Discografia
 */
public MusicArtSol(String Nome,String NomeArtista,String Cognome, String DataNasc, String DataMorte, String Nazione, Album Discografia,String Sito){
	this.Nome=Nome;
	this.NomeArtista=NomeArtista;
	this.Cognome=Cognome;
	this.DataNasc=DataNasc;
	this.DataMorte=DataMorte;
	this.Nazione=Nazione;
	this.Discografia=Discografia;
	this.Sito=Sito;
}

public String getNomeArte(){
	return Nome;
}


/**
 * Funzione che ritorna il valore del cognome del 
 * musicista solista
 * @return Cognome
 */
public String getCognome(){
	return Cognome;
}
/**
 * Funzione che ritorna il valore della data di nascita del 
 * musicista solista
 * @return Cognome
 */
public String getDataNasc(){
	return DataNasc;
}

/**
 * Funzione che ritorna il valore della data di morte del 
 * musicista solista
 * @return Cognome
 */
public String getDataMorte(){
	return DataMorte;
}

/**
 * Funzione che ritorna il valore della nazione del 
 * musicista solista
 * @return Cognome
 */
public String getNazione(){
	return Nazione;
}

/**
 * Funzione che ritorna il valore della discografia del 
 * musicista solista
 * @return Cognome
 */
public Album getDiscografia(){
	return Discografia;
}

/**
 * Funzione che ritorna il valore del Sito internet
 */
public String getSito(){
	return Sito;
}

/**
 * Funzione che setta il valore del nome
 */

public void setName(String Name){
	 super.setName(Name);
}
/**
 * Funzione che setta il valore del cognome
 */

public void setCognome(String Cognome){
	this.Cognome=Cognome;	
}

/**
 * Funzione che setta il valore della data di nascita
 */

public void setDataNasc(String DataNasc){
	this.DataNasc=DataNasc;	
}

/**
 * Funzione che setta il valore della data di morte
 */

public void setDataMorte(String DataMorte){
	this.DataMorte=DataMorte;	
}

/**
 * Funzione che setta il valore della Nazione
 */

public void setNazione(String Nazione){
	this.Nazione=Nazione;	
}

/**
 * Funzione che setta il valore della discografia
 */

public void setDiscografia(Album Discografia){
	this.Discografia=Discografia;	
}
/**
 * Funzione che setta il valore del Sito internet
 */

public void setSito(String Sito){
	this.Sito=Sito;	
}

}
