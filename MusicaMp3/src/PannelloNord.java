import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;
public class PannelloNord extends JPanel{
private PannelloCheck pck;
private PannelloCentroSud pcs;
private PannelloCentro pc;
private PannelloArtisti pa;

private Lista l,lista,ListaArtisti,ListaGruppi;
private JTable tab;


public PannelloNord(Lista l,PannelloCentro pc,Lista lista,JTable tab){
	
	this.pc = pc;
	this.l = l;
	this.lista=lista;
	this.tab=tab;
	setLayout( new BorderLayout());
	
	
	ListaArtisti = pc.returnListaArtisti();
	ListaGruppi = pc.returnListaGruppi();
	/*
	pcs = new PannelloCentroSud(l,tab);
	Border etchedBordo = BorderFactory.createEtchedBorder();
	Border titledBordo = BorderFactory.createTitledBorder(etchedBordo, "Catalogazione Brani");
	Border emptyBordo  = BorderFactory.createEmptyBorder(0,0,0,0);
	Border compoundBordo=BorderFactory.createCompoundBorder(titledBordo, emptyBordo);
	pcs.setBorder(compoundBordo);
	*/
	
	pck = new PannelloCheck(l,pc,lista,tab);
	
	Border etchedBdr = BorderFactory.createEtchedBorder();
	Border titledBdr = BorderFactory.createTitledBorder(etchedBdr, "Ricerca brani");
	Border emptyBdr  = BorderFactory.createEmptyBorder(0,0,0,0);
	Border compoundBdr=BorderFactory.createCompoundBorder(titledBdr, emptyBdr);
	pck.setBorder(compoundBdr);
	
	pa = new PannelloArtisti(ListaArtisti,ListaGruppi);
	Border etchedBdr1 = BorderFactory.createEtchedBorder();
	Border titledBdr1 = BorderFactory.createTitledBorder(etchedBdr1, "Schede Artisti");
	Border emptyBdr1  = BorderFactory.createEmptyBorder(0,0,0,0);
	Border compoundBdr1=BorderFactory.createCompoundBorder(titledBdr1, emptyBdr1);
	pa.setBorder(compoundBdr1);
	
	
	
	//setto nel pannello centrale il pannello che gestisce gli artisti
	pc.setPa(pa);
	add(pck,BorderLayout.EAST);
	add(pa);
	
}

public Lista ritornaLista(){
	return lista;
}

public PannelloCentroSud ReturnPannello(){
	return pcs;
}


}
