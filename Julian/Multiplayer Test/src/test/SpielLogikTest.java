package test;

import static org.junit.Assert.*;

import java.io.IOException;
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
			spiel1.getSpieler().filialeEroeffnen(1, 1, 1, "Spieler1", 2);
			//SPiele 2+3 kaufwen Filiale
			
			spiel1.rundenEnde();
			spiel2.rundenEnde();
			spiel3.rundenEnde();
		
		}
		catch(Exception e){
			fail("Not yet implemented");	
		}
		
		
	}

}
