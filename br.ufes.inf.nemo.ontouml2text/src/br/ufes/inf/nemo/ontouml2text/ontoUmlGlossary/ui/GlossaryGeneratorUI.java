package br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary.ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary.OntoUmlGlossary;

public class GlossaryGeneratorUI extends JFrame {
	
	private OntoUmlGlossary ontoUmlGlossary;
	private OntoUMLParser parser;
	private JPanel contentPane;
	private JComboBox<String> cmbLanguage;
	private JTextField edtOutputFileName;
	private JTextField edtOutputDirectory;
	private JCheckBox chkAnalyseDescriptiveConsistency;
	private JButton btnSelectOutputDirectory;
	private JButton btnGenerateGlossary;
	private static final long serialVersionUID = 1L;
	
	private boolean doGlossaryGeneration;
	private JButton btnCancel;
	private JTextField edtSubtitle;
	private JTextField edtTitle;
	private JLabel lblTitle;
	private JCheckBox chkInheritMediations;

	public GlossaryGeneratorUI(OntoUMLParser parser){
		this();		
		this.parser = parser;
	}
	
	/**
	 * Create the frame.
	 */
	public GlossaryGeneratorUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GlossaryGeneratorUI.class.getResource("/resources/icon/glossary.png")));
		setPreferredSize(new Dimension(390, 387));
		setSize(new Dimension(390, 387));
		
		//setType(Type.UTILITY);
		setTitle("Glossary of Terms");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("HTML File Name:");
		
		edtOutputFileName = new JTextField();
		edtOutputFileName.setText("Glossary");
		edtOutputFileName.setColumns(10);
		
		JLabel lblOutputDirectory = new JLabel("Output Folder:");
		
		edtOutputDirectory = new JTextField();
		edtOutputDirectory.setColumns(10);
		
		btnSelectOutputDirectory = new JButton("...");
		
		chkAnalyseDescriptiveConsistency = new JCheckBox("Analyse Descriptive Consistency");
		chkAnalyseDescriptiveConsistency.setToolTipText("Check if exists any inconsistency that can be affect the generation of glossary");
		
		cmbLanguage = new JComboBox<String>();
		cmbLanguage.setFocusable(false);
		cmbLanguage.addItem("Portuguese - BR");
		cmbLanguage.setSelectedIndex(0);
		
		JLabel lblLanguage = new JLabel("Target Language:");
		
		edtSubtitle = new JTextField();
		edtSubtitle.setText("Detailed Description of Concepts");
		edtSubtitle.setColumns(10);
		
		JLabel lblSubtitle = new JLabel("Subtitle:");
		
		edtTitle = new JTextField();
		edtTitle.setText("Glossary");
		edtTitle.setColumns(10);
		
		lblTitle = new JLabel("Title:");
		
		JPanel panel = new JPanel();
		
		chkInheritMediations = new JCheckBox("Inherit Mediations of Parent Relators ");
		chkInheritMediations.setSelected(true);
		chkInheritMediations.setToolTipText("Check if exists any inconsistency that can be affect the generation of glossary");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblOutputDirectory, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
								.addComponent(chkAnalyseDescriptiveConsistency, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLanguage, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSubtitle, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
								.addComponent(edtOutputFileName, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(edtOutputDirectory, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSelectOutputDirectory, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
								.addComponent(edtSubtitle, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
								.addComponent(chkInheritMediations, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(edtTitle, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbLanguage, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLanguage)
					.addGap(9)
					.addComponent(cmbLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(edtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(lblSubtitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(edtSubtitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(edtOutputFileName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblOutputDirectory)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(edtOutputDirectory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSelectOutputDirectory))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chkAnalyseDescriptiveConsistency)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chkInheritMediations)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		btnGenerateGlossary = new JButton("Generate");
		//btnGenerateGlossary.setIcon(new ImageIcon(GlossaryGeneratorUI.class.getResource("/resources/icon/1392761208_rich_text_align_left.png")));
		btnGenerateGlossary.setActionCommand("generateGlossary");
		
		btnCancel = new JButton("Cancel");
		//btnCancel.setIcon(new ImageIcon(GlossaryGeneratorUI.class.getResource("/resources/icon/cancel.png")));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(btnGenerateGlossary);
		panel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnGenerateGlossary.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {

				if(edtOutputFileName.getText().equals("") || edtOutputDirectory.getText().equals(""))
					this.showErrorMessage(this,"There is no output file name or output directory. Please, complete the fields.","Output Name Error",JOptionPane.ERROR_MESSAGE,null);	
				
				else{			
					if(cmbLanguage.getSelectedIndex() == 0){ // Brazilian Portuguese
						ontoUmlGlossary = new OntoUmlGlossary(OntoUmlGlossary.PT_BR, parser, edtOutputFileName.getText(), 
								edtOutputDirectory.getText(), edtTitle.getText(), edtSubtitle.getText(), chkInheritMediations.isSelected()); 
					}
					
					if(chkAnalyseDescriptiveConsistency.isSelected()){
						SwingUtilities.invokeLater(new Runnable() {			
							public void run() {
								List<String> missingUserDescriptions = ontoUmlGlossary.verifiyMissingUserDescriptions();
								List<String> isolatedDescriptions = ontoUmlGlossary.verfifyIsolatedDescriptions();
								List<String> nonDeterminedRelationships = ontoUmlGlossary.verfifyNonDeterminedRelationships();
								
								if(missingUserDescriptions.size() + isolatedDescriptions.size() + nonDeterminedRelationships.size() > 0) {
									GlossaryGeneratorAnalisysUI analisys = new GlossaryGeneratorAnalisysUI(ontoUmlGlossary, 
											missingUserDescriptions,
											isolatedDescriptions,
											nonDeterminedRelationships);
									analisys.setVisible(true);	
								}else{
									ontoUmlGlossary.modelToText();
									JOptionPane.showMessageDialog(null, "Glossary generated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
									dispose();
								}
							}
						});		
					}else{
						Component root = SwingUtilities.getRoot((JButton) e.getSource());
						
						try{				
			                root.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							
							ontoUmlGlossary.modelToText();
							JOptionPane.showMessageDialog(null, "Glossary generated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}finally{
							root.setCursor(Cursor.getDefaultCursor());
						}
					}
				}
			}

			private void showErrorMessage(ActionListener actionListener,
					String message, String title, int errorMessage,
					Object object) {
				JOptionPane.showMessageDialog(contentPane, message, title, 0);
				
			}
			
		});
		contentPane.setLayout(gl_contentPane);
		
		setLocationRelativeTo(null);
		
		btnSelectOutputDirectory.setActionCommand("outputSelection");
		
		btnSelectOutputDirectory.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				  JFileChooser chooser = new JFileChooser();
			      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	
			      try {
			            int code = chooser.showOpenDialog(contentPane);
			            if (code == JFileChooser.APPROVE_OPTION) {
			               File selectedFile = chooser.getSelectedFile();
			               edtOutputDirectory.setText(selectedFile.getAbsolutePath());
			            }
			      } catch (Exception f) {
			         f.printStackTrace();
			      }	
			}
			
		});
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	public String getOutputDirectory(){
		return edtOutputDirectory.getText();
	}
	
	public boolean doDescriptiveConsistency(){
		return chkAnalyseDescriptiveConsistency.isSelected();
	}
	
	public boolean doGlossaryGeneration(){
		return doGlossaryGeneration;
	}
}
