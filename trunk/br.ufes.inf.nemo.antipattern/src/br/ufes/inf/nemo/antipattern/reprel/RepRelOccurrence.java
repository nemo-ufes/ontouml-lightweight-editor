package br.ufes.inf.nemo.antipattern.reprel;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RepRelOccurrence extends AntipatternOccurrence {
	
	private Classifier relator;
	private ArrayList<Mediation> mediations, problematicMediations;
	private ArrayList<Property> mediatedProperties, problematicMediatedProperties;

	
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
	 * Constructor
	 *
	 * @param relator
	 * @param parser
	 * @throws Exception
	 */
	public RepRelOccurrence(Relator relator, RepRelAntipattern ap) throws Exception 
	{
		super(ap);
		this.setRelator(relator,parser);
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
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);
		return parser;
	}
	
	/**
	 * Set AntiPattern data...
	 * 
	 * @param mediation
	 * @throws Exception
	 */
	public void setRelator (Classifier relator, OntoUMLParser parser) throws Exception {
			
		this.relator = relator;
		
		mediations = new ArrayList<Mediation>();
		problematicMediations = new ArrayList<Mediation>();
		mediatedProperties = new ArrayList<Property>();
		problematicMediatedProperties = new ArrayList<Property>(); 
		
		for (Mediation med : parser.getAllInstances(Mediation.class)) {
			Property sourceEnd = med.getMemberEnd().get(0);
			Property targetEnd = med.getMemberEnd().get(1);
			
			if(sourceEnd.getType().equals(relator) || relator.allParents().contains(sourceEnd.getType()))
				mediatedProperties.add(sourceEnd);
			else if (targetEnd.getType().equals(relator) || relator.allParents().contains(targetEnd.getType()))
				mediatedProperties.add(targetEnd);
		
		}
		System.out.println("Size Mediation: "+mediatedProperties.size());
		
		for (Property end : mediatedProperties) {
			mediations.add((Mediation) end.getAssociation());
			if(end.getUpper()==-1 || end.getUpper()>1){
				problematicMediatedProperties.add(end);
				problematicMediations.add((Mediation) end.getAssociation());
			}
		}
		
		if (problematicMediations.size()<2)
			throw new Exception("==========\n\t\t\tRepRel: The required cardinalities are not met for "+parser.getStringRepresentation(relator));
	}
	
	/**
	 * To String method...
	 * 
	 */
	@Override
	public String toString() {
		
		String result;

		result= super.parser.getStringRepresentation(this.relator)+"\n"+
				"Mediations:";
		
		for(Property p: mediatedProperties)
			result+="\n\t"+super.parser.getStringRepresentation(p.getOpposite())+"  -  "+super.parser.getStringRepresentation(p);
	
		return result;
	}
	
	public String explanation(OntoUMLParser parser) throws Exception{
		String expl = 	"The relator \'"+relator.getName()+"\' mediates "+mediations.size()+" types: ";
						
		for (int i = 0; i < mediatedProperties.size(); i++) {
			if (i==mediatedProperties.size()-1)
				expl += " and ";
			else if (i!=0)
				expl += ", ";
			
			expl+="'"+mediatedProperties.get(i).getType().getName()+"'";
		}
		
		expl += ".\n";
		
		if(mediations.size()==problematicMediations.size())
			expl+="All";
		else
			expl += "Exactly "+problematicMediations.size();
		
		expl += " of them are connected to '"+relator.getName()+"\' through mediations which have an upper bound multiplicity in the '"+relator.getName()+"\' side greater than one. They are:";
		
		for (Mediation m : problematicMediations) {
			expl+="\n"+(parser.getMediated(m).getName())+" ";
			
			Property relatorEnd = parser.getRelatorEnd(m);
			Property mediatedEnd = parser.getMediatedEnd(m);
			
			if (mediatedEnd.getLower()==-1)
				expl+="0";
			else expl+= mediatedEnd.getLower();
			
			expl+="..";
			
			if (mediatedEnd.getUpper()==-1)
				expl+="*";
			else expl+= mediatedEnd.getUpper();
			
			expl+=" "+m.getName()+" ";
			
			if (relatorEnd.getLower()==-1)
				expl+="0";
			else expl+= relatorEnd.getLower();
			
			expl+="..";
			
			if (relatorEnd.getUpper()==-1)
				expl+="*";
			else expl+= relatorEnd.getUpper();
		
			expl += " "+relator.getName();
		}
			
		return expl;
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
		String relator = "_'"+mediationList.get(0).relator().getName()+"'";
		
		String expr = new String();
		int i=0;
		for(Mediation m: mediationList)
		{
			Property src = m.getMemberEnd().get(0);
			String srcName = new String();
			Property tgt = m.getMemberEnd().get(1);
			String tgtName = new String();
			if(src.getName()==null || src.getName().isEmpty())  srcName = src.getType().getName().toLowerCase().trim();
			if(tgt.getName()==null || tgt.getName().isEmpty())  tgtName = tgt.getType().getName().toLowerCase().trim();
			srcName = "_'"+srcName+"'";
			tgtName = "_'"+tgtName+"'";			
			String localExpr = new String();
			if (src.getType() instanceof Relator){
				localExpr += "r."+srcName+" = self."+srcName;
			}else if (tgt.getType() instanceof Relator){
				localExpr += "r."+tgtName+" = self."+tgtName;
			}
			if(i==0) expr += localExpr;
			else expr += " and "+localExpr;			
			i++;
		}
		return
		"context "+relator+"\n"+
		"inv: "+relator+".allInstances()->select( r : "+relator+" | r<>self and "+expr+")->size() = "+n;
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
			Property src = m.getMemberEnd().get(0);
			String srcName = new String();
			Property tgt = m.getMemberEnd().get(1);
			String tgtName = new String();
			if(src.getName()==null || src.getName().isEmpty())  srcName = src.getType().getName().toLowerCase().trim();
			if(tgt.getName()==null || tgt.getName().isEmpty())  tgtName = tgt.getType().getName().toLowerCase().trim();
			srcName = "_'"+srcName+"'";
			tgtName = "_'"+tgtName+"'";			
			String localExpr = new String();
			if (src.getType() instanceof Relator){
				localExpr += "r."+srcName+" = self."+srcName;
			}else if (tgt.getType() instanceof Relator){
				localExpr += "r."+tgtName+" = self."+tgtName;
			}
			if(i==0) expr += localExpr;
			else if (i<=mediationList.size()) expr += " and "+localExpr;			
			i++;
		}
		expr+= " and r.concurrentWith(self)";
		return 
		"context "+relator+"\n"+
		"inv: "+relator+".allInstances()->select( r : "+relator+" | r<>self and "+expr+")->size() = "+n+"\n";		
	}
}
