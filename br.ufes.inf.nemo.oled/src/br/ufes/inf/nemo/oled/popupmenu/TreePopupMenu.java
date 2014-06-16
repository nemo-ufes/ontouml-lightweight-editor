package br.ufes.inf.nemo.oled.popupmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import RefOntoUML.Association;
import RefOntoUML.Element;
import RefOntoUML.Generalization;
import RefOntoUML.Property;
import RefOntoUML.Type;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.dialog.DiagramListDialog;
import br.ufes.inf.nemo.oled.explorer.OntoUMLElement;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.explorer.ProjectTree;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditorWrapper;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;

public class TreePopupMenu extends JPopupMenu {
 
	private static final long serialVersionUID = 1L;
	public Object element;
	public AppFrame frame;
	public ProjectTree tree;
	public DefaultMutableTreeNode selectedNode;
	
	public void createRefreshItem()
	{    	
		JMenuItem refreshItem = new JMenuItem("Refresh");
		add(refreshItem);
		refreshItem.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {    				
				ProjectBrowser.refreshTree(frame.getDiagramManager().getCurrentProject());
			}
		});    	    	
	}
	
	public void createCompleteItem()
	{
		JMenuItem autoCompleteItem = new JMenuItem("Complete selection");
    	add(autoCompleteItem);	    	
    	autoCompleteItem.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.getDiagramManager().getEditorDispatcher().autoComplete();
			}
		});
	}
	
	public void createAddDiagramItem()
	{
		JMenuItem addDiagramItem = new JMenuItem("Add Diagram");
		add(addDiagramItem);
		addDiagramItem.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getDiagramManager().newDiagram();    				
			}
		});
	}
	
	public void createRenameItem()
	{
		JMenuItem setNameItem = new JMenuItem("Rename");
		add(setNameItem);    		
		setNameItem.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				if (TreePopupMenu.this.element instanceof OntoUMLElement)
				{    					
					RefOntoUML.Element element = (RefOntoUML.Element)((OntoUMLElement)TreePopupMenu.this.element).getElement();    					
					ProjectBrowser.frame.getDiagramManager().rename(element);					
				}
			}
		});
	}
	
	public void createMoveToDiagramItem()
	{
		final OntoUMLElement ontoElement = ((OntoUMLElement)selectedNode.getUserObject());  
		JMenuItem moveToDiagramItem = new JMenuItem("Move to Diagram");
		add(moveToDiagramItem);    			    			
		moveToDiagramItem.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				DiagramEditor d = frame.getDiagramManager().getCurrentDiagramEditor();
				frame.getDiagramManager().moveToDiagram((RefOntoUML.Element)ontoElement.getElement(), d);        			
			};
		});    
	}
	
	public void createClassChangeItem()
	{
		final OntoUMLElement ontoElement = ((OntoUMLElement)selectedNode.getUserObject());  
		final Type type = (Type)ontoElement.getElement();
		ClassStereotypeChangeMenu changeMenu = new ClassStereotypeChangeMenu(frame.getDiagramManager());
		changeMenu.setElement(type);
		add(changeMenu);	
	}
	
	public void createRelationChangeItem()
	{
		final OntoUMLElement ontoElement = ((OntoUMLElement)selectedNode.getUserObject()); 
		final Association type = (Association)ontoElement.getElement();    			
		RelationStereotypeChangeMenu changeMenu = new RelationStereotypeChangeMenu(frame.getDiagramManager());
		changeMenu.setElement(type);
		add(changeMenu);
	}
	
	public void createAddElementItem()
	{
		OntoUMLElement ontoElement = ((OntoUMLElement)selectedNode.getUserObject());		
		final RefOntoUML.Package eContainer = (RefOntoUML.Package)ontoElement.getElement();
		JMenu addElementMenu = new JMenu("Add Element");
		add(addElementMenu);
		JMenuItem packageItem = new JMenuItem("Package");
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
		JMenuItem genSetItem = new JMenuItem("Generalization Set");
		addElementMenu.add(packageItem);
        packageItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.getDiagramManager().addElement(ElementType.PACKAGE,eContainer);
        	}
        });
		addElementMenu.add(kindItem);
		kindItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				frame.getDiagramManager().addElement(ElementType.KIND,eContainer);
			}
		});
		addElementMenu.add(quantityItem);
		quantityItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				frame.getDiagramManager().addElement(ElementType.QUANTITY,eContainer);
			}
		});
		addElementMenu.add(collectiveItem);        
		collectiveItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				frame.getDiagramManager().addElement(ElementType.COLLECTIVE,eContainer);
			}
		});
		addElementMenu.add(subkindItem);        
		subkindItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				frame.getDiagramManager().addElement(ElementType.SUBKIND,eContainer);
			}
		});
		addElementMenu.add(phaseItem);
		phaseItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				frame.getDiagramManager().addElement(ElementType.PHASE,eContainer);
			}
		});
		addElementMenu.add(roleItem);
		roleItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				frame.getDiagramManager().addElement(ElementType.ROLE,eContainer);
			}
		});
		addElementMenu.add(categoryItem);        
		categoryItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				frame.getDiagramManager().addElement(ElementType.CATEGORY,eContainer);
			}
		});
		addElementMenu.add(rolemixinItem);
		rolemixinItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				frame.getDiagramManager().addElement(ElementType.ROLEMIXIN,eContainer);
			}
		});    		
		addElementMenu.add(mixinItem);
		mixinItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				frame.getDiagramManager().addElement(ElementType.MIXIN,eContainer);
			}
		});  
		addElementMenu.add(modeItem); 
		modeItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				frame.getDiagramManager().addElement(ElementType.MODE,eContainer);
			}
		});    		
		addElementMenu.add(relatorItem);
		relatorItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				frame.getDiagramManager().addElement(ElementType.RELATOR,eContainer);
			}
		});    		
		addElementMenu.add(datatypeItem);
		datatypeItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				frame.getDiagramManager().addElement(ElementType.DATATYPE,eContainer);
			}
		});
		addElementMenu.add(genSetItem);
		genSetItem.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				frame.getDiagramManager().addElement(ElementType.GENERALIZATIONSET,eContainer);
			}
		});
		packageItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/package.png")));
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
        genSetItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/generalization.png")));	
	}
	
	public void createAddRelationItem()
	{
		OntoUMLElement ontoElement = ((OntoUMLElement)selectedNode.getUserObject());
		final RefOntoUML.Package eContainer = (RefOntoUML.Package)ontoElement.getElement();
		JMenu addRelationMenu = new JMenu("Add Relation");
		add(addRelationMenu);    			
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
		addRelationMenu.add(materialItem);
		materialItem.addActionListener(new ActionListener() {				
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getDiagramManager().addRelation(RelationType.MATERIAL,eContainer);
	        	}
	        });
		addRelationMenu.add(formalItem);
		formalItem.addActionListener(new ActionListener() {				
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getDiagramManager().addRelation(RelationType.FORMAL,eContainer);
	        	}
	        });    			
		addRelationMenu.add(characterizationItem);
		characterizationItem.addActionListener(new ActionListener() {				
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getDiagramManager().addRelation(RelationType.CHARACTERIZATION,eContainer);
	        	}
	        });    			
		addRelationMenu.add(mediationItem);
		mediationItem.addActionListener(new ActionListener() {				
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getDiagramManager().addRelation(RelationType.MEDIATION,eContainer);
	        	}
	        });
		addRelationMenu.add(componentOfItem);
		componentOfItem.addActionListener(new ActionListener() {				
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getDiagramManager().addRelation(RelationType.COMPONENTOF,eContainer);
	        	}
	        });
		addRelationMenu.add(memberOfItem);        
		memberOfItem.addActionListener(new ActionListener() {				
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getDiagramManager().addRelation(RelationType.MEMBEROF,eContainer);
	        	}
	        });
		addRelationMenu.add(subcollectionOfItem);
		subcollectionOfItem.addActionListener(new ActionListener() {				
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getDiagramManager().addRelation(RelationType.SUBCOLLECTIONOF,eContainer);
	        	}
	        });    			
		addRelationMenu.add(subquantityOfItem);
		subquantityOfItem.addActionListener(new ActionListener() {				
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getDiagramManager().addRelation(RelationType.SUBQUANTITYOF,eContainer);
	        	}
	        });    			
		addRelationMenu.add(associationItem);
		associationItem.addActionListener(new ActionListener() {				
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getDiagramManager().addRelation(RelationType.ASSOCIATION,eContainer);
	        	}
	        });  
		addRelationMenu.add(derivationItem);
		derivationItem.addActionListener(new ActionListener() {				
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getDiagramManager().addRelation(RelationType.DERIVATION,eContainer);
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
	
	public void createAddItem()
	{
		OntoUMLElement ontoElement = ((OntoUMLElement)selectedNode.getUserObject());
		final RefOntoUML.Type eContainer = (RefOntoUML.Type)ontoElement.getElement();
		JMenu addItem = new JMenu("Add");
		JMenuItem addGenItem = new JMenuItem("Generalization");
		JMenuItem addCommentItem = new JMenuItem("Comment");
		JMenuItem addConstraintItem = new JMenuItem("Constraintx");
		addItem.add(addGenItem);    			
		addItem.add(addCommentItem);
		addItem.add(addConstraintItem);
		add(addItem);
		addGenItem.addActionListener(new ActionListener() {				
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getDiagramManager().addRelation(RelationType.GENERALIZATION,eContainer);
	        	}
	        });
		addCommentItem.addActionListener(new ActionListener() {				
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getDiagramManager().addElement(ElementType.COMMENT,eContainer);
	        	}
	        });
		addConstraintItem.addActionListener(new ActionListener() {				
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getDiagramManager().addElement(ElementType.CONSTRAINT,eContainer);
	        	}
	        });
		addGenItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/generalization.png")));
		addCommentItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/comment.png")));
		addConstraintItem.setIcon(new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/tree/constraintx.png")));
	}
	
	public void createInvertItem()
	{
		OntoUMLElement ontoElement = ((OntoUMLElement)selectedNode.getUserObject());
		final Association association = (Association)ontoElement.getElement();
		JMenu invertMenu = new JMenu("Invert");    			
		JMenuItem invertEndPointsItem = new JMenuItem("End Points");
		invertMenu.add(invertEndPointsItem);
		add(invertMenu);    			
		invertEndPointsItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.getDiagramManager().invertEndPoints(association);
        	}
        }); 
	}
	
	public void createDeleteItem()
	{
		JMenuItem deleteItem = new JMenuItem("Delete");
		add(deleteItem);
		deleteItem.setIcon(new ImageIcon(TreePopupMenu.class.getResource("/resources/icons/x16/cross.png")));
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
				}
			}
		});
	}
	
	public void createFinInDiagramItem()
	{
		final OntoUMLElement ontoElement = ((OntoUMLElement)selectedNode.getUserObject());
		JMenuItem findDiagramItem = new JMenuItem("Find in Diagrams");
		add(findDiagramItem);		
		findDiagramItem.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {							
				ArrayList<DiagramEditor> diagrams = ProjectBrowser.frame.getDiagramManager().getDiagramEditors((Element)ontoElement.getElement());
				DiagramListDialog.open(ProjectBrowser.frame, diagrams,(Element)ontoElement.getElement());
			}
		});
	}
	
    public TreePopupMenu(final AppFrame frame, final ProjectTree tree, Object element)
    {       
    	this.element = element; 
    	this.frame = frame;
    	this.tree = tree;
    	
    	DefaultMutableTreeNode node = ((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent());
    	this.selectedNode = node;
    	
    	// Root Node: OLED Project
    	if (tree.getRootNode().equals(selectedNode)) {
    		createRefreshItem();  
    		return;
    	} 
    	
    	// Model Node: Model
    	if (tree.getModelRootNode().equals(selectedNode)) {
    		createCompleteItem();
    		return;
    	} 
    	
    	// Diagram Node: Diagrams
    	if(tree.getDiagramRootNode().equals(selectedNode)) {
    		createAddDiagramItem();
    		return;
    	} 

    	// Model Elements...
    	
    	if ((!(TreePopupMenu.this.element instanceof StructureDiagram)) && !((RefOntoUML.Element)((OntoUMLElement)TreePopupMenu.this.element).getElement() instanceof Generalization)) {
    		createRenameItem();
    	}
    	
    	if(selectedNode.getUserObject() instanceof StructureDiagram)
    	{
    		createDeleteItem();
    	}
    	
		if (selectedNode.getUserObject() instanceof OntoUMLElement)
		{
    		final OntoUMLElement ontoElement = ((OntoUMLElement)selectedNode.getUserObject());    		
    		if(ontoElement.getElement() instanceof RefOntoUML.Type || ontoElement.getElement() instanceof RefOntoUML.Generalization)
    		{
    			createMoveToDiagramItem();
    			createFinInDiagramItem();
    		}		   		
    		if(ontoElement.getElement() instanceof RefOntoUML.Class || ontoElement.getElement() instanceof RefOntoUML.DataType)
    		{    			
    			createAddItem();
    			createClassChangeItem();    			
    		}
    		if(ontoElement.getElement() instanceof RefOntoUML.Association)
    		{ 
    			createRelationChangeItem();
    			createInvertItem();
    		}
    		if(ontoElement.getElement() instanceof RefOntoUML.Package)
    		{
    			createAddElementItem();
    			createAddRelationItem();
    		}
    		if (!(ontoElement.getElement() instanceof Property))
    		{
    			createDeleteItem();
    		} 
		}    	
    }
}
