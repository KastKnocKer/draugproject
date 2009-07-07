import javax.swing.filechooser.FileFilter;
import java.io.*;
public class FiltroMp3 extends FileFilter{
	
	public FiltroMp3() {
		super();
	}

	public String getDescription() {
		return ("*.mp3");}
	
	public boolean accept(File f) {
		if (f.isDirectory()) return true;
		if (f.getName().endsWith(".mp3"))return true;
		else
			return false;
	}
	
}
