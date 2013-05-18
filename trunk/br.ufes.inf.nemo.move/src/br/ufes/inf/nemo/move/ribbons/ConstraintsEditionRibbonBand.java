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

public class ConstraintsEditionRibbonBand extends JRibbonBand {

	private static final long serialVersionUID = 1L;
	private TheFrame frame;
	private JCommandButton newButton;
	private JCommandButton openButton;
	private JCommandButton saveButton;
	private JCommandButton parseButton;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConstraintsEditionRibbonBand(TheFrame frame, String title, ResizableIcon icon) 
	{
		super(title, icon);
		
		this.frame = frame;		
		
		newButton = new JCommandButton("New",getResizableIconFromResource("resources/icon/doc-36x36.png"));
		openButton = new JCommandButton("Open",getResizableIconFromResource("resources/icon/open-36x36.png"));
		saveButton = new JCommandButton("Save", getResizableIconFromResource("resources/icon/save-36x36.png"));
		parseButton = new JCommandButton("Parse", getResizableIconFromResource("resources/icon/check-36x36.png"));		
		
		addCommandButton(newButton, RibbonElementPriority.MEDIUM);
		addCommandButton(openButton, RibbonElementPriority.MEDIUM);		
		addCommandButton(saveButton, RibbonElementPriority.MEDIUM);		
		addCommandButton(parseButton, RibbonElementPriority.TOP);
		
		setResizePolicies((List) Arrays.asList(
				new CoreRibbonResizePolicies.None(getControlPanel()),
				new IconRibbonBandResizePolicy(getControlPanel())));
		setPreferredSize(new Dimension(400, 90));
		
		newButton.addActionListener(new NewOCLListener());
		openButton.addActionListener(new OpenOCLListener());
		saveButton.addActionListener(new SaveOCLListener());
		parseButton.addActionListener(new ParseOCLListener());
	}
	
	private static ResizableIcon getResizableIconFromResource(String resource) 
	{
		return ImageWrapperResizableIcon.getIcon(TheFrame.class.getClassLoader().getResource(resource), new Dimension(36,36));
	}
	
	/**
	 * New OCL Action Listener.
	 */
	 class NewOCLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	frame.getManager().doNewOCL();
	    }
	 }
	 
	/**
	 * Open OCL Action Listener.
	 */
	 class OpenOCLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	frame.getManager().doOpenOCL();
	    }
	 }
	 
	/**
	 * Save OCLL Action Listener.
	 */
	 class SaveOCLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	frame.getManager().doSaveOCL();
	    }
	 }
	 
	 /**
	 * Parse OCL Action Listener.
	 */
	 class ParseOCLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	frame.getManager().doParseOCL(true);	    	
	    }
	 }
}
