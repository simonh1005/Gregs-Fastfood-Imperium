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
	ChatWin chat;
	
	public MessageReceiver(Socket server, ChatWin chat)
	{
		this.server = server;
		this.chat = chat;
	}
	
	private void receiveMessage()
	{		
		try
		{
			InputStream is = server.getInputStream();
			BufferedReader rein = new BufferedReader(new InputStreamReader(
					server.getInputStream()));		
			String m;
			while ((m = rein.readLine()) != null)
			{
				chat.getOutput().append(m +"\n");				
			}
		} catch (IOException e)
		{
			System.out.println("Server disconnected");
		}
		
	}

	@Override
	public void run()
	{
		receiveMessage();
		super.run();
	}
	
}
