import java.awt.*;
import javax.swing.*;

import java.awt.Dimension;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
public class MyFrame extends JFrame{
	private JMenuBar barra = new JMenuBar();
	private JMenu file = new JMenu("File ");
		private JMenuItem nuovo = new JMenuItem("Nuova Play List ");
		private JMenuItem aggiungiCartella = new JMenuItem("Aggiungi Cartella");
		private JMenuItem salva = new JMenuItem("Salva Progetto in corso ...");
		private JMenuItem esci = new JMenuItem ("Esci");
	private JMenu modifica = new JMenu("Modifica ");
		private JMenuItem preferenze = new JMenuItem("Preferenze ...");
	private JMenu help = new JMenu("?                                                                              ");
		private JMenuItem guida = new JMenuItem("Guida a Draug Lettore Mp3");
		private JMenuItem contattaci = new JMenuItem("Contattaci");
		private JMenuItem about = new JMenuItem("Informazioni su Draug Lettore Mp3");
	private JMenu iTunzMp3 = new JMenu("iTunz Mp3");
		private JMenuItem GF =new JMenuItem ("di Forliano Gerardo");
			
public MyFrame (String titolo){
	super(titolo);
	//passo il riferimento alla lista
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(0,0,1024,800);
	file.add(nuovo); 				/*Aggiungi elementi al menu File*/
	file.add(aggiungiCartella);
	file.add(salva);
	file.addSeparator();
	file.add(esci);
	
	modifica.add(preferenze);		/*Aggiungi elementi al menu Modifica */
	
	help.add(guida);
	help.add(contattaci);			/*Aggiungi elementi al menu Help*/
	help.addSeparator();
	help.add(about);
	
	iTunzMp3.add(GF);		/*Eggingi elemento a DraugLettoreMp3*/
	
	barra.add(file);				/*Aggiungi alla barra dei menu  i menu File,Archivio,Help*/
	barra.add(modifica);
	barra.add(help);
	barra.add(iTunzMp3);
	
	
	setJMenuBar(barra);
	setLayout(new BorderLayout());
	
EventiMyFrame EMF = new EventiMyFrame (esci,aggiungiCartella);	/*Andiamo a creare l'oggetto ascoltatore*/
esci.addActionListener(EMF);
aggiungiCartella.addActionListener(EMF);

}


}


	
