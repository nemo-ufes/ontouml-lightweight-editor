package br.ufes.inf.nemo.oled.popupmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.model.ElementType;

public class ElementCreationMenu extends JMenu{

	private static final long serialVersionUID = 3797953970276009760L;
	
	public DiagramManager diagramManager;
	public RefOntoUML.Element eContainer;
	
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
		add(packageItem);
        packageItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		diagramManager.addElement(ElementType.PACKAGE,eContainer);
        	}
        });
		add(kindItem);
		kindItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.KIND,eContainer);
			}
		});
		add(quantityItem);
		quantityItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.QUANTITY,eContainer);
			}
		});
		add(collectiveItem);        
		collectiveItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.COLLECTIVE,eContainer);
			}
		});
		add(subkindItem);        
		subkindItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.SUBKIND,eContainer);
			}
		});
		add(phaseItem);
		phaseItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.PHASE,eContainer);
			}
		});
		add(roleItem);
		roleItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.ROLE,eContainer);
			}
		});
		add(categoryItem);        
		categoryItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.CATEGORY,eContainer);
			}
		});
		add(rolemixinItem);
		rolemixinItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.ROLEMIXIN,eContainer);
			}
		});    		
		add(mixinItem);
		mixinItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.MIXIN,eContainer);
			}
		});  
		add(modeItem); 
		modeItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.MODE,eContainer);
			}
		});    		
		add(relatorItem);
		relatorItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.RELATOR,eContainer);
			}
		});    		
		add(datatypeItem);
		datatypeItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.DATATYPE,eContainer);
			}
		});
		add(enumItem);
		enumItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.ENUMERATION,eContainer);
			}			
		});
		add(primitiveItem);
		primitiveItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.PRIMITIVETYPE,eContainer);
			}			
		});
		add(perceivableItem);
		perceivableItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.PERCEIVABLEQUALITY,eContainer);
			}			
		});
		add(nonperceivableItem);
		nonperceivableItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.NONPERCEIVABLEQUALITY,eContainer);
			}			
		});		
		add(nominalItem);
		nominalItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				diagramManager.addElement(ElementType.NOMINALQUALITY,eContainer);
			}			
		});		
		add(genSetItem);
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
	}
}
