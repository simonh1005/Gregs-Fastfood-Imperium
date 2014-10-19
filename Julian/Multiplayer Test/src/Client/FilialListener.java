package Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class FilialListener implements ActionListener
{
	Filiale f;
	JLayeredPane p;

	public FilialListener(Filiale f, JLayeredPane pane)
	{
		this.f = f;
		p = pane;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton btn = (JButton) e.getSource();
		String src = btn.getName();
		JOptionPane.showConfirmDialog(p,f.toHTML());

	}

}
