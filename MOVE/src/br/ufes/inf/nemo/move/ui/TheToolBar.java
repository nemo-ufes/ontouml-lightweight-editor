package br.ufes.inf.nemo.move.ui;

import javax.swing.JToolBar;
import javax.swing.UIManager;

/**
 * This Panel was created using the Windows Builder in Eclipse. 
 * 
 * @author John Guerson
 */

public class TheToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;

	public TheToolBar() 
	{
		setRollover(true);
		setFloatable(false);
		setBackground(UIManager.getColor("ToolBar.dockingBackground"));
	}

}
