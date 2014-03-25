package br.ufes.inf.nemo.xmi2ontouml.util;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.PackageableElement;

/**
 * A Copier exclusive for OntoUML models, which extends the {@link EcoreUtil.Copier}
 */
public class OntoUMLCopier extends Copier
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * List of objects that will be copied.
	 */
	private Collection<?> objectsToCopy;
	
	/**
	 * Creates an instance.
	 */
	public OntoUMLCopier()
	{
		super();
	}
	
	public OntoUMLCopier(Collection<?> objectsToCopy)
	{
		super();
		this.objectsToCopy = objectsToCopy;
	}
	
	/**
	 * Returns a copy of the given eObject.
	 * @param eObject the object to copy.
	 * @return the copy.
	 */
	@Override
	public EObject copy(EObject eObject)
	{
		if (eObject == null)
		{
			return null;
		}
		else
		{
			EObject copyEObject = createCopy(eObject);
			put(eObject, copyEObject);
			EClass eClass = eObject.eClass();
			for (int i = 0, size = eClass.getFeatureCount(); i < size; ++i)
			{
				EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
				if (eStructuralFeature.isChangeable() && !eStructuralFeature.isDerived())
				{
					if (eStructuralFeature instanceof EAttribute)
					{
						copyAttribute((EAttribute)eStructuralFeature, eObject, copyEObject);
					}
					else
					{
						EReference eReference = (EReference)eStructuralFeature;
						if (eReference.isContainment())
						{
							copyContainment(eReference, eObject, copyEObject);
						}
//						else if (eReference.getName().equals("type") && eObject.eGet(eReference) != null)
//						{
//							EObject dependEObject = (EObject) eObject.eGet(eReference);
//							copy(dependEObject);
//							EObject parent = dependEObject.eContainer();
//							while (parent != null && !objectsToCopy.contains(parent))
//							{
//								copy(parent);
//							}
//						}
					}
				}
			}
			
			copyProxyURI(eObject, copyEObject);
			
			return copyEObject;
		}
	}
	
	/**
	 * Called to handle the copying of a containment feature in the OntoUML contex;
	 * this adds a list of copies or sets a single copy as appropriate for the multiplicity.
	 * @param eReference the feature to copy.
	 * @param eObject the object from which to copy.
	 * @param copyEObject the object to copy to.
	 */
	@Override
	protected void copyContainment(EReference eReference, EObject eObject, EObject copyEObject)
	{
		if (eObject.eIsSet(eReference))
		{
			if (eReference.isMany())
			{
				@SuppressWarnings("unchecked") List<EObject> source = (List<EObject>)eObject.eGet(eReference);
				@SuppressWarnings("unchecked") List<EObject> target = (List<EObject>)copyEObject.eGet(getTarget(eReference));
				if (source.isEmpty())
				{
					target.clear();
				}
				else
				{
					if (eReference.getName().equals("packagedElement"))
					{
						for (EObject eObj : source)
						{
							PackageableElement PE = (PackageableElement) eObj;
							if (objectsToCopy.contains(PE))
							{
								target.add(copy(eObj));
							}
							else if (PE instanceof GeneralizationSet)
							{
								for (Generalization gen : ((GeneralizationSet) PE).getGeneralization())
								{
									if (objectsToCopy.contains(gen.getGeneral()) && objectsToCopy.contains(gen.getSpecific()))
									{
										target.add(copy(eObj));
										break;
									}
								}
							}
						}
					}
					else if (eReference.getName().equals("generalization"))
					{
						for (EObject eObj : source)
						{
							Generalization gen = (Generalization) eObj;
							if (objectsToCopy.contains(gen.getGeneral()) && objectsToCopy.contains(gen.getSpecific()))
							{
								target.add(copy(eObj));
							}
						}
					}
					else target.addAll(copyAll(source));
				}
			}
			else
			{
				EObject childEObject = (EObject)eObject.eGet(eReference);
				copyEObject.eSet(getTarget(eReference), childEObject == null ? null : copy(childEObject));
			}
		}
	}
	
	public enum complementKind
	{
		NONE,
		ASSOCIATIONS,
		SUPERTYPES,
		ATTRIBUTE_TYPES,
		ALL,
	}
}
