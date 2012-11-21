package br.ufes.inf.nemo.move.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * @author John Guerson
 */

public class TheMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private TheFrame frame;
	private JMenu mnFile;
	
	/**
	 * Constructor.
	 * 
	 * @param parent
	 */
	public TheMenuBar (TheFrame parent)
	{
		this();
		
		this.frame = parent;
	}
	
	/**
	 * Constructor.
	 */
	public TheMenuBar()
	{	
		super();
		
		mnFile = new JMenu("File");
		add(mnFile);
		
		JMenu mnHelp = new JMenu("Help");
		add(mnHelp);
		
		addActionListeners();
	}
	
	/**
	 * Add Action Listener on Menu Items.
	 */
	private void addActionListeners()
	{
	}
}
