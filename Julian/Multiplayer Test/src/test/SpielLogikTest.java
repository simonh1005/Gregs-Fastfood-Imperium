package test;

import static org.junit.Assert.*;

import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Client.ClientWindow;
import Client.UIWindow;

public class SpielLogikTest {

	Socket meinSocket;
	UIWindow testUI;
	@Before
	public void setUp() throws Exception {
		meinSocket = new Socket();
		testUI = new UIWindow();
	}

	@Test
	public void test() {
		ClientWindow client = new ClientWindow("Hugo");
		ClientWindow client1 = new ClientWindow("Spieler2");
		ClientWindow client2 = new ClientWindow("Spieler3");
		client.startGame();
		
		try{
			
			
			
		}
		catch(Exception e){
			fail("Not yet implemented");	
		}
		
		
	}

}
