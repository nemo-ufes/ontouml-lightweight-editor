package br.ufes.inf.nemo.oled.ui.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.ui.AppFrame;
import br.ufes.inf.nemo.oled.ui.DiagramEditorWrapper;
import br.ufes.inf.nemo.oled.ui.OntoUMLElement;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.ProjectTree;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;

public class TreePopupMenu extends JPopupMenu {
 
	private static final long serialVersionUID = 1L;
		
	public JMenuItem deleteItem = new JMenuItem("Delete");
	public JMenuItem autoCompleteItem = new JMenuItem("Complete selection");
	public JMenuItem refreshItem = new JMenuItem("Refresh");
	public JMenuItem addDiagramItem = new JMenuItem("Add Diagram");
	public JMenuItem moveToDiagramItem = new JMenuItem("Move to Diagram");
	
	public JMenu addElementMenu = new JMenu("Add Element");
	public JMenuItem packageItem = new JMenuItem("Package");
	public JMenuItem kindItem = new JMenuItem("Kind");
	public JMenuItem collectiveItem  = new JMenuItem("Collective");
	public JMenuItem quantityItem = new JMenuItem("Quantity");
	public JMenuItem subkindItem = new JMenuItem("SubKind");
	public JMenuItem phaseItem = new JMenuItem("Phase");
	public JMenuItem roleItem = new JMenuItem("Role");
	public JMenuItem categoryItem = new JMenuItem("Category");
	public JMenuItem rolemixinItem = new JMenuItem("RoleMixin");
	public JMenuItem mixinItem = new JMenuItem("Mixin");	
	public JMenuItem relatorItem = new JMenuItem("Relator");
	public JMenuItem modeItem = new JMenuItem("Mode");	
	public JMenuItem datatypeItem = new JMenuItem("DataType");
		
	public JMenu addRelationMenu = new JMenu("Add Relation");
	public JMenuItem genItem = new JMenuItem("Generalization");
	public JMenuItem mediationItem = new JMenuItem("Mediation");	
	public JMenuItem materialItem = new JMenuItem("Material");
	public JMenuItem formalItem = new JMenuItem("Formal");
	public JMenuItem componentOfItem = new JMenuItem("ComponentOf");
	public JMenuItem memberOfItem = new JMenuItem("MemberOf");
	public JMenuItem associationItem = new JMenuItem("Association");
	public JMenuItem characterizationItem = new JMenuItem("Characterization");
	public JMenuItem subquantityOfItem = new JMenuItem("SubQuantityOf");
	public JMenuItem subcollectionOfItem = new JMenuItem("SubCollectionOf");	
	public JMenuItem derivationItem = new JMenuItem("Derivation");
	
	public JMenuItem addGenItem = new JMenuItem("Add Generalization");
	
	public Object element;
	
    public TreePopupMenu(final AppFrame frame, final ProjectTree tree, Object element)
    {       
    	this.element = element;    	
    	DefaultMutableTreeNode node = ((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent());
    	
    	// Auto Complete Selection
    	if (tree.getModelRootNode().equals(node)){
	    	add(autoCompleteItem);	    	
	    	autoCompleteItem.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					frame.getDiagramManager().getEditorDispatcher().autoComplete();
				}
			});
	    }
    	
    	// Refresh Tree
    	if (tree.getRootNode().equals(node)){
    		add(refreshItem);
    		refreshItem.addActionListener(new ActionListener() {				
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				//FIXME every modification creates a new tree
    				ProjectBrowser.refreshTree(frame.getDiagramManager().getCurrentProject());
    			}
    		});
    	}    	
    	
    	//Add Diagram
    	if(tree.getDiagramRootNode().equals(node)){
    		add(addDiagramItem);
    		addDiagramItem.addActionListener(new ActionListener() {				
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				frame.getDiagramManager().newDiagram();    				
    			}
    		});
    	}

    	// Move To Diagram 	    		
		if (node.getUserObject() instanceof OntoUMLElement)
		{
    		OntoUMLElement ontoElement = ((OntoUMLElement)node.getUserObject());    		
    		if(ontoElement.getElement() instanceof RefOntoUML.Class || ontoElement.getElement() instanceof RefOntoUML.DataType)
    		{
    			add(moveToDiagramItem);
    			final RefOntoUML.Type type = (RefOntoUML.Type)ontoElement.getElement();    			
    			moveToDiagramItem.addActionListener(new ActionListener() {				
        			@Override
        			public void actionPerformed(ActionEvent e) {
        				DiagramEditor d = frame.getDiagramManager().getCurrentDiagramEditor();
        				if(d!=null){
        					d.setCreationMode(type,type.eContainer());
        				}
        			}
        		});    			
    		}
		}
		
    	// Add Element 	    		
		if (node.getUserObject() instanceof OntoUMLElement)
		{
    		OntoUMLElement ontoElement = ((OntoUMLElement)node.getUserObject());    		
    		if(ontoElement.getElement() instanceof RefOntoUML.Package)
    		{
    			final RefOntoUML.Package eContainer = (RefOntoUML.Package)ontoElement.getElement();
    			add(addElementMenu);
    			addElementMenu.add(packageItem);
    	        packageItem.addActionListener(new ActionListener() {				
    	        	@Override
    	        	public void actionPerformed(ActionEvent e) {
    	        		frame.getDiagramManager().add(ElementType.PACKAGE,eContainer);
    	        	}
    	        });
        		addElementMenu.add(kindItem);
        		kindItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.KIND,eContainer);
        			}
        		});
        		addElementMenu.add(quantityItem);
        		quantityItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.QUANTITY,eContainer);
        			}
        		});
        		addElementMenu.add(collectiveItem);        
        		collectiveItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.COLLECTIVE,eContainer);
        			}
        		});
        		addElementMenu.add(subkindItem);        
        		subkindItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.SUBKIND,eContainer);
        			}
        		});
        		addElementMenu.add(phaseItem);
        		phaseItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.PHASE,eContainer);
        			}
        		});
        		addElementMenu.add(roleItem);
        		roleItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.ROLE,eContainer);
        			}
        		});
        		addElementMenu.add(categoryItem);        
        		categoryItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.CATEGORY,eContainer);
        			}
        		});
        		addElementMenu.add(rolemixinItem);
        		rolemixinItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.ROLEMIXIN,eContainer);
        			}
        		});    		
        		addElementMenu.add(mixinItem);
        		mixinItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.MIXIN,eContainer);
        			}
        		});  
        		addElementMenu.add(modeItem); 
        		modeItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.MODE,eContainer);
        			}
        		});    		
        		addElementMenu.add(relatorItem);
        		relatorItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.RELATOR,eContainer);
        			}
        		});    		
        		addElementMenu.add(datatypeItem);
        		datatypeItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.DATATYPE,eContainer);
        			}
        		});
        		packageItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/package.png")));
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
    		}
		}
		
		// Add Relation 	    		
		if (node.getUserObject() instanceof OntoUMLElement)
		{
    		OntoUMLElement ontoElement = ((OntoUMLElement)node.getUserObject());    		
    		if(ontoElement.getElement() instanceof RefOntoUML.Package)
    		{
    			final RefOntoUML.Package eContainer = (RefOntoUML.Package)ontoElement.getElement();
    			add(addRelationMenu);    			    			
    			addRelationMenu.add(materialItem);
    			materialItem.addActionListener(new ActionListener() {				
	   	        	@Override
	   	        	public void actionPerformed(ActionEvent e) {
	   	        		frame.getDiagramManager().add(RelationType.MATERIAL,eContainer);
	   	        	}
       	        });
    			addRelationMenu.add(formalItem);
    			formalItem.addActionListener(new ActionListener() {				
	   	        	@Override
	   	        	public void actionPerformed(ActionEvent e) {
	   	        		frame.getDiagramManager().add(RelationType.FORMAL,eContainer);
	   	        	}
       	        });    			
    			addRelationMenu.add(characterizationItem);
    			characterizationItem.addActionListener(new ActionListener() {				
	   	        	@Override
	   	        	public void actionPerformed(ActionEvent e) {
	   	        		frame.getDiagramManager().add(RelationType.CHARACTERIZATION,eContainer);
	   	        	}
       	        });    			
    			addRelationMenu.add(mediationItem);
    			mediationItem.addActionListener(new ActionListener() {				
	   	        	@Override
	   	        	public void actionPerformed(ActionEvent e) {
	   	        		frame.getDiagramManager().add(RelationType.MEDIATION,eContainer);
	   	        	}
       	        });
    			addRelationMenu.add(componentOfItem);
    			componentOfItem.addActionListener(new ActionListener() {				
	   	        	@Override
	   	        	public void actionPerformed(ActionEvent e) {
	   	        		frame.getDiagramManager().add(RelationType.COMPONENTOF,eContainer);
	   	        	}
       	        });
    			addRelationMenu.add(memberOfItem);        
    			memberOfItem.addActionListener(new ActionListener() {				
	   	        	@Override
	   	        	public void actionPerformed(ActionEvent e) {
	   	        		frame.getDiagramManager().add(RelationType.MEMBEROF,eContainer);
	   	        	}
       	        });
    			addRelationMenu.add(subcollectionOfItem);
    			subcollectionOfItem.addActionListener(new ActionListener() {				
	   	        	@Override
	   	        	public void actionPerformed(ActionEvent e) {
	   	        		frame.getDiagramManager().add(RelationType.SUBCOLLECTIONOF,eContainer);
	   	        	}
       	        });    			
    			addRelationMenu.add(subquantityOfItem);
    			subquantityOfItem.addActionListener(new ActionListener() {				
	   	        	@Override
	   	        	public void actionPerformed(ActionEvent e) {
	   	        		frame.getDiagramManager().add(RelationType.SUBQUANTITYOF,eContainer);
	   	        	}
       	        });    			
    			addRelationMenu.add(associationItem);
    			associationItem.addActionListener(new ActionListener() {				
	   	        	@Override
	   	        	public void actionPerformed(ActionEvent e) {
	   	        		frame.getDiagramManager().add(RelationType.ASSOCIATION,eContainer);
	   	        	}
       	        });  
    			addRelationMenu.add(derivationItem);
    			derivationItem.addActionListener(new ActionListener() {				
	   	        	@Override
	   	        	public void actionPerformed(ActionEvent e) {
	   	        		frame.getDiagramManager().add(RelationType.DERIVATION,eContainer);
	   	        	}
       	        });      			
    			associationItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/association-arrow.png")));
    	        materialItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/association.png")));
    	        formalItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/association.png")));
    	        characterizationItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/association.png")));
    	        mediationItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/association.png")));
    	        componentOfItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/aggregation.png")));
    	        memberOfItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/aggregation-m.png")));
    	        subcollectionOfItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/aggregation-c.png")));
    	        subquantityOfItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/aggregation-q.png")));
    	        derivationItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/derivation.png")));
    		}
		}
		
		// Add Generalization 	    		
		if (node.getUserObject() instanceof OntoUMLElement)
		{
    		OntoUMLElement ontoElement = ((OntoUMLElement)node.getUserObject());    		
    		if(ontoElement.getElement() instanceof RefOntoUML.Class || ontoElement.getElement() instanceof RefOntoUML.DataType)
    		{
    			final RefOntoUML.Type eContainer = (RefOntoUML.Type)ontoElement.getElement();
    			add(addGenItem);    			
    			addGenItem.addActionListener(new ActionListener() {				
	   	        	@Override
	   	        	public void actionPerformed(ActionEvent e) {
	   	        		frame.getDiagramManager().add(RelationType.GENERALIZATION,eContainer);
	   	        	}
       	        });
    			addGenItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/inheritance.png")));
    		}
		}
		
    	// Delete 
    	if (!tree.getModelRootNode().equals(node) && !tree.getDiagramRootNode().equals(node) && !tree.getRootNode().equals(node)){    		
    		add(deleteItem);
    		deleteItem.setIcon(new ImageIcon(TreePopupMenu.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/delete.png")));
    		deleteItem.addActionListener(new ActionListener() {				
    			@Override
    			public void actionPerformed(ActionEvent e) {			
    							
    				if (TreePopupMenu.this.element instanceof OntoUMLElement)
    				{
    					OntoUMLElement ontoElem = (OntoUMLElement) ((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent()).getUserObject();
    					RefOntoUML.Element elemForDeletion = (RefOntoUML.Element)ontoElem.getElement();
    					frame.getDiagramManager().delete(elemForDeletion);    					    					
	    				tree.setSelectionPath(new TreePath(tree.getModelRootNode().getPath()));    					    					
    				}
    				else if (TreePopupMenu.this.element instanceof StructureDiagram)
    				{
    					frame.getDiagramManager().removeDiagram((StructureDiagram)TreePopupMenu.this.element);
    					// FIXME every modification creates a new tree
    					ProjectBrowser.rebuildTree(frame.getDiagramManager().getCurrentProject());
    				}
    			}
    		});
    	}  
    }
}
