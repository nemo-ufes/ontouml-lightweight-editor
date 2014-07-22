/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.dialog;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.model.OCLDocument;
import br.ufes.inf.nemo.xmi2ontouml.Creator;
import br.ufes.inf.nemo.xmi2ontouml.framework.XMI2RefConstraint;
import br.ufes.inf.nemo.xmi2ontouml.framework.XMI2RefModel;
import br.ufes.inf.nemo.xmi2ontouml.util.ChckBoxTreeNodeElem;
import br.ufes.inf.nemo.xmi2ontouml.util.RefOntoUMLUtil;

/**
 * @author Vinicius Sobral
 */
public class ImportXMIDialog extends JDialog implements ActionListener, TreeSelectionListener
{
	private static final long serialVersionUID = 2093867102692070258L;
	
	private JTextField filePathField;
	private JButton browseBtn, btnUseDefaultOptions;
	private JCheckBox chckbxImportConstraints, chckbxImportComments,
	chckbxIgnoreUnknownStereotypes, chckbxCreateDefaultClassassociation, 
	chckbxGenerateAEndsNames, chckbxGenerateAssocNames, 
	chckbxShowWarningLog, chckbxIgnoreElementsWith,	chckbxGenerateCard;
	private JTabbedPane mainTabbedPane;
	private JScrollPane treeScrollPane;
	private CheckboxTree[] trees;
	private JRadioButton rdbtnFilterModelBy, rdbtnFilterModelBy_1;
	private JTextArea objDescription;	
	DiagramManager diagManager;	
	private JPanel panel_2;
	private JPanel panel_3;

	private JButton btnRun;
	private JButton btnFilter;
	private JPanel panel_5;
	
	/**
	 * @wbp.parser.constructor
	 */
	public ImportXMIDialog(JFrame owner, boolean modal, DiagramManager diagManager)
	{
		super(owner, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ImportXMIDialog.class.getResource("/resources/icons/x16/ea.jpg")));		
		this.diagManager = diagManager;		
		initGUI();
		setLocationRelativeTo(owner);
		setVisible(true);
	}
	
	public ImportXMIDialog(JFrame owner, boolean modal, DiagramManager diagManager, String filePath)
	{
		super(owner, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ImportXMIDialog.class.getResource("/resources/icons/x16/ea.jpg")));		
		this.diagManager = diagManager;		
		initGUI();		
		filePathField.setText(filePath);
		trees = null;
		treeScrollPane.validate();
		treeScrollPane.repaint();
		rdbtnFilterModelBy.setEnabled(false);
		rdbtnFilterModelBy_1.setEnabled(false);
		rdbtnFilterModelBy.setSelected(true);		
		setLocationRelativeTo(owner);
		setVisible(true);
	}
	
	private void initGUI()
	{
		setTitle("Import from EA");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(537,532));
		setSize(new Dimension(524, 528));		
		mainTabbedPane = new JTabbedPane();
		JPanel treePanel = new JPanel();
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBackground(Color.WHITE);
		mainTabbedPane.addTab("Parser", optionsPanel);
		mainTabbedPane.addTab("Filter", treePanel);
		getContentPane().add(mainTabbedPane, BorderLayout.CENTER);				
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.setBorder(BorderFactory.createTitledBorder("Elements"));		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setBorder(BorderFactory.createTitledBorder("Error Treatment"));		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_2.setBorder(BorderFactory.createTitledBorder("Automation"));		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_3.setBorder(BorderFactory.createTitledBorder("Log"));		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(BorderFactory.createTitledBorder(""));
		GroupLayout gl_optionsPanel = new GroupLayout(optionsPanel);
		gl_optionsPanel.setHorizontalGroup(
			gl_optionsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_optionsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_optionsPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_4, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE))
					.addGap(26))
		);
		gl_optionsPanel.setVerticalGroup(
			gl_optionsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optionsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		filePathField = new JTextField();
		filePathField.setBackground(Color.WHITE);
		filePathField.setColumns(60);
		filePathField.setEditable(false);
		browseBtn = new JButton("...");
		browseBtn.addActionListener(this);		
		btnRun = new JButton("Parse File");				
		btnUseDefaultOptions = new JButton("Reset Configuration");
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(filePathField, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(browseBtn, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnUseDefaultOptions)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRun)
					.addContainerGap(222, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(filePathField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(browseBtn))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUseDefaultOptions)
						.addComponent(btnRun))
					.addGap(17))
		);
		panel_4.setLayout(gl_panel_4);
		btnUseDefaultOptions.addActionListener(this);
		btnRun.addActionListener(this);
		chckbxShowWarningLog = new JCheckBox("Show Warnings Log");
		chckbxShowWarningLog.setBackground(Color.WHITE);
		panel_3.add(chckbxShowWarningLog);
		chckbxShowWarningLog.setSelected(true);
		chckbxGenerateAEndsNames = new JCheckBox("Auto generate names for unnamed Association Ends");
		chckbxGenerateAEndsNames.setBackground(Color.WHITE);
		panel_2.add(chckbxGenerateAEndsNames);
		chckbxGenerateAssocNames = new JCheckBox("Auto generate names for unnamed Associations");
		chckbxGenerateAssocNames.setBackground(Color.WHITE);
		panel_2.add(chckbxGenerateAssocNames);
		chckbxGenerateCard = new JCheckBox("Set 1 for null cardinalities");
		chckbxGenerateCard.setBackground(Color.WHITE);
		chckbxGenerateCard.setPreferredSize(new Dimension(270, 23));
		panel_2.add(chckbxGenerateCard);
		chckbxGenerateCard.setSelected(true);
		chckbxCreateDefaultClassassociation = new JCheckBox("Create default Class/Association elements for unknown stereotypes");
		chckbxCreateDefaultClassassociation.setBackground(Color.WHITE);
		panel_1.add(chckbxCreateDefaultClassassociation);
		chckbxCreateDefaultClassassociation.setSelected(true);
		chckbxIgnoreUnknownStereotypes = new JCheckBox("Ignore Elements with unknown stereotypes");
		chckbxIgnoreUnknownStereotypes.setBackground(Color.WHITE);
		panel_1.add(chckbxIgnoreUnknownStereotypes);
		chckbxIgnoreElementsWith = new JCheckBox("Ignore Elements with error");
		chckbxIgnoreElementsWith.setBackground(Color.WHITE);
		panel_1.add(chckbxIgnoreElementsWith);
		chckbxIgnoreElementsWith.setSelected(true);
		chckbxIgnoreUnknownStereotypes.addActionListener(this);
		chckbxCreateDefaultClassassociation.addActionListener(this);
		chckbxImportConstraints = new JCheckBox("Import Constraints");
		chckbxImportConstraints.setBackground(Color.WHITE);
		panel.add(chckbxImportConstraints);
		chckbxImportConstraints.setSelected(true);
		chckbxImportComments = new JCheckBox("Import Comments");
		chckbxImportComments.setBackground(Color.WHITE);
		panel.add(chckbxImportComments);
		chckbxImportComments.setSelected(false);
		optionsPanel.setLayout(gl_optionsPanel);			
		treePanel.setLayout(new BorderLayout(0, 0));		
		JPanel horizontalBox = new JPanel();
		horizontalBox.setBackground(Color.WHITE);
		horizontalBox.setPreferredSize(new Dimension(50, 40));
		treePanel.add(horizontalBox, BorderLayout.NORTH);		
		btnFilter = new JButton("Import to OLED");
		
		panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		GroupLayout gl_horizontalBox = new GroupLayout(horizontalBox);
		gl_horizontalBox.setHorizontalGroup(
			gl_horizontalBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_horizontalBox.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
					.addGap(69)
					.addComponent(btnFilter)
					.addGap(18))
		);
		gl_horizontalBox.setVerticalGroup(
			gl_horizontalBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_horizontalBox.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_horizontalBox.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnFilter)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		rdbtnFilterModelBy = new JRadioButton("Show package structure");
		rdbtnFilterModelBy.setBackground(Color.WHITE);
		panel_5.add(rdbtnFilterModelBy);
		rdbtnFilterModelBy.addActionListener(this);
		rdbtnFilterModelBy.setEnabled(false);
		rdbtnFilterModelBy_1 = new JRadioButton("Show diagrams");
		rdbtnFilterModelBy_1.setBackground(Color.WHITE);
		panel_5.add(rdbtnFilterModelBy_1);
		rdbtnFilterModelBy_1.addActionListener(this);
		rdbtnFilterModelBy_1.setEnabled(false);
		horizontalBox.setLayout(gl_horizontalBox);
		btnFilter.addActionListener(this);		
		treeScrollPane = new JScrollPane();
		treeScrollPane.setPreferredSize(new Dimension(400, 300));
		treeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		treePanel.add(treeScrollPane, BorderLayout.CENTER);		
		objDescription = new JTextArea();
		objDescription.setBackground(Color.WHITE);
		objDescription.setSize(new Dimension(200,30));
		objDescription.setOpaque(false);
		objDescription.setEditable(false);
		treePanel.add(objDescription, BorderLayout.SOUTH);			
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == browseBtn)
		{
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
					treeScrollPane.validate();
					treeScrollPane.repaint();
					rdbtnFilterModelBy.setEnabled(false);
					rdbtnFilterModelBy_1.setEnabled(false);
					rdbtnFilterModelBy.setSelected(true);					
				}
			}
		}
		else if (e.getSource() == btnUseDefaultOptions)
		{
			chckbxImportConstraints.setSelected(true);
			chckbxIgnoreUnknownStereotypes.setSelected(false);
			chckbxCreateDefaultClassassociation.setSelected(true);
			chckbxIgnoreElementsWith.setSelected(true);
			chckbxGenerateAssocNames.setSelected(false);
			chckbxGenerateAEndsNames.setSelected(false);
			chckbxGenerateCard.setSelected(true);
			chckbxShowWarningLog.setSelected(true);
		}
		else if (e.getSource() == btnRun || e.getSource() == btnFilter)
		{
			if (filePathField.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(this, "Please select a file");
			}
			else if (trees == null)
			{
				try
				{
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Creator transfManager = new Creator();
					Model model = transfManager.parse(filePathField.getText(), 
							chckbxImportComments.isSelected(),
							chckbxImportConstraints.isSelected(),
							chckbxIgnoreUnknownStereotypes.isSelected(), 
							chckbxCreateDefaultClassassociation.isSelected(), 
							chckbxIgnoreElementsWith.isSelected(), 
							chckbxGenerateAssocNames.isSelected(), 
							chckbxGenerateAEndsNames.isSelected(), 
							chckbxGenerateCard.isSelected());
					
//					TreeIterator<EObject> ti = model.eAllContents();
//					while (ti.hasNext())
//					{
//						EObject el = ti.next();
//						EObject parent = el.eContainer();
//						while (parent != null)
//						{
//							if (((NamedElement)parent).getName() != null &&
//									(((NamedElement)parent).getName().equals("Fiscalização") ||
//									((NamedElement)parent).getName().equals("Infração") ||
//									((NamedElement)parent).getName().equals("Veículo Ferroviário") ||
//									((NamedElement)parent).getName().equals("Concessão Ferroviária") ||
//									((NamedElement)parent).getName().equals("Obra em Concessão") ||
//									((NamedElement)parent).getName().equals("Bem da Concessão") ||
//									((NamedElement)parent).getName().equals("Seguro em Concessão") ||
//									((NamedElement)parent).getName().equals("Transporte Ferroviário") ||
//									((NamedElement)parent).getName().equals("Gestão Ambiental")))
//							{
//								SyntacticVerificator.elems.add((Element) el);
//							}
//							parent = parent.eContainer();
//						}
//					}
					
					int opt = JOptionPane.showConfirmDialog(this, "Parsing completed. Do you want to filter the model now?", "EA Parser", JOptionPane.INFORMATION_MESSAGE);
					if (opt == 0)
					{
						trees = transfManager.generateModelTrees(model, this);
						
						treeScrollPane.setViewportView(trees[0]);
						treeScrollPane.validate();
						treeScrollPane.repaint();
						rdbtnFilterModelBy.setEnabled(true);
						rdbtnFilterModelBy_1.setEnabled(true);
						rdbtnFilterModelBy.setSelected(true);
//						btnFilterModelAnd.setEnabled(true);
						
						
						mainTabbedPane.setSelectedIndex(1);
					}
					else if (opt == 1)
					{
						finalize(model);
					}
				}
				catch (Exception ex)
				{
					ErrorInfo info = new ErrorInfo("Error", "Model was not imported",
		        			null, "category", ex, Level.SEVERE, null);
		        	JXErrorPane.showDialog(diagManager, info);
					ex.printStackTrace();
				}
			}
			else
			{
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
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
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
			ArrayList<OCLDocument> documents;
			for (XMI2RefConstraint constr : XMI2RefModel.getConstraints()){
				documents = diagManager.getFrame().getBrowserManager().getProjectBrowser().getOCLDocuments();
				if(documents.size() > 0){
					documents.get(0).addContent(constr.getStringRepresentation());
				}				
			}
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
