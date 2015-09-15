package sml2.util;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator.SubstitutionLabelProvider;
import org.eclipse.emf.ecore.util.EcoreUtil;

import sml2.AllenLink;
import sml2.AllenRelation;
import sml2.ComparativeRelation;
import sml2.ContextFormalLink;
import sml2.EqualsLink;
import sml2.FormalLink;
import sml2.FormalRelation;
import sml2.Function;
import sml2.FunctionParameter;
import sml2.Instantiation;
import sml2.InstantiationLink;
import sml2.Link;
import sml2.Literal;
import sml2.MediationLink;
import sml2.OrderedComparativeLink;
import sml2.PrimitiveComparativeRelation;
import sml2.ReferenceNode;
import sml2.ReflectedParticipant;
import sml2.ReflectedReference;
import sml2.SMLModel;
import sml2.SituationParticipant;
import sml2.SituationType;
import sml2.SituationTypeAssociation;
import sml2.SituationTypeElement;
import sml2.SituationTypedElement;
import sml2.TemporalKind;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Mediation;

public class Sml2SubstitutionLabelProvider implements SubstitutionLabelProvider
{

	@Override
	public String getObjectLabel(EObject eObject)
	{
		try
		{
			if (eObject instanceof SMLModel)
				return MessageFormat.format("<{1}> {0}", ((SMLModel) eObject).getContextModel().getName(),
						eObject.eClass().getName());
						
			else if (eObject instanceof SituationType)
				return MessageFormat.format("<{1}> {0}", ((SituationType) eObject).getName(),
						eObject.eClass().getName());
			
			else if (eObject instanceof SituationTypeAssociation)
			{
				String name;
				if (eObject instanceof FormalRelation)
				{
					name = ((FormalRelation) eObject).isNegated() ? "not ":"";
					
					if (eObject instanceof EqualsLink)
						name += "equals";
					
					else if (eObject instanceof OrderedComparativeLink)
						name += ((OrderedComparativeLink) eObject).getType().getName();
					
					else if (eObject instanceof AllenLink)
						name += ((AllenLink) eObject).getType().getName();
					
					else if (eObject instanceof InstantiationLink)
						name += "instanceOf";
					
					else if (eObject instanceof ContextFormalLink)
						name = ((ContextFormalLink) eObject).getType().getName();
				}
				
				else if (eObject instanceof MediationLink)
					name = ((MediationLink) eObject).getType().getName();
				
				else if (eObject instanceof FunctionParameter)
					name = ((FunctionParameter) eObject).getLabel();
				
				else //AttributeLink
					name = "";
				
				return MessageFormat.format("<{1}> {0} ({2} -> {3})", name, eObject.eClass().getName(), 
						getObjectLabel(((SituationTypeAssociation) eObject).getSource()), 
						getObjectLabel(((SituationTypeAssociation) eObject).getTarget()));
			}
			
			else if (eObject instanceof SituationTypeElement)
			{
				String name;
				if (eObject instanceof SituationParticipant)
					name = (((SituationParticipant) eObject).getTemporality().equals(TemporalKind.PAST) ? "Past " : "")
						+((SituationType)((SituationParticipant) eObject).getType()).getName();
				
				else if (eObject instanceof SituationTypedElement)
					name = ((RefOntoUML.NamedElement)((SituationTypedElement) eObject).getType()).getName();
				
				else if (eObject instanceof Function)
					name = ((Function) eObject).getName();
				
				else if (eObject instanceof Literal)
					name = ((Literal) eObject).getValue()+":"+((Literal) eObject).getType().getName();
				
				else // is a ReflectedParticipant
					name = getObjectLabel(((ReflectedParticipant) eObject).getParticipant());
				
				return MessageFormat.format("<{1}> {0} (from {2})", name, eObject.eClass().getName(), 
						((SituationTypeElement) eObject).getSituation().getName());
			}
			
			else if (eObject instanceof ReferenceNode)
				return MessageFormat.format("<{1}> {0}", ((ReferenceNode)eObject).getLabel(), eObject.eClass().getName());
			
			else if (eObject instanceof ReflectedReference)
				return MessageFormat.format("<{1}> {0}", ((ReflectedReference)eObject).getReference().getLabel(), eObject.eClass().getName());
		}
		catch (NullPointerException e)
		{
			return "<"+eObject.eClass().getName()+">";
		}
		
		return "<"+eObject.eClass().getName()+">";
	}

	@Override
	public String getFeatureLabel(EStructuralFeature eStructuralFeature)
	{
		return MessageFormat.format("{0}:{1}",getObjectLabel(eStructuralFeature.eContainer()),
				eStructuralFeature.getName());
	}

	@Override
	public String getValueLabel(EDataType eDataType, Object value) {
		return EcoreUtil.getIdentification(eDataType)+":"+value;
	}
}
