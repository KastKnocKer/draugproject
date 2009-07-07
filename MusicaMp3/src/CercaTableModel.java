import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.*;

public class CercaTableModel extends AbstractMp3Model{
private JRadioButton ConSenzaCopertina,ConCopertina,SenzaCopertina,ConSenzaTesto,ConTesto,SenzaTesto;
private Lista lista;

String[] ColName = {"#ID","Percorso","Artista","Album","Titolo","Numero Traccia","Genere","Anno" };
private String selectedItem;
public final int catCol=1;
private JCheckBox ck[];

private Lista tmp=new Lista();

/*public CercaTableModel(Lista l){
	this.l=l;
}*/
public CercaTableModel(Lista l,JCheckBox ck[], String selectedItem){
	super(l);
	this.ck=ck;
	this.selectedItem=selectedItem;
	
}

public int getColumnCount() {
	return ColName.length;
}

public int getRowCount() {

	int Cont=0;
		for(int i=0;i<list.size();i++){
			if(ck[0].isSelected()){
				
				if(((Mp3)list.getObjPos(i)).GetTitolo().contains(selectedItem)){
					Cont++;
				}
				/*if(selectedItem.equals(((Mp3)list.getObjPos(i)).GetTitolo())){
					Cont++;
			    }*/
			}
			if(ck[1].isSelected()){
				if(((Mp3)list.getObjPos(i)).GetArtista().contains(selectedItem)){
					Cont++;
				}
				/*if(selectedItem.equals(((Mp3)list.getObjPos(i)).GetArtista())){
					
					Cont++;
				}*/
			}
			if(ck[2].isSelected()){
				
				if(((Mp3)list.getObjPos(i)).GetAlbum().contains(selectedItem)){
					Cont++;
				}
				/*if(selectedItem.equals(((Mp3)list.getObjPos(i)).GetAlbum())){
					
					Cont++;
				}*/
			}
			if(ck[3].isSelected()){
				
				if(((Mp3)list.getObjPos(i)).GetAnno().contains(selectedItem)){
					Cont++;
				}
				/*if(selectedItem.equals(((Mp3)list.getObjPos(i)).GetAnno())){
					
					Cont++;
				}*/
			}
			if(ck[4].isSelected()){
				if(((Mp3)list.getObjPos(i)).GetGenere().contains(selectedItem)){
					Cont++;
				}
				/*if(selectedItem.equals(((Mp3)list.getObjPos(i)).GetGenere())){
					
					Cont++;
				}*/
			}
		}
	return Cont;
}

public Object getValueAt(int row, int col) {
	
	
	if(ck[0].isSelected()){
		
		for(int i=0; i<list.size();i++){
			Mp3 brano=(Mp3)list.getObjPos(i);
			if((brano.GetTitolo()).contains(selectedItem)) tmp.insert(brano);
		}
		Mp3 ogg = (Mp3) tmp.getObjPos(row);
		switch(col){
		case 0: return row+1;
		case 1: return ((Mp3) ogg).GetPercorso();
		case 2: return ((Mp3) ogg).GetArtista(); 
		case 3: return ((Mp3) ogg).GetAlbum();
		case 4: return ((Mp3) ogg).GetTitolo();
		case 5: return ((Mp3) ogg).GetNumeroTraccia();
		case 6: return ((Mp3)ogg).GenereDaNumero();
		case 7: return ((Mp3) ogg).GetAnno();
		default: return "***";}
	}
	if(ck[1].isSelected()){
	
		for(int i=0; i<list.size();i++){
			Mp3 brano=(Mp3)list.getObjPos(i);
			if((brano.GetArtista()).contains(selectedItem)) tmp.insert(brano);
		}
		Mp3 ogg=(Mp3) tmp.getObjPos(row);
		switch(col){
		case 0: return row+1;
		case 1: return ((Mp3) ogg).GetPercorso();
		case 2: return ((Mp3) ogg).GetArtista(); 
		case 3: return ((Mp3) ogg).GetAlbum();
		case 4: return ((Mp3) ogg).GetTitolo();
		case 5: return ((Mp3) ogg).GetNumeroTraccia();
		case 6: return ((Mp3)ogg).GenereDaNumero();
		case 7: return ((Mp3) ogg).GetAnno();
		default: return "***";}
	}
	if(ck[2].isSelected()){
		for(int i=0; i<list.size();i++){
			Mp3 brano=(Mp3)list.getObjPos(i);
			if((brano.GetAlbum()).contains(selectedItem)) tmp.insert(brano);
		}
		Mp3 ogg=(Mp3) tmp.getObjPos(row);
		switch(col){
		case 0: return row+1;
		case 1: return ((Mp3) ogg).GetPercorso();
		case 2: return ((Mp3) ogg).GetArtista(); 
		case 3: return ((Mp3) ogg).GetAlbum();
		case 4: return ((Mp3) ogg).GetTitolo();
		case 5: return ((Mp3) ogg).GetNumeroTraccia();
		case 6: return ((Mp3)ogg).GenereDaNumero();
		case 7: return ((Mp3) ogg).GetAnno();
		default: return "***";}
	}
	if(ck[3].isSelected()){
		for(int i=0; i<list.size();i++){
			Mp3 brano=(Mp3)list.getObjPos(i);
			if((brano.GetAnno()).contains(selectedItem)) tmp.insert(brano);
		}
		Mp3 ogg=(Mp3) tmp.getObjPos(row);
		switch(col){
		case 0: return row+1;
		case 1: return ((Mp3) ogg).GetPercorso();
		case 2: return ((Mp3) ogg).GetArtista(); 
		case 3: return ((Mp3) ogg).GetAlbum();
		case 4: return ((Mp3) ogg).GetTitolo();
		case 5: return ((Mp3) ogg).GetNumeroTraccia();
		case 6: return ((Mp3)ogg).GenereDaNumero();
		case 7: return ((Mp3) ogg).GetAnno();
		default: return "***";}
	}
	else
		for(int i=0; i<list.size();i++){
			Mp3 brano=(Mp3)list.getObjPos(i);
			if((brano.GetGenere()).contains(selectedItem)) tmp.insert(brano);
		}
		Mp3 ogg=(Mp3) tmp.getObjPos(row);
		switch(col){
		case 0: return row+1;
		case 1: return ((Mp3) ogg).GetPercorso();
		case 2: return ((Mp3) ogg).GetArtista(); 
		case 3: return ((Mp3) ogg).GetAlbum();
		case 4: return ((Mp3) ogg).GetTitolo();
		case 5: return ((Mp3) ogg).GetNumeroTraccia();
		case 6: return ((Mp3)ogg).GenereDaNumero();
		case 7: return ((Mp3) ogg).GetAnno();
		default: return "***";}
	}
	
public String getColumnName(int col){
	return ColName[col];
}
public boolean isCellEditable(int row, int col)
{
// nessuna cella editabile
return false;
}
public Lista ReturnList(){
	return tmp;
}
}

