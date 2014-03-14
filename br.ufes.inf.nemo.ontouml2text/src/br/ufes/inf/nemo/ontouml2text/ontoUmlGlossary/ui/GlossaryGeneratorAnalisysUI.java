package br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary.OntoUmlGlossary;

public class GlossaryGeneratorAnalisysUI extends JFrame {

	@SuppressWarnings("unused")
	private final OntoUmlGlossary ontoUmlGlossary;
	private Integer missingDesc;
	
	private static final long serialVersionUID = 1L;
	/**
	 * Create the frame.
	 * @param conceptsWithoutDesc : concepts without user description list
	 */
	public GlossaryGeneratorAnalisysUI(final OntoUmlGlossary ontoUmlGlossary, 
			List<String> conceptsWithoutDesc, List<String> isolatedConcepts, List<String> nonDeterminedRelationships) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GlossaryGeneratorAnalisysUI.class.getResource("/resources/icon/1392761208_rich_text_align_left.png")));
		this.ontoUmlGlossary = ontoUmlGlossary;
		
		setBounds(400, 400, 491, 460);
		this.setLocationRelativeTo(null); 
		setTitle("OntoUML Glossary Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelOne = new JPanel();
		tabbedPane.addTab("General", null, panelOne, null);
		panelOne.setLayout(null);
		
		JPanel surroundPanel1 = new JPanel();
		surroundPanel1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Missing User Description", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		surroundPanel1.setBounds(12, 51, 431, 57);
		panelOne.add(surroundPanel1);
		surroundPanel1.setLayout(null);
		
		
		missingDesc = conceptsWithoutDesc.size();
		JLabel lblMissingUserDescriptionCounter = new JLabel(missingDesc.toString() + " case(s) found.");
		lblMissingUserDescriptionCounter.setBounds(23, 28, 128, 16);
		surroundPanel1.add(lblMissingUserDescriptionCounter);
		
		JButton btnMissingUserDescriptionDetails = new JButton("Details");
		btnMissingUserDescriptionDetails.setIcon(new ImageIcon(GlossaryGeneratorAnalisysUI.class.getResource("/resources/icon/1392762699_find.png")));
		
		btnMissingUserDescriptionDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnMissingUserDescriptionDetails.setBounds(322, 14, 97, 33);
		surroundPanel1.add(btnMissingUserDescriptionDetails);
		
		JPanel surroundPanel2 = new JPanel();
		surroundPanel2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Isolated Concepts", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		surroundPanel2.setBounds(12, 130, 431, 57);
		panelOne.add(surroundPanel2);
		surroundPanel2.setLayout(null);
		
		JLabel lblIsolatedConceptsCounter = new JLabel(isolatedConcepts.size()+" case(s) found.");
		lblIsolatedConceptsCounter.setBounds(21, 30, 128, 16);
		surroundPanel2.add(lblIsolatedConceptsCounter);
		
		JButton btnIsolatedConceptsDetails = new JButton("Details");
		btnIsolatedConceptsDetails.setIcon(new ImageIcon(GlossaryGeneratorAnalisysUI.class.getResource("/resources/icon/1392762699_find.png")));
		
		btnIsolatedConceptsDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnIsolatedConceptsDetails.setBounds(322, 14, 97, 33);
		surroundPanel2.add(btnIsolatedConceptsDetails);
		
		JPanel group3 = new JPanel();
		group3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Concepts With Non-Determined Relationship", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		group3.setBounds(12, 204, 431, 57);
		panelOne.add(group3);
		group3.setLayout(null);
		
		JLabel lblNonDeterminedRelationshipsCounter = new JLabel(nonDeterminedRelationships.size()+" case(s) found.");
		lblNonDeterminedRelationshipsCounter.setBounds(23, 30, 128, 16);
		group3.add(lblNonDeterminedRelationshipsCounter);
		
		JButton btnNonDeterminedRelationshipsDetails = new JButton("Details");
		btnNonDeterminedRelationshipsDetails.setIcon(new ImageIcon(GlossaryGeneratorAnalisysUI.class.getResource("/resources/icon/1392762699_find.png")));
		
		btnNonDeterminedRelationshipsDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnNonDeterminedRelationshipsDetails.setBounds(324, 14, 97, 33);
		group3.add(btnNonDeterminedRelationshipsDetails);
		
		JLabel lblResults = new JLabel("Results of Analisys");
		lblResults.setBounds(12, 13, 147, 16);
		panelOne.add(lblResults);
		
		JButton btnCancel = new JButton("Cancel Generation");
		btnCancel.setIcon(new ImageIcon(GlossaryGeneratorAnalisysUI.class.getResource("/resources/icon/cancel.png")));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnCancel.setBounds(246, 344, 157, 35);
		panelOne.add(btnCancel);
		
		JButton btnContinue = new JButton("Continue Anyway");
		btnContinue.setIcon(new ImageIcon(GlossaryGeneratorAnalisysUI.class.getResource("/resources/icon/1392762597_warning.png")));
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component root = SwingUtilities.getRoot((JButton) arg0.getSource());
				
				try{				
	                root.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	                
					ontoUmlGlossary.modelToText();
					JOptionPane.showMessageDialog(null, "Glossary generated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}finally{
					root.setCursor(Cursor.getDefaultCursor());
				}
			}
		});
		
		btnContinue.setBounds(79, 344, 157, 35);
		panelOne.add(btnContinue);
		
		JPanel panelTwo = new JPanel();
		tabbedPane.addTab("Missing User Descriptions", null, panelTwo, null);
		
		JPanel panelThree = new JPanel();
		tabbedPane.addTab("Isolated Concepts", null, panelThree, null);
		
		JPanel panelFour = new JPanel();
		tabbedPane.addTab("Non-Determined Relationships", null, panelFour, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Objects with non-determined relationships", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(23, 29, 404, 307);
		panel_1.add(scrollPane_2);
		
		DefaultListModel<String> nonDeterminedRelationshipsList = new DefaultListModel<String>();
		int i = 0;
		
		for(String s : nonDeterminedRelationships){
			nonDeterminedRelationshipsList.add(i, s);
			i++;
		}
		
		JList<String> lstNonDeterminedRelationships = new JList<String>(nonDeterminedRelationshipsList);
		scrollPane_2.setViewportView(lstNonDeterminedRelationships);
		GroupLayout gl_panelFour = new GroupLayout(panelFour);
		gl_panelFour.setHorizontalGroup(
			gl_panelFour.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFour.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelFour.setVerticalGroup(
			gl_panelFour.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelFour.createSequentialGroup()
					.addContainerGap(20, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelFour.setLayout(gl_panelFour);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Objects without relationships", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panelThree = new GroupLayout(panelThree);
		gl_panelThree.setHorizontalGroup(
			gl_panelThree.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelThree.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelThree.setVerticalGroup(
			gl_panelThree.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelThree.createSequentialGroup()
					.addContainerGap(20, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		DefaultListModel<String> isolatedConceptsList = new DefaultListModel<String>();
		i = 0;
		
		for(String s : isolatedConcepts){
			isolatedConceptsList.add(i, s);
			i++;
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 29, 404, 307);
		panel_2.add(scrollPane_1);
		
		JList<String> lstIsolatedConcepts = new JList<String>(isolatedConceptsList);
		scrollPane_1.setViewportView(lstIsolatedConcepts);
		lstIsolatedConcepts.setBackground(Color.WHITE);
		panelThree.setLayout(gl_panelThree);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Objects without User Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 32, 404, 307);
		panel.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(24, 11, 249, 16);
		panel.add(lblNewLabel);
		
		DefaultListModel<String> modelList = new DefaultListModel<String>();
		i = 0;

		for(String s : conceptsWithoutDesc){
			modelList.add(i, s);
			i++;
		}
		
		JList<String> lstMissingUserDescription = new JList<String>(modelList);
		scrollPane.setViewportView(lstMissingUserDescription);
		lstMissingUserDescription.setBackground(UIManager.getColor("Button.disabledShadow"));
		
		GroupLayout gl_panelTwo = new GroupLayout(panelTwo);
		gl_panelTwo.setHorizontalGroup(
			gl_panelTwo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTwo.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_panelTwo.setVerticalGroup(
			gl_panelTwo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTwo.createSequentialGroup()
					.addGap(20)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelTwo.setLayout(gl_panelTwo);
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
