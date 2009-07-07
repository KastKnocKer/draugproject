import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class PannelloCheck extends JPanel implements ItemListener{
	private JTextField txt1;
	private JLabel	label;
	private JCheckBox ck[];
	private static JButton Cerca;
	private static Lista l,lista;
	private static PannelloCentro pc;
	private JTable tab;
	private boolean WithArtwork,WithLyric;
	private int ConA=0,ConL=0;
	private JRadioButton ConSenzaCopertina,ConCopertina,SenzaCopertina,ConSenzaTesto,ConTesto,SenzaTesto ;
public PannelloCheck(Lista l,PannelloCentro pc,Lista lista,JTable tab){
	this.l = l;
	this.pc = pc;
	this.lista = lista;
	this.tab=tab;
	//creo l'oggetto txt per il titolo
	label = new JLabel("Seleziona i criteri di ricerca ");
	//non deve essere editabile
	
	//creo l'oggetto Checkbox formato da 7 elementi
	ck = new JCheckBox [5];
	//attribuisco ad ogni checkbox le proprio caratteristiche
	ck[0] = new JCheckBox("Titolo");
	ck[1] = new JCheckBox("Artista");
	ck[2] = new JCheckBox("Album");
	ck[3] = new JCheckBox("Anno");
	ck[4] = new JCheckBox("Genere");
	
	//aggiungiamo l'ascoltatore per ogni checkbox
	for (int i=0;i<5;i++)
		ck[i].addItemListener(this);
	
	//fissiamo il border
	setLayout(new GridLayout(8,2));
	
	//aggiungiamo la txt e le check
	
	add(label);
	for (int i=0;i<5;i++)
	add(ck[i]);
	
	ConSenzaCopertina = new JRadioButton("Con e senza copertina");
	ConSenzaCopertina.setSelected(true);
	ConCopertina = new JRadioButton ("Con copertina");
	SenzaCopertina = new JRadioButton ("Senza copertina");
	
	ButtonGroup group = new ButtonGroup();
    group.add(ConSenzaCopertina);
    group.add(ConCopertina);
    group.add(SenzaCopertina);
	
    ConSenzaTesto = new JRadioButton("Con e senza Testo");
    ConSenzaTesto.setSelected(true);
	ConTesto = new JRadioButton ("Con Testo");
	SenzaTesto = new JRadioButton ("Senza Testo");
	
	ButtonGroup group1 = new ButtonGroup();
    group1.add(ConSenzaTesto);
    group1.add(ConTesto);
    group1.add(SenzaTesto);
    
    
    add(ConSenzaCopertina);
    add(ConSenzaTesto);
    add(ConCopertina);
    add(ConTesto);
    add(SenzaCopertina);
    add(SenzaTesto);
    
	txt1 = new JTextField("Seleziona un Criterio");
	txt1.setEditable(false);
	add(txt1);
	
	/*String testo = new String();
	testo = txt1.getText();*/
	//creaiamo il tasto cerca
	Cerca = new JButton ("Cerca");
	AscoltatoreCerca AC = new AscoltatoreCerca(txt1,l,tab,ck,ConSenzaCopertina,ConCopertina,SenzaCopertina,ConSenzaTesto,ConTesto,SenzaTesto);
	Cerca.addActionListener(AC);
	add(Cerca);
	
	
	
}
public void paint(Graphics g){
	super.paint(g);
	
	
	Cerca.setSize(100, 30);
	Cerca.setLocation(172, 175);
	
}

public void itemStateChanged(ItemEvent g) {
	//in questo modo edito o meno la Txt,a seconda se l'utente ha cliccato su una JCheck box
	int j=0;
	 
for (int i = 0;i < 5;i++){
	if (ck[i].isSelected()){
		j ++;
	}
}

if (j==1){
	txt1.setEditable(true);
}
else
	{txt1.setEditable(false);}

if(ConSenzaCopertina.isSelected()!=true){
	if(ConCopertina.isSelected()==true){
		WithArtwork=true;
	}
	if(SenzaCopertina.isSelected()==true){
		WithArtwork=false;
	}
}
if(ConSenzaTesto.isSelected()!=true){
	if(ConTesto.isSelected()==true){
		WithLyric=true;
	}
	if(SenzaTesto.isSelected()==true){
		WithLyric=false;
	}
}

if(ConSenzaCopertina.isSelected()==true){
	ConA=1;
}
if(ConSenzaTesto.isSelected()==true){
	ConL=1;
}
}

public boolean returnWithArtwork(){
	return WithArtwork;
}

public boolean returnWithLyric(){
	return WithLyric;
}

public int returnConA(){
	return ConA;
}

public int returnConL(){
	return ConL;
}

}
	





