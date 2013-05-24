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
import br.ufes.inf.nemo.move.ui.dialog.OptionsDialog;

public class SimulationRibbonBand extends JRibbonBand {

	private static final long serialVersionUID = 1L;
	
	private TheFrame frame;
	private JCommandButton analyzerButton;
	private JCommandButton searchButton;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SimulationRibbonBand(TheFrame frame, String title, ResizableIcon icon) 
	{		
		super(title, icon);
		
		this.frame = frame;		
				
		analyzerButton = new JCommandButton("Simulate",getResizableIconFromResource("resources/icon/alloy-36x36.png"));
		searchButton = new JCommandButton("AntiPatterns",getResizableIconFromResource("resources/icon/search-36x36.png"));
		
		addCommandButton(analyzerButton, RibbonElementPriority.TOP);
		addCommandButton(searchButton, RibbonElementPriority.MEDIUM);
		
		setResizePolicies((List) Arrays.asList(
				new CoreRibbonResizePolicies.None(getControlPanel()),
				new IconRibbonBandResizePolicy(getControlPanel())));
		setPreferredSize(new Dimension(400, 90));
		
		analyzerButton.addActionListener(new AlloyAnalyzerListener());
		searchButton.addActionListener(new SearchAntiPatternListener());
	}
	
	private static ResizableIcon getResizableIconFromResource(String resource) 
	{
		return ImageWrapperResizableIcon.getIcon(TheFrame.class.getClassLoader().getResource(resource), new Dimension(36,36));
	}
	
	/**
	 * Alloy Analyzer Action Listener.
	 */
	 class AlloyAnalyzerListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	if(frame.getManager().getOntoUMLModel().getOntoUMLParser()==null)
   			{	       			
   				frame.showInformationMessageDialog("Alloy Analyzer","You need to open a Model!");   				
   			}else{   				
   				frame.getManager().doParseOCL(false);   				
   				frame.getManager().getOntoUMLOptionModel().getOptions().openAnalyzer=true;   				
   				OptionsDialog.open(frame.getManager().getOntoUMLOptionModel(),frame.getManager().getOCLOptionModel(),frame);       				
   			}
	    }
	 }
	 
	/**
	 * Search AntiPattern Action Listener.
	 */
	 class SearchAntiPatternListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	frame.getManager().doSearchAntiPatterns();
	    }
	 }
}
