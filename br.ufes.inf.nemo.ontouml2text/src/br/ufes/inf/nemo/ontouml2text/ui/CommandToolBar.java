package br.ufes.inf.nemo.ontouml2text.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class CommandToolBar extends JToolBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7649052185595810477L;
	
	private MainWindow main;
	private JButton generateDescrBtn;
	private JButton generateCSVBtn;
	
	/**
	 *	Constructor.
	 */
	public CommandToolBar() 
	{
		super();
		
		initGUI();
	}
	
	public CommandToolBar(MainWindow main) 
	{
		this();
		
		this.main = main;
	}
	
	private void initGUI()
	{
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));		
		setBackground(UIManager.getColor("ToolBar.dockingBackground"));
		
		setOrientation(SwingConstants.VERTICAL);
		setPreferredSize(new Dimension(50, 350));
		
		createGenerateDescriptionBtn();
		
		createGenerateCSVBtn();
	}
	
	private void createGenerateDescriptionBtn()
	{
		generateDescrBtn = new ToolbarButton("","/resources/icon/import-36x36.png");
		generateDescrBtn.setToolTipText("Generates the model description.");
		
		generateDescrBtn.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			main.generateModelDescription();
       			
       			main.getAppDialog().dispose();
       		}
       	});
		
		add(generateDescrBtn);
	}
	
	private void createGenerateCSVBtn()
	{
		generateCSVBtn = new ToolbarButton("","/resources/icon/csv-36x36.png");
		generateCSVBtn.setToolTipText("Exports the model description to a CSV file.");
		
		generateCSVBtn.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			main.generateCSV();
       			
       			main.getAppDialog().dispose();
       		}
       	});
		
		add(generateCSVBtn);
	}
	
	public class ToolbarButton extends JButton implements MouseListener {

		private static final long serialVersionUID = 1L;

		public ToolbarButton (String name, String path)
		{
			this();
			setText(name);		
			setIcon(new ImageIcon(ToolbarButton.class.getResource(path)));
			setVerticalTextPosition(SwingConstants.BOTTOM);
			setHorizontalTextPosition(SwingConstants.CENTER);
		}
		
		public ToolbarButton ()
		{
			super();
			setFocusable(false);
			this.addMouseListener(this);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {	
			
		}

		@Override
		public void mouseEntered(MouseEvent e) 
		{		
			setBackground(new Color(0xF2FCAC));
			repaint();
		}	

		@Override
		public void mouseExited(MouseEvent e) {
			setBackground(new Color(240,240,240));
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		

	}
}
