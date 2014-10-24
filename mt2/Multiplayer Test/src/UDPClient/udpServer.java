package UDPClient;

import java.io.IOException;
import java.net.*;

import javax.swing.JPanel;

import Server.Sender;
import Server.ServerWin;

public class udpServer
{
	static DatagramSocket udpSocket;
	static ServerSocket tcpSocket;
	public static final int tcpPort = 99;
	static Sender sender;

	public static void main(String[] args)
	{
		System.out.println("Server getartet");
		try
		{
			tcpSocket = new ServerSocket(tcpPort);
			// sender = new Sender(new ServerWin());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true)
		{
			waitForClient(); // blocks until client connects via UDP Broadcast
			connectClient(); // blocks until client connects via tcp
		}

	}

	private static void waitForClient()
	{
		try
		{
			udpSocket = new DatagramSocket(628);
			// Auf Anfrage warten

			DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
			udpSocket.receive(packet);

			// Empfänger auslesen

			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			int len = packet.getLength();
			byte[] data = packet.getData();

			System.out.printf(
					"Anfrage von %s vom Port %d mit der Länge %d:%n%s%n",
					address, port, len, new String(data, 0, len));
			System.out.println("Warte auf TCP Verbindung");
			answer(address, port);
			udpSocket.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void connectClient()
	{
		try
		{
			Socket tcpClient = tcpSocket.accept();
			System.out.println("TCP Verbindungerfolgt");
			// sender.addPlayer(tcpClient);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	private static void answer(InetAddress a, int port) throws IOException
	{ // Sends the Port of the TCP Socket
		int serverPort = udpSocket.getLocalPort();
		String buffer = "" + serverPort;
		byte[] data = buffer.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, a, port);
		udpSocket.send(packet);

	}
}
