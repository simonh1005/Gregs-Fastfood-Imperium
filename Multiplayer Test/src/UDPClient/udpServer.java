package UDPClient;
//TEST
import java.io.IOException;
import java.net.*;

public class udpServer
{

	public static void main(String[] args)
	{
		DatagramSocket socket;
		try
		{
			System.out.println("Server Started at port 628");
			socket = new DatagramSocket(628);
			// Auf Anfrage warten

			DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

			socket.receive(packet);
			System.out.println("Packet empfangen" + packet.toString());
			// Empfänger auslesen

			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			int len = packet.getLength();
			byte[] data = packet.getData();

			System.out.printf(
					"Anfrage von %s vom Port %d mit der Länge %d:%n%s%n",
					address, port, len, new String(data, 0, len));

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		while (true)
		{

		}
	}
}
