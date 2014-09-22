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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.explorer.CustomOntoUMLElement;

/**
 * @author John Guerson
 */
public class GeneralizationSetEditionPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private DiagramManager diagramManager;
	private GeneralizationSet genSet;
	private JTextField textField;
	@SuppressWarnings("unused")
	private Component parent;
	private JButton btnAdd;
	private JButton btnDelete;
	private JScrollPane scrollPane;
	private JCheckBox cbxDisjoint;
	private JCheckBox cbxCovering;
	private JLabel lblName;
	@SuppressWarnings("rawtypes")
	private DefaultListModel genModel;
	private JList<CustomOntoUMLElement> genList;
	private JLabel lblParticipatingGeneralizations;
	
	public GeneralizationSetEditionPanel(JDialog parent, final DiagramManager diagramManager, final GeneralizationSet genSet) 
	{		
		initData(parent,diagramManager,genSet);
		initGUI();		
	}
	
	/**
	 * @wbp.parser.constructor 
	 */
	public GeneralizationSetEditionPanel(JFrame parent, final DiagramManager diagramManager, final GeneralizationSet genSet) 
	{		
		initData(parent,diagramManager,genSet);
		initGUI();		
	}		
	
	public GeneralizationSetEditionPanel(final Component parent, final DiagramManager diagramManager, final GeneralizationSet genSet) 
	{
		initData(parent,diagramManager,genSet);
		initGUI();
	}
	
	public void initData(final Component parent,final DiagramManager diagramManager, GeneralizationSet genSet)
	{
		this.parent = parent;
		this.diagramManager = diagramManager;
		this.genSet = genSet;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initGUI()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(""));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createTitledBorder(""));
		
		genModel = new DefaultListModel();
		for(Generalization gen: genSet.getGeneralization())
		{
			genModel.addElement(new CustomOntoUMLElement(gen,""));
		}		
		genList = new JList<CustomOntoUMLElement>(genModel);
		
		scrollPane = new JScrollPane(genList);
		
		lblParticipatingGeneralizations = new JLabel("Participating generalizations:");
		
		btnAdd = new JButton("");		
		btnAdd.setIcon(new ImageIcon(GeneralizationSetEditionPanel.class.getResource("/resources/icons/x16/add.png")));
		btnAdd.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OntoUMLParser refparser = diagramManager.getFrame().getBrowserManager().getProjectBrowser().getParser();
				ArrayList<CustomOntoUMLElement> list = new ArrayList<CustomOntoUMLElement>();
				for(Generalization g: refparser.getAllInstances(Generalization.class))
				{
					if (!(genSet.getGeneralization().contains(g))) list.add(new CustomOntoUMLElement(g,""));
				}				
				if (list.size()==0) {
					JOptionPane.showMessageDialog(GeneralizationSetEditionPanel.this, "No generalization left in the model.", "Add", JOptionPane.INFORMATION_MESSAGE);
				}else{
					CustomOntoUMLElement selected = (CustomOntoUMLElement) JOptionPane.showInputDialog(GeneralizationSetEditionPanel.this, 
					        "Which generalization do you want to include in Generalization Set"+genSet.getName(),
					        "Add",
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        list.toArray(), 
					        list.toArray()[0]
					);
					if(selected!=null){
						genModel.addElement(selected);
					
						// add
						genSet.getGeneralization().add(((Generalization)selected.getElement()));
						((Generalization)selected.getElement()).getGeneralizationSet().add(genSet);
					}
				}				
			}
		});		
		
		btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(GeneralizationSetEditionPanel.class.getResource("/resources/icons/x16/cross.png")));
		btnDelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				CustomOntoUMLElement gen = (CustomOntoUMLElement)genList.getSelectedValue();
				
				//remove
				genSet.getGeneralization().remove(((Generalization)gen.getElement()));
				((Generalization)gen.getElement()).getGeneralizationSet().remove(genSet);
				
				genModel.removeElement(genList.getSelectedValue());				

			}
		});
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 324, Short.MAX_VALUE)
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addComponent(lblParticipatingGeneralizations, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblParticipatingGeneralizations, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(4))
		);
		panel_1.setLayout(gl_panel_1);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
					.addGap(9))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 204, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		lblName = new JLabel("Name:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		cbxCovering = new JCheckBox("Covering/Complete");		
		cbxDisjoint = new JCheckBox("Disjoint");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(cbxDisjoint)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cbxCovering))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxCovering)
						.addComponent(cbxDisjoint))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		setInitialData();
	}
	
	public class CustomComparator implements Comparator<CustomOntoUMLElement> 
    {
        @Override
        public int compare(CustomOntoUMLElement o1, CustomOntoUMLElement o2) {
            return o1.toString().compareToIgnoreCase(o2.toString());
        }
    }
	
	public void setInitialData()
	{
		cbxCovering.setSelected(genSet.isIsCovering());
		cbxDisjoint.setSelected(genSet.isIsDisjoint());
		textField.setText(genSet.getName());				
	}
	
	public void transferGenSetData()
	{
		boolean redesign = false;
		
		genSet.setIsCovering(cbxCovering.isSelected());
		genSet.setIsDisjoint(cbxDisjoint.isSelected());
		genSet.setName(textField.getText());	
		
		diagramManager.updateOLEDFromModification(genSet, redesign);
	}
	
}
