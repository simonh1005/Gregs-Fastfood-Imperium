package Server;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ServerWin extends JFrame
{
	private static ServerWin instance;	
	public static final int port = 99;
	public static final int udpPort = 666;
	JTextArea display = new JTextArea();	
	Sender sender;
	DatagramSocket udpSocket;
	ServerSocket server;

	public static ServerWin getInstance()
	{
		if (instance == null)
		{
			instance = new ServerWin();
		}	
		return instance;
	}

	private ServerWin()
	{
		super("Game Lobby");
		sender = new Sender();
		display.setEditable(false);
		add(display, BorderLayout.CENTER);
		this.setBounds(500, 500, 500, 500);
		this.setIconImage(new ImageIcon("src/Server/Server.jpg").getImage());
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() { @Override
	        public void windowClosing(WindowEvent event) {  exitServer(); }  });
		
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
		log("Server gestartet" + "\n IP:"
				+ server.getInetAddress().getHostAddress() + " \n Port:" + port);
		connectClient(); // Wait for the first client, he started the server and
							// does not need to send an UDP request

		while (true) // Wait for Clients, start new ClientHandler for each one
						// and add him to the sender
		{
			waitForClient();
			connectClient();
		}

	}

	private void waitForClient() // UDP Socket
	{
		try
		{
			udpSocket = new DatagramSocket(udpPort);

			DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
			udpSocket.receive(packet); // wait for Broadcast of the client

			// get Sender
			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			int len = packet.getLength();
			byte[] data = packet.getData();

			System.out.printf(
					"Anfrage von %s vom Port %d mit der Länge %d:%n%s%n",
					address, port, len, new String(data, 0, len));

			answer(address, port);
			udpSocket.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void connectClient()
	{
		try
		{
			Socket tcpClient = server.accept();
			sender.addPlayer(tcpClient);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void answer(InetAddress a, int port) throws IOException
	{ // Sends the Port of the TCP socket vis the UDP socket
		int serverPort = server.getLocalPort();
		String buffer = "" + serverPort;
		byte[] data = buffer.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, a, port);
		udpSocket.send(packet);
	}

	public void log(String message)
	{
		display.append(message + "\n");
	}

	private void exitServer()
	{
		// Send Message to clients, so they can also close themselves
		sender.sendToAll("<exit>server","server");
		try
		{
			Thread.sleep(300);
		} catch (InterruptedException e)
		{			
		}
		System.exit(0);
		
	}
}
