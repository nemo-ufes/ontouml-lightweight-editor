package br.ufes.inf.nemo.move.mvc.view;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.move.mvc.model.OntoUMLModel;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLCheckBoxTree;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLPathBar;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLToolBar;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLTreeNodeElem;
import br.ufes.inf.nemo.move.ui.util.TreeScrollPane;
import javax.swing.border.MatteBorder;

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
	private OntoUMLPathBar ontobar;
	private OntoUMLToolBar ontotoolbar;
	private TreeScrollPane ontotree;
	private CheckboxTree modeltree;
	private JPanel panel_1;
	private JPanel panel_2;
	
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
		setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.GRAY));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
					
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setLayout(new BorderLayout(0, 0));
		panel.setPreferredSize(new Dimension(40, 50));
		
		ontobar = new OntoUMLPathBar();		
						
		ontotoolbar = new OntoUMLToolBar();
		ontotoolbar.setPreferredSize(new Dimension(40,10));
		
		panel.add(BorderLayout.NORTH,ontobar);
		panel.add(BorderLayout.CENTER,ontotoolbar);
		
		add(BorderLayout.NORTH,panel);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(0, 0, 0, 1, (Color) Color.GRAY));
		panel_2.setPreferredSize(new Dimension(16, 30));
		panel.add(panel_2, BorderLayout.WEST);
		
		ontotree = new TreeScrollPane();
		add(BorderLayout.CENTER,ontotree);		
		
		panel_1 = new JPanel();		
		panel_1.setPreferredSize(new Dimension(15,30));
		add(panel_1, BorderLayout.WEST);
		
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
				addTreeListener(new ModelTreeListener());				
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
				
				frame.getProperties().setData(chckNode);
				frame.focusOnProperties();
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
			ontobar.textPath.setText("  Loaded...");
		else if (path!= null && refmodel !=null){
			ontobar.textPath.setText("  "+path.substring(path.lastIndexOf(File.separator)+1, path.length()));
		}
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
		ontotoolbar.btnOpen.addActionListener(actionListener);
	}	    
	
	/**
	 * Add Verify Model Action Listener.
	 * 
	 * @param actionListener
	 */
	public void addVerifyModelListener(ActionListener actionListener) 
	{
		ontotoolbar.btnVerify.addActionListener(actionListener);
	}	
	
	/**
	 * Add Show Unique Names Action Listener.
	 * 
	 * @param actionListener
	 */
	public void addShowUniqueNamesListener(ActionListener actionListener) 
	{
		ontotoolbar.btnShowUnique.addActionListener(actionListener);
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
		ontotoolbar.btnSaveAs.addActionListener(actionListener);
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
