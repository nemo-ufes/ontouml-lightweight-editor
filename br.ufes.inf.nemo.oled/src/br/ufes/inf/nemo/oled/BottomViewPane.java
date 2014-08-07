package br.ufes.inf.nemo.oled;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import br.ufes.inf.nemo.oled.model.UmlProject;
import javax.swing.ImageIcon;

public class BottomViewPane  extends JPanel {

	private static final long serialVersionUID = -4823234075231975784L;
	
	public AppFrame frame;
	public InfoManager infoManager;
	public UmlProject project;
	
	public InfoManager getInfoManager() {
		return infoManager;
	}

	public AppFrame getFrame(){
		return frame;
	}
	
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		infoManager.setPreferredSize(preferredSize);
		super.setPreferredSize(preferredSize);
	};
	
	@Override
	public void setSize(Dimension preferredSize) {
		infoManager.setSize(preferredSize);
		super.setPreferredSize(preferredSize);
	};
	
	public BottomViewPane (final AppFrame frame, UmlProject project) 
	{
		//setBackground(Color.WHITE);
		setMinimumSize(new Dimension(0,0));
		setLayout(new BorderLayout(0,0));
		this.frame = frame;	
		this.project = project;
		
		infoManager = new InfoManager(frame,project);
		infoManager.setTabPlacement(JTabbedPane.BOTTOM);		
				
		add(infoManager,BorderLayout.CENTER);
		
		TitlePane panel = new TitlePane("Information Footer",null);		
		panel.title.setIcon(new ImageIcon(BottomViewPane.class.getResource("/resources/icons/x16/layout_select_footer.png")));
		add(panel, BorderLayout.NORTH);
	}
}