import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class SchedaArtSolPanel extends JPanel implements ActionListener{
private String NomeArte,Nome,Cognome,Nazione,Genere, Esordio,DataNasc,DataMorte,Discografia,Sito,Biografia;
private JButton Salva;
private Object [] Label;
private Object [] Testo;
private JButton Ritorna;
private PannelloArtisti pa;
private MusicArtSol ArtSolista;


/**
 * Costruttore che inizializza i valori iniziali vuoti
 */

public SchedaArtSolPanel(){
	NomeArte="";
	Nome="";
	Cognome="";
	Nazione="";
	Genere="";
	Esordio="";
	DataNasc="";
	DataMorte="";
	Discografia="";
	Sito="";
	Biografia="";
}

public SchedaArtSolPanel(String NomeArte,String Nome,String Cognome,String Nazione,String Genere,String Esordio,String DataNasc,String DataMorte,String Discografia,String Sito,String Biografia){
	this.NomeArte=NomeArte;
	this.Nome=Nome;
	this.Cognome=Cognome;
	this.Nazione=Nazione;
	this.Genere=Genere;
	this.Esordio=Esordio;
	this.DataNasc=DataNasc;
	this.DataMorte=DataMorte;
	this.Discografia=Discografia;
	this.Sito=Sito;
	this.Biografia=Biografia;
}
	
public void ModificaCampi(MusicArtSol ArtSolista){
	this.ArtSolista=ArtSolista;
	
	((JTextField)Testo[0]).setText(ArtSolista.getNomeArte());
	((JTextField)Testo[1]).setText(ArtSolista.getName());
	((JTextField)Testo[2]).setText(ArtSolista.getCognome());
	((JTextField)Testo[3]).setText(ArtSolista.getNazione());
	((JTextField)Testo[4]).setText(ArtSolista.getGenre());
	((JTextField)Testo[5]).setText(ArtSolista.getEsordio());
	((JTextField)Testo[6]).setText(ArtSolista.getDataNasc());
	((JTextField)Testo[7]).setText(ArtSolista.getDataMorte());
	//((JComboBox)Testo[8]).addItem(ArtSolista.getDiscografia());
	((JTextField)Testo[9]).setText(ArtSolista.getSito());
	((JTextArea)Testo[10]).setText(ArtSolista.getBio());
	
}

public void SchedaArtSolPanel1(PannelloArtisti pa){
	this.pa=pa;
	
	// Creazione Label e JTextField
	Label = new Object[11];
	Testo=new Object[11];
	
	Label[0]= new JLabel("Nome D'Arte");
	Testo[0]=new JTextField(NomeArte);
	
	add((JLabel)Label[0]);
	add((JTextField)Testo[0]);
	
	Label[1]= new JLabel("Nome");
	Testo[1]=new JTextField(Nome);
	
	add((JLabel)Label[1]);
	add((JTextField)Testo[1]);
	
	Label[2]= new JLabel("Cognome");
	Testo[2]=new JTextField(Cognome);
	
	add((JLabel)Label[2]);
	add((JTextField)Testo[2]);
	
	Label[3]= new JLabel("Nazionalità");
	Testo[3]=new JTextField(Nazione);
	
	add((JLabel)Label[3]);
	add((JTextField)Testo[3]);
	
	Label[4]= new JLabel("Genere");
	Testo[4]=new JTextField(Genere);
	
	add((JLabel)Label[4]);
	add((JTextField)Testo[4]);
	
	Label[5]= new JLabel("Esordio");
	Testo[5]=new JTextField(Esordio);
	
	add((JLabel)Label[5]);
	add((JTextField)Testo[5]);
	
	Label[6]= new JLabel("Data di Nascita");
	Testo[6]=new JTextField(DataNasc);
	
	add((JLabel)Label[6]);
	add((JTextField)Testo[6]);
	
	Label[7]= new JLabel("Data di Morte");
	Testo[7]=new JTextField(DataMorte);
	
	add((JLabel)Label[7]);
	add((JTextField)Testo[7]);
	
	Label[8]= new JLabel("Discografia");
	//controllare
	Testo[8]=new JComboBox();
	((JComboBox)Testo[8]).addItem("");
	
	((JComboBox)Testo[8]).addItem("Aggiungi Album");
	((JComboBox)Testo[8]).addActionListener(this);
	
	add((JLabel)Label[8]);
	add((JComboBox)Testo[8]);
	
	Label[9]= new JLabel("Sito Web");
	Testo[9]=new JTextField(Sito);
	
	add((JLabel)Label[9]);
	add((JTextField)Testo[9]);
	
	Label[10]=new JLabel("Biografia");
	Testo[10]=new JTextArea(Biografia);
	
	
	((JTextArea)Testo[10]).setAutoscrolls(true);
	
	add((JLabel)Label[10]);
	add((JTextArea)Testo[10]);
	
	Salva = new JButton("Salva");
	Salva.addActionListener(this);
	add(Salva);
	
	Ritorna = new JButton("Ritorna");
	Ritorna.addActionListener(this);
	add(Ritorna);
	
	
}

public void paint(Graphics g){
	super.paint(g);
	int x=50,y=0;
	
	
	for(int i=0;i<10;i++){
		((JLabel)Label[i]).setSize(100,20);
		if(i==8){
			((JComboBox)Testo[i]).setSize(100,20);
		}
		else
		{((JTextField)Testo[i]).setSize(100,20);}
	}
	
	for(int i=0;i<6;i++){
		((JLabel)Label[i]).setLocation(x, y);
		((JTextField)Testo[i]).setLocation(x + 100,y);
		
		
		y += 30;
		
	}
	int z = 260, p = 0;
	for(int i=6;i<10;i++)
	{
		((JLabel)Label[i]).setLocation(z, p);
		
		if(i==8){
			((JComboBox)Testo[i]).setLocation(z + 100,p);
		}
		else
		{((JTextField)Testo[i]).setLocation(z + 100,p);}
		
		p += 30;
	}
	
	// Disposizione della biografia
	((JLabel)Label[10]).setLocation(470, 0);
	((JTextArea)Testo[10]).setLocation(470,25);
	
	//Dimensione della biografia
	
	((JLabel)Label[10]).setSize(100, 20);
	((JTextArea)Testo[10]).setSize(230,120);
	
	

	


Ritorna.setSize(100, 30);
Ritorna.setLocation(600, 150);

Salva.setSize(100, 30);
Salva.setLocation(490,150);
}


public void actionPerformed (ActionEvent e){
	Object ob = e.getSource();

	if(ob==Ritorna){
		pa.RitornaPA();
	}
	
	if(ob==Salva){
		ArtSolista.setName(((JTextField)Testo[1]).getText());
		ArtSolista.setCognome(((JTextField)Testo[2]).getText());
		ArtSolista.setNazione(((JTextField)Testo[3]).getText());
		ArtSolista.setGenre(((JTextField)Testo[4]).getText());
		ArtSolista.setEsord(((JTextField)Testo[5]).getText());
		ArtSolista.setDataNasc(((JTextField)Testo[6]).getText());
		ArtSolista.setDataMorte(((JTextField)Testo[7]).getText());
		//ArtSolista.setDiscografia(((JComboBox)Testo[8]).getSelectedItem());
		ArtSolista.setSito(((JTextField)Testo[9]).getText());
		ArtSolista.setBio(((JTextArea)Testo[10]).getText());
		
		this.ModificaCampi(ArtSolista);
		
		
	}
	
	// nel caso in cui clikkiiamo sulla JCombobox
	String selectedItem =(String) ((JComboBox)Testo[8]).getSelectedItem();
	if(ob == ((JComboBox)Testo[8])){
		if(selectedItem.equals("Aggiungi Album")){
			Album album = new Album();
			
			SchedaAlbumDialog AlbumDialog = new SchedaAlbumDialog(ArtSolista.getNomeArte(),(JComboBox)Testo[8],album);
			AlbumDialog.showInputDialog();
			
			System.out.println("Ok ti mostro l'elenco degli Album");
		}
		else
		{
			
		}
	}
}
}
