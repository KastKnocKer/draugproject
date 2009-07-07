import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.jaudiotagger.audio.exceptions.*;
import org.jaudiotagger.audio.*;
import org.jaudiotagger.tag.*;




public class EventiMouse implements ActionListener{
	private JMenuItem SchedaArtista,Elimina,Proprietà;
	private Lista l,listTot;
	private int row;
	private Mp3 Brano;
	private JTable t;
	private AbstractTableModel tempdataModel;
	private PannelloArtisti pa;
	private Lista ListaArtisti,ListaGruppi;
	
	private JComboBox FiltroGenere,FiltroArtista,FiltroAlbum,FiltroAnno; 
	public EventiMouse(JMenuItem SchedaArtista,JMenuItem Elimina,JMenuItem Proprietà,Lista listTot,Lista l,int row,JTable t,JComboBox FiltroGenere, JComboBox FiltroArtista, JComboBox FiltroAlbum,JComboBox FiltroAnno,PannelloArtisti pa,Lista ListaArtisti,Lista ListaGruppi){
		this.SchedaArtista = SchedaArtista;
		this.Elimina = Elimina;
		this.Proprietà = Proprietà;
		this.l=l;
		this.listTot = listTot;
		this.row=row;
		this.t = t;
		this.FiltroGenere = FiltroGenere;
		this.FiltroArtista = FiltroArtista;
		this.FiltroAnno = FiltroAnno;
		this.FiltroAlbum = FiltroAlbum;
		
		this.pa=pa;
		this.ListaArtisti=ListaArtisti;
		this.ListaGruppi=ListaGruppi;
	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==Proprietà){
		
			Brano = new Mp3(l);
			Brano = (Mp3) l.getObjPos(row);
			
			InputDialog Dialog = new InputDialog(l,Brano,row,t,FiltroGenere,FiltroArtista,FiltroAlbum,FiltroAnno);
			
			
				
						try {
							Dialog.showInputDialog();
						} catch (CannotReadException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (TagException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ReadOnlyFileException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvalidAudioFrameException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (CannotWriteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
				
				
		}
		if (e.getSource()==Elimina){
			Brano = new Mp3(l);
			Brano = (Mp3) l.getObjPos(row);
			
			//Filtro Genere aggiorna
			int cont = 0;
			for(int i=0;i<listTot.size();i++)
				if(Brano.GetGenere().equals(((Mp3)listTot.getObjPos(i)).GetGenere()))
					cont++;
					
			if(cont == 1) FiltroGenere.removeItem(Brano.GetGenere());
			
			//Filtro Artista aggiorna
			int cont1 = 0;
			for(int j=0;j<listTot.size();j++)
				if(Brano.GetArtista().equals(((Mp3)listTot.getObjPos(j)).GetArtista()))
					cont1++;
					
			if(cont1 == 1) FiltroArtista.removeItem(Brano.GetArtista());
			
			//Filtro Album aggiorna
			int cont2 = 0;
			for(int k=0;k<listTot.size();k++)
				if(Brano.GetAlbum().equals(((Mp3)listTot.getObjPos(k)).GetAlbum()))
					cont2++;
					
			if(cont2 == 1) FiltroAlbum.removeItem(Brano.GetAlbum());
			
			//Filtro Anno aggiorna
			int cont3 = 0;
			for(int z=0;z<listTot.size();z++)
				if(Brano.GetAnno().equals(((Mp3)listTot.getObjPos(z)).GetAnno()))
					cont3++;
					
			if(cont3 == 1) FiltroAnno.removeItem(Brano.GetAnno());
			
			listTot.remove(Brano);
			listTot.salvaLista();
			/*l.remove(Brano);
			l.salvaLista();*/
			//aggiornare tabella
			/*tempdataModel = new Mp3TableModel(l);*/
			tempdataModel = new Mp3TableModel(listTot);
			t.setModel(tempdataModel);
			((AbstractTableModel) t.getModel()).fireTableDataChanged();
			
			}
		if (e.getSource()==SchedaArtista){
			Brano = new Mp3(l);
			Brano = (Mp3) l.getObjPos(row);
			
			SchedaArtDialog SchedaArt = new SchedaArtDialog(pa,Brano.GetArtista(),Brano,ListaArtisti,ListaGruppi);
			SchedaArt.showInputDialog();
		}
		
	}
}


