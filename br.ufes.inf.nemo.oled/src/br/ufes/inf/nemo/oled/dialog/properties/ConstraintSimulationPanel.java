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
package br.ufes.inf.nemo.oled.dialog.properties;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.dialog.SingleConstraintPanel;
import br.ufes.inf.nemo.tocl.tocl2alloy.TOCL2AlloyOption;

/**
 * @author John Guerson
 */

public class ConstraintSimulationPanel extends JPanel {

	private static final long serialVersionUID = 566520388850119106L;

	@SuppressWarnings("unused")
	private TOCL2AlloyOption oclOptions;
	
	private AppFrame frame;	
	
	private JPanel ctpanel;
	private JScrollPane scrollPane; 
	private ArrayList<SingleConstraintPanel> singleConstraintsListPanel;
	private JButton btnDisableAll;
	private JButton btnEnableAll;
	private JLabel lblChooseWhichConstraints;
	private JButton btndefault;

	public ConstraintSimulationPanel (TOCL2AlloyOption oclOptions,AppFrame frame)
	{		
		this();
	
		this.frame = frame;
		this.oclOptions = oclOptions;
		
		setOCLOptionPane(oclOptions,frame);
	}
	
	public void setOCLOptionPane (TOCL2AlloyOption oclOptions, AppFrame frame)
	{
		this.frame=frame;
		ctpanel.removeAll();
		this.oclOptions = oclOptions;
		
		if (oclOptions.getConstraintList().size()<4)
		{
			ctpanel.setLayout(new GridLayout(4, 1, 0, 0));			
		} else {
			ctpanel.setLayout(new GridLayout(oclOptions.getConstraintList().size(), 1, 0, 0));			
		}
		
		for(Constraint ct : oclOptions.getConstraintList())
		{
			SingleConstraintPanel singleConstraint = new SingleConstraintPanel(ct,oclOptions.getConstraintType(ct),
			oclOptions.getParser().getUMLEnvironment());			
			singleConstraintsListPanel.add(singleConstraint);
			ctpanel.add(singleConstraint);			
		}		
		
		btnEnableAll.setEnabled(true);
		btnDisableAll.setEnabled(true);
		btndefault.setEnabled(true);
		
		invalidate();
	}
		
	/**
	 * Get a list of Scopes from Options View.
	 * 
	 * @return
	 */
	public ArrayList<Integer> getScopesListSelected()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(SingleConstraintPanel singleCt: singleConstraintsListPanel)
		{
			if (singleCt.checkEnforce.isSelected())
				list.add((Integer)singleCt.spinCommandScope.getValue());
		}
		return list;
	}
	
	public ArrayList<Integer> getWorldScopeListSelected()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(SingleConstraintPanel singleCt: singleConstraintsListPanel)
		{
			if (singleCt.checkEnforce.isSelected())
				list.add((Integer)singleCt.worldSpinner.getValue());
		}
		return list;
	}
	
	public ArrayList<Integer> getBitWidthListSelected()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(SingleConstraintPanel singleCt: singleConstraintsListPanel)
		{
			if (singleCt.checkEnforce.isSelected())
				list.add((Integer)singleCt.bitSpinner.getValue());
		}
		return list;
	}
	
	public ArrayList<Boolean> getSelectionList()
	{
		ArrayList<Boolean> list = new ArrayList<Boolean>();
		for(SingleConstraintPanel singleCt: singleConstraintsListPanel)
		{
			if (singleCt.checkEnforce.isSelected()) list.add(true);
		}
		return list;		
	}
	
	/**
	 * Get a list of Transformation Types from Options Pane. i.e. if it is "FACT","SIMULATION" OR "ASSERTION"
	 * 
	 * @return
	 */
	public ArrayList<String> getTransformationsTypesListSelected()
	{
		ArrayList<String> list = new ArrayList<String>();
		for(SingleConstraintPanel singleCt: singleConstraintsListPanel)
		{
			if (singleCt.checkEnforce.isSelected())
				list.add((String)singleCt.comboTransformationType.getSelectedItem());
		}
		return list;
	}
	
	/**
	 * Get a list of Constraint from Options Pane.
	 * 
	 * @return
	 */
	public ArrayList<Constraint> getConstraintListSelected()
	{
		ArrayList<Constraint> list = new ArrayList<Constraint>();
		for(SingleConstraintPanel singleCt: singleConstraintsListPanel)
		{
			if (singleCt.checkEnforce.isSelected())
				list.add((Constraint)singleCt.constraint);
		}
		return list;
	}
			
	/**
	 * Create an Empty Pane for OCL Options.
	 */
	public ConstraintSimulationPanel() 
	{
		setBorder(BorderFactory.createTitledBorder("Constraints"));		
		setPreferredSize(new Dimension(500, 260));
		
		ctpanel = new JPanel();		
		ctpanel.setLayout(new GridLayout(4, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createTitledBorder(""));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollPane.setViewportView(ctpanel);
		scrollPane.setPreferredSize(new Dimension(591,179));
				
		singleConstraintsListPanel = new ArrayList<SingleConstraintPanel>();
		
		lblChooseWhichConstraints = new JLabel("Choose which constraints do you want to simulate and check.");
		
		btnEnableAll = new JButton("Enable All");	
		btnEnableAll.setPreferredSize(new Dimension(100, 25));
		btnEnableAll.setEnabled(false);
		
		btnEnableAll.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				for(SingleConstraintPanel singleCt : singleConstraintsListPanel)
				{
					singleCt.checkEnforce.setSelected(true);
				}
			}
		});
		btnDisableAll = new JButton("Disable All");
		btnDisableAll.setPreferredSize(new Dimension(100, 25));
		btnDisableAll.setEnabled(false);
		
		btnDisableAll.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				for(SingleConstraintPanel singleCt : singleConstraintsListPanel)
				{
					singleCt.checkEnforce.setSelected(false);
				}
			}
		});
		
		btndefault = new JButton("Default");
		btndefault.setEnabled(false);
		btndefault.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
						.addComponent(lblChooseWhichConstraints, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btndefault)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEnableAll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDisableAll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblChooseWhichConstraints)
					.addGap(5)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEnableAll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btndefault, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDisableAll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10))
		);
		setLayout(groupLayout);
	}			
	
	/**
	 * Get the main Frame of Application.
	 * 
	 * @return
	 */
	public AppFrame getFrame()
	{
		return frame;
	}

}
