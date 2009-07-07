import java.awt.*;
import java.util.Vector;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
public class PannelloCentro extends JPanel implements ActionListener,MouseListener{
	public static Lista l,listaGlobale,lista;
	public static Mp3TableModel dataModel,dataModeltmp,tempdataModel;
	
	private static JButton AggiungiMp3,AggiungiCartella,Libreria;
	
	private static File f,c;
	private static Mp3 Brano;
	
	private JTable t;
	
	private JPopupMenu popup; 
	private int row,ROW;
	String row1;
	private PannelloCentroSud pcs;
	private PannelloArtisti pa;
	private JComboBox FiltroGenere,FiltroArtista,FiltroAlbum,FiltroAnno; 
	
	private Lista ListaArtisti,ListaGruppi;
	
public PannelloCentro(Lista l,Lista lista) throws ClassNotFoundException{
	
	this.lista = lista;
	this.l = l;
	
	ListaArtisti = new Lista();
	ListaGruppi = new Lista();
	
	l.leggiLista();
	setLayout(new BorderLayout());
	
	
	
		dataModel = new Mp3TableModel(l);
		// crea la tabella
		t = new JTable(dataModel);
		// Aggiunge un MouseListener al componente che deve mostrare il menu
	     
		t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		t.addMouseListener(this);
		
		// aggiunge la tabella ad uno ScollPane
		JScrollPane scrollpane = new JScrollPane(t);
		// aggiunge lo ScrollPane al pannello
		add(scrollpane,BorderLayout.CENTER);
		setVisible(true);
		
		
		AggiungiMp3 = new JButton("Aggiungi Mp3");
		add(AggiungiMp3,BorderLayout.AFTER_LINE_ENDS);
		
		AggiungiCartella = new JButton("Aggiungi Cartella Mp3");
		add(AggiungiCartella,BorderLayout.AFTER_LINE_ENDS);
		
		Libreria = new JButton("Libreria");
		add(Libreria,BorderLayout.AFTER_LINE_ENDS);
		
		AggiungiCartella.addActionListener(this);
		AggiungiMp3.addActionListener(this);
		Libreria.addActionListener(this);
		
		
	      
	}

public void RiscriviTab()
{
	dataModel.fireTableDataChanged();
}

public void actionPerformed (ActionEvent e){
Object ob = e.getSource();

if(ob==AggiungiCartella){
	JFileChooser fchooser = new JFileChooser();
	
	int value;
 	fchooser.setDialogTitle("Directories");
 	fchooser.setFileFilter(new FiltroDirect());
 	fchooser.setApproveButtonText("Aggiungi");
 	fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
 	value=fchooser.showOpenDialog(null);
 	c = fchooser.getSelectedFile();
 	
 	File fileDir = new File(c.getAbsolutePath());
 	if(fileDir == null) return;
    if(!fileDir.isDirectory()) return;
    if(!fileDir.canRead()) return;

    File[] VettoreFileDir = fileDir.listFiles();
 
    
    try{
	for(int i=0;i<VettoreFileDir.length;i++){
		if(VettoreFileDir[i].getAbsolutePath().endsWith(".mp3"))
		{	Mp3 Song = new Mp3(VettoreFileDir[i]);
			l.insert(Song);
			l.salvaLista();
		
			tempdataModel = new Mp3TableModel(l);
			t.setModel(tempdataModel);
		//pcs.togliFiltri();
		//pcs.AggiornaFiltri();
			((AbstractTableModel) t.getModel()).fireTableDataChanged();
		
		
			FiltroGenere = pcs.ReturnFGenere();
			FiltroArtista = pcs.ReturnFArtista();
			FiltroAnno = pcs.ReturnFAnno();
			FiltroAlbum = pcs.ReturnFAlbum();
		
		//Filtro Genere aggiorna
		int cont=FiltroGenere.getItemCount();
		boolean found=false;
		for(int p=0;p<cont && !found;p++)
			if(Song.GetGenere().equals(FiltroGenere.getItemAt(p))) 
				found=true;
		if(!found) FiltroGenere.addItem(Song.GetGenere());
		
		//Filtro Artista aggiorna
		int cont1 =FiltroArtista.getItemCount();
		boolean found1=false;
		for(int j=0;j<cont1 && !found1;j++)
			if(Song.GetArtista().equals(FiltroArtista.getItemAt(j))) 
				found1=true;
		if(!found1) FiltroArtista.addItem(Song.GetArtista());
		
		
		//Filtro Album aggiorna
		int cont2=FiltroAlbum.getItemCount();
		boolean found2=false;
		for(int k=0;k<cont2 && !found2;k++)
			if(Song.GetAlbum().equals(FiltroAlbum.getItemAt(k))) 
				found2=true;
		if(!found2) FiltroAlbum.addItem(Song.GetAlbum());
		
		//Filtro Anno aggiorna
		int cont3=FiltroAnno.getItemCount();
		boolean found3=false;
		for(int x=0;x<cont3 && !found3;x++)
			if(Song.GetAnno().equals(FiltroAnno.getItemAt(x))) 
				found3=true;
		if(!found3) FiltroAnno.addItem(Song.GetAnno());
		}
	}
    }
	catch(Exception er)
		{
			System.out.println("Errore di lettura file" + er);
		}
	}



if(ob == AggiungiMp3){
	
	
	JFileChooser fchooser = new JFileChooser();
	
	int value;
	
	fchooser.setDialogTitle("Files Mp3");
	fchooser.setFileFilter(new FiltroMp3());
	fchooser.setApproveButtonText("Aggiungi");
	fchooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	value = fchooser.showOpenDialog(null);
	f = fchooser.getSelectedFile();
	try{
		//usiamo la classe brano con i rispettivi tag
		Mp3 Song = new Mp3(f);
		l.insert(Song);
		l.salvaLista();
		
		tempdataModel = new Mp3TableModel(l);
		t.setModel(tempdataModel);
		//pcs.togliFiltri();
		//pcs.AggiornaFiltri();
		((AbstractTableModel) t.getModel()).fireTableDataChanged();
		
		
		FiltroGenere = pcs.ReturnFGenere();
		FiltroArtista = pcs.ReturnFArtista();
		FiltroAnno = pcs.ReturnFAnno();
		FiltroAlbum = pcs.ReturnFAlbum();
		
		//Filtro Genere aggiorna
		int cont=FiltroGenere.getItemCount();
		boolean found=false;
		for(int i=0;i<cont && !found;i++)
			if(Song.GetGenere().equals(FiltroGenere.getItemAt(i))) 
				found=true;
		if(!found) FiltroGenere.addItem(Song.GetGenere());
		
		//Filtro Artista aggiorna
		int cont1 =FiltroArtista.getItemCount();
		boolean found1=false;
		for(int j=0;j<cont1 && !found1;j++)
			if(Song.GetArtista().equals(FiltroArtista.getItemAt(j))) 
				found1=true;
		if(!found1) FiltroArtista.addItem(Song.GetArtista());
		
		
		//Filtro Album aggiorna
		int cont2=FiltroAlbum.getItemCount();
		boolean found2=false;
		for(int k=0;k<cont2 && !found2;k++)
			if(Song.GetAlbum().equals(FiltroAlbum.getItemAt(k))) 
				found2=true;
		if(!found2) FiltroAlbum.addItem(Song.GetAlbum());
		
		//Filtro Anno aggiorna
		int cont3=FiltroAnno.getItemCount();
		boolean found3=false;
		for(int x=0;x<cont3 && !found3;x++)
			if(Song.GetAnno().equals(FiltroAnno.getItemAt(x))) 
				found3=true;
		if(!found3) FiltroAnno.addItem(Song.GetAnno());
		}
	catch(Exception er)
		{
			System.out.println("Errore di lettura file" + er);
		}
}
if (ob == Libreria){
	Lista lista =new Lista();
	try {
		lista.leggiLista();
	} catch (ClassNotFoundException e1) {
		
		e1.printStackTrace();
	}
	
	dataModeltmp = new Mp3TableModel(lista);
	t.setModel(dataModeltmp);
}
}

	

public void paint(Graphics g){
	super.paint(g);
	AggiungiMp3.setSize(100, 30);
	AggiungiMp3.setLocation(900, 50);
	
	AggiungiCartella.setSize(100, 30);
	AggiungiCartella.setLocation(900, 120);
	
	Libreria.setSize(100, 30);
	Libreria.setLocation(900, 190);}



    /*public void mousePressed(MouseEvent e) {
      if (e.isPopupTrigger()) {
         popup.show(e.getComponent(),e.getX(), e.getY());
      }
    }
    public void mouseReleased(MouseEvent e) {
      if (e.isPopupTrigger()) {
        popup.show(e.getComponent(),e.getX(), e.getY());
      }
    }*/
   
public void mouseClicked(MouseEvent e) {
	AbstractMp3Model TmpModel = (AbstractMp3Model) t.getModel();
	
	if(e.getButton() != MouseEvent.BUTTON1)
	{
		
		FiltroGenere = pcs.ReturnFGenere();
		FiltroArtista = pcs.ReturnFArtista();
		FiltroAnno = pcs.ReturnFAnno();
		FiltroAlbum = pcs.ReturnFAlbum();
		
		row = t.rowAtPoint (e.getPoint ());
		popup = new JPopupMenu();
		JMenuItem SchedaArtista= new JMenuItem("Scheda Artista");
		JMenuItem Elimina = new JMenuItem("Elimina");
		JMenuItem Proprietà = new JMenuItem("Proprietà");
		


		
	
  EventiMouse EM = new EventiMouse(SchedaArtista,Elimina,Proprietà,l,TmpModel.ReturnList(),row,t,FiltroGenere,FiltroArtista,FiltroAnno,FiltroAlbum,pa,ListaArtisti,ListaGruppi);
  SchedaArtista.addActionListener(EM);
  Elimina.addActionListener(EM);
  Proprietà.addActionListener(EM);
  
    	popup.add(SchedaArtista);
    	popup.add(Elimina);
    	popup.add(Proprietà); 
    
    	popup.show(e.getComponent(),e.getX(), e.getY());
	}
    
    
	
}

public Lista returnListaArtisti(){
	return ListaArtisti;
}
public Lista returnListaGruppi(){
	return ListaGruppi;
}

public String getRowSelected()
{
	if ( t.getSelectedRow() != -1 )
		return (String)t.getModel().getValueAt(t.getSelectedRow(), 1);	
	else
		return null;
}

public void setPcs(PannelloCentroSud pcs){
	this.pcs=pcs;
}

public void setPa(PannelloArtisti pa){
	this.pa=pa;
}
public JTable RitornaTabella(){
	return t;
}


public void mouseEntered(MouseEvent e) {
	
	
}


public void mouseExited(MouseEvent e) {
	
	
}


public void mousePressed(MouseEvent e) {

	
}


public void mouseReleased(MouseEvent e) {


}
}
