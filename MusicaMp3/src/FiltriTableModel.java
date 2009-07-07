import javax.swing.JComboBox;
import javax.swing.table.*;

public class FiltriTableModel extends AbstractMp3Model{

	private static final long serialVersionUID = 1L;
	private JComboBox FiltroGenere,FiltroArtista,FiltroAlbum,FiltroAnni;

	String[] ColName = {"#ID","Percorso","Artista","Album","Titolo","Numero Traccia","Genere","Anno" };
	String scelta1,scelta2,scelta3,scelta4;

	private static Lista listaperprovare;
	Lista tmp=new Lista();
	public final int catCol=1;
	String selectedItem;
	
	public FiltriTableModel(String selectedItem, Lista l,JComboBox FiltroGenere,JComboBox FiltroArtista,JComboBox FiltroAlbum,JComboBox FiltroAnni){
		super(l);
		
		
		this.selectedItem=selectedItem;
		this.FiltroGenere=FiltroGenere;
		this.FiltroArtista=FiltroArtista;
		this.FiltroAlbum=FiltroAlbum;
		this.FiltroAnni=FiltroAnni;
	}
	
	
	
	public int getColumnCount() {
		return ColName.length;
	}

	public int getRowCount() {
		int Cont=0;
		if(FiltroGenere!=null){
			Cont=0;
			for(int i=0;i<list.size();i++){
				if(selectedItem.equals(((Mp3)list.getObjPos(i)).GetGenere())){
					Cont++;
				}
			}
		}
		if(FiltroArtista!=null){
			Cont=0;
			for(int i=0;i<list.size();i++){
			if(selectedItem.equals(((Mp3)list.getObjPos(i)).GetArtista())){
				Cont++;
			}
		}}
		if(FiltroAlbum!=null){
			Cont=0;
			for(int i=0;i<list.size();i++){
			if(selectedItem.equals(((Mp3)list.getObjPos(i)).GetAlbum())){
				Cont++;
			}
		}}
		if(FiltroAnni!=null){
			Cont=0;
			for(int i=0;i<list.size();i++){
			if(selectedItem.equals(((Mp3)list.getObjPos(i)).GetAnno())){
				Cont++;
			}
		}
		}
		
		return Cont;
	}

	public Object getValueAt(int row, int col) {
		//Lista tmp=new Lista();
		if(FiltroGenere !=null){
			
			for(int i=0; i<list.size();i++){
				Mp3 brano=(Mp3)list.getObjPos(i);
				if(brano.GetGenere().equals(selectedItem)) tmp.insert(brano);
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
		if(FiltroArtista!=null){
		
			for(int i=0; i<list.size();i++){
				Mp3 brano=(Mp3)list.getObjPos(i);
				if(brano.GetArtista().equals(selectedItem)) tmp.insert(brano);
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
		if(FiltroAlbum!=null){
			for(int i=0; i<list.size();i++){
				Mp3 brano=(Mp3)list.getObjPos(i);
				if(brano.GetAlbum().equals(selectedItem)) tmp.insert(brano);
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
			{
			for(int i=0; i<list.size();i++){
				Mp3 brano=(Mp3)list.getObjPos(i);
				if(brano.GetAnno().equals(selectedItem)) tmp.insert(brano);
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



