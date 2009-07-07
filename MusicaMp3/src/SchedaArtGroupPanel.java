import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class SchedaArtGroupPanel extends JPanel implements ActionListener {

	private String NomeGruppo,Sito,Discografia,DataForm,DataScio,Biografia;
	private Object [] Label;
	private Object [] Testo;
	private JButton Ritorna;
	private PannelloArtisti pa;
	private JButton Salva;
	private MusicArtGroup ArtGroup;

	/**
	 * Costruttore che inizializza i valori iniziali vuoti
	 */

	public SchedaArtGroupPanel(){
		NomeGruppo="";
		Sito="";
		Discografia="";
		DataForm="";
		DataScio="";
		Biografia="";
	}
	
	/**
	 * Costruttore che accetta le variabili in ingresso e setta i campi
	 * 
	 * @param NomeGruppo
	 * @param Sito
	 * @param Discografia
	 * @param DataForm
	 * @param DataScio
	 */
	
	public SchedaArtGroupPanel(String NomeGruppo,String Sito, String Discografia, String DataForm, String DataScio,String Biografia){
		this.NomeGruppo=NomeGruppo;
		this.Sito=Sito;
		this.Discografia=Discografia;
		this.DataForm=DataForm;
		this.DataScio=DataScio;	
		this.Biografia=Biografia;
	}
	
	public void ModificaCampi(MusicArtGroup ArtGroup){
		this.ArtGroup=ArtGroup;
		
		((JTextField)Testo[0]).setText(ArtGroup.getName());
		((JTextField)Testo[1]).setText(ArtGroup.getSito());
		//((JTextField)Testo[2]).setText(ArtGroup.getDiscografia());
		((JTextField)Testo[3]).setText(ArtGroup.getDataForm());
		((JTextField)Testo[4]).setText(ArtGroup.getDataSciogl());
		((JTextArea)Testo[5]).setText(ArtGroup.getBio());

		
	}
	
	public void SchedaArtGroupPanel1(PannelloArtisti pa){
		this.pa = pa;
		
		// Creazione Label e JTextField
		Label = new Object[7];
		Testo=new Object[7];
		
		Label[0] = new JLabel("Nome Gruppo ");
		Testo[0] = new JTextField(NomeGruppo);
		
		add((JLabel)Label[0]);
		add((JTextField)Testo[0]);
		
		Label[1] = new JLabel("Sito web ");
		Testo[1] = new JTextField(Sito);
		
		add((JLabel)Label[1]);
		add((JTextField)Testo[1]);
		
		Label[2] = new JLabel ("Discografia ");
		Testo[2]=new JComboBox();
		((JComboBox)Testo[2]).addItem("");
		
		((JComboBox)Testo[2]).addItem("Aggiungi Album");
		((JComboBox)Testo[2]).addActionListener(this);
		
		
		add((JLabel)Label[2]);
		add((JComboBox)Testo[2]);
		
		Label[3] = new JLabel ("Data della formazione del gruppo");
		Testo[3] = new JTextField(DataForm);
		
		add((JLabel)Label[3]);
		add((JTextField)Testo[3]);
		
		Label[4] = new JLabel ( "Data dello scioglimento del gruppo");
		Testo[4] = new JTextField(DataScio);
		
		add((JLabel)Label[4]);
		add((JTextField)Testo[4]);
		
		Label[5] = new JLabel ("Biografia del Gruppo ");
		Testo[5] = new JTextArea(Biografia);
		
		add((JLabel)Label[5]);
		add((JTextArea)Testo[5]);
		
		Salva = new JButton("Salva");
		Salva.addActionListener(this);
		add(Salva);
		
		Ritorna = new JButton("Ritorna");
		Ritorna.addActionListener(this);
		add(Ritorna);
		
		setVisible(true);
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		int x=50,y=0;
		
		
		for(int i=0;i<5;i++){
			((JLabel)Label[i]).setSize(180,20);
			if(i==2){
				((JComboBox)Testo[i]).setSize(180,20);
			}
			else
			{((JTextField)Testo[i]).setSize(180,20);}
		}
		
		for(int i=0;i<5;i++){
			((JLabel)Label[i]).setLocation(x, y);
			if(i==2){
				((JComboBox)Testo[i]).setLocation(x + 180,y);
			}
			else
			{((JTextField)Testo[i]).setLocation(x + 180,y);}
			
			
			y += 30;
			
		}
		
		
		// Disposizione della biografia
		((JLabel)Label[5]).setLocation(470, 0);
		((JTextArea)Testo[5]).setLocation(470,25);
		
		//Dimensione della biografia
		
		((JLabel)Label[5]).setSize(100, 20);
		((JTextArea)Testo[5]).setSize(230,120);
		
		

		


	Ritorna.setSize(100, 30);
	Ritorna.setLocation(600, 150);
	
	Salva.setSize(100, 30);
	Salva.setLocation(490,150);
	}
	
	public void actionPerformed (ActionEvent e){
		Object ob = e.getSource();

		if(ob==Ritorna){
			pa.RitornaPA();
		}
		
		if(ob==Salva){
			//ArtGroup.setName(((JTextField)Testo[0]).getText());
			ArtGroup.setSito(((JTextField)Testo[1]).getText());
			//ArtGroup.setDiscografia(((JTextField)Testo[2]).getText());
			ArtGroup.setDataForm(((JTextField)Testo[3]).getText());
			ArtGroup.setDataSciogl(((JTextField)Testo[4]).getText());
			ArtGroup.setBio(((JTextArea)Testo[5]).getText());
			
			this.ModificaCampi(ArtGroup);
		}
		// nel caso in cui clikkiiamo sulla JCombobox
		String selectedItem =(String) ((JComboBox)Testo[2]).getSelectedItem();
		if(ob == ((JComboBox)Testo[2])){
			if(selectedItem.equals("Aggiungi Album")){
				
				System.out.println("Ok ti mostro l'elenco degli Album");
			}
		}
	}
	
}
