package Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ServerWin extends JFrame implements ActionListener
{
	public static final int port = 99;
	JTextArea display = new JTextArea();
	JButton ok_btn = new JButton("OK");
	ServerSocket server;
	Sender sender = new Sender();

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public static void main(String[] args)
	{
		ServerWin win = new ServerWin();
		win.setBounds(0, 0, 200, 200);
		win.setDefaultCloseOperation(EXIT_ON_CLOSE);
		win.setVisible(true);
	}

	public ServerWin()
	{
		super("ChatServer");

		try
		{
			server = new ServerSocket(port);

			add(display, "Center");
			System.out.println("server gestartet");

			start();
//			test();
		} catch (IOException e)
		{
			System.out.println("Belegen des Ports fehlgeschlagen");
			System.exit(ABORT);
		}

	}

	private void start()
	{
		while (true)
		{
			try
			{
				Socket client = server.accept();
	
				new ClientHandler(client,sender).start();
				sender.addClient(client);
	
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}

	private void test()
	{
		try
		{
			Socket client = server.accept();

			InputStream is = client.getInputStream();
			int m;
			while ((m = is.read()) != -1)
			{
				System.out.println((byte) m);
			}

			System.out.println((byte) is.read());

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
