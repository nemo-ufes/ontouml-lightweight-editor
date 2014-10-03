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
package br.ufes.inf.nemo.oled.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.dialog.properties.ConstraintSimulationPanel;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;
import br.ufes.inf.nemo.tocl.parser.TOCLParser;
import br.ufes.inf.nemo.tocl.tocl2alloy.TOCL2AlloyOption;

/**
 * @author John Guerson
 */

public class AlloySettingsDialog extends JDialog {
	
	private static final long serialVersionUID = 7877781445149017806L;
	
	private ConstraintSimulationPanel constraintSimulationPanel;
	private ModelSimulationPanel modelSimulationPanel;	
	private AppFrame frame;
	private JButton btnOk;	
	private JButton btnCancel;	
	private JPanel btnpanel;	
	
	private TOCLParser toclparser;
	/**
	 * Launch the Dialog.
	 */
	public static void open(OntoUML2AlloyOptions refOptions, TOCL2AlloyOption oclOptions, AppFrame frame)
	{
		try {
			
			AlloySettingsDialog dialog = new AlloySettingsDialog(refOptions,oclOptions,frame);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(frame);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructor.
	 * 
	 * @param refOptions
	 * @param oclOptions
	 * @param frame
	 * @wbp.parser.constructor
	 */
	public AlloySettingsDialog(OntoUML2AlloyOptions refOptions, TOCL2AlloyOption oclOptions, AppFrame frame)
	{
		this(frame);
		this.frame = frame;		
		this.toclparser = oclOptions.getParser();
		
		setOptionDialog(refOptions,oclOptions);
	}
	
	/**
	 * Set Options Dialog.
	 * 
	 * @param refOptions
	 * @param oclOptions
	 */
	public void setOptionDialog (OntoUML2AlloyOptions refOptions, TOCL2AlloyOption oclOptions)
	{
		modelSimulationPanel.setOntoUMLOptionsPane(refOptions,frame);				
		constraintSimulationPanel.setOCLOptionPane(oclOptions,frame);

		if (oclOptions.getConstraintList().size()>0) {
			getContentPane().add(constraintSimulationPanel, BorderLayout.CENTER);
			setSize(new Dimension(536, 530));
		}else{
			setSize(new Dimension(536, 258));
		}		
		
		invalidate();
	}
	
	/**
	 * Constructor
	 */
	public AlloySettingsDialog(AppFrame frame) 
	{
		super(frame);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AlloySettingsDialog.class.getResource("/resources/icons/x16/alloy/alloy.png")));
		setTitle("Simulation & Checking: Alloy");
		setSize(new Dimension(536, 258));
		
		btnOk = new JButton("OK");	
		btnOk.setPreferredSize(new Dimension(70, 25));
		btnOk.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			OkActionPerformed(event);
       		}
       	});
		
		btnCancel = new JButton("Cancel");
		btnCancel.setPreferredSize(new Dimension(90, 25));
		btnCancel.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			dispose();
       		}
       	});
		
		btnpanel = new JPanel();
		btnpanel.setBorder(BorderFactory.createTitledBorder(""));
		btnpanel.setPreferredSize(new Dimension(100, 40));
		btnpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnpanel.add(btnOk);
		btnpanel.add(btnCancel);
				
		modelSimulationPanel = new ModelSimulationPanel();		
		constraintSimulationPanel = new ConstraintSimulationPanel();
		getContentPane().setLayout(new BorderLayout(5, 5));
		
		getContentPane().add(modelSimulationPanel, BorderLayout.NORTH);
		//getContentPane().add(constraintSimulationPanel, BorderLayout.CENTER);
		getContentPane().add(btnpanel, BorderLayout.SOUTH);
	}
			
	public void OkActionPerformed(ActionEvent event)
	{
		OntoUML2AlloyOptions ontoumlOptions = new OntoUML2AlloyOptions();
		ontoumlOptions.antiRigidity = modelSimulationPanel.isSelectedAntirigidity(); 
		ontoumlOptions.identityPrinciple = modelSimulationPanel.isSelectedIdentityPrinciple();
		ontoumlOptions.weakSupplementation = modelSimulationPanel.isSelectedWeakSupplementation();
		ontoumlOptions.relatorConstraint = modelSimulationPanel.isSelectedRelatorConstraint();			    	
		
		frame.getProjectBrowser().setOntoUMLOption(ontoumlOptions);
				
		TOCL2AlloyOption oclOptions = new TOCL2AlloyOption(toclparser);		
		oclOptions.setTransformationType(constraintSimulationPanel.getTransformationsTypesListSelected());
    	oclOptions.setCommandScope(constraintSimulationPanel.getScopesListSelected());    			
    	oclOptions.setBiwidth(constraintSimulationPanel.getBitWidthListSelected());
    	oclOptions.setWorldScope(constraintSimulationPanel.getWorldScopeListSelected());
		oclOptions.setConstraintList(constraintSimulationPanel.getConstraintListSelected());
    	
		frame.getProjectBrowser().setOCLOption(oclOptions);
		  
//    	dispose();
    
    	frame.getDiagramManager().transformToAlloy();		  		
	}
	
	public AppFrame getFrame()
	{
		return frame;
	}
}
