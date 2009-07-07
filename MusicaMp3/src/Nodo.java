/*Questa è una classe particolare che deve essere invisibile, usata solo per l'implementazione
 * della classe lista*/
class Node 
{
	Mp3 B;
	Node Next;

	//Costruttore
	public Node(Mp3 b)
	{
		this(b,null);
		/*B = new Mp3(b);
		Next = null;*/
	}
	public Node(Mp3 b, Node next)
	{
		B = b;
		Next = next;
	}
}
