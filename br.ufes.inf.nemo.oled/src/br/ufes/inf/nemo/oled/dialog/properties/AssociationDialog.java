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
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Relationship;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;

/**
 * @author John Guerson
 */
public class AssociationDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private AssociationElement assocElement;
	private Relationship relationship;
	private DiagramManager diagramManager;
	@SuppressWarnings("unused")
	private JFrame parent;
	
	private JTabbedPane tabbedPane;
	private JButton btnConfirm;
	private JButton btnCancel;
	private AssociationEditionPanel assocEdition;
	private PropertyEditionPanel end1Edition;
	private PropertyEditionPanel end2Edition;
	private CommentsEditionPanel commentsEdition;
	private JPanel associationTab;
	private JPanel sourceTab;
	private JPanel targetTab;
	private JPanel commentTab;
	
	public void selectTab (int index)
	{
		tabbedPane.setSelectedIndex(index);
	}
	
	public AssociationDialog(final AppFrame parent, final AssociationElement assocElement, RefOntoUML.Relationship relationship, boolean modal) 
	{
		super(parent, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClassDialog.class.getResource("/resources/icons/x16/cog.png")));
		
//		Image icon = new BufferedImage(1, 1,BufferedImage.TYPE_INT_ARGB_PRE);
//		setIconImage(icon);
		
		this.diagramManager = parent.getDiagramManager();
		this.assocElement = assocElement;
		this.relationship = relationship;
		this.parent = parent;
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(""+""+getStereotype(relationship)+" "+ ((Classifier)relationship).getName());
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setPreferredSize(new Dimension(100, 50));
		
		btnConfirm = new JButton("Ok");		
		btnConfirm.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				okActionPerformed(e);
				dispose();
			}
		});
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		associationTab = new JPanel();
		tabbedPane.addTab("Association", null, associationTab, null);
		
		assocEdition = new AssociationEditionPanel (diagramManager,assocElement,(Classifier)relationship,modal);
		GroupLayout gl_associationTab = new GroupLayout(associationTab);
		gl_associationTab.setHorizontalGroup(
			gl_associationTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_associationTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(assocEdition, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_associationTab.setVerticalGroup(
			gl_associationTab.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_associationTab.createSequentialGroup()
					.addGap(10)
					.addComponent(assocEdition, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
					.addGap(10))
		);
		associationTab.setLayout(gl_associationTab);
		
		sourceTab = new JPanel();
		tabbedPane.addTab("Source End", null, sourceTab, null);
		end1Edition = new PropertyEditionPanel(this,diagramManager,assocElement,(Classifier)relationship,((Association)relationship).getMemberEnd().get(0));
		GroupLayout gl_sourceTab = new GroupLayout(sourceTab);
		gl_sourceTab.setHorizontalGroup(
			gl_sourceTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_sourceTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(end1Edition, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_sourceTab.setVerticalGroup(
			gl_sourceTab.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_sourceTab.createSequentialGroup()
					.addGap(10)
					.addComponent(end1Edition, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
					.addGap(10))
		);
		sourceTab.setLayout(gl_sourceTab);
		
		targetTab = new JPanel();
		tabbedPane.addTab("Target End", null, targetTab, null);
		end2Edition = new PropertyEditionPanel(this,diagramManager,assocElement,(Classifier)relationship,((Association)relationship).getMemberEnd().get(1));
		GroupLayout gl_targetTab = new GroupLayout(targetTab);
		gl_targetTab.setHorizontalGroup(
			gl_targetTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_targetTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(end2Edition, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_targetTab.setVerticalGroup(
			gl_targetTab.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_targetTab.createSequentialGroup()
					.addGap(10)
					.addComponent(end2Edition, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
					.addGap(10))
		);
		targetTab.setLayout(gl_targetTab);
		
		commentTab = new JPanel();
		tabbedPane.addTab("Comments", null, commentTab, null);
		commentsEdition = new CommentsEditionPanel (diagramManager,assocElement,(Classifier)relationship);
		GroupLayout gl_commentTab = new GroupLayout(commentTab);
		gl_commentTab.setHorizontalGroup(
			gl_commentTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_commentTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(commentsEdition, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_commentTab.setVerticalGroup(
			gl_commentTab.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_commentTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(commentsEdition, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
					.addContainerGap())
		);
		commentTab.setLayout(gl_commentTab);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(3)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 434, Short.MAX_VALUE)
					.addGap(1))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(3)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(308, Short.MAX_VALUE)
					.addComponent(btnConfirm)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnConfirm))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		gl_panel.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnConfirm, btnCancel});
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
//		tabbedPane.addTab("Constraints",constraintsEdition);
//		tabbedPane.addTab("Related Elements",relatedElements);
				
		setSize(new Dimension(470, 450));		
	}
		
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
		
	public void okActionPerformed(ActionEvent arg0)
	{		
		end1Edition.transferPropertyData();
		end2Edition.transferPropertyData();
		commentsEdition.transferCommentsData();
		//constraintsEdition.transferConstraintsData();
		assocEdition.transferAssocData();
		if(getStereotype(relationship).compareTo((String) assocEdition.stereoCombo.getSelectedItem())!=0)
		{
			diagramManager.changeRelationStereotype(relationship, (String) assocEdition.stereoCombo.getSelectedItem());
		}
	}	
}

