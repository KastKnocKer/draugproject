import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class SchedaAlbumDialog extends JOptionPane implements PropertyChangeListener{

	private String [] opzioni;
	private Object[] campi;
	private int result;
	private String Artista;
	private JComboBox AlbumBox;
	private Album album;
	
	public SchedaAlbumDialog(String Artista, JComboBox AlbumBox, Album album){
		this.Artista=Artista;
		this.AlbumBox = AlbumBox;
		this.album=album;
		
		campi = new Object[6];
		campi[0] = new JLabel("Nome dell'Album");
		campi[1] = new JTextField();
		
		campi[2] = new JLabel("Genere Album");
		campi[3] =  new JTextField();
		
		campi [4] = new JLabel("Anno dell'Album");
		campi [5] = new JTextField();
		
		opzioni = new String[2];
		opzioni[0] = new String("Salva");
		opzioni[1]= new String("Annulla");
	}
	
	public void showInputDialog(){
		((JTextField)campi[1]).setText(album.getName());
		((JTextField)campi[3]).setText(album.getGenre());
		((JTextField)campi[5]).setText(album.getYear());
		
		result=JOptionPane.showOptionDialog(null, campi, "Aggiungi Album a " + Artista, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, opzioni, opzioni[0]);
		
		if(result==0){
			
			//SETTA NOME ALBUM, se è diverso dall'attuale
			if(!((JTextField)campi[1]).getText().equals(album.getName())){
				album.setName(((JTextField)campi[1]).getText());
			}
			
			if(!((JTextField)campi[3]).getText().equals(album.getName())){
				album.setGenre(((JTextField)campi[3]).getText());
			}
			if(!((JTextField)campi[5]).getText().equals(album.getName())){
				album.setYear(((JTextField)campi[5]).getText());
			}
			
			AlbumBox.addItem(album.getName());
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
