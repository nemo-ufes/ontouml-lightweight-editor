package br.ufes.inf.nemo.antipattern.reprel;


import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;

public class RepRelOccurrence extends AntipatternOccurrence {
	
	private Classifier relator;
	
	private ArrayList<Mediation> mediations = new ArrayList<Mediation>();
	private ArrayList<Mediation> problematicMediations = new ArrayList<Mediation>();
	private ArrayList<Property> mediatedProperties = new ArrayList<Property>();
	private ArrayList<Property> problematicMediatedProperties = new ArrayList<Property>(); 
		
	
	@Deprecated
	public RepRelOccurrence(Relator relator, RepRelAntipattern ap) throws Exception 
	{
		super(ap);
		this.setRelator(relator,parser);
	}
	
	public RepRelOccurrence(Relator relator, HashSet<Property> allMediated, HashSet<Property> problematicMediated, RepRelAntipattern ap) throws Exception 
	{
		super(ap);
		
		this.relator = relator;
		this.mediatedProperties.addAll(allMediated);
		this.problematicMediatedProperties.addAll(problematicMediated);
		
		for (Property p : problematicMediated)
			problematicMediations.add((Mediation) p.getAssociation());
		
		for (Property p : allMediated)
			mediations.add((Mediation) p.getAssociation());
		
		if (problematicMediations.size()<2)
			throw new Exception("RepRel: The required cardinalities are not met for "+parser.getStringRepresentation(relator));

	}
	
	@Deprecated
	public void setRelator (Classifier relator, OntoUMLParser parser) throws Exception {
			
		this.relator = relator;
		
		
		
		for (Mediation med : parser.getAllInstances(Mediation.class)) {
			Property sourceEnd = med.getMemberEnd().get(0);
			Property targetEnd = med.getMemberEnd().get(1);
			
			if(sourceEnd.getType().equals(relator) || relator.allParents().contains(sourceEnd.getType()))
				mediatedProperties.add(sourceEnd);
			else if (targetEnd.getType().equals(relator) || relator.allParents().contains(targetEnd.getType()))
				mediatedProperties.add(targetEnd);
		
		}
		
		setAuxilaryLists();
		
		if (problematicMediations.size()<2)
			throw new Exception("==========\n\t\t\tRepRel: The required cardinalities are not met for "+parser.getStringRepresentation(relator));
	}
	
	@Deprecated
	private void setAuxilaryLists() {
		for (Property end : mediatedProperties) {
			mediations.add((Mediation) end.getAssociation());
			if(end.getUpper()==-1 || end.getUpper()>1){
				problematicMediatedProperties.add(end);
				problematicMediations.add((Mediation) end.getAssociation());
			}
		}
	}

	public ArrayList<Mediation> getMediations() {
		return mediations;
	}

	public ArrayList<Mediation> getProblematicMediations() {
		return problematicMediations;
	}

	public ArrayList<Property> getMediatedProperties() {
		return mediatedProperties;
	}

	public ArrayList<Property> getProblematicMediatedProperties() {
		return problematicMediatedProperties;
	}

	public Classifier getRelator(){
		return relator;
	}
	
	/**
	 * Select in the OntoUML model only those elements related with this antipattern...
	 */
	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(relator);
		for(Mediation m: mediations){
			selection.add(m);
		}
		
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);
		return parser;
	}
	
	
	
	/**
	 * To String method...
	 * 
	 */
	@Override
	public String toString() {
		
		String result;

		result= OntoUMLNameHelper.getTypeAndName(relator,false,false)+"\n"+
				"Highlighted Mediated Types:";
		
		for(Property p: problematicMediatedProperties)
			result+="\n\t"+OntoUMLNameHelper.getTypeAndName(p.getType(),true,false)+" (from: "+OntoUMLNameHelper.getName(p.getOpposite().getType())+" ["+OntoUMLNameHelper.getMultiplicity(p.getOpposite(), true, "..")+"])";
		return result;
	}

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(relator);
	}

	// ========== OUTCOMING FIXES =========
	
	public void changeUpperMult(Mediation m, int n) {
		fix.addAll(fixer.setUpperCardinalityOnRelatorSide(m, n));
	}

	public void createInvariantWithQualities(ArrayList<ArrayList<Mediation>> mMatrix, ArrayList<Integer> nList) {
		fix.addAll(fixer.createAttribute(relator, "startTime", ClassStereotype.DATATYPE, "DateTime"));
		fix.addAll(fixer.createAttribute(relator, "endTime", ClassStereotype.DATATYPE,"DateTime"));
		fix.includeRule(generateOCLDerivationConcurrentWith((Relator)relator)+"\n\n");
		for(ArrayList<Mediation> mList: mMatrix){
			fix.includeRule(generateOCLInvariantWithQualities(mList,nList.get(mMatrix.indexOf(mList))));	
		}		
	}

	public void createInvariant(ArrayList<ArrayList<Mediation>> mMatrix, ArrayList<Integer> nList) {
		for(ArrayList<Mediation> mList: mMatrix){
			fix.includeRule(generateOCLInvariant(mList,nList.get(mMatrix.indexOf(mList))));	
		}		
	}
	
	public String generateOCLInvariant(ArrayList<Mediation> mediationList, int n)
	{	
		
		String relatorName = addQuotes(this.relator.getName());
	
		String expr = new String();
		int i=0;
		for(Mediation m: mediationList)
		{
			Property mediatedEnd = OntoUMLParser.getMediatedEnd(m);
			fix.addAll(fixer.fixPropertyName(mediatedEnd));
			
			String mediatedName = addQuotes(mediatedEnd.getName());
			String localExpr = "r."+mediatedName+" = self."+mediatedName;
			
			if(i==0) 
				expr += localExpr;
			else 
				expr += " and "+localExpr;			
			
			i++;
		}
		return
		"context "+relatorName+"\n"+
		"inv: "+relatorName+".allInstances()->select( r : "+relatorName+" | r<>self and "+expr+")->size() = "+n;
	}
	
	public String generateOCLDerivationConcurrentWith(Relator relator)
	{
		String relatorName = "_'"+relator.getName()+"'";
		return 
		"context "+relatorName+"::concurrentWith(r: Relator):Boolean"+"\n"+
		"body: self.startTime=r.startTime and self.endTime=r.endTime or"+"\n"+
		"self.startTime<r.startTime and self.endTime>r.endTime or"+"\n"+		
		"self.startTime>r.startTime and self.endTime<r.endTime or"+"\n"+
		"self.startTime>r.startTime and self.startTime<r.endTime or"+"\n"+
		"self.startTime<r.startTime and self.endTime>r.endTime ";
	}
	
	public String generateOCLInvariantWithQualities(ArrayList<Mediation> mediationList, int n)
	{
		String relator = "_'"+mediationList.get(0).relator().getName()+"'";
		String expr = new String();
		
		int i=0;
		for(Mediation m: mediationList)
		{
			Property mediatedEnd = OntoUMLParser.getMediatedEnd(m);
			fix.addAll(fixer.fixPropertyName(mediatedEnd));
			
			String mediatedName = addQuotes(mediatedEnd.getName());	
			String localExpr = "r."+mediatedName+" = self."+mediatedName;
			
			if(i==0) 
				expr += localExpr;
			else 
				expr += " and "+localExpr;			
			
			i++;
		}
		expr+= " and r.concurrentWith(self)";
		
		return 	"context "+relator+"\n"+
				"inv: "+relator+".allInstances()->select( r : "+relator+" | r<>self and "+expr+")->size() = "+n+"\n";		
	}
}
