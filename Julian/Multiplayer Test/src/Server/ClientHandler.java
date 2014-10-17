package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientHandler extends Thread
{
	Socket client;
	Sender sender;
	String inputMessage = "";
	String name;

	public ClientHandler(Socket s, Sender sender)
	{
		client = s;
		this.sender = sender;			
	}

	@Override
	public void run()
	{
		try
		{
			InputStream is = client.getInputStream();
			PrintStream raus = new PrintStream(client.getOutputStream());

			BufferedReader rein = new BufferedReader(new InputStreamReader(
					client.getInputStream()));

			name = rein.readLine();			
			System.out.println("Neuer Spieler verbunden: " + name);
			sender.sendToAll("<newPlayer>" +name, client, name, false);
			sender.sendToAll("<msg>" +name +">Joined the room", client, name, false); //<msg><sender_name>text
		
			
			
//			Eingehende Nachrichten verarbeiten
			String m;
			String[] ms;
			while ((m = rein.readLine()) != null)
			{
				ms = m.split(">");
				switch (ms[0])
				{				
				case "<msg": //Chat Message for this Player; in ms[1], the sender is saved
				{					
					System.out.println(ms[1] + ":" + ms[2]);
					sender.sendToAll(m, client, name, false);
					break;
				}
				default:
					break;
				}
				// System.out.println(m);
				
			}

			System.out.println((byte) is.read());

		} catch (IOException e)
		{
			System.out.println("Client disconnected");
		}
	}

}
