import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
public class PannelloGlobale extends JPanel{
	
	public PannelloCentro pc;
	public PannelloSin ps;
	private Lista l;
	private JTable tab;
	
	private PannelloCentroSud pcs;
public PannelloGlobale(Lista l) throws ClassNotFoundException{
	
	this.l = l;
	Lista lista = new Lista();
	
	
	
	setLayout(new BorderLayout());


	pc = new PannelloCentro(l,lista);
	
	Border etchedBdr = BorderFactory.createEtchedBorder();
	Border titledBdr = BorderFactory.createTitledBorder(etchedBdr, "Libreria Globale");
	Border emptyBdr  = BorderFactory.createEmptyBorder(10,0,10,45);
	Border compoundBdr=BorderFactory.createCompoundBorder(titledBdr, emptyBdr);
	pc.setBorder(compoundBdr);
	add(pc,BorderLayout.CENTER);
	tab	=pc.RitornaTabella();
	
	ps = new PannelloSin(l,pc,lista,tab);
	Border etchedBordo = BorderFactory.createEtchedBorder();
	Border titledBordo = BorderFactory.createTitledBorder(etchedBordo, "Gestione Multimediale");
	Border emptyBordo  = BorderFactory.createEmptyBorder(00,0,0,0);
	Border compoundBordo=BorderFactory.createCompoundBorder(titledBordo, emptyBordo);
	ps.setBorder(compoundBordo);
	add(ps,BorderLayout.NORTH);
	
	pcs=ps.ReturnPannello();
	pc.setPcs(pcs);
}

public void paint(Graphics g){
	super.paint(g);
	//g.drawImage(img,1,1,getWidth(),getHeight(),null);			/*Chiamiamo il metodo DrawImage sull'oggetto g*/
    /*Font f = new Font("Matura MT Script Capitals",Font.BOLD,12);	/*Settiamo il font*/
    /*g.setFont(f);
	g.setColor(Color.black);
	g.drawString("iTunz - Lettore mp3", getWidth() - 200, getHeight() - 50);*/}

}
