package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList.UnmodifiableEList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import RefOntoUML.Association;
import RefOntoUML.Dependency;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Property;


public class RefOntoUMLUtil {
	
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
	    		  
	    	  } else if (setting.getEObject() instanceof Property &&
	    			  setting.getEObject().eContainer() instanceof Association) {
	    		  EcoreUtil.remove(setting.getEObject().eContainer());
	    		  
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
		    		  
		    	  } else if (setting.getEObject() instanceof Property &&
		    			  setting.getEObject().eContainer() instanceof Association) {
		    		  EcoreUtil.remove(setting.getEObject().eContainer());
		    		  
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
