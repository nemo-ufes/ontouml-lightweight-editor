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

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.ontouml2alloy.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.util.Options;
import br.ufes.inf.nemo.ontouml2alloy.util.ResourceUtil;

/**
 * This Frame was created using the Windows Builder in Eclipse. 
 *
 * 	@author John Guerson, Tiago Sales and Lucas Thom
 */

public class TheFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public JPanel contentPane;
	
	public JTabbedPane tabbedPane;
	
	public EnforcePanel enforcepanel;
	
	public FilesPanel filespanel;	
		
	public ExecutePanel executepanel;
	
	public TitlePanel titlepanel;
	
	/**
	 * Create the frame for OLED.
	 */
	public TheFrame (RefOntoUML.Model model, String alsPath)
	{
		this();
		filespanel.load(model, alsPath);		
	}
	
	/**
	 * Create the frame.
	 */
	
	public TheFrame() 
	{							
		setTitle("OntoUML2Alloy");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 483, 466);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		filespanel = new FilesPanel();		
		tabbedPane.addTab("Files", null, filespanel, null);		
		
		enforcepanel = new EnforcePanel();
				
		tabbedPane.addTab("Enforce", null, enforcepanel, null);
		
		contentPane.add(BorderLayout.CENTER,tabbedPane);
				
		executepanel = new ExecutePanel();
		
		contentPane.add(BorderLayout.SOUTH,executepanel);
				
		titlepanel = new TitlePanel();		
		contentPane.add(BorderLayout.NORTH,titlepanel);
		
		String iconPath = "/resources/br/ufes/inf/nemo/ontouml2alloy/window.png";
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Image.class.getResource(iconPath)));
		
		executepanel.btnExecuteWithAnalyzer .addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{								
				ExecuteButtonActionPerformed (arg0);				
			}
		});
	}		


	public void ExecuteButtonActionPerformed (ActionEvent arg0)
	{
		dispose();
		
		try {
		
		Options opt = enforcepanel.getOptions();
						
		if (filespanel.txtOntoUML.isEnabled()) 
		{
			Resource resource = ResourceUtil.loadOntoUML(filespanel.getOntoUMLPath());
			filespanel.refmodel = (RefOntoUML.Package) resource.getContents().get(0);			
		}
				
		filespanel.setAlloyPath(filespanel.getAlloyPath());
		
		OntoUML2Alloy.Transformation(filespanel.refmodel, filespanel.getAlloyPath(), opt);

		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}		
	}
}