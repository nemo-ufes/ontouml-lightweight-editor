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
import br.ufes.inf.nemo.oled.ui.AppFrame;
import br.ufes.inf.nemo.oled.ui.OntoUMLElement;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.ProjectTree;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;

public class TreePopupMenu extends JPopupMenu {
 
	private static final long serialVersionUID = 1L;
		
	public JMenuItem deleteItem = new JMenuItem("Delete");
	public JMenuItem autoCompleteItem = new JMenuItem("Complete selection");
	public JMenuItem refreshItem = new JMenuItem("Refresh");
	public JMenuItem addDiagramItem = new JMenuItem("Add Diagram");
		
	public JMenu addMenu = new JMenu("Add");
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
    				ProjectBrowser.rebuildTree(frame.getDiagramManager().getCurrentProject());
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
    	
    	// Add    	    		
		if (node.getUserObject() instanceof OntoUMLElement)
		{
    		OntoUMLElement ontoElement = ((OntoUMLElement)node.getUserObject());    		
    		if(ontoElement.getElement() instanceof RefOntoUML.Package)
    		{
    			final RefOntoUML.Package eContainer = (RefOntoUML.Package)ontoElement.getElement();
    			add(addMenu);
    			addMenu.add(packageItem);
    	        packageItem.addActionListener(new ActionListener() {				
    	        	@Override
    	        	public void actionPerformed(ActionEvent e) {
    	        		frame.getDiagramManager().add(ElementType.PACKAGE,eContainer);
    	        	}
    	        });
        		addMenu.add(kindItem);
        		kindItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.KIND,eContainer);
        			}
        		});
        		addMenu.add(quantityItem);
        		quantityItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.QUANTITY,eContainer);
        			}
        		});
        		addMenu.add(collectiveItem);        
        		collectiveItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.COLLECTIVE,eContainer);
        			}
        		});
        		addMenu.add(subkindItem);        
        		subkindItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.SUBKIND,eContainer);
        			}
        		});
        		addMenu.add(phaseItem);
        		phaseItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.PHASE,eContainer);
        			}
        		});
        		addMenu.add(roleItem);
        		roleItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.ROLE,eContainer);
        			}
        		});
        		addMenu.add(categoryItem);        
        		categoryItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.CATEGORY,eContainer);
        			}
        		});
        		addMenu.add(rolemixinItem);
        		rolemixinItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.ROLEMIXIN,eContainer);
        			}
        		});    		
        		addMenu.add(mixinItem);
        		mixinItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.MIXIN,eContainer);
        			}
        		});  
        		addMenu.add(modeItem); 
        		modeItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.MODE,eContainer);
        			}
        		});    		
        		addMenu.add(relatorItem);
        		relatorItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.RELATOR,eContainer);
        			}
        		});    		
        		addMenu.add(datatypeItem);
        		datatypeItem.addActionListener(new ActionListener() {				
        			public void actionPerformed(ActionEvent e) {
        				frame.getDiagramManager().add(ElementType.DATATYPE,eContainer);
        			}
        		});
        		addMenu.add(genItem);
        		addMenu.add(materialItem);
        		addMenu.add(formalItem);
        		addMenu.add(characterizationItem);
        		addMenu.add(mediationItem);        
        		addMenu.add(componentOfItem);        
        		addMenu.add(memberOfItem);        
        		addMenu.add(subcollectionOfItem);
        		addMenu.add(subquantityOfItem);
        		addMenu.add(associationItem);
        		addMenu.add(derivationItem);
    		}
		}    	
    }
}
