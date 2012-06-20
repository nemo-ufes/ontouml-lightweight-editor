// Roberto Carraretto 2010
package br.ufes.inf.nemo.ontouml.transformation.abfront2ref;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

// TODO: Class Attributes? (Similar to DataTypes)

public class Transformation
{
	// Creates RefOntoUML Objects
	RefOntoUML.RefOntoUMLFactory myfactory;
	// The master container that engulfs all RefOntoUML Objects
	RefOntoUML.Model mymodel;
	// Maps OntoUML Elements in RefOntoUML Elements (auxiliar for Properties, Generalizations and GeneralizationSets)  
	HashMap<OntoUML.Element, RefOntoUML.Element> mymap;
	// Things that (sometimes) can't be processed until I've read all the objects in the file
	LinkedList<OntoUML.Element> todolist;
	
	// Constructor
	public Transformation()
	{
		myfactory = RefOntoUML.RefOntoUMLFactory.eINSTANCE;
		mymodel = myfactory.createModel();
		mymap = new HashMap<OntoUML.Element, RefOntoUML.Element>();	
		todolist = new LinkedList<OntoUML.Element>();
	}
	
	public EObject getModel()
	{
		return mymodel;
	}
	
	public void ProcessEObject (EObject obj)
	{
		// A ordem de processamento: Classes, DataTypes / Associations / Generalizations, Outros (?) / GeneralizationSets 
		
		if (obj instanceof OntoUML.Class || obj instanceof OntoUML.Datatype)
		{
			DealEObject (obj);
			
			// I have to process attributes later
			if (obj instanceof OntoUML.Datatype)
				todolist.addLast((OntoUML.Element) obj);
		}
		else
		{
			if (obj instanceof OntoUML.GeneralizationSet)
			{
				// GeneralizationSets no final
				todolist.addLast((OntoUML.Element) obj);
			}
			else
			{
				if (obj instanceof OntoUML.Association)
				{
					// Associations no inicio
					todolist.addFirst((OntoUML.Element) obj);
				}
				else
				{
					// Qualquer outra coisa, depois das Associations
					int i = 0;
					for (Iterator<OntoUML.Element> it = todolist.iterator(); it.hasNext();)
					{
						if (it.next() instanceof OntoUML.Association)
							i++;
						else
							break;
					}
					
					todolist.add(i, (OntoUML.Element) obj);
				}
			}
			// talvez o melhor mesmo seja uma lista para cada tipo de Elemento e depois percorrer cada lista na ordem correta de processamento
			// ou colocar em uma lista unica e depois dar um Sort baseado no instanceof 
		}
	}
	
	public void ProcessAll()
	{
		for (OntoUML.Element el : todolist)
		{
			if (el instanceof OntoUML.Datatype)
				ProcessDataTypeAttributes ((OntoUML.Datatype)el);
			else
				DealEObject (el);
		}
	}
	
	// Deals with Alessander's OntoUML Objects
	private void DealEObject (EObject obj)
	{
		// The goal is to find the leaf metaclass
		// Eventually check for some support metaclass for optimization reasons
		
		// Classes		
		if (obj instanceof OntoUML.Class)
		{
			// RigidSortal
			if (obj instanceof OntoUML.Kind)
			{
				DealKind ((OntoUML.Kind) obj);
			}
			else if (obj instanceof OntoUML.SubKind)
			{
				DealSubKind ((OntoUML.SubKind) obj);
			}
			else if (obj instanceof OntoUML.Collective)
			{
				DealCollective ((OntoUML.Collective) obj);
			}
			else if (obj instanceof OntoUML.Quantity)
			{
				DealQuantity ((OntoUML.Quantity) obj);
			}
			
			// AntiRigidSortal
			else if (obj instanceof OntoUML.Role)
			{
				DealRole ((OntoUML.Role) obj);
			}
			else if (obj instanceof OntoUML.Phase)
			{
				DealPhase ((OntoUML.Phase) obj);
			}
			
			// MixinClass
			else if (obj instanceof OntoUML.Category)
			{
				DealCategory ((OntoUML.Category) obj);
			}
			else if (obj instanceof OntoUML.Mixin)
			{
				DealMixin ((OntoUML.Mixin) obj);
			}
			else if (obj instanceof OntoUML.RoleMixin)
			{
				DealRoleMixin ((OntoUML.RoleMixin) obj);
			}
			
			// Moment
			else if (obj instanceof OntoUML.Relator)
			{
				DealRelator ((OntoUML.Relator) obj);
			}
			else if (obj instanceof OntoUML.Mode)
			{
				DealMode ((OntoUML.Mode) obj);
			}
		}
		
		// Association
		else if (obj instanceof OntoUML.Association)
		{
			if (obj instanceof OntoUML.MaterialAssociation)
			{
				DealMaterialAssociation ((OntoUML.MaterialAssociation) obj);			
			}
			else if (obj instanceof OntoUML.FormalAssociation)
			{
				DealFormalAssociation ((OntoUML.FormalAssociation) obj);
			}
		}
		
		// Meroynimc
		else if (obj instanceof OntoUML.Meronymic)
		{

			if (obj instanceof OntoUML.componentOf)
			{
				DealComponentOf ((OntoUML.componentOf) obj);
			}
			else if (obj instanceof OntoUML.memberOf)
			{
				DealMemberOf ((OntoUML.memberOf) obj);
			}
			else if (obj instanceof OntoUML.subCollectionOf)
			{
				DealSubCollectionOf ((OntoUML.subCollectionOf) obj);
			}
			else if (obj instanceof OntoUML.subQuantityOf)
			{
				DealSubQuantityOf ((OntoUML.subQuantityOf) obj);
			}
		}
		
		// DependencyRelationship
		else if (obj instanceof OntoUML.DependencyRelationship)
		{
			if (obj instanceof OntoUML.Mediation)
			{
				DealMediation((OntoUML.Mediation) obj);
			}
			else if (obj instanceof OntoUML.Derivation)
			{
				DealDerivation((OntoUML.Derivation) obj);
			}
			else if (obj instanceof OntoUML.Characterization)
			{
				DealCharacterization ((OntoUML.Characterization) obj);
			}
		}
		
		// Datatype
		else if (obj instanceof OntoUML.Datatype)
		{
			DealDataType ((OntoUML.Datatype) obj);
		}
		// DatatypeRelationship (it doesn't exist in UML)
		else if (obj instanceof OntoUML.DatatypeRelationship)
		{
			DealDataTypeRelationship ((OntoUML.DatatypeRelationship) obj);
		}
				
		// Generalization (apenas o container do Alessander permite isso) 
		else if (obj instanceof OntoUML.Generalization)
		{
			DealGeneralization((OntoUML.Generalization) obj);
		}
		// GeneralizationSet
		else if (obj instanceof OntoUML.GeneralizationSet)
		{	
			DealGeneralizationSet((OntoUML.GeneralizationSet) obj);
		}
		
		else
		{
			System.err.println("Unsupported metaclass: " + obj.toString());
		}
	}
	
	// Add the packageable element to the model
	public void AddPackageableElement (RefOntoUML.PackageableElement pe)
	{
		mymodel.getPackagedElement().add(pe);
	}
	
	// Relate Classifiers and Generalizations
	// This is important for solving Generalizations and Properties (relating Classifiers) and GeneralizationSets (relating Generalizations)
	public void RelateElements (OntoUML.Element c1, RefOntoUML.Element c2)
	{
		mymap.put(c1, c2);
	}
	
	public void DealNamedElement (OntoUML.NamedElement ne1, RefOntoUML.NamedElement ne2)
	{
		System.out.println(ne1.getName());
		ne2.setName(ne1.getName());
	}
	
	public void DealClassifier (OntoUML.Classifier c1, RefOntoUML.Classifier c2)
	{
		DealNamedElement (c1, c2);
		AddPackageableElement (c2);
		// Important for Generalization, Property
		RelateElements (c1, c2);
		// isAbstract
		c2.setIsAbstract(c1.isIsAbstract());
	}
	
	public void DealKind (OntoUML.Kind k1)
	{
		System.out.print("<kind> ");
		RefOntoUML.Kind k2 = myfactory.createKind();
		DealClassifier (k1, k2);
	}
	
	public void DealSubKind (OntoUML.SubKind sk1)
	{
		System.out.print("<subkind> ");
		RefOntoUML.SubKind sk2 = myfactory.createSubKind();
		DealClassifier (sk1, sk2);		
	}
	
	public void DealCollective (OntoUML.Collective c1)
	{
		System.out.print("<collective> ");
		RefOntoUML.Collective c2 = myfactory.createCollective();
		DealClassifier (c1, c2);
		c2.setIsExtensional(c1.isIsExtensional());
	}
	
	public void DealQuantity (OntoUML.Quantity q1)
	{
		System.out.print("<quantity> ");
		RefOntoUML.Quantity q2 = myfactory.createQuantity();
		DealClassifier (q1, q2);
	}
	
	public void DealRole (OntoUML.Role r1)
	{
		System.out.print("<role> ");
		RefOntoUML.Role r2 = myfactory.createRole();	
		DealClassifier (r1, r2);
	}
	
	public void DealPhase (OntoUML.Phase ph1)
	{
		System.out.print("<phase> ");
		RefOntoUML.Phase ph2 = myfactory.createPhase();
		DealClassifier (ph1, ph2);
	}
	
	public void DealCategory (OntoUML.Category ct1)
	{
		System.out.print("<category> ");
		RefOntoUML.Category ct2 = myfactory.createCategory();	
		DealClassifier (ct1, ct2);		
	}
	
	public void DealMixin (OntoUML.Mixin m1)
	{
		System.out.print("<mixin> ");
		RefOntoUML.Mixin m2 = myfactory.createMixin();
		DealClassifier (m1, m2);
	}
	
	public void DealRoleMixin (OntoUML.RoleMixin r1)
	{
		System.out.print("<roleMixin> ");
		RefOntoUML.RoleMixin r2 = myfactory.createRoleMixin();
		DealClassifier (r1, r2);
	}
	
	public void DealRelator (OntoUML.Relator r1)
	{
		System.out.print("<relator> ");
		RefOntoUML.Relator r2 = myfactory.createRelator();
		DealClassifier (r1, r2);
	}
	
	public void DealMode (OntoUML.Mode m1)
	{
		System.out.print("<mode> " );
		RefOntoUML.Mode m2 = myfactory.createMode();
		DealClassifier (m1, m2);
	}
	
	public void DealDataType (OntoUML.Datatype dt1)
	{
		System.out.print("[datatype] ");
		RefOntoUML.DataType dt2 = myfactory.createDataType();
		DealClassifier (dt1, dt2);
	}

	public void ProcessDataTypeAttributes (OntoUML.Datatype dt1)
	{
		System.out.println("batata");
		RefOntoUML.DataType dt2 = (RefOntoUML.DataType) mymap.get(dt1);
		
		// All owned attributes of the DataType
		for (OntoUML.Property attr1 : dt1.getAttribute())
		{	
			RefOntoUML.Property attr2 = myfactory.createProperty();
			DealProperty(attr1, attr2);
			dt2.getOwnedAttribute().add(attr2);
		}
	}
	
	public void DealProperty (OntoUML.Property p1, RefOntoUML.Property p2)
	{
		System.out.println("    Property (" + p1.getEndType().getName() + "): " + p1.getLower() + " " + p1.getUpper() + " ");
		
		DealNamedElement (p1, p2);
		// isLeaf (RedefinableElement)
		p2.setIsLeaf(p1.isIsLeaf());
		// isStatic (Feature)
		p2.setIsStatic(p1.isIsStatic());
		// isReadOnly (StructuralFeature)
		p2.setIsReadOnly(p1.isIsReadOnly());
		
		// lower, upper (MultiplicityElement)
		RefOntoUML.LiteralInteger lowerValue = myfactory.createLiteralInteger();
		RefOntoUML.LiteralUnlimitedNatural upperValue = myfactory.createLiteralUnlimitedNatural();
		lowerValue.setValue(p1.getLower());
		upperValue.setValue(p1.getUpper()); // I guess this works (-1 corresponds to infinite) 
		
		p2.setLowerValue(lowerValue);
		p2.setUpperValue(upperValue);
			
		// Type (TypedElement)
		RefOntoUML.Type t2 = (RefOntoUML.Type) mymap.get(p1.getEndType()); // NOTE: p1.getType() do Alessander nao funciona, mas tudo bem
		p2.setType(t2);
		
		// isDerived
		p2.setIsDerived(p1.isIsDerived());
	}
	
	public void BindPropertyToAssociation (RefOntoUML.Property p2, RefOntoUML.Association a, boolean isNavigable)
	{
		// memberEnd, ownedEnd, navigableOwnedEnd	
		a.getMemberEnd().add(p2);
		
		// Acho que o editor grafico do Alessander nao suporta Ownership
		// Logo considero que a Association sempre tem posse da Property
		a.getOwnedEnd().add(p2);
		
		// Aparentemente, Alessander implementa navegabilidade como atributo de Property
		// A documentacao da UML implementa isso na associacao
		if (isNavigable)
		{
			a.getNavigableOwnedEnd().add(p2);
		}
		
		// (property's) association
		p2.setAssociation(a);		
	}
	
	public void DealAssociation (OntoUML.Association assoc, RefOntoUML.Association assoc2)
	{
		DealClassifier(assoc, assoc2);
		
		// isDerived
		assoc2.setIsDerived(assoc.isIsDerived());
		
		// Get all memberEnds
		EList<OntoUML.Property> ael = assoc.getAssociationEnd();
		RefOntoUML.Property p2;
		
		for (Iterator<OntoUML.Property> it = ael.iterator(); it.hasNext();)
		{
			OntoUML.Property p1 = it.next();
			p2 = myfactory.createProperty();
			
			DealProperty(p1, p2);
			BindPropertyToAssociation (p2, assoc2, p1.isIsNavigable());
		}
	}
	
	public void DealMaterialAssociation (OntoUML.MaterialAssociation material1)
	{
		System.out.println("<material> " + material1.getName());		
		RefOntoUML.MaterialAssociation material2 = myfactory.createMaterialAssociation();
		DealAssociation (material1, material2);
	}
	
	public void DealFormalAssociation (OntoUML.FormalAssociation formal1)
	{
		System.out.println("<formal> " + formal1.getName());		
		RefOntoUML.FormalAssociation formal2 = myfactory.createFormalAssociation();
		DealAssociation (formal1, formal2);
	}
	
	public void DealDirectedBinaryRelationship (OntoUML.DirectedBinaryRelationship dbr, RefOntoUML.DirectedBinaryAssociation dba)
	{
		DealClassifier (dbr, dba);
	
		// source
		OntoUML.Property p1 = (OntoUML.Property) dbr.getSource().get(0);					
		RefOntoUML.Property p2 = myfactory.createProperty();
		DealProperty(p1, p2);
		BindPropertyToAssociation (p2, dba, p1.isIsNavigable());
		
		// target
		p1 = (OntoUML.Property) dbr.getTarget().get(0);
		p2 = myfactory.createProperty();
		DealProperty(p1, p2);
		BindPropertyToAssociation (p2, dba, p1.isIsNavigable());
	}
	
	public void DealMeronymic (OntoUML.Meronymic m1, RefOntoUML.Meronymic m2)
	{
		DealDirectedBinaryRelationship (m1, m2);
		
		m2.setIsShareable(m1.isIsShareable());
		m2.setIsEssential(m1.isIsEssential());
		m2.setIsInseparable(m1.isIsInseparable());
		m2.setIsImmutablePart(m1.isIsImmutablePart());
		m2.setIsImmutableWhole(m1.isIsImmutableWhole());
	}
	
	public void DealComponentOf (OntoUML.componentOf cmpOf1)
	{
		System.out.print("<componentOf> ");
		RefOntoUML.componentOf cmpOf2 = myfactory.createcomponentOf();
		DealMeronymic (cmpOf1, cmpOf2);
	}
	
	public void DealMemberOf (OntoUML.memberOf m1)
	{
		System.out.print("<memberOf> ");
		RefOntoUML.memberOf m2 = myfactory.creatememberOf();
		DealMeronymic (m1, m2);
	}
	
	public void DealSubCollectionOf (OntoUML.subCollectionOf sc1)
	{
		System.out.print("<subCollectionOf> ");
		RefOntoUML.subCollectionOf sc2 = myfactory.createsubCollectionOf();
		DealMeronymic (sc1, sc2);
	}
	
	public void DealSubQuantityOf (OntoUML.subQuantityOf sq1)
	{
		System.out.print("<subQuantityOf> ");
		RefOntoUML.subQuantityOf sq2 = myfactory.createsubQuantityOf();
		DealMeronymic (sq1, sq2);
	}
	
	public void DealMediation (OntoUML.Mediation mediation1)
	{
		System.out.print("<mediation> ");
		RefOntoUML.Mediation mediation2 = myfactory.createMediation();
		DealDirectedBinaryRelationship (mediation1, mediation2);
	}
	
	public void DealDerivation (OntoUML.Derivation derivation1)
	{
		System.out.print("<derivation> ");		
		RefOntoUML.Derivation derivation2 = myfactory.createDerivation();
		DealDirectedBinaryRelationship (derivation1, derivation2);
	}
	
	public void DealCharacterization (OntoUML.Characterization c1)
	{
		System.out.print("<characterization> ");
		RefOntoUML.Characterization c2 = myfactory.createCharacterization();
		DealDirectedBinaryRelationship (c1, c2);
	}
	
	public void DealDataTypeRelationship (OntoUML.DatatypeRelationship dtr1)
	{
		System.out.print("<dataTypeRelationship> ");
		RefOntoUML.Association a2 = myfactory.createAssociation();
		
		DealClassifier (dtr1, a2);
		
		// source
		OntoUML.Property p1 = (OntoUML.Property) dtr1.getSource().get(0);					
		RefOntoUML.Property p2 = myfactory.createProperty();
		DealProperty(p1, p2);
		BindPropertyToAssociation (p2, a2, p1.isIsNavigable());
		
		// target
		p1 = (OntoUML.Property) dtr1.getTarget().get(0);
		p2 = myfactory.createProperty();
		DealProperty(p1, p2);
		BindPropertyToAssociation (p2, a2, p1.isIsNavigable());
	}
	
	public void DealGeneralization (OntoUML.Generalization gen1)
	{
		// O container do Alessander contem Generalizations
		// O container de uma Generalization deveria ser o Specific
		System.out.print("[Generalization]: ");		
		RefOntoUML.Generalization gen2 = myfactory.createGeneralization();
		
		// O metamodelo do Alessander nao esta de acordo com a especificacao da UML
		// source do generalization *deveria* ser o Specific Classifier
		// target do generalization *deveria* ser o General Classifier
		
		// source (Specific)
		OntoUML.Classifier e1 = (OntoUML.Classifier) gen1.getTarget().get(0); 
		RefOntoUML.Classifier e2 = (RefOntoUML.Classifier) mymap.get(e1);
		System.out.print(e1.getName() + " -> ");
		
		// Poderia ter setado apenas um dos dois (Generalization::Specific, Classifier::Generalization), ja que sao EOpposites
		gen2.setSpecific(e2);
		// O Specific tem posse do generalization
		e2.getGeneralization().add(gen2);

		// target (General)
		e1 = (OntoUML.Classifier) gen1.getSource().get(0); 
		e2 = (RefOntoUML.Classifier) mymap.get(e1);
		System.out.println(e1.getName());

		gen2.setGeneral(e2);

		// Important for GeneralizationSet
		RelateElements (gen1, gen2);
	}
	
	public void DealGeneralizationSet (OntoUML.GeneralizationSet gs1)
	{
		System.out.print("[Generalization Set] ");
		RefOntoUML.GeneralizationSet gs2 = myfactory.createGeneralizationSet();
		
		DealNamedElement(gs1, gs2);
				
		// Add all the generalizations
		for  (Iterator<OntoUML.Generalization> it = gs1.getGeneralization().iterator(); it.hasNext();)
		{
			RefOntoUML.Generalization gen = (RefOntoUML.Generalization) mymap.get(it.next());
			
			// Poderia ter setado apenas um dos dois (GeneralizationSet::Generalization, Generalization::GeneralizationSet), ja que sao EOpposites
			gs2.getGeneralization().add(gen);
			gen.getGeneralizationSet().add(gs2);
		}
		
		// isCovering, isDisjoint
		gs2.setIsCovering(gs1.isIsCovering());
		gs2.setIsDisjoint(gs1.isIsDisjoint());
		
		// They are PackageableElements, don't forget it
		AddPackageableElement (gs2);
	}
}
