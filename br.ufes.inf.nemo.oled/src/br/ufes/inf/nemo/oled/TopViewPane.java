package br.ufes.inf.nemo.oled;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;

public class TopViewPane extends JPanel {

	private static final long serialVersionUID = -4823234075231975784L;
	
	public AppFrame frame;
	public DiagramManager diagramManager;
	
	public DiagramManager getDiagramManager() {
		return diagramManager;
	}

	public AppFrame getFrame(){
		return frame;
	}
	
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		diagramManager.setPreferredSize(preferredSize);
		super.setPreferredSize(preferredSize);
	};
	
	@Override
	public void setSize(Dimension preferredSize) {
		diagramManager.setSize(preferredSize);
		super.setPreferredSize(preferredSize);
	};
	
	public TopViewPane (final AppFrame frame) 
	{
		//setBackground(Color.WHITE);
		setMinimumSize(new Dimension(0,0));
		setLayout(new BorderLayout(0,0));
		this.frame = frame;	
		
		diagramManager = new DiagramManager(frame);
		diagramManager.setTabPlacement(JTabbedPane.BOTTOM);		
		diagramManager.addStartPanel(diagramManager,true);
		
		add(diagramManager,BorderLayout.CENTER);
		
		TitlePane panel = new TitlePane("Editor",null);
		panel.title.setIcon(new ImageIcon(TopViewPane.class.getResource("/resources/icons/x16/ui_tab_content.png")));
		add(panel, BorderLayout.NORTH);
	}
}
