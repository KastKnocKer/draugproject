import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.text.JTextComponent;


public class SchedaArtDialog extends JOptionPane implements PropertyChangeListener{
	private String [] opzioni;
	private Object[] campi;
	private int result;
	private  String Artista;
	private  Mp3 Brano;
	private  Lista ListaArtisti,ListaGruppi;
	private PannelloArtisti pa;
	private DefaultListModel modelloLista,modelloLista1;
	private MusicArtSol ArtSolista;
	private MusicArtGroup ArtGroup;
	private int k=0; //per la posizione all'interno della lista degli artisti
	
	private JList ListaArtistiList,ListaGroupList;
	private ListModel Modello;
	private String vector[];
	
	public SchedaArtDialog(PannelloArtisti pa,String Artista,Mp3 Brano,Lista ListaArtisti,Lista ListaGruppi){
		this.Artista=Artista;
		this.Brano=Brano;
		this.pa=pa;
		this.ListaArtisti=ListaArtisti;
		this.ListaGruppi=ListaGruppi;
		
		//rifermito ad oggetto lista del pannello
		ListaArtistiList=pa.ReturnListaArt();
		ListaGroupList=pa.ReturnListaArtGroup();
		
		
		campi = new Object[5];
		campi[0] = new JLabel("Nome Artista");
		
		
		campi[1] = new JTextField();
		
		((JTextField) campi[1]).setEditable(false);
		
		campi[2] = new JLabel("Seleziona la scheda artista più appropriata ");
		
		
		campi[3] = new JRadioButton("Artista Solista");
		campi[4] = new JRadioButton( "Gruppo musicale");
		
		ButtonGroup group = new ButtonGroup();
		group.add((JRadioButton)campi[3]);
		group.add((JRadioButton)campi[4]);
		
		
		
		opzioni = new String[2];
		opzioni[0] = new String("Crea Scheda");
		opzioni[1]= new String("Annulla");
		
	
		
		
		
	}
	
	public void showInputDialog(){
		((JTextField) campi[1]).setText(Artista);
	
		
		result=JOptionPane.showOptionDialog(null, campi, " Creazione Scheda Artista " , JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, opzioni, opzioni[0]);
		
		if(result==0){
			
			
			int cont = 0;
		//se ho selezionato gestione artisti SOLISTA
			if(((JRadioButton)campi[3]).isSelected()){
				
				
				//creiamo l'oggetto MusicArtSolista
				
				ArtSolista = new MusicArtSol(Artista);
				
				//scandisce la lista e vede se c'è gia una scheda artista
				for(int i=0; i<ListaArtisti.sizeMA();i++){
					if(((MusicArtSol)ListaArtisti.getObjPosMA(i)).getNomeArte().equals(Artista)){
						cont++;
					}
				}
				//se non c'è un artista nella lista, aggiungiamo la nuova scheda
				if(cont == 0){
					ListaArtisti.insertMA(ArtSolista);
					System.out.println("Fatto"/*((MusicArtSol)ListaArtisti.getObjPosMA(0)).getNomeArte()*/);
				}
				else
					//se invece c'è un'artista nella lista riprendiamo quella vecchia
				{
					
					for(int i=0; i<ListaArtisti.sizeMA();i++){
						if(((MusicArtSol)ListaArtisti.getObjPosMA(i)).getNomeArte().equals(Artista)){
							k=i;
							System.out.println("C'è gia nella lista");
						}
					}
					
				}
				if(k==0){ //se l'abbiamo appena inserito
					int j=0;
					for(int i=0; i<ListaArtisti.sizeMA();i++){
						if(((MusicArtSol)ListaArtisti.getObjPosMA(i)).getNomeArte().equals(Artista)){
							j=i;
						}
					}
					pa.VisualizzaSchedaArtSol(ListaArtisti, j );
					
				}
				else {
				pa.VisualizzaSchedaArtSol(ListaArtisti,k);
				}
				vector = new String[ListaArtisti.sizeMA()];
				for(int i=0; i<ListaArtisti.sizeMA();i++){
					vector[i] = ((MusicArtSol)ListaArtisti.getObjPosMA(i)).getNomeArte();
				}
				ListaArtistiList.setListData(vector);
				
				/*modelloLista = (DefaultListModel) ListaArtistiList.getModel();
				modelloLista.addElement(Artista);
				ListaArtistiList.setModel(modelloLista);*/
				
						
			}
			
			//se seleziona la selezione GRUPPOOOOOO
			if(((JRadioButton)campi[4]).isSelected()){
				
				//creo l'oggetto scheda Gruppo
				ArtGroup = new MusicArtGroup(Artista);
				
				
				//scandisce la lista e vede se c'è gia una scheda GRUPPO
				for(int i=0; i<ListaGruppi.sizeMA();i++){
					if(((MusicArtGroup)ListaGruppi.getObjPosMA(i)).getName().equals(Artista)){
						cont++;
					}
				}
				//se non c'è un artista nella lista, aggiungiamo la nuova scheda
				if(cont == 0){
					ListaGruppi.insertMA(ArtGroup);
					System.out.println("Fatto"/*((MusicArtSol)ListaArtisti.getObjPosMA(0)).getNomeArte()*/);
				}
				else
					//se invece c'è un'artista nella lista riprendiamo quella vecchia
				{
					
					for(int i=0; i<ListaGruppi.sizeMA();i++){
						if(((MusicArtGroup)ListaGruppi.getObjPosMA(i)).getName().equals(Artista)){
							k=i;
							System.out.println("C'è gia nella lista");
						}
					}
					
				}
				if(k==0){ //se l'abbiamo appena inserito
					int j=0;
					for(int i=0; i<ListaGruppi.sizeMA();i++){
						if(((MusicArtGroup)ListaGruppi.getObjPosMA(i)).getName().equals(Artista)){
							j=i;
						}
					}
					pa.VisualizzaSchedaArtGroup(ListaGruppi, j );
					
				}
				else {
				pa.VisualizzaSchedaArtGroup(ListaGruppi,k);
				}
				vector = new String[ListaGruppi.sizeMA()];
				for(int i=0; i<ListaGruppi.sizeMA();i++){
					vector[i] = ((MusicArtGroup)ListaGruppi.getObjPosMA(i)).getName();
				}
				ListaGroupList.setListData(vector);
				/*modelloLista1 = (DefaultListModel) ListaGroupList.getModel();
				modelloLista1.addElement(Artista);
				ListaGroupList.setModel(modelloLista1);*/
				
			}
			
			
		}
	}


	
	public void propertyChange(PropertyChangeEvent evt) {
		
		
	}
}
