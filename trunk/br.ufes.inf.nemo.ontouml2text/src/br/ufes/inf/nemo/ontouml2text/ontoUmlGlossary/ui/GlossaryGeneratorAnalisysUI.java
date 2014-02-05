package br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary.ui;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JList;


public class GlossaryGeneratorAnalisysUI extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the frame.
	 * @param conceptsWithoutDesc 
	 */
	public GlossaryGeneratorAnalisysUI(DefaultListModel<String> conceptsWithoutDesc) {
		setBounds(400, 400, 491, 460);
		this.setLocationRelativeTo(null); 
		setTitle("OntoUML Glossary Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
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
		
		JPanel surroundPanel2 = new JPanel();
		surroundPanel2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Undefined Direction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		surroundPanel2.setBounds(12, 130, 431, 57);
		panelOne.add(surroundPanel2);
		surroundPanel2.setLayout(null);
		
		JPanel surroundPanel3 = new JPanel();
		surroundPanel3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Undefined Gender", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		surroundPanel3.setBounds(12, 204, 431, 57);
		panelOne.add(surroundPanel3);
		surroundPanel3.setLayout(null);
		
		JPanel surroundPanel4 = new JPanel();
		surroundPanel4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Undefined Plural", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		surroundPanel4.setBounds(12, 284, 431, 57);
		panelOne.add(surroundPanel4);
		surroundPanel4.setLayout(null);
		
		JLabel lblResults = new JLabel("Results of Analisys");
		lblResults.setBounds(12, 13, 147, 16);
		panelOne.add(lblResults);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnOk.setBounds(183, 355, 97, 25);
		panelOne.add(btnOk);
		
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
		scrollPane.setBounds(26, 68, 178, 234);
		panel.add(scrollPane);
			
		JList<String> list = new JList<String>(conceptsWithoutDesc);		
		list.setBackground(UIManager.getColor("Button.disabledShadow"));
		list.setBounds(31, 47, 144, 234);
		//panel.add(list);	
		
		scrollPane.setViewportView(list);
	}
}
