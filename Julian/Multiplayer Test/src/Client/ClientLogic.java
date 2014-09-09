package Client;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ClientLogic
{

	public ClientLogic()
	{
		// TODO Auto-generated constructor stub
	}
	public static void serverDisconnected()
	{
		JOptionPane.showMessageDialog(null, "Server disconnected, Game will be closed");
		System.exit(0);
	}

}
