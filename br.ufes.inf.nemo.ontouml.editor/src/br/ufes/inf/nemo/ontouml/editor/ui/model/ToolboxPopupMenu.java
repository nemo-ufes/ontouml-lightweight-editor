package br.ufes.inf.nemo.ontouml.editor.ui.model;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ToolboxPopupMenu extends JPopupMenu {	

	private static final long serialVersionUID = 1L;
	
	JMenuItem kindItem = new JMenuItem("Kind");
	JMenuItem collectiveItem  = new JMenuItem("Collective");
	JMenuItem quantityItem = new JMenuItem("Quantity");
	JMenuItem subkindItem = new JMenuItem("SubKind");
	JMenuItem phaseItem = new JMenuItem("Phase");
	JMenuItem roleItem = new JMenuItem("Role");
	JMenuItem categoryItem = new JMenuItem("Category");
	JMenuItem rolemixinItem = new JMenuItem("RoleMixin");
	JMenuItem mixinItem = new JMenuItem("Mixin");	
	JMenuItem relatorItem = new JMenuItem("Relator");
	JMenuItem modeItem = new JMenuItem("Mode");
	
	JMenuItem mediationItem = new JMenuItem("Mediation");	
	JMenuItem materialItem = new JMenuItem("Material");
	JMenuItem formalItem = new JMenuItem("Formal");
	JMenuItem componentOfItem = new JMenuItem("ComponentOf");
	JMenuItem memberOfItem = new JMenuItem("MemberOf");
	JMenuItem associationItem = new JMenuItem("Association");
	JMenuItem characterizationtem = new JMenuItem("Characterization");
	JMenuItem subquantityOfItem = new JMenuItem("SubQuantityOf");
	JMenuItem subcollectionOfItem = new JMenuItem("SubCollectionOf");
	JMenuItem derivationItem = new JMenuItem("Derivation");
	
    public ToolboxPopupMenu()
    {        
        add(kindItem);
        add(collectiveItem);
        add(quantityItem);
        add(subkindItem);
        add(roleItem);
        add(phaseItem);
        add(categoryItem);        
        add(rolemixinItem);
        add(mixinItem);
        add(relatorItem);
        add(modeItem);
        
        addSeparator();
        
        add(materialItem);
        add(mediationItem);        
        add(derivationItem);
        add(formalItem);                
        add(componentOfItem);        
        add(memberOfItem);
        add(subquantityOfItem);
        add(subcollectionOfItem);        
        add(characterizationtem);
        add(associationItem);
    }	
}
