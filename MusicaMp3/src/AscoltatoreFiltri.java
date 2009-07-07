import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


public class AscoltatoreFiltri implements ActionListener {
	private Lista l,listaFiltri,lista;
	private Node tump;
	private JTable tab;
	private Mp3TableModel dataModel;
	private JComboBox FiltroGenere,FiltroArtista,FiltroAlbum,FiltroAnno;
	public AscoltatoreFiltri(Lista l,Lista listaFiltri,JTable tab,JComboBox FiltroGenere,JComboBox FiltroArtista,JComboBox FiltroAlbum,JComboBox FiltroAnno){
		this.l = l;
		this.listaFiltri = listaFiltri;
		this.tab=tab;
		this.FiltroGenere=FiltroGenere;
		this.FiltroArtista=FiltroArtista;
		this.FiltroAlbum=FiltroAlbum;
		this.FiltroAnno=FiltroAnno;
		dataModel = (Mp3TableModel)tab.getModel();
	}
	
	public void actionPerformed(ActionEvent e) {
		
    
		
		
		JComboBox cb = (JComboBox)e.getSource();
		String selectedItem =(String)cb.getSelectedItem();
		
		if(cb == FiltroGenere){
				tab.setModel(new FiltriTableModel(selectedItem, l,FiltroGenere,null,null,null));
				/*FiltriTableModel mod=(FiltriTableModel)tab.getModel();
				mod.fireTableDataChanged();*/}
				
		if(cb == FiltroArtista){
			tab.setModel(new FiltriTableModel(selectedItem, l,null,FiltroArtista,null,null));
			FiltriTableModel mod=(FiltriTableModel)tab.getModel();
			mod.fireTableDataChanged();}
		
		
		if(cb == FiltroAlbum){
			tab.setModel(new FiltriTableModel(selectedItem, l,null,null,FiltroAlbum,null));
			FiltriTableModel mod=(FiltriTableModel)tab.getModel();
			mod.fireTableDataChanged();}
	
		
		if(cb == FiltroAnno){
			tab.setModel(new FiltriTableModel(selectedItem, l,null,null,null,FiltroAnno));
			FiltriTableModel mod=(FiltriTableModel)tab.getModel();
			mod.fireTableDataChanged();}


}
	
}

	

