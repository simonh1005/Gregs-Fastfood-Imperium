package Server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Client.SpielLogik;

public class Sender extends Thread
{
	public static final int MAX_PLAYERS = 4;
	Socket[] player = new Socket[MAX_PLAYERS];
	private ClientHandler[] handler = new ClientHandler[MAX_PLAYERS];
	private int number_of_player = 0;

	// private String message;

	public void addPlayer(Socket client)
	{
		if (number_of_player < MAX_PLAYERS)
		{

			sendToPlayer(client, "" + number_of_player);

			for (int i = 0; i < number_of_player; i++) // Send all other players
														// tothe new one
			{
				sendToPlayer(client, "<newPlayer>" + handler[i].name);
				sendToPlayer(client, "<msg>" + handler[i].name
						+ ">Joined the room");
			}
			player[number_of_player] = client;

			handler[number_of_player] = new ClientHandler(client, this);
			handler[number_of_player].start();
			number_of_player++;
		} else
		{
			sendToPlayer(client, "-1");
			System.out.println("Maximale anzahl an Spielern bereits erreicht");
		}
	}

	public void sendToAll(String message, Socket sender, String name,
			boolean sendTosender)
	{
		String preafix = "";
		for (int i = 0; i < number_of_player; i++)
		{
			if (sendTosender || (sender != player[i])) // Dont send to the
														// sender, if the
														// boolean is false
			{
				PrintStream raus;
				try
				{
					raus = new PrintStream(player[i].getOutputStream());
					raus.println(message);
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public void sendToAll(String message, String name) // Also sends to sender
	{
		String preafix = "";
		for (int i = 0; i < number_of_player; i++)
		{
			PrintStream raus;
			try
			{
				raus = new PrintStream(player[i].getOutputStream());
				raus.println(message);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void sendToPlayer(Socket p, String message)
	{
		PrintStream raus;
		try
		{
			raus = new PrintStream(p.getOutputStream());
			raus.println(message);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		super.run();
	}

	public void startGame()
	{			
			String[] namen = new String[number_of_player];
			Socket[] tmp = new Socket[number_of_player];
			for (int i = 0; i < number_of_player; i++)
			{
				namen[i] = handler[i].getName();
				tmp[i] = player[i]; //Es soll kein Array der L�nge 4 �bergeben werde, wenn weniger als 4 Spielr existieren
				if (i > 0)
				{
					sendToPlayer(player[i], "<start>");
				}
			}
			try
			{
				
				Spiel s = new Spiel(tmp, namen);
			} catch (ParserConfigurationException | SAXException | IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
