package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientWindow extends JFrame implements ActionListener, KeyListener
{
	static String server = "localhost";
	static int port = 99;
	boolean isAdmin;
	JButton start_btn;
	JTextField chat_tb = new JTextField(255);
	String username;
	Socket s;
	PrintStream out;
	JPanel content_panel = new JPanel(new BorderLayout());
	JTextArea chat = new JTextArea();
	DefaultListModel<String> playerlistModel = new DefaultListModel<String>();
	JList<String> playerBox = new JList<String>(playerlistModel);

	public ClientWindow(String username) throws HeadlessException
	{
		super();
		this.username = username;
		// this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildUI();
	}

	public ClientWindow() throws HeadlessException
	{
		super();
		username = JOptionPane.showInputDialog("Bitte geben Sie ihren Namen ein");		
		// this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildUI();

	}
	public ClientWindow(String server, int tcpPort) throws HeadlessException
	{
		super();
		username = JOptionPane.showInputDialog("Bitte geben Sie ihren Namen ein");		
		// this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.server = server;
		this.port = tcpPort;
		buildUI();

	}
	private void buildUI()
	{
		this.setVisible(true);
		if (username == null)
		{
			dispose();
		}
		this.setTitle(username);
		
		playerBox.setBackground(Color.gray);
		playerlistModel.addElement(username);
		content_panel.add(playerBox, BorderLayout.NORTH);
		chat_tb.addKeyListener(this);
		chat.setEditable(false);
		content_panel.add(chat, BorderLayout.CENTER);
		content_panel.add(chat_tb, BorderLayout.SOUTH);
		add(content_panel, BorderLayout.CENTER);
		
		
		connectToServer();
	}

	private void connectToServer()
	{
		try
		{
			s = new Socket(server, port);
			out = new PrintStream(s.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			String posstring = in.readLine();
			if (posstring.equals("1"))
			{
				isAdmin = true;
				start_btn = new JButton("Start");
				start_btn.addActionListener(this);
				start_btn.setEnabled(false);
				add(start_btn, BorderLayout.SOUTH);
			}
			else if(posstring.equals("-1")) //Game already full
			{
				JOptionPane.showMessageDialog(this,
						"Die maximale Anzahl an spielern ist bereits erreicht \n Programm wird beendet");
				dispose();
			}
			MessageReceiver mr = new MessageReceiver(s, this);
			out.println(username);
			mr.start();

		} catch (UnknownHostException e)
		{
			JOptionPane.showMessageDialog(this, "Ein unbekannter fehler ist aufgetreten! \n Programm wird beendet");
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(this,
					"Server wurde nicht gefunden! \n Programm wird beendet");
			System.exit(-1);
			
		}
	}

	public void addPlayer(String name)
	{
		if (!(start_btn == null))
		{
			start_btn.setEnabled(true);
		}
		playerlistModel.addElement(name);

	}
	public void displayMessage(String message, String sender)
	{
		chat.append(sender + ": " + message + "\n");
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent key)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent key)
	{
		if (key.getKeyCode() == KeyEvent.VK_ENTER)
		{
			writeMsg(chat_tb.getText());			
			displayMessage(chat_tb.getText(), "Du");
			chat_tb.setText("");
		}
		
	}

	private void writeMsg(String message)
	{
			out.println("<msg>" + username + ">" + message);	
	}

	@Override
	public void keyTyped(KeyEvent key)
	{
				
	}
}
