package br.ufes.inf.nemo.sml2alloy;

import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.emf.ecore.EStructuralFeature;

import sml2.Participant;
import sml2.SituationType;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.AlloyModule;
import br.ufes.inf.nemo.alloy.ArrowOperation;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.BinaryOperator;
import br.ufes.inf.nemo.alloy.CommandDeclaration;
import br.ufes.inf.nemo.alloy.Declaration;
import br.ufes.inf.nemo.alloy.DisjointExpression;
import br.ufes.inf.nemo.alloy.Expression;
import br.ufes.inf.nemo.alloy.FunctionDeclaration;
import br.ufes.inf.nemo.alloy.ModuleImportation;
import br.ufes.inf.nemo.alloy.PredicateInvocation;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.alloy.SignatureReference;
import br.ufes.inf.nemo.alloy.UnaryOperation;
import br.ufes.inf.nemo.alloy.Variable;
import br.ufes.inf.nemo.alloy.VariableReference;
import br.ufes.inf.nemo.alloy.api.AlloyAPI;
import br.ufes.inf.nemo.ontouml2alloy.transformer.Transformer;
import br.ufes.inf.nemo.sml2alloy.exception.UnsupportedElementException;
import br.ufes.inf.nemo.sml2alloy.parser.SMLParser;

public class SML2AlloyTransformer
{
	public SMLParser smlparser;
	public AlloyFactory factory;
	public AlloyModule module;
	
	public Transformer ontoumltransformer;
	public SMLRuleCreator rulecreator;
	
	public SignatureDeclaration world;
	public SignatureDeclaration sigSituation;
	public Variable exists;
	
	public SML2AlloyTransformer (SMLParser smlparser, AlloyFactory factory, Transformer ontoumltransformer)
	{
		this.smlparser = smlparser;
		this.factory = factory;
		this.ontoumltransformer = ontoumltransformer;
		{
			this.module = ontoumltransformer.module;
			this.exists = ontoumltransformer.exists;
			this.world = ontoumltransformer.world;
		}
		this.rulecreator = new SMLRuleCreator(smlparser);
	}
	
	public void run()
	{
		//Remove run command to be added again at the end
		module.getParagraph().remove(module.getParagraph().size()-1);
		
		ModuleImportation mi = AlloyAPI.createModuleImport(factory,"situations","", world);		
		module.getImports().add(mi);
		
		//Creates signature Situation
		sigSituation = factory.createSignatureDeclaration();
		sigSituation.setName("Situation");
		module.getParagraph().add(2, sigSituation);
		
		//Adds Situation to exists
		Expression newexp = adjustsSignaturesUnionExpression(
				((UnaryOperation)exists.getDeclaration().getExpression()).getExpression());
		((UnaryOperation)exists.getDeclaration().getExpression()).setExpression(newexp);
		
		//Adds Situation to additionalFacts
		PredicateInvocation pI = (PredicateInvocation) ontoumltransformer.additionalFact.getBlock().getExpression().get(1);
		Expression newexp2 = adjustsSignaturesUnionExpression(pI.getParameter().get(0));
		pI.getParameter().add(0, newexp2);
		
		populatesWithSituations();
		
		populatesWithAssociations();
		populatesWithAssociationEnds();
		
		populatesWithCompleteness();
		
		populatesWithTopLevelSituationDisjointness();
		
		CommandDeclaration cmd = AlloyAPI.createDefaultRunComand(factory, module);	
		module.getParagraph().add(cmd);
		
		populatesWithRules();
	}
	
	protected Expression adjustsSignaturesUnionExpression (Expression original)
	{
		SignatureReference sr = factory.createSignatureReference();
		sr.setSignature(sigSituation.getName());
		
		BinaryOperation bOp = factory.createBinaryOperation();
		bOp.setRightExpression(sr);
		bOp.setOperator(BinaryOperator.UNION);
		bOp.setLeftExpression(original);
		
		return bOp;
	}
	
	/** 
	 * Populates With Situations 
	 */
	protected void populatesWithSituations()
	{
		ArrayList<Declaration> classesDeclaration = new ArrayList<Declaration>();
		
		for(SituationType sittype: smlparser.getSituationTypes())		
		{
			try
			{
				Declaration decl = AlloyAPI.createDeclaration(factory, exists, smlparser.getAlias(sittype), sigSituation.getName());			
				if (decl!=null) classesDeclaration.add(decl);
			}
			catch (UnsupportedElementException e)
			{
				System.err.println(e.getMessage());
			}
		}
		// Sort classes declarations in the signature world
		Collections.sort(classesDeclaration, ontoumltransformer.new DeclarationComparator());
		
		world.getRelation().addAll(classesDeclaration);
	}
	
	/**
	 * Populates With Association Ends for Participations
	 */
	protected void populatesWithAssociationEnds()
	{
		for(SituationType sit : smlparser.getSituationTypes())
		{
			for(Participant part : smlparser.getInstances(sit, Participant.class))
			{
				try
				{
					String assocName = smlparser.getParticipationAlias(sit, part);
					String sitName = smlparser.getAlias(sit);
					String sitElemName = smlparser.getAlias(smlparser.getOntoUMLCounterpart(part));
					String fun1Name = sitName.toLowerCase();
					String fun2Name = smlparser.getAlias(part.getNodeParameter());
					
					ArrayList<FunctionDeclaration> funList = new ArrayList<FunctionDeclaration>();
					// for single snapshot
					funList.add(AlloyAPI.createFunctionDeclaration(factory, world, true, fun1Name, 
							"World."+sitElemName, "World."+sitName, assocName, false));
					funList.add(AlloyAPI.createFunctionDeclaration(factory, world, false, fun2Name, 
							"World."+sitName, "World."+sitElemName, assocName, false));	
					// for all worlds
					funList.add(AlloyAPI.createTemporalFunctionDeclaration(factory, world, true, fun1Name, 
							"World."+sitElemName, "World."+sitName, assocName, false));
					funList.add(AlloyAPI.createTemporalFunctionDeclaration(factory, world, false, fun2Name, 
							"World."+sitName, "World."+sitElemName, assocName, false));
					
					module.getParagraph().addAll(funList);
				}
				catch (UnsupportedElementException e)
				{
					System.err.println(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Populates With Participations
	 */
	protected void populatesWithAssociations()
	{
		ArrayList<Declaration> associationsDeclaration = new ArrayList<Declaration>();
		
		for(SituationType sit : smlparser.getSituationTypes())
		{
			for(Participant part : smlparser.getInstances(sit, Participant.class))
			{
				VariableReference source = factory.createVariableReference();
				VariableReference target = factory.createVariableReference();
				//TODO ou vai ser [0..1] ou [0..*], como saber?
				int lowerSource=0, upperSource=-1, lowerTarget=1, upperTarget=1;
				
				try
				{
					source.setVariable(smlparser.getAlias(sit));
					target.setVariable(smlparser.getAlias(smlparser.getOntoUMLCounterpart(part)));
					
					ArrowOperation aOp = AlloyAPI.createArrowOperation(factory, source.getVariable(), lowerSource, upperSource, target.getVariable(), lowerTarget, upperTarget);			
					Declaration decl = AlloyAPI.createDeclaration(factory, smlparser.getParticipationAlias(sit, part), aOp);
					if (decl!=null)
					{
						EStructuralFeature feat = part.eClass().getEStructuralFeature("isPast");
						if (feat != null && part.eGet(feat).equals(true))
							 sigSituation.getRelation().add(decl);
						
						else associationsDeclaration.add(decl);
					}
				}
				catch (UnsupportedElementException e)
				{
					System.err.println(e.getMessage());
				}
			}
		}
		
		// Sort associations declarations in the signature world
		Collections.sort(associationsDeclaration, ontoumltransformer.new DeclarationComparator());
		
		world.getRelation().addAll(associationsDeclaration);
	}
	
	/**
	 * 	Populates With Top Level Situations Disjointness
	 */
	protected void populatesWithTopLevelSituationDisjointness()
	{
		// SituationClass
		ArrayList<SituationType> topSituationClassList = new ArrayList<SituationType>();
		topSituationClassList.addAll(smlparser.getSituationTypes());		
		if(topSituationClassList.size() > 1)
		{
			ArrayList<DisjointExpression> rulesList = createTopLevelSituationDisjointExpressions(factory, topSituationClassList);			
			for (DisjointExpression disj : rulesList) 
			{ 
				world.getBlock().getExpression().add(disj); 
			}
		}
	}
	
	protected ArrayList<DisjointExpression> createTopLevelSituationDisjointExpressions (AlloyFactory factory, ArrayList<SituationType> toplevels)
    {
            ArrayList<DisjointExpression> result = new ArrayList<DisjointExpression>();
            
            for (SituationType s1: toplevels)
            {
            	ArrayList<String> paramList = new ArrayList<String>();
            	try
            	{
	                paramList.add(smlparser.getAlias(s1));
	                
	                ArrayList<String> exprList = new ArrayList<String>();
	                for(SituationType s2: toplevels) 
	                {
	                	if (!s1.equals(s2))
	                		exprList.add(smlparser.getAlias(s2));
	                }
	                
	                if (exprList.size()>1)
	                {
	                    // create a union(+) operation for the exprList
	                    BinaryOperation bo = AlloyAPI.createUnionExpression(factory, exprList);
	                    paramList.add(bo.toString());
	                } else if (exprList.size()==1)
	                {
	                    paramList.add(exprList.get(0));
	                }
            	}
            	catch (UnsupportedElementException e)
            	{
            		System.err.println(e.getMessage());
            	}
                
                //add Top Level Disjoint Expression Rule to to the List
                result.add( AlloyAPI.createDisjointExpression(factory,paramList) ); 
            }
            
            return result;
    }
	
	/**
	 * Populates With Completeness
	 */
	protected void populatesWithCompleteness()
	{	
		// exists:>Situation in situationNamesList[0] +...+ situationNamesList[N] 
		 
		ArrayList<SituationType> situationsList = new ArrayList<SituationType>();
		ArrayList<String> situationNamesList = new ArrayList<String>();
		situationsList.addAll(smlparser.getSituationTypes());
		
		for(SituationType sit : situationsList)
		{
			try {
				situationNamesList.add(smlparser.getAlias(sit));
			} catch (UnsupportedElementException e) {
				System.err.println(e.getMessage());
			}
		}
		
		if(situationNamesList.size() > 0)
		{
			AlloyAPI.createExistsCompareOperationInWorld(factory, exists, world, sigSituation, situationNamesList);
			
			// disj[situationNamesList[0],...,situationNamesList[N]]
			if (situationNamesList.size()>1)
			{
				DisjointExpression disj = AlloyAPI.createDisjointExpression(factory, situationNamesList);	
				if (disj!=null) world.getBlock().getExpression().add(disj);
			}
		}
	}
	
	protected void populatesWithRules()
	{
		rulecreator.createCommonRules();
		
		for(SituationType sit : smlparser.getSituationTypes())
		{
			rulecreator.createSituationRule(sit);
		}
	}
}