/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * licence terms.
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
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.plaf.basic.BasicButtonUI;

import br.ufes.inf.nemo.oled.AppCommandListener;
import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.ConfigurationHelper;
import br.ufes.inf.nemo.oled.util.IconLoader;
import br.ufes.inf.nemo.oled.util.IconLoader.IconType;

/**
* This class provides a start screen for the application
* 
* @author Antognoni Albuquerque
* @version 1.0
*/
public class StartPanel extends JPanel implements Editor {

	private static final long serialVersionUID = 2465192499054892865L;
	private JPanel backgroundPanel;
	private JPanel contentPanel;
	private JButton openProjectButton;
	private JButton learnOntoUMLButton;
	private JPanel recentPanel;
	@SuppressWarnings("rawtypes")
	private JList recentList;
	private JScrollPane recentScroll;
	private JLabel recentLabel;
	private JButton communityButton;
	private JButton newProjectButton;
	private JLabel headLabel;
	private JPanel buttonsPanel;
	private AppCommandListener commandListener;

	/**
	 * Constructor
	 * 
	 * @param commandDispatcher the handler for the buttons commands
	 */
	public StartPanel(AppCommandListener commandDispatcher) {
		super();
		this.commandListener = commandDispatcher;
		initGUI();
		populateRecentProjects();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void populateRecentProjects() {
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
	
	//Builds the UI
	@SuppressWarnings("rawtypes")
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(450, 393));
			{
				contentPanel = new JPanel();
				this.add(contentPanel, BorderLayout.CENTER);
				FlowLayout contentPanelLayout = new FlowLayout();
				contentPanelLayout.setHgap(0);
				contentPanelLayout.setVgap(50);
				contentPanel.setLayout(contentPanelLayout);
				contentPanel.setBackground(new java.awt.Color(255,255,255));
				contentPanel.setPreferredSize(new java.awt.Dimension(450, 392));
				{
					buttonsPanel = new JPanel();
					contentPanel.add(buttonsPanel);
					GroupLayout buttonsPanelLayout = new GroupLayout((JComponent)buttonsPanel);
					buttonsPanel.setLayout(buttonsPanelLayout);
					buttonsPanel.setPreferredSize(new java.awt.Dimension(384, 312));
					buttonsPanel.setBackground(new java.awt.Color(255,255,255));
					{
						headLabel = new JLabel();
						headLabel.setSize(384, 90);
						headLabel.setIcon(IconLoader.getInstance().getIcon(IconType.HEAD_WELCOME));
					}
					{
						recentPanel = new JPanel();
						BorderLayout recentPanelLayout = new BorderLayout();
						recentPanel.setLayout(recentPanelLayout);
						recentPanel.setBackground(new java.awt.Color(255,255,255));
						{
							recentScroll = new JScrollPane();
							recentPanel.add(recentScroll, BorderLayout.CENTER);
							recentScroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
							{
								recentList = new JList();
								recentScroll.setViewportView(recentList);
							}
						}
					}
					{
						recentLabel = new JLabel();
						recentLabel.setText("Open Recent:");
					}
					{
						learnOntoUMLButton = createStartButton("import");
					}
					{
						communityButton = createStartButton("issuereport");
					}
					{
						openProjectButton = createStartButton("openproject");
					}
					{
						newProjectButton = createStartButton("newproject");
					}

					/*
					 
					 
					buttonsPanelLayout.setHorizontalGroup(buttonsPanelLayout.createParallelGroup()
					 	.addComponent(headLabel, GroupLayout.Alignment.LEADING, 0, 384, Short.MAX_VALUE)
					 	.addComponent(recentPanel, GroupLayout.Alignment.LEADING, 0, 384, Short.MAX_VALUE)
					 	.addGroup(buttonsPanelLayout.createSequentialGroup()
					 	    .addGroup(buttonsPanelLayout.createParallelGroup()
					 	        .addComponent(recentLabel, GroupLayout.Alignment.LEADING, 0, 207, Short.MAX_VALUE)
					 	        .addGroup(GroupLayout.Alignment.LEADING, buttonsPanelLayout.createSequentialGroup()
					 	            .addComponent(newProjectButton, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					 	            .addGap(25))
					 	        .addGroup(GroupLayout.Alignment.LEADING, buttonsPanelLayout.createSequentialGroup()
					 	            .addComponent(learnOntoUMLButton, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					 	            .addGap(25)))
					 	    .addGroup(buttonsPanelLayout.createParallelGroup()
					 	        .addComponent(openProjectButton, GroupLayout.Alignment.LEADING, 0, 177, Short.MAX_VALUE)
					 	        .addComponent(communityButton, GroupLayout.Alignment.LEADING, 0, 177, Short.MAX_VALUE))));
					buttonsPanelLayout.setVerticalGroup(buttonsPanelLayout.createSequentialGroup()
					 	.addComponent(headLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					 	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					 	.addGroup(buttonsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					 	    .addComponent(newProjectButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					 	    .addComponent(openProjectButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					 	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					 	.addGroup(buttonsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					 	    .addComponent(learnOntoUMLButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					 	    .addComponent(communityButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					 	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					 	.addComponent(recentLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					 	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					 	.addComponent(recentPanel, 0, 70, Short.MAX_VALUE));
					  
					 */
					buttonsPanelLayout.setHorizontalGroup(buttonsPanelLayout.createParallelGroup()
					 	.addGroup(buttonsPanelLayout.createSequentialGroup()
					 	    .addGroup(buttonsPanelLayout.createParallelGroup()
					 	        .addComponent(recentLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
					 	        .addGroup(GroupLayout.Alignment.LEADING, buttonsPanelLayout.createSequentialGroup()
					 	            .addComponent(newProjectButton, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					 	            .addGap(25))
					 	        .addGroup(GroupLayout.Alignment.LEADING, buttonsPanelLayout.createSequentialGroup()
					 	            .addComponent(learnOntoUMLButton, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					 	            .addGap(25)))
					 	    .addGroup(buttonsPanelLayout.createParallelGroup()
					 	        .addComponent(openProjectButton, GroupLayout.Alignment.LEADING, 0, 177, Short.MAX_VALUE)
					 	        .addComponent(communityButton, GroupLayout.Alignment.LEADING, 0, 177, Short.MAX_VALUE)))
					 	.addComponent(headLabel, GroupLayout.Alignment.LEADING, 0, 384, Short.MAX_VALUE)
					 	.addComponent(recentPanel, GroupLayout.Alignment.LEADING, 0, 384, Short.MAX_VALUE));
					buttonsPanelLayout.setVerticalGroup(buttonsPanelLayout.createSequentialGroup()
					 	.addComponent(headLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					 	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					 	.addGroup(buttonsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					 	    .addComponent(newProjectButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					 	    .addComponent(openProjectButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					 	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					 	.addGroup(buttonsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					 	    .addComponent(learnOntoUMLButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					 	    .addComponent(communityButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					 	.addGap(0, 31, Short.MAX_VALUE)
					 	.addComponent(recentLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					 	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					 	.addComponent(recentPanel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE));
				}
			}
			{
				backgroundPanel = new BackgroundPanel();
				backgroundPanel.setPreferredSize(new Dimension(400, 150));
				this.add(backgroundPanel, BorderLayout.SOUTH);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Creates buttons for the start screen
	private JButton createStartButton(String name)
	{
		String prefix = "startpanel." + name;
		JButton button = new JButton(getResourceString(prefix + ".caption"));
		button.setIcon(IconLoader.getInstance().getIcon(getResourceString(prefix + ".image")));	 
		button.setIconTextGap(10);
		button.setActionCommand(getResourceString(prefix + ".command"));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commandListener.handleCommand(e.getActionCommand());
			}
		});
		
		button.setFocusable(false);
		button.setToolTipText(getResourceString(prefix + ".tooltip"));
		return button;
	}
	
	/**
	 * Returns the specified resource as a String object.
	 * @param property the property name
	 * @return the property value or null if not found
	 */
	private String getResourceString(String property) {
		return ApplicationResources.getInstance().getString(property);
	}
	
	/**
	 * Internal panel, used to decorate the footer of the start screen
	 * @author Antognoni Albuquerque
	 */
	private class BackgroundPanel extends JPanel
	{

		private static final long serialVersionUID = 2336092539913014948L;
		
		// The Image to store the background image in.
	    Image image;
	    public BackgroundPanel()
	    {
	        // Loads the background image and stores in image object.
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

	/**
	 * Custom button, created based on a given image
	 * @author Antognoni Albuquerque
	 */
	@SuppressWarnings("unused")
	private class ImageButton extends JButton {

		Image image;
		private static final long serialVersionUID = 3362039507300806289L;

		/**
		 * Constructor 
		 * @param image
		 */
		public ImageButton(Image image) {
			
			this.image = image;
			
			//We add one pixel to give room for the effect during pressed state
			setPreferredSize(new Dimension(image.getWidth(this) + 1, image.getHeight(this) + 1));

			//Make the button looks the same for all Laf's
			setUI(new BasicButtonUI());
			
			//Make it transparent
			setContentAreaFilled(false);
			
			//No need to be focusable
			setFocusable(false);
			
			//setBorder(BorderFactory.createEtchedBorder());
			setBorderPainted(false);
			
			//Making nice rollover effect
			setRolloverEnabled(true);
		}


		/**
		 * Updates the UI. 
		 */
		public void updateUI() {
			//we don't want to update UI for this button
		}

		/**
		 * Paints the button with the given image
		 */
		protected void paintComponent(Graphics g) {
			
			if (getModel().isPressed()) {
				g.drawImage(image, 1, 1, this);
			}
			else
			{
				g.drawImage(image, 0, 0, this);
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
