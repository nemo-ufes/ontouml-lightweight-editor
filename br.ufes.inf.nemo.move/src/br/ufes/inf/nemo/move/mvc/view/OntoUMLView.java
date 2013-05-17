package br.ufes.inf.nemo.move.mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.move.mvc.model.OntoUMLModel;
import br.ufes.inf.nemo.move.tree.ontouml.OntoUMLCheckBoxTree;
import br.ufes.inf.nemo.move.tree.ontouml.OntoUMLElem;
import br.ufes.inf.nemo.move.ui.TheFrame;
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
	private TreeScrollPane ontotree;
	private OntoUMLCheckBoxTree modeltree;
	
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
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 128, 128)));
		setBackground(UIManager.getColor("Panel.background"));
		setLayout(new BorderLayout(0, 0));
		
		ontotree = new TreeScrollPane();
		ontotree.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(BorderLayout.CENTER,ontotree);
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
						
			modeltree = new OntoUMLCheckBoxTree(new DefaultMutableTreeNode(new OntoUMLElem(refmodel,"")),refmodel,refparser);					
			
			frame.getManager().createOntoUMLController();
			
			ontotree.treePanel.add(BorderLayout.CENTER,modeltree);
			ontotree.validate();
			ontotree.repaint();
		}
	}
	
	/** 
	 * Get the Check Box Model Tree. 
	 */
	public OntoUMLCheckBoxTree getModelTree() { return modeltree; }
	
	/**
	 * Refresh the Model Tree.
	 */
	public void refreshModelTree()
	{
		modeltree.updateUI();		
		ontotree.validate();
		ontotree.repaint();
	}
	
	/** 
	 * Set Path View from a absolute Path and a OntoUML Package Model.
	 * 
	 * @param path
	 * @param refmodel
	 */
	public void setPath(String path, RefOntoUML.Package refmodel)
	{
		if (path==null && refmodel!=null){
			//frame.getTheStatusBar().setText("  Location:  Loaded...");
		}else if (path!= null && refmodel !=null){
			//frame.getTheStatusBar().setText("  Location:  "+path);
		}
	}
	
	/** 
	 * Get the main frame of application.
	 * 
	 * @return
	 */
	public TheFrame getTheFrame() 
	{ 
		return frame; 
	}	
	
	public void addTreeSelectionListener(TreeSelectionListener selectionListener)
	{
		modeltree.addTreeSelectionListener(selectionListener);
	}
	
	public void addTreeModelListener(TreeModelListener modeListener)
	{
		modeltree.getModel().addTreeModelListener(modeListener);
	}
	

}
