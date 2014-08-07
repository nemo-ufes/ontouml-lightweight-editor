package br.ufes.inf.nemo.oled;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class TitlePane extends JPanel {
	
	private static final long serialVersionUID = 7182319329510468466L;
	public JLabel title;
	
	public TitlePane()
	{		
		setBorder(new LineBorder(UIManager.getColor("Panel.background")));
		
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(3);
		flowLayout.setHgap(3);
		flowLayout.setAlignment(FlowLayout.LEFT);
		//panel.setBorder(new LineBorder(UIManager.getColor("TabbedPane.darkShadow")));
		//setBackground(new Color(0x999999));
		setBackground(new Color(0xDDDDDD));
		title = new JLabel();
		title.setBackground(Color.WHITE);		
		title.setAlignmentY(Component.TOP_ALIGNMENT);
		add(title);				
		title.setText("Toolbox");
	}
	
	public TitlePane(String titleText, String iconPath)
	{
		this();
		if(iconPath!=null) title.setIcon(new ImageIcon(TitlePane.class.getResource(iconPath)));
		title.setText(titleText);
	}

}
