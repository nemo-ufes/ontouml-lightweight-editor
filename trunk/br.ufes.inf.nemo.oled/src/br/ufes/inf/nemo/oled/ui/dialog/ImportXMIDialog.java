package br.ufes.inf.nemo.oled.ui.dialog;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;

import RefOntoUML.Model;
import br.ufes.inf.nemo.oled.ui.DiagramManager;
import br.ufes.inf.nemo.xmi2ontouml.Creator;
import br.ufes.inf.nemo.xmi2ontouml.framework.XMI2RefConstraint;
import br.ufes.inf.nemo.xmi2ontouml.util.ChckBoxTreeNodeElem;
import br.ufes.inf.nemo.xmi2ontouml.util.RefOntoUMLUtil;
import javax.swing.Box;
import java.awt.Component;

public class ImportXMIDialog extends JDialog implements ActionListener, TreeSelectionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2093867102692070258L;
	private JTextField filePathField;
	private JButton browseBtn, btnUseDefaultOptions, btnImport;
	private JCheckBox chckbxImportConstraints, chckbxIgnoreUnknownStereotypes, 
		chckbxCreateDefaultClassassociation, chckbxGenerateAEndsNames,
		chckbxGenerateAssocNames, chckbxShowWarningLog;
	private JTabbedPane mainTabbedPane;
	private JScrollPane treeScrollPane;
	private CheckboxTree[] trees;
	private JRadioButton rdbtnFilterModelBy, rdbtnFilterModelBy_1;
	private JTextArea objDescription;
	
	DiagramManager diagManager;
	
	
	public ImportXMIDialog(JFrame owner, boolean modal, DiagramManager diagManager)
	{
		super(owner, modal);
		
		this.diagManager = diagManager;
		
		initGUI();
		setLocationRelativeTo(owner);
		setVisible(true);
	}
	
	private void initGUI()
	{
		setTitle("Import from XMI");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//		setPreferredSize(new Dimension(600, 500));
		setBounds(new Rectangle(0, 0, 600, 500));
		{
			mainTabbedPane = new JTabbedPane();
			JPanel treePanel = new JPanel();
			JPanel optionsPanel = new JPanel();
			mainTabbedPane.addTab("Options", optionsPanel);
			mainTabbedPane.addTab("Trees", treePanel);
			getContentPane().add(mainTabbedPane, BorderLayout.CENTER);
			
			{
				JLabel lblFilePath = new JLabel("File Path");
				filePathField = new JTextField();
				filePathField.setColumns(60);
				filePathField.setEditable(false);
				browseBtn = new JButton("Browse");
				browseBtn.addActionListener(this);
				
				JLabel lblConstraints = new JLabel("Constraints");
				chckbxImportConstraints = new JCheckBox("Import Constraints");
				chckbxImportConstraints.setSelected(true);
				
				JLabel lblElements = new JLabel("Elements");
				chckbxIgnoreUnknownStereotypes = new JCheckBox("Ignore Unknown Stereotypes");
				chckbxIgnoreUnknownStereotypes.addActionListener(this);
				chckbxCreateDefaultClassassociation = new JCheckBox("Create default Class/Association elements for unknown stereotypes");
				chckbxCreateDefaultClassassociation.setSelected(true);
				chckbxCreateDefaultClassassociation.addActionListener(this);
				
				JLabel lblAutomation = new JLabel("Automation");
				chckbxGenerateAssocNames = new JCheckBox("Auto generate names for unnamed Association Ends");
				chckbxGenerateAEndsNames = new JCheckBox("Auto generate names for unnamed Associations");
				
				JLabel lblLogs = new JLabel("Logs");
				chckbxShowWarningLog = new JCheckBox("Show Warnings Log");
				chckbxShowWarningLog.setSelected(true);
				
				JSeparator separator = new JSeparator();
				JSeparator separator_1 = new JSeparator();
				JSeparator separator_2 = new JSeparator();
				JSeparator separator_3 = new JSeparator();
				btnUseDefaultOptions = new JButton("Use Default Options");
				btnUseDefaultOptions.addActionListener(this);
				
				GroupLayout gl_optionsPanel = new GroupLayout(optionsPanel);
				gl_optionsPanel.setHorizontalGroup(
					gl_optionsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_optionsPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_optionsPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxGenerateAssocNames)
								.addComponent(chckbxGenerateAEndsNames)
								.addComponent(chckbxCreateDefaultClassassociation)
								.addComponent(chckbxIgnoreUnknownStereotypes)
								.addComponent(chckbxImportConstraints)
								.addGroup(gl_optionsPanel.createSequentialGroup()
									.addComponent(filePathField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(browseBtn))
								.addComponent(lblFilePath)
								.addGroup(gl_optionsPanel.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_optionsPanel.createSequentialGroup()
										.addComponent(lblConstraints)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_optionsPanel.createSequentialGroup()
										.addComponent(lblAutomation)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_optionsPanel.createSequentialGroup()
										.addComponent(lblElements)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 508, GroupLayout.PREFERRED_SIZE))
									.addComponent(chckbxShowWarningLog)
									.addGroup(gl_optionsPanel.createSequentialGroup()
										.addComponent(lblLogs)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(separator_3, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)))
								.addComponent(btnUseDefaultOptions))
							.addContainerGap(4, Short.MAX_VALUE))
				);
				gl_optionsPanel.setVerticalGroup(
					gl_optionsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_optionsPanel.createSequentialGroup()
							.addGap(9)
							.addComponent(lblFilePath)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_optionsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(filePathField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(browseBtn))
							.addGap(18)
							.addGroup(gl_optionsPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblConstraints)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxImportConstraints)
							.addGap(18)
							.addGroup(gl_optionsPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblElements)
								.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxIgnoreUnknownStereotypes)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxCreateDefaultClassassociation)
							.addGap(18)
							.addGroup(gl_optionsPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAutomation)
								.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxGenerateAEndsNames)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxGenerateAssocNames)
							.addGap(18)
							.addGroup(gl_optionsPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblLogs)
								.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxShowWarningLog)
							.addGap(18)
							.addComponent(btnUseDefaultOptions)
							.addContainerGap(147, Short.MAX_VALUE))
				);
				optionsPanel.setLayout(gl_optionsPanel);
			}
			
			{
				treePanel.setLayout(new BorderLayout(0, 0));
				
				rdbtnFilterModelBy = new JRadioButton("Show package structure");
				rdbtnFilterModelBy.addActionListener(this);
				rdbtnFilterModelBy.setEnabled(false);
				
				rdbtnFilterModelBy_1 = new JRadioButton("Show diagrams");
				rdbtnFilterModelBy_1.addActionListener(this);
				rdbtnFilterModelBy_1.setEnabled(false);
				
				Box horizontalBox = Box.createHorizontalBox();
				
				Component horizontalGlue = Box.createHorizontalGlue();
				horizontalBox.add(horizontalGlue);
				horizontalBox.add(rdbtnFilterModelBy);
				horizontalBox.add(rdbtnFilterModelBy_1);
				treePanel.add(horizontalBox, BorderLayout.NORTH);
				
				Component horizontalGlue_1 = Box.createHorizontalGlue();
				horizontalBox.add(horizontalGlue_1);
				
				treeScrollPane = new JScrollPane();
				treeScrollPane.setPreferredSize(new Dimension(400, 300));
				treeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				treePanel.add(treeScrollPane, BorderLayout.CENTER);
				
				objDescription = new JTextArea();
				objDescription.setSize(new Dimension(200,30));
				objDescription.setOpaque(false);
				objDescription.setEditable(false);
				treePanel.add(objDescription, BorderLayout.SOUTH);
			}
			
			Box importBox = Box.createHorizontalBox();
			importBox.setPreferredSize(new Dimension(100,30));
			Component horizontalGlue = Box.createHorizontalGlue();
			importBox.add(horizontalGlue);
			
			btnImport = new JButton("Parse XMI");
//			btnImport.setPreferredSize(new Dimension(81, 30));
			btnImport.addActionListener(this);
			importBox.add(btnImport);
			
			getContentPane().add(importBox, BorderLayout.SOUTH);
			
			Component horizontalGlue_1 = Box.createHorizontalGlue();
			importBox.add(horizontalGlue_1);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == browseBtn)
		{
			// TODO tratar quando se dá browser em outro arquivo sendo que já tem um carregado
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Import XMI");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("XMI, XML (*.xmi, *.xml)", "xmi", "xml");
			fileChooser.addChoosableFileFilter(filter);
			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				if (fileChooser.getFileFilter() == filter)
				{
					File file = fileChooser.getSelectedFile();
					filePathField.setText(file.getAbsolutePath());
					trees = null;
					btnImport.setText("Parse XMI");
				}
			}
		}
		else if (e.getSource() == btnUseDefaultOptions)
		{
			chckbxImportConstraints.setSelected(true);
			chckbxIgnoreUnknownStereotypes.setSelected(false);
			chckbxCreateDefaultClassassociation.setSelected(true);
			chckbxGenerateAssocNames.setSelected(false);
			chckbxGenerateAEndsNames.setSelected(false);
			chckbxShowWarningLog.setSelected(true);
		}
		else if (e.getSource() == btnImport)
		{
			if (filePathField.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(this, "Please select a file");
			}
			else if (trees == null)
			{
				try
				{
					Creator transfManager = new Creator();
					Model model = transfManager.parse(filePathField.getText());
					
					if (model != null)
					{
						int opt = JOptionPane.showConfirmDialog(this, "Parsing complete. Do you wish to filter the model now?");
						if (opt == 0)
						{
							trees = transfManager.generateModelTrees(model, this);
							
							treeScrollPane.setViewportView(trees[0]);
							treeScrollPane.validate();
							treeScrollPane.repaint();
							rdbtnFilterModelBy.setEnabled(true);
							rdbtnFilterModelBy_1.setEnabled(true);
							rdbtnFilterModelBy.setSelected(true);
//							btnFilterModelAnd.setEnabled(true);
							btnImport.setText("Filter and Import to OLED");
							
							mainTabbedPane.setSelectedIndex(1);
						}
						else if (opt == 1)
						{
							finalize(model);
						}
					}
					else
						throw new Exception("Modelo não foi importado.");
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
			else
			{
				Model model;
				
				CheckboxTree tree = (rdbtnFilterModelBy.isSelected() ? trees[0] : trees[1]);
				
				if (tree.getCheckingPaths().length == 0)
				{
					JOptionPane.showMessageDialog(this, "No Element is selected " +
							"in the active tree. Please select at least one Element");
				}
				else
				{
					model = RefOntoUMLUtil.Filter(tree);
					finalize(model);
				}
			}
		}
		else if (e.getSource() == rdbtnFilterModelBy)
		{
			rdbtnFilterModelBy.setSelected(true);
			rdbtnFilterModelBy_1.setSelected(false);
			
			treeScrollPane.setViewportView(trees[0]);
			treeScrollPane.validate();
			treeScrollPane.repaint();
		}
		else if (e.getSource() == rdbtnFilterModelBy_1)
		{
			rdbtnFilterModelBy.setSelected(false);
			rdbtnFilterModelBy_1.setSelected(true);
			
			treeScrollPane.setViewportView(trees[1]);
			treeScrollPane.validate();
			treeScrollPane.repaint();
		}
		else if (e.getSource() == chckbxIgnoreUnknownStereotypes)
		{
			if (chckbxIgnoreUnknownStereotypes.isSelected())
				chckbxCreateDefaultClassassociation.setSelected(false);
		}
		else if (e.getSource() == chckbxCreateDefaultClassassociation)
		{
			if (chckbxCreateDefaultClassassociation.isSelected())
				chckbxIgnoreUnknownStereotypes.setSelected(false);
		}
	}
	
	private void finalize(Model model)
	{
		diagManager.closeCurrentProject();
		diagManager.createCurrentProject(model);
		
		if (chckbxShowWarningLog.isSelected())
		{
			String inconsistencyLog = RefOntoUMLUtil.verifyInconsistency(model);
			if (inconsistencyLog != "") {
	        	ErrorInfo info = new ErrorInfo("Warning", "Model imported with warnings",
	        			null, "category", new Exception(inconsistencyLog), Level.WARNING, null);
	        	JXErrorPane.showDialog(diagManager, info);
	    	}
		}
		
		if (chckbxImportConstraints.isSelected())
		{
			for (XMI2RefConstraint constr : XMI2RefConstraint.getConstraints())
				diagManager.getFrame().getInfoManager().getOcleditor().addText(constr.getStringRepresentation()+"\n\n");
		}
		
		this.dispose();
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e)
	{
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
		ChckBoxTreeNodeElem chckNode = (ChckBoxTreeNodeElem) node.getUserObject();
		String info = chckNode.getInfo();
		objDescription.setText(info);
	}

}
