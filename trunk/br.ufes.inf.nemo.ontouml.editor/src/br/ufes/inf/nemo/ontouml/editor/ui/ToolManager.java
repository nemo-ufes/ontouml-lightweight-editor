package br.ufes.inf.nemo.ontouml.editor.ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class ToolManager extends JTabbedPane{
	
	@SuppressWarnings("unused")
	private MainFrame frame;
	
	public ToolManager(MainFrame frame)
	{
		super();
		this.frame = frame;
		
		ProjectExplorer explorer = new ProjectExplorer();
		explorer.registerApplicationHandler(frame);
		
		addTab("Project Explorer", explorer);
		addTab("Tab 2", new JPanel());
	}

}
