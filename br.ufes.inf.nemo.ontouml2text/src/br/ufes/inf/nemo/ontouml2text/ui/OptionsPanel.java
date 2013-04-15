package br.ufes.inf.nemo.ontouml2text.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class OptionsPanel extends JPanel implements ActionListener, ItemListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2698550872201019269L;
	
//	private JTextField filePath;
	private JCheckBox definitionCheckBox, genCheckBox, specCheckBox, attCheckBox, assocCheckBox;
//	private JCheckBox chckbxGenerateModelDescription;
//	private JCheckBox chckbxExportModelDescription;
//	private JButton selectPathBtn;
//	private JTextField textField;

	/**
	 * Constructor
	 */
	public OptionsPanel()
	{
		initGUI();
	}

	/**
	 * Create the dialog.
	 */
	public void initGUI() {
		setPreferredSize(new Dimension(500, 350));
//		{
//			filePath = new JTextField();
//			filePath.setAlignmentY(Component.TOP_ALIGNMENT);
//			filePath.setAlignmentX(Component.LEFT_ALIGNMENT);
//			filePath.setColumns(10);
//					
//			selectPathBtn = new JButton("Select Path");
//			selectPathBtn.addActionListener(this);
//		}
		{
			definitionCheckBox = new JCheckBox("Include Classes Definitions");
			definitionCheckBox.addItemListener(this);
		}
		{
			genCheckBox = new JCheckBox("Include Generalizations");
			genCheckBox.setSelected(true);
			genCheckBox.addItemListener(this);
		}
		{
			specCheckBox = new JCheckBox("Include Specializations");
			specCheckBox.setSelected(true);
			specCheckBox.addItemListener(this);
		}
		{
			attCheckBox = new JCheckBox("Include Attributes");
			attCheckBox.setSelected(true);
			attCheckBox.addItemListener(this);
		}
		{
			assocCheckBox = new JCheckBox("Include Associations");
			assocCheckBox.setSelected(true);
			assocCheckBox.addItemListener(this);
		}
		
//		chckbxGenerateModelDescription = new JCheckBox("Generate model description for the selected file");
//		
//		chckbxExportModelDescription = new JCheckBox("Export model description to file");
//		
//		textField = new JTextField();
//		textField.setColumns(10);
//		
//		JButton btnNewButton = new JButton("Select Path");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//			}
//		});
				
		GroupLayout gl_mainOptions = new GroupLayout(this);
		gl_mainOptions.setHorizontalGroup(
			gl_mainOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainOptions.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_mainOptions.createParallelGroup(Alignment.LEADING)
						.addComponent(definitionCheckBox)
						.addComponent(genCheckBox, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
						.addComponent(specCheckBox, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addComponent(attCheckBox, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addComponent(assocCheckBox))
					.addContainerGap(337, Short.MAX_VALUE))
				.addGroup(gl_mainOptions.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mainOptions.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mainOptions.createSequentialGroup()
							//.addComponent(chckbxGenerateModelDescription)
							.addContainerGap())
						.addGroup(gl_mainOptions.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_mainOptions.createSequentialGroup()
								//.addComponent(chckbxExportModelDescription)
								.addContainerGap())
							.addGroup(gl_mainOptions.createSequentialGroup()
								.addGroup(gl_mainOptions.createParallelGroup(Alignment.TRAILING))
									//.addComponent(textField, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
									//.addComponent(filePath, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
								.addGap(16)
								.addGroup(gl_mainOptions.createParallelGroup(Alignment.LEADING))
									//.addComponent(selectPathBtn, Alignment.TRAILING)
									//.addComponent(btnNewButton, Alignment.TRAILING))
								.addGap(18)))))
		);
		gl_mainOptions.setVerticalGroup(
			gl_mainOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainOptions.createSequentialGroup()
					.addGap(8)
					//.addComponent(chckbxGenerateModelDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_mainOptions.createParallelGroup(Alignment.BASELINE))
						//.addComponent(filePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						//.addComponent(selectPathBtn))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					//.addComponent(chckbxExportModelDescription)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_mainOptions.createParallelGroup(Alignment.BASELINE))
						//.addComponent(btnNewButton)
						//.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addComponent(definitionCheckBox)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(genCheckBox)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(specCheckBox)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(attCheckBox)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(assocCheckBox)
					.addGap(38))
		);
		
		setLayout(gl_mainOptions);
	}

	@Override
	public void actionPerformed(ActionEvent a)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select File to Export");
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent i) {
		
	}
	
	public boolean[] getOptionsList()
	{
		boolean[] optionsList = { definitionCheckBox.isSelected(), genCheckBox.isSelected(), 
		specCheckBox.isSelected(), attCheckBox.isSelected(), assocCheckBox.isSelected() };
		
		return optionsList;
	}
}
