import java.awt.*;
import javax.swing.*;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
public class EventiMyFrame implements ActionListener{
private JMenuItem esci,aggiungiCartella,aggiungiMp3;
private JOptionPane pane = new JOptionPane();
private File f,c;
private Mp3 Brano;

public EventiMyFrame(JMenuItem esci,JMenuItem aggiungiCartella){
	this.esci = esci;
	this.aggiungiCartella = aggiungiCartella;
	this.aggiungiMp3 = aggiungiMp3;
	}
public void actionPerformed (ActionEvent e){

    if (e.getSource()==esci){

    //Pannello per chiedere conferma di uscire
    int a = pane.showConfirmDialog(null,"Uscire dal programma?", "Vuoi veramente uscire?", 
            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

            if(a==JOptionPane.OK_OPTION)
            {System.exit(0);}
            }
    //Evento per aprire una cartella (manca gestione)
    if (e.getSource()==aggiungiCartella){
    	 JFileChooser fchooser = new JFileChooser(); /*Oggetto JFileChooser che permette di navigare il file system e di selezionare uno o
													più file su cui eseguire una determinata operazione.*/
    	 int value;
    	fchooser.setDialogTitle("Directories");
    	fchooser.setFileFilter(new FiltroDirect());
    	fchooser.setApproveButtonText("Aggiungi");
    	fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	value=fchooser.showOpenDialog(null);
    	c = fchooser.getSelectedFile();
    	
    	
    }	
}
}

