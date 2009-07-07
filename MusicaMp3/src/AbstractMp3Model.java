import javax.swing.table.AbstractTableModel;


public abstract class AbstractMp3Model extends AbstractTableModel{

	Lista list;
	
public AbstractMp3Model(Lista lista){
	super();
	this.list=lista;
}

public void SetLista(Lista l){
	this.list=l;
}
public Lista ReturnList(){
	return list;
}
}
