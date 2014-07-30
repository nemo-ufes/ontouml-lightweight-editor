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
import javax.swing.border.EmptyBorder;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Enumeration;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

/**
 * @author John Guerson
 */
public class ClassDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private ClassElement classElement;
	private Classifier element;
	private DiagramManager diagramManager;
	@SuppressWarnings("unused")
	private JFrame parent;
	
	private JTabbedPane tabbedPane;
	private JButton btnConfirm;
	private JButton btnCancel;
	private ClassEditionPanel classEdition;
	private CommentsEditionPanel commentsEdition;
	private AttributesEditionPanel attributesEdition;
	private EnumLiteralEditionPanel literalsEdition;
	private ConstraintEditionPanel constraintsEdition;
	private RelatedElementsPanel relatedElements;
	
	public void selectTab (int index)
	{
		tabbedPane.setSelectedIndex(index);
	}
	
	public ClassDialog(final AppFrame parent, final ClassElement classElement, Classifier element, boolean modal) 
	{
		super(parent, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClassDialog.class.getResource("/resources/icons/x16/cog.png")));
		
		this.diagramManager = parent.getDiagramManager();
		this.classElement = classElement;		
		this.element = element;
		this.parent = parent;
		
//		Image icon = new BufferedImage(1, 1,BufferedImage.TYPE_INT_ARGB_PRE);
//		setIconImage(icon);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(""+""+getStereotype(element)+" "+element.getName());
		
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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(309, Short.MAX_VALUE)
					.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
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
		panel.setLayout(gl_panel);
		
		classEdition = new ClassEditionPanel (diagramManager,classElement,element);
				
		JPanel classTab = new JPanel();
		classTab.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		tabbedPane.addTab("Class",classTab);		
		GroupLayout gl_classTab = new GroupLayout(classTab);
		
		if(element instanceof Enumeration){
			literalsEdition = new EnumLiteralEditionPanel(this,diagramManager,classElement,element);
			gl_classTab.setHorizontalGroup(
				gl_classTab.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_classTab.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_classTab.createParallelGroup(Alignment.LEADING)
							.addComponent(classEdition, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
							.addComponent(literalsEdition, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_classTab.setVerticalGroup(
				gl_classTab.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_classTab.createSequentialGroup()
						.addContainerGap()
						.addComponent(classEdition, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(literalsEdition, GroupLayout.PREFERRED_SIZE, 10, Short.MAX_VALUE)
						.addGap(10))
			);
			classTab.setLayout(gl_classTab);
		}else{
			attributesEdition = new AttributesEditionPanel(this,diagramManager,classElement,element);
			gl_classTab.setHorizontalGroup(
				gl_classTab.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_classTab.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_classTab.createParallelGroup(Alignment.LEADING)
							.addComponent(classEdition, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
							.addComponent(attributesEdition, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_classTab.setVerticalGroup(
				gl_classTab.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_classTab.createSequentialGroup()
						.addContainerGap()
						.addComponent(classEdition, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(attributesEdition, GroupLayout.PREFERRED_SIZE, 10, Short.MAX_VALUE)
						.addGap(10))
			);
			classTab.setLayout(gl_classTab);	
		}	
		
		JPanel commentTab = new JPanel();
		tabbedPane.addTab("Comments", null, commentTab, null);
		commentsEdition = new CommentsEditionPanel (diagramManager,classElement,element);
		GroupLayout gl_commentTab = new GroupLayout(commentTab);
		gl_commentTab.setHorizontalGroup(
			gl_commentTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_commentTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(commentsEdition, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_commentTab.setVerticalGroup(
			gl_commentTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_commentTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(commentsEdition, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
					.addContainerGap())
		);
		commentTab.setLayout(gl_commentTab);
		
		JPanel constraintTab = new JPanel();
		tabbedPane.addTab("Constraints", null, constraintTab, null);
		constraintsEdition = new ConstraintEditionPanel(diagramManager,classElement,element);
		GroupLayout gl_constraintTab = new GroupLayout(constraintTab);
		gl_constraintTab.setHorizontalGroup(
			gl_constraintTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_constraintTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(constraintsEdition, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_constraintTab.setVerticalGroup(
			gl_constraintTab.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_constraintTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(constraintsEdition, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
					.addContainerGap())
		);
		constraintTab.setLayout(gl_constraintTab);
		
		JPanel relatedElementsTab = new JPanel();
		tabbedPane.addTab("Related Elements", null, relatedElementsTab, null);
		relatedElements = new RelatedElementsPanel(diagramManager,classElement,element);
		GroupLayout gl_relatedElementsTab = new GroupLayout(relatedElementsTab);
		gl_relatedElementsTab.setHorizontalGroup(
			gl_relatedElementsTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_relatedElementsTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(relatedElements, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_relatedElementsTab.setVerticalGroup(
			gl_relatedElementsTab.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_relatedElementsTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(relatedElements, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
					.addContainerGap())
		);
		relatedElementsTab.setLayout(gl_relatedElementsTab);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 434, Short.MAX_VALUE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
					.addGap(1))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(3)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(2))
		);
		getContentPane().setLayout(groupLayout);
				
		setSize(new Dimension(500, 452));
		
		classEdition.selectNameText();
		if(element instanceof Enumeration) classEdition.stereoCombo.setEnabled(false);
	}
		
	public void refreshAttributesData()
	{		
		attributesEdition.refreshData();		
	}
	
	public void refreshLiteralsData()
	{
		literalsEdition.refreshData();
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
		commentsEdition.transferCommentsData();
		if(attributesEdition!=null) attributesEdition.transferAttributesData();		
		if(literalsEdition!=null) literalsEdition.transferLiteralData();
		constraintsEdition.transferConstraintsData();
		classEdition.transferClassData();				
		if(getStereotype(element).compareTo((String) classEdition.stereoCombo.getSelectedItem())!=0)
		{
			diagramManager.changeClassStereotype(element, (String) classEdition.stereoCombo.getSelectedItem());
		}		
	}
}
