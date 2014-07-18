package br.ufes.inf.nemo.oled.popupmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.model.RelationType;

public class RelationCreationMenu  extends JMenu{

	private static final long serialVersionUID = 3797953970276009760L;
	
	public DiagramManager diagramManager;
	public RefOntoUML.Element eContainer;
	
	public RelationCreationMenu(final DiagramManager diagramManager, RefOntoUML.Element eContainer)
	{		
		this();
		this.diagramManager=diagramManager;
		this.eContainer = eContainer;
	}
	
	public RelationCreationMenu()
	{
		super("Add Relation");
		JMenuItem mediationItem = new JMenuItem("Mediation");	
		JMenuItem materialItem = new JMenuItem("Material");
		JMenuItem formalItem = new JMenuItem("Formal");
		JMenuItem componentOfItem = new JMenuItem("ComponentOf");
		JMenuItem memberOfItem = new JMenuItem("MemberOf");
		JMenuItem structurationItem = new JMenuItem("Structuration");
		JMenuItem associationItem = new JMenuItem("Association");
		JMenuItem characterizationItem = new JMenuItem("Characterization");
		JMenuItem subquantityOfItem = new JMenuItem("SubQuantityOf");
		JMenuItem subcollectionOfItem = new JMenuItem("SubCollectionOf");	
		JMenuItem derivationItem = new JMenuItem("Derivation");
		add(materialItem);
		materialItem.addActionListener(new ActionListener() {				
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		diagramManager.addRelation(RelationType.MATERIAL,eContainer);
	    	}
	    });
		add(formalItem);
		formalItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		diagramManager.addRelation(RelationType.FORMAL,eContainer);
        	}
        });    			
		add(characterizationItem);
		characterizationItem.addActionListener(new ActionListener() {				
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		diagramManager.addRelation(RelationType.CHARACTERIZATION,eContainer);
	        	}
	        });    			
		add(mediationItem);
		mediationItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		diagramManager.addRelation(RelationType.MEDIATION,eContainer);
        	}
        });
		add(componentOfItem);
		componentOfItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		diagramManager.addRelation(RelationType.COMPONENTOF,eContainer);
        	}
        });
		add(memberOfItem);        
		memberOfItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		diagramManager.addRelation(RelationType.MEMBEROF,eContainer);
        	}
        });
		add(subcollectionOfItem);
		subcollectionOfItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		diagramManager.addRelation(RelationType.SUBCOLLECTIONOF,eContainer);
        	}
        });    			
		add(subquantityOfItem);
		subquantityOfItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		diagramManager.addRelation(RelationType.SUBQUANTITYOF,eContainer);
        	}
        });    	
		add(structurationItem);
		structurationItem.addActionListener(new ActionListener() {				
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		diagramManager.addRelation(RelationType.STRUCTURATION,eContainer);
	    	}
	    });
		add(associationItem);
		associationItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		diagramManager.addRelation(RelationType.ASSOCIATION,eContainer);
        	}
        });  
		add(derivationItem);
		derivationItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		diagramManager.addRelation(RelationType.DERIVATION,eContainer);
        	}
        });      			
		associationItem.setIcon(new ImageIcon(RelationCreationMenu.class.getResource("/resources/icons/x16/tree/association-arrow.png")));
        materialItem.setIcon(new ImageIcon(RelationCreationMenu.class.getResource("/resources/icons/x16/tree/materialassociation.png")));
        formalItem.setIcon(new ImageIcon(RelationCreationMenu.class.getResource("/resources/icons/x16/tree/formalassociation.png")));
        characterizationItem.setIcon(new ImageIcon(RelationCreationMenu.class.getResource("/resources/icons/x16/tree/characterization.png")));
        mediationItem.setIcon(new ImageIcon(RelationCreationMenu.class.getResource("/resources/icons/x16/tree/mediation.png")));
        componentOfItem.setIcon(new ImageIcon(RelationCreationMenu.class.getResource("/resources/icons/x16/tree/componentof.png")));
        memberOfItem.setIcon(new ImageIcon(RelationCreationMenu.class.getResource("/resources/icons/x16/tree/memberof.png")));
        structurationItem.setIcon(new ImageIcon(RelationCreationMenu.class.getResource("/resources/icons/x16/tree/structuration.png")));
        subcollectionOfItem.setIcon(new ImageIcon(RelationCreationMenu.class.getResource("/resources/icons/x16/tree/subcollectionof.png")));
        subquantityOfItem.setIcon(new ImageIcon(RelationCreationMenu.class.getResource("/resources/icons/x16/tree/subquantityof.png")));
        derivationItem.setIcon(new ImageIcon(RelationCreationMenu.class.getResource("/resources/icons/x16/tree/derivation.png")));	
	}
}
