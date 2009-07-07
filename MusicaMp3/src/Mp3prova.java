import java.awt.*;

import javax.swing.JFrame;
import javax.swing.UIManager;
public class Mp3prova {
	static Lista l;
	public static void main(String[] args) throws ClassNotFoundException {	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} 
		catch (Exception ex) {
			System.err.println("Impossibile impostare L&F di sistema");
			}
		l = new Lista();
	
	
	MyFrame mf = new MyFrame("Draug Lettore Mp3");//passo anche il riferimento alla lista

	Container cmf = mf.getContentPane();
	PannelloGlobale pp = new PannelloGlobale(l);
	cmf.add(pp);
	mf.setVisible(true);}
}
