package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import br.ufes.inf.nemo.oled.AppCommandListener;
import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.Editor;
import br.ufes.inf.nemo.oled.util.ConfigurationHelper;
import br.ufes.inf.nemo.oled.util.IconLoader;
import br.ufes.inf.nemo.oled.util.IconLoader.IconType;

public class StartPanel extends JPanel implements Editor {
	
	private static final long serialVersionUID = 1851067745174024251L;
	private BackgroundPanel backgroundPanel;
	private JPanel recentPanel;
	@SuppressWarnings("rawtypes")
	private JList recentList;
	private JScrollPane recentScroll;
	private JLabel lblOpenRecent;
	private AppCommandListener commandListener;
	private JLabel lblTitle;
	private JButton btnImportFromEa;
	private JLabel lblLogo;
	
	public StartPanel(AppCommandListener commandDispatcher) {
		super();
		this.commandListener = commandDispatcher;
		initGUI();
		populateRecentProjects();
	}
	
	@SuppressWarnings("rawtypes")
	public void initGUI() 
	{
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		add(mainPanel,BorderLayout.CENTER);		
		lblLogo = new JLabel("");
		lblLogo.setVerticalAlignment(SwingConstants.TOP);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(StartPanel.class.getResource("/resources/icons/nemo73.png")));						
		recentPanel = new JPanel();
		BorderLayout recentPanelLayout = new BorderLayout();
		recentPanel.setLayout(recentPanelLayout);
		recentPanel.setBackground(new java.awt.Color(255,255,255));
		recentScroll = new JScrollPane();
		recentPanel.add(recentScroll, BorderLayout.CENTER);
		recentScroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		recentList = new JList();
		//recentList.setBackground(new Color(0xF2FCAC));
		recentScroll.setViewportView(recentList);		
		lblOpenRecent = new JLabel(" Open Recent Projects:");		
		lblTitle = new JLabel("");
		lblTitle.setIcon(new ImageIcon(StartPanel.class.getResource("/resources/icons/headwelcome.png")));		
		JButton btnNewProject = new JButton("New Project");
		btnNewProject.setIconTextGap(10);
		btnNewProject.setFocusable(false);
		btnNewProject.setIcon(new ImageIcon(StartPanel.class.getResource("/resources/icons/x16/page_2.png")));
		btnNewProject.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				commandListener.handleCommand("NEW_PROJECT");				
			}
		});
		JButton btnOpenProject = new JButton("Open Project");
		btnOpenProject.setIconTextGap(10);
		btnOpenProject.setFocusable(false);
		btnOpenProject.setIcon(new ImageIcon(StartPanel.class.getResource("/resources/icons/x16/folder.png")));
		btnOpenProject.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				commandListener.handleCommand("OPEN_PROJECT");				
			}
		});
		btnImportFromEa = new JButton("Import from EA");
		btnImportFromEa.setIconTextGap(10);
		btnImportFromEa.setFocusable(false);		
		btnImportFromEa.setIcon(new ImageIcon(StartPanel.class.getResource("/resources/icons/x16/ea.jpg")));
		btnImportFromEa.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				commandListener.handleCommand("IMPORT_XMI");				
			}
		});
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_mainPanel.createSequentialGroup()
							.addComponent(lblLogo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTitle, 0, 0, Short.MAX_VALUE))
						.addGroup(gl_mainPanel.createSequentialGroup()
							.addComponent(btnNewProject)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOpenProject)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnImportFromEa))
						.addComponent(lblOpenRecent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(recentPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(194))
		);
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblTitle)
						.addComponent(lblLogo))
					.addGap(22)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewProject, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOpenProject, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnImportFromEa, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblOpenRecent)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(recentPanel, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		mainPanel.setLayout(gl_mainPanel);		
		backgroundPanel = new BackgroundPanel();
		backgroundPanel.setPreferredSize(new Dimension(400, 150));
		add(backgroundPanel, BorderLayout.SOUTH);				
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked"})
	private void populateRecentProjects(){
		recentList.setModel(new DefaultComboBoxModel(ConfigurationHelper.getRecentProjects()));
		recentList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() >= 2) 
		        {
		        	commandListener.handleCommand("OPEN_RECENT_PROJECT");
		        }
		    }
		});
	}

	public String getSelectedRecentFile()
	{
		return (String) recentList.getSelectedValue();
	}
	
	private class BackgroundPanel extends JPanel
	{
		private static final long serialVersionUID = 2336092539913014948L;		
	    Image image;
	    public BackgroundPanel()
	    {
	        image = IconLoader.getInstance().getImage(IconType.BACKGROUND_WELCOME.name());
	    }
	    public void paint(Graphics g)
	    {
	        // Draws the image to the BackgroundPanel.	        
			int posx = 0, posy = 0, hvar = 0;
			int width = image.getWidth(this);
			//int height = image.getHeight(this);			
			Rectangle clip = g.getClipBounds();			
			hvar = (int) (clip.getWidth() / width);			
			for(int i = 0; i <= hvar; i++)
			{
				posx = width * i;
				g.drawImage(image, posx, posy, this);
			}
	    }
	}
	
	@Override
	public boolean isSaveNeeded() {
		return false;
	}

	@Override
	public EditorNature getEditorNature() {
		return EditorNature.READ_ONLY;
	}

	@Override
	public Diagram getDiagram() {
		return null;
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public UmlProject getProject() {
		return null;
	}
}
