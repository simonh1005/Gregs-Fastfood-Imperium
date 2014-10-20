package Client;

public class SpielLogik {

	private int aktJahr;
	private int aktQuartal;
	private static Spieler spieler = new Spieler();
	
	
	public void spielStarten(){
		//Was passiert hier?
	}
	
	public static void spielBeenden(){
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

	public static Spieler getSpieler() {
		return spieler;
	}

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}
	
	
	
	
	
	
}
