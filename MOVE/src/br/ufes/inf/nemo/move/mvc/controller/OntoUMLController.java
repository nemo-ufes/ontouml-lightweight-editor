package br.ufes.inf.nemo.move.mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
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
		
		ontoumlview.addAllAncestorsSelectionListener(new AllAncestorsSelectionListener());
		ontoumlview.addAllDescendantsSelectionListener(new AllDescendantsSelectionListener());
		ontoumlview.addDefaultSelectionListener(new DefaultSelectionListener());
		ontoumlview.addSortalAncestorsSelectionListener(new SortalAncestorsSelectionListener());
		ontoumlview.addCompleteHierarchySelectionListener(new CompleteHierarchySelectionListener());		
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
	    	
	    	ontoumlview.getTheFrame().getManager().getAntiPatternListView().Clear();
	    	
	    	ontoumlview.getTheFrame().getManager().getUMLModel().setUMLModel(path.replace(".refontouml",".uml"));
	    	
	    	ontoumlview.getTheFrame().getManager().getAlloyModel().setAlloyModel(path.replace(".refontouml",".als"));
	    		    		    					
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
	 * All Ancestors Selection Action Listener.
	 * 
	 * @author John
	 */
	 class AllAncestorsSelectionListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	if (ontoumlview.getModelTree()==null) return;
	    	
	    	String msg = ontoumlview.getTheFrame().getManager().UpdateSelection(OntoUMLParser.ALL_ANCESTORS);
   				    	
    		JOptionPane.showMessageDialog(
    			ontoumlview.getTheFrame(),msg,"Complete Selections - All Ancestors",JOptionPane.INFORMATION_MESSAGE,
    			new ImageIcon(OCLEditorBar.class.getResource("/resources/br/ufes/inf/nemo/move/selection-36x36.png"))
    		);   		
	    }
	 }	 
	 
	 /**
	 * All Descendants Selection Action Listener.
	 * 
	 * @author John
	 */
	 class AllDescendantsSelectionListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	if (ontoumlview.getModelTree()==null) return;
	    	
	    	String msg = ontoumlview.getTheFrame().getManager().UpdateSelection(OntoUMLParser.ALL_DESCENDANTS);
   				    	
    		JOptionPane.showMessageDialog(
    			ontoumlview.getTheFrame(),msg,"Complete Selections - All Descendants",JOptionPane.INFORMATION_MESSAGE,
    			new ImageIcon(OCLEditorBar.class.getResource("/resources/br/ufes/inf/nemo/move/selection-36x36.png"))
    		);   		
	    }
	 }	
	 
	 /**
	 * Default Selection Action Listener.
	 * 
	 * @author John
	 */
	 class DefaultSelectionListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	if (ontoumlview.getModelTree()==null) return;
	    	
	    	String msg = ontoumlview.getTheFrame().getManager().UpdateSelection(OntoUMLParser.NO_HIERARCHY);
   				    	
    		JOptionPane.showMessageDialog(
    			ontoumlview.getTheFrame(),msg,"Complete Selections - Mandatory Dependencies",JOptionPane.INFORMATION_MESSAGE,
    			new ImageIcon(OCLEditorBar.class.getResource("/resources/br/ufes/inf/nemo/move/selection-36x36.png"))
    		);   		
	    }
	 }			 
	 
	 /**
	 * Substance Sortal Ancestors Selection Action Listener.
	 * 
	 * @author John
	 */
	 class SortalAncestorsSelectionListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	if (ontoumlview.getModelTree()==null) return;
	    	
	    	String msg = ontoumlview.getTheFrame().getManager().UpdateSelection(OntoUMLParser.SORTAL_ANCESTORS);
   				    	
    		JOptionPane.showMessageDialog(
    			ontoumlview.getTheFrame(),msg,"Complete Selections - Ancestors until a Substance Sortal",JOptionPane.INFORMATION_MESSAGE,
    			new ImageIcon(OCLEditorBar.class.getResource("/resources/br/ufes/inf/nemo/move/selection-36x36.png"))
    		);   		
	    }
	 }	
	 
	 /**
	 * Complete all hierarchy {all acnestors and descendants} Selection Action Listener.
	 * 
	 * @author John
	 */
	 class CompleteHierarchySelectionListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	if (ontoumlview.getModelTree()==null) return;
	    	
	    	String msg = ontoumlview.getTheFrame().getManager().UpdateSelection(OntoUMLParser.COMPLETE_HIERARCHY);
   				    	
    		JOptionPane.showMessageDialog(
    			ontoumlview.getTheFrame(),msg,"Complete Selections - All Ancestors and Descendants",JOptionPane.INFORMATION_MESSAGE,
    			new ImageIcon(OCLEditorBar.class.getResource("/resources/br/ufes/inf/nemo/move/selection-36x36.png"))
    		);   		
	    }
	 }			 
}
