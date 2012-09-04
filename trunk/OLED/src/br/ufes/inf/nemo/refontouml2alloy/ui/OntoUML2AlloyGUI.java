package br.ufes.inf.nemo.refontouml2alloy.ui;

/**
 * 
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

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import RefOntoUML.Model;
import br.ufes.inf.nemo.refontouml2alloy.OntoUML2Alloy;

/** 
 * This GUI was created using the WindowsBuilder in Eclipse 
 * 
 * */

public class OntoUML2AlloyGUI extends JFrame {

	private static final long serialVersionUID = 1L;
		
	private JPanel contentPane;

	public static RefOntoUML.Model refmodel;
	public static String directoryPath;
	private static JCheckBox cbxWeakSupplementation;		
	private static JCheckBox cbxRelators;
		
	/* ============================================================================*/
	
	/** Launch the application. */	 
	public static void invoke (Model model, String dirPath) 
	{
		refmodel = model;
		directoryPath = dirPath;

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OntoUML2AlloyGUI frame = new OntoUML2AlloyGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
			
	/* ============================================================================*/
	
	/** Create the frame. */
	public OntoUML2AlloyGUI() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(OntoUML2AlloyGUI.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/window.png")));
		
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Throwable e) { }
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 277, 231);
		setLocationRelativeTo(null); 
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Setup", null, panel, null);
		
		cbxWeakSupplementation = new JCheckBox("Weak Supplementation");
		cbxWeakSupplementation.setSelected(true);
		
		JLabel lblEnforcement = new JLabel("Enforce:");
		
		cbxRelators = new JCheckBox("Relators Rule");
		cbxRelators.setSelected(true);
		
		JButton btnExecute = new JButton("Execute");
		btnExecute.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{								
				ExecuteButtonActionPerformed (arg0);				
				dispose();
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(cbxRelators)
								.addComponent(cbxWeakSupplementation)
								.addComponent(lblEnforcement)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(63)
							.addComponent(btnExecute, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEnforcement)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxWeakSupplementation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxRelators)
					.addGap(18)
					.addComponent(btnExecute)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.add(tabbedPane);
	}
	
	/* ============================================================================*/
	
	/** Execute Button Action Performed */
	public void ExecuteButtonActionPerformed (ActionEvent arg0)
	{
		try {

		boolean weakSuppl = cbxWeakSupplementation.isSelected();
		boolean relatorsRule = cbxRelators.isSelected();
		
		OntoUML2Alloy.Transformation(refmodel, directoryPath, relatorsRule, weakSuppl);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,e.getLocalizedMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);					
			e.printStackTrace();
		}
	}
			
}
