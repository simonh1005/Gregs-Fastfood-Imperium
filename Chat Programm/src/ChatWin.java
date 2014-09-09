import java.awt.Button;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ChatWin extends JFrame implements ActionListener, KeyListener
{
	JTextArea output = new JTextArea();
	JTextField input = new JTextField(35);
	JButton send_btn = new JButton("Senden");
	JPanel bot_pan = new JPanel();
	String username;

	static final String server = "localhost";
	static final int port = 99;

	PrintStream out;
	Socket s;

	public static void main(String[] args)
	{
		ChatWin win = new ChatWin("Chat");
		win.setBounds(100, 100, 500, 500);
		win.setDefaultCloseOperation(EXIT_ON_CLOSE);
		win.setVisible(true);

	}

	public ChatWin(String title)
	{
		super(title);
		output.setEditable(false);
		username = JOptionPane
				.showInputDialog("Bitte geben Sie ihren Namen ein");

		if (username == null)
		{
			System.exit(ABORT);
		}
		this.setTitle("Chat von " + username);
		send_btn.addActionListener(this);
		input.addKeyListener(this);
		bot_pan.add(input);
		bot_pan.add(send_btn);

		add(output, "Center");
		add(bot_pan, "South");
		try
		{
			s = new Socket(server, port);
			out = new PrintStream(s.getOutputStream());

			MessageReceiver mr = new MessageReceiver(s, this);
			out.println(username);
			mr.start();

		} catch (UnknownHostException e)
		{
			JOptionPane.showMessageDialog(this, "Host wurde nicht gefunden");
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(this,
					"Server not found! \n Programm wird beendet");
			System.exit(-1);
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if (arg0.getSource() == send_btn)
		{
			sendMessage();
		}

	}

	public JTextArea getOutput()
	{
		return output;
	}

	private void sendMessage()
	{
		out.println(input.getText());
		input.setText("");
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			sendMessage();
		}

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

}
