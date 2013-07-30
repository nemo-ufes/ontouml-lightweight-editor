package br.ufes.inf.nemo.move.ribbons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.ui.dialog.AutoCompletionDialog;

public class ModelRibbonBand extends JRibbonBand {

	private static final long serialVersionUID = 1L;
	private TheFrame frame;
	private JCommandButton newButton;
	private JCommandButton openButton;
	private JCommandButton saveButton;
	private JCommandButton diagnoseButton;
	private JCommandButton verifyButton;	
	private JCommandButton autoCompletionButton;
	private JCommandButton showAliasButton;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelRibbonBand(TheFrame frame, String title, ResizableIcon icon) 
	{
		super(title, icon);		
		
		this.frame = frame;
		
		newButton = new JCommandButton("New",getResizableIconFromResource("resources/icon/doc-36x36.png"));
		openButton = new JCommandButton("Open",getResizableIconFromResource("resources/icon/open-36x36.png"));
		saveButton = new JCommandButton("Save", getResizableIconFromResource("resources/icon/save-36x36.png"));
		diagnoseButton = new JCommandButton("Problems", getResizableIconFromResource("resources/icon/diagnostic-36x36.png"));
		verifyButton = new JCommandButton("Syntax", getResizableIconFromResource("resources/icon/check-36x36.png"));
		autoCompletionButton = new JCommandButton("Complete",getResizableIconFromResource("resources/icon/completion-36x36.png"));		
		showAliasButton = new JCommandButton("Aliases",getResizableIconFromResource("resources/icon/label-36x36.png"));	
			
		newButton.setEnabled(false);
		verifyButton.setEnabled(false);
		
		addCommandButton(newButton, RibbonElementPriority.TOP);
		addCommandButton(openButton, RibbonElementPriority.MEDIUM);		
		addCommandButton(saveButton, RibbonElementPriority.MEDIUM);		
		addCommandButton(diagnoseButton, RibbonElementPriority.TOP);		
		addCommandButton(verifyButton, RibbonElementPriority.MEDIUM);
		addCommandButton(autoCompletionButton, RibbonElementPriority.TOP);
		addCommandButton(showAliasButton, RibbonElementPriority.MEDIUM);
		
		setResizePolicies((List) Arrays.asList(
				new CoreRibbonResizePolicies.None(getControlPanel()),				
				new IconRibbonBandResizePolicy(getControlPanel())));
		setPreferredSize(new Dimension(400, 90));
		
		newButton.addActionListener(new NewOntoUMLListener());
		openButton.addActionListener(new OpenOntoUMLListener());
		saveButton.addActionListener(new SaveOntoUMLListener());
		diagnoseButton.addActionListener(new DiagnoseOntoUMLListener());
		verifyButton.addActionListener(new VerifyOntoUMLListener());
		autoCompletionButton.addActionListener(new CompletionOntoUMLListener());
		showAliasButton.addActionListener(new ShowAliasesOntoUMLListener());
	}
	
	private static ResizableIcon getResizableIconFromResource(String resource) 
	{
		return ImageWrapperResizableIcon.getIcon(TheFrame.class.getClassLoader().getResource(resource), new Dimension(36,36));
	}
	
	/**
	 * Open OntoUML Action Listener.
	 */
	 class OpenOntoUMLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	frame.getManager().doOpenOntoUML();
	    }
	 }
	 
	/**
	 * Save OntoUML Action Listener.
	 */
	 class SaveOntoUMLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	frame.getManager().doSaveOntoUML();
	    }
	 }
	 
	 /**
	 * New OntoUML Action Listener.
	 */
	 class NewOntoUMLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    		    	
	    }
	 }
	 
	 /**
	 * Diagnose OntoUML Action Listener.
	 */
	 class DiagnoseOntoUMLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	frame.getManager().doModelDiagnostic();	    	
	    }
	 }	 
	 
	 /**
	 * Verify OntoUML Action Listener.
	 * 
	 * @author John
	 */
	 class VerifyOntoUMLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	
	    }
	 }	 
	 /**
	 * Completion OntoUML Action Listener.
	 */
	 class CompletionOntoUMLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	if(frame.getManager().getOntoUMLModel().getOntoUMLParser()==null)
            {                               
                frame.showInformationMessageDialog("Auto Completion","First, you need to open a Model.");                              
            }else{                                  
            	AutoCompletionDialog.open(frame);
            } 
	    }
	 }
		 
	/**
	 * Aliases OntoUML Action Listener.
	 */
	 class ShowAliasesOntoUMLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	frame.getManager().doShowOrHideAliases();
	    }
	 }
}
