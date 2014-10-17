package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

public class Server
{
	public static final int port = 99;
	public static final int udpPort = 666;
	private ServerSocket serverSock;
	private DatagramSocket udpSocket;
	private Socket client;
	private static Server instance;
	Sender sender;

	private void startServer()
	{

	}

	private void acceptNewClients()
	{
		connectClient(); // Wait for the first client, he started the server and
		// does not need to send an UDP request

		while (true) // Wait for Clients, start new ClientHandler for each one
		// and add him to the sender
		{
			waitForClient();
			connectClient();
		}
	}

	private void waitForClient()
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
			Socket tcpClient = serverSock.accept();
			sender.addPlayer(tcpClient);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

	private Server() throws IOException
	{
		serverSock = new ServerSocket(port);
	}
	private void answer(InetAddress a, int port) throws IOException
	{ // Sends the Port of the TCP socket vis the UDP socket
		int serverPort = serverSock.getLocalPort();
		String buffer = "" + serverPort;
		byte[] data = buffer.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, a, port);
		udpSocket.send(packet);
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
	public static void newServer()
	{
		if (instance == null)
		{
			try
			{
				instance = new Server();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
