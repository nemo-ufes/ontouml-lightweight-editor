package br.ufes.inf.nemo.antipattern.mixrig;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Mixin;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class MixRigOccurrence extends AntipatternOccurrence {

	Mixin mixin;
	ArrayList<Classifier> subtypes;
	boolean isRigid; 
	
	public MixRigOccurrence(Mixin mixin, MixRigAntipattern ap) throws Exception {
		super(ap);
		
		if(mixin==null)
			throw new NullPointerException("MixRig: null mixin provided.");
		
		if(!(mixin instanceof Mixin))
			throw new NullPointerException("MixRig: mixin must be an instance of Mixin.");
		
		this.mixin = mixin;
		
		this.subtypes = new ArrayList<Classifier>();
		this.subtypes.addAll(mixin.children());
		
		if (this.subtypes.size()==0)
			throw new Exception("MixRig: Mixin has no subtypes!");
		
		boolean hasRigid = false;
		boolean hasAntiRigid = false;
		
		for (Classifier child : subtypes) {
			if (child instanceof SubstanceSortal || child instanceof SubKind || child instanceof Category)
				hasRigid = true;
			else
				hasAntiRigid = true;
		}
		
		if(hasRigid && hasAntiRigid)
			throw new Exception("MixRig: Mixin has both rigid and anti-rigid subtypes.");
		
		this.isRigid=!hasAntiRigid;
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(this.mixin);
		selection.addAll(subtypes);
				
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	@Override
	public String toString(){
		String result = "Mixin: "+super.parser.getStringRepresentation(this.mixin) + "\n";
		
		if (isRigid)
			result += "Rigid";
		else
			result+="Anti-Rigid";
		
		result += "\nSubtypes: ";
		
		for (Classifier subtype : this.subtypes) {
			result+="\n\t"+super.parser.getStringRepresentation(subtype);
		}
		return result;
	}
	
	@Override
	public String getShortName() {
		return parser.getStringRepresentation(mixin);
	}

}
