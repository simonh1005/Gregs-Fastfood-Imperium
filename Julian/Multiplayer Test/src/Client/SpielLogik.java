package Client;

import java.net.Socket;

public class SpielLogik {

	private int aktJahr;
	private int aktQuartal;
	private Spieler spieler;

	public SpielLogik(Socket socket,String name){ //Infos von Map etc kriegen 
		spieler = new Spieler(this,name,socket);
	}
	
	public void spielStarten(){
		//Was passiert hier?
	}
	
	public void spielBeenden(){
		// Was mach ich hier?
	}
	
	public void rundenEnde(){
		// An Server Verbrauch etc senden
	}
	
	public QuartalsBericht showBerichte(String spieler,int jahr, int quartal){
		
		return null;
	}
	
	public void setAbsaetze(int[] absatz){
		// Was macht die hier?
	}

	public Spieler getSpieler() {
		return spieler;
	}

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}
	
	
	
	
	
	
}
