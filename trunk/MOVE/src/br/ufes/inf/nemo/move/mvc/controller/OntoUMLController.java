package br.ufes.inf.nemo.move.mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Generalization;
import RefOntoUML.NamedElement;
import br.ufes.inf.nemo.move.mvc.model.OntoUMLModel;
import br.ufes.inf.nemo.move.mvc.view.OntoUMLView;
import br.ufes.inf.nemo.move.ui.ocl.OCLEditorBar;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLCheckBoxTree;

/**
 * @author John Guerson
 */

public class OntoUMLController {

	private OntoUMLView ontoumlview;
	private OntoUMLModel ontoumlmodel;
	
	/**
	 * Constructor.
	 * 
	 * @param ontoumlview
	 * @param ontoumlmodel
	 */
	public OntoUMLController(OntoUMLView ontoumlview, OntoUMLModel ontoumlmodel)
	{
		this.ontoumlview = ontoumlview;
		this.ontoumlmodel = ontoumlmodel;
		
		ontoumlview.addLoadOntoUMLListener(new LoadOntoUMLListener());
		ontoumlview.addVerifyModelListener(new VerifyModelListener());
		ontoumlview.addShowUniqueNamesListener(new ShowUniqueNamesListener());
		ontoumlview.addCompleteSelectionListener(new CompleteSelectionListener());
	}	
	
	/**
	 * Load OntoUML Action Listener.
	 * 
	 * @author John
	 */
	 class LoadOntoUMLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	try{
	     	
	      	String path = ontoumlview.getOntoUMLPathLocation();
	    				
	      	if (path==null) return;
	      	
	    	ontoumlmodel.setOntoUML(path);
	    					
	    	ontoumlview.setPath(ontoumlmodel.getOntoUMLPath(),ontoumlmodel.getOntoUMLModelInstance());
	    	ontoumlview.setModelTree(ontoumlmodel.getOntoUMLModelInstance(),ontoumlmodel.getOntoUMLParser());	    		
	    	ontoumlview.validate();
	    	ontoumlview.repaint();
	    	
	    	ontoumlview.getTheFrame().getAntiPatternListView().Clear();
	    	
	    	ontoumlview.getTheFrame().getUMLModel().setUMLModel(path.replace(".refontouml",".uml"));
	    	
	    	ontoumlview.getTheFrame().getAlloyModel().setAlloyModel(path.replace(".refontouml",".als"));
	    		    		    					
	    	} catch (IOException exception) {				
	    		String msg = "An error ocurred while loading the model.\n"+exception.getMessage();
	    		JOptionPane.showMessageDialog(ontoumlview,msg,"Error",JOptionPane.ERROR_MESSAGE);
	    	}
	    }
	 }   
	 
	 /**
	 * Verify OntoUML Action Listener.
	 * 
	 * @author John
	 */
	 class VerifyModelListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	ontoumlview.getTheFrame().getConsole().write(ontoumlmodel.verifyModel());	    	
	    	ontoumlview.getTheFrame().ShowConsole();
	    }
	 }
	 
	 
	 /**
	 * Show Unique Names Action Listener.
	 * 
	 * @author John
	 */
	 class ShowUniqueNamesListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	if (ontoumlview.getModelTree()==null) return;
	    	((OntoUMLCheckBoxTree.OntoUMLTreeCellRenderer)ontoumlview.getModelTree().getCellRenderer()).showOrHideUniqueName();	    	
	    	ontoumlview.getModelTree().updateUI();
	    }
	 }	 
	 
	 /**
	 * Complete Selection Action Listener.
	 * 
	 * @author John
	 */
	 class CompleteSelectionListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	if (ontoumlview.getModelTree()==null) return;
	    	
	    	List<EObject> selected = OntoUMLCheckBoxTree.getCheckedElements(ontoumlview.getModelTree()); 
	    	
   			ontoumlmodel.getOntoUMLParser().selectThisElements((ArrayList<EObject>)selected,true);
   			
   			List<EObject> added = ontoumlmodel.getOntoUMLParser().autoSelectDependencies(ontoumlview.includeHierarchy());
   			
   			String msg = new String();
   			for(EObject o: added) 
   			{
   				if (o instanceof NamedElement) msg += ((NamedElement)o).getName()+" added.\n";
   				if (o instanceof Generalization) msg += ((Generalization)o).getSpecific().getName()+"->"+((Generalization)o).getGeneral().getName()+" added.\n";	   						
   			}
   			if (msg.isEmpty()) msg = "All the elements are correctly selected.";
   			
    		JOptionPane.showMessageDialog(
    			ontoumlview.getTheFrame(),msg,"Complete Selections",JOptionPane.INFORMATION_MESSAGE,
    			new ImageIcon(OCLEditorBar.class.getResource("/resources/br/ufes/inf/nemo/move/selection-36x36.png"))
    		);
    		
    		OntoUMLCheckBoxTree.checkElements(added, true,ontoumlview.getModelTree());	    		
	    	ontoumlview.getModelTree().updateUI();
	    }
	 }	 
}
