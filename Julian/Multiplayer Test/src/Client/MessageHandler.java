package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.sound.midi.Receiver;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Server.Spieler;

public class MessageHandler extends Thread {
	Socket server;
	SpielLogik parent;

	public MessageHandler(Socket server, SpielLogik parent) {
		this.server = server;
		this.parent = parent;
	}

	private void receiveMessage() {
		try {
			InputStream is = server.getInputStream();
			BufferedReader rein = new BufferedReader(new InputStreamReader(
					server.getInputStream()));
			String m;
			String[] ms = new String[3]; // tag in 1st element and parameter in
											// the 2nd
			while ((m = rein.readLine()) != null) {
				ms = m.split(">", 3);

				switch (ms[0]) {

				case "<rundenEnde": // Chat Message for this Player; in ms[1],
									// the sender is saved
				{
					JOptionPane.showConfirmDialog(null, "Rundenedme");
					parent.rundenEnde();
					break;
				}
				case "<answer": // Antwort des Servers auf eine Anfrage, in
								// ms[1] steht die Anfrage, auf dieGeantwortet
								// wurde, in ms[2] die Antwort
				{
					switch (ms[1]) {
					case "filKauf":
						int bid = Integer.parseInt(ms[2].split(">")[0]
								.substring(0, 1));
						int fid = Integer.parseInt(ms[2].split(">")[0]
								.substring(1));
						String besitzer = ms[2].split(">")[1];
						// parent.getSpieler().getBezirke()[bid].getFilialen(fid).setBesitzer(besitzer);
						break;
					default:
						break;
					}
					break;
				}
				case "<preise":
				{
					String[] z11 = ms[1].split(",");	//1,1,1  2,2,2  3,3,3  4,4,4
					int z;
					Marktpreis[] preise = new Marktpreis[4];
					for (int i = 0; i < 4; i++) {
						z = i*3;
						preise[i].setPreise(new double[]{Double.parseDouble(z11[z]),Double.parseDouble(z11[z+1]),Double.parseDouble(z11[z+2])});
					}
					parent.getSpieler().setPreise(preise);	
				}
				case "<kunden": //<kunden>1,2,3,4,5,...42
				{
					int z = 0;
					String[] z11 = ms[1].split(",");
					
					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < parent.getSpieler().getBezirke()[i].getAnzFilialen(); j++) {
							parent.getSpieler().getBezirke()[i].getFiliale(z).setMaxKunden(Integer.parseInt(z11[z]));
							z++;
						}
					}
				}
				case "<exit": {
					if (ms[1].equals("server")) {
						JOptionPane.showMessageDialog(null,
								"Server disconnected.");
						System.exit(0);
					}
				}
				default:
					break;
				}
			}
		} catch (IOException e) {
			System.out.println("Server disconnected");
		}

	}

	@Override
	public void run() {
		super.run();
		receiveMessage();

	}

}
