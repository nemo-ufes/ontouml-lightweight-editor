package br.ufes.inf.nemo.antipattern.mrgs;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Mixin;
import RefOntoUML.Package;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.RoleMixin;
import br.ufes.inf.nemo.antipattern.AntiPatternUtil;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.common.ocl.OCLQueryExecuter;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

//Mixed Rigidity in Generalization Set
public class MRGSAntiPattern extends Antipattern{

	GeneralizationSet gs;
	ArrayList<Classifier> specifics;
	ArrayList<Classifier> rigidSpecifics;
	ArrayList<Classifier> antiRigidSpecifics;
	ArrayList<Classifier> semiRigidSpecifics;
	
	public MRGSAntiPattern(GeneralizationSet gs, OntoUMLParser parser) throws Exception {
		
		this.gs = gs;
		this.specifics = new ArrayList<Classifier>();
		this.rigidSpecifics = new ArrayList<Classifier>();
		this.antiRigidSpecifics = new ArrayList<Classifier>();
		this.semiRigidSpecifics = new ArrayList<Classifier>();
		
		for (Generalization g : gs.getGeneralization()) {
			if (parser.isSelected(g) && parser.isSelected(g.getSpecific())){
				
				Classifier specific = g.getSpecific();
				
				if (specific instanceof RigidSortalClass || specific instanceof Category )
					rigidSpecifics.add(specific);
				else if (specific instanceof AntiRigidSortalClass || specific instanceof RoleMixin)
					antiRigidSpecifics.add(specific);
				else if (specific instanceof Mixin)
					semiRigidSpecifics.add(specific);
			}
		}
		
		if (rigidSpecifics.size()==0 && antiRigidSpecifics.size()==0)
			throw new Exception("Only semi rigid specifics.");
		if (rigidSpecifics.size()==0 && semiRigidSpecifics.size()==0)
			throw new Exception("Only anti rigid specifics.");
		if (semiRigidSpecifics.size()==0 && antiRigidSpecifics.size()==0)
			throw new Exception("Only rigid specifics.");
		
		specifics.addAll(antiRigidSpecifics);
		specifics.addAll(rigidSpecifics);
		specifics.addAll(semiRigidSpecifics);
	}

	private static String oclQuery = 	"GeneralizationSet.allInstances()->select(gs:GeneralizationSet |"+
										"	not gs.parent().oclIsTypeOf(Mixin)"+
										"	and"+
										"	("+
										"		("+
										"		gs.children()->exists(x | x.oclIsKindOf(RigidSortalClass) or x.oclIsKindOf(Category))"+ 
										"		and "+
										"		gs.children()->exists(x | x.oclIsKindOf(AntiRigidSortalClass) or x.oclIsKindOf(RoleMixin))"+
										"		)"+
										"		or"+
										"		("+
										"		gs.children()->exists(x | x.oclIsKindOf(RigidSortalClass) or x.oclIsKindOf(Category))"+ 
										"		and "+
										"		gs.children()->exists(x | x.oclIsKindOf(Mixin))"+
										"		)"+
										"		or"+
										"		("+
										"		gs.children()->exists(x | x.oclIsKindOf(Mixin))"+ 
										"		and "+
										"		gs.children()->exists(x | x.oclIsKindOf(AntiRigidSortalClass) or x.oclIsKindOf(RoleMixin))"+
										"		)"+
										"	)"+
										")";


	public static ArrayList<MRGSAntiPattern> identify(OntoUMLParser parser) {
		
		ArrayList<MRGSAntiPattern> result = new ArrayList<MRGSAntiPattern>();
		
		try {
			Copier copier = new Copier();
			Package model = parser.createPackageFromSelections(copier);
			Collection<GeneralizationSet> query_result = new ArrayList<>();		
			Object o = OCLQueryExecuter.executeQuery(oclQuery, (EClassifier)model.eClass(), model);
			
			query_result.addAll((Collection<GeneralizationSet>)o);
			
			for (GeneralizationSet a : query_result) 
			{
				GeneralizationSet original = (GeneralizationSet) AntiPatternUtil.getOriginal(a, copier);
				
				MRGSAntiPattern mrgs;
				
				try {
					mrgs = new MRGSAntiPattern(original, parser);
					result.add(mrgs);
				} catch (Exception e) { }
			}		
			
		} catch (Exception e) { }
		
		return result;
		
	}
	
	@Override
	public OntoUMLParser setSelected(OntoUMLParser parser) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString(){
		String result = "GenSet: "+gs.getName()+"\n"+
						"General: "+gs.getGeneralization().get(0).getGeneral().getName()+"\n"+
						"Specifics:";
		
		for (Generalization g : gs.getGeneralization()){
			result += "\n\t"+g.getSpecific().getName();
		}
		
		return result;
		
	}

}
