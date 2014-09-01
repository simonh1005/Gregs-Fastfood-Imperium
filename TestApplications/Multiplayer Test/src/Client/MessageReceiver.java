package Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.sound.midi.Receiver;


public class MessageReceiver extends Thread
{
	Socket server;
	ClientWindow parent;
	
	public MessageReceiver(Socket server, ClientWindow parent)
	{
		this.server = server;
		this.parent = parent;
	}
	
	private void receiveMessage()
	{		
		try
		{
			InputStream is = server.getInputStream();
			BufferedReader rein = new BufferedReader(new InputStreamReader(
					server.getInputStream()));		
			String m;
			String[] ms = new String[3]; //tag in 1st element and parameter in the 2nd
			while ((m = rein.readLine()) != null)
			{
		//		chat.getOutput().append(m +"\n");		
				ms = m.split(">");
				
				switch (ms[0])
				{
				case "<newPlayer": //New player Connected
					parent.addPlayer(ms[1]);
					break;
				case "<msg": //Chat Message for this Player; in ms[1], the sender is saved
				{					
					parent.displayMessage(ms[2], ms[1]);
					break;
				}
				default:
					break;
				}
			}
		} catch (IOException e)
		{
			System.out.println("Server disconnected");
		}
		
	}

	@Override
	public void run()
	{
		super.run();
		receiveMessage();
		
	}
	
}
