package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import RefOntoUML.Mediation;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;

public class ChangeFormalStereotypePage extends UndefFormalPage{
	
	public Composite parent;
	public Button btnYes;
	public Button btnNo;

	private Composite optionComposite;
	public Button btn1SourceWhole;
	public Button btn1TargetWhole;
	private Button btn2SourceWhole;
	private Button btn2TargetWhole;
	
	/**
	 * Create the wizard.
	 */
	public ChangeFormalStereotypePage(UndefFormalOccurrence uf) 
	{
		super(uf);
		setDescription("Source: "+uf.getSource().getName()+", Target: "+uf.getTarget().getName());
	}
	
	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		
		Label label = new Label(container, SWT.WRAP);
		label.setBounds(10, 10, 554, 144);
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setBounds(10, 160, 554, 100);
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
       
	        if (btnNo.getSelection()){
	        	 optionComposite.setVisible(false);
	        	 btn1TargetWhole.setVisible(false);
	    		 btn1SourceWhole.setVisible(false);	    	  
	    		 btn2TargetWhole.setVisible(false);
	    		 btn2SourceWhole.setVisible(false);
	        }
	        if (btnYes.getSelection()){
	          if(occurrence.isBetweenModes() || occurrence.isBetweenQuantities() || occurrence.isBetweenCollectives()){
	    		  optionComposite.setVisible(true);
	    		  btn1TargetWhole.setVisible(true);
	    		  btn1SourceWhole.setVisible(true);	  	          
	    	  }	    		
	          if(occurrence.isBetweenCollectives()){
	    		  btn2TargetWhole.setVisible(true);
	    		  btn2SourceWhole.setVisible(true);
	    	  }    	  
	        }
	    }};
					    
		btnYes = new Button(composite, SWT.RADIO);
		btnYes.setBounds(73, 10, 388, 16);
		btnYes.setText("Yes");
		btnYes.addSelectionListener(listener);
		
		btnNo = new Button(composite, SWT.RADIO);
		btnNo.setBounds(20, 10, 47, 16);
		btnNo.setText("No");
		btnNo.addSelectionListener(listener);
		
		optionComposite = new Composite(composite, SWT.NONE);
		optionComposite.setBounds(10, 32, 534, 58);
		optionComposite.setVisible(false);
		
		btn1SourceWhole = new Button(optionComposite, SWT.RADIO);
		btn1SourceWhole.setBounds(10, 10, 244, 16);
		btn1SourceWhole.setVisible(false);
		btn1SourceWhole.addSelectionListener(listener);
		
		btn1TargetWhole = new Button(optionComposite, SWT.RADIO);
		btn1TargetWhole.setBounds(10, 32, 244, 16);
		btn1TargetWhole.setVisible(false);
		btn1TargetWhole.addSelectionListener(listener);
		
		btn2SourceWhole = new Button(optionComposite, SWT.RADIO);
		btn2SourceWhole.setBounds(260, 10, 264, 16);
		btn2SourceWhole.setVisible(false);
		btn2SourceWhole.addSelectionListener(listener);
		
		btn2TargetWhole = new Button(optionComposite, SWT.RADIO);
		btn2TargetWhole.setBounds(260, 32, 264, 16);
		btn2TargetWhole.setVisible(false);
		btn2TargetWhole.addSelectionListener(listener);
		
		// Relator / Object
		if (occurrence.isBetweenRelatorAndObject()) 
		{
			label.setText("The relation that usually holds between an object and a relator is a particular type " +
			"of internal relation, entitled mediation. It captures that instances of the relator are existentially dependent on the instances of the " +
			"object. Is that the case?");
		}
		
		// Mode / Object
		else if (occurrence.isBetweenModeAndAnyOther() || occurrence.isBetweenModes())
		{
			label.setText("The relation that usually holds between modes and other types (even other modes) is a " +
			" type of internal relation, entitled characterization. It captures that instances of the mode provide intrinsic " +
			"characteristics of the type, which implies that they are existentially dependent on the instances of the characterized type. Is that the case?");
			if (occurrence.isBetweenModes()){		
				btnYes.setText("Yes, a characterization");
				btn1SourceWhole.setText(""+occurrence.getSource().getName()+" as source");
				btn1SourceWhole.setSelection(true);
				btn1TargetWhole.setText(""+occurrence.getTarget().getName()+" as target");
			}
		}
		
		// Quantity / Quantity
		else if (occurrence.isBetweenQuantities())
		{
			label.setText("The relation that usually holds between two quantities is particular type of internal relation, named subQuantityOf." +
			" That captures that the whole, a maximal amount of matter is composed by another maximal amout of matter, the part. An example is the " +
			"relation between Wine and Water, where the latter is a sub quantity of the former. Is that the case?");
			btnYes.setText("Yes, a quantityof");
			btn1SourceWhole.setText(""+occurrence.getSource().getName()+" as whole");
			btn1SourceWhole.setSelection(true);
			btn1TargetWhole.setText(""+occurrence.getTarget().getName()+" as whole");			
		}
		
		// Collective / Collective
		else if (occurrence.isBetweenCollectives())
		{
			label.setText("There are two types of relation that usually hold between two collectives: memberOf and subCollectionOf. Both of them are part-whole relations, " +
			"which imply asymmetry (if a part of b then b not part of a), irreflexivity (a not part of a) and acyclicity (if a part of b and b part of c then c is not part of a)."+"\n\n"+
			"A memberOf holds between a member and its collection, like a tree is a member of a forest. A subCollectionOf holds between two collections, like the collections of forks in a " +
			"cutlery set. Is that the case?");
			btnYes.setText("Yes, a memberOf or subcollectionOf");
			btn1SourceWhole.setText("MemberOf: "+occurrence.getTarget().getName()+" as member");	
			btn1SourceWhole.setSelection(true);
			btn1TargetWhole.setText("MemberOf: "+occurrence.getSource().getName()+" as member");
			btn2SourceWhole.setText("SubCollectionOf: "+occurrence.getTarget().getName()+" as subcollection");
			btn2TargetWhole.setText("SubCollectionOf: "+occurrence.getSource().getName()+" as subcollection");			
		}
		
		// Collective / Functional
		else if (occurrence.isBetweenCollectiveAndFunctional())
		{
			label.setText("The relation that is usually applicable between a collection ( as <collective>) and a functional complex ( as <functional>) is the memberOf. It is a particular type of part-whole relation, which implies: "+""+
			"- asymmetry: if a is a member of b then b is not a member of a;"+"\n"+
			"- irreflexivity: a is not a member of a; and"+"\n"+ 
			"- acyclicity: if a is a member of b and and b is a member of c then c is not a member of of a"+"\n"+
			"- intransitivity with other memberOf relations: if a member of b and b member of c then a is not a member of c"+"\n"+
			"Is that the case?");
		}
		
		// Functional / Functional
		else if (occurrence.isBetweenFunctionals())
		{
			label.setText("One possible relation that holds between functional complexes is a particular type of part-whole relation entitled componentOf. This has the semantics that for the whole to function as such, he needs a part functioning as such."+""+ 
			"As all part-whole relations componentOf is embbeded with the following constraints at the instance level:"+"\n"+
			"- asymmetry: if a is a functional part of b then b is not a functional part of a;"+"\n"+	
			"- irreflexivity: a is not a functional part of a; and"+"\n"+ 
			"- acyclicity: if a is a functional part of b and b is a functional part of c then c is not a functional part of a"+"\n"+
			"Is that the case?");		
		}
		
		// Object / Object
		else if (occurrence.isBetweenObjects())
		{
			label.setText("Consider the instances ‘x’ of "+occurrence.getSource().getName()+" and ‘y’ of "+occurrence.getTarget().getName()+". Is the reason why ‘x’ is connected to ‘y’ " +
			"through <<formal>> "+occurrence.getFormal().getName()+", the occurrence of an external event, which also creates a truth-maker for the relation? To help you answering this question, consider the " +
			"example: the relation that holds between an Employee and the Company in which he works is a material relation. A Hiring event creates the relation alongside with " +
			"its truth-maker, the Employment (a relator).");
		}	
	}	
	 
	@Override
	public IWizardPage getNextPage() 
	{	
		// Relator / Object
		if (occurrence.isBetweenRelatorAndObject())
		{
			if(btnYes.getSelection()){
				//Action =============================
				UndefFormalAction newAction = new UndefFormalAction(occurrence);
				newAction.setChangeToMediation(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				getAntipatternWizard().replaceAction(0,newAction);	
				//======================================
				
				//Check condition
				Relator relator = null;
				if(occurrence.getSource() instanceof Relator) relator = (Relator)occurrence.getSource();
				else relator = (Relator)occurrence.getTarget();
				ArrayList<Mediation> mediations = new ArrayList<Mediation>();
				occurrence.getParser().getAllMediations(relator, mediations);
				
				if (mediations.size()>0) return ((UndefFormalWizard)getWizard()).getFinishing();
				else return ((UndefFormalWizard)getWizard()).getFifthPage();
			}
			if(btnNo.getSelection()){
				return ((UndefFormalWizard)getWizard()).getThirdPage();
			}
		}
		
		// Mode / Object
		else if (occurrence.isBetweenModeAndAnyOther())
		{
			if(btnYes.getSelection()){
				//Action =============================
				UndefFormalAction newAction = new UndefFormalAction(occurrence);
				newAction.setChangeToCharacterization(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				getAntipatternWizard().replaceAction(0,newAction);	
				//======================================
				return ((UndefFormalWizard)getWizard()).getFinishing();
			}
			if(btnNo.getSelection()){
				return ((UndefFormalWizard)getWizard()).getThirdPage();
			}
		}		
		
		// Quantity / Quantity
		else if (occurrence.isBetweenQuantities())
		{
			if(btnYes.getSelection()){				
				//Action =============================
				UndefFormalAction newAction = new UndefFormalAction(occurrence);
				if (btn1SourceWhole.getSelection()) newAction.setChangeToSubQuantityOfSrcWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				if (btn1TargetWhole.getSelection()) newAction.setChangeToSubQuantityOfTgtWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				getAntipatternWizard().replaceAction(0,newAction);	
				//======================================
				return ((UndefFormalWizard)getWizard()).getFinishing();
			}
			if(btnNo.getSelection()){
				return ((UndefFormalWizard)getWizard()).getSecondPage();
			}
		}
		
		// Collective / Collective
		else if (occurrence.isBetweenCollectives())
		{
			if(btnYes.getSelection()){
				//Action =============================
				UndefFormalAction newAction = new UndefFormalAction(occurrence);
				if(btn1SourceWhole.getSelection()) newAction.setChangeToMemberOfSrcWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				if(btn1TargetWhole.getSelection()) newAction.setChangeToMemberOfTgtWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				if(btn2SourceWhole.getSelection()) newAction.setChangeToSubCollectionOfSrcWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				if(btn2TargetWhole.getSelection()) newAction.setChangeToSubCollectionOfTgtWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				getAntipatternWizard().replaceAction(0,newAction);
				//======================================
				return ((UndefFormalWizard)getWizard()).getFinishing();
			}
			if(btnNo.getSelection()){
				return ((UndefFormalWizard)getWizard()).getSecondPage();
			}
		}
		
		// Collective / Functional
		else if (occurrence.isBetweenCollectiveAndFunctional()){
			if(btnYes.getSelection()){
				//Action =============================
				UndefFormalAction newAction = new UndefFormalAction(occurrence);
				newAction.setChangeToMemberOfSrcWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());						
				getAntipatternWizard().replaceAction(0,newAction);	
				//======================================
				return ((UndefFormalWizard)getWizard()).getFinishing();
			}
			if(btnNo.getSelection()){
				return ((UndefFormalWizard)getWizard()).getSecondPage();
			}
		}
		
		// Functional / Functional
		else if (occurrence.isBetweenFunctionals()){
			if(btnYes.getSelection()){
				//Action =============================
				UndefFormalAction newAction = new UndefFormalAction(occurrence);
				newAction.setChangeToMemberOfSrcWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				getAntipatternWizard().replaceAction(0,newAction);	
				//======================================
				return ((UndefFormalWizard)getWizard()).getFinishing();
			}
			if(btnNo.getSelection()){
				return ((UndefFormalWizard)getWizard()).getSecondPage();
			}
		}
		
		// Object / Object
		else if (occurrence.isBetweenObjects()) {
			return ((UndefFormalWizard)getWizard()).getSecondPage();
		}
		
		return super.getNextPage();
	}
}
