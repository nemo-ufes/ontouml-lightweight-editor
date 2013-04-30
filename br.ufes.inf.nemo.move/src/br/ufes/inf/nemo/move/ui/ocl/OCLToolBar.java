package br.ufes.inf.nemo.move.ui.ocl;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

/**
 * @author John Guerson
 */

public class OCLToolBar extends JToolBar {
	
	private static final long serialVersionUID = -115797584019893402L;
	
	public JButton btnOpen;
	public JButton btnSave;
	public JButton btnNew;
	public JButton btnParse;
	
	public OCLToolBar() 
	{
		setOrientation(SwingConstants.VERTICAL);
		setPreferredSize(new Dimension(30, 10));
		
		btnNew = new JButton("");
		btnNew.setFocusable(false);
		add(btnNew);
		
		btnNew.setToolTipText("New OCL textual document (*.ocl)");
		btnNew.setIcon(new ImageIcon(OCLToolBar.class.getResource("/resources/icon/doc-16x16.png")));
		
		btnOpen = new JButton("");
		btnOpen.setFocusable(false);
		
		add(btnOpen);
		btnOpen.setToolTipText("Open OCL textual document (*.ocl)");
		btnOpen.setIcon(new ImageIcon(OCLToolBar.class.getResource("/resources/icon/open-16x16.png")));
		
		btnSave = new JButton("");
		btnSave.setFocusable(false);
		
		add(btnSave);
		btnSave.setToolTipText("Save constraints into an OCL textual document (*.ocl)");
		btnSave.setIcon(new ImageIcon(OCLToolBar.class.getResource("/resources/icon/save-16x16.png")));
		
		btnParse = new JButton("");
		btnParse.setFocusable(false);
		
		add(btnParse);
		btnParse.setToolTipText("Verify constraints syntactically");
		btnParse.setIcon(new ImageIcon(OCLToolBar.class.getResource("/resources/icon/check-16x16.png")));				
	}	
}
