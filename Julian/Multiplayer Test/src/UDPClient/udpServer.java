package UDPClient;

import java.io.IOException;
import java.net.*;

public class udpServer {
	static DatagramSocket socket;
	public static void main(String[] args) {
		//neuer Kommentar
		try {
			socket = new DatagramSocket(628);
			// Auf Anfrage warten

			DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
			socket.receive(packet);

			// Empfänger auslesen

			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			int len = packet.getLength();
			byte[] data = packet.getData();

			System.out.printf(
					"Anfrage von %s vom Port %d mit der Länge %d:%n%s%n",
					address, port, len, new String(data, 0, len));
			
			answer(address, port);
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void answer(InetAddress a, int port) throws IOException {
		String ip = socket.getLocalAddress().toString();
		byte[] data = ip.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, a, port);
		
		socket.send(packet);
		
	}
}
