package br.ufes.inf.nemo.move.ui.ontouml;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

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
		setBackground(Color.WHITE);
		setFloatable(false);
		setPreferredSize(new Dimension(30,50));
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(192, 192, 192)));
		
		btnOpen = new JButton("");
		btnOpen.setBackground(Color.WHITE);
		btnOpen.setFocusable(false);
		btnOpen.setToolTipText("Open OntoUML Model (*.refontouml)");
		btnOpen.setIcon(new ImageIcon(OntoUMLToolBar.class.getResource("/resources/icon/open-16x16.png")));
			
		btnSaveAs = new JButton("");
		btnSaveAs.setBackground(Color.WHITE);
		btnSaveAs.setFocusable(false);
		btnSaveAs.setIcon(new ImageIcon(OntoUMLToolBar.class.getResource("/resources/icon/save-16x16.png")));
		btnSaveAs.setToolTipText("Save OntoUML Model (*.refontouml)");
		
		JSeparator toolbarSeparator1 = new JToolBar.Separator();
		toolbarSeparator1.setOrientation(SwingConstants.VERTICAL);
				
		btnVerify = new JButton("");
		btnVerify.setBackground(Color.WHITE);
		btnVerify.setFocusable(false);
		btnVerify.setToolTipText("See some problems found in your model");
		btnVerify.setIcon(new ImageIcon(OntoUMLToolBar.class.getResource("/resources/icon/diagnostic-16x16.png")));
				
		JSeparator toolbarSeparator2 = new JToolBar.Separator();
		toolbarSeparator2.setOrientation(SwingConstants.VERTICAL);
				
		btnShowUnique = new JButton("");
		btnShowUnique.setBackground(Color.WHITE);
		btnShowUnique.setFocusable(false);
		btnShowUnique.setToolTipText("Show Generated Aliases");
		btnShowUnique.setIcon(new ImageIcon(OntoUMLToolBar.class.getResource("/resources/icon/label2-16x16.png")));		
				
		add(btnOpen);
		add(btnSaveAs);	
		add(toolbarSeparator2);
		add(btnVerify);
		add(toolbarSeparator1);		
		add(btnShowUnique);		
	}	
}
