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

public class ModelEditionRibbonBand extends JRibbonBand {

	private static final long serialVersionUID = 1L;
	private TheFrame frame;
	private JCommandButton newButton;
	private JCommandButton openButton;
	private JCommandButton saveButton;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelEditionRibbonBand(TheFrame frame, String title, ResizableIcon icon) 
	{
		super(title, icon);		
		
		this.frame = frame;
		
		newButton = new JCommandButton("New",getResizableIconFromResource("resources/icon/doc-36x36.png"));
		openButton = new JCommandButton("Open",getResizableIconFromResource("resources/icon/open-36x36.png"));
		saveButton = new JCommandButton("Save", getResizableIconFromResource("resources/icon/save-36x36.png"));
	
		newButton.setEnabled(false);
		
		addCommandButton(newButton, RibbonElementPriority.TOP);
		addCommandButton(openButton, RibbonElementPriority.MEDIUM);		
		addCommandButton(saveButton, RibbonElementPriority.MEDIUM);		
		
		setResizePolicies((List) Arrays.asList(
				new CoreRibbonResizePolicies.None(getControlPanel()),				
				new IconRibbonBandResizePolicy(getControlPanel())));
		setPreferredSize(new Dimension(400, 90));
		
		newButton.addActionListener(new NewOntoUMLListener());
		openButton.addActionListener(new OpenOntoUMLListener());
		saveButton.addActionListener(new SaveOntoUMLListener());		
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
}
