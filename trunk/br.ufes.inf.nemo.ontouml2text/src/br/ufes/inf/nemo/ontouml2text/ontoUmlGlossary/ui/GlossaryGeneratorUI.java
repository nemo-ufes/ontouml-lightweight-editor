package br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary.OntoUmlGlossary;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComboBox;

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

	public GlossaryGeneratorUI(OntoUMLParser parser){
		this();		
		this.parser = parser;
	}
	
	/**
	 * Create the frame.
	 */
	public GlossaryGeneratorUI() {
		
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("OntoUML Glossary Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Output File Name");
		
		edtOutputFileName = new JTextField();
		edtOutputFileName.setText("Glossary Output");
		edtOutputFileName.setColumns(10);
		
		JLabel lblOutputDirectory = new JLabel("Output Directory");
		
		edtOutputDirectory = new JTextField();
		edtOutputDirectory.setColumns(10);
		
		btnSelectOutputDirectory = new JButton("...");
		
		chkAnalyseDescriptiveConsistency = new JCheckBox("Analyse Descriptive Consistency");
		chkAnalyseDescriptiveConsistency.setToolTipText("Check if exists any inconsistency that can be affect the generation of glossary");
		
		btnGenerateGlossary = new JButton("Generate Glossary");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		cmbLanguage = new JComboBox<String>();
		cmbLanguage.addItem("Português - BR");
		cmbLanguage.setSelectedIndex(0);
		
		JLabel lblLanguage = new JLabel("Target Language");
		
		edtSubtitle = new JTextField();
		edtSubtitle.setText("Detailed Description of Concepts");
		edtSubtitle.setColumns(10);
		
		JLabel lblSubtitle = new JLabel("Subtitle");
		
		edtTitle = new JTextField();
		edtTitle.setText("Glossary");
		edtTitle.setColumns(10);
		
		lblTitle = new JLabel("Title");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLanguage)
						.addComponent(cmbLanguage, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
						.addComponent(chkAnalyseDescriptiveConsistency)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(82)
							.addComponent(btnGenerateGlossary)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel))
						.addComponent(lblOutputDirectory)
						.addComponent(lblNewLabel)
						.addComponent(lblSubtitle)
						.addComponent(lblTitle)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(edtSubtitle, Alignment.LEADING)
								.addComponent(edtTitle, Alignment.LEADING)
								.addComponent(edtOutputDirectory, Alignment.LEADING)
								.addComponent(edtOutputFileName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSelectOutputDirectory, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addGap(18))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLanguage)
					.addGap(9)
					.addComponent(cmbLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
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
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblOutputDirectory)
					.addGap(1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(edtOutputDirectory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSelectOutputDirectory))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkAnalyseDescriptiveConsistency)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnGenerateGlossary))
					.addGap(24))
		);
		contentPane.setLayout(gl_contentPane);
		
		setLocationRelativeTo(null);
		
		btnSelectOutputDirectory.setActionCommand("outputSelection");
		btnGenerateGlossary.setActionCommand("generateGlossary");
		
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
		
		btnGenerateGlossary.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {

				if(edtOutputFileName.getText().equals("") || edtOutputDirectory.getText().equals(""))
					this.showErrorMessage(this,"There is no output file name or output directory. Please, complete the fields.","Output Name Error",JOptionPane.ERROR_MESSAGE,null);	
				
				else{			
					if(cmbLanguage.getSelectedIndex() == 0){ // Brazilian Portuguese
						ontoUmlGlossary = new OntoUmlGlossary(OntoUmlGlossary.PT_BR, parser, edtOutputFileName.getText(), 
								edtOutputDirectory.getText(), edtTitle.getText(), edtSubtitle.getText()); 
					}
					
					if(chkAnalyseDescriptiveConsistency.isSelected()){
						SwingUtilities.invokeLater(new Runnable() {			
							public void run() {
								GlossaryGeneratorAnalisysUI analisys = new GlossaryGeneratorAnalisysUI(ontoUmlGlossary, 
										ontoUmlGlossary.verifiyMissingUserDescriptions(),
										ontoUmlGlossary.verfifyIsolatedDescriptions(),
										ontoUmlGlossary.verfifyNonDeterminedRelationships());
								analisys.setVisible(true);	
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
