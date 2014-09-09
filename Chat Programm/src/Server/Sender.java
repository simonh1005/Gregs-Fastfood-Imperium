package Server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Sender extends Thread
{
	Socket[] clients = new Socket[10];
	private int n_o_clients;

	// private String message;

	public void addClient(Socket client)
	{
		if (n_o_clients < 10)
		{
			clients[n_o_clients] = client;
			n_o_clients++;
		} else
		{
			System.out.println("Maximal Anzahl Clients erreicht");
		}
	}

	public void sendToAll(String message, Socket sender, String name)
	{
		String preafix = "";
		for (int i = 0; i < n_o_clients; i++)
		{
			if (clients[i] != sender)
			{
				preafix = name + ": ";
			}
			else
			{
				preafix = "Du: ";
			}
			PrintStream raus;
			try
			{
				raus = new PrintStream(clients[i].getOutputStream());
				raus.println(preafix + message);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		super.run();
	}

}
