import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
public class AscoltatoreCerca implements ActionListener{
private Lista l, listaconsenzaTestoArtwork;
private JTable tab;
private Mp3TableModel dataModel;
private JTextField selectedItem;
private JCheckBox ck[];

private JOptionPane pane = new JOptionPane();
private JRadioButton ConSenzaCopertina,ConCopertina,SenzaCopertina,ConSenzaTesto,ConTesto,SenzaTesto;


public AscoltatoreCerca(JTextField selectedItem,Lista l,JTable tab,JCheckBox ck[],JRadioButton ConSenzaCopertina,JRadioButton ConCopertina,JRadioButton SenzaCopertina,JRadioButton ConSenzaTesto,JRadioButton ConTesto,JRadioButton SenzaTesto){
	this.ck=ck;
	this.selectedItem=selectedItem;
	this.l=l;
	this.tab=tab;
	this.ConSenzaTesto = ConSenzaTesto;
	this.ConTesto = ConTesto;
	this.SenzaTesto = SenzaTesto;
	
	this.ConSenzaCopertina = ConSenzaCopertina;
	this.ConCopertina = ConCopertina;
	this.SenzaCopertina = SenzaCopertina;
	
	dataModel = (Mp3TableModel)tab.getModel();
}

public void actionPerformed(ActionEvent e) {

		
		listaconsenzaTestoArtwork = new Lista();
		
		if(ConSenzaCopertina.isSelected()){
			
				listaconsenzaTestoArtwork.insertWithArtwork(l);
				listaconsenzaTestoArtwork.insertNoWithArtwork(l);
			
		}
		
		if(ConCopertina.isSelected()){
			listaconsenzaTestoArtwork.insertWithArtwork(l);
		}
		if(SenzaCopertina.isSelected()){
			listaconsenzaTestoArtwork.insertNoWithArtwork(l);
		}
		
		//DA CONTROLLARE... 
		/*
		
		if(ConSenzaTesto.isSelected()){
			
			listaconsenzaTestoArtwork.insertWithLyric(l);
			listaconsenzaTestoArtwork.insertNoWithLyric(l);
		
		}
		if(ConTesto.isSelected()){
			listaconsenzaTestoArtwork.insertWithLyric(l);
		}
		if(SenzaTesto.isSelected()){
			listaconsenzaTestoArtwork.insertNoWithLyric(l);
		}*/
		
		//System.out.println(listaconsenzaTestoArtwork);
	
		/*for(int i=0;i<listaconsenzaTestoArtwork.size();i++){
			System.out.print(((Mp3)listaconsenzaTestoArtwork.getObjPos(i)).GetTitolo()+" *** "+ ((Mp3)listaconsenzaTestoArtwork.getObjPos(i)).hasCopertina());
		}*/
	int j=0;
	 
	for (int i = 0;i < 5;i++){
		if (ck[i].isSelected()){
			j ++;
		}
	}

	if (j!=1){
	
		JOptionPane.showMessageDialog(null,"Selezionare un solo criterio di ricerca...","Attenzione!!",JOptionPane.WARNING_MESSAGE);
	}
	else
		{tab.setModel(new CercaTableModel(listaconsenzaTestoArtwork,ck,selectedItem.getText()));
		CercaTableModel mod=(CercaTableModel)tab.getModel();
		mod.fireTableDataChanged();
		}
}
}
