import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.*;
public class PannelloCentroSud extends JPanel{
	private JComboBox FiltroGenere,FiltroArtista,FiltroAlbum,FiltroAnno;
	public static Mp3TableModel dataModel;
	
	
	private Lista l,listaFiltri;
	private JTable tab;
	private int j=0,numeroGeneri=0;
	private String[] Generi,Artisti,Album,Anni,tempGeneri,tempArtisti,tempAlbum,tempAnni;
	
public PannelloCentroSud(Lista l,JTable tab){
this.l = l;
this.tab = tab;
	listaFiltri=new Lista();
	
	FiltroGenere = new JComboBox();
	FiltroGenere.addItem("Seleziona Genere");
	
	FiltroArtista = new JComboBox();
	FiltroArtista.addItem("Seleziona Artista");
	
	FiltroAlbum = new JComboBox();
	FiltroAlbum.addItem("Seleziona Album");
	
	FiltroAnno = new JComboBox();
	FiltroAnno.addItem("Seleziona Anno");
	
	
	
	AggiornaFiltri();
	
	
	add(FiltroGenere);
	add(FiltroArtista);
	add(FiltroAlbum);
	add(FiltroAnno);
	
	AscoltatoreFiltri AF = new AscoltatoreFiltri(l,listaFiltri,tab,FiltroGenere,FiltroArtista,FiltroAlbum,FiltroAnno);
	
	FiltroGenere.addActionListener(AF);
	FiltroArtista.addActionListener(AF);
	FiltroAlbum.addActionListener(AF);
	FiltroAnno.addActionListener(AF);
	
		
}

public JComboBox ReturnFGenere(){
	return FiltroGenere;
}

public JComboBox ReturnFArtista(){
	return FiltroArtista;
}
public JComboBox ReturnFAlbum(){
	return FiltroAlbum;
}
public JComboBox ReturnFAnno(){
	return FiltroAnno;
}

public void AggiornaFiltri(){
	Generi = new String[l.returnNumeroGeneri()];
	Artisti = new String[l.returnNumeroArtisti()];
	Album = new String[l.returnNumeroAlbum()];
	Anni = new String[l.returnNumeroAnni()];
	//visualizzare filtri
	int k=0;
	for(int i=0;i<l.size();i++){
		Boolean trovato=false;
		int j=0;
		
		for(j=0;j<Generi.length && !trovato;j++){
			if(((Mp3)l.getObjPos(i)).GetGenere().equals(Generi[j])) trovato=true;}
		
		if(!trovato) {Generi[k]=((Mp3)l.getObjPos(i)).GetGenere(); k++;}
	}
	
	for (int z=0; z<Generi.length; z++){
		FiltroGenere.addItem(Generi[z]);
	}
	
	int a=0;
	for(int i=0;i<l.size();i++){
		Boolean trovato=false;
		int j=0;
		
		for(j=0;j<Artisti.length && !trovato;j++){
			if(((Mp3)l.getObjPos(i)).GetArtista().equals(Artisti[j])) trovato=true;}
		if(!trovato) {Artisti[a]=((Mp3)l.getObjPos(i)).GetArtista();a++;}}
	
	
	for (int h=0; h<Artisti.length; h++){
		FiltroArtista.addItem(Artisti[h]);
	}
	
	int b=0;
	for(int i=0;i<l.size();i++){
		Boolean trovato=false;
		int j=0;
		
		for(j=0;j<Album.length && !trovato;j++){
			if(((Mp3)l.getObjPos(i)).GetAlbum().equals(Album[j])) trovato=true;}
		if(!trovato) {Album[b]=((Mp3)l.getObjPos(i)).GetAlbum();b++;}}
	
	
	for (int y=0; y<Album.length; y++){
		FiltroAlbum.addItem(Album[y]);
	}
	
	int c=0;
	for(int i=0;i<l.size();i++){
		Boolean trovato=false;
		int j=0;
		
		for(j=0;j<Anni.length && !trovato;j++){
			if(((Mp3)l.getObjPos(i)).GetAnno().equals(Anni[j])) trovato=true;}
		if(!trovato) {Anni[c]=((Mp3)l.getObjPos(i)).GetAnno();c++;}}
	
	
	for (int x=0; x<Anni.length; x++){
		FiltroAnno.addItem(Anni[x]);
	}
}

public void togliFiltri(){
	FiltroGenere.removeAllItems();
	FiltroArtista.removeAllItems();
	FiltroAlbum.removeAllItems();
	FiltroAnno.removeAllItems();
	
	FiltroGenere.addItem("Seleziona Genere");
	FiltroArtista.addItem("Seleziona Artista");
	FiltroAlbum.addItem("Seleziona Album");
	FiltroAnno.addItem("Seleziona Anno");
}

public void paint(Graphics g){
	super.paint(g);
	
	FiltroGenere.setBounds(10, 25, 110, 20);
	FiltroArtista.setBounds(123, 25, 170, 20);
	
	FiltroAlbum.setBounds(296, 25, 157, 20);
	FiltroAnno.setBounds(455, 25, 100, 20);
	}

}





	

