package UDPClient;

import java.io.IOException;
import java.net.*;

public class udpClient
{
	static final int port = 628;

	public static void main(String[] args)
	{
		try
		{
			String message = "myName";
			byte[] bytes = message.getBytes();
			InetAddress inetAddress = InetAddress.getByName("255.255.255.255");
			DatagramPacket packet = new DatagramPacket(bytes, bytes.length,inetAddress, port);
			DatagramSocket socket = new DatagramSocket();
			socket.setBroadcast(true);
			socket.send(packet);
			
			socket.setSoTimeout(1000);
			socket.receive(packet);
			InetAddress ip = packet.getAddress();
			
			System.out.println("Server found at IP:" + ip);
			socket.close();
			System.out.println("No error occured");
		} catch (SocketException e)
		{
			e.printStackTrace();
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
