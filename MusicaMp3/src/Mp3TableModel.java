import java.io.Serializable;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class Mp3TableModel extends AbstractMp3Model {

	private static Object obj;
	
	//intestazioni delle colonne
	String[] ColName = {"#ID","Percorso","Artista","Album","Titolo","Numero Traccia","Genere","Anno" };
	
	//inizializzato con il vettore
	public Mp3TableModel(Lista l){
		super(l);
	}
	

	// il numero di colonne
	public int getColumnCount()
	{ return ColName.length; }
	
	// numero righe = dimensione della lista
	public int getRowCount() { return list.size();}
	
	/** 
	 * Specifica la visualizzazione delle informazioni degli mp3 per riga.
	 * @param riga
	 * @param colonna
	 * @return object, mp3 che dev'essere visualizzato nella singola cella.
	 */

	public Object getValueAt (int row, int col){
		obj = new Object();
		// seleziona gli mp3 al'interno della lista
		obj = list.getObjPos(row);
		
		// la stringa corrispondente alla colonna
		switch (col){
		case 0: return row+1;
		case 1: return ((Mp3) obj).GetPercorso();
		case 2: return ((Mp3) obj).GetArtista(); 
		case 3: return ((Mp3) obj).GetAlbum();
		case 4: return ((Mp3) obj).GetTitolo();
		case 5: return ((Mp3) obj).GetNumeroTraccia();
		case 6: return ((Mp3)obj).GenereDaNumero();
					//((Mp3) obj).GetGenere();
		case 7: return ((Mp3) obj).GetAnno();
		default: return "***";}
		}
	// ritorna il nome della colonna
	public String getColumnName(int col) {
	return ColName[col];
	}
	
	// specifica se le celle sono editabili
	public boolean isCellEditable(int row, int col)
	{
	// nessuna cella editabile
	return false;
	}
	
	
	
}
