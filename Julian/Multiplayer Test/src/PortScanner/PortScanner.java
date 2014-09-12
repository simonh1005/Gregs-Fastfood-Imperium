package PortScanner;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.PortUnreachableException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PortScanner extends JFrame implements ActionListener
{
	JTextArea ip_ta = new JTextArea("localhost");
	JTextArea port_ta = new JTextArea("port");
	JButton go_btn = new JButton("Go");
	JPanel panel = new JPanel();

	public static void main(String[] args)
	{
		PortScanner ps = new PortScanner();

	}

	public PortScanner()
	{
		this.setBounds(100, 100, 500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.setLayout(new BorderLayout());
		panel.add(ip_ta, BorderLayout.NORTH);
		panel.add(port_ta, BorderLayout.SOUTH);

		add(panel, BorderLayout.NORTH);
		go_btn.addActionListener(this);
		add(go_btn, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		udpScan();
		tcpScan();
	}

	private void tcpScan()
	{
		try
		{
			System.out.println();
			System.out.print("TCP Scan:");
			Socket s = new Socket(ip_ta.getText(), Integer.parseInt(port_ta.getText()));
		} catch (NumberFormatException e)
		{
			System.out.println("Fehlerehafte Port-Eingabe");
			e.printStackTrace();
		} catch (UnknownHostException e)
		{
			System.out.println("Port belegt");
			e.printStackTrace();
		} catch (IOException e)
		{
			System.out.println("Fehler");			
		}
		
		

	}

	private void udpScan()
	{
		try
		{
			DatagramSocket s = new DatagramSocket(751);
			byte[] msg = "abcd".getBytes();
			InetAddress ip = InetAddress.getByName(ip_ta.getText());
			DatagramPacket p = new DatagramPacket(msg, msg.length, ip,
					Integer.parseInt(port_ta.getText()));

			s.send(p);
			System.out.println("UDP Scan: Port eventuell frei");
		} catch (NumberFormatException | SocketException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			PortUnreachableException pe = new PortUnreachableException();
			if (e.getClass().equals(pe.getClass()))
			{
				System.out.println("UDP Scan: Port nicht erreichbar");
			}
		}
	}
}
