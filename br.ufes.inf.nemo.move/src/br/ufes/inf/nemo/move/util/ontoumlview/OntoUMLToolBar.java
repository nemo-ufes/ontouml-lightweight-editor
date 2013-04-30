package br.ufes.inf.nemo.move.util.ontoumlview;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

/**
 * @author John Guerson
 */

public class OntoUMLToolBar extends JToolBar {
	
	private static final long serialVersionUID = -115797584019893402L;
	
	public JButton btnOpen;
	public JButton btnVerify;
	public JButton btnShowUnique;
	public JButton btnSaveAs;
	
	public OntoUMLToolBar() 
	{
		setFloatable(false);
		setOrientation(SwingConstants.VERTICAL);
		btnOpen = new JButton("");
		btnOpen.setFocusable(false);
		add(btnOpen);
		
		btnOpen.setToolTipText("Open OntoUML Model (*.refontouml)");
		btnOpen.setIcon(new ImageIcon(OntoUMLToolBar.class.getResource("/resources/icon/open-16x16.png")));
		
		btnSaveAs = new JButton("");
		btnSaveAs.setFocusable(false);
		
		add(btnSaveAs);
		btnSaveAs.setIcon(new ImageIcon(OntoUMLToolBar.class.getResource("/resources/icon/save-16x16.png")));
		btnSaveAs.setToolTipText("Save OntoUML Model (*.refontouml)");
		
		btnVerify = new JButton("");
		btnVerify.setFocusable(false);
		
		add(btnVerify);
		btnVerify.setToolTipText("See some problems found in your model");
		btnVerify.setIcon(new ImageIcon(OntoUMLToolBar.class.getResource("/resources/icon/diagnostic-16x16.png")));
		
		btnShowUnique = new JButton("");
		btnShowUnique.setFocusable(false);
		
		add(btnShowUnique);
		btnShowUnique.setToolTipText("Show Generated Aliases");
		btnShowUnique.setIcon(new ImageIcon(OntoUMLToolBar.class.getResource("/resources/icon/label2-16x16.png")));
									
	}	
}
