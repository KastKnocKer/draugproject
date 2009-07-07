import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Time;

import javax.media.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;



public class PannelloSin extends JPanel implements ActionListener/*,ChangeListener,MouseListener*/{
	public PannelloNord pn;
	private JLabel GestioneMultimediale;
	private JButton Play,Stop,Pause;
	private PannelloCentro pc;
	private JTable tab;
	private Lista l,lista;
	private PannelloCentroSud pcs;
	// Oggetto player (vero e proprio)
	private Player player;
	private JSlider Barra=null;
	private boolean AggiornamentoBarra=true;
	private Time durata;
	private Mp3 mp3;
	private boolean pausa=false;
	private File file;
	
public PannelloSin(Lista l,PannelloCentro pc,Lista lista,JTable tab){
	this.pc = pc;
	this.l = l;
	this.lista=lista;
	this.tab=tab;
	setLayout(new BorderLayout());
	

	
	Play = new JButton("Play");			//creo i pulsanti (manca gestione pause)
	Stop = new JButton("Stop");
	Pause = new JButton("Pause");
	
	//barra di avanzamento
	Barra=new JSlider(0, 50, 0);
	/*Barra.addChangeListener(this);
	Barra.addMouseListener(this);*/
	
	ImageIcon icon1 = new ImageIcon("Play.gif");
	Play.setIcon(icon1);
	add(Play);	
	Play.addActionListener(this);
	
	/*ImageIcon icon3 = new ImageIcon("Pause.gif");
	Pause.setIcon(icon3);
	add(Pause);
	Pause.addActionListener(this);*/
	
	ImageIcon icon2 = new ImageIcon("Stop.gif");
	Stop.setIcon(icon2);
	add(Stop);
	Stop.addActionListener(this);
	
	
	add(Barra,BorderLayout.WEST);
	
	

	pcs = new PannelloCentroSud(l,tab);
	Border etchedBordo = BorderFactory.createEtchedBorder();
	Border titledBordo = BorderFactory.createTitledBorder(etchedBordo, "Catalogazione Brani");
	Border emptyBordo  = BorderFactory.createEmptyBorder(0,0,0,0);
	Border compoundBordo=BorderFactory.createCompoundBorder(titledBordo, emptyBordo);
	pcs.setBorder(compoundBordo);
	
	add(pcs/*,BorderLayout.EAST*/);
	pn = new PannelloNord(l,pc,lista,tab);
	add(pn,BorderLayout.SOUTH);

	//pcs = pn.ReturnPannello();
	
	
	}

public void paint(Graphics g){
	super.paint(g);

	Play.setBounds(206, 35, 80, 30);
	//Pause.setBounds(287, 35, 80, 30);
	Stop.setBounds(368, 35,80, 30);
	
	
	pcs.setBounds(440, 20, 562, 60);
}

public Lista ritornaLista(){
	return lista;
}

public PannelloCentroSud ReturnPannello(){
	return pcs;
}

public void actionPerformed(ActionEvent e) 
{
	if ( e.getSource().equals(Play) )
	{
		if ( pc.getRowSelected() != null )
			play(pc.getRowSelected());
		
		/*else if ( MyTree.getMp3Selected() != null )
			play(MyTree.getMp3Selected());*/
		else
			JOptionPane.showMessageDialog(null, "Seleziona un mp3...");
	} else if ( e.getSource().equals(Stop) )
		close();
}




/**
 * Crea il player e inizia l'esecuzione del file
 * musicale.
 * 
 * @param File Mp3 da ascoltare.
 */
public void play(String File) {
    if ( player == null )
	{
    	try {
    		FileInputStream fis = new FileInputStream(File);
    		BufferedInputStream bis = new BufferedInputStream(fis);
    		player = new Player(bis);
    	}
    	catch (Exception e) {
    		System.out.println("Problem playing file " + File);
    		System.out.println(e);
    	}
    	// Lancia il playing in un nuovo treaad
    	new Thread() {
    		public void run() {
    			try { player.play(); }
    			catch (Exception e) { System.out.println(e); }
    		}
    	}.start();
	} else 
	{
		close();
		play(File);
	}
}



/**
 * Procedura che aggiorna la barra di progressione della riproduzione degli mp3
 */
/*public void AggiornaBarra(){
	if(AggiornamentoBarra){
	int posizionesec=(int)  player.getMediaTime().getSeconds();
	int percentuale= (posizionesec*1000)/ (int) durata.getSeconds();
	Barra.setValue(percentuale);
	}
}*/

/**
 * Ferma l'esecuzione mp3.
 */
public void close() 
{ 
	if (player != null)
	{
		player.close();
		player = null;
	}
}



}



