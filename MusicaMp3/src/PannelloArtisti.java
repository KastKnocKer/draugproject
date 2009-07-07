import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;
public class PannelloArtisti extends JPanel implements MouseListener,ActionListener{
private JList ListaArtistiList,ListaGroupList;
private ListModel listModel,listModel1;
private SchedaArtSolPanel ArtistiSolPanel;
private SchedaArtGroupPanel ArtistiGroupPanel;
private JScrollPane listScroller,listScroller1;
private int row,row1;
private JPopupMenu popup;
private Lista ListaArtisti,ListaGruppi;
private JMenuItem Visualizza,Elimina;
private String vector[];
private EventiMouseArtisti EMA;

public PannelloArtisti(Lista ListaArtisti,Lista ListaGruppi){
	
	this.ListaArtisti = ListaArtisti;
	this.ListaGruppi=ListaGruppi;
	
    setLayout( new BorderLayout());
	
    listModel = new DefaultListModel();
    listModel1 = new DefaultListModel();
    
    
ListaArtistiList = new JList(listModel);
ListaGroupList = new JList(listModel);

//prima lista fatta dagli artisti solisti
ListaArtistiList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
ListaArtistiList.setLayoutOrientation(JList.VERTICAL);
ListaArtistiList.addMouseListener(this);



ListaGroupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
ListaGroupList.setLayoutOrientation(JList.VERTICAL);

//ascoltatore esterno per non entrare in conflitto con 
//gli eventi dell'altra lista fatta dagli artisti
EMA = new EventiMouseArtisti(this, ListaGruppi,ListaGroupList);
ListaGroupList.addMouseListener(EMA);

listScroller = new JScrollPane(ListaArtistiList);
listScroller1 = new JScrollPane(ListaGroupList);



ArtistiSolPanel = new SchedaArtSolPanel();
ArtistiSolPanel.SchedaArtSolPanel1(this);
add(ArtistiSolPanel,BorderLayout.LINE_START);

ArtistiGroupPanel = new SchedaArtGroupPanel();
ArtistiGroupPanel.SchedaArtGroupPanel1(this);
add(ArtistiGroupPanel,BorderLayout.LINE_END);



ArtistiSolPanel.setVisible(false);
ArtistiGroupPanel.setVisible(false);

Border etchedBordo = BorderFactory.createEtchedBorder();
Border titledBordo = BorderFactory.createTitledBorder(etchedBordo, "Lista schede Artisti Solisti");
Border emptyBordo  = BorderFactory.createEmptyBorder(0,0,0,0);
Border compoundBordo=BorderFactory.createCompoundBorder(titledBordo, emptyBordo);

add(listScroller);
listScroller.setVisible(true);
listScroller.setBorder(compoundBordo);

Border etchedBordo1 = BorderFactory.createEtchedBorder();
Border titledBordo1 = BorderFactory.createTitledBorder(etchedBordo1, "Lista schede Gruppi Musicali");
Border emptyBordo1  = BorderFactory.createEmptyBorder(0,0,0,0);
Border compoundBordo1=BorderFactory.createCompoundBorder(titledBordo1, emptyBordo1);

add(listScroller1);
listScroller1.setVisible(true);
setVisible(true);
listScroller1.setBorder(compoundBordo1);


}

public void paint(Graphics g){
	
super.paint(g);
ArtistiGroupPanel.setLocation(6, 19);
listScroller.setBounds(5, 15, 350, 185);
listScroller1.setBounds(360, 15, 350, 185);
//listScroller1.setLocation(0, 710);

}

public JList ReturnListaArt(){
	return ListaArtistiList;
}

public JList ReturnListaArtGroup(){
	return ListaGroupList;
}

/**
 * Funzione che restituisce la schermata iniziale formata solo dalla lista
 * degli artisti
 * 
 */

public void RitornaPA(){
	
	
	ArtistiSolPanel.setVisible(false);
	ArtistiGroupPanel.setVisible(false);
	listScroller1.setVisible(true);
	listScroller.setVisible(true);
}


/**
 * Funzione che restituisce il pannello degli artisti soliti
 * prendendo come paramentro la lista degli artisti e la posizione della scheda all'interno
 * della lista
 * 
 */

public void VisualizzaSchedaArtSol(Lista ListaArtisti, int k){
	
	
	ArtistiGroupPanel.setVisible(false);
	
	ArtistiSolPanel.ModificaCampi((MusicArtSol)ListaArtisti.getObjPosMA(k));
	ArtistiSolPanel.setVisible(true);
	listScroller.setVisible(false);
	listScroller1.setVisible(false);
}

/**
 * 
 * funzione che restituisce il pannello degli artisti in gruppi
 */
public void VisualizzaSchedaArtGroup(Lista ListaArtisti, int k){
	

	
	ArtistiGroupPanel.ModificaCampi((MusicArtGroup)ListaGruppi.getObjPosMA(k));
	ArtistiGroupPanel.setVisible(true);
	

	listScroller.setVisible(false);
	listScroller1.setVisible(false);
	ArtistiSolPanel.setVisible(false);
	
	
}

public void mouseClicked(MouseEvent e) {
	if(e.getButton() != MouseEvent.BUTTON1)
	{
		
		popup = new JPopupMenu();
		Visualizza= new JMenuItem("Visualizza ");
		Elimina = new JMenuItem("Elimina");
		
		popup.add(Visualizza);
    	popup.add(Elimina); 
    	
    	Visualizza.addActionListener(this);
    	Elimina.addActionListener(this);
    
    	popup.show(e.getComponent(),e.getX(), e.getY());
    	
    	
    	
    	//Row prende il valore che clicco nella lista
    	row = ListaArtistiList.getSelectedIndex();
    	//row1 = ListaGroupList.getSelectedIndex();
    	
    	System.out.println(row);
    	//System.out.println(row1);
    	
    	//Manca gestione dei JMenuItem Elimina
	}
	/*if(e.getButton() != MouseEvent.MOUSE_LAST){
		row = ListaArtistiList.getSelectedIndex();
		
		System.out.printf("%d",row);
		//ArtistiSolPanel.ModificaCampi((MusicArtSol)ListaArtisti.getObjPosMA(row));
		this.VisualizzaSchedaArtSol(ListaArtisti, row);
	}*/
}


public void actionPerformed(ActionEvent e) {
	if (e.getSource()==Visualizza){
		
		this.VisualizzaSchedaArtSol(ListaArtisti, row);
		
		
	}
	
	if(e.getSource()==Elimina){
		
		vector = new String[ListaArtisti.sizeMA()];
		for(int i=0; i<ListaArtisti.sizeMA();i++){
			vector[i] = ((MusicArtSol)ListaArtisti.getObjPosMA(i)).getNomeArte();
		}
		
		ListaArtisti.removeMA((MusicArtSol)ListaArtisti.getObjPosMA(row));
		
		
		vector[row]="";
		ListaArtistiList.setListData(vector);
		
		System.out.println("Eliminata");
	}
}
@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}



}


