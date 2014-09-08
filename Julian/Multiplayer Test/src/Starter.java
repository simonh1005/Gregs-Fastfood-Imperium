import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Client.ClientWindow;
import Server.ServerWin;

public class Starter extends JFrame implements ActionListener
{
	JCheckBox server_cb = new JCheckBox("Also start the Server?");
	JCheckBox client_cb = new JCheckBox("Choose Names manually");
	JLabel client_label = new JLabel("Number of Clients: ");
	JTextField client_tf = new JTextField(5);
	JButton go_btn = new JButton("Start programms");
	JPanel panel = new JPanel(new FlowLayout());
	JPanel cb_panel = new JPanel(new FlowLayout());

	public static void main(String[] args)
	{
		Starter s = new Starter();
		s.setBounds(500, 500, 600, 150);
		s.setVisible(true);

	}

	public Starter()
	{
		panel.add(client_label);
		panel.add(client_tf);

		cb_panel.add(server_cb);
		cb_panel.add(client_cb);
		
		go_btn.addActionListener(this);
		
		add(panel, BorderLayout.CENTER);
		add(cb_panel, BorderLayout.NORTH);
		add(go_btn, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if (server_cb.isSelected())
		{
			new ServerStarter().start(); // starts Server in new Thread
		}
		try
		{
			int client_nb = Integer.parseInt(client_tf.getText());
			if (client_cb.isSelected())
			{
				for (int i = 0; i < client_nb; i++)
				{
					ClientWindow client = new ClientWindow();
					client.setBounds(100, 100, 500, 200);
				}
			} else
			{
				for (int i = 0; i < client_nb; i++)
				{
					ClientWindow client = new ClientWindow("player" + (i + 1));
					client.setBounds(100, 100, 500, 200);
				}
				
			}
		} finally
		{

		}
		dispose();
	}

}
