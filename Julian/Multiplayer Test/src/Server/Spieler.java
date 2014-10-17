package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Spieler extends Thread
{
	private Socket s;
	private String name;
	private Spiel parent;

	public Spieler(Socket s, String name,Spiel parent)
	{
		super();
		this.s = s;	
		this.parent = parent;
	}

	private void receiveMessages() throws IOException
	{
		InputStream is = s.getInputStream();
		PrintStream raus = new PrintStream(s.getOutputStream());

		BufferedReader rein = new BufferedReader(new InputStreamReader(
				s.getInputStream()));

//		name = rein.readLine();
//		parent.sendMsgToAll("<newPlayer>" + name);
//		parent.sendMsgToAll("<msg>" + name + ">Joined the room"); // <msg><Chat_name>text
		String m;
		String[] ms;
		while ((m = rein.readLine()) != null)
		{
			receiveMessages();
		}
		ms = m.split(">");
		switch (ms[0])
		{
		case "<msg": // Chat Message for this Player; in ms[1], the Chat is
						// saved
		{
			parent.sendMsgToAll(m);
			break;
		}
		default:
			break;
		}
	}

	public void sendToPlayer(String msg)
	{
		PrintStream raus;
		try
		{
			raus = new PrintStream(s.getOutputStream());
			raus.println(msg);
		} catch (IOException e)
		{ // keine Prüfung, ob Nachricht wirklich ankommt, da Fehler sehr
			// unwahrsceinlich und Nachricht nicht kritisch
		}

	}

	public void run()
	{
		try
		{
			receiveMessages();
		} catch (IOException e)
		{
			System.out.println("Fehler beim Verbinden zum Client:"
					+ e.getMessage());
		}

	}
}
