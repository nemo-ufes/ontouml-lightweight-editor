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

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.ontouml2alloy.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.util.ResourceUtil;

/**
 * This Frame was created using the Windows Builder in Eclipse. 
 *
 * 	@author John Guerson 
 *  @author Tiago Sales 
 *  @author Lucas Thom
 */

public class TheFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public JPanel contentPane;
	
	public JTabbedPane tabbedPane;
	
	public RulesPanel rpanel;
	
	public FilesPanel fpanel;	
		
	/**
	 * Create the frame for OLED.
	 */
	public TheFrame (RefOntoUML.Model model, String alsPath)
	{
		this();
		fpanel.configurePanelForOLED(model, alsPath);
	}
	
	/**
	 * Create the frame.
	 */
	
	public TheFrame() 
	{							
		setTitle("OntoUML2Alloy");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 478, 391);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		fpanel = new FilesPanel();		
		tabbedPane.addTab("Files", null, fpanel, null);
		
		rpanel = new RulesPanel();
		tabbedPane.addTab("Rules", null, rpanel, null);
		
		contentPane.add(tabbedPane);
		
		fpanel.btnBrowseOntoUML.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				BrowseOntoUMLActionPerformed(arg0);
			}
		});
		
		fpanel.btnExecute.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{								
				ExecuteButtonActionPerformed (arg0);				
			}
		});

		fpanel.btnBrowseAlloy.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				BrowseAlloyActionPerformed(arg0);
			}
		});		
				
		String iconPath = "/resources/br/ufes/inf/nemo/ontouml2alloy/window.png";
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Image.class.getResource(iconPath)));
	}
		
	/**
	 *	Action Performed for Browse OntoUML JButton in FilesPanel. 
	 */
	public void BrowseOntoUMLActionPerformed (ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open");
		FileNameExtensionFilter ontoumlFilter = new FileNameExtensionFilter(
				"Eclipse Ecore-based Model (*.refontouml)", "refontouml");
		fileChooser.addChoosableFileFilter(ontoumlFilter);
		fileChooser.setFileFilter(ontoumlFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == ontoumlFilter) 
			{				
				fpanel.txtOntoUML.setText( fileChooser.getSelectedFile().getPath() );
			}
		}
	}
	
	/**
	 *	Action Performed for Browse Alloy JButton in FilesPanel. 
	 */
	public void BrowseAlloyActionPerformed (ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open");
		FileNameExtensionFilter alloyFilter = new FileNameExtensionFilter(
				"Eclipse Ecore-based Model (*.als)", "als");
		fileChooser.addChoosableFileFilter(alloyFilter);
		fileChooser.setFileFilter(alloyFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == alloyFilter) 
			{				
				fpanel.txtAlloy.setText( fileChooser.getSelectedFile().getPath() );
			}
		}
	}
	
	/**
	 *	Action Performed for Execute JButton in FilesPanel. 	
	 */
	public void ExecuteButtonActionPerformed (ActionEvent arg0)
	{
		try {

		boolean weakSuppl = rpanel.cbxWeakSupplementation.isSelected();
		boolean relatorsRule = rpanel.cbxRelators.isSelected();
				
		if (fpanel.txtOntoUML.isEnabled()) 
		{
			Resource resource = ResourceUtil.loadOntoUML(fpanel.txtOntoUML.getText());
			fpanel.refmodel = (RefOntoUML.Model) resource.getContents().get(0);
			fpanel.alsPath = fpanel.txtAlloy.getText();
		}
				
		OntoUML2Alloy.Transformation(fpanel.refmodel, fpanel.alsPath, relatorsRule, weakSuppl,true);

		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(contentPane,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}
	}
	
}
