package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2ecore.OntoUML2Ecore;
import br.ufes.inf.nemo.ontouml2ecore.OntoUML2EcoreOption;

public class EcoreSettingDialog extends JDialog {

	private static final long serialVersionUID = -5085660043989206713L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField pathField;
	private JButton okButton;
	private JButton cancelButton;
	private JButton btnBrowse;
	private JFileChooser fileChooser = new JFileChooser();	
	private JCheckBox cbxIgnorePackage;	
	private JCheckBox cbxIgnoreDerivation; 
	private JLabel lblStatus;
	
	/**
	 * Launch the application.
	 */
	public static void open(JFrame parent, boolean modal, OntoUMLParser refparser) 
	{
		try {			
			EcoreSettingDialog dialog = new EcoreSettingDialog(parent,modal,refparser);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(parent);			
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EcoreSettingDialog(final JFrame parent, boolean modal, final OntoUMLParser refparser) 
	{
		super(parent,modal);
		
		setTitle("Transformation -> Ecore");
		setBounds(100, 100, 450, 302);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		
		JTextPane txtpnThisIsA = new JTextPane();
		txtpnThisIsA.setBackground(UIManager.getColor("Panel.background"));
		txtpnThisIsA.setText("This transformation to Ecore ignores all the stereotypes of classes and relationships of OntoUML. Check the options you want to enforce and then press the button to transform your OntoUML model into Ecore.");
		
		cbxIgnorePackage = new JCheckBox("Ignore package hierarchy");		
		cbxIgnoreDerivation = new JCheckBox("Ignore derivation relationships");
		
		pathField = new JTextField();
		pathField.setColumns(10);
		
		String userhome = System.getProperty("user.home");
		pathField.setText(userhome+File.separator+"Model.ecore");
		
		btnBrowse = new JButton("...");
		btnBrowse.addActionListener(new ActionListener() {					
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Ecore Model (*.ecore)", "ecore");
				fileChooser.setDialogTitle("Transformation -> Ecore -> Browse");
				fileChooser.addChoosableFileFilter(filter);
				fileChooser.setFileFilter(filter);
				fileChooser.setSelectedFile(new File("Model.uml"));
				fileChooser.setAcceptAllFileFilterUsed(false);
				if (fileChooser.showDialog(EcoreSettingDialog.this,"OK") == JFileChooser.APPROVE_OPTION) {
					try {						
						File file = fileChooser.getSelectedFile();				
						if (!file.exists()) {
							if(!file.getName().endsWith(".ecore")) {
								file = new File(file.getCanonicalFile() + ".ecore");
							}else{
								file = new File(file.getCanonicalFile()+"");
							}
						}					
						pathField.setText(file.getAbsolutePath());
					} catch (Exception ex) {						
						ex.printStackTrace();
					}
				}				
			}
		});	
				
		lblStatus = new JLabel();
						
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(pathField, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnBrowse, 0, 0, Short.MAX_VALUE))
							.addComponent(cbxIgnoreDerivation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(cbxIgnorePackage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtpnThisIsA, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnThisIsA, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxIgnorePackage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxIgnoreDerivation)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pathField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowse))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setPreferredSize(new Dimension(10, 50));
			getContentPane().add(buttonPane, BorderLayout.CENTER);
			{
				okButton = new JButton("Transform");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						parent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						Date date = new Date();
						try{
							OntoUML2Ecore.convertToEcore(refparser, pathField.getText(), new OntoUML2EcoreOption(cbxIgnorePackage.isSelected(), cbxIgnoreDerivation.isSelected()));
							lblStatus.setText("Succeesfully generated at "+dateFormat.format(date));
							lblStatus.setForeground(new Color(34, 139, 34));
						}catch(Exception e){
							lblStatus.setText("An error occurred ("+dateFormat.format(date)+")");
							lblStatus.setForeground(new Color(220, 20, 60));
							e.printStackTrace();
						}
						parent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				});	
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dispose();						
					}
				});				
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(145)
						.addComponent(okButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addGap(123))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(okButton)
							.addComponent(cancelButton))
						.addContainerGap(16, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}
}
