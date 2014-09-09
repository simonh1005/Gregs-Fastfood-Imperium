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

	@Override
	public void run()
	{

		try
		{
			InputStream is = client.getInputStream();
			BufferedReader rein = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			PrintStream raus = new PrintStream(client.getOutputStream());
			name = rein.readLine();
			System.out.println(name);
			String m;
			while ((m = rein.readLine()) != null)
			{
				System.out.println(m);

				sender.sendToAll(m, client, name);
			}

			System.out.println((byte) is.read());

		} catch (IOException e)
		{
			System.out.println("Client disconnected");
		}
	}

	public ClientHandler(Socket s,Sender sender)
	{
		client = s;
		this.sender = sender;
		System.out.println("Neuer Client handler");
	}

}
