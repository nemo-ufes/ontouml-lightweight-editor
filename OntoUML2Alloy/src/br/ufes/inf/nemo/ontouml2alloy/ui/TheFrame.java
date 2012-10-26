package br.ufes.inf.nemo.ontouml2alloy.ui;

/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OntoUML2Alloy (OntoUML to Alloy Transformation).
 *
 * OntoUML2Alloy is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OntoUML2Alloy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OntoUML2Alloy; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.util.Options;
import br.ufes.inf.nemo.ontouml2alloy.verifier.OntoUMLVerifier;

/**
 * This Frame was created using the Windows Builder in Eclipse. 
 *
 * 	@author John Guerson
 */

public class TheFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public JPanel contentPane;
	
	public JTabbedPane tabbedPane;
	
	public OptionsPanel enforcepanel;
	
	public OntoUMLModelPanel modelpanel;
	
	public ExecutePanel executepanel;
	
	public AlloyOutputPanel outputpanel;
		
	private JMenuBar menuBar;
	
	private JMenu mnHelp;
	
	private JMenu mnFile;
	
	private JMenuItem mntmLoadModel;
	
	/**
	 * Create the frame for OLED.
	 */
	public TheFrame (RefOntoUML.Model model, String alsPath)
	{
		this();		
		
		modelpanel.setModel(model);
		outputpanel.setAlloyPath(alsPath);
		
		tabbedPane.setSelectedIndex(0);
	}
	
	/**
	 * Create the frame.
	 */
	
	public TheFrame() 
	{							
		setTitle("OntoUML Model Validation Environment");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 535, 575);
		setLocationRelativeTo(null);
						
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmLoadModel = new JMenuItem("Load Model");
		mnFile.add(mntmLoadModel);
		
		mntmLoadModel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		
		mntmLoadModel.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 LoadOntoUMLActionPerformed(event);					
       		}
       	});		
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
				        
		modelpanel = new OntoUMLModelPanel();		
		contentPane.add(BorderLayout.NORTH,modelpanel);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		executepanel = new ExecutePanel();		
		executepanel.btnExecuteWithAnalyzer.addActionListener(new ActionListener() 
		{
	       public void actionPerformed(ActionEvent event) 
	       {        	   
	    	   ExecuteButtonActionPerformed(event);
	       }
	    });		
		contentPane.add(BorderLayout.SOUTH,executepanel);		
		
		enforcepanel = new OptionsPanel();
		tabbedPane.addTab("Axiomatization Enforcement", null, enforcepanel, null);		
		contentPane.add(BorderLayout.CENTER,tabbedPane);				
						
		outputpanel = new AlloyOutputPanel();
		tabbedPane.addTab("Output", null, outputpanel, null);

		String iconPath = "/resources/br/ufes/inf/nemo/ontouml2alloy/window.png";
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Image.class.getResource(iconPath)));
		
	}		

	/**	Action Performed for Loading  OntoUML Model. */
	public void LoadOntoUMLActionPerformed (ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Load Model");
		FileNameExtensionFilter ontoumlFilter = new FileNameExtensionFilter("OntoUML Model (*.refontouml)", "refontouml");
		fileChooser.addChoosableFileFilter(ontoumlFilter);
		fileChooser.setFileFilter(ontoumlFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == ontoumlFilter) 
			{
				try{
					
					modelpanel.setModel(fileChooser.getSelectedFile().getPath());
					outputpanel.setAlloyPath(fileChooser.getSelectedFile().getPath().replace(".refontouml",".als"));
					
				} catch (IOException e) {				
					String msg = "An error ocurred while loading the model into a resource";
					JOptionPane.showMessageDialog(this,msg,"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**	Action Performed for Executing Validation. */
	public void ExecuteButtonActionPerformed (ActionEvent arg0)
	{				
		try {
		
		Options opt = enforcepanel.getOptions();
		String alsPath = outputpanel.getAlloyPath();
		RefOntoUML.Package refmodel = modelpanel.getModel();
		
		// run verifier...
		OntoUMLVerifier verifier = new OntoUMLVerifier(refmodel);
		verifier.initialize();
		
		// no substance sortal warning...
		if (!verifier.haveSubstanceSortal && opt.identityPrinciple) 
		{
			opt.identityPrinciple=false;
			enforcepanel.cbxIdentityPrinciple.setSelected(false);
			JOptionPane.showMessageDialog(this, "No Substance Sortals in the model.\n\nThe Identity Principle Axiom should not be enforced."
					+"\nThis option was unchecked by default.\n ", "Warning",JOptionPane.WARNING_MESSAGE);
		}
	
		// dispose window...
		dispose();
		
		// run transformation...
		OntoUML2Alloy.Transformation(refmodel, alsPath, opt);

		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}		
	}
}