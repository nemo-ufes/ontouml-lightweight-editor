package br.ufes.inf.nemo.move.mvc.view;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.move.mvc.model.OntoUMLModel;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLCheckBoxTree;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLElemTabbedPane;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLTreeBar;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLTreeNodeElem;
import br.ufes.inf.nemo.move.ui.util.TreeScrollPane;

/**
 * This class represents a View for OntoUML Model.
 * 
 * @author John Guerson
 */

public class OntoUMLView extends JPanel {

	private static final long serialVersionUID = -8391565085376481547L;

	@SuppressWarnings("unused")
	private OntoUMLModel ontoumlModel;
	
	private TheFrame frame;	
	private OntoUMLTreeBar ontobar;
	private TreeScrollPane ontotree;
	private CheckboxTree modeltree;
	private JScrollPane scrollPane;
	
	private JSplitPane ontoumlSplitPane;
	private JPanel modelpanel;
	private OntoUMLElemTabbedPane elempanel;
	private JLabel lblNoSelection;
			
	public OntoUMLElemTabbedPane getElementPanel()
	{
		return elempanel;
	}
	
	/** 
	 * Creates a View from a OntoUML model and the main frame application.
	 * 
	 * @param ontoumlModel
	 */
	public OntoUMLView(OntoUMLModel ontoumlModel, TheFrame frame)
	{
		this();
		
		this.ontoumlModel = ontoumlModel;
		this.frame = frame;
		
		setPath(ontoumlModel.getOntoUMLPath(),ontoumlModel.getOntoUMLModelInstance());
		setModelTree(ontoumlModel.getOntoUMLModelInstance(),ontoumlModel.getOntoUMLParser());
				
		validate();
		repaint();
	}
	
	/**
	 * Creates an Empty View for OntoUML Model.
	 */
	public OntoUMLView() 
	{
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		modelpanel = new JPanel();
		modelpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		modelpanel.setLayout(new BorderLayout(0, 0));
		modelpanel.setPreferredSize(new Dimension(400, 543));
					
		ontobar = new OntoUMLTreeBar();		
		
		modelpanel.add(BorderLayout.NORTH,ontobar);
		
		ontotree = new TreeScrollPane();
		modelpanel.add(BorderLayout.CENTER,ontotree);
		
		JPanel tempPanel = new JPanel();
		tempPanel.setPreferredSize(new Dimension(400, 150));
		
		lblNoSelection = new JLabel("No Selection");
		lblNoSelection.setHorizontalAlignment(SwingConstants.CENTER);
		
		GroupLayout gl_tempPanel = new GroupLayout(tempPanel);
		gl_tempPanel.setHorizontalGroup(
			gl_tempPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tempPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNoSelection, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_tempPanel.setVerticalGroup(
			gl_tempPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tempPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNoSelection)
					.addContainerGap(190, Short.MAX_VALUE))
		);
		tempPanel.setLayout(gl_tempPanel);
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 170));
		scrollPane.setViewportView(tempPanel);
		
		ontoumlSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,modelpanel,scrollPane);						
				
		ontoumlSplitPane.setOneTouchExpandable(true);		
		ontoumlSplitPane.setBorder(null);		
		
		add(ontoumlSplitPane, BorderLayout.SOUTH);	
		
		
	}
		
	/**
	 * Set Model Tree from a Package and the OntoUML Parser.
	 * 
	 * @param refmodel
	 * @param refparser
	 */
	public void setModelTree(RefOntoUML.Package refmodel,OntoUMLParser refparser)
	{	
		if (refmodel!=null)
		{
			if(modeltree!=null) ontotree.treePanel.remove(modeltree);	
						
			modeltree = OntoUMLCheckBoxTree.createCheckBoxTree(refmodel,refparser);					
			
			if (modeltree!=null){
				elempanel= new OntoUMLElemTabbedPane();				
				addTreeListener(new ModelTreeListener());
				scrollPane.setViewportView(elempanel);
				ontoumlSplitPane.setRightComponent(scrollPane);
				ontoumlSplitPane.setDividerLocation(0.50);
			}
			
			ontotree.treePanel.add(BorderLayout.CENTER,modeltree);
			ontotree.validate();
			ontotree.repaint();
		}
	}
		
	class ModelTreeListener implements TreeSelectionListener 
	 {
		 @Override
			public void valueChanged(TreeSelectionEvent e) 
			{
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				OntoUMLTreeNodeElem chckNode = (OntoUMLTreeNodeElem) node.getUserObject();							
				elempanel.setData(chckNode);
				if (chckNode.getElement() instanceof RefOntoUML.Class) elempanel.setSelectedIndex(0);
				else if (chckNode.getElement() instanceof RefOntoUML.DataType) elempanel.setSelectedIndex(0);
				else if(chckNode.getElement() instanceof RefOntoUML.Association) elempanel.setSelectedIndex(1); 
				else if(chckNode.getElement() instanceof RefOntoUML.GeneralizationSet)elempanel.setSelectedIndex(2);
				else if(chckNode.getElement() instanceof RefOntoUML.Property)elempanel.setSelectedIndex(3); 
			}
	 }
	
	/** 
	 * Get the Check Box Model Tree. 
	 */
	public CheckboxTree getModelTree() { return modeltree; }
	
	/** 
	 * Set Path View from a absolute Path and a OntoUML Package Model.
	 * 
	 * @param path
	 * @param refmodel
	 */
	public void setPath(String path, RefOntoUML.Package refmodel)
	{
		if (path==null && refmodel!=null)
			ontobar.textPath.setText("Loaded...");
		else if (path!= null && refmodel !=null)
			ontobar.textPath.setText(path);
	}
	
	/** 
	 * Get the main frame of application.
	 * 
	 * @return
	 */
	public TheFrame getTheFrame() { return frame; }
	
	/**
	 * Add Load OntoUML Action listener.
	 * 
	 * @param actionListener
	 */
	public void addLoadOntoUMLListener(ActionListener actionListener) 
	{
		ontobar.btnOpen.addActionListener(actionListener);
	}	    
	
	/**
	 * Add Verify Model Action Listener.
	 * 
	 * @param actionListener
	 */
	public void addVerifyModelListener(ActionListener actionListener) 
	{
		ontobar.btnVerify.addActionListener(actionListener);
	}	
	
	/**
	 * Add Show Unique Names Action Listener.
	 * 
	 * @param actionListener
	 */
	public void addShowUniqueNamesListener(ActionListener actionListener) 
	{
		ontobar.btnShowUnique.addActionListener(actionListener);
	}	
			
	public void addTreeListener(TreeSelectionListener selectionListener)
	{
		modeltree.addTreeSelectionListener(selectionListener);
	}
	
	/**
	 * Export Model Action Listener.
	 * 
	 * @param actionListener
	 */
	public void addSaveAsModelListener(ActionListener actionListener)
	{
		ontobar.btnSaveAs.addActionListener(actionListener);
	}

	/**
	 * Get OntoUML Path Location.
	 */
	public String getOntoUMLPathLocation()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open OntoUML");
		FileNameExtensionFilter ontoumlFilter = new FileNameExtensionFilter("Reference OntoUML Model (*.refontouml)", "refontouml");
		fileChooser.addChoosableFileFilter(ontoumlFilter);
		fileChooser.setFileFilter(ontoumlFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == ontoumlFilter) 
			{
				return fileChooser.getSelectedFile().getPath();
			}
		}
		return null;
	}
	
	/**
	 * Save OntoUML Path Location.
	 */
	public String saveOntoUMLPathLocation()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save OntoUML");
		FileNameExtensionFilter ontoumlFilter = new FileNameExtensionFilter("Reference OntoUML Model (*.refontouml)", "refontouml");
		fileChooser.addChoosableFileFilter(ontoumlFilter);
		fileChooser.setFileFilter(ontoumlFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == ontoumlFilter) 
			{
				String path = fileChooser.getSelectedFile().getPath();
				if (path.contains(".refontouml"))
					return fileChooser.getSelectedFile().getPath();
				else
					return fileChooser.getSelectedFile().getPath()+".refontouml";				
			}
		}
		return null;
	}

}
