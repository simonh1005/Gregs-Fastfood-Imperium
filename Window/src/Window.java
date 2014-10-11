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
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

  
public class Window {

	private JFrame frmFoodimperium;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
	public Window() {
		initialize();
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
		
		JButton button = new JButton("");
		button.setForeground(Color.LIGHT_GRAY);
		button.setBounds(235, 126, 10, 10);
		layeredPane_11.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(317, 114, 10, 10);
		layeredPane_11.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setBounds(308, 188, 10, 10);
		layeredPane_11.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.setBounds(221, 202, 10, 10);
		layeredPane_11.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.setBounds(203, 161, 10, 10);
		layeredPane_11.add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.setBounds(261, 78, 10, 10);
		layeredPane_11.add(button_5);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Window.class.getResource("/Icon/BerlinKarteStrasse3.png")));
		label_1.setBounds(0, 0, 797, 614);
		layeredPane_11.add(label_1);
		
		JLayeredPane layeredPane_3 = new JLayeredPane();
		tabbedPane.addTab("Marktplatz", null, layeredPane_3, null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_3.setBounds(0, 0, 993, 552);
		layeredPane_3.add(tabbedPane_3);
		
		JLayeredPane layeredPane_20 = new JLayeredPane();
		tabbedPane_3.addTab("Gesamt", null, layeredPane_20, null);
		
		JLayeredPane layeredPane_8 = new JLayeredPane();
		tabbedPane_3.addTab("Gem\u00FCse", null, layeredPane_8, null);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 11, 60, 60);
		layeredPane_8.add(label);
		
		JButton btnKaufen = new JButton("Kaufen");
		btnKaufen.setBounds(109, 120, 89, 23);
		layeredPane_8.add(btnKaufen);
		
		JButton btnVerkaufen = new JButton("Verkaufen");
		btnVerkaufen.setBounds(218, 120, 89, 23);
		layeredPane_8.add(btnVerkaufen);
		
		JLabel lblGemse = new JLabel("Gem\u00FCse");
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
		
		JLabel lblGteklasse = new JLabel("G\u00FCteklasse:");
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
		
		textField_4 = new JTextField();
		textField_4.setBounds(185, 58, 66, 20);
		layeredPane_8.add(textField_4);
		textField_4.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		layeredPane_8.setLayer(comboBox, 0);
		comboBox.setBounds(251, 58, 28, 20);
		layeredPane_8.add(comboBox);
		
		JLayeredPane layeredPane_12 = new JLayeredPane();
		tabbedPane_3.addTab("Fleisch", null, layeredPane_12, null);
		
		JLayeredPane layeredPane_13 = new JLayeredPane();
		tabbedPane_3.addTab("Teigwaren", null, layeredPane_13, null);
		
		JLayeredPane layeredPane_14 = new JLayeredPane();
		tabbedPane_3.addTab("Sonstige", null, layeredPane_14, null);
		
		JLayeredPane layeredPane_4 = new JLayeredPane();
		tabbedPane.addTab("Marketing", null, layeredPane_4, null);
		
		JTabbedPane tabbedPane_4 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_4.setBounds(0, 0, 993, 552);
		layeredPane_4.add(tabbedPane_4);
		
		JLayeredPane layeredPane_9 = new JLayeredPane();
		tabbedPane_4.addTab("Werbung", null, layeredPane_9, null);
		
		JLayeredPane layeredPane_15 = new JLayeredPane();
		tabbedPane_4.addTab("Sponsoring", null, layeredPane_15, null);
		
		JLayeredPane layeredPane_5 = new JLayeredPane();
		tabbedPane.addTab("Forschung", null, layeredPane_5, null);
		
		JTabbedPane tabbedPane_5 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_5.setBounds(0, 0, 993, 552);
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
