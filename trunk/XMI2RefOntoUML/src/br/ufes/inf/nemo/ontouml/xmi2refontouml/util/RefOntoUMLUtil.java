package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTreeCellRenderer;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.eclipse.emf.common.util.BasicEList.UnmodifiableEList;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;

import br.ufes.inf.nemo.ontouml.xmi2refontouml.transformation.Mediator;

import RefOntoUML.Association;
import RefOntoUML.Comment;
import RefOntoUML.Dependency;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Property;
import RefOntoUML.util.ValidationMessage;


public class RefOntoUMLUtil {
	
	/**
	 * Creates a CheckboxTree from a RefOntoUML.Model to serve
	 * as a element selection to the XMI2RefOntoUML transformation.
	 * @param model the RefOntoUML Model
	 * @return the CheckboxTree with the Model elements to be selected.
	 */
	
	public static CheckboxTree createSelectionTreeFromModel(RefOntoUML.Model model) {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new ChckBoxTreeNodeElem(model));
		CheckboxTree modelTree = new CheckboxTree(rootNode);
		modelTree.setCellRenderer(new OntoUMLTreeCellRenderer());
		drawTree(rootNode, model);
		return modelTree;
	}
	
	/**
	 * Auxiliary function for the createSelectionTreeFromModel.
	 * It runs the Elements from the model creating the tree nodes.
	 * @param parent the direct parent node of the element that will be created.
	 * @param refElement the RefOntoUML Element for which a node will be created.
	 */
	
	private static void drawTree(DefaultMutableTreeNode parent, RefOntoUML.Element refElement) {
		if (refElement instanceof RefOntoUML.Model) {
			EList<EObject> contents = refElement.eContents();
			for (EObject eobj : contents) {
				drawTree(parent, (RefOntoUML.Element) eobj);
			}
			
		} else if (refElement instanceof RefOntoUML.Package) {
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new ChckBoxTreeNodeElem(refElement));
			parent.add(newNode);
			
			EList<EObject> contents = refElement.eContents();
			for (EObject eobj : contents) {
				drawTree(newNode, (RefOntoUML.Element) eobj);
			}
			
		} else if (refElement instanceof RefOntoUML.Classifier) {
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new ChckBoxTreeNodeElem(refElement));			
			parent.add(newNode);
			
		}
	}
	
	/**
	 * Creates a CheckboxTree organizing the elements by diagrams
	 * instead of by the Model structure (Packages). It uses the
	 * Mapper to find the diagrams and their classes.
	 * @param mapper the bridge that in responsible for reading the
	 * tool specific XMI and returning the information that is needed.
	 * @return the CheckboxTree with the elements organized by diagram.
	 */
	
	public static CheckboxTree createSelectionTreeByDiagram(Mapper mapper) {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new ChckBoxTreeNodeElem("Diagrams"));
		CheckboxTree modelTree = new CheckboxTree(rootNode);
		modelTree.setCellRenderer(new OntoUMLTreeCellRenderer());
		
		List<Object> diagramList = mapper.getDiagramList();
    	for (Object diagram : diagramList) {
    		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
    				new ChckBoxTreeNodeElem(mapper.getName(diagram)));
    		rootNode.add(newNode);
    		List<String> elemIdList = mapper.getDiagramElements(diagram);
    		for (String id : elemIdList) {
    			DefaultMutableTreeNode newElemNode = new DefaultMutableTreeNode(
    					new ChckBoxTreeNodeElem(Mediator.elemMap.get(id)));
    			newNode.add(newElemNode);
    		}
    	}
    	
    	return modelTree;
	}
	
	/**
	 * Removes the selected elements from the Model and from the Tree.
	 * @param modelTree the tree containing the Model that will be filtered.
	 */

//	public static void filter(CheckboxTree modelTree) {
//    	TreePath[] treepathList = modelTree.getCheckingPaths();
//    	for (TreePath treepath : treepathList) {
//    		DefaultMutableTreeNode childNode = (DefaultMutableTreeNode)treepath.getLastPathComponent();
//    		ChckBoxTreeNodeElem chckNode = (ChckBoxTreeNodeElem) childNode.getUserObject();
//    		
//    		RefOntoUML.Element oldElem = chckNode.getElement();
//    		
//    		if (oldElem != null) {
//    			RefOntoUMLUtil.delete(oldElem, true);
//    		}
//    	}
//    	
//    	removeExcludedNodes((DefaultMutableTreeNode) modelTree.getModel().getRoot(), modelTree);
//    }
	
	public static void filter(CheckboxTree modelTree) {
		List<DefaultMutableTreeNode> checkedNodes = new ArrayList<DefaultMutableTreeNode>();
    	TreePath[] treepathList = modelTree.getCheckingPaths();
    	
    	for (TreePath treepath : treepathList) {
    		checkedNodes.add((DefaultMutableTreeNode)treepath.getLastPathComponent());
    	}
    	
    	crossTreeDeleting((DefaultMutableTreeNode) modelTree.getModel().getRoot(), modelTree, checkedNodes);
    }
	
	public static void crossTreeDeleting(DefaultMutableTreeNode treeNode, 
			CheckboxTree modelTree, List<DefaultMutableTreeNode> checkedNodes) {
    	
    	if (treeNode == null) {
    		return;
    	}
    	
    	if (!treeNode.isLeaf()) {
    		crossTreeDeleting((DefaultMutableTreeNode)treeNode.getFirstChild(), modelTree, checkedNodes);
    	}
    	
    	crossTreeDeleting(treeNode.getNextSibling(), modelTree, checkedNodes);
    	
		if (!checkedNodes.contains(treeNode)) {
			ChckBoxTreeNodeElem chckNode = (ChckBoxTreeNodeElem) treeNode.getUserObject();
			RefOntoUML.Element oldElem = chckNode.getElement();
			
			if (oldElem != null) {
				RefOntoUMLUtil.delete(oldElem, true);
				if (oldElem.eResource() != null) {
					System.out.println("Debbug: Não excluiu e era pra excluir.");
				}
			}
			
			DefaultTreeModel treeModel = (DefaultTreeModel)modelTree.getModel();
			treeModel.removeNodeFromParent(treeNode);
		}
    	
    }
	
	/**
	 * Auxiliary function that removes from the tree the nodes that contain
	 * elements that were removed from the Model.
	 * @param treeNode
	 * @param modelTree
	 */
    
    public static void removeExcludedNodes(DefaultMutableTreeNode treeNode, CheckboxTree modelTree) {
    	
    	if (treeNode == null) {
    		return;
    	}
    	
    	if (!treeNode.isLeaf()) {
    		removeExcludedNodes((DefaultMutableTreeNode)treeNode.getFirstChild(), modelTree);
    	}
    	
    	removeExcludedNodes(treeNode.getNextSibling(), modelTree);
    	
		ChckBoxTreeNodeElem chckNode = (ChckBoxTreeNodeElem) treeNode.getUserObject();
		
		RefOntoUML.Element oldElem = chckNode.getElement();
		if ((oldElem != null && oldElem.eResource() == null) ||
				(oldElem == null && treeNode.getChildCount() == 0)) {
			DefaultTreeModel treeModel = (DefaultTreeModel)modelTree.getModel();
			treeModel.removeNodeFromParent(treeNode);
		}
    	
    }
    
    static class OntoUMLTreeCellRenderer implements CheckboxTreeCellRenderer {

    	JCheckBox button = new JCheckBox();
    	JPanel panel = new JPanel();
    	JLabel label = new JLabel();
    	
    	@Override
		public boolean isOnHotspot(int x, int y) {
			return (button.getBounds().contains(x, y));
		}

    	public OntoUMLTreeCellRenderer() {
    		label.setFocusable(true);
    		label.setOpaque(true);
    		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    		panel.add(button);
    		panel.add(label);
    		button.setBackground(UIManager.getColor("Tree.textBackground"));
    		panel.setBackground(UIManager.getColor("Tree.textBackground"));
    	}
       
    	@Override
    	public Component getTreeCellRendererComponent(JTree tree, Object value, 
    			boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
    	   
    		label.setText(value.toString());
    		String elementType;
    		RefOntoUML.Element element = ((ChckBoxTreeNodeElem)((DefaultMutableTreeNode)value).
    				getUserObject()).getElement();
    		if (element != null) {
    			elementType = element.getClass().toString().replace("class " +
    					"RefOntoUML.impl.", "").replace("Impl", "");
    		} else
    			elementType = "diagram";
    		
    		label.setIcon(new ImageIcon(getClass().getClassLoader().
    				getResource("resources/br/ufes/inf/nemo/oled/ui/xmi2ontoref/"+
    				elementType.toLowerCase()+".png")));
    		
    		if (selected)
    			label.setBackground(UIManager.getColor("Tree.selectionBackground"));
    		else
    			label.setBackground(UIManager.getColor("Tree.textBackground"));
    	   
    		TreeCheckingModel checkingModel = ((CheckboxTree)tree).getCheckingModel();
    	   	TreePath path = tree.getPathForRow(row);
    	   	boolean enabled = checkingModel.isPathEnabled(path);
    	   	boolean checked = checkingModel.isPathChecked(path);
    	   	boolean grayed = checkingModel.isPathGreyed(path);
    	   	button.setEnabled(enabled);
    	   	if (grayed) {
    	   		label.setForeground(Color.lightGray);
    	   	} else {
    	   		label.setForeground(Color.black);
    	   	}
    	   	button.setSelected(checked);
    	   	return panel;
    	}
    }
    
    /**
     * Validates a model according to the RefOntoUML OCL rules.
     * @param model the model that will be validated.
     */
    
    public void validate(RefOntoUML.Model model) {
		Diagnostician validator = Diagnostician.INSTANCE;
		
		// (Opcional, apenas para inicializar mais rápido) 
		// As the first validation takes long due to initialization process,
		// we start it here so the user doesn't get the initialization hit
		//validator.validate(factory.createClass());
		
		Map<Object, Object> context = new HashMap<Object, Object>();
		BasicDiagnostic diag = new BasicDiagnostic();
		
		// Returns true if the model is valid.
		if (validator.validate(model, diag, context)){
			System.out.println("Valid model.");
		} else {
			System.out.println("Invalid model.");
		}
		
		for (Diagnostic item : diag.getChildren()) {	
			System.out.println(ValidationMessage.getFinalMessage(item.getMessage()));
		}
	}
	
	/**
	   * Deletes the object from its {@link EObject#eResource containing} resource 
	   * and/or its {@link EObject#eContainer containing} object
	   * as well as from any other feature that references it 
	   * within the enclosing resource set, resource, or root object.
	   * @param eObject the object to delete.
	   * @since 2.3
	   */
    public static void delete(EObject eObject)
	  {
	    EObject rootEObject = EcoreUtil.getRootContainer(eObject);
	    Resource resource = rootEObject.eResource();

	    Collection<EStructuralFeature.Setting> usages;
	    if (resource == null)
	    {
	      usages = EcoreUtil.UsageCrossReferencer.find(eObject, rootEObject);
	    }
	    else
	    {
	      ResourceSet resourceSet = resource.getResourceSet();
	      if (resourceSet == null)
	      {
	        usages = EcoreUtil.UsageCrossReferencer.find(eObject, resource);
	      }
	      else
	      {
	        usages = EcoreUtil.UsageCrossReferencer.find(eObject, resourceSet);
	      }
	    }

	    for (EStructuralFeature.Setting setting : usages)
	    {
	      if (setting.getEStructuralFeature().isChangeable())
	      {	    	  
	    	  if (setting.getEObject() instanceof Generalization ||
	    			  setting.getEObject() instanceof Dependency) {
	    		  delete(setting.getEObject());
	    		  
	    	  } else if (setting.getEObject() instanceof GeneralizationSet) {
	    		  EcoreUtil.remove(setting, eObject);
	    		  if (((GeneralizationSet)setting.getEObject()).getGeneralization().size() == 0) {
		    			  EcoreUtil.remove(setting.getEObject());
	    		  }
	    		  
	    	  } else if (setting.getEObject() instanceof Property) {
	    		  if (setting.getEObject().eContainer() instanceof Association) {
	    			  delete(setting.getEObject().eContainer(), true);
	    		  } else {
	    			  delete(setting.getEObject(), true);
	    		  }
	    		  
	    	  } else if (setting.getEObject() instanceof Comment) {
	    		  EcoreUtil.remove(setting, eObject);
	    		  if (((Comment)setting.getEObject()).getAnnotatedElement().size() == 0) {
	    			  delete (setting.getEObject());
	    		  }
	    		  
	    	  } else if (!(setting instanceof UnmodifiableEList)) {
	    		  EcoreUtil.remove(setting, eObject);
	    	  }
	      }
	    }
	    EcoreUtil.remove(eObject);
	  }

	  /**
	   * Deletes the object from its {@link EObject#eResource containing} resource 
	   * and/or its {@link EObject#eContainer containing} object
	   * as well as from any other feature that references it 
	   * within the enclosing resource set, resource, or root object.
	   * If recursive true, contained children of the object that are in the same resource 
	   * are similarly removed from any features that references them.
	   * @param eObject the object to delete.
	   * @param recursive whether references to contained children should also be removed.
	   * @since 2.4
	   */
	  public static void delete(EObject eObject, boolean recursive)
	  {
	    if (recursive)
	    {
	      EObject rootEObject = EcoreUtil.getRootContainer(eObject);
	      Resource resource = rootEObject.eResource();

	      Set<EObject> eObjects = new HashSet<EObject>();        
	      Set<EObject> crossResourceEObjects = new HashSet<EObject>();        
	      eObjects.add(eObject);
	      for (@SuppressWarnings("unchecked") TreeIterator<InternalEObject> j = (TreeIterator<InternalEObject>)(TreeIterator<?>)eObject.eAllContents();  j.hasNext(); )
	      {
	        InternalEObject childEObject = j.next();
	        if (childEObject.eDirectResource() != null)
	        {
	          crossResourceEObjects.add(childEObject);
	        }
	        else
	        {
	          eObjects.add(childEObject);
	        }
	      }

	      Map<EObject, Collection<EStructuralFeature.Setting>> usages;
	      if (resource == null)
	      {
	        usages = EcoreUtil.UsageCrossReferencer.findAll(eObjects, rootEObject);
	      }
	      else
	      {
	        ResourceSet resourceSet = resource.getResourceSet();
	        if (resourceSet == null)
	        {
	          usages = EcoreUtil.UsageCrossReferencer.findAll(eObjects, resource);
	        }
	        else
	        {
	          usages = EcoreUtil.UsageCrossReferencer.findAll(eObjects, resourceSet);
	        }
	      }

	      for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : usages.entrySet())
	      {
	        EObject deletedEObject = entry.getKey();
	        Collection<EStructuralFeature.Setting> settings = entry.getValue();
	        for (EStructuralFeature.Setting setting : settings)
	        {
	          if (!eObjects.contains(setting.getEObject()) && setting.getEStructuralFeature().isChangeable())
	          {
	        	  if (setting.getEObject() instanceof Generalization ||
		    			  setting.getEObject() instanceof Dependency) {
		    		  delete(setting.getEObject());
		    		  
		    	  } else if (setting.getEObject() instanceof GeneralizationSet) {
		    		  EcoreUtil.remove(setting, deletedEObject);
		    		  if (((GeneralizationSet)setting.getEObject()).getGeneralization().size() == 0) {
			    			  EcoreUtil.remove(setting.getEObject());
		    		  }
		    		  
		    	  } else if (setting.getEObject() instanceof Property) {
		    		  if (setting.getEObject().eContainer() instanceof Association) {
		    			  delete(setting.getEObject().eContainer(), true);
		    		  } else {
		    			  delete(setting.getEObject(), true);
		    		  }
		    		  
		    	  } else if (setting.getEObject() instanceof Comment) {
		    		  EcoreUtil.remove(setting, deletedEObject);
		    		  if (((Comment)setting.getEObject()).getAnnotatedElement().size() == 0) {
		    			  delete (setting.getEObject());
		    		  }
		    		  
		    	  } else if (!(setting instanceof UnmodifiableEList)) {
		    		  EcoreUtil.remove(setting, deletedEObject);
		    	  }
	          }
	        }
	      }
	      
	      EcoreUtil.remove(eObject);

	      for (EObject crossResourceEObject : crossResourceEObjects)
	      {
	    	  EcoreUtil.remove(crossResourceEObject.eContainer(), crossResourceEObject.eContainmentFeature(), crossResourceEObject);
	      }
	    }
	    else
	    {
	      delete(eObject);
	    }
	  }
	
}
