package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.Bullet;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Relator;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;

public class ChangeRelationStereotypePage extends UndefFormalPage{
	
	public Composite parent;
	public Button btnYes;
	public Button btnNo;

	private Composite optionComposite;
	public Button btn1SourceWhole;
	public Button btn1TargetWhole;
	private Button btn2SourceWhole;
	private Button btn2TargetWhole;
	private StyledText questionText;
	
	/**
	 * Create the wizard.
	 */
	public ChangeRelationStereotypePage(UndefFormalOccurrence uf) 
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
		
		questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		questionText.setJustify(true);
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setText("The question will go here!");
		questionText.setAlwaysShowScrollBars(false);
		
		Composite composite = new Composite(container, SWT.NONE);
		
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
	          if(getAntipatternWizard().isBetweenModes() || getAntipatternWizard().isBetweenQuantities() || getAntipatternWizard().isBetweenCollectives() || getAntipatternWizard().isBetweenFunctionals()){
	    		  optionComposite.setVisible(true);
	    		  btn1TargetWhole.setVisible(true);
	    		  btn1SourceWhole.setVisible(true);	  	          
	    	  }	    		
	          if(getAntipatternWizard().isBetweenCollectives()){
	    		  btn2TargetWhole.setVisible(true);
	    		  btn2SourceWhole.setVisible(true);
	    	  }    	  
	        }
	    }};
					    
		btnYes = new Button(composite, SWT.RADIO);
		btnYes.setText("Yes");
		btnYes.addSelectionListener(listener);
		
		btnNo = new Button(composite, SWT.RADIO);
		btnNo.setText("No");
		btnNo.addSelectionListener(listener);
		
		optionComposite = new Composite(composite, SWT.NONE);
		optionComposite.setVisible(false);
		
		btn1SourceWhole = new Button(optionComposite, SWT.RADIO);
		btn1SourceWhole.setVisible(false);
		btn1SourceWhole.addSelectionListener(listener);
		
		btn1TargetWhole = new Button(optionComposite, SWT.RADIO);
		btn1TargetWhole.setVisible(false);
		btn1TargetWhole.addSelectionListener(listener);
		
		btn2SourceWhole = new Button(optionComposite, SWT.RADIO);
		btn2SourceWhole.setVisible(false);
		btn2SourceWhole.addSelectionListener(listener);
		
		btn2TargetWhole = new Button(optionComposite, SWT.RADIO);
		btn2TargetWhole.setVisible(false);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(questionText, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
						.add(composite, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))
					.add(14))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(composite, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.add(12))
		);
		GroupLayout gl_composite = new GroupLayout(composite);
		gl_composite.setHorizontalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite.createSequentialGroup()
					.add(btnNo, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnYes, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_composite.createSequentialGroup()
					.add(10)
					.add(optionComposite, GroupLayout.PREFERRED_SIZE, 566, GroupLayout.PREFERRED_SIZE))
		);
		gl_composite.setVerticalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite.createSequentialGroup()
					.add(10)
					.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
						.add(btnNo)
						.add(btnYes))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(optionComposite, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
		);
		GroupLayout gl_optionComposite = new GroupLayout(optionComposite);
		gl_optionComposite.setHorizontalGroup(
			gl_optionComposite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_optionComposite.createSequentialGroup()
					.add(10)
					.add(gl_optionComposite.createParallelGroup(GroupLayout.LEADING)
						.add(btn1SourceWhole, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
						.add(btn1TargetWhole, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
						.add(btn2SourceWhole, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
						.add(btn2TargetWhole, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
					.add(10))
		);
		gl_optionComposite.setVerticalGroup(
			gl_optionComposite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_optionComposite.createSequentialGroup()
					.add(10)
					.add(btn1SourceWhole)
					.add(6)
					.add(btn1TargetWhole)
					.add(6)
					.add(btn2SourceWhole)
					.add(6)
					.add(btn2TargetWhole))
		);
		optionComposite.setLayout(gl_optionComposite);
		composite.setLayout(gl_composite);
		container.setLayout(gl_container);
		btn2TargetWhole.addSelectionListener(listener);
		
		setQuestionUI();
	}	
	
	void setQuestionUI() {
		
		btn1SourceWhole.setVisible(false);
		btn1TargetWhole.setVisible(false);
		btn2SourceWhole.setVisible(false);
		btn2TargetWhole.setVisible(false);
		
		btn1SourceWhole.setSelection(false);
		btn1TargetWhole.setSelection(false);
		btn2SourceWhole.setSelection(false);
		btn2TargetWhole.setSelection(false);
		
		btnNo.setSelection(true);
		btnYes.setSelection(false);
		
		// Relator / Object
		if (getAntipatternWizard().isBetweenRelatorAndObject()) 
		{
			questionText.setText(
					"If not Domain Comparative, the internal relation that usually holds between an Object type and a Relator is the Mediation. " +
					"\n\nIt captures that instances of the relator are existentially dependent on the instances of the object." +
					"\r\n\r\n" +
					"Is that the case?");
			
			btnYes.setText("Yes, it should be a Mediation");
		}
		
		// Mode / Object or Mode / Mode
		else if (getAntipatternWizard().isBetweenModeAndClass() || getAntipatternWizard().isBetweenModes())
		{
			questionText.setText(
					"If not Domain Comparative, the internal relation that usually holds between a Mode and another Class (including another Mode) is the Characterization. "+
					"It captures that the Mode is an intrinsic property of the characterized Class. " +
					"\n\n" +
					"Note that the use of Modes and Characterizations in OntoUML is restricted as follows: "+
					"\n\nA Mode type can only characterize one Class" +
					"\nIn every Characterization, the characterized end must be readOnly" +
					"\nIn every Characterization, the multiplicity on the characterized end is exatcly 1" +
					"\nIn every Characterization, the multiplicity on the Mode end must be equal or greater than 1"+
					"\r\n\r\n" +
					"Does the relation "+OntoUMLNameHelper.getTypeAndName(occurrence.getFormal(),true,true)+" fits in such conditions?");
			StyleRange styleRange = new StyleRange();
			styleRange.metrics = new GlyphMetrics(0, 0, 40);
			Bullet bullet = new Bullet(styleRange);
			questionText.setLineBullet(4, 4, bullet);
			btnYes.setText("Yes, it should be a Characterization");
			
			if (getAntipatternWizard().isBetweenModes()){			
				btn1SourceWhole.setText("<"+occurrence.getSource().getName()+"> acting as the Characterizing Mode");
				btn1SourceWhole.setSelection(true);
				btn1TargetWhole.setText("<"+occurrence.getSource().getName()+"> acting as the Characterized Mode");
			}
		}
		
		// Quantity / Quantity
		else if (getAntipatternWizard().isBetweenQuantities())
		{
			questionText.setText(
					"If not Domain Comparative, the internal relation that usually holds between two Quantities is the SubQuantityOf. "+
					"\n\nThis is a particular type of part-whole relation that holds between ammounts of matter." +
					"\r\n\r\n" +
					"Note that the use of SubQuantityOf in OntoUML is restricted as follows: "+
					"\n\nIt is always nonShareable" +
					"\nThe upper cardinality on the whole end must be equal to 1."+
					"\n\nIs the relation "+OntoUMLNameHelper.getTypeAndName(occurrence.getFormal(),true,true)+" if fact a SubQuantityOf?");
			
			StyleRange styleRange = new StyleRange();
			styleRange.metrics = new GlyphMetrics(0, 0, 40);
			Bullet bullet = new Bullet(styleRange);
			questionText.setLineBullet(6, 2, bullet);
			
			btnYes.setText("Yes, it should be a SubQuantityOf");
			btn1SourceWhole.setText("<"+occurrence.getSource().getName()+"> acting as the Whole");
			btn1SourceWhole.setSelection(true);
			btn1TargetWhole.setText("<"+occurrence.getTarget().getName()+"> acting as the Whole");			
		}
		
		// Collective / Collective
		else if (getAntipatternWizard().isBetweenCollectives())
		{
			questionText.setText(
					"If not Domain Comparative, there are two types of internal relations that can hold between two Collectives: MemberOf and SubCollectionOf."+
					"\n\nThey are both part-whole relations, but MemberOf holds a between collection and its members, while SubCollectionOf holds between a collection and its sub-collections."+
					"\n\n" +
					"Shoudn't the relation "+OntoUMLNameHelper.getTypeAndName(occurrence.getFormal(),true,true)+" be categorized as MemberOf or SubCollectionOf?");
			
			btnYes.setText("Yes, it should be a MemberOf or a SubCollectionOf");
			
			btn1SourceWhole.setText("MemberOf: <"+occurrence.getSource().getName()+"> acting as the Whole");	
			btn1TargetWhole.setText("MemberOf: <"+occurrence.getTarget().getName()+"> acting as the Whole");
			btn2SourceWhole.setText("SubCollectionOf: <"+occurrence.getSource().getName()+"> acting as the Whole");
			btn2TargetWhole.setText("SubCollectionOf: <"+occurrence.getTarget().getName()+"> acting as the Whole");
			btn1SourceWhole.setSelection(true);
		}
		
		// Collective / Functional
		else if (getAntipatternWizard().isBetweenCollectiveAndFunctional())
		{
			questionText.setText(
					"If not Domain Comparative, the internal relation that usually holds between a Collection and a Functional Complex is the MemberOf. "+
					"\n\nIt is a particular type of part-whole relations, and it has embedded the following constraints: "+
					"\r\n\n"+
					"Asymmetry: if A is a member of B then B is not a member of A;"+"\n"+
					"Irreflexivity: A is not a member of A; and"+"\n"+ 
					"Acyclicity: if A is a member of B and and B is a member of C then C is not a member of A"+"\n"+
					"Intransitivity: if A member of B and B member of C then A is not a member of C"+
					"\r\n\r\n" +
					"Does that capture the intended semantics of the relation "+OntoUMLNameHelper.getTypeAndName(occurrence.getFormal(),true,true)+"?");
			StyleRange styleRange = new StyleRange();
			styleRange.metrics = new GlyphMetrics(0, 0, 40);
			Bullet bullet = new Bullet(styleRange);
			questionText.setLineBullet(4, 4, bullet);
			
			btnYes.setText("Yes, it should be a MemberOf");
		}
		
		// Functional / Functional
		else if (getAntipatternWizard().isBetweenFunctionals())
		{
			questionText.setText(
					"If not Domain Comparative, the internal relation that usually holds between two Functional Complexes is the ComponentOf. "+
					"\n\nIt is a particular type of part-whole relation and it captures functionality dependence, i.e., " +
					"in order for an instance of the whole to function as such, it requires an instance of the part functioning as such."+
					"\r\n\r\nNote that the following constraints are embedded in all ComponentOfs: \n\n"+
					"Asymmetry: if A is a part of B then B is not a part of A;"+"\n"+	
					"Irreflexivity: A is not a part of A; and"+"\n"+ 
					"Acyclicity: if A is a part of B and B is a part of C then C is not a part of A"+"\n"+
					"Transitivity: if A is a part of B and B is a part of C then A is a part of C"+"\n\n"+
					"Isn't the relation "+OntoUMLNameHelper.getTypeAndName(occurrence.getFormal(),true,true)+" a ComponentOf?");	
			
			StyleRange styleRange = new StyleRange();
			styleRange.metrics = new GlyphMetrics(0, 0, 40);
			Bullet bullet = new Bullet(styleRange);
			questionText.setLineBullet(6, 4, bullet);
			
			btnYes.setText("Yes, it should be a ComponentOf");
			
			btn1SourceWhole.setText("<"+occurrence.getSource().getName()+"> acting as the Whole");
			btn1SourceWhole.setSelection(true);
			btn1TargetWhole.setText("<"+occurrence.getTarget().getName()+"> acting as the Whole");
		}
		
		
	}	
	 
	@Override
	public IWizardPage getNextPage() 
	{	
		
		if(btnYes.getSelection()){
		
			// Relator / Object
			if (getAntipatternWizard().isBetweenRelatorAndObject()) {
				//Action =============================
				UndefFormalAction newAction = new UndefFormalAction(occurrence);
				newAction.setChangeToMediation(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				getAntipatternWizard().replaceAction(0,newAction);	
				//======================================
				
				//Check condition
				Classifier relator = null;
				if(occurrence.getTarget() instanceof Relator && !(occurrence.getSource() instanceof Relator)) 
					relator = occurrence.getTarget();
				else 
					relator = occurrence.getTarget();
				
				ArrayList<Mediation> mediations = new ArrayList<Mediation>();
				occurrence.getParser().getAllMediations(relator, mediations);
				
				if (mediations.size()>0)
					return getAntipatternWizard().getFinishing();
				else 
					return getAntipatternWizard().getCreateMediatedPage();
			}
		
			// Mode / Object
			else if (getAntipatternWizard().isBetweenModeAndClass()){
				//Action =============================
				UndefFormalAction newAction = new UndefFormalAction(occurrence);
				newAction.setChangeToCharacterization(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				getAntipatternWizard().replaceAction(0,newAction);	
				//======================================
				return getAntipatternWizard().getFinishing();
			}
		
			// Quantity / Quantity
			else if (getAntipatternWizard().isBetweenQuantities()){		
				//Action =============================
				UndefFormalAction newAction = new UndefFormalAction(occurrence);
				if (btn1SourceWhole.getSelection()) newAction.setChangeToSubQuantityOfSrcWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				if (btn1TargetWhole.getSelection()) newAction.setChangeToSubQuantityOfTgtWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				getAntipatternWizard().replaceAction(0,newAction);	
				//======================================
				return getAntipatternWizard().getFinishing();
			}
		
			// Collective / Collective
			else if (getAntipatternWizard().isBetweenCollectives()){
				//Action =============================
				UndefFormalAction newAction = new UndefFormalAction(occurrence);
				if(btn1SourceWhole.getSelection()) newAction.setChangeToMemberOfSrcWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				if(btn1TargetWhole.getSelection()) newAction.setChangeToMemberOfTgtWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				if(btn2SourceWhole.getSelection()) newAction.setChangeToSubCollectionOfSrcWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				if(btn2TargetWhole.getSelection()) newAction.setChangeToSubCollectionOfTgtWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
				getAntipatternWizard().replaceAction(0,newAction);
				//======================================
				return getAntipatternWizard().getFinishing();
			}
		
			// Collective / Functional
			else if (getAntipatternWizard().isBetweenCollectiveAndFunctional()){
				//Action =============================
				UndefFormalAction newAction = new UndefFormalAction(occurrence);
				newAction.setChangeToMemberOfSrcWhole(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());						
				getAntipatternWizard().replaceAction(0,newAction);	
				//======================================
				return getAntipatternWizard().getFinishing();
			}
		
			// Functional / Functional
			else if (getAntipatternWizard().isBetweenFunctionals()){
			
				//Action =============================
				UndefFormalAction newAction = new UndefFormalAction(occurrence);
				
				if(btn1SourceWhole.getSelection())
					newAction.setChangeToComponentOF(occurrence.getSource(), occurrence.getTarget());
				else
					newAction.setChangeToComponentOF(occurrence.getTarget(), occurrence.getSource());
					
				getAntipatternWizard().replaceAction(0,newAction);	
				return getAntipatternWizard().getFinishing();			
			}
		}
		
		if(btnNo.getSelection()){
			getAntipatternWizard().noRelationTypePossiblePage.setQuestion();
			return getAntipatternWizard().noRelationTypePossiblePage;
		}
		
		return null;
	}
}
