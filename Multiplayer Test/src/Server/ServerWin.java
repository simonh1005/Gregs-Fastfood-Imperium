package Server;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ServerWin extends JFrame
{
	ServerSocket server;
	public static final int port = 99;
	JTextArea display = new JTextArea();
	Sender sender;

	public ServerWin()
	{
		
		super("ChatServer");
		sender = new Sender(this);
		display.setEditable(false);
		add(display, BorderLayout.CENTER);
		this.setBounds(500, 500, 500, 500);
		this.setIconImage(new ImageIcon("src/Server/Server.jpg").getImage());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		try
		{
			server = new ServerSocket(port);
			start();

		} catch (IOException e)
		{
			System.out.println("Belegen des Ports fehlgeschlagen");
			System.exit(ABORT);
		}

	}

	private void start()
	{
		display.append("Server gestarted\n");
		while (true) // Wait for Clients, start new ClientHandler for each one
						// and add him to the sender
		{
			try
			{
				Socket client = server.accept();
				
	//			new ClientHandler(client, sender, this).start();
				sender.addPlayer(client);			
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void log(String message)
	{
		display.append(message + "\n");
	}
}
