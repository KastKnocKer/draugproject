import javax.swing.filechooser.FileFilter;
import java.io.*;
public class FiltroDirect extends FileFilter{
	
	public FiltroDirect(){
		super();
	}

	public String getDescription() {
		return ("Directories");
	}
	
	public boolean accept(File f){
		if (f.isDirectory())
			return true;
			else return false;}
}
