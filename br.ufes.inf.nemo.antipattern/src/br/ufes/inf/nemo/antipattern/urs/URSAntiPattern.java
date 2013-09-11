package br.ufes.inf.nemo.antipattern.urs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Package;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.AntiPatternUtil;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.ac.ACAntiPattern;
import br.ufes.inf.nemo.antipattern.rwrt.RWRTAntiPattern;
import br.ufes.inf.nemo.common.ocl.OCLQueryExecuter;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlparser.ParsingElement;

//Undefined Role Specialization
public class URSAntiPattern extends Antipattern{

	private Role role;
	private ArrayList<Mediation> roleMediations;
	private HashMap<Role,ArrayList<Mediation>> subRolesHash;
	private ArrayList<Role> undefinedRoles;
	
	public URSAntiPattern(Role role, OntoUMLParser parser) throws Exception {
		
		this.role = role;
		
		this.roleMediations = new ArrayList<Mediation>();
		for (Mediation m : parser.getAllInstances(Mediation.class)) {
			if (parser.getMediated(m).equals(role) && !roleMediations.contains(m))
				roleMediations.add(m);
		}
		
		if (roleMediations.size()==0)
			throw new Exception("Role is not defined to characterize the antipattern!!");
		
		this.subRolesHash = new HashMap<Role, ArrayList<Mediation>>();
		this.undefinedRoles = new ArrayList<Role>(); 
		
		for (Classifier child : parser.getAllChildren(role)) {
			
			if (child instanceof Role) {
				
				ArrayList<Mediation> subRoleMediations = new ArrayList<Mediation>();
				
				for (Mediation m : parser.getAllInstances(Mediation.class)) {
					if (parser.getMediated(m).equals(child) && !subRoleMediations.contains(m))
						subRoleMediations.add(m);
				}
				
				this.subRolesHash.put((Role) child, subRoleMediations);
				
				if (subRoleMediations.size()==0)
					this.undefinedRoles.add((Role) child);
				
			}
		}
		
		if (this.undefinedRoles.size()==0)
			throw new Exception("The are no undefined subroles!!");
		
	}

	@Override
	public OntoUMLParser setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(role);
		selection.addAll(roleMediations);
		
		for (ArrayList<Mediation> subRoleMediations : subRolesHash.values()) {
			selection.addAll(subRoleMediations);
		}
		
		selection.addAll(subRolesHash.keySet());
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	private static String oclQuery = 	"Role.allInstances()->select(x:Role | "+
										"	Mediation.allInstances()->select(m:Mediation | " +
										"		m.endType->includes(x))->size()>0 "+
										"		and "+
										"		x.allChildren()->select(y:Classifier| "+
										"			y.oclIsTypeOf(Role) "+
										"			and "+
										"			Mediation.allInstances()->select(m:Mediation | " +
										"				m.endType->includes(y))->size()=0)->size()>0 "+
										"	)";
	
	public static ArrayList<URSAntiPattern> identify(OntoUMLParser parser) {
	
		ArrayList<URSAntiPattern> result = new ArrayList<URSAntiPattern>();
		
		try {
			Copier copier = new Copier();
			Package model = parser.createPackageFromSelections(copier);
			Collection<Role> query_result = new ArrayList<>();		
			Object o = OCLQueryExecuter.executeQuery(oclQuery, (EClassifier)model.eClass(), model);
			
			query_result.addAll((Collection<Role>)o);
			
			for (Role a : query_result) 
			{
				Role original = (Role) AntiPatternUtil.getOriginal(a, copier);
				
				URSAntiPattern urs;
				
				try {
					urs = new URSAntiPattern(original, parser);
					result.add(urs);
				} catch (Exception e) { }
			}		
			
		} catch (Exception e) { }
		
		return result;
		
	}
	
	@Override
	public String toString(){
		
		String result = "Defined Role: "+ role.getName()+"\n"+
						"Relators: ";
		
		for (int i = 0; i < roleMediations.size(); i++) {
			if(i>0)
				result += ", ";
			result += roleMediations.get(i).relator().getName();
		}
		
		result+="\nUndefined: \n";
		
		for (Role undefinedRole : this.undefinedRoles) {
			result += "\t"+undefinedRole.getName()+"\n";
		}
		
		return result;
		
	}
}


