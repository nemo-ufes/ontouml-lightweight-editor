package br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JScrollPane;


public class GlossaryGeneratorAnalisysUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	//private DefaultListModel<String> listModel = new DefaultListModel<String>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					GlossaryGeneratorAnalisysUI frame = new GlossaryGeneratorAnalisysUI();
					System.out.println("Criei uma GlossaryGeneratorAnalisysUI");
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GlossaryGeneratorAnalisysUI() {
		setBounds(100, 100, 491, 435);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelOne = new JPanel();
		tabbedPane.addTab("General", null, panelOne, null);
		panelOne.setLayout(null);
		
		JPanel surroundPanel1 = new JPanel();
		surroundPanel1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Missing User Description", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		surroundPanel1.setBounds(12, 51, 431, 57);
		panelOne.add(surroundPanel1);
		surroundPanel1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(12, 28, 56, 16);
		surroundPanel1.add(lblNewLabel_2);
		
		JPanel surroundPanel2 = new JPanel();
		surroundPanel2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Undefined Direction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		surroundPanel2.setBounds(12, 130, 431, 57);
		panelOne.add(surroundPanel2);
		surroundPanel2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(12, 28, 56, 16);
		surroundPanel2.add(lblNewLabel_1);
		
		JPanel surroundPanel3 = new JPanel();
		surroundPanel3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Undefined Gender", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		surroundPanel3.setBounds(12, 204, 431, 57);
		panelOne.add(surroundPanel3);
		surroundPanel3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(12, 28, 56, 16);
		surroundPanel3.add(lblNewLabel_3);
		
		JPanel surroundPanel4 = new JPanel();
		surroundPanel4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Undefined Plural", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		surroundPanel4.setBounds(12, 284, 431, 57);
		panelOne.add(surroundPanel4);
		surroundPanel4.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(12, 28, 56, 16);
		surroundPanel4.add(lblNewLabel_4);
		
		JLabel lblResults = new JLabel("Results of Analisys");
		lblResults.setBounds(12, 13, 147, 16);
		panelOne.add(lblResults);
		
		JPanel panelTwo = new JPanel();
		tabbedPane.addTab("Missing User Descriptions", null, panelTwo, null);
		panelTwo.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Objects without User Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 20, 452, 338);
		panelTwo.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(6, 18, 249, 16);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 47, 147, 224);
		panel.add(scrollPane);
		
		/*JList <String> list = new JList<String>(listModel);
		scrollPane.setViewportView(list);
		
		ArrayList<String> a = new ArrayList<String>();
		a.add("Ariane");

		listModel.add(0,a.get(0));*/

	}
}
