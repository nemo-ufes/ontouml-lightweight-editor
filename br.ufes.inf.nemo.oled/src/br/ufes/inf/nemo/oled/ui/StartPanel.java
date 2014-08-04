/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.oled.AppCommandListener;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.Editor;
import br.ufes.inf.nemo.oled.util.ConfigurationHelper;
import br.ufes.inf.nemo.oled.util.IconLoader;
import br.ufes.inf.nemo.oled.util.IconLoader.IconType;

/**
 * @author John Guerson
 */
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
	private JHyperLinkLabel btnImportFromEa;
	private JLabel lblResources;
	private JHyperLinkLabel lblOledHostSite;
	private JHyperLinkLabel lblEA;
	private JHyperLinkLabel lblResearchGroupSite;
	private JLabel lblBugReport;
	private JHyperLinkLabel btnNewProject;
	private JHyperLinkLabel btnOpenProject;
	private Component rigidArea;
	private Component rigidArea_1;
	private Component rigidArea_2;
	private Component rigidArea_3;
	private Component rigidArea_4;
	
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
		recentPanel = new JPanel();
		recentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		JPanel middlePanel = new JPanel();
		FlowLayout fl_middlePanel = (FlowLayout) middlePanel.getLayout();
		fl_middlePanel.setAlignment(FlowLayout.LEFT);
		middlePanel.setBackground(Color.WHITE);
		
		JPanel leftPanel = new JPanel();		
		leftPanel.setBackground(Color.WHITE);
		
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGap(182)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(recentPanel, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
						.addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(middlePanel, 0, 0, Short.MAX_VALUE)
						.addComponent(lblOpenRecent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(331))
		);
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblOpenRecent)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(recentPanel, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(middlePanel, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		btnNewProject = new JHyperLinkLabel("New Project");
		leftPanel.add(btnNewProject);
		btnNewProject.setIconTextGap(10);
		btnNewProject.setToolTipText("Create a new OLED project");
		btnNewProject.setFocusable(false);
		btnNewProject.setIcon(new ImageIcon(StartPanel.class.getResource("/resources/icons/x16/page_2.png")));
		
		rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		leftPanel.add(rigidArea_3);
		btnOpenProject = new JHyperLinkLabel("Open Project");
		btnOpenProject.setSize(new Dimension(105, 23));
		leftPanel.add(btnOpenProject);
		btnOpenProject.setIconTextGap(10);
		btnOpenProject.setToolTipText("Open an existing OLED project");
		btnOpenProject.setFocusable(false);
		btnOpenProject.setIcon(new ImageIcon(StartPanel.class.getResource("/resources/icons/x16/folder.png")));
		
		rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		leftPanel.add(rigidArea_4);
		btnImportFromEa = new JHyperLinkLabel("Import from EA");
		btnImportFromEa.setToolTipText("<html>Bring your models from Enterprise Architect (version 10) into OLED <br>and benefit of all the editor capabilities.</html>");
		leftPanel.add(btnImportFromEa);		
		btnImportFromEa.setIcon(new ImageIcon(StartPanel.class.getResource("/resources/icons/x16/ea.jpg")));
		btnImportFromEa.addMouseListener(new MouseAdapter() {			
			 @Override
			    public void mouseClicked(MouseEvent e) {				
				 commandListener.handleCommand("IMPORT_XMI");				
			}
		});
		btnOpenProject.addMouseListener(new MouseAdapter() {			
			 @Override
			    public void mouseClicked(MouseEvent e) {	
				commandListener.handleCommand("OPEN_PROJECT");				
			}
		});
		btnNewProject.addMouseListener(new MouseAdapter() {			
			 @Override
			    public void mouseClicked(MouseEvent e) {	
				commandListener.handleCommand("NEW_PROJECT");				
			}
		});
		
		lblResources = new JLabel("Resources:");
		lblResources.setPreferredSize(new Dimension(250, 16));
		middlePanel.add(lblResources);
		
		lblEA = new JHyperLinkLabel("How to install and use OntoUML in Enterprise Architect (EA) version 10");
		middlePanel.add(lblEA);
		lblEA.setIcon(new ImageIcon(StartPanel.class.getResource("/resources/icons/x16/ea.jpg")));	
		lblEA.setToolTipText("See how you can install and use OntoUML in EA");
		
		lblBugReport = new JHyperLinkLabel("Bug Report");
		middlePanel.add(lblBugReport);
		lblBugReport.setText("Bug Report - Issues List");
		lblBugReport.setToolTipText("Please report any bug here");
		lblBugReport.setIcon(new ImageIcon(StartPanel.class.getResource("/resources/icons/x16/bug.png")));
		
		rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setPreferredSize(new Dimension(100, 20));
		middlePanel.add(rigidArea);
		
		lblOledHostSite = new JHyperLinkLabel("OLED Host Site");
		middlePanel.add(lblOledHostSite);
		lblOledHostSite.setText("Project Host Site - Google Code");
		lblOledHostSite.setToolTipText("See the host site (SVN) of our project on google code");
		lblOledHostSite.setIcon(new ImageIcon(StartPanel.class.getResource("/resources/icons/x16/googlecode.png")));
		
		rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_1.setPreferredSize(new Dimension(100, 20));
		middlePanel.add(rigidArea_1);
		
		lblResearchGroupSite = new JHyperLinkLabel("Research Group Site (nemo)");
		middlePanel.add(lblResearchGroupSite);
		lblResearchGroupSite.setToolTipText("See the site of our research group (nemo)");
		lblResearchGroupSite.setText("Research Group Site - NEMO");
		lblResearchGroupSite.setIcon(new ImageIcon(StartPanel.class.getResource("/resources/icons/window16.png")));
		
		rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_2.setPreferredSize(new Dimension(100, 20));
		middlePanel.add(rigidArea_2);
		lblResearchGroupSite.addMouseListener(new MouseAdapter() {			
			 @Override
			    public void mouseClicked(MouseEvent e) {			     
				 if(commandListener instanceof AppFrame){
					 AppFrame frame = (AppFrame)commandListener;
					 frame.getDiagramManager().openLinkWithBrowser("http://nemo.inf.ufes.br/");
				 }
			 }
		});
		lblOledHostSite.addMouseListener(new MouseAdapter() {			
			 @Override
			    public void mouseClicked(MouseEvent e) {			     
				 if(commandListener instanceof AppFrame){
					 AppFrame frame = (AppFrame)commandListener;
					 frame.getDiagramManager().openLinkWithBrowser("https://code.google.com/p/ontouml-lightweight-editor/");
				 }
			 }
		});
		lblEA.addMouseListener(new MouseAdapter() {			
			 @Override
			    public void mouseClicked(MouseEvent e) {			     
				 if(commandListener instanceof AppFrame){
					 AppFrame frame = (AppFrame)commandListener;
					 frame.getDiagramManager().openLinkWithBrowser("https://code.google.com/p/ontouml-lightweight-editor/wiki/EnterpriseArchitect");
				 }
			 }
		});
		lblBugReport.addMouseListener(new MouseAdapter() {			
			 @Override
			    public void mouseClicked(MouseEvent e) {			     
				 if(commandListener instanceof AppFrame){
					 AppFrame frame = (AppFrame)commandListener;
					 frame.getDiagramManager().openLinkWithBrowser("https://code.google.com/p/ontouml-lightweight-editor/issues/list");
				 }
			 }
		});
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
