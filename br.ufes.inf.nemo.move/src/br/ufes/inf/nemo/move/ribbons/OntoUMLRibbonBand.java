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


public class OntoUMLRibbonBand extends JRibbonBand {

	private static final long serialVersionUID = 1L;
	private TheFrame frame;
	private JCommandButton openButton;
	private JCommandButton saveButton;
	private JCommandButton diagnoseButton;
	private JCommandButton aliasButton;
	private JCommandButton completionButton;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public OntoUMLRibbonBand(TheFrame frame, String title, ResizableIcon icon) 
	{
		super(title, icon);		
		
		this.frame = frame;
		
		openButton = new JCommandButton("Open",getResizableIconFromResource("resources/icon/open-36x36.png"));
		saveButton = new JCommandButton("Save", getResizableIconFromResource("resources/icon/save-36x36.png"));
		diagnoseButton = new JCommandButton("Diagnose", getResizableIconFromResource("resources/icon/diagnostic-36x36.png"));
		aliasButton = new JCommandButton("Aliases", getResizableIconFromResource("resources/icon/label2-36x36.png"));
		completionButton = new JCommandButton("Completion", getResizableIconFromResource("resources/icon/completion-36x36.png"));
	
		addCommandButton(openButton, RibbonElementPriority.TOP);		
		addCommandButton(saveButton, RibbonElementPriority.MEDIUM);		
		addCommandButton(diagnoseButton, RibbonElementPriority.MEDIUM);		
		addCommandButton(aliasButton, RibbonElementPriority.MEDIUM);
		addCommandButton(completionButton, RibbonElementPriority.MEDIUM);
		
		setResizePolicies((List) Arrays.asList(
				new CoreRibbonResizePolicies.None(getControlPanel()),
				new CoreRibbonResizePolicies.Mirror(getControlPanel()),
				new CoreRibbonResizePolicies.Mid2Low(getControlPanel()),
				new CoreRibbonResizePolicies.Mid2Low(getControlPanel()),
				new CoreRibbonResizePolicies.High2Low(getControlPanel()),
				new IconRibbonBandResizePolicy(getControlPanel())));
		setPreferredSize(new Dimension(400, 90));
		
		openButton.addActionListener(new OpenOntoUMLListener());
		saveButton.addActionListener(new SaveOntoUMLListener());
		diagnoseButton.addActionListener(new DiagnoseOntoUMLListener());
		aliasButton.addActionListener(new AliasesOntoUMLListener());
		completionButton.addActionListener(new CompletionOntoUMLListener());
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
	 * Auto Completion OntoUML Action Listener.
	 */
	 class CompletionOntoUMLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	if(frame.getManager().getOntoUMLModel().getOntoUMLParser()==null)
   			{	       			
   				frame.showInformationMessageDialog("Auto Completion","You need to open a Model!");   				
   			}else{   				
   				AutoCompletionDialog.open(frame);
   			}	    	
	    }
	 }
	 
	 /**
	 * Aliases OntoUML Action Listener.
	 * 
	 * @author John
	 */
	 class AliasesOntoUMLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	frame.getManager().doGenerateAliases();
	    }
	 }	 

}
