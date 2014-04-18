package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import java.util.HashMap;

import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.MixinClass;
import RefOntoUML.Mode;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalAntipattern;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class UndefFormalWizard extends AntipatternWizard {

		enum Nature {FUNCTIONAL, COLLECTIVE, QUANTITY, MODE, RELATOR, DATATYPE}
		
		protected HashMap<Nature,Boolean> sourceNatureHash;
		protected HashMap<Nature,Boolean> targetNatureHash;
		
		protected ChangeRelationStereotypePage changeStereotypePage;
		protected changeToMaterialPage changeToMaterialPage;
		protected IsComparativeFormalPage isComparativeFormalPage;
		protected CreateDataTypePage createDatatypePage;
		protected CreateMediatedPage createMediatedPage;
		protected CantDefineNaturePage cantDefineNaturePage;
		protected NoRelationTypePossiblePage noRelationTypePossiblePage;
		protected final boolean hasNatureIssue;
		
		public UndefFormalWizard(UndefFormalOccurrence ap) {
			super(ap, UndefFormalAntipattern.getAntipatternInfo().name);
			sourceNatureHash = setEndsNature(ap.getSource());
			targetNatureHash = setEndsNature(ap.getTarget());
			hasNatureIssue = !canDefineBothEndsNature();
		}
	    
		@Override
		public void addPages() 
		{	
			isComparativeFormalPage = new IsComparativeFormalPage(getAp());
			createDatatypePage = new CreateDataTypePage(getAp());
			changeToMaterialPage = new changeToMaterialPage(getAp());

			if(!canDefineBothEndsNature())
				cantDefineNaturePage = new CantDefineNaturePage(getAp());
			
			changeStereotypePage = new  ChangeRelationStereotypePage(getAp());
			createMediatedPage = new CreateMediatedPage(getAp());
			noRelationTypePossiblePage = new NoRelationTypePossiblePage(getAp());
			finishing = new FinishingPage();
			options = new UndefFormalRefactoringPage(getAp());
					
			presentation = new PresentationPage(
				UndefFormalAntipattern.getAntipatternInfo().name,
				UndefFormalAntipattern.getAntipatternInfo().acronym,
				ap.toString(),
				UndefFormalAntipattern.getAntipatternInfo().description,
				isComparativeFormalPage,
				options
			);
			
			addPage(presentation);	
			addPage(isComparativeFormalPage);
			addPage(createDatatypePage);
			addPage(changeToMaterialPage);
			
			if(cantDefineNaturePage!=null) addPage(cantDefineNaturePage);
			
			addPage(changeStereotypePage);
			addPage(createMediatedPage);
			addPage(noRelationTypePossiblePage);
			addPage(options);
			addPage(finishing);
		}

		public UndefFormalOccurrence getAp() {
			return ((UndefFormalOccurrence)ap);
		}

		public ChangeRelationStereotypePage getChangeFormalStereotypePage() {
			return changeStereotypePage;
		}
		
		public changeToMaterialPage getChangeToMaterialPage()
		{
			return changeToMaterialPage;
		}
			
		public IsComparativeFormalPage getIsComparativeFormalPage()
		{
			return isComparativeFormalPage;
		}
		
		public CreateDataTypePage getCreateDataTypePage()
		{
			return createDatatypePage;
		}
		
		public CreateMediatedPage getCreateMediatedPage()
		{
			return createMediatedPage;
		}
		
		private HashMap<Nature,Boolean> setEndsNature(Classifier c){
			HashMap<Nature,Boolean> natureHash = new HashMap<Nature,Boolean>();
			
			if(c instanceof Relator){
				natureHash.put(Nature.RELATOR, true);
				natureHash.put(Nature.MODE, false);
				natureHash.put(Nature.DATATYPE, false);
				natureHash.put(Nature.FUNCTIONAL, false);
				natureHash.put(Nature.COLLECTIVE, false);
				natureHash.put(Nature.QUANTITY, false);
				
				return natureHash;
			}
			else
				natureHash.put(Nature.RELATOR, false);
			
			if(c instanceof Mode){	
				natureHash.put(Nature.MODE, true );
				natureHash.put(Nature.DATATYPE, false);
				natureHash.put(Nature.FUNCTIONAL, false);
				natureHash.put(Nature.COLLECTIVE, false);
				natureHash.put(Nature.QUANTITY, false);
				
				return natureHash;
			}
			else
				natureHash.put(Nature.MODE, false );
			
			if(c instanceof DataType){
				natureHash.put(Nature.DATATYPE, true);
				natureHash.put(Nature.FUNCTIONAL, false);
				natureHash.put(Nature.COLLECTIVE, false);
				natureHash.put(Nature.QUANTITY, false);
				
				return natureHash;
			}
			else
				natureHash.put(Nature.DATATYPE, false);
			
			if(getAp().getParser().isFunctionalComplex(c)){
				natureHash.put(Nature.FUNCTIONAL, true);
				natureHash.put(Nature.COLLECTIVE, false);
				natureHash.put(Nature.QUANTITY, false);
				
				return natureHash;
			}
			
			if(getAp().getParser().isCollective(c)){
				natureHash.put(Nature.FUNCTIONAL, false);
				natureHash.put(Nature.COLLECTIVE, true);
				natureHash.put(Nature.QUANTITY, false);
				
				return natureHash;
			}
			
			else if(getAp().getParser().isQuantity(c)){
				natureHash.put(Nature.FUNCTIONAL, false);
				natureHash.put(Nature.COLLECTIVE, false);
				natureHash.put(Nature.QUANTITY, true);
				
				return natureHash;
			}
			
			if(c instanceof MixinClass){
				if(getAp().getParser().hasFunctionalComplexChild((MixinClass) c))
					natureHash.put(Nature.FUNCTIONAL, true);
				else
					natureHash.put(Nature.FUNCTIONAL, false);
				
				if(getAp().getParser().hasQuantityChild((MixinClass) c))
					natureHash.put(Nature.QUANTITY, true);
				else
					natureHash.put(Nature.QUANTITY, false);
				
				if(getAp().getParser().hasCollectiveChild((MixinClass) c))
					natureHash.put(Nature.COLLECTIVE, true);
				else
					natureHash.put(Nature.COLLECTIVE, false);
			}
			else{
				natureHash.put(Nature.FUNCTIONAL, false);
				natureHash.put(Nature.COLLECTIVE, false);
				natureHash.put(Nature.QUANTITY, false);
			}
			
			return natureHash;	
		}
		
		public boolean canDefineNature(HashMap<Nature,Boolean> natureHash){
			
			for (Boolean value : natureHash.values()) {
				if(value)
					return true;
			}
			
			return false;
		}
		
		public boolean canDefineSourceNature() {
			return canDefineNature(sourceNatureHash);
		}
		
		public boolean canDefineTargetNature() {
			return canDefineNature(targetNatureHash);
		}
		
		public boolean canDefineBothEndsNature(){
			return canDefineSourceNature() && canDefineTargetNature();
		}
		
		public boolean hasRelationTypeBetweenSourceAndTarget(){
			
			if(sourceNatureHash==null)
				sourceNatureHash = setEndsNature(getAp().getSource());
			
			if(targetNatureHash==null)
				targetNatureHash = setEndsNature(getAp().getTarget());
		
			if(isBetweenRelatorAndObject() || isBetweenModeAndClass() || isBetweenQuantities() || 
					isBetweenCollectiveAndFunctional() || isBetweenCollectives() || isBetweenFunctionals())
				return true;
			
			return false;
		}
		
		
		
		private boolean isObject(HashMap<Nature,Boolean> natureHash){
			return (natureHash.get(Nature.FUNCTIONAL) || natureHash.get(Nature.COLLECTIVE) || natureHash.get(Nature.QUANTITY)) &&
					!natureHash.get(Nature.RELATOR) && !natureHash.get(Nature.MODE) && !natureHash.get(Nature.DATATYPE);
		}
		
		private boolean isFunctional(HashMap<Nature,Boolean> natureHash){
			return natureHash.get(Nature.FUNCTIONAL) && !natureHash.get(Nature.COLLECTIVE) && !natureHash.get(Nature.QUANTITY) &&
					!natureHash.get(Nature.RELATOR) && !natureHash.get(Nature.MODE) && !natureHash.get(Nature.DATATYPE);
		}
		
		private boolean isCollective(HashMap<Nature,Boolean> natureHash){
			return natureHash.get(Nature.COLLECTIVE) && !natureHash.get(Nature.FUNCTIONAL) && !natureHash.get(Nature.QUANTITY) &&
					!natureHash.get(Nature.RELATOR) && !natureHash.get(Nature.MODE) && !natureHash.get(Nature.DATATYPE);
		}
		
		private boolean isQuantity(HashMap<Nature,Boolean> natureHash){
			return natureHash.get(Nature.QUANTITY) && !natureHash.get(Nature.COLLECTIVE) && !natureHash.get(Nature.FUNCTIONAL) &&
					!natureHash.get(Nature.RELATOR) && !natureHash.get(Nature.MODE) && !natureHash.get(Nature.DATATYPE);
		}
		
		private boolean isRelator(HashMap<Nature,Boolean> natureHash){
			return natureHash.get(Nature.RELATOR) && !natureHash.get(Nature.COLLECTIVE) && !natureHash.get(Nature.FUNCTIONAL) &&
					!natureHash.get(Nature.QUANTITY) && !natureHash.get(Nature.MODE) && !natureHash.get(Nature.DATATYPE);
		}
		
		private boolean isMode(HashMap<Nature,Boolean> natureHash){
			return natureHash.get(Nature.MODE) && !natureHash.get(Nature.COLLECTIVE) && !natureHash.get(Nature.FUNCTIONAL) &&
					!natureHash.get(Nature.RELATOR) && !natureHash.get(Nature.QUANTITY) && !natureHash.get(Nature.DATATYPE);
		}
		
		@SuppressWarnings("unused")
		private boolean isDatatype(HashMap<Nature,Boolean> natureHash){
			return natureHash.get(Nature.DATATYPE) && !natureHash.get(Nature.COLLECTIVE) && !natureHash.get(Nature.FUNCTIONAL) &&
					!natureHash.get(Nature.RELATOR) && !natureHash.get(Nature.MODE) && !natureHash.get(Nature.QUANTITY);
		}
		
		private boolean isClass(HashMap<Nature,Boolean> natureHash){
			return !natureHash.get(Nature.DATATYPE) && 
						(natureHash.get(Nature.QUANTITY) || natureHash.get(Nature.COLLECTIVE) || natureHash.get(Nature.FUNCTIONAL) ||
						natureHash.get(Nature.RELATOR) || natureHash.get(Nature.MODE)) ;
		}
		
		public boolean isBetweenRelatorAndObject() {
			return (isRelator(sourceNatureHash) && isObject(targetNatureHash)) ||
					(isRelator(targetNatureHash) && isObject(sourceNatureHash));
		}
		
		public boolean isBetweenModeAndClass() {
			return (isMode(sourceNatureHash) && isClass(targetNatureHash)) ||
					(isMode(targetNatureHash) && isClass(sourceNatureHash));
		}

		public boolean isBetweenModes() {
			return isMode(targetNatureHash) && isMode(sourceNatureHash);
		}
		
		public boolean isBetweenQuantities() {
			return isQuantity(targetNatureHash) && isQuantity(sourceNatureHash);
		}

		public boolean isBetweenCollectives() {
			return isCollective(targetNatureHash) && isCollective(sourceNatureHash);
		}
		
		public boolean isBetweenCollectiveAndFunctional() {
			return (isCollective(sourceNatureHash) && isFunctional(targetNatureHash)) ||
					(isCollective(targetNatureHash) && isFunctional(sourceNatureHash));
		}
		
		public boolean isBetweenFunctionals(){
			return isFunctional(targetNatureHash) && isFunctional(sourceNatureHash);
		}

		
		
}