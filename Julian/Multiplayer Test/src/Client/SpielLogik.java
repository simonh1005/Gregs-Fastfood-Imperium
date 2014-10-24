package Client;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class SpielLogik {

	private int aktJahr;
	private int aktQuartal;
	private Spieler spieler;
	private UIWindow ui;
	private Marktpreis preise;
	private Bezirk[] bezirke = new Bezirk[8];

	public SpielLogik(Socket socket, String name, UIWindow ui) { // Infos von
																	// Map etc
																	// kriegen
		spieler = new Spieler(this, name, socket);
		this.ui = ui;
		aktJahr = 1;
		aktQuartal = 1;
		try {					//Eventuell Fehler wenn SpielLogik als parent aufgebaut wird?
			loadMap();
			loadMarket();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SpielLogik(Socket socket , String name) {
		spieler = new Spieler(this, name, socket);
		aktJahr = 1;
		aktQuartal = 1;
	}

	public void spielBeenden() {
		spieler.sendToServer("<lost>");
		JOptionPane.showMessageDialog(null, "Sie haben leider verloren.");
	}

	public void rundenEnde() { // die Kunden pro Filiale vom Server kriegen als
								// int[]
		for (Bezirk b : spieler.getBezirke()) {
			for (int i = 0; i < b.getMaxFilialen(); i++) {
				spieler.sendToServer("<FilproPreis>"
						+ b.getFiliale(i).getProduktPreis());
				spieler.sendToServer("<Filmit>"
						+ b.getFiliale(i).getMitarbeiter());
			}
			spieler.berechneEinnahmen();
			spieler.setLiquiditaet();
			spieler.ausscheidenPruefen();
			spieler.setMonatlicheKosten();
		}

		if (aktQuartal / 4 == 1) {
			spieler.sendToServer("<quartalsbericht>" + "Neuer Bericht");// //////////////////////////////Noch
																		// von
																		// Quartalsbericht
																		// in
																		// String
																		// umwandeln
																		// implementieren
		}
	}

	public QuartalsBericht showBerichte(String spieler, int jahr, int quartal) {
		// zurueckgestellt
		return null;
	}

	public void setAbsaetze(int[] absatz) {// ////////
		// Was macht die hier?
	}

	public Spieler getSpieler() {
		return spieler;
	}

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}

	public int getAktJahr() {
		return aktJahr;
	}

	public void setAktJahr(int aktJahr) {
		this.aktJahr = aktJahr;
	}

	public int getAktQuartal() {
		return aktQuartal;
	}

	public void setAktQuartal(int aktQuartal) {
		this.aktQuartal = aktQuartal;
	}

	private void loadMarket() throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File("src/markt.xml"));
		Element markt = document.getDocumentElement();
		NodeList preisNodes = markt.getElementsByTagName("preis");
		for (int i = 0; i < 4; i++) {
			Element aktPreis = (Element) preisNodes.item(i);
			Marktpreis.setMarktPreis(i, getDirectChildValue(aktPreis, "name"),
					Double.parseDouble(getDirectChildValue(aktPreis,
							"qualitaet1")), Double
							.parseDouble(getDirectChildValue(aktPreis,
									"qualitaet2")), Double
							.parseDouble(getDirectChildValue(aktPreis,
									"qualitaet3")));
		}

	}

	private void loadMap() throws ParserConfigurationException, SAXException,
			IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File("src/map.xml"));
		Element map = document.getDocumentElement();
		NodeList filiale_node = map.getElementsByTagName("filiale");
		NodeList bezirk_node = map.getElementsByTagName("bezirk");

		int z = 0;
		for (int i = 0; i < bezirk_node.getLength(); i++) {
			int anzFil = Integer.parseInt(bezirk_node.item(i).getAttributes()
					.getNamedItem("maxFilialen").getNodeValue());
			String[] boniStr = bezirk_node.item(i).getAttributes()
					.getNamedItem("name").getNodeValue().split(",");
			double[] boni = new double[] { Double.parseDouble(boniStr[0]),
					Double.parseDouble(boniStr[1]),
					Double.parseDouble(boniStr[2]) };
			bezirke[i] = new Bezirk(i, bezirk_node.item(i).getAttributes()
					.getNamedItem("name").getNodeValue(),
					Integer.parseInt(bezirk_node.item(i).getAttributes()
							.getNamedItem("einwohner").getNodeValue()), anzFil,
					boni);

			for (int j = 0; j < anzFil; j++) {
				Element f = (Element) filiale_node.item(z);
				bezirke[i].addNewFiliale(getDirectChildValue(f, "name"),
						Integer.parseInt(getDirectChildValue(f, "kaufpreis")));
				z++;
			}
		}

	}

	private String getDirectChildValue(Element parent, String name) // hilfsmethode
	// für
	// loadMap
	{
		for (Node child = parent.getFirstChild(); child != null; child = child
				.getNextSibling()) {
			if (child instanceof Element && name.equals(child.getNodeName()))
				return child.getTextContent();
		}
		return "";
	}

}
