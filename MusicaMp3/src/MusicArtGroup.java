
public class MusicArtGroup extends MusicArt{
private String Discografia, Sito, DataForm, DataSciogl;
private String NomeGruppo;

/**
 * Costruttore che setta i dati iniziali vuoti
 */

public MusicArtGroup(String NomeGruppo){
	this.NomeGruppo=NomeGruppo;
	Discografia="";
	Sito="";
	DataForm="";
	DataSciogl="";
}
/**
 * Costruttore che inizializza i valori del gruppo di musicisti con 
 * i valori passati per parametro
 * 
 * @param Discografia
 * @param Sito
 * @param DataForm
 * @param DataSciogl
 */
public MusicArtGroup(String NomeGruppo,String Discografia,String Sito, String DataForm, String DataSciogl){
	this.NomeGruppo=NomeGruppo;
	this.Sito=Sito;
	this.DataForm=DataForm;
	this.DataSciogl=DataSciogl;
	this.Discografia=Discografia;
}
/**
 * funzione che restituisce il valore del nome del gruppo //DALLA CLASSE ASTRATTA...
 */

public String getName(){
	return NomeGruppo;
}

/**
 * Funzione che restituisce il valore del Sito internet
 * @return Sito
 */
public String getSito(){
	return Sito;
}

/**
 * funzione che restituisce il valore della Discografia
 * @return Discografia
 */
public String getDiscografia(){
	return Discografia;
}

/**
 * Funzione che restituisce il valore della data di formazione
 * del gruppo
 * 
 * @return DataNasc
 */
public String getDataForm(){
	return DataForm;
}

/**
 * funzione che restituisce il valore della data di scioglimento 
 * del gruppo
 * 
 * @return DataSciogl
 */
public String getDataSciogl(){
	return DataSciogl;
}

/////////////////////////////////////////// Funzioni di settaggio ///////////////////////////////////////////////
/**
 * Funzione che setta il nome del gruppo // DALLA CLASSE ASTRATTA
 *
 */

public void setName(String Nome){
	this.NomeGruppo= Nome;
}
/**
 * 
 * Funzione che setta il valore del Sito internet
 * 
 */
public void setSito(String Sito){
	this.Sito=Sito;
}

/**
 * Funzione che setta il valore della Discografia
 *
 */
public void setDiscografia(String Discografia){
	this.Discografia=Discografia;
}

/**
 * Funzione che setta il valore della data di formazione
 * del gruppo
 * 
 * 
 */
public void setDataForm(String DataForm){
	this.DataForm=DataForm;
}

/**
 * funzione che setta il valore della data di scioglimento 
 * del gruppo
 * 
 */
public void setDataSciogl( String DataSciogl){
	this.DataSciogl=DataSciogl;
}

}
