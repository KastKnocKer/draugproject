/*Questa è una classe particolare che deve essere invisibile, usata solo per l'implementazione
 * della classe lista artisti*/

	class NodeArt 
	{
		MusicArt B;
		NodeArt Next;

		//Costruttore
		public NodeArt(MusicArt b)
		{
			this(b,null);
			/*B = new Mp3(b);
			Next = null;*/
		}
		public NodeArt(MusicArt b, NodeArt next)
		{
			B = b;
			Next = next;
		}
	}

