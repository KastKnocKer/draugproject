import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


public class EventiMouseArtisti implements MouseListener,ActionListener{
	private JMenuItem Visualizza,Elimina;
	private JPopupMenu popup;
	private int row;
	private JList ListaGroupList;
	private PannelloArtisti pa;
	private Lista ListaGruppi;
	private String vector[];
	
	public EventiMouseArtisti(PannelloArtisti pa,Lista ListaGruppi,JList ListaGroupList){
		this.ListaGroupList = ListaGroupList;
		this.ListaGruppi=ListaGruppi;
		this.pa=pa;
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
    	row = ListaGroupList.getSelectedIndex();
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==Visualizza){
			
			pa.VisualizzaSchedaArtGroup(ListaGruppi, row);
			
			
		}
		
		if(e.getSource()==Elimina){
			
			vector = new String[ListaGruppi.sizeMA()];
			for(int i=0; i<ListaGruppi.sizeMA();i++){
				vector[i] = ((MusicArtGroup)ListaGruppi.getObjPosMA(i)).getName();
			}
			
			ListaGruppi.removeMA((MusicArtGroup)ListaGruppi.getObjPosMA(row));
			
			
			vector[row]="";
			ListaGroupList.setListData(vector);
			
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
