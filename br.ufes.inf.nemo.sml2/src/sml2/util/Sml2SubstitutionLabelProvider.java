package sml2.util;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator.SubstitutionLabelProvider;
import org.eclipse.emf.ecore.util.EcoreUtil;

import sml2.AllenLink;
import sml2.AttributeReference;
import sml2.ContextFormalLink;
import sml2.EntityParticipant;
import sml2.EqualsLink;
import sml2.FormalRelation;
import sml2.Function;
import sml2.FunctionParameter;
import sml2.InstantiationLink;
import sml2.MediationLink;
import sml2.ModeReference;
import sml2.OrderedComparativeLink;
import sml2.QualityLiteral;
import sml2.ReferenceNode;
import sml2.RelatorParticipant;
import sml2.SMLModel;
import sml2.SelfReference;
import sml2.SituationParticipant;
import sml2.SituationType;
import sml2.SituationTypeAssociation;
import sml2.SituationTypeElement;
import sml2.TemporalKind;
import sml2.TypeLiteral;

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
					
					if (eObject instanceof AllenLink)
						name += ((AllenLink) eObject).getType().getName();
					
					else if (eObject instanceof ContextFormalLink)
						name = ((ContextFormalLink) eObject).getType().getName();
					
					else if (eObject instanceof EqualsLink)
						name += "equals";
					
					else if (eObject instanceof InstantiationLink)
						name += "instanceOf";
					
					else if (eObject instanceof OrderedComparativeLink)
						name += ((OrderedComparativeLink) eObject).getType().getName();
				}
				
				else if (eObject instanceof MediationLink)
					name = ((MediationLink) eObject).getType().getName();
				
				else //AttributeLink
					name = "";
				
				return MessageFormat.format("<{1}> {0} ({2} -> {3})", name, eObject.eClass().getName(), 
						getObjectLabel(((SituationTypeAssociation) eObject).getSource()), 
						getObjectLabel(((SituationTypeAssociation) eObject).getTarget()));
			}
			
			else if (eObject instanceof SituationTypeElement)
			{
				String name = new String();
				if (eObject instanceof AttributeReference)
					name = ((AttributeReference) eObject).getType().getName();
					
				else if (eObject instanceof EntityParticipant)
					name = ((EntityParticipant) eObject).getType().getName();
				
				else if (eObject instanceof Function)
					name = ((Function) eObject).getName();
				
				else if (eObject instanceof ModeReference)
					name = ((ModeReference) eObject).getType().getName();
					
				else if (eObject instanceof QualityLiteral)
					name = ((QualityLiteral) eObject).getValue()+":"+((QualityLiteral) eObject).getType().getName();
				
				else if (eObject instanceof RelatorParticipant)
					name = ((RefOntoUML.Relator)((RelatorParticipant) eObject).getType()).getName();
				
				else if (eObject instanceof SelfReference)
					name = getObjectLabel(((SelfReference) eObject).getSituation());
				
				else if (eObject instanceof SituationParticipant)
					name = (((SituationParticipant) eObject).getTemporality().equals(TemporalKind.PAST) ? "Past " : "")
						+((SituationType)((SituationParticipant) eObject).getType()).getName();
				
				else if (eObject instanceof TypeLiteral)
					name = ((TypeLiteral) eObject).getType().getName();
				
				return MessageFormat.format("<{1}> {0} (from {2})", name, eObject.eClass().getName(), 
						((SituationTypeElement) eObject).getSituation().getName());
			}
			
			else if (eObject instanceof ReferenceNode)
				return MessageFormat.format("<{1}> {0}", ((ReferenceNode)eObject).getLabel(), eObject.eClass().getName());
			
			else if (eObject instanceof FunctionParameter)
				return MessageFormat.format("<{1}> {0} ({2})", ((FunctionParameter) eObject).getLabel(),
					eObject.eClass().getName(), getObjectLabel(((FunctionParameter) eObject).getParameter()));
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
