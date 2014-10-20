package Client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class SpielLogik {

	private int aktJahr;
	private int aktQuartal;
	private Spieler spieler;
	private UIWindow ui;

	public SpielLogik(Socket socket,String name, UIWindow ui){ //Infos von Map etc kriegen 
		spieler = new Spieler(this,name,socket);
		this.ui = ui;
	}
	
	public void spielBeenden(){
		spieler.sendToServer("<lost>");
		JOptionPane.showMessageDialog(null, "Sie haben leider verloren.");
	}
	
	public void rundenEnde(){
		//spieler.sendToServer("blubb");
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
