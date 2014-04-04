package br.ufes.inf.nemo.antipattern.wizard.binover;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.wizard.IWizardPage;

import RefOntoUML.Association;
import RefOntoUML.impl.CharacterizationImpl;
import RefOntoUML.impl.FormalAssociationImpl;
import RefOntoUML.impl.MaterialAssociationImpl;
import RefOntoUML.impl.MediationImpl;
import RefOntoUML.impl.componentOfImpl;
import RefOntoUML.impl.memberOfImpl;
import RefOntoUML.impl.subCollectionOfImpl;
import RefOntoUML.impl.subQuantityOfImpl;
import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence.BinaryProperty;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence.BinaryPropertyValue;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 */

public class BinOverWizard extends AntipatternWizard {

	protected DisjointnessPage disjointPage;
	protected ReflexivityPage reflexivityPage;
	protected SymmetryPage symmetryPage;
	protected TransitivityPage transitivityPage;
	protected CyclicityPage cyclicityPage;
	protected ChangeStereotypePage 	symmetryChangePage, reflexivityChangePage,  
									transitivityChangePage, cyclicityChangePage;

	
	
	protected BinaryPropertyValue transitivity, reflexivity, symmetry, cyclicity;
	
	public BinOverWizard(BinOverOccurrence ap) {
		super(ap, BinOverAntipattern.getAntipatternInfo().name);	 	 
	}
    
	@Override
	public void addPages() 
	{	
		super.addPages();
		
		finishing = new FinishingPage();
		options = new BinOverRefactoringPage((BinOverOccurrence)ap);
				
		disjointPage = new DisjointnessPage((BinOverOccurrence)ap);
		reflexivityPage = new ReflexivityPage((BinOverOccurrence)ap);
		symmetryPage = new SymmetryPage((BinOverOccurrence)ap);
		transitivityPage = new TransitivityPage((BinOverOccurrence)ap);
		cyclicityPage = new CyclicityPage((BinOverOccurrence)ap);
		
		reflexivityChangePage = new ChangeStereotypePage((BinOverOccurrence)ap, BinaryProperty.Reflexivity);
		symmetryChangePage = new ChangeStereotypePage((BinOverOccurrence)ap, BinaryProperty.Symmetry);
		transitivityChangePage = new ChangeStereotypePage((BinOverOccurrence)ap, BinaryProperty.Transitivity);
		cyclicityChangePage = new ChangeStereotypePage((BinOverOccurrence)ap, BinaryProperty.Cyclicity);
		
		presentation = new PresentationPage(
			BinOverAntipattern.getAntipatternInfo().name,
			BinOverAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			disjointPage,
			options
		);
		
		addPage(presentation);		
		addPage(disjointPage);
		addPage(reflexivityPage);
		addPage(symmetryPage);
		addPage(transitivityPage);
		addPage(cyclicityPage);
		
		addPage(reflexivityChangePage);
		addPage(symmetryChangePage);
		addPage(transitivityChangePage);
		addPage(cyclicityChangePage);
		
		addPage(options);
		addPage(finishing);
	}

	public BinOverOccurrence getAp() {
		return (BinOverOccurrence)ap;
	}
		
	public static String getStereotypeName(Class<? extends Association> stereo){
		String name = stereo.toString().replaceAll("class RefOntoUML.impl.","");
	    name = name.replaceAll("Impl","");
	    name = Normalizer.normalize(name, Normalizer.Form.NFD);	
	    if (!name.equalsIgnoreCase("association")) name = name.replace("Association","");
	    return name;
	}
	
	public BinaryPropertyValue getDefaultValue(Class<? extends Association> stereotype, BinaryProperty type){
		if (type==BinaryProperty.Reflexivity){
			if(stereotype==FormalAssociationImpl.class || stereotype==MaterialAssociationImpl.class)
				return BinaryPropertyValue.NONE;
			else
				return BinaryPropertyValue.ANTI_REFLEXIVE;
		}
		else if (type==BinaryProperty.Symmetry){
			if(stereotype==FormalAssociationImpl.class || stereotype==MaterialAssociationImpl.class || 
					stereotype==MediationImpl.class)
				return BinaryPropertyValue.NONE;
			else
				return BinaryPropertyValue.ASYMMETRIC;
		}
		else if(type==BinaryProperty.Transitivity){
			if(stereotype==FormalAssociationImpl.class || stereotype==MaterialAssociationImpl.class || 
					stereotype==MediationImpl.class || stereotype==CharacterizationImpl.class)
				return BinaryPropertyValue.NONE;
			else if (stereotype==memberOfImpl.class) 
				return BinaryPropertyValue.ANTI_TRANSITIVE;
			else
				return BinaryPropertyValue.TRANSITIVE;
		}
		else if (type==BinaryProperty.Cyclicity){
			if(stereotype==FormalAssociationImpl.class || stereotype==MaterialAssociationImpl.class || 
					stereotype==MediationImpl.class)
				return BinaryPropertyValue.NONE;
			else
				return BinaryPropertyValue.ACYCLIC;
		}
		else
			return null;
	}
	
	public ArrayList<Class<? extends Association>> possibleStereotypes(BinaryPropertyValue property){
		
		ArrayList<Class<? extends Association>> possibleStereotypes = new ArrayList<Class<? extends Association>>();
		possibleStereotypes.add(FormalAssociationImpl.class);
		possibleStereotypes.add(MaterialAssociationImpl.class);
		
		if (property==BinaryPropertyValue.ANTI_REFLEXIVE || property==BinaryPropertyValue.ASYMMETRIC || property==BinaryPropertyValue.ACYCLIC){
			possibleStereotypes.add(componentOfImpl.class);
			possibleStereotypes.add(memberOfImpl.class);
			possibleStereotypes.add(subCollectionOfImpl.class);
			possibleStereotypes.add(subQuantityOfImpl.class);
			possibleStereotypes.add(MediationImpl.class);
			possibleStereotypes.add(CharacterizationImpl.class);
		}
		
		else if(property==BinaryPropertyValue.ANTI_TRANSITIVE)
			possibleStereotypes.add(memberOfImpl.class);
		
		else if (property==BinaryPropertyValue.TRANSITIVE){
				possibleStereotypes.add(componentOfImpl.class);
				possibleStereotypes.add(subCollectionOfImpl.class);
				possibleStereotypes.add(subQuantityOfImpl.class);
			}
		return possibleStereotypes;
	}
	
	public ArrayList<Class<? extends Association>> allStereotypes(){
		ArrayList<Class<? extends Association>> stereotypes = new ArrayList<Class<? extends Association>>();
		stereotypes.add(FormalAssociationImpl.class);
		stereotypes.add(MaterialAssociationImpl.class);
		stereotypes.add(componentOfImpl.class);
		stereotypes.add(memberOfImpl.class);
		stereotypes.add(subCollectionOfImpl.class);
		stereotypes.add(subQuantityOfImpl.class);
		stereotypes.add(MediationImpl.class);
		stereotypes.add(CharacterizationImpl.class);
		return stereotypes;
	}
	
	public ArrayList<String> getStereotypeNames(ArrayList<Class<? extends Association>> stereotypes){
		ArrayList<String> result = new ArrayList<String>();
		for (Class<? extends Association> stereo : stereotypes) {
			result.add(getStereotypeName(stereo));
		}
		return result;
	}
	
	public DisjointnessPage getDisjointPage() {
		disjointPage.updateDescription();
		return disjointPage;
	}

	public ReflexivityPage getReflexivityPage() {
		reflexivityPage.updateDescription();
		return reflexivityPage;
	}

	public SymmetryPage getSymmetryPage() {
		symmetryPage.updateUI();
		symmetryPage.updateDescription();
		return symmetryPage;
	}

	public TransitivityPage getTransitivityPage() {
		transitivityPage.updateDescription();
		transitivityPage.updateUI();
		return transitivityPage;
	}

	public CyclicityPage getCyclicityPage() {
		cyclicityPage.updateDescription();
		cyclicityPage.updateUI();
		return cyclicityPage;
	}

	public ChangeStereotypePage getSymmetryChangePage(BinaryPropertyValue chosenPropertyValue) {
		System.out.println("Value: "+chosenPropertyValue);
		symmetryChangePage.updateDescription();
		symmetryChangePage.setUI(chosenPropertyValue);
		return symmetryChangePage;
	}

	public ChangeStereotypePage getReflexivityChangePage(BinaryPropertyValue chosenPropertyValue) {
		System.out.println("Value: "+chosenPropertyValue);
		reflexivityChangePage.updateDescription();
		reflexivityChangePage.setUI(chosenPropertyValue);
		return reflexivityChangePage;
	}

	public ChangeStereotypePage getTransitivityChangePage(BinaryPropertyValue chosenPropertyValue) {
		System.out.println("Value: "+chosenPropertyValue);
		transitivityChangePage.updateDescription();
		transitivityChangePage.setUI(chosenPropertyValue);
		return transitivityChangePage;
	}
	
	public ChangeStereotypePage getCyclicityChangePage(BinaryPropertyValue chosenPropertyValue) {
		System.out.println("Value: "+chosenPropertyValue);
		cyclicityChangePage.updateDescription();
		cyclicityChangePage.setUI(chosenPropertyValue);
		return cyclicityChangePage;
	}

	public Class<? extends Association> getCurrentStereotype(IWizardPage currentPage) {
		BinOverOccurrence binOver = (BinOverOccurrence) ap;
		Class<? extends Association> newStereo;
		
		if(currentPage.equals(reflexivityPage) || currentPage.equals(reflexivityChangePage))
			return binOver.getAssociation().getClass();
		
		else if(currentPage.equals(symmetryPage) || currentPage.equals(symmetryChangePage)){
			try { newStereo = ((BinOverAction) getAction(1).get(0)).getNewStereotype(); }
			catch (Exception e){ newStereo = null;	}
			
			if(newStereo!=null)
				return newStereo;
		}
		
		else if(currentPage.equals(transitivityPage) || currentPage.equals(transitivityChangePage)){
			try { newStereo = ((BinOverAction) getAction(2).get(0)).getNewStereotype(); }
			catch (Exception e){ newStereo = null;	}
			
			if(newStereo!=null)
				return newStereo;
			else{
				try { newStereo = ((BinOverAction) getAction(1).get(0)).getNewStereotype(); }
				catch (Exception e){ newStereo = null;	}
				
				if(newStereo!=null)
					return newStereo;
			}
		}
		
		else if(currentPage.equals(cyclicityPage) || currentPage.equals(cyclicityChangePage)){
			try { newStereo = ((BinOverAction) getAction(3).get(0)).getNewStereotype(); }
			catch (Exception e){ newStereo = null;	}
			
			if(newStereo!=null)
				return newStereo;
			else{
				try { newStereo = ((BinOverAction) getAction(2).get(0)).getNewStereotype(); }
				catch (Exception e){ newStereo = null;	}
				
				if(newStereo!=null)
					return newStereo;
				
				else{
					try { newStereo = ((BinOverAction) getAction(1).get(0)).getNewStereotype(); }
					catch (Exception e){ newStereo = null;	}
					
					if(newStereo!=null)
						return newStereo;
				}
			}
		}
		
		return binOver.getAssociation().getClass();
		
	}
	
	public String getCurrentStereotypeName(IWizardPage page){
		return getStereotypeName(getCurrentStereotype(page));
	}
	

	public BinaryPropertyValue getTransitivity() {
		return transitivity;
	}

	public BinaryPropertyValue getReflexivity() {
		return reflexivity;
	}

	public BinaryPropertyValue getSymmetry() {
		return symmetry;
	}

	public BinaryPropertyValue getCyclicity() {
		return cyclicity;
	}
	
	@Override
	public Collection<AntiPatternAction<?>> getAllActions() {
		
		ArrayList<AntiPatternAction<?>> allActions = new ArrayList<AntiPatternAction<?>>();
		ArrayList<AntiPatternAction<?>> runnableActions = new ArrayList<AntiPatternAction<?>>();
		BinOverAction changeStereotypeAction = null;
		allActions.addAll(actions.get(0));
		
		if(actions.get(4)!=null && !actions.get(4).isEmpty()){
			allActions.addAll(actions.get(4));
			changeStereotypeAction = (BinOverAction) actions.get(4).get(0);
		}
		else if(actions.get(3)!=null && !actions.get(3).isEmpty()) {
			allActions.addAll(actions.get(3));
			changeStereotypeAction = (BinOverAction) actions.get(3).get(0);
		}

		else if(actions.get(2)!=null && !actions.get(2).isEmpty()) {
			allActions.addAll(actions.get(2));
			changeStereotypeAction = (BinOverAction) actions.get(2).get(0);
		}

		else if(actions.get(1)!=null && !actions.get(1).isEmpty()) {
			allActions.addAll(actions.get(1));
			changeStereotypeAction = (BinOverAction) actions.get(1).get(0);
		}
	
		Class<? extends Association> stereotype;
		
		if(changeStereotypeAction != null && changeStereotypeAction.getNewStereotype()!=null){
			stereotype = changeStereotypeAction.getNewStereotype();
			runnableActions.add(changeStereotypeAction);
		}
		else
			stereotype = ((BinOverOccurrence)ap).getAssociation().getClass();
		
		for (AntiPatternAction<?> antiPatternAction : allActions) {
			if(antiPatternAction instanceof BinOverAction){
				
				BinOverAction boAction = (BinOverAction) antiPatternAction;
				
				boolean isActionBPChange = 	boAction.getCode()==BinOverAction.Action.SET_BINARY_PROPERTY;
				
				boolean isMaterial = stereotype.equals(MaterialAssociationImpl.class);
				boolean isFormal = stereotype.equals(FormalAssociationImpl.class);
				boolean isNon = (boAction.getProperty()==BinaryPropertyValue.NON_REFLEXIVE || boAction.getProperty()==BinaryPropertyValue.NON_SYMMETRIC ||
								boAction.getProperty()==BinaryPropertyValue.NON_TRANSITIVE || boAction.getProperty()==BinaryPropertyValue.NON_CYCLIC);	
				
				System.out.println("Getting Action: "+boAction.getCode());
				System.out.println("\tisBP: "+isActionBPChange);
				System.out.println("\tisNon: "+isNon);
				System.out.println("\tisMaterial: "+isMaterial);
				System.out.println("\tisFormal: "+isFormal);
				System.out.println();
				
				if(isActionBPChange && !isNon && (isMaterial || isFormal))
					runnableActions.add(boAction);

			}
		}

		return runnableActions;
	}
	
		
}
