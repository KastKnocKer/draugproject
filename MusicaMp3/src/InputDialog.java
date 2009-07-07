
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import org.jaudiotagger.audio.exceptions.*;
import org.jaudiotagger.tag.*;


public class InputDialog extends JOptionPane implements PropertyChangeListener{
private Object [] campi;
private String [] opzioni;
private Lista lista,l;
int result,row;
private Mp3 Brano;
SpinnerNumberModel spinModel;

private JTable t;
private int i = 0;
private JComboBox FiltroGenere,FiltroArtista,FiltroAlbum,FiltroAnno;


public InputDialog(Lista l,Mp3 Brano,int row,JTable t,JComboBox FiltroGenere,JComboBox FiltroArtista,JComboBox FiltroAlbum,JComboBox FiltroAnno){
	this.l=l;
	this.Brano=Brano;
	this.row=row;
	this.t=t;
	this.FiltroGenere=FiltroGenere;
	this.FiltroArtista=FiltroArtista;
	this.FiltroAlbum=FiltroAlbum;
	this.FiltroAnno=FiltroAnno;
	
	campi = new Object[21];
	campi[0]= new JLabel("Artista");
	campi[1]= new JTextField();
	
	campi[2]= new JLabel("Titolo");
	campi[3]= new JTextField();
	
	campi[4]=new JLabel("Album");
	campi[5]=new JTextField();
	
	campi[6]= new JLabel("Genere");
	campi[7]=new JTextField();
	
	campi[8]= new JLabel("Bitrate");
	campi[9]=new JTextField();
	((JTextField)campi[9]).setEditable(false);
	
	campi[10]= new JLabel("Sample Rate");
	campi[11]=new JTextField();
	((JTextField)campi[11]).setEditable(false);
	
	campi[12]= new JLabel("Channels");
	campi[13]=new JTextField();
	((JTextField)campi[13]).setEditable(false);
	
	campi[14]= new JLabel("Anno");
	campi[15]=new JTextField();
	
	campi[16]= new JLabel("Numero Traccia");
	campi[17]=new JTextField();
	
	campi[19]=new JLabel("Testo Canzone");
	campi[20]=new JTextArea();
	
	
	opzioni = new String[2];
	opzioni[0] = new String("Salva");
	opzioni[1]= new String("Annulla");
	
}

public void showInputDialog() throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException{
	((JTextField)campi[1]).setText(Brano.GetArtista());
	((JTextField)campi[3]).setText(Brano.GetTitolo());
	((JTextField)campi[5]).setText(Brano.GetAlbum());
	((JTextField)campi[7]).setText(Brano.GetGenere());
	((JTextField)campi[9]).setText(Integer.toString(Brano.GetBitRate()));
	((JTextField)campi[11]).setText(Integer.toString(Brano.GetSampleRate()));
	((JTextField)campi[13]).setText(Brano.GetChannels());
	((JTextField)campi[15]).setText(Brano.GetAnno());
	((JTextField)campi[17]).setText(Brano.GetNumeroTraccia()/*Integer.toString(row+1)*/);
	((JTextArea)campi[20]).setText(Brano.getLyrics());
	
	
	// Per ridurre l'icona.. 
	ImageIcon image = new ImageIcon();
	image = Brano.getArtwork();
	Image img2,img = image.getImage();
	img2=img.getScaledInstance(200, 200, 0);

	int width=img.getWidth(null);
	int height=img.getHeight(null);
	if((width>100)||(height>70)){
		ImageIcon newIcon = new ImageIcon(img2);
		result=JOptionPane.showOptionDialog(null, campi, "Proprietà: " + Brano.GetTitolo(), JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, newIcon, opzioni, opzioni[0]);
	}
	else
		result=JOptionPane.showOptionDialog(null, campi, "Proprietà: " + Brano.GetTitolo(), JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, image, opzioni, opzioni[0]);
	
	if(result==0){
		
		//setta artista
		if(!((JTextField)campi[1]).getText().equals(((Mp3)l.getObjPos(row)).GetArtista())){
			/////////////////////////////////////////////////////////////////////
			//SETTAGGIO ARTISTA
			((Mp3)l.getObjPos(row)).SetArtista(((JTextField)campi[1]).getText());
	
		}
		//Setta titolo
		if(!((JTextField)campi[3]).getText().equals(((Mp3)l.getObjPos(row)).GetTitolo())){
			((Mp3)l.getObjPos(row)).SetTitolo(((JTextField)campi[3]).getText());
		}
		//setta Album
		if(!((JTextField)campi[5]).getText().equals(((Mp3)l.getObjPos(row)).GetAlbum())){
			((Mp3)l.getObjPos(row)).SetAlbum(((JTextField)campi[5]).getText());
		}
		//setta Genere
		if(!((JTextField)campi[7]).getText().equals(((Mp3)l.getObjPos(row)).GetGenere())){
			((Mp3)l.getObjPos(row)).SetGenere(((JTextField)campi[7]).getText());
		}
		//setta Anno
		if(!((JTextField)campi[15]).getText().equals(((Mp3)l.getObjPos(row)).GetAnno())){
			((Mp3)l.getObjPos(row)).SetAnno(((JTextField)campi[15]).getText());
		}
		
		//settaNumeroTraccia
		if(!((JTextField)campi[17]).getText().equals(((Mp3)l.getObjPos(row)).GetNumeroTraccia())){
			((Mp3)l.getObjPos(row)).SetNumeroTraccia(((JTextField)campi[17]).getText());
		}
		
		

		l.salvaLista();
		Lista tmpL = new Lista();
		Lista tmpL1 = new Lista();
		try {
			tmpL.leggiLista();
			tmpL.salvaLista();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			tmpL1.leggiLista();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Mp3TableModel dataModeltmp = new Mp3TableModel(tmpL1);
		t.setModel(dataModeltmp);

	
		
	
	}

	}
	
	
public void propertyChange(PropertyChangeEvent e) {
        }

}

