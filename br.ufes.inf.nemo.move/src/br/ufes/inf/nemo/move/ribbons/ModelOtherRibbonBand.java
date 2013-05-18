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

public class ModelOtherRibbonBand extends JRibbonBand {
	
	private static final long serialVersionUID = 1L;
	
	private TheFrame frame;
	private JCommandButton autoCompletionButton;
	private JCommandButton showAliasButton;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelOtherRibbonBand(TheFrame frame, String title, ResizableIcon icon) 
	{
		super(title, icon);		
		
		this.frame = frame;
		
		autoCompletionButton = new JCommandButton("Auto Complete",getResizableIconFromResource("resources/icon/completion-36x36.png"));		
		showAliasButton = new JCommandButton("Show Aliases",getResizableIconFromResource("resources/icon/label-36x36.png"));	
	
		addCommandButton(autoCompletionButton, RibbonElementPriority.TOP);
		addCommandButton(showAliasButton, RibbonElementPriority.MEDIUM);
		
		setResizePolicies((List) Arrays.asList(
				new CoreRibbonResizePolicies.None(getControlPanel()),
				new IconRibbonBandResizePolicy(getControlPanel())));
		setPreferredSize(new Dimension(400, 90));
		
		autoCompletionButton.addActionListener(new CompletionOntoUMLListener());
		showAliasButton.addActionListener(new ShowAliasesOntoUMLListener());
	}
	
	private static ResizableIcon getResizableIconFromResource(String resource) 
	{
		return ImageWrapperResizableIcon.getIcon(TheFrame.class.getClassLoader().getResource(resource), new Dimension(36,36));
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
