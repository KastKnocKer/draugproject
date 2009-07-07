import java.awt.*;
import javax.swing.*;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagFieldKey;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v1Tag;
import org.jaudiotagger.tag.id3.ID3v23Frame;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;
import org.jaudiotagger.tag.id3.framebody.FrameBodyUSLT;

import java.io.*;

public class Mp3 extends MP3File implements Serializable {
	private String Artista=null;
	private String Titolo=null;
	private String Album=null;
	private String Genere=null;
	private String NumeroTraccia = null;
	private String Anno=null;
	private String Channels=null;
	private String TestoCanzone = null ;
	private boolean WithArtwork=false;
	private boolean WithLyric=false;
	
	private static final String StringID3v1Tag = "ID3v1";
	private static final String StringID3v2Tag = "ID3v2";
	private static final String StringNoTag = "NoTag";
	
	private String Percorso = null;
	public int ID,BitRate,SampleRate;
	private Lista l;
	
	private String TagVersion=null;
	private boolean ID3v1Tag=false;
	private boolean ID3v2Tag=false;
	private ImageIcon Copertina;;

	private MP3File mp3;
	private int conCopertina;
	private File file;
	
	transient MP3AudioHeader mp3AudioHeader=null;

	public Mp3(File file) throws IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotReadException{
		super(file,LOAD_ALL);
		this.file=file;
		
		
		 			Percorso= file.getAbsolutePath();

		 			ID3v23Tag tag= (ID3v23Tag) this.getTag(); 
		 			
		 			
		 			
					mp3AudioHeader =  (MP3AudioHeader) this.getAudioHeader(); 
					BitRate	= (int) mp3AudioHeader.getBitRateAsNumber();
					SampleRate =	mp3AudioHeader.getSampleRateAsNumber();
					Channels =		mp3AudioHeader.getChannels();
					
					
					if( this.hasID3v1Tag() ) {ID3v1Tag=true; TagVersion=StringID3v1Tag;}
					if( this.hasID3v2Tag() ) {ID3v2Tag=true; TagVersion=StringID3v2Tag; }
					if( !ID3v1Tag && !ID3v2Tag ) TagVersion=StringNoTag;
					
					if( ID3v2Tag ){
						AbstractID3v2Tag tag1 = this.getID3v2Tag(); 
						
						Artista=tag1.getFirstArtist();
						Titolo=tag1.getFirstTitle();
						Album=tag1.getFirstAlbum();
						Genere=tag1.getFirstGenre();
						Genere=this.GenereDaNumero();
						NumeroTraccia=tag1.getFirstTrack();
						Anno=tag1.getFirstYear();
						TestoCanzone = getLyrics();
						Copertina = getArtwork();
						//TestoCanzone=tag1.getFirst(TagFieldKey.LYRICS);
						/*AggiornaLyric();
						AggiornaCopertina();*/
						return;
					}
					
					if( ID3v1Tag ){
						ID3v1Tag tag2 = this.getID3v1Tag();
						
				    	Artista=tag2.getFirstArtist();
						Titolo=tag2.getFirstTitle();
						Album=tag2.getFirstAlbum();
						//TestoCanzone=tag2.getFirst(TagFieldKey.LYRICS);
						//NumTracciaCd=tag.getFirstTrack();
						Anno=tag2.getFirstYear();
						Genere=tag2.getFirstGenre();
						Genere=this.GenereDaNumero();
						TestoCanzone = getLyrics();
						Copertina = getArtwork();
						//TestoCanzone=null;
						return;
					}
}

	public Mp3(Lista l){
		this.l=l;
	}
	
	public Mp3(Mp3 B){
		ID = B.ID;
		Percorso = B.Percorso;
		Artista=B.Artista;
		Album = B.Album;
		Titolo = B.Titolo;
		NumeroTraccia = B.NumeroTraccia;
		Genere = B.Genere;
		Anno = B.Anno;
	}
	
public String getLyrics(){
	try{
		   ID3v23Tag tag = (ID3v23Tag) this.getTag();
		   if (tag.getFirst(TagFieldKey.LYRICS).isEmpty()) return null;
		   ID3v23Frame lyricframe = (ID3v23Frame) tag.getFrame(org.jaudiotagger.tag.id3.ID3v23FieldKey.LYRICS.getFieldName());
		   FrameBodyUSLT FBU = (FrameBodyUSLT) lyricframe.getBody();
		   TestoCanzone = FBU.getFirstTextValue();
		   if( (TestoCanzone.length()>0)&&(!TestoCanzone.equals("Sconosciuto")) )WithLyric=true;
		}
		catch(Exception e){
			TestoCanzone = null;
		}
		
		return TestoCanzone;
}
public ImageIcon getArtwork() {
	
        
        try{
        	MP3File nuovo = (MP3File) AudioFileIO.read(file);
            ID3v23Tag tag = (ID3v23Tag) nuovo.getTag();
        try {
                byte[] artwork = null;
                ID3v23Frame apicFrame = (ID3v23Frame) tag.getFrame("APIC");
                FrameBodyAPIC cc = (FrameBodyAPIC) apicFrame.getBody();
                artwork = (byte[]) cc.getObjectValue(org.jaudiotagger.tag.datatype.DataTypes.OBJ_PICTURE_DATA);
                Copertina = new ImageIcon(artwork);
                WithArtwork = true;
                return Copertina;
                
        }
        catch (Exception e) {
                Copertina = new ImageIcon("Default image.png");
                return Copertina;
                }
        }
        catch (Exception e) {
            return null;
    }


}
	
	
	//Modifica Posizione
	public boolean PutPos(int pos)	
	{
		try 
		{
			ID = pos;
		}
		catch(ExceptionInInitializerError e)
		{
			return false;
		}
		return true;
	}
	
	
	
	public String GetPercorso(){
		return Percorso;
	}
	/**
	 * Ritorna il nome dell'artista dell'mp3
	 * @return String Artista
	 */
	public String GetArtista(){
		if ( (Artista!=null)&&(Artista.length()>0) ) return Artista;
		return "Sconosciuto";
	}
	
	/**
	 * Risetta il tag artista
	 * @param nome
	 * @throws CannotReadException
	 * @throws IOException
	 * @throws TagException
	 * @throws ReadOnlyFileException
	 * @throws InvalidAudioFrameException
	 * @throws CannotWriteException
	 */
	public void SetArtista(String nome) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException{
	
	ID3v23Tag tag1;
	tag1 = (ID3v23Tag) this.getTag();
	try 
	{
		tag1.setArtist(nome);
	} 
	catch (FieldDataInvalidException e) 
	{
		System.out.println("errore");
	}

	try {
		this.commit();
	} catch (CannotWriteException e) {
		e.printStackTrace();
	}
	}
	
	
	/**
	 * Risetta il tag Titolo
	 * 
	 */
	public void SetTitolo(String titolo) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException{
		
		ID3v23Tag tag1;
		tag1 = (ID3v23Tag) this.getTag();
		try 
		{
			tag1.setTitle(titolo);
		} 
		catch (FieldDataInvalidException e) 
		{
			System.out.println("errore");
		}

		try {
			this.commit();
		} catch (CannotWriteException e) {
			e.printStackTrace();
		}
		}
	
	/**
	 * Risetta l'album
	 */
	
public void SetAlbum(String album) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException{
		
		ID3v23Tag tag1;
		tag1 = (ID3v23Tag) this.getTag();
		try 
		{
			tag1.setAlbum(album);
		} 
		catch (FieldDataInvalidException e) 
		{
			System.out.println("errore");
		}

		try {
			this.commit();
		} catch (CannotWriteException e) {
			e.printStackTrace();
		}
		}
	
/**
 * Risetta il numero del cd
 * 
 */

public void SetNumeroTraccia(String numeroTraccia) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException{
	
	ID3v23Tag tag1;
	tag1 = (ID3v23Tag) this.getTag();
	try 
	{
		tag1.setTrack(numeroTraccia);
	} 
	catch (FieldDataInvalidException e) 
	{
		System.out.println("errore");
	}

	try {
		this.commit();
	} catch (CannotWriteException e) {
		e.printStackTrace();
	}
	}

/**
 * Risetta Genere
 */

public void SetAnno(String anno) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException{
	
	ID3v23Tag tag1;
	tag1 = (ID3v23Tag) this.getTag();
	try 
	{
		tag1.setYear(anno);
	} 
	catch (FieldDataInvalidException e) 
	{
		System.out.println("errore");
	}

	try {
		this.commit();
	} catch (CannotWriteException e) {
		e.printStackTrace();
	}
	}

/**Risettta il Genere
 * 
 */
public void SetGenere(String genere) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException{
	
	ID3v23Tag tag1;
	tag1 = (ID3v23Tag) this.getTag();
	try 
	{
		tag1.setGenre(genere);
	} 
	catch (FieldDataInvalidException e) 
	{
		System.out.println("errore");
	}

	try {
		this.commit();
	} catch (CannotWriteException e) {
		e.printStackTrace();
	}
	}

	/**
	 * Ritorna il titolo dell'mp3
	 * @return String Artista
	 */
	public String GetTitolo(){
		if ( (Titolo!=null)&&(Titolo.length()>0) ) return Titolo;
		return "Sconosciuto";
	}
	/**
	 * Ritorna l'album di appartenenza dell'mp3
	 * @return String Album
	 */
	public String GetAlbum(){
		if ( (Album!=null)&&(Album.length()>0) ) return Album;
		return "Sconosciuto";
	}
	/**
	 * Ritorna il genere dell'mp3
	 * @return String genere
	 */

	public String GetGenere(){
			if ( (Genere!=null)&&(Genere.length()>0) ) return Genere;
			return "Sconosciuto";
		}
	/**
	 * Ritorna il numero della traccia del cd
	 * @return String Numero traccia del cd
	 */
	public String GetNumeroTraccia(){
		if ( (NumeroTraccia!=null)&&(NumeroTraccia.length()>0) ) return NumeroTraccia;
		return "Sconosciuto";
	}
	/**
	 * Ritorna i bitrate dell'mp3
	 * @return int Bitrate
	 */
	public int GetBitRate(){
		return BitRate;
	}
	/**
	 * Ritorna i Samplerate dell'mp3
	 * @return int Samplerate
	 */
	public int GetSampleRate(){
		return SampleRate;
	}
	
	/**
	 * Ritorna l'anno dell'mp3
	 * @return String Anno
	 */
	public String GetAnno(){
		if ( (Anno!=null)&&(Anno.length()>0) ) return Anno;
		return "Sconosciuto";
	}
	
	/**
	 * Ritorna i canali dell'mp3
	 * @return String Channels
	 */
	public String GetChannels(){
		if (Channels!=null) return Channels;
		return "Sconosciuto";
	}
	//public boolean HasCopertina(){return Copertina;}
	/**
	 * Indica se l'mp3 possiede il testo della canzone
	 * @return boolean
	 */
	public boolean HasLyric(){return WithLyric;}
	/**
	 * Indica se l'mp3 possiede la copertina
	 * @return boolean
	 */
	public boolean hasCopertina(){return WithArtwork;}

	/**
	 * Traduce i metadati del genere dei tag ID3v1 in stringhe
	 * @return String
	 */
	
	public String GenereDaNumero(){
		try{
		if (Genere.startsWith("(")) {
			 int index = Genere.lastIndexOf(")");
			 Genere = Genere.substring(0, index);
			 Genere = Genere.replace("(", "");
			 int i = new Integer(Genere);
			 switch (i) {
				case   0 : return "Blues"; 
				case   1 : return "Classic Rock";
				case   2 : return "Country";
				case   3 : return "Dance";
				case   4 : return "Disco";
				case   5 : return "Funk";
				case   6 : return "Grunge";
				case   7 : return "Hip-Hop";
				case   8 : return "Jazz";
				case   9 : return "Metal";
				case  10 : return "New Age";
				case  11 : return "Oldies";
				case  12 : return "Other";
				case  13 : return "Pop";
				case  14 : return "R&B";
				case  15 : return "Rap";
				case  16 : return "Reggae";
				case  17 : return "Rock";
				case  18 : return "Techno";
				case  19 : return "Industrial";
				case  20 : return "Alternative";
				case  21 : return "Ska";
				case  22 : return "Death Metal";
				case  23 : return "Pranks";
				case  24 : return "Soundtrack";
				case  25 : return "Euro-Techno";
				case  26 : return "Ambient";
				case  27 : return "Trip-Hop";
				case  28 : return "Vocal";
				case  29 : return "Jazz Funk";
				case  30 : return "Fusion";
				case  31 : return "Trance";
				case  32 : return "Classical";
				case  33 : return "Instrumental";
				case  34 : return "Acid";
				case  35 : return "House";
				case  36 : return "Game";
				case  37 : return "Sound Clip";
				case  38 : return "Gospel";
				case  39 : return "Noise";
				case  40 : return "AlternRock";
				case  41 : return "Bass";
				case  42 : return "Soul";
				case  43 : return "Punk";
				case  44 : return "Space";
				case  45 : return "Meditative";
				case  46 : return "Instrumental Pop";
				case  47 : return "Instrumental Rock";
				case  48 : return "Ethnic";
				case  49 : return "Gothic";
				case  50 : return "Darkwave";
				case  51 : return "Techno-Industrial";
				case  52 : return "Electronic";
				case  53 : return "Pop-Folk";
				case  54 : return "Eurodance";
				case  55 : return "Dream";
				case  56 : return "Southern Rock";
				case  57 : return "Comedy";
				case  58 : return "Cult";
				case  59 : return "Gangsta";
				case  60 : return "Top 40";
				case  61 : return "Christian Rap";
				case  62 : return "Pop/Funk";
				case  63 : return "Jungle";
				case  64 : return "Native American";
				case  65 : return "Cabaret";
				case  66 : return "New Wave";
				case  67 : return "Psychadelic";
				case  68 : return "Rave";
				case  69 : return "Showtunes";
				case  70 : return "Trailer";
				case  71 : return "Lo-Fi";
				case  72 : return "Tribal";
				case  73 : return "Acid Punk";
				case  74 : return "Acid Jazz";
				case  75 : return "Polka";
				case  76 : return "Retro";
				case  77 : return "Musical";
				case  78 : return "Rock & Roll";
				case  79 : return "Hard Rock";
				case  80 : return "Folk";
				case  81 : return "Folk-Rock";
				case  82 : return "National Folk";
				case  83 : return "Swing";
				case  84 : return "Fast Fusion";
				case  85 : return "Bebob";
				case  86 : return "Latin";
				case  87 : return "Revival";
				case  88 : return "Celtic";
				case  89 : return "Bluegrass";
				case  90 : return "Avantgarde";
				case  91 : return "Gothic Rock";
				case  92 : return "Progressive Rock";
				case  93 : return "Psychedelic Rock";
				case  94 : return "Symphonic Rock";
				case  95 : return "Slow Rock";
				case  96 : return "Big Band";
				case  97 : return "Chorus";
				case  98 : return "Easy Listening";
				case  99 : return "Acoustic";
				case 100 : return "Humour";
				case 101 : return "Speech";
				case 102 : return "Chanson";
				case 103 : return "Opera";
				case 104 : return "Chamber Music";
				case 105 : return "Sonata";
				case 106 : return "Symphony";
				case 107 : return "Booty Bass";
				case 108 : return "Primus";
				case 109 : return "Porn Groove";
				case 110 : return "Satire";
				case 111 : return "Slow Jam";
				case 112 : return "Club";
				case 113 : return "Tango";
				case 114 : return "Samba";
				case 115 : return "Folklore";
				case 116 : return "Ballad";
				case 117 : return "Power Ballad";
				case 118 : return "Rhythmic Soul";
				case 119 : return "Freestyle";
				case 120 : return "Duet";
				case 121 : return "Punk Rock";
				case 122 : return "Drum Solo";
				case 123 : return "A capella";
				case 124 : return "Euro-House";
				case 125 : return "Dance Hall";
				
				default: return Genere;
				}
		 }
		 return Genere;
		}catch(Exception E){return Genere;}
	}
	/**
	 * Aggiorna il testo della canzone dell'mp3
	 */
	public void AggiornaLyric(){
		try{
			   ID3v23Tag tag = (ID3v23Tag) this.getTag();
			   if (tag.getFirst(TagFieldKey.LYRICS).isEmpty()) return;
			   ID3v23Frame lyricframe = (ID3v23Frame) tag.getFrame(org.jaudiotagger.tag.id3.ID3v23FieldKey.LYRICS.getFieldName());
			   FrameBodyUSLT FBU = (FrameBodyUSLT) lyricframe.getBody();
			   TestoCanzone = FBU.getFirstTextValue();
			   if( (TestoCanzone.length()>0)&&(!TestoCanzone.equals("Sconosciuto")) )WithLyric=true;
			   
			}
			catch(Exception e){
				TestoCanzone = null;
			}
	}	
	
	
	
}
