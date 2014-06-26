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
package br.ufes.inf.nemo.oled.popupmenu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditorWrapper;

/**
 * @author John Guerson
 */
public class ToolboxPopupMenu extends JPopupMenu {	

	private static final long serialVersionUID = 1L;
	
	JMenuItem pointerItem = new JMenuItem("Pointer");
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
	JMenuItem derivationItem = new JMenuItem("Derivation");
	JMenuItem unionItem = new JMenuItem("Union");	
	JMenuItem exclusionItem = new JMenuItem("Exclusion");
	JMenuItem specializationItem = new JMenuItem("Specialization");
	JMenuItem intersectionItem = new JMenuItem("Intersection");
	JMenuItem pastspecializationItem = new JMenuItem("PastSpecialization");
	JMenuItem participationItem = new JMenuItem("Participation");
	
	public AppFrame frame;
	public double x;
	public double y;
	
    public ToolboxPopupMenu(final AppFrame frame, final double x, final double y)
    {        
    	this.frame = frame;
    	this.x=x;
    	this.y=y;
    	add(pointerItem);
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
        add(derivationItem);
        
        addSeparator();
        add(unionItem);
        add(exclusionItem);
        add(intersectionItem);
        add(pastspecializationItem);
        add(participationItem);
        
        kindItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/kind.png")));
        quantityItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/quantity.png")));
        collectiveItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/collective.png")));
        subkindItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/subkind.png")));
        phaseItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/phase.png")));
        roleItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/role.png")));
        categoryItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/category.png")));
        rolemixinItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/rolemixin.png")));
        mixinItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/mixin.png")));
        modeItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/mode.png")));
        relatorItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/relator.png")));
        datatypeItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/datatype.png")));
        genItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/generalization.png")));
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
        
        unionItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/sitemap.png")));        
        exclusionItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/sitemap.png")));
        intersectionItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/sitemap.png")));
        
        pointerItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/mousepointer.png")));
        
        kindItem.addMouseListener(new MouseAdapter()
	    {
    			@Override
    			public void mousePressed(MouseEvent e) 
    			{    				
    			    if (SwingUtilities.isLeftMouseButton(e))
    	            {	            	     	
    			    	frame.getToolManager().getElementsPalette().getPalleteElement("kind").setSelected(true);             
    	            }
    			}	       
		});     
        quantityItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("quantity").setSelected(true);
	            }
       		}
		});
        collectiveItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("collective").setSelected(true);
	            }
       		}
		});
        subkindItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("subkind").setSelected(true);
	            }
       		}
		});
        phaseItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("phase").setSelected(true);
	            }
       		}
		});
        roleItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("role").setSelected(true);
	            }
       		}
		});
        categoryItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("category").setSelected(true);
	            }
       		}
		});
        rolemixinItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("rolemixin").setSelected(true);
	            }
       		}
		});
        mixinItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("mixin").setSelected(true);
	            }
       		}
		});
        modeItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("mode").setSelected(true);
	            }
       		}
		});
        relatorItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("relator").setSelected(true);
	            }
       		}
		});
        datatypeItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("datatype").setSelected(true);
	            }
       		}
		});
        genItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("generalization").setSelected(true);
	            }
       		}
		});
        materialItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("material").setSelected(true);
	            }
       		}
		});
       formalItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("formal").setSelected(true);
	            }
       		}
		});
       characterizationItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("characterization").setSelected(true);
	            }
      		}
		});
       mediationItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("mediation").setSelected(true);
	            }
     		}
		});
       componentOfItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("componentof").setSelected(true);
	            }
    		}
		});
       memberOfItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("memberof").setSelected(true);	
	            }
			}
		});
       subcollectionOfItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("subcollectionof").setSelected(true);
	            }
			}
		});
       subquantityOfItem.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("subquantityof").setSelected(true);
	            }
			}
		});
       associationItem.addMouseListener(new MouseAdapter()
	    {    	   
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("association").setSelected(true);
	            }
			}
		});
       derivationItem.addMouseListener(new MouseAdapter()
	    {    	   
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getToolManager().getElementsPalette().getPalleteElement("derivation").setSelected(true);
	            }
			}
		});
       unionItem.addMouseListener(new MouseAdapter()
	    {    	   
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getDiagramManager().openDerivedTypePatternUnion(x, y);
	            }
			}
		});
       intersectionItem.addMouseListener(new MouseAdapter()
	    {    	   
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getDiagramManager().openDerivedTypePatternIntersection(x, y);
	            }
			}
		});
       
       exclusionItem.addMouseListener(new MouseAdapter()
	    {    	   
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getDiagramManager().openDerivedTypePatternExclusion(x,y);
	            }
			}
		});
       
       specializationItem.addMouseListener(new MouseAdapter()
	    {    	   
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getDiagramManager().openDerivedTypePatternSpecialization(x,y);
	            }
			}
		});
       
       pastspecializationItem.addMouseListener(new MouseAdapter()
	    {    	   
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getDiagramManager().openDerivedTypePatternPastSpecialization(x,y);
	            }
			}
		});
       
       participationItem.addMouseListener(new MouseAdapter()
	    {    	   
			@Override
			public void mousePressed(MouseEvent e) 
			{			
			    if (SwingUtilities.isLeftMouseButton(e))
	            {
			    	frame.getDiagramManager().openDerivedTypePatternParticipation(x,y);
	            }
			}
		});
       
       pointerItem.addMouseListener(new MouseAdapter()
 	    {    	   
 			@Override
 			public void mousePressed(MouseEvent e) 
 			{			
 			    if (SwingUtilities.isLeftMouseButton(e))
 	            {
 			    	frame.getToolManager().getElementsPalette().getPalleteElement("select").setSelected(true);
 	            }
 			}
 		});
    }	
    
    
}
