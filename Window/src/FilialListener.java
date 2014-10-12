import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class FilialListener implements ActionListener
{
	Filiale f;

	public FilialListener(Filiale f)
	{
		this.f = f;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton btn = (JButton) e.getSource();
		String src = btn.getName();

		JOptionPane.showConfirmDialog(null, "Button name: " + src);

	}

}
