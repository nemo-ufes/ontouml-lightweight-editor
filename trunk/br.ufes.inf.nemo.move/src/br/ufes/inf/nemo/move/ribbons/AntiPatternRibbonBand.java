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

public class AntiPatternRibbonBand extends JRibbonBand {

	private static final long serialVersionUID = 1L;
	private TheFrame frame;
	private JCommandButton searchButton;
	private JCommandButton infoButton;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AntiPatternRibbonBand(TheFrame frame, String title, ResizableIcon icon) 
	{
		super(title, icon);	
		
		this.frame = frame;		
		
		searchButton = new JCommandButton("Search",getResizableIconFromResource("resources/icon/search-36x36.png"));
		infoButton = new JCommandButton("Info",getResizableIconFromResource("resources/icon/help-36x36.png"));		
		
		addCommandButton(searchButton, RibbonElementPriority.TOP);
		addCommandButton(infoButton, RibbonElementPriority.MEDIUM);
		
		setResizePolicies((List) Arrays.asList(
				new CoreRibbonResizePolicies.None(getControlPanel()),								
				new IconRibbonBandResizePolicy(getControlPanel())));
		setPreferredSize(new Dimension(400, 90));
		
		searchButton.addActionListener(new SearchAntiPatternListener());				
	}
	
	private static ResizableIcon getResizableIconFromResource(String resource) 
	{
		return ImageWrapperResizableIcon.getIcon(TheFrame.class.getClassLoader().getResource(resource), new Dimension(36,36));
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
