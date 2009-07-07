import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

/** 
 * Lista a nodi che implementa l'interfaccia List
 * 
 * @author Forliano Gerardo
 * */
public class Lista implements List {
	
	/**
	 * Variabile che linka il primo elemento.
	 */
	private NodeArt MyNode;
	private Node node;
	private Lista LinkLibreriaGlobale=this;
	
	private String Tipo;
	private String Tipi[];
	/**
	 * Costruttori.
	 */
	public Lista(){setNode(null);setNodeMA(null);}
	
	public Lista(Mp3 b){setNode(new Node(b));}
	
	public Lista(Mp3 b,List l){if(l!=null) revInsert(l); insert(b);}
	
	
	// per gli artisti
	public Lista(MusicArt b){setNodeMA(new NodeArt(b));}
	/**
	 * Restituisce l'oggetto in testa.
	 */
	public Mp3 head(){
		if(isEmpty()) return null; else return getNode().B;
		}
	/**
	 * Restituisce la coda della lista.
	 */
	public Lista tail(){
		if(isEmpty()) return null;
		else
		{
			Lista tmp=new Lista();
			tmp.setNode(getNode().Next);
			return tmp;
		}
	}
	/**
	 * Inserisce inversamente.
	 */
	private void revInsert(List l){
		if (!l.isEmpty()){
			revInsert(l.tail());
			insert(l.head());
		}
	}
	/**
	 * Dice se la lista è vuota.
	 */
	public boolean isEmpty(){
		return (getNode()==null);
	}
	
	/**
	 * Dice Se la lista di artisti è vuota
	 */
	
	public boolean isEmptyMA(){
		return (getNodeMA()==null);
	}
	/**
	 * Restituisce l'ultimo elemento della lista.
	 */
	public Mp3 last(){
		if(isEmpty()) return null;
		Node tmp=getNode();
		while(tmp.Next!=null) tmp=tmp.Next;
		return tmp.B;
	}
	/**
	 * Inserisce un oggetto nella lista.
	 */
	public void insert(Mp3 b){
		node = new Node(b,node);
	}
	
	/**
	 * Inserisce un oggetto MusicArt nella lista.
	 */
	
	public void insertMA(MusicArt b){
		MyNode = new NodeArt(b,MyNode);
		
		
		
		
	}
	
	/**
	 * inserisce nella lista, gli elementi della lista passanti per parametro solo se l'mp3 ha la copertina
	 * @param l
	 */
public void insertWithArtwork(Lista l){
	for(int i=0;i<l.size();i++){
		if(((Mp3)l.getObjPos(i)).hasCopertina()){
			insert((Mp3)l.getObjPos(i));
		}
	}
}

public void insertNoWithArtwork(Lista l){
	for(int i=0;i<l.size();i++){
		if(!((Mp3)l.getObjPos(i)).hasCopertina()){
			insert((Mp3)l.getObjPos(i));
		}
	}
}
	public void insertWithLyric(Lista l){
		
		//controlla gli mp3 che ha gia in lista
		for(int i=0;i<size();i++){
			if(!((Mp3)getObjPos(i)).HasLyric()){
				remove((Mp3)getObjPos(i));
			}
		}
		//controlla la lista rimanente
		for(int j=0;j<size();j++){
			if(((Mp3)l.getObjPos(j)).HasLyric()){
				insert((Mp3)l.getObjPos(j));
			}
		}
	}
	
	public void insertNoWithLyric(Lista l){
		
		//controlla gli mp3 che ha gia in lista
		for(int i=0;i<size();i++){
			if(((Mp3)getObjPos(i)).HasLyric()){
				remove((Mp3)getObjPos(i));
			}
		}
		//controlla la lista rimanente
		for(int j=0;j<l.size();j++){
			if(!((Mp3)l.getObjPos(j)).HasLyric()){
					insert((Mp3)l.getObjPos(j));
			}
		}
	}
	
	/**
	 * Inserisce un oggetto nella lista, a seconda se hanno la copertina o il testo
	 */
	public void insert(Mp3 b,boolean WithArtwork,boolean WithLyric,int ConA,int ConL){
		//se ho selezionato entrambi i campi ConSEnzaCopertina e ConSenzaTesto
		if(ConA==1 && ConL==1){
				insert(b);	
				return;
		}
		
		//se ho selezionato ConSenzaCopertina e ConTesto
		if(ConA == 1 && WithLyric==true){
			if(b.HasLyric()){
				insert(b);
				return;
			}
				
		}
		//se ho selezionato ConSenzaCopertina e SenzaTesto
		if(ConA == 1 && WithLyric==false){
			if(!b.HasLyric()){
				insert(b);
				return;
			}
		}
		
		//se ho selezionato ConCopertina e ConSenzaTesto
		if(WithArtwork==true && ConL==1){
			if(b.hasCopertina()){
				insert(b);
				return;}
		}
		//se ho selezionato ConCopertina e ConTesto
			if(WithArtwork==true && WithLyric==true){
				if(b.hasCopertina() && b.HasLyric()){
					insert(b);
					return;
				}
			}
		//se ho selezionato ConCopertina e SenzaTesto
			if(WithArtwork==true && WithLyric==false){
				if(b.hasCopertina() && !b.HasLyric()){
					insert(b);
					return;
				}
			}
		//se ho selezionato SenzaCopertina e ConSenzaTesto
			if(WithArtwork==false && ConL==1){
				if(!b.hasCopertina()){
					insert(b);
					return;
				}
			}
			//se ho selezionato SenzaCopertina e ConTesto
			if(WithArtwork==false && WithLyric==true){
				if(!b.hasCopertina() && b.HasLyric()){
					insert(b);
					return;
				}
			}
			
			//se ho selezionato SenzaCopertina e SenzaTesto
			if(WithArtwork==false && WithLyric==false){
				if(!b.hasCopertina() && ! b.HasLyric()){
					insert (b);
					return;
				}
			}
		
	}
	/**
	 * Rimuove un oggetto MP3 dalla lista.
	 */
	public void remove(Mp3 b){
		if(!isEmpty()){
			Node pred=getNode();
			Node tmp=getNode().Next;
			if(getNode().B.equals(b)) setNode(getNode().Next);
			else
				while(tmp!=null)
					if(tmp.B.equals(b)){
						pred.Next=tmp.Next;
						return;
					}
					else
					{
						pred=pred.Next;
						tmp=tmp.Next;
					}
		}
	}
	
	/**
	 * Rimuove un oggetto scheda artista dalla lista.
	 */
	public void removeMA(MusicArt b){
		if(!isEmptyMA()){
			NodeArt pred=getNodeMA();
			NodeArt tmp=getNodeMA().Next;
			if(getNodeMA().B.equals(b)) setNodeMA(getNodeMA().Next);
			else
				while(tmp!=null)
					if(tmp.B.equals(b)){
						pred.Next=tmp.Next;
						return;
					}
					else
					{
						pred=pred.Next;
						tmp=tmp.Next;
					}
		}
	}
	/**
	 * Restituisce il numero di elementi della lista.
	 */
	public int size(){
		Node tmp=getNode();
		int cont=0;
		while(tmp!=null){
			tmp=tmp.Next;
			cont++;
		}
		return cont;
	}
	
	/**
	 *  Restituisce il numero di MusicArt nella lista.
	 * 
	 */
	
	public int sizeMA(){
		NodeArt tmp = getNodeMA();
		int cont = 0;
		while(tmp!=null){
			tmp=tmp.Next;
			cont++;
		}
		return cont;
		
	}
	/**
	 * Dice se la lista contiene l'oggetto Mp3 richiesto.
	 */
	public boolean contains(Mp3 b){
		Node tmp=getNode();
		while(tmp!=null){
			if(tmp.B.equals(b)) return true;
			tmp=tmp.Next;
		}
		return false;
	}
	
	/**
	 * Dice se la lista contiene l'artista richiesto
	 */
	
	public boolean containsMA(MusicArt b){
		NodeArt tmp=getNodeMA();
		while(tmp!=null){
			if(tmp.B.equals(b)) return true;
			tmp = tmp.Next;
		}
		return false;
	}
	
	/**
	 * Restituisce la stringa dei dati della lista.
	 */
	public String toString(){
		String tmp="";
		for(Node i=getNode() ; i!=null ; i=i.Next) tmp=tmp + " " + i.B.toString();
		return tmp;
	}
	
	public String Mp3String(int Pos){
		Node tmb=getNode();
		if(isEmpty()) return null;
		if(Pos < this.size()){
			for(int i=0;i<Pos;i++) tmb=tmb.Next;
			return tmb.B.toString();
		}
		return null;
			
		
	}
////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Metodi Aggiunti//////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
	/**
	 * Restituisce l'elemento di posizione n richiesta. (n<lenght)
	 */
	public Object getObjPos(int Pos){
	Node tmp=getNode();
	if(isEmpty()) return null;
	if(Pos < this.size()){
		for(int i=0;i<Pos;i++) tmp=tmp.Next;
		return tmp.B;
	}
	return null;	
	}
	
	/** 
	 * Restituisce l'elemento Artista di posizione n richiesta.
	 */
	
	public Object getObjPosMA(int Pos){
		NodeArt tmp = getNodeMA();
		if(isEmptyMA()) return null;
		if(Pos < this.sizeMA()){
			for(int i=0;i<Pos;i++) tmp=tmp.Next;
			return tmp.B;
		}
		return null;
	}

	
	public void setLista(){
		node=LinkLibreriaGlobale.getNode();
	}
	public void nodoNullo(){
		node = null;
	}
	public void ScambiaLista(Lista lista){
		node = null;
		node = lista.getNode();
		
	}
	
	/**
	 * Setta il nodo dato
	 * @param node
	 */
	public void setNode(Node node) {
		this.node = node;
	}
	
	/**
	 * setta il nodo degli artisti
	 */
	
	
	//per gli artisti
	public void setNodeMA(NodeArt MyNode){
		this.MyNode = MyNode;
	}
	/**
	 * Restituisce il nodo
	 * @return Nodo
	 */
	public Node getNode() {
		return node;
	}
	
	
	/**
	 * Restituisce il NodoArt
	 * @return MyNode;
	 */
	
	public NodeArt getNodeMA(){
		return MyNode;
	}
	
	public void salvaLista() {
	
		FileWriter fSave = null;
		Node tmpN = node;
		//File mp3File = new File("Libreria Mp3.dat");
		try {
			fSave = new FileWriter(new File("Libreria.dat"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < size(); i++)
		{
			try {
				fSave.write(tmpN.B.GetPercorso(), 0, tmpN.B.GetPercorso().length());
				fSave.write('\n');
			} catch (IOException e) {
				e.printStackTrace();
			}
			tmpN = tmpN.Next;
		}
		try {
			fSave.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}

	public void leggiLista() throws ClassNotFoundException
	{
		FileReader fRead = null;
		try {
			fRead = new FileReader("Libreria.dat");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//LEGGE FIN CHE NON è FINITO IL FILE
		try {
			while(fRead.ready())
			{
				boolean go = true;
				String Buffer = "";
				do
				{
					char ch = (char)fRead.read();
					if(ch != '\n')
						Buffer += ch;
					else
						go = false;
				}
				while(go);
				try {
					this.insert(new Mp3(new File(Buffer)));
				} catch (TagException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ReadOnlyFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidAudioFrameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CannotReadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		}
	
	public void Svuota(){
		node.B=null;
		node.Next=null;
	}
	
	public void SvuotaMA(){
		MyNode.B=null;
		MyNode.Next=null;
	}
	
	public void caricaLibreriaGlobaleFiltrata(String Artista,String Album,String Genere,String Anno,String Titolo/*,int Copertina, int Lyric*/) {
		int NMp3 = LinkLibreriaGlobale.size();
		for (int i=0;i<NMp3;i++){
			
		    boolean Flag=true;
		   
		    if (Album!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).GetAlbum().toLowerCase().equals(Album.toLowerCase()) ) Flag=false;}
		    if (Artista!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).GetArtista().toLowerCase().equals(Artista.toLowerCase()) ) Flag=false;}
		    if (Genere!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).GetGenere().toLowerCase().equals(Genere.toLowerCase()) ) Flag=false;}
		    if (Anno!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).GetAnno().toLowerCase().equals(Anno.toLowerCase()) ) Flag=false;}
		    if (Titolo!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).GetTitolo().toLowerCase().equals(Titolo.toLowerCase()) ) Flag=false;}		
		    
		    
		    if (Album!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).GetAlbum().toLowerCase().startsWith(Album.toLowerCase()) ) Flag=false;}
		    if (Artista!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).GetArtista().toLowerCase().startsWith(Artista.toLowerCase()) ) Flag=false;}
		    if (Genere!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).GetGenere().toLowerCase().startsWith(Genere.toLowerCase()) ) Flag=false;}
		    if (Anno!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).GetAnno().toLowerCase().startsWith(Anno.toLowerCase()) ) Flag=false;}
		    if (Titolo!=null){ if ( !((Mp3)LinkLibreriaGlobale.getObjPos(i)).GetTitolo().toLowerCase().startsWith(Titolo.toLowerCase()) ) Flag=false;}		
		    
		    
		    /*if (Copertina>=0){
		    	if( (Copertina==0)&&( ((Mp3)LinkLibreriaGlobale.getObjPos(i)).hasCopertina()==true ) ) Flag=false;
		    	if( (Copertina==1)&&( ((Mp3)LinkLibreriaGlobale.getObjPos(i)).hasCopertina()==false ) ) Flag=false;
		    }*/
		    /*if (Lyric>=0){
		    	if( (Lyric==0)&&( ((Mp3)LinkLibreriaGlobale.getObjPos(i)).hasLyric()==true ) ) Flag=false;
		    	if( (Lyric==1)&&( ((Mp3)LinkLibreriaGlobale.getObjPos(i)).hasLyric()==false ) ) Flag=false;
		    }*/
		    	
		    
			if (Flag==true)this.insert( (Mp3) LinkLibreriaGlobale.getObjPos(i) );	
			
		}
		
		System.out.println("Caricamento filtrato da Libreria Globale, Terminato.");
		System.out.println("Elementi Trovati: " + this.size() );
		
	}
	
/*public String[] getAllCateg(){
	if(isEmpty()){
		return null;
	}
	if(size()==1){
		String tmp[] = new String[1];
		tmp[0]=((Mp3)getObjPos(0)).g
	}
}*/
public int returnNumeroGeneri(){
		int numeroGeneri=0;
		for(int i=0;i<this.size();i++){
			Boolean trovato=false;
			if(i==0){
				numeroGeneri++;
			}
			for(int j = i-1;j>=0;j--){
				if(((Mp3)this.getObjPos(j)).GetGenere().equals(((Mp3)this.getObjPos(i)).GetGenere())){
					trovato=true;
				}
			}
			if(!trovato && i!=0)numeroGeneri++;
		}
		
	return numeroGeneri;}

public int returnNumeroArtisti(){
	int numeroArtisti=0;
	for(int i=0;i<this.size();i++){
		Boolean trovato=false;
		if(i==0){
			numeroArtisti++;
		}
		for(int j = i-1;j>=0;j--){
			if(((Mp3)this.getObjPos(j)).GetArtista().equals(((Mp3)this.getObjPos(i)).GetArtista())){
				trovato=true;
			}
		}
		if(!trovato && i!=0)numeroArtisti++;
	}
	
return numeroArtisti;}

public int returnNumeroAlbum(){
	int numeroAlbum=0;
	for(int i=0;i<this.size();i++){
		Boolean trovato=false;
		if(i==0){
			numeroAlbum++;
		}
		for(int j = i-1;j>=0;j--){
			if(((Mp3)this.getObjPos(j)).GetAlbum().equals(((Mp3)this.getObjPos(i)).GetAlbum())){
				trovato=true;
			}
		}
		if(!trovato && i!=0)numeroAlbum++;
	}
	
return numeroAlbum;}

public int returnNumeroAnni(){
	int numeroAnni=0;
	for(int i=0;i<this.size();i++){
		Boolean trovato=false;
		if(i==0){
			numeroAnni++;
		}
		for(int j = i-1;j>=0;j--){
			if(((Mp3)this.getObjPos(j)).GetAnno().equals(((Mp3)this.getObjPos(i)).GetAnno())){
				trovato=true;
			}
		}
		if(!trovato && i!=0)numeroAnni++;
	}
	
return numeroAnni;
}


}

	
	
	
	
	/*public Contatore c;
	
	/*Metodo privato usato da alcune funzioni esso funziona in modo ricorsivo
	 * ricopiando tutti gli elemtni della lista passatagli nella nostra*/
	

	/*
	private void Copia_Lista(Lista l)
	{
		if(!l.Vuota())
		{
			Copia_Lista(l.Tail());
			Ins_Testa(l.Testa());
		}
	}
	
	/**
	 * Costruttore senza parametri della Lista vuota
	 * @return 
	 * 	 */

	/*
	public Lista()
	{
		node = null;
		c = new Contatore();
	}
	
	/*/
	/* * Costruttore di classe Lista con il primo elemento
	 * @param LibMp3 
	 */

	//Costruttore Lista con il primo elemento
	/*public Lista(Mp3 b)
	{
		node = new Nodo(b);
		c = new Contatore();
		c.Inc();
	}*/
	
	/**
	 * Costruttore di classe Lista 
	 * @param b
	 * @param l
	 */

	//Costruttore Lista con aggiunta di un elemtno in testa ad un'altra lista
	/*public Lista(Mp3 b, Lista l)
	{
		if(!l.Vuota())
			Copia_Lista(l);
		    Ins_Testa(b);
	}
	
	//Lista Vuota?
	public boolean Vuota()
	{
		if(node==null)
			return true;
		else
			return false;
	}
	
	
	//Restituisce l'elemento in testa
	public Mp3 Testa()
	{	
		return node.B;
	}
	
	//Lista senza il primo elemento
	public Lista Tail()
	{
		if(!Vuota())
		{
			Lista tmp = new Lista();
			tmp.node = node.Next;
			c.Dec();
			return tmp;
		}
		else
		{
			return null;
		}
		
	}
	
	//INSERISCE L'ELEMENTO IN TESTA
	public void Ins_Testa(Mp3 b)
	{
		c.Inc();
		b.PutPos(c.Vedi());
		node = new Nodo(b,node);
		
	}
	//INSERISCE L'ELEMENTO COME DA PROTOCOLLO SCELT0O IN CODA
	public void Ins_El(Mp3 b)
	{
		if(!Vuota())
		{
			//Lista ltemp = new Lista();
			//ltemp.node = node;
			while(/*ltemp.*//*node.Next != null)
			{
				/*ltemp.*//*node = /*ltemp.*//*node.Next;
			}
			c.Inc();
			b.PutPos(c.Vedi());
			/*ltemp.*//*node.Next = new Nodo(b,null);
		}	
		else
		{	
			Ins_Testa(b);
		}
	}
	
	
	public Object getObjPos(int Pos){
		Nodo tmp = node;
		if(isEmpty()) return null;
			for(int i=0;i<Pos;i++) tmp = tmp.Next;
			return tmp.B;}
	
	
	
	//INSERISCI ELEMENTO B DI SEGUITO ALLA POSIZIONE N DATAGLI COME PARAMETRO
	public void Ins_Pos(Mp3 b,int n)
	{
		if(n == c.Vedi())
			Ins_El(b);
		else
		{	
			b.PutPos(n+1);
			if(!Vuota() && n != 0)
			{
				Lista ltemp = new Lista();
				ltemp.node = node;
				while(ltemp.node.B.GetID() != n)	//SCORRO FINO ALLA POSIZIONE GIUSTA
				{
					ltemp.node=ltemp.node.Next;
				}
				ltemp.node.Next = new Nodo(b, ltemp.node.Next); //HO AGGIUNTO IL NUOVO ELEMNTO
				c.Inc();
				for(;n < c.Vedi()-1; n++)			//DEVO RIMETTERE LE POSIZIONI IN ORDINE
				{
					ltemp.node = ltemp.node.Next;
					ltemp.node.Next.B.PutPos(n+2);
				}
			}	
			else
				Ins_Testa(b);
		}
	}
	
	//ELIMINA ELEMENTO IN POSZIONE N
	public void Remove_Pos(int n)
	{
		Lista ltemp1 = new Lista();
		Lista ltemp2 = new Lista();
		ltemp1.node = node.Next;
		ltemp2.node = node;
		if(n == 1 || c.Vedi() == 1)
		{
			node=node.Next;
			c.Dec();
		}
		else
		{
			if(!Vuota() && n != 0 && n <= c.Vedi())
			{
				while(ltemp1.node.B.GetID() != n)
				{
					ltemp2.node=ltemp1.node;
					ltemp1.node=ltemp1.node.Next;
				}
				ltemp2.node.Next = ltemp1.node.Next;
				c.Dec();
			}
		}
		for(; n <= c.Vedi(); n++)
		{
			ltemp2.node.Next.B.PutPos(n);
			ltemp2.node = ltemp2.node.Next;
		}
		
	}
	/*
	//ORDINAMENTO DELLA LISTA PER NOME DALLA A-Z (1) O DALLA Z-A (2)
	public void Ordina_Nome(int n)
	{
		if(!Vuota() && node.Next == null)
		{
			Lista Ltmp = new Lista();
			Nodo Ntmp, Ncmp;
			switch(n)
			{
				//ORDINA PER NOME ALFABETICO A-Z
				case 1:		
				{
					int Pos_el;
					while(node != null)
					{
						Ntmp = node.Next;
						Ncmp = node;
						for(int i = 0; i < (c.Vedi()-1); i++)
						{
							if(!(Ncmp.B.GetNome().compareTo(Ntmp.B.GetNome())<0))
								Ncmp = Ntmp;
							Ntmp=Ntmp.Next;
						}
						Pos_el = Ncmp.B.GetID();	//TENGO LA POSIZIONE DA ELIMINARE
						Ltmp.Ins_El(new Mp3(Ncmp.B));		//INSERISCO L'ELEMENTO IN CODA
						Remove_Pos(Pos_el);			//ELIMINO L'ELEMENTO SPOSTATO PER NON RICONTARLO
					}	
					break;
				}
			
				//ORDINA PER NOME ALFABETICO Z-A
				case 2:		
				{
					int Pos_el;
					while(node != null)
					{
						Ntmp = node.Next;
						Ncmp = node;
						for(int i = 0; i < (c.Vedi()-1); i++)
						{
							if((Ncmp.B.GetNome().compareTo(Ntmp.B.GetNome())<0))
								Ncmp = Ntmp;
							Ntmp=Ntmp.Next;
						}
						Pos_el = Ncmp.B.GetID();	//TENGO LA POSIZIONE DA ELIMINARE
						Ltmp.Ins_El(new Mp3(Ncmp.B));		//INSERISCO L'ELEMENTO IN CODA
						Remove_Pos(Pos_el);			//ELIMINO L'ELEMENTO SPOSTATO PER NON RICONTARLO
					}	
					break;
				}	
			}
			node = Ltmp.node;
			c = Ltmp.c;
		}
	}
		
	//ORDINAMENTO DELLA LISTA PER PATH DALLA A-Z (1) O DALLA Z-A (2)
	public void Ordina_Path(int n)
	{
		if(!Vuota() && node.Next == null)
		{
			Lista Ltmp = new Lista();
			Nodo Ntmp, Ncmp;
			switch(n)
			{
				//ORDINA PER PATH ALFABETICO A-Z
				case 1:		
				{
					int Pos_el;
					while(node != null)
					{
						Ntmp = node.Next;
						Ncmp = node;
						for(int i = 0; i < (c.Vedi()-1); i++)
						{
							if(!(Ncmp.B.GetPath().compareTo(Ntmp.B.GetPath())<0))
								Ncmp = Ntmp;
							Ntmp=Ntmp.Next;
						}
						Pos_el = Ncmp.B.GetID();	//TENGO LA POSIZIONE DA ELIMINARE
						Ltmp.Ins_El(new Mp3(Ncmp.B));		//INSERISCO L'ELEMENTO IN CODA
						Remove_Pos(Pos_el);			//ELIMINO L'ELEMENTO SPOSTATO PER NON RICONTARLO
					}	
					break;
				}
				
				//ORDINA PER PATH ALFABETICO Z-A
				case 2:		
				{
					int Pos_el;
					while(node != null)
					{
						Ntmp = node.Next;
						Ncmp = node;
						for(int i = 0; i < (c.Vedi()-1); i++)
						{
							if((Ncmp.B.GetPath().compareTo(Ntmp.B.GetPath())<0))
								Ncmp = Ntmp;
							Ntmp=Ntmp.Next;
						}
						Pos_el = Ncmp.B.GetID();	//TENGO LA POSIZIONE DA ELIMINARE
						Ltmp.Ins_El(new Mp3(Ncmp.B));		//INSERISCO L'ELEMENTO IN CODA
						Remove_Pos(Pos_el);			//ELIMINO L'ELEMENTO SPOSTATO PER NON RICONTARLO
					}	
					break;
				}	
			}
			node = Ltmp.node;
			c = Ltmp.c;
		}
	}
			
	//ORDINAMENTO DELLA LISTA PER ARTISTA DALLA A-Z (1) O DALLA Z-A (2)
	public void Ordina_Artista(int n)
	{
		if(!Vuota() && node.Next == null)
		{
			Lista Ltmp = new Lista();
			Nodo Ntmp, Ncmp;
			switch(n)
			{
				//ORDINA PER ARTISTA ALFABETICO A-Z
				case 1:		
				{
					int Pos_el;
					while(node != null)
					{
						Ntmp = node.Next;
						Ncmp = node;
						for(int i = 0; i < (c.Vedi()-1); i++)
						{
							if(!(Ncmp.B.GetArtista().compareTo(Ntmp.B.GetArtista())<0))
								Ncmp = Ntmp;
							Ntmp=Ntmp.Next;
						}
						Pos_el = Ncmp.B.GetID();	//TENGO LA POSIZIONE DA ELIMINARE
						Ltmp.Ins_El(new Mp3(Ncmp.B));		//INSERISCO L'ELEMENTO IN CODA
						Remove_Pos(Pos_el);			//ELIMINO L'ELEMENTO SPOSTATO PER NON RICONTARLO
					}	
						break;
				}
					
				//ORDINA PER ARTISTA ALFABETICO Z-A
				case 2:		
				{
					int Pos_el;
					while(node != null)
					{
						Ntmp = node.Next;
						Ncmp = node;
						for(int i = 0; i < (c.Vedi()-1); i++)
						{
							if((Ncmp.B.GetArtista().compareTo(Ntmp.B.GetArtista())<0))
								Ncmp = Ntmp;
							Ntmp=Ntmp.Next;
						}
						Pos_el = Ncmp.B.GetID();	//TENGO LA POSIZIONE DA ELIMINARE
						Ltmp.Ins_El(new Mp3(Ncmp.B));		//INSERISCO L'ELEMENTO IN CODA
						Remove_Pos(Pos_el);			//ELIMINO L'ELEMENTO SPOSTATO PER NON RICONTARLO
					}	
					break;
				}	
			}
			node = Ltmp.node;
			c = Ltmp.c;
		}
	}
	
	//ORDINAMENTO DELLA LISTA PER ARTISTA DALLA A-Z (1) O DALLA Z-A (2)
	public void Ordina_Titolo(int n)
	{
		if(!Vuota() && node.Next == null)
		{
			Lista Ltmp = new Lista();
			Nodo Ntmp, Ncmp;
			switch(n)
			{
				//ORDINA PER TITOLO ALFABETICO A-Z
				case 1:		
				{
					int Pos_el;
					while(node != null)
					{
						Ntmp = node.Next;
						Ncmp = node;
						for(int i = 0; i < (c.Vedi()-1); i++)
						{
							if(!(Ncmp.B.GetTitolo().compareTo(Ntmp.B.GetTitolo())<0))
								Ncmp = Ntmp;
							Ntmp=Ntmp.Next;
						}
						Pos_el = Ncmp.B.GetID();	//TENGO LA POSIZIONE DA ELIMINARE
						Ltmp.Ins_El(new Mp3(Ncmp.B));		//INSERISCO L'ELEMENTO IN CODA
						Remove_Pos(Pos_el);			//ELIMINO L'ELEMENTO SPOSTATO PER NON RICONTARLO
					}	
						break;
				}
					
				//ORDINA PER TITOLO ALFABETICO Z-A
				case 2:		
				{
					int Pos_el;
					while(node != null)
					{
						Ntmp = node.Next;
						Ncmp = node;
						for(int i = 0; i < (c.Vedi()-1); i++)
						{
							if((Ncmp.B.GetTitolo().compareTo(Ntmp.B.GetTitolo())<0))
								Ncmp = Ntmp;
							Ntmp=Ntmp.Next;
						}
						Pos_el = Ncmp.B.GetID();	//TENGO LA POSIZIONE DA ELIMINARE
						Ltmp.Ins_El(new Mp3(Ncmp.B));		//INSERISCO L'ELEMENTO IN CODA
						Remove_Pos(Pos_el);			//ELIMINO L'ELEMENTO SPOSTATO PER NON RICONTARLO
					}	
					break;
				}	
			}
			node = Ltmp.node;
			c = Ltmp.c;
		}
	}
  
	//ORDINAMENTO DELLA LISTA PER Genere DALLA A-Z (1) O DALLA Z-A (2)
	public void Ordina_Genere(int n)
	{
		if(!Vuota() && node.Next == null)
		{
			Lista Ltmp = new Lista();
			Nodo Ntmp, Ncmp;
			switch(n)
			{
				//ORDINA PER GENERE ALFABETICO A-Z
				case 1:		
				{
					int Pos_el;
					while(node != null)
					{
						Ntmp = node.Next;
						Ncmp = node;
						for(int i = 0; i < (c.Vedi()-1); i++)
						{
							if(!(Ncmp.B.GetGenere().compareTo(Ntmp.B.GetGenere())<0))
								Ncmp = Ntmp;
							Ntmp=Ntmp.Next;
						}
						Pos_el = Ncmp.B.GetID();	//TENGO LA POSIZIONE DA ELIMINARE
						Ltmp.Ins_El(new Mp3(Ncmp.B));		//INSERISCO L'ELEMENTO IN CODA
						Remove_Pos(Pos_el);			//ELIMINO L'ELEMENTO SPOSTATO PER NON RICONTARLO
					}	
						break;
				}
					
				//ORDINA PER GENERE ALFABETICO Z-A
				case 2:		
				{
					int Pos_el;
					while(node != null)
					{
						Ntmp = node.Next;
						Ncmp = node;
						for(int i = 0; i < (c.Vedi()-1); i++)
						{
							if((Ncmp.B.GetGenere().compareTo(Ntmp.B.GetGenere())<0))
								Ncmp = Ntmp;
							Ntmp=Ntmp.Next;
						}
						Pos_el = Ncmp.B.GetID();	//TENGO LA POSIZIONE DA ELIMINARE
						Ltmp.Ins_El(new Mp3(Ncmp.B));		//INSERISCO L'ELEMENTO IN CODA
						Remove_Pos(Pos_el);			//ELIMINO L'ELEMENTO SPOSTATO PER NON RICONTARLO
					}	
					break;
				}	
			}
			node = Ltmp.node;
			c = Ltmp.c;
		}
	}
	
	//ORDINAMENTO DELLA LISTA PER NUMERO TRACCIA DAL MAGGIORE (1) O DAL MINORE (2)
	public void Ordina_NT(int n)
	{
		if(!Vuota() && node.Next == null)
		{
			Lista Ltmp = new Lista();
			Nodo Ntmp, Ncmp;
			switch(n)
			{
				//ORDINA NUMERO TRACCIA DAL MAGGIORE
				case 1:		
				{
					int Pos_el;
					while(node != null)
					{
						Ntmp = node.Next;
						Ncmp = node;
						for(int i = 0; i < (c.Vedi()-1); i++)
						{
							if(Ncmp.B.GetNT() < Ntmp.B.GetNT())
								Ncmp = Ntmp;
							Ntmp=Ntmp.Next;
						}
						Pos_el = Ncmp.B.GetID();	//TENGO LA POSIZIONE DA ELIMINARE
						Ltmp.Ins_El(new Mp3(Ncmp.B));		//INSERISCO L'ELEMENTO IN CODA
						Remove_Pos(Pos_el);			//ELIMINO L'ELEMENTO SPOSTATO PER NON RICONTARLO
					}	
						break;
				}
					
				//ORDINA NUMERO TRACCIA DAL MINORE
				case 2:		
				{
					int Pos_el;
					while(node != null)
					{
						Ntmp = node.Next;
						Ncmp = node;
						for(int i = 0; i < (c.Vedi()-1); i++)
						{
							if(Ncmp.B.GetNT() > Ntmp.B.GetNT())
								Ncmp = Ntmp;
							Ntmp=Ntmp.Next;
						}
						Pos_el = Ncmp.B.GetID();	//TENGO LA POSIZIONE DA ELIMINARE
						Ltmp.Ins_El(new Mp3(Ncmp.B));		//INSERISCO L'ELEMENTO IN CODA
						Remove_Pos(Pos_el);			//ELIMINO L'ELEMENTO SPOSTATO PER NON RICONTARLO
					}	
					break;
				}	
			}
			node = Ltmp.node;
			c = Ltmp.c;
		}
	}

	//ORDINAMENTO DELLA LISTA PER ANNO DALLA A-Z (1) O DALLA Z-A (2)
	public void Ordina_Anno(int n)
	{
		if(!Vuota() && node.Next == null)
		{
			Lista Ltmp = new Lista();
			Nodo Ntmp, Ncmp;
			switch(n)
			{
				//ORDINA PER ANNO DAL MAGGIORE
				case 1:		
				{
					int Pos_el;
					while(node != null)
					{
						Ntmp = node.Next;
						Ncmp = node;
						for(int i = 0; i < (c.Vedi()-1); i++)
						{
							if(Ncmp.B.GetAnno() < Ntmp.B.GetAnno())
								Ncmp = Ntmp;
							Ntmp=Ntmp.Next;
						}
						Pos_el = Ncmp.B.GetID();	//TENGO LA POSIZIONE DA ELIMINARE
						Ltmp.Ins_El(new Mp3(Ncmp.B));		//INSERISCO L'ELEMENTO IN CODA
						Remove_Pos(Pos_el);			//ELIMINO L'ELEMENTO SPOSTATO PER NON RICONTARLO
					}	
						break;
				}
					
				//ORDINA PER ANNO DAL MINORE
				case 2:		
				{
					int Pos_el;
					while(node != null)
					{
						Ntmp = node.Next;
						Ncmp = node;
						for(int i = 0; i < (c.Vedi()-1); i++)
						{
							if(Ncmp.B.GetAnno() > Ntmp.B.GetAnno())
								Ncmp = Ntmp;
							Ntmp=Ntmp.Next;
						}
						Pos_el = Ncmp.B.GetID();	//TENGO LA POSIZIONE DA ELIMINARE
						Ltmp.Ins_El(new Mp3(Ncmp.B));		//INSERISCO L'ELEMENTO IN CODA
						Remove_Pos(Pos_el);			//ELIMINO L'ELEMENTO SPOSTATO PER NON RICONTARLO
					}	
					break;
				}	
			}
			node = Ltmp.node;
			c = Ltmp.c;
		}
	}
	
	//FILTRO CHE RESTITUISCE UNA LISTA CON SOLO IL GENERE INDICATOGLI
	public Lista Filtro_Genere(String Genere)
	{
		Lista Lreturn = new Lista();
		Nodo tmp = node;
		for(int i = 0; i<c.Vedi(); i++)
		{
			if(tmp.B.GetGenere().equals(Genere))
				Lreturn.Ins_El(new Mp3(tmp.B));
			tmp = tmp.Next;
		}
		return Lreturn;
	}
	
	//FILTRO CHE RESTITUISCE UNA LISTA CON SOLO L'ARTISTA INDICATOGLI
	public Lista Filtro_Artista(String Artista)
	{
		Lista Lreturn = new Lista();
		Nodo tmp = node;
		for(int i = 0; i<c.Vedi(); i++)
		{
			if(tmp.B.GetArtista().equals(Artista))
				Lreturn.Ins_El(new Mp3(tmp.B));
			tmp = tmp.Next;
		}
		return Lreturn;
	}
	
	//FILTRO CHE RESTITUISCE UNA LISTA CON SOLO L'ALBUM INDICATOGLI
	public Lista Filtro_Album(String Album)
	{
		Lista Lreturn = new Lista();
		Nodo tmp = node;
		for(int i = 0; i<c.Vedi(); i++)
		{
			if(tmp.B.GetAlbum().equals(Album))
				Lreturn.Ins_El(new Mp3(tmp.B));
			tmp = tmp.Next;
		}
		return Lreturn;
	}
	
	//FILTRO CHE RESTITUISCE UNA LISTA CON SOLO L'ANNO INDICATOGLI
	public Lista Filtro_Anno(int Anno)
	{
		Lista Lreturn = new Lista();
		Nodo tmp = node;
		for(int i = 0; i<c.Vedi(); i++)
		{
			if(tmp.B.GetAnno() ==  Anno)
				Lreturn.Ins_El(new Mp3(tmp.B));
			tmp = tmp.Next;
		}
		return Lreturn;
	}
	
	//FUNZIONE CHE DATOGLI UN PARAMETRO RESTITUISCE RIEMPIE UN VETTORE CON TUTTI I TIPI DI QUEL PARAMETRO
	public void Combo_Stringhe(String str[], int n)
	{
		switch(n)
		{
			case 1:				//RIEMPI LA STRINGA CON TUTTI I GENERE
			{
				boolean uguale = false;
				Nodo tmp = node.Next;
				str[0] = node.B.GetGenere();
				int k = 1;
				for(int i = 1; i < c.Vedi(); i++)	//SCORRO LA LISTA PER ANALIZZARE TUTTI I GENERI
				{
					for(int s = 0; s < str.length && !uguale; s++)//CONTROLLO SE HO GIA INSERITO QUEL GENERE
						if(tmp.B.GetGenere().equals(str[s]))
							uguale = true;
					if(!uguale)
					{
						str[k] = tmp.B.GetGenere();
						k++;
					}
					else
						uguale = false ;
					tmp = tmp.Next;
				}
				break;
			}
			
			case 2:				//RIEMPI LA STRINGA CON TUTTI GL'ARTISTI
			{
				boolean uguale = false;
				Nodo tmp = node.Next;
				str[0] = node.B.GetArtista();
				int k = 1;
				for(int i = 1; i < c.Vedi(); i++)	//SCORRO LA LISTA PER ANALIZZARE TUTTI GL'ARTISTI
				{
					for(int s = 0; s < str.length && !uguale; s++)//CONTROLLO SE HO GIA INSERITO QUELL'ARTISTA
						if(tmp.B.GetArtista().equals(str[s]))
							uguale = true;
					if(!uguale)
					{
						str[k] = tmp.B.GetArtista();
						k++;
					}
					else
						uguale = false ;
					tmp = tmp.Next;
				}
				break;
			}
			
			case 3:				//RIEMPI LA STRINGA CON TUTTI GL'ANNI
			{
				boolean uguale = false;
				Nodo tmp = node.Next;
				str[0].valueOf(tmp.B.GetAnno());
				int k = 1;
				for(int i = 1; i < c.Vedi(); i++)	//SCORRO LA LISTA PER ANALIZZARE TUTTI GL'ANNI
				{
					for(int s = 0; s < str.length && !uguale; s++)//CONTROLLO SE HO GIA INSERITO QUELL'ANNO
						if(tmp.B.GetAnno() == str[s].hashCode())
							uguale = true;
					if(!uguale)
					{
						str[k].valueOf(tmp.B.GetAnno());
						k++;
						
					}
					else
						uguale = false ;
					tmp = tmp.Next;
				}
				break;
			}
			
			case 4:				//RIEMPI LA STRINGA CON TUTTI GL'ALBUM
			{
				boolean uguale = false;
				Nodo tmp = node.Next;
				str[0] = node.B.GetAlbum();
				int k = 1;
				for(int i = 1; i < c.Vedi(); i++)	//SCORRO LA LISTA PER ANALIZZARE TUTTI GL'ALBUM
				{
					for(int s = 0; s < str.length && !uguale; s++)//CONTROLLO SE HO GIA INSERITO QUELL'ALBUM
						if(tmp.B.GetAlbum().equals(str[s]))
							uguale = true;
					if(!uguale)
					{
						str[k] = tmp.B.GetGenere();
						k++;
					}
					else
						uguale = false ;
					tmp = tmp.Next;
				}
				break;
			}
		}
	}*/

	
	/*public boolean add(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object remove(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object set(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
}//FINE LISTA*/

	