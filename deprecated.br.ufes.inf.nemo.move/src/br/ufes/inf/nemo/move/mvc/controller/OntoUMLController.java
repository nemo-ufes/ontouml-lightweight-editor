package br.ufes.inf.nemo.move.mvc.controller;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import br.ufes.inf.nemo.move.mvc.model.OntoUMLModel;
import br.ufes.inf.nemo.move.mvc.view.OntoUMLView;

/**
 * @author John Guerson
 */

public class OntoUMLController {

	private OntoUMLView ontoumlview;
	//private OntoUMLModel ontoumlmodel;
	
	/**
	 * Constructor.
	 * 
	 * @param ontoumlview
	 * @param ontoumlmodel
	 */
	public OntoUMLController(OntoUMLView ontoumlview, OntoUMLModel ontoumlmodel)
	{
		this.ontoumlview = ontoumlview;
		//this.ontoumlmodel = ontoumlmodel;
		
		if (ontoumlview.getModelTree()!=null)
		{						
			ontoumlview.addTreeSelectionListener(new OntoUMLTreeSelectionListener());	
			ontoumlview.addTreeModelListener(new OntoUMLTreeModelListener());
		}
	}	
	
	class OntoUMLTreeSelectionListener implements TreeSelectionListener 
	 {
		 @Override
			public void valueChanged(TreeSelectionEvent e) 
			{
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				ontoumlview.getTheFrame().getProperties().setData(node);
				ontoumlview.getTheFrame().focusOnProperties();
			}
	 }
	
	class OntoUMLTreeModelListener implements TreeModelListener 
	 {
		//listen for changes in the model (including check box toggles)
		@Override
		public void treeNodesChanged(final TreeModelEvent e) {
			System.out.println(System.currentTimeMillis() + ": nodes changed");
		}

		@Override
		public void treeNodesInserted(final TreeModelEvent e) {
			System.out.println(System.currentTimeMillis() + ": nodes inserted");
		}

		@Override
		public void treeNodesRemoved(final TreeModelEvent e) {
			System.out.println(System.currentTimeMillis() + ": nodes removed");
		}

		@Override
		public void treeStructureChanged(final TreeModelEvent e) {
			System.out.println(System.currentTimeMillis() + ": structure changed");
		}
	 }

}
