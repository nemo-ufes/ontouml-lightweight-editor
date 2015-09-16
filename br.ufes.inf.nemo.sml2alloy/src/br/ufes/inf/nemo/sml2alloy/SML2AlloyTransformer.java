package br.ufes.inf.nemo.sml2alloy;

import java.util.ArrayList;
import java.util.Collections;

import sml2.Participant;
import sml2.SelfReference;
import sml2.SituationParticipant;
import sml2.SituationType;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.AlloyModule;
import br.ufes.inf.nemo.alloy.ArrowOperation;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.BinaryOperator;
import br.ufes.inf.nemo.alloy.Block;
import br.ufes.inf.nemo.alloy.CommandDeclaration;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.Declaration;
import br.ufes.inf.nemo.alloy.DisjointExpression;
import br.ufes.inf.nemo.alloy.Expression;
import br.ufes.inf.nemo.alloy.FactDeclaration;
import br.ufes.inf.nemo.alloy.FunctionDeclaration;
import br.ufes.inf.nemo.alloy.ModuleImportation;
import br.ufes.inf.nemo.alloy.PredicateInvocation;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.alloy.SignatureReference;
import br.ufes.inf.nemo.alloy.UnaryOperation;
import br.ufes.inf.nemo.alloy.Variable;
import br.ufes.inf.nemo.alloy.VariableReference;
import br.ufes.inf.nemo.alloy.api.AlloyAPI;
import br.ufes.inf.nemo.ontouml2alloy.transformer.Transformer;
import br.ufes.inf.nemo.sml2alloy.exception.UnsupportedElementException;
import br.ufes.inf.nemo.sml2alloy.parser.CardinalityRule;
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
		
		populatesWithParticipations();
		populatesWithParticipationProperties();
		populatesWithParticipationEnds();
		
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
	 * Creates the situation declarations inside the World signature:
	 * 
	 * <sitName>: set exists:>Situation 
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
	 * Creates function declarations that represents the association ends for participations, with the
	 * purpose of navigating the model. Depending if the participations are transtemporal or not, the
	 * declarations are different. They have the following structure, respectively:
	 * 
	 * fun <fun1Name> (x: World.<sitElemName>) World.<sitName> {
	 * 		(<assocName>).x
	 * }
	 * fun <fun2Name> (x: World.<sitName>) World.<sitElemName> {
	 * 		x.(<assocName>)
	 * }
	 * 
	 * or
	 * 
	 * fun <fun1Name> (x: World.<sitElemName>, w: World) World.<sitName> {
	 * 		(w.<assocName>).x
	 * }
	 * fun <fun2Name> (x: World.<sitName>, w: World) World.<sitElemName> {
	 * 		x.(w.<assocName>)
	 * }
	 */
	protected void populatesWithParticipationEnds()
	{
		ArrayList<FunctionDeclaration> funList = new ArrayList<FunctionDeclaration>();
		
		for(SituationType sit : smlparser.getSituationTypes())
		{
			for(Participant part : smlparser.getInstances(sit, Participant.class))
			{
				if (part.getIsImageOf() != null || part instanceof SelfReference) continue;
				
				try
				{
					String assocName = smlparser.getParticipationAlias(sit, part);
					String sitName = smlparser.getAlias(sit);
					String sitElemName = smlparser.getAlias(smlparser.getElementType(part));
					String fun1Name = sitName.toLowerCase();
					String fun2Name = smlparser.getAlias(part);
					
					if (smlparser.isTemporal(part))
					{
						funList.add(AlloyAPI.createTransTemporalFunctionDeclaration(factory, world, true, fun1Name,
								"World."+sitElemName, "World."+sitName, assocName));
						funList.add(AlloyAPI.createTransTemporalFunctionDeclaration(factory, world, false, fun2Name,
								"World."+sitName, "World."+sitElemName, assocName));
					}
					else
					{
						// for single snapshot
						funList.add(AlloyAPI.createFunctionDeclaration(factory, world, true, fun1Name, 
								"World."+sitElemName, "World."+sitName, assocName, false));
						funList.add(AlloyAPI.createFunctionDeclaration(factory, world, false, fun2Name, 
								"World."+sitName, "World."+sitElemName, assocName, false));	
//						// for all worlds
//						funList.add(AlloyAPI.createTemporalFunctionDeclaration(factory, world, true, fun1Name, 
//								"World."+sitElemName, "World."+sitName, assocName, false));
//						funList.add(AlloyAPI.createTemporalFunctionDeclaration(factory, world, false, fun2Name, 
//								"World."+sitName, "World."+sitElemName, assocName, false));
					}
				}
				catch (UnsupportedElementException e)
				{
					System.err.println(e.getMessage());
				}
			}
		}
		module.getParagraph().addAll(funList);
	}
	
	/**
	 * Creates the participations declarations, depending on the temporality of the participant,
	 * inside the World signature or the Situation signature, respectively, as follows:
	 * 
	 * <participationAlias>: set <sitName> lone/set -> one/some <partName>
	 * 
	 * or 
	 * 
	 * <participationAlias>: set <sitSignatureName>
	 */
	protected void populatesWithParticipations()
	{
		ArrayList<Declaration> associationsDeclaration = new ArrayList<Declaration>();
		ArrayList<Declaration> transtempAssocDeclaration = new ArrayList<Declaration>();
		
		for(SituationType sit : smlparser.getSituationTypes())
		{
			for(Participant part : smlparser.getInstances(sit, Participant.class))
			{
				if (part.getIsImageOf() != null || part instanceof SelfReference) continue;
				
				createParticipationDeclaration(new Object[] {sit, part, part.getMin(), part.getMax()},
						associationsDeclaration, transtempAssocDeclaration);
			}
		}
		
		// Sort associations declarations in the signature world and signature situation
		Collections.sort(associationsDeclaration, ontoumltransformer.new DeclarationComparator());
		Collections.sort(transtempAssocDeclaration, ontoumltransformer.new DeclarationComparator());
		
		sigSituation.getRelation().addAll(transtempAssocDeclaration);
		world.getRelation().addAll(associationsDeclaration);
	}
	
	protected void createParticipationDeclaration(Object[] info, 
			ArrayList<Declaration> associationsDeclaration, ArrayList<Declaration> transtempAssocDeclaration)
	{
		SituationType sit = (SituationType) info[0];
		Participant part = (Participant) info[1];
		int lowerSource = 0;
		int upperSource = -1;
		int lowerTarget = (int) info[2];
		int upperTarget = (int) info[3];
		
		VariableReference source = factory.createVariableReference();
		VariableReference target = factory.createVariableReference();
		try
		{
			//Past participants are defined as transtemporal participations
			if (smlparser.isTemporal(part))
			{
				target.setVariable(part instanceof SituationParticipant ? sigSituation.getName() : ontoumltransformer.sigObject.getName());
				Declaration decl = AlloyAPI.createSimpleDeclaration(factory, smlparser.getParticipationAlias(sit, part), target.getVariable());
				if (decl != null)
				{
					transtempAssocDeclaration.add(decl);
					addTransTemporalRestrictions(info);
				}
			}
			else
			{
				source.setVariable(smlparser.getAlias(sit));
				target.setVariable(smlparser.getAlias(smlparser.getElementType(part)));
				ArrowOperation aOp = AlloyAPI.createArrowOperation(factory, source.getVariable(), lowerSource, upperSource, target.getVariable(), lowerTarget, upperTarget);			
				Declaration decl = AlloyAPI.createDeclaration(factory, smlparser.getParticipationAlias(sit, part), aOp);
				if (decl!=null)
				{
					associationsDeclaration.add(decl);
					addParticipationCardinalities(info);
				}
			}
		}
		catch (UnsupportedElementException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Creates a fact declaration that ensures the immutability of the participation targets, if so, 
	 * for every participation. The fact is as follows:
	 * 
	 * fact participationProperties {
	 * 		immutable_target[<sitName>, <participationName>]
	 * 		(...)
	 * }
	 */
	protected void populatesWithParticipationProperties()
	{
		FactDeclaration participation_properties = factory.createFactDeclaration();
		participation_properties.setName("participationProperties");
		participation_properties.setBlock(factory.createBlock());
		
		for(SituationType sit : smlparser.getSituationTypes())
		{
			for(Participant part : smlparser.getInstances(sit, Participant.class))
			{
				if (part.getIsImageOf() != null || part instanceof SelfReference) continue;
				
				if (!smlparser.isTemporal(part) && part.isImmutable())
				{
					try
					{
						PredicateInvocation pI = AlloyAPI.createImmutablePredicateInvocation(factory, "immutable_target", smlparser.getAlias(sit), smlparser.getParticipationAlias(sit, part));
						participation_properties.getBlock().getExpression().add(pI);
					}
					catch (UnsupportedElementException e)
					{
						System.out.println(e.getMessage());
					}	
				}
			}
		}
		
		if (!participation_properties.getBlock().getExpression().isEmpty())
			module.getParagraph().add(participation_properties);
	}
	
	
	/**
	 * Creates a fact to specify the type and cardinality of the transtemporal participations, since it cannot
	 * be defined properly in the declaration itself. These restrictions are as follows:
	 * 
	 * fact historicalParticipation {
	 * 		univ.<participationAlias> in World.<sitName>
	 * 		#univ.<participationAlias> CompareOperator 0/<upperSource>
	 * 
	 * 		<participationAlias>.univ in World.<partName>
	 * 		#<participationAlias>.univ CompareOperation <lowerTarget>/<upperTarget>
	 * }
	 * @param info
	 * @throws UnsupportedElementException
	 */
	protected void addTransTemporalRestrictions(Object[] info) throws UnsupportedElementException
	{
		FactDeclaration situationFact = factory.createFactDeclaration();
		situationFact.setName("historicalParticipation");
		Block block = factory.createBlock();
		situationFact.setBlock(block);
		
		SituationType sit = (SituationType) info[0];
		Participant part = (Participant) info[1];
		
		//source
		situationFact.getBlock().getExpression().add(AlloyAPI.createCompareOperation(factory, 
				smlparser.getParticipationAlias(sit, part)+".univ", 
				CompareOperator.SUBSET, "World."+smlparser.getAlias(sit)));
		//target
		situationFact.getBlock().getExpression().add(AlloyAPI.createCompareOperation(factory, 
				"univ."+smlparser.getParticipationAlias(sit, part), 
				CompareOperator.SUBSET, "World."+smlparser.getAlias(smlparser.getElementType(part))));
		
		ArrayList<QuantificationExpression> qeList = new ArrayList<QuantificationExpression>();
		try
		{
			qeList.addAll(CardinalityRule.createTransTemporalQuantificationExpressions(factory, smlparser, sit, part));

			for (QuantificationExpression qe: qeList) situationFact.getBlock().getExpression().add(qe);
		}
		catch (UnsupportedElementException e)
		{
			System.out.println(e.getMessage());
		}
		
		module.getParagraph().add(situationFact);
	}
	
	/**
	 * Populates With Participation Cardinalities. 
	 */
	protected void addParticipationCardinalities(Object[] info)
	{
		SituationType sit = (SituationType) info[0];
		Participant part = (Participant) info[1];
		
		ArrayList<QuantificationExpression> qeList;
		try
		{
			qeList = CardinalityRule.createQuantificationExpressions(factory, smlparser, sit, part);
			for (QuantificationExpression qe: qeList) world.getBlock().getExpression().add(qe);
		}
		catch (UnsupportedElementException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 	Populates With Top Level Situations Disjointness
	 */
	protected void populatesWithTopLevelSituationDisjointness()
	{
		// SituationClass
		ArrayList<SituationType> topSituationClassList = new ArrayList<SituationType>();
		topSituationClassList.addAll(smlparser.getSituationTypes());		
		if(topSituationClassList.size() > 2)
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
		rulecreator.createCommonRules(sigSituation, exists);
		
		for(SituationType sit : smlparser.getSituationTypes())
		{
			rulecreator.createSituationRule(sit);
		}
	}
}