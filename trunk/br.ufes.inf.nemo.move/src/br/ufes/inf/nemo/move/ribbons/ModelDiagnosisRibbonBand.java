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

public class ModelDiagnosisRibbonBand extends JRibbonBand {

	private static final long serialVersionUID = 1L;
	private TheFrame frame;	
	private JCommandButton diagnoseButton;
	private JCommandButton verifyButton;	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelDiagnosisRibbonBand(TheFrame frame, String title, ResizableIcon icon) 
	{
		super(title, icon);		
		
		this.frame = frame;

		diagnoseButton = new JCommandButton("Find Problems", getResizableIconFromResource("resources/icon/diagnostic-36x36.png"));
		verifyButton = new JCommandButton("Verify Syntax", getResizableIconFromResource("resources/icon/check-36x36.png"));
			
		addCommandButton(diagnoseButton, RibbonElementPriority.TOP);		
		addCommandButton(verifyButton, RibbonElementPriority.MEDIUM);
				
		verifyButton.setEnabled(false);
		
		setResizePolicies((List) Arrays.asList(
				new CoreRibbonResizePolicies.None(getControlPanel()),
				new IconRibbonBandResizePolicy(getControlPanel())));
		setPreferredSize(new Dimension(400, 90));
		
		diagnoseButton.addActionListener(new DiagnoseOntoUMLListener());
		verifyButton.addActionListener(new VerifyOntoUMLListener());
	}
	
	private static ResizableIcon getResizableIconFromResource(String resource) 
	{
		return ImageWrapperResizableIcon.getIcon(TheFrame.class.getClassLoader().getResource(resource), new Dimension(36,36));
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

}
