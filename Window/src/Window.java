import java.awt.EventQueue;

import javax.swing.JDialog;
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
import javax.swing.JOptionPane;
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

  
public class Window {

	private JFrame frmFoodimperium;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_4;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_3;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_27;
	private JTextField textField_28;
	private JTextField textField_29;
	private JTextField textField_30;
	private JTextField textField_31;
	private JTextField textField_32;
	private JTextField textField_33;
	private JTextField textField_34;
	private JTextField textField_35;
	
	JLabel tmp_pane = new JLabel("pos");//////////////////////////

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmFoodimperium.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window()
	{
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
				System.out.println("Neuer Bezirk"
						+ bezirk_node.item(i).getAttributes().getNamedItem("name")
								.getNodeValue());
				int anzFil = Integer.parseInt(bezirk_node.item(i).getAttributes().getNamedItem("maxFilialen").getNodeValue());
				bezirke[i] = new Bezirk(
								Integer.parseInt(bezirk_node.item(i).getAttributes()
								.getNamedItem("id").getNodeValue()), bezirk_node.item(i)
								.getAttributes().getNamedItem("name")
								.getNodeValue(), Integer.parseInt(bezirk_node.item(i)
								.getAttributes().getNamedItem("einwohner")
								.getNodeValue()), anzFil, bezirk_node.item(i).getAttributes()
								.getNamedItem("boni").getNodeValue());
				
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
				System.out.println("  AnzFilialen: " + anzFil);
			}		
			System.out.println("AnzFilialen gesamt: " + z);
		} catch (ParserConfigurationException|SAXException|IOException e)
		{
			e.printStackTrace();
		}		
		//temporary test code
				
					tmp_pane.setBounds(0,550,150,30);
					tmp_pane.setBackground(Color.LIGHT_GRAY);
					tmp_pane.setOpaque(true);
					pane.add(tmp_pane);
					
					pane.addMouseMotionListener(new MouseMotionListener()
					{
						
						@Override
						public void mouseMoved(MouseEvent e)
						{
							tmp_pane.setText("("+e.getPoint().x + ": " + e.getPoint().y + " )");
							
						}
						
						@Override
						public void mouseDragged(MouseEvent e)
						{						
							
						}
					});
				//
		
		
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
		frmFoodimperium.setIconImage(Toolkit.getDefaultToolkit().getImage(Window.class.getResource("/Icon/burger-icon.png")));
		frmFoodimperium.setTitle("Fast-Food Empire");
		frmFoodimperium.setBounds(100, 100, 902, 735);
		frmFoodimperium.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Window.class.getResource("/Icon/Finance-Money-icon.png")));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Window.class.getResource("/Icon/Time-And-Date-Clock-icon.png")));
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
		
		JLabel lblNewLabel_3 = new JLabel("Umsatz/Quartal:");
		lblNewLabel_3.setBounds(50, 90, 80, 14);
		layeredPane.add(lblNewLabel_3);
		
		JLabel lblKostenquartal = new JLabel("Kosten/Quartal:");
		lblKostenquartal.setBounds(50, 130, 80, 14);
		layeredPane.add(lblKostenquartal);
		
		JLabel lblGewinnquartal = new JLabel("Gewinn/Quartal:");
		lblGewinnquartal.setBounds(50, 170, 80, 14);
		layeredPane.add(lblGewinnquartal);
		
		JLabel lblJahr = new JLabel("Jahr:");
		lblJahr.setBounds(50, 50, 30, 14);
		layeredPane.add(lblJahr);
		
		JLabel lblQuartal = new JLabel("Quartal:");
		lblQuartal.setBounds(160, 50, 46, 14);
		layeredPane.add(lblQuartal);
		
		textField_31 = new JTextField();
		textField_31.setBounds(90, 47, 40, 20);
		layeredPane.add(textField_31);
		textField_31.setColumns(10);
		
		textField_32 = new JTextField();
		textField_32.setBounds(216, 47, 46, 20);
		layeredPane.add(textField_32);
		textField_32.setColumns(10);
		
		textField_33 = new JTextField();
		textField_33.setBounds(160, 87, 86, 20);
		layeredPane.add(textField_33);
		textField_33.setColumns(10);
		
		textField_34 = new JTextField();
		textField_34.setBounds(160, 127, 86, 20);
		layeredPane.add(textField_34);
		textField_34.setColumns(10);
		
		textField_35 = new JTextField();
		textField_35.setBounds(160, 167, 86, 20);
		layeredPane.add(textField_35);
		textField_35.setColumns(10);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Finanzen", null, layeredPane_1, null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_1.setBounds(0, 0, 871, 622);
		layeredPane_1.add(tabbedPane_1);
		
		JLayeredPane layeredPane_19 = new JLayeredPane();
		tabbedPane_1.addTab("Detailliert", null, layeredPane_19, null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(50, 21, 89, 20);
		layeredPane_19.add(comboBox);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(50, 60, 46, 14);
		layeredPane_19.add(lblName);
		
		JLabel lblTyp = new JLabel("Typ:");
		lblTyp.setBounds(50, 85, 46, 14);
		layeredPane_19.add(lblTyp);
		
		JLabel lblMitarbeiter = new JLabel("Mitarbeiter:");
		lblMitarbeiter.setBounds(50, 110, 63, 14);
		layeredPane_19.add(lblMitarbeiter);
		
		JLabel lblQuartal_1 = new JLabel("Quartal:");
		lblQuartal_1.setBounds(50, 135, 46, 14);
		layeredPane_19.add(lblQuartal_1);
		
		JLabel lblUmsatz = new JLabel("Umsatz:");
		lblUmsatz.setBounds(50, 160, 46, 14);
		layeredPane_19.add(lblUmsatz);
		
		JLabel lblKosten_1 = new JLabel("Kosten:");
		lblKosten_1.setBounds(50, 185, 46, 14);
		layeredPane_19.add(lblKosten_1);
		
		JLabel lblGewinn = new JLabel("Gewinn:");
		lblGewinn.setBounds(50, 210, 46, 14);
		layeredPane_19.add(lblGewinn);
		
		JLabel lblAktuell = new JLabel("Aktuell:");
		lblAktuell.setBounds(181, 32, 46, 14);
		layeredPane_19.add(lblAktuell);
		
		textField_24 = new JTextField();
		textField_24.setBounds(159, 57, 86, 20);
		layeredPane_19.add(textField_24);
		textField_24.setColumns(10);
		
		textField_25 = new JTextField();
		textField_25.setBounds(159, 82, 86, 20);
		layeredPane_19.add(textField_25);
		textField_25.setColumns(10);
		
		textField_26 = new JTextField();
		textField_26.setBounds(159, 107, 86, 20);
		layeredPane_19.add(textField_26);
		textField_26.setColumns(10);
		
		textField_27 = new JTextField();
		textField_27.setBounds(159, 132, 86, 20);
		layeredPane_19.add(textField_27);
		textField_27.setColumns(10);
		
		textField_28 = new JTextField();
		textField_28.setBounds(159, 157, 86, 20);
		layeredPane_19.add(textField_28);
		textField_28.setColumns(10);
		
		textField_29 = new JTextField();
		textField_29.setBounds(159, 182, 86, 20);
		layeredPane_19.add(textField_29);
		textField_29.setColumns(10);
		
		textField_30 = new JTextField();
		textField_30.setBounds(159, 207, 86, 20);
		layeredPane_19.add(textField_30);
		textField_30.setColumns(10);
		
		JLayeredPane layeredPane_6 = new JLayeredPane();
		tabbedPane_1.addTab("Bilanz", null, layeredPane_6, null);
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabbedPane.addTab("Filialen", null, layeredPane_2, null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_2.setBounds(0, 0, 871, 622);
		layeredPane_2.add(tabbedPane_2);
		
		JLayeredPane layeredPane_21 = new JLayeredPane();
		tabbedPane_2.addTab("Immobilien", null, layeredPane_21, null);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(50, 11, 100, 20);
		layeredPane_21.add(comboBox_1);
		
		JLabel lblName_1 = new JLabel("Name:");
		lblName_1.setBounds(50, 42, 46, 14);
		layeredPane_21.add(lblName_1);
		
		JLabel lblTyp_1 = new JLabel("Typ:");
		lblTyp_1.setBounds(50, 67, 46, 14);
		layeredPane_21.add(lblTyp_1);
		
		JLabel lblMitarbeiter_1 = new JLabel("Mitarbeiter:");
		lblMitarbeiter_1.setBounds(50, 92, 66, 14);
		layeredPane_21.add(lblMitarbeiter_1);
		
		JLabel lblQuartal_2 = new JLabel("Quartal:");
		lblQuartal_2.setBounds(50, 117, 46, 14);
		layeredPane_21.add(lblQuartal_2);
		
		JLabel lblFilialenwert = new JLabel("Filialwert:");
		lblFilialenwert.setBounds(50, 142, 66, 14);
		layeredPane_21.add(lblFilialenwert);
		
		JLabel lblNewLabel_4 = new JLabel("Warenverkaufspreis:");
		lblNewLabel_4.setBounds(50, 167, 108, 14);
		layeredPane_21.add(lblNewLabel_4);
		
		JButton btnFilialeVerkaufen = new JButton("Filiale verkaufen");
		btnFilialeVerkaufen.setBounds(50, 210, 120, 23);
		layeredPane_21.add(btnFilialeVerkaufen);
		
		JButton btnndern = new JButton("\u00C4ndern");
		btnndern.setBounds(180, 210, 89, 23);
		layeredPane_21.add(btnndern);
		
		textField_18 = new JTextField();
		textField_18.setBounds(183, 39, 86, 20);
		layeredPane_21.add(textField_18);
		textField_18.setColumns(10);
		
		textField_19 = new JTextField();
		textField_19.setBounds(183, 64, 86, 20);
		layeredPane_21.add(textField_19);
		textField_19.setColumns(10);
		
		textField_20 = new JTextField();
		textField_20.setBounds(183, 89, 86, 20);
		layeredPane_21.add(textField_20);
		textField_20.setColumns(10);
		
		textField_21 = new JTextField();
		textField_21.setBounds(183, 114, 86, 20);
		layeredPane_21.add(textField_21);
		textField_21.setColumns(10);
		
		textField_22 = new JTextField();
		textField_22.setBounds(183, 139, 86, 20);
		layeredPane_21.add(textField_22);
		textField_22.setColumns(10);
		
		textField_23 = new JTextField();
		textField_23.setBounds(180, 164, 86, 20);
		layeredPane_21.add(textField_23);
		textField_23.setColumns(10);
		
		JLayeredPane layeredPane_11 = new JLayeredPane();
		tabbedPane_2.addTab("Map", null, layeredPane_11, null);
		
		buildMap(layeredPane_11); //////////////////////////////////////////////
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Window.class.getResource("/Icon/BerlinKarteStrasse3.png")));
		label_1.setBounds(0, 0, 797, 614);
		layeredPane_11.add(label_1);
		
		JLayeredPane layeredPane_3 = new JLayeredPane();
		tabbedPane.addTab("Marktplatz", null, layeredPane_3, null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_3.setBounds(0, 0, 871, 622);
		layeredPane_3.add(tabbedPane_3);
		
		JLayeredPane layeredPane_8 = new JLayeredPane();
		tabbedPane_3.addTab("Zutaten", null, layeredPane_8, null);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 11, 60, 60);
		layeredPane_8.add(label);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(137, 268, 89, 23);
		layeredPane_8.add(btnReset);
		
		JButton btnKaufen = new JButton("Kaufen");
		btnKaufen.setBounds(236, 268, 89, 23);
		layeredPane_8.add(btnKaufen);
		
		JLabel lblInventar = new JLabel("Inventar:");
		lblInventar.setBounds(109, 57, 46, 14);
		layeredPane_8.add(lblInventar);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setBounds(185, 51, 40, 20);
		layeredPane_8.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAuswahl = new JLabel("Auswahl:");
		lblAuswahl.setBounds(109, 165, 46, 14);
		layeredPane_8.add(lblAuswahl);
		
		JLabel lblG = new JLabel("G1");
		lblG.setBounds(185, 38, 20, 14);
		layeredPane_8.add(lblG);
		
		textField_5 = new JTextField();
		textField_5.setBounds(235, 51, 40, 20);
		layeredPane_8.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblG_1 = new JLabel("G2");
		lblG_1.setBounds(235, 38, 20, 14);
		layeredPane_8.add(lblG_1);
		
		textField_6 = new JTextField();
		textField_6.setBounds(285, 51, 40, 20);
		layeredPane_8.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblG_2 = new JLabel("G3");
		lblG_2.setBounds(285, 38, 20, 14);
		layeredPane_8.add(lblG_2);
		
		JLabel lblBentigt = new JLabel("Ben\u00F6tigt:");
		lblBentigt.setBounds(109, 91, 46, 14);
		layeredPane_8.add(lblBentigt);
		
		textField_7 = new JTextField();
		textField_7.setBounds(185, 88, 40, 20);
		layeredPane_8.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(235, 88, 40, 20);
		layeredPane_8.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(285, 88, 40, 20);
		layeredPane_8.add(textField_9);
		textField_9.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(185, 127, 40, 20);
		layeredPane_8.add(textField_4);
		textField_4.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(235, 127, 40, 20);
		layeredPane_8.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblPreis = new JLabel("Preis/Einheit:");
		lblPreis.setBounds(109, 130, 66, 14);
		layeredPane_8.add(lblPreis);
		
		textField_11 = new JTextField();
		textField_11.setBounds(285, 127, 40, 20);
		layeredPane_8.add(textField_11);
		textField_11.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setBounds(185, 162, 40, 20);
		layeredPane_8.add(textField_12);
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		textField_13.setBounds(235, 162, 40, 20);
		layeredPane_8.add(textField_13);
		textField_13.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(285, 162, 40, 20);
		layeredPane_8.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblKosten = new JLabel("Kosten:");
		lblKosten.setBounds(109, 202, 46, 14);
		layeredPane_8.add(lblKosten);
		
		textField_14 = new JTextField();
		textField_14.setBounds(185, 196, 40, 20);
		layeredPane_8.add(textField_14);
		textField_14.setColumns(10);
		
		textField_15 = new JTextField();
		textField_15.setBounds(235, 196, 40, 20);
		layeredPane_8.add(textField_15);
		textField_15.setColumns(10);
		
		textField_16 = new JTextField();
		textField_16.setBounds(285, 196, 40, 20);
		layeredPane_8.add(textField_16);
		textField_16.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Gesamtkosten:");
		lblNewLabel_2.setBounds(109, 232, 76, 14);
		layeredPane_8.add(lblNewLabel_2);
		
		textField_17 = new JTextField();
		textField_17.setBounds(185, 227, 140, 20);
		layeredPane_8.add(textField_17);
		textField_17.setColumns(10);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(109, 11, 117, 20);
		layeredPane_8.add(comboBox_3);
		
		JLayeredPane layeredPane_5 = new JLayeredPane();
		tabbedPane.addTab("Forschung", null, layeredPane_5, null);
		
		JTabbedPane tabbedPane_5 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_5.setBounds(0, 0, 871, 622);
		layeredPane_5.add(tabbedPane_5);
		
		JLayeredPane layeredPane_10 = new JLayeredPane();
		tabbedPane_5.addTab("Marktforschung", null, layeredPane_10, null);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(50, 30, 100, 20);
		layeredPane_10.add(comboBox_2);
		
		JLabel lblBevlkerung = new JLabel("Bev\u00F6lkerung:");
		lblBevlkerung.setBounds(160, 80, 70, 14);
		layeredPane_10.add(lblBevlkerung);
		
		JLabel lblDner = new JLabel("% D\u00F6ner:");
		lblDner.setBounds(240, 80, 70, 14);
		layeredPane_10.add(lblDner);
		
		JLabel lblPizza = new JLabel("% Pizza");
		lblPizza.setBounds(320, 80, 70, 14);
		layeredPane_10.add(lblPizza);
		
		JLabel lblCurrywurst = new JLabel("% CurryWurst:");
		lblCurrywurst.setBounds(400, 80, 85, 14);
		layeredPane_10.add(lblCurrywurst);
		
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
