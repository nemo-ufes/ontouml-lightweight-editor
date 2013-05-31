package br.ufes.inf.nemo.ontouml.editor.ui;

import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class ToolManager extends JTabbedPane{
	
	private MainFrame frame;
	private ProjectExplorer explorer;
	
	public MainFrame getFrame() {
		return frame;
	}

	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}

	public ProjectExplorer getExplorer() {
		return explorer;
	}

	public void setExplorer(ProjectExplorer explorer) {
		this.explorer = explorer;
	}

	public ToolManager(MainFrame frame)
	{
		super();
		this.frame = frame;
		
		explorer = new ProjectExplorer();
		explorer.registerApplicationHandler(frame);
		
		addTab("Project Explorer", explorer);		
	}

}
