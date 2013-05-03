package br.ufes.inf.nemo.move.mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.ufes.inf.nemo.move.mvc.model.AntiPatternListModel;
import br.ufes.inf.nemo.move.mvc.view.AntiPatternListView;
import br.ufes.inf.nemo.move.ui.dialog.AntiPatternListDialog;

/**
 * @author John Guerson
 */

public class AntiPatternListController {

	@SuppressWarnings("unused")
	private AntiPatternListModel antipatterListModel;
	private AntiPatternListView antipatternListView;
	
	/**
	 * COnstructor.
	 * 
	 * @param antipatterListModel
	 * @param antipatternListView
	 */
	public AntiPatternListController(AntiPatternListModel antipatterListModel, AntiPatternListView antipatternListView)
	{
		this.antipatternListView = antipatternListView;
		this.antipatterListModel = antipatterListModel;		
		
		antipatternListView.addRunAntiPaternsManagerListener(new RunAntiPatternLListener());	
	}
			
	/**
	 * Run AntiPattern Manager Listener.
	 * 
	 * @author John
	 */
	 class RunAntiPatternLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	if (antipatternListView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()==null) 
	    	{ 
	    		antipatternListView.getTheFrame().showInformationMessageDialog("Run AntiPatterns Manager","First you need to open a model"); 
	    		return; 
	    	}
	    	
	    	try {
	    		
	    		AntiPatternListDialog.open(antipatternListView.getTheFrame());
	    		
	    	}catch(Exception x){
	    		JOptionPane.showMessageDialog(antipatternListView.getTheFrame(),x.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
	    		x.printStackTrace();
	    	}	

	    }
	 }
	
}
