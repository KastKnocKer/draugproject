
public class Thread_ControlloPlayer extends Thread{
	
	private PannelloSin JPAP;

	
	/**
	 * Costruttore
	 * @param JPanelArea_Play
	 */
	public Thread_ControlloPlayer(PannelloSin JPAP) {
		super();
		this.JPAP=JPAP;
		
	}
	


	/**
	 * Avvia il thread
	 */
	public void run(){
		while(true){
			//JPAP.AggiornaBarra();
			try {
				Thread.sleep( new Long(1000) );
			} catch (InterruptedException e) {}

		}
	}

}
