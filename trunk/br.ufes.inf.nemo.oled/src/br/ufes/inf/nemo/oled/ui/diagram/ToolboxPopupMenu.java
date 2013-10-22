package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import br.ufes.inf.nemo.oled.ui.AppFrame;
import br.ufes.inf.nemo.oled.ui.DiagramEditorWrapper;

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
	JMenuItem datatypeItem = new JMenuItem("DataType");
	JMenuItem genItem = new JMenuItem("Generalization");
	JMenuItem mediationItem = new JMenuItem("Mediation");	
	JMenuItem materialItem = new JMenuItem("Material");
	JMenuItem formalItem = new JMenuItem("Formal");
	JMenuItem componentOfItem = new JMenuItem("ComponentOf");
	JMenuItem memberOfItem = new JMenuItem("MemberOf");
	JMenuItem associationItem = new JMenuItem("Association");
	JMenuItem characterizationItem = new JMenuItem("Characterization");
	JMenuItem subquantityOfItem = new JMenuItem("SubQuantityOf");
	JMenuItem subcollectionOfItem = new JMenuItem("SubCollectionOf");	
	public AppFrame frame;
	
    public ToolboxPopupMenu(final AppFrame frame)
    {        
    	this.frame = frame;
    	add(kindItem);
    	add(quantityItem);
    	add(collectiveItem);        
        add(subkindItem);        
        add(phaseItem);     
        add(roleItem);              
        add(categoryItem);        
        add(rolemixinItem);
        add(mixinItem);    
        add(modeItem); 
        add(relatorItem);
        add(datatypeItem);        
        add(genItem);
        add(materialItem);
        add(formalItem);
        add(characterizationItem);
        add(mediationItem);        
        add(componentOfItem);        
        add(memberOfItem);        
        add(subcollectionOfItem);
        add(subquantityOfItem);
        add(associationItem);
        
        kindItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/class.png")));
        quantityItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/class.png")));
        collectiveItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/class.png")));
        subkindItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/class.png")));
        phaseItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/class.png")));
        roleItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/class.png")));
        categoryItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/class.png")));
        rolemixinItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/class.png")));
        mixinItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/class.png")));
        modeItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/class.png")));
        relatorItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/class.png")));
        datatypeItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/class.png")));
        genItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/inheritance.png")));
        associationItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/association-arrow.png")));
        materialItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/association.png")));
        formalItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/association.png")));
        characterizationItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/association.png")));
        mediationItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/association.png")));
        componentOfItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/aggregation.png")));
        memberOfItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/aggregation-m.png")));
        subcollectionOfItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/aggregation-c.png")));
        subquantityOfItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/aggregation-q.png")));
        
        kindItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{       			
       			frame.getToolManager().getOpenPalette().getPalleteElement("kind").setSelected(true);
       		}
		});
        quantityItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getToolManager().getOpenPalette().getPalleteElement("quantity").setSelected(true);
       		}
		});
        collectiveItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getToolManager().getOpenPalette().getPalleteElement("collective").setSelected(true);
       		}
		});
        subkindItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getToolManager().getOpenPalette().getPalleteElement("subkind").setSelected(true);
       		}
		});
        phaseItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getToolManager().getOpenPalette().getPalleteElement("phase").setSelected(true);
       		}
		});
        roleItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getToolManager().getOpenPalette().getPalleteElement("role").setSelected(true);
       		}
		});
        categoryItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getToolManager().getOpenPalette().getPalleteElement("category").setSelected(true);
       		}
		});
        rolemixinItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getToolManager().getOpenPalette().getPalleteElement("rolemixin").setSelected(true);
       		}
		});
        mixinItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getToolManager().getOpenPalette().getPalleteElement("mixin").setSelected(true);
       		}
		});
        modeItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getToolManager().getOpenPalette().getPalleteElement("mode").setSelected(true);
       		}
		});
        relatorItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getToolManager().getOpenPalette().getPalleteElement("relator").setSelected(true);
       		}
		});
        datatypeItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getToolManager().getOpenPalette().getPalleteElement("datatype").setSelected(true);
       		}
		});
        genItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getToolManager().getOpenPalette().getPalleteElement("generalization").setSelected(true);
       		}
		});
        materialItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getToolManager().getOpenPalette().getPalleteElement("material").setSelected(true);
       		}
		});
       formalItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			frame.getToolManager().getOpenPalette().getPalleteElement("formal").setSelected(true);
       		}
		});
       characterizationItem.addActionListener(new ActionListener() 
		{
      		public void actionPerformed(ActionEvent event) 
      		{
      			frame.getToolManager().getOpenPalette().getPalleteElement("characterization").setSelected(true);
      		}
		});
       mediationItem.addActionListener(new ActionListener() 
		{
     		public void actionPerformed(ActionEvent event) 
     		{
     			frame.getToolManager().getOpenPalette().getPalleteElement("mediation").setSelected(true);
     		}
		});
       componentOfItem.addActionListener(new ActionListener() 
		{
    		public void actionPerformed(ActionEvent event) 
    		{
    			frame.getToolManager().getOpenPalette().getPalleteElement("componentof").setSelected(true);
    		}
		});
       memberOfItem.addActionListener(new ActionListener() 
		{
   		public void actionPerformed(ActionEvent event) 
   		{
   			frame.getToolManager().getOpenPalette().getPalleteElement("memberof").setSelected(true);
   		}
		});
       subcollectionOfItem.addActionListener(new ActionListener() 
		{
   		public void actionPerformed(ActionEvent event) 
   		{
   			frame.getToolManager().getOpenPalette().getPalleteElement("subcollectionof").setSelected(true);
   		}
		});
       subquantityOfItem.addActionListener(new ActionListener() 
		{
   		public void actionPerformed(ActionEvent event) 
   		{
   			frame.getToolManager().getOpenPalette().getPalleteElement("subquantityof").setSelected(true);
   		}
		});
       associationItem.addActionListener(new ActionListener() 
		{
   		public void actionPerformed(ActionEvent event) 
   		{
   			frame.getToolManager().getOpenPalette().getPalleteElement("association").setSelected(true);
   		}
		});        
    }	
    
    
}
