import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DynUI extends JFrame implements ActionListener
{
	JButton[] btn_fil = new JButton[9];
	JLabel[] bezirk_lbl = new JLabel[8];
	Bezirk[] bezirke = new Bezirk[8];

	public static void main(String[] args)
	{
		DynUI ui = new DynUI();

	}

	public DynUI()
	{
		
		this.setBounds(500, 500, 500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		try
		{
			buildUI();
			this.setVisible(true);
		} catch (Exception e)
		{
			System.out.println("Fehler");
			e.printStackTrace();
		}
	}

	private void buildUI() throws ParserConfigurationException, SAXException,
			IOException
	{

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File("src/map.xml"));
		Element map = document.getDocumentElement();
		NodeList filiale = map.getElementsByTagName("filiale");
		NodeList bezirk = map.getElementsByTagName("bezirk");

		for (int i = 0; i < bezirk.getLength(); i++)
		{
			System.out.println("Neuer Bezirk"
					+ bezirk.item(i).getAttributes().getNamedItem("name")
							.getNodeValue());
			bezirke[i] = new Bezirk(
							Integer.parseInt(bezirk.item(i).getAttributes()
							.getNamedItem("id").getNodeValue()), bezirk.item(i)
							.getAttributes().getNamedItem("name")
							.getNodeValue(), Integer.parseInt(bezirk.item(i)
							.getAttributes().getNamedItem("einwohner")
							.getNodeValue()), Integer.parseInt(bezirk.item(i)
							.getAttributes().getNamedItem("maxFilialen")
							.getNodeValue()), bezirk.item(i).getAttributes()
							.getNamedItem("boni").getNodeValue());
			
			bezirk_lbl[i] = new JLabel("       ");			
			bezirk_lbl[i].setBackground(Color.white); //zum Testen
			bezirk_lbl[i].setOpaque(true);
//			bezirk_lbl[i].addMouseListener(new LabelAdapter());
			bezirk_lbl[i].setToolTipText(bezirke[i].toHTML());
			add(bezirk_lbl[i]);
			System.out.println(bezirk_lbl[i].getBackground());
		}
		
		
	
		for (int i = 0; i < filiale.getLength(); i++)
		{

			System.out.println("Neue Filiale:" + i);

		}
			
	}

	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}
}
