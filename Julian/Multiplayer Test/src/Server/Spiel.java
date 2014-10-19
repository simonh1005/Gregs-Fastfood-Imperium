package Server;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Spiel
{
	Spieler[] spieler;
	ArrayList<JahresBerichtAlle> jahresberichte = new ArrayList<JahresBerichtAlle>();
	Bezirk[] bezirke = new Bezirk[8];
	Timer timer = new Timer();

	public Spiel(Socket[] socks, String[] names)
			throws ParserConfigurationException, SAXException, IOException
	{
		System.out.println("Neues Spiel wurde gestarted");
		spieler = new Spieler[socks.length];
		for (int i = 0; i < socks.length; i++)
		{
			spieler[i] = new Spieler(socks[i], names[i], this);
		}
		loadMap();
		loadMarket();
		start();
		System.out.println("Spiel wurde geladen");
	}

	private void start()
	{
		timer.schedule(new TimerTask()
		{
			
			@Override
			public void run()
			{
				pruefeRundenEnde();
				
			}
		}, 10000);
		
	}

	private void loadMarket() throws ParserConfigurationException,
			SAXException, IOException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File("src/markt.xml"));
		Element markt = document.getDocumentElement();
		NodeList preisNodes = markt.getElementsByTagName("preis");
		for (int i = 0; i < 4; i++)
		{
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

	private void loadMap() throws ParserConfigurationException // Lese Map
																// (Bezirke +
																// Filialen) aus
																// Datei
			, SAXException, IOException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File("src/map.xml"));
		Element map = document.getDocumentElement();
		NodeList filiale_node = map.getElementsByTagName("filiale");
		NodeList bezirk_node = map.getElementsByTagName("bezirk");

		int z = 0;
		for (int i = 0; i < bezirk_node.getLength(); i++)
		{
			// System.out.println("Neuer Bezirk"
			// + bezirk_node.item(i).getAttributes().getNamedItem("name")
			// .getNodeValue());
			int anzFil = Integer.parseInt(bezirk_node.item(i).getAttributes()
					.getNamedItem("maxFilialen").getNodeValue());
			int[] boni = new int[] { 1, 1, 1 }; // Müssen noch richtig
												// gespeichert werden;
			bezirke[i] = new Bezirk(i, bezirk_node.item(i).getAttributes()
					.getNamedItem("name").getNodeValue(),
					Integer.parseInt(bezirk_node.item(i).getAttributes()
							.getNamedItem("einwohner").getNodeValue()), anzFil,
					boni);

			for (int j = 0; j < anzFil; j++)
			{
				Element f = (Element) filiale_node.item(z);
				bezirke[i].addNewFiliale(getDirectChildValue(f, "name"),
						Integer.parseInt(getDirectChildValue(f, "kaufpreis")));
				z++;
			}
			// System.out.println("  AnzFilialen: " + anzFil);
		}

	}

	private String getDirectChildValue(Element parent, String name) // hilfsmethode
																	// für
																	// loadMap
	{
		for (Node child = parent.getFirstChild(); child != null; child = child
				.getNextSibling())
		{
			if (child instanceof Element && name.equals(child.getNodeName()))
				return child.getTextContent();
		}
		return "";
	}

	private void setJahresBericht(String QuartalsBericht)
	{
		JahresBerichtAlle bericht =jahresberichte.get(jahresberichte.size()-1);
		bericht.setQuartalsbericht(QuartalsBericht);
	}

	public void calcKundschaft()
	{

	}

	private void calcMarktpreis()
	{

	}

	public String filialePruefen(int fid, Spieler spieler)
	{
		return  bezirke[(int)(fid/10)].getFiliale(fid).getBesitzer();
		
	}
	
	public void pruefeRundenEnde()
	{
		for (Spieler s : spieler)
		{
			s.sendToPlayer("<rundenEnde>");
		}
	}	
	public Bezirk getBezirk(int id)
	{
		return bezirke[id];
	}

	public void sendMsgToAll(String msg)
	{
		for (Spieler s: spieler)
		{
			s.sendToPlayer(msg);
		}
		
	}
}
