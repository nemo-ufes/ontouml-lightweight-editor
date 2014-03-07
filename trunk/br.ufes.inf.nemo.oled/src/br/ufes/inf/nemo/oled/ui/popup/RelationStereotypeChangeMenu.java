package br.ufes.inf.nemo.oled.ui.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Derivation;
import RefOntoUML.FormalAssociation;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.ui.DiagramEditorWrapper;

public class RelationStereotypeChangeMenu extends JMenu{

	private static final long serialVersionUID = 1L;
	
	public DiagramManager diagramManager;
	public RefOntoUML.Relationship type;
	
	public void setElement(RefOntoUML.Element element)
	{
		this.type = (RefOntoUML.Relationship)element;
	}
	
	public RelationStereotypeChangeMenu(final DiagramManager diagramManager)
	{
		super("Change to");
		
		this.diagramManager=diagramManager;
				
		JMenuItem mediationItem = new JMenuItem("Mediation");	
		JMenuItem materialItem = new JMenuItem("Material");
		JMenuItem formalItem = new JMenuItem("Formal");
		JMenuItem componentOfItem = new JMenuItem("ComponentOf");
		JMenuItem memberOfItem = new JMenuItem("MemberOf");
		JMenuItem associationItem = new JMenuItem("Association");
		JMenuItem characterizationItem = new JMenuItem("Characterization");
		JMenuItem subquantityOfItem = new JMenuItem("SubQuantityOf");
		JMenuItem subcollectionOfItem = new JMenuItem("SubCollectionOf");	
		JMenuItem derivationItem = new JMenuItem("Derivation");    			
		add(mediationItem);
		mediationItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!(type instanceof Mediation)) diagramManager.changeRelationStereotype((RefOntoUML.Relationship)type,"Mediation");
        	}
        });
		add(materialItem);
		materialItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!(type instanceof MaterialAssociation))diagramManager.changeRelationStereotype((RefOntoUML.Relationship)type,"Material");
        	}
        });
		add(formalItem);
		formalItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!(type instanceof FormalAssociation)) diagramManager.changeRelationStereotype((RefOntoUML.Relationship)type,"Formal");
        	}
        });
		add(componentOfItem);
		componentOfItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!(type instanceof componentOf)) diagramManager.changeRelationStereotype((RefOntoUML.Relationship)type,"ComponentOf");
        	}
        });
		add(memberOfItem);
		memberOfItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!(type instanceof memberOf)) diagramManager.changeRelationStereotype((RefOntoUML.Relationship)type,"MemberOf");
        	}
        });
        add(associationItem);
        associationItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!(type instanceof Association))diagramManager.changeRelationStereotype((RefOntoUML.Relationship)type,"Association");
        	}
        });
        add(characterizationItem);
        characterizationItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!(type instanceof Characterization))diagramManager.changeRelationStereotype((RefOntoUML.Relationship)type,"Characterization");
        	}
        });
        add(subquantityOfItem);
        subquantityOfItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!(type instanceof subQuantityOf)) diagramManager.changeRelationStereotype((RefOntoUML.Relationship)type,"SubQuantityOf");
        	}
        });
        add(subcollectionOfItem);
        subcollectionOfItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!(type instanceof subCollectionOf))diagramManager.changeRelationStereotype((RefOntoUML.Relationship)type,"SubCollectionOf");
        	}
        });
        add(derivationItem);
        derivationItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (!(type instanceof Derivation))diagramManager.changeRelationStereotype((RefOntoUML.Relationship)type,"Derivation");
        	}
        });       	        		
        associationItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/association-arrow.png")));
        materialItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/materialassociation.png")));
        formalItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/formalassociation.png")));
        characterizationItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/characterization.png")));
        mediationItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/mediation.png")));
        componentOfItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/componentof.png")));
        memberOfItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/memberof.png")));
        subcollectionOfItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/subcollectionof.png")));
        subquantityOfItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/subquantityof.png")));
        derivationItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/derivation.png")));	
        
	}
}