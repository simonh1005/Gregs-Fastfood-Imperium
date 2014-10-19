package Client;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLayeredPane;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class UIWindow {

	JFrame frmFoodimperium;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	
//	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Window window = new Window();
//					window.frmFoodimperium.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	
	public UIWindow() {
		initialize();
	}
	private void buildMap(JLayeredPane pane)
	{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try
		{
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("src/map.xml"));
			Element map = document.getDocumentElement();
			NodeList filiale_node = map.getElementsByTagName("filiale");
			NodeList bezirk_node = map.getElementsByTagName("bezirk");
			JLabel[] bezirk_lbl = new JLabel[8]; 
			Bezirk[] bezirke = new Bezirk[8];
			Filiale[] filialen = new Filiale[42];
			Rectangle[] bezirk_pos = {new Rectangle(180,93,148,107),new Rectangle(102,237,175,130),new Rectangle(371,80,98,120),new Rectangle(316,254,108,66),
										new Rectangle(145,381,176,100),new Rectangle(346,356,165,132),new Rectangle(490,157,122,205),new Rectangle(523,380,238,140)};
			int z = 0;
			for (int i = 0; i < bezirk_node.getLength(); i++)
			{
				//System.out.println("Neuer Bezirk"
				//		+ bezirk_node.item(i).getAttributes().getNamedItem("name")
				//				.getNodeValue());
				int anzFil = Integer.parseInt(bezirk_node.item(i).getAttributes().getNamedItem("maxFilialen").getNodeValue());
				int[] boni = {1,1,1};
				bezirke[i] = new Bezirk(
								Integer.parseInt(bezirk_node.item(i).getAttributes()
								.getNamedItem("id").getNodeValue()), bezirk_node.item(i)
								.getAttributes().getNamedItem("name")
								.getNodeValue(), Integer.parseInt(bezirk_node.item(i)
								.getAttributes().getNamedItem("einwohner")
								.getNodeValue()), anzFil, boni);
				
				bezirk_lbl[i] = new JLabel("");			
//				bezirk_lbl[i].setBackground(Color.white); //zum Testen
//				bezirk_lbl[i].setOpaque(true);
				bezirk_lbl[i].setToolTipText(bezirke[i].toHTML());
				bezirk_lbl[i].setBounds(bezirk_pos[i]);
					
				for (int j = 0; j < anzFil; j++)
				{
					Element f = (Element) filiale_node.item(z);
					Rectangle pos = new Rectangle(Integer.parseInt(getDirectChildValue(f, "posX")),Integer.parseInt(getDirectChildValue(f, "posY")),10,10);
					filialen[z] = new Filiale(10*i+j, getDirectChildValue(f, "name"),Integer.parseInt(getDirectChildValue(f, "kaufpreis")));					
					JButton btn = new JButton();
					btn.setForeground(Color.LIGHT_GRAY);
					btn.setBounds(pos);
					btn.setName("fil_btn" + 10*i+j);
					btn.addActionListener(new FilialListener(filialen[z],pane));
					z++;
					pane.add(btn);
				}
				pane.add(bezirk_lbl[i]);	
				//System.out.println("  AnzFilialen: " + anzFil);
			}		
			System.out.println("AnzFilialen gesamt: " + z);
		} catch (ParserConfigurationException|SAXException|IOException e)
		{
			e.printStackTrace();
		}		
		
	}
	public static String getDirectChildValue(Element parent, String name)
	{
	    for(Node child = parent.getFirstChild(); child != null; child = child.getNextSibling())
	    {
	        if(child instanceof Element && name.equals(child.getNodeName())) return child.getTextContent();
	    }
	    return "";
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFoodimperium = new JFrame();
		frmFoodimperium.setIconImage(Toolkit.getDefaultToolkit().getImage(UIWindow.class.getResource("/Icon/burger-icon.png")));
		frmFoodimperium.setTitle("Fast-Food Empire");
		frmFoodimperium.setBounds(100, 100, 902, 735);
		frmFoodimperium.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JLabel lblNewLabel = new JLabel("");
//		lblNewLabel.setIcon(new ImageIcon(UIWindow.class.getResource("/Icon/Finance-Money-icon.png")));	
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(UIWindow.class.getResource("/Icon/Time-And-Date-Clock-icon.png")));
		GroupLayout groupLayout = new GroupLayout(frmFoodimperium.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(714, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE))
		);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("\u00DCbersicht", null, layeredPane, null);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Finanzen", null, layeredPane_1, null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_1.setBounds(0, 0, 868, 630);
		layeredPane_1.add(tabbedPane_1);
		
		JLayeredPane layeredPane_19 = new JLayeredPane();
		tabbedPane_1.addTab("Gesamt", null, layeredPane_19, null);
		
		JLayeredPane layeredPane_6 = new JLayeredPane();
		tabbedPane_1.addTab("Bilanz", null, layeredPane_6, null);
		
		JLayeredPane layeredPane_18 = new JLayeredPane();
		tabbedPane_1.addTab("Kalkulation", null, layeredPane_18, null);
		
		JLayeredPane layeredPane_17 = new JLayeredPane();
		tabbedPane_1.addTab("Kredit", null, layeredPane_17, null);
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabbedPane.addTab("Filialen", null, layeredPane_2, null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_2.setBounds(0, 0, 868, 630);
		layeredPane_2.add(tabbedPane_2);
		
		JLayeredPane layeredPane_7 = new JLayeredPane();
		tabbedPane_2.addTab("Gesamt", null, layeredPane_7, null);
		
		JLayeredPane layeredPane_21 = new JLayeredPane();
		tabbedPane_2.addTab("Immobilien", null, layeredPane_21, null);
		
		JLayeredPane layeredPane_11 = new JLayeredPane();
		tabbedPane_2.addTab("Map", null, layeredPane_11, null);

		
		buildMap(layeredPane_11);
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(UIWindow.class.getResource("/Icon/BerlinKarteStrasse3.png")));
		label_1.setBounds(0, 0, 797, 614);
		layeredPane_11.add(label_1);
		
		JLayeredPane layeredPane_3 = new JLayeredPane();
		tabbedPane.addTab("Marktplatz", null, layeredPane_3, null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_3.setBounds(0, 0, 868, 630);
		layeredPane_3.add(tabbedPane_3);
		
		JLayeredPane layeredPane_20 = new JLayeredPane();
		tabbedPane_3.addTab("Gesamt", null, layeredPane_20, null);
		
		JLayeredPane layeredPane_8 = new JLayeredPane();
		tabbedPane_3.addTab("Döner", null, layeredPane_8, null);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 11, 60, 60);
		layeredPane_8.add(label);
		
		JButton btnKaufen = new JButton("Kaufen");
		btnKaufen.setBounds(109, 120, 89, 23);
		layeredPane_8.add(btnKaufen);
		
		JButton btnVerkaufen = new JButton("Verkaufen");
		btnVerkaufen.setBounds(218, 120, 89, 23);
		layeredPane_8.add(btnVerkaufen);
		
		JLabel lblGemse = new JLabel("Döner");
		lblGemse.setBounds(109, 11, 46, 14);
		layeredPane_8.add(lblGemse);
		
		JLabel lblInventar = new JLabel("Inventar:");
		lblInventar.setBounds(109, 36, 46, 14);
		layeredPane_8.add(lblInventar);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setText("0");
		textField_2.setBounds(185, 33, 66, 20);
		layeredPane_8.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblGteklasse = new JLabel("Güteklasse:");
		lblGteklasse.setBounds(109, 61, 66, 14);
		layeredPane_8.add(lblGteklasse);
		
		JLabel lblAuswahl = new JLabel("Auswahl:");
		lblAuswahl.setBounds(109, 86, 46, 14);
		layeredPane_8.add(lblAuswahl);
		
		textField_3 = new JTextField();
		textField_3.setText("0");
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setBounds(185, 83, 66, 20);
		layeredPane_8.add(textField_3);
		textField_3.setColumns(10);
		
//		textField_4 = new JTextField();
//		textField_4.setBounds(185, 58, 66, 20);
//		layeredPane_8.add(textField_4);
//		textField_4.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		layeredPane_8.setLayer(comboBox, 0);
		comboBox.setBounds(185, 58, 66, 20);
		layeredPane_8.add(comboBox);
		
		JLayeredPane layeredPane_12 = new JLayeredPane();
		tabbedPane_3.addTab("Pizza", null, layeredPane_12, null);
		
		JLayeredPane layeredPane_13 = new JLayeredPane();
		tabbedPane_3.addTab("Wurst", null, layeredPane_13, null);
		
		JLayeredPane layeredPane_14 = new JLayeredPane();
		tabbedPane_3.addTab("Sonstige", null, layeredPane_14, null);
		
		JLayeredPane layeredPane_4 = new JLayeredPane();
		tabbedPane.addTab("Marketing", null, layeredPane_4, null);
		
		JTabbedPane tabbedPane_4 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_4.setBounds(0, 0, 868, 630);
		layeredPane_4.add(tabbedPane_4);
		
		JLayeredPane layeredPane_9 = new JLayeredPane();
		tabbedPane_4.addTab("Werbung", null, layeredPane_9, null);
		
		JLayeredPane layeredPane_15 = new JLayeredPane();
		tabbedPane_4.addTab("Sponsoring", null, layeredPane_15, null);
		
		JLayeredPane layeredPane_5 = new JLayeredPane();
		tabbedPane.addTab("Forschung", null, layeredPane_5, null);
		
		JTabbedPane tabbedPane_5 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_5.setBounds(0, 0, 868, 630);
		layeredPane_5.add(tabbedPane_5);
		
		JLayeredPane layeredPane_10 = new JLayeredPane();
		tabbedPane_5.addTab("Marktforschung", null, layeredPane_10, null);
		
		JLayeredPane layeredPane_16 = new JLayeredPane();
		tabbedPane_5.addTab("Produktforschung", null, layeredPane_16, null);
		frmFoodimperium.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmFoodimperium.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmTips = new JMenuItem("Tips");
		mnHelp.add(mntmTips);
		
		JMenu mnOption = new JMenu("Option");
		menuBar.add(mnOption);
	}
}
