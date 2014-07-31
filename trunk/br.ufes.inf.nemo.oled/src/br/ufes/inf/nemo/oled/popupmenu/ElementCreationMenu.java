package br.ufes.inf.nemo.oled.popupmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.model.ElementType;

public class ElementCreationMenu extends JMenu{

	private static final long serialVersionUID = 3797953970276009760L;
	
	public DiagramManager diagramManager;
	public RefOntoUML.Element eContainer;
	public HashMap<String,JMenuItem> elementsMap = new HashMap<String,JMenuItem>();
	
	public ElementCreationMenu(final DiagramManager diagramManager, RefOntoUML.Element eContainer)
	{		
		this();
		this.diagramManager=diagramManager;
		this.eContainer = eContainer;
	}
	
	public ElementCreationMenu()
	{
		super("Add Element");		
		JMenuItem packageItem = new JMenuItem("Package");
		JMenuItem kindItem = new JMenuItem("Kind");
		JMenuItem collectiveItem  = new JMenuItem("Collective");
		JMenuItem quantityItem = new JMenuItem("Quantity");
		JMenuItem subkindItem = new JMenuItem("SubKind");
		JMenuItem phaseItem = new JMenuItem("Phase");
		JMenuItem roleItem = new JMenuItem("Role");
		JMenuItem categoryItem = new JMenuItem("Category");
		JMenuItem rolemixinItem = new JMenuItem("Role Mixin");
		JMenuItem mixinItem = new JMenuItem("Mixin");	
		JMenuItem relatorItem = new JMenuItem("Relator");
		JMenuItem modeItem = new JMenuItem("Mode");	
		JMenuItem datatypeItem = new JMenuItem("Data Type");
		JMenuItem enumItem = new JMenuItem("Enumeration");
		JMenuItem primitiveItem = new JMenuItem("Primitive Type");
		JMenuItem perceivableItem = new JMenuItem("Perceivable Quality");
		JMenuItem nonperceivableItem = new JMenuItem("Non Perceivable Quality");
		JMenuItem nominalItem = new JMenuItem("Nominal Quality");
		JMenuItem genSetItem = new JMenuItem("Generalization Set");
		//add(packageItem);
		elementsMap.put("package", packageItem);
        packageItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		diagramManager.addElement(ElementType.PACKAGE,eContainer);
        	}
        });
		//add(kindItem);
        elementsMap.put("kind", kindItem);
		kindItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.KIND,eContainer);
			}
		});
		//add(quantityItem);
		elementsMap.put("quantity", quantityItem);
		quantityItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.QUANTITY,eContainer);
			}
		});
		//add(collectiveItem);
		elementsMap.put("collective", collectiveItem);       
		collectiveItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.COLLECTIVE,eContainer);
			}
		});
		//add(subkindItem);
		elementsMap.put("subkind", subkindItem);
		subkindItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.SUBKIND,eContainer);
			}
		});
		//add(phaseItem);
		elementsMap.put("phase", phaseItem);
		phaseItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.PHASE,eContainer);
			}
		});
		//add(roleItem);
		elementsMap.put("role", roleItem);
		roleItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.ROLE,eContainer);
			}
		});
		//add(categoryItem);
        elementsMap.put("category", categoryItem);     
		categoryItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.CATEGORY,eContainer);
			}
		});
		//add(rolemixinItem);
        elementsMap.put("rolemixin", rolemixinItem);
		rolemixinItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.ROLEMIXIN,eContainer);
			}
		});    		
		 //add(mixinItem);
        elementsMap.put("mixin", mixinItem);
		mixinItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.MIXIN,eContainer);
			}
		});  
		 //add(modeItem);
        elementsMap.put("mode", modeItem);        
		modeItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.MODE,eContainer);
			}
		});    		
		//add(relatorItem);
        elementsMap.put("relator", relatorItem);
		relatorItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.RELATOR,eContainer);
			}
		});    		
		//add(datatypeItem);
        elementsMap.put("datatype", datatypeItem);
		datatypeItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.DATATYPE,eContainer);
			}
		});
		//add(enumItem);
        elementsMap.put("enumeration", enumItem);
		enumItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.ENUMERATION,eContainer);
			}			
		});
		//add(primitiveItem);
        elementsMap.put("primitive type", primitiveItem);
		primitiveItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.PRIMITIVETYPE,eContainer);
			}			
		});
		//add(perceivableItem);
        elementsMap.put("perceivable quality", perceivableItem);
		perceivableItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.PERCEIVABLEQUALITY,eContainer);
			}			
		});
		 //add(nonperceivableItem);
        elementsMap.put("nonperceivable quality", nonperceivableItem);
		nonperceivableItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.NONPERCEIVABLEQUALITY,eContainer);
			}			
		});		
		//add(nominalItem);
        elementsMap.put("nominal quality", nominalItem);
		nominalItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.NOMINALQUALITY,eContainer);
			}			
		});		
		//add(genSetItem);
        elementsMap.put("generalization set", genSetItem);
		genSetItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.GENERALIZATIONSET,eContainer);
			}
		});
		packageItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/package.png")));
        kindItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/kind.png")));
        quantityItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/quantity.png")));
        collectiveItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/collective.png")));
        subkindItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/subkind.png")));
        phaseItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/phase.png")));
        roleItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/role.png")));
        categoryItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/category.png")));
        rolemixinItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/rolemixin.png")));
        mixinItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/mixin.png")));
        modeItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/mode.png")));
        relatorItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/relator.png")));
        datatypeItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/datatype.png")));
        enumItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/datatype.png")));
        primitiveItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/datatype.png")));
        perceivableItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/datatype.png")));
        nonperceivableItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/datatype.png")));
        nominalItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/datatype.png")));
        genSetItem.setIcon(new ImageIcon(ElementCreationMenu.class.getResource("/resources/icons/x16/tree/generalization.png")));
        
        sort();
  	}
  	
  	public void sort()
  	{
  		ArrayList<JMenuItem> result = sort(elementsMap.values());		
  		for(JMenuItem pe: result){
  			add(pe);			
  		}
  	}
  	
  	class JMenuItemComparator implements Comparator<JMenuItem> 
      {
          @Override
          public int compare(JMenuItem o1, JMenuItem o2) {        	
          	return o1.getText().compareToIgnoreCase(o2.getText());
          }
      }
  	
  	public ArrayList<JMenuItem> sort(Collection<JMenuItem> list)
  	{
  		ArrayList<JMenuItem> result = new ArrayList<JMenuItem>();
  		result.addAll(list);
  		Collections.sort(result,new JMenuItemComparator());
  		return result;
  	}
}
