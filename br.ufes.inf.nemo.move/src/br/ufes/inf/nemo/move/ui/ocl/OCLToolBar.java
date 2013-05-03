package br.ufes.inf.nemo.move.ui.ocl;

import java.awt.Color;
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

public class OCLToolBar extends JToolBar {
	
	private static final long serialVersionUID = -115797584019893402L;
	
	public JButton btnOpen;
	public JButton btnSave;
	public JButton btnNew;
	public JButton btnParse;
	//private Component rigidArea;
	
	public OCLToolBar() 
	{
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(30, 50));
		setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		
		btnNew = new JButton("");
		btnNew.setBackground(Color.WHITE);
		btnNew.setFocusable(false);				
		btnNew.setToolTipText("New OCL textual document (*.ocl)");
		btnNew.setIcon(new ImageIcon(OCLToolBar.class.getResource("/resources/icon/doc-16x16.png")));
		
		btnOpen = new JButton("");
		btnOpen.setBackground(Color.WHITE);
		btnOpen.setFocusable(false);		
		btnOpen.setToolTipText("Open OCL textual document (*.ocl)");
		btnOpen.setIcon(new ImageIcon(OCLToolBar.class.getResource("/resources/icon/open-16x16.png")));
		
		btnSave = new JButton("");
		btnSave.setBackground(Color.WHITE);
		btnSave.setFocusable(false);		
		btnSave.setToolTipText("Save constraints into an OCL textual document (*.ocl)");
		btnSave.setIcon(new ImageIcon(OCLToolBar.class.getResource("/resources/icon/save-16x16.png")));
		
		JSeparator toolbarSeparator1 = new JToolBar.Separator();
		toolbarSeparator1.setOrientation(SwingConstants.VERTICAL);
		
		btnParse = new JButton("");
		btnParse.setBackground(Color.WHITE);
		btnParse.setFocusable(false);		
		btnParse.setToolTipText("Verify constraints syntactically");
		btnParse.setIcon(new ImageIcon(OCLToolBar.class.getResource("/resources/icon/check-16x16.png")));
		
		//rigidArea = Box.createRigidArea(new Dimension(20, 20));
		//add(rigidArea);
		
		add(btnNew);
		add(btnOpen);
		add(btnSave);
		add(toolbarSeparator1);
		add(btnParse);
	}	
}
