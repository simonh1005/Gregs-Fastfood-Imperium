package Starters;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import Client.ClientWindow;

public class AutomaticLauncher
{
	static final int port = 666;
	static final int tcpPort = 99;

	public static void main(String[] args)
	{
		try
		{
			String message = "SearchForServer";
			byte[] bytes = message.getBytes();
			InetAddress inetAddress = InetAddress.getByName("255.255.255.255");
			DatagramPacket packet = new DatagramPacket(bytes, bytes.length,
					inetAddress, port);
			DatagramSocket socket = new DatagramSocket();
			socket.setBroadcast(true);
			socket.send(packet);

			searchServer(0, socket, packet);

			socket.close();
			System.out.println("No error occured");
		} catch (UnknownHostException e)
		{
			System.out.println("No Server Found, will start own server");
			startServer();
			startClient("localhost");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void startServer()
	{
		new ServerStarter().start();

	}

	private static void searchServer(int failures, DatagramSocket socket,
			DatagramPacket packet) throws UnknownHostException
	{

		if (failures < 3)
		{
			try
			{
				socket.setSoTimeout(200);
				socket.receive(packet);
				InetAddress ip = packet.getAddress();
				System.out.println("Server found at IP:" + ip);
				startClient(ip.getHostAddress());
			} catch (IOException e)
			{
				searchServer(failures + 1, socket, packet);
				System.out.println("Connection to server failed" + failures
						+ " time(s); Trying again");
			}
		} else
		{
			throw new UnknownHostException();
		}

	}

	private static void startClient(String ip)
	{
		ClientWindow client = new ClientWindow(ip, tcpPort);
		client.setBounds(100, 100, 500, 200);
	}

}
