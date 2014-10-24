package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.plaf.SliderUI;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Client.ClientWindow;
import Client.Marktpreis;
import Client.SpielLogik;
import Client.UIWindow;
import Server.Server;
import Starters.*;

public class SpielLogikTest {

	Socket s1; 
	Socket s2;
	Socket s3; 
	UIWindow testUI;
	@Before
	public void setUp() throws Exception {
		Server.newServer();
		connectServer();
		
		
		testUI = new UIWindow();
	}

	private void connectServer() throws UnknownHostException, IOException {
		
		String message = "SearchForServer";
		byte[] bytes = message.getBytes();
		InetAddress inetAddress = InetAddress.getByName("localhost");
		DatagramPacket packet = new DatagramPacket(bytes, bytes.length,
				inetAddress, 666);
		DatagramSocket socket = new DatagramSocket();
		socket.setBroadcast(true);
		socket.send(packet);
		s1 = new Socket("localhost", 99);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		socket.send(packet);
		s2 = new Socket("localhost", 99);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		socket.send(packet);
		s3 = new Socket("localhost", 99);
		
	}

	@Test
	public void test() {
		SpielLogik spiel1 = new SpielLogik(s1, "Spieler1");
		SpielLogik spiel2 = new SpielLogik(s2, "Spieler2");
		SpielLogik spiel3 = new SpielLogik(s3, "Spieler3");
		
		try{
			//Spieler 1
			System.out.println("Alle Spieler starten mit " + spiel1.getSpieler().getKontostand() +" €");
			System.out.println("-------------------------------------------------------------------");
			spiel1.getSpieler().filialeEroeffnen(1, 1, 1, "Spieler1", 1);//1.1, 100k wert
			System.out.println("   Spieler 1 ein Filiale für " + spiel1.getSpieler().getBezirke()[0].getFiliale(1).getKaufPreis() +"€");
			
			spiel1.getSpieler().einkaufen(1000, 1, 1);//Döner? vorratskauf
			System.out.println("   Spieler 1 Rohstoffe für " + spiel1.getSpieler().getPreise()[0].getPrice(1000, 1));
			spiel1.getSpieler().mitarbeiterEinstellen(4);
			spiel1.getSpieler().MAAnzAendern(4, 1);
			System.out.println("   Sein neuer Kontostand beträgt:" + spiel1.getSpieler().getKontostand());
			//SPiele 2
			spiel2.getSpieler().filialeEroeffnen(11, 1, 2, "Spieler2", 1);// 2. Bezirk 1. Filiale 100k
			spiel2.getSpieler().einkaufen(500, 1, 2);//Curry?
			spiel2.getSpieler().mitarbeiterEinstellen(4);
			spiel2.getSpieler().MAAnzAendern(4, 6);
			//Spieler 3
			spiel3.getSpieler().filialeEroeffnen(2, 2, 3, "Spieler3", 2);// 1. Bezirk 2. Filiale 120k
			spiel3.getSpieler().einkaufen(300, 2, 3);//pizza?
			spiel3.getSpieler().mitarbeiterEinstellen(15);
			spiel3.getSpieler().MAAnzAendern(15, 2);
			
			//Runde 1 beenden
			spiel1.rundenEnde();
			spiel2.rundenEnde();
			spiel3.rundenEnde();
			
			
			
			//Spieler 1 
			spiel1.getSpieler().filialeEroeffnen(3, 1, 1, "Spieler1", 1);//1.3, 100k wert
			spiel1.getSpieler().einkaufen(100, 1, 1);//Döner?
			spiel1.getSpieler().mitarbeiterEinstellen(2);
			spiel1.getSpieler().MAAnzAendern(2, 3);
			
			//Spieler 2
			spiel2.getSpieler().filialeEroeffnen(12, 1, 1, "Spieler2", 1);// 2.2 Filiale 100k Döner?
			spiel2.getSpieler().einkaufen(500, 1, 2);//Curry? laufende Kosten
			spiel2.getSpieler().einkaufen(500, 1, 1);//Döner laufende Kosten, erhöhter Marktpreis wegen Vorratskauf Spieler1
			spiel2.getSpieler().mitarbeiterEinstellen(2);
			spiel2.getSpieler().MAAnzAendern(2, 7);
			
			//Spieler 3
			spiel3.getSpieler().einkaufen(300, 2, 3);//pizza?
			spiel3.getSpieler().mitarbeiterEinstellen(2);
			spiel3.getSpieler().MAAnzAendern(2, 2);
			
			
			//Runde 2 beenden
			spiel1.rundenEnde();
			spiel2.rundenEnde();
			spiel3.rundenEnde();
			
			//Spieler 1 
			spiel1.getSpieler().einkaufen(400, 1, 1);//Döner? laufende Kosten
			
			//Spieler 2
			spiel2.getSpieler().einkaufen(500, 1, 2);//Curry? laufende Kosten
			spiel2.getSpieler().einkaufen(500, 1, 1);//Döner laufende Kosten
			
			//Spieler 3
			spiel3.getSpieler().filialeEroeffnen(4, 3, 3, "Spieler3", 3);// 1.4 100k, Größe 3 
			spiel3.getSpieler().einkaufen(600, 2, 3);//pizza? Vorratskauf
			spiel3.getSpieler().einkaufen(500,3, 3); //Pizza stufe 3 für filiale 4
			spiel3.getSpieler().mitarbeiterEinstellen(50);
			spiel3.getSpieler().MAAnzAendern(50, 4);
			
			
			
			//Runde 3 beenden
			spiel1.rundenEnde();
			spiel2.rundenEnde();
			spiel3.rundenEnde();
			
			//Spieler 1 kauft Filiale, Vorratskauf
			
			//Spieler2 kauft Filiale
			
			//Spieler 3 normaler Kauf
			
			//Runde 4 beenden
			
		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Fehler aufgetreten");	
		}
		
		
	}

}
