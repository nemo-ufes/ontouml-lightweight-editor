package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import java.text.Normalizer;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import org.eclipse.swt.graphics.Point;

public class CreateComponentOfComposite extends Composite {

	public HomoFuncOccurrence homoFunc;
	
	public CreateComponentOfComposite(Composite parent, int style, HomoFuncOccurrence homoFunc) 
	{
		super(parent, style);
		setSize(new Point(540, 157));
		this.homoFunc=homoFunc;
		createPartControl();
	}
	
	private Text componentOfNameField;
	private Combo typeCombo;
	private Button btnIsShareable;
	private Button btnIsEssential;
	private Button btnIsInseparable;
	private Button btnIsImmutablePart;
	private Button btnIsImmutableWhole;
	private Label lblType;
	private Label lblComponentofName;
	private Button btnCreateNewPart;
	private Button btnDeletePart;	
	private List relationList;
	public ArrayList<RelationElement> relations = new ArrayList<RelationElement>();
		
	public String getComponentOfName()
	{
		return componentOfNameField.getText();
	}
	
	public RefOntoUML.Class getType()
	{
		for(RefOntoUML.Class type: homoFunc.getParser().getAllInstances(RefOntoUML.Class.class))
		{
			String str = getStereotype(type)+" "+type.getName();
			if (str.compareToIgnoreCase(typeCombo.getItem(typeCombo.getSelectionIndex()))==0){
				return type;
			}
		}
		
		return null;
	}
	 
	public boolean isShareable()
	{
		return btnIsShareable.getSelection();
	}
	
	public boolean isEssential()
	{
		return btnIsEssential.getSelection();
	}
	
	public boolean isInseparable()
	{
		return btnIsInseparable.getSelection();
	}
	
	public boolean isImmutablePart()
	{
		return btnIsImmutablePart.getSelection();
	}
	
	public boolean isImmutableWhole(){
		return btnIsImmutableWhole.getSelection();
	}
	
	public void enableAll(boolean value)
	{
		lblType.setEnabled(value);		
		lblComponentofName.setEnabled(value);		
		typeCombo.setEnabled(value);		
		componentOfNameField.setEnabled(value);		
		btnIsShareable.setEnabled(value);		
		btnIsEssential.setEnabled(value);		
		btnIsInseparable.setEnabled(value);		
		btnIsImmutablePart.setEnabled(value);		
		btnIsImmutableWhole.setEnabled(value);
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
	
	public void createPartControl() 
	{		
		setLayout(null);
		
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		lblType = new Label(this, SWT.NONE);
		lblType.setText("Existing type:");
		lblType.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblType.setBounds(10, 13, 122, 21);
		
		lblComponentofName = new Label(this, SWT.NONE);
		lblComponentofName.setText("ComponentOf name:");
		lblComponentofName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblComponentofName.setBounds(10, 40, 122, 21);
		
		typeCombo = new Combo(this, SWT.READ_ONLY);
		typeCombo.setItems(new String[] {});
		typeCombo.setBounds(138, 10, 256, 23);
		
		for(RefOntoUML.Class type: homoFunc.getParser().getAllInstances(RefOntoUML.Class.class))
		{
			typeCombo.add(getStereotype(type)+" "+type.getName());
		}
		
		componentOfNameField = new Text(this, SWT.BORDER);
		componentOfNameField.setBounds(138, 39, 256, 21);
		
		btnIsShareable = new Button(this, SWT.CHECK);
		btnIsShareable.setText("isShareable");
		btnIsShareable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnIsShareable.setBounds(400, 117, 122, 21);
		
		btnIsEssential = new Button(this, SWT.CHECK);
		btnIsEssential.setText("isEssential");
		btnIsEssential.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnIsEssential.setBounds(400, 12, 122, 16);
		
		btnIsInseparable = new Button(this, SWT.CHECK);
		btnIsInseparable.setText("isInseparable");
		btnIsInseparable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnIsInseparable.setBounds(400, 66, 122, 16);
		
		btnIsImmutablePart = new Button(this, SWT.CHECK);
		btnIsImmutablePart.setText("isImmutablePart");
		btnIsImmutablePart.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnIsImmutablePart.setBounds(400, 39, 122, 16);
		
		btnIsImmutableWhole = new Button(this, SWT.CHECK);		
		btnIsImmutableWhole.setText("isImmutableWhole");
		btnIsImmutableWhole.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnIsImmutableWhole.setBounds(400, 95, 122, 16);
		
		btnCreateNewPart = new Button(this, SWT.NONE);
		btnCreateNewPart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (getType() !=null){
					RelationElement newrelation = new RelationElement(getType(), getComponentOfName(), 
							isShareable(), isEssential(), isImmutablePart(), isImmutableWhole(), isInseparable());
					relations.add(newrelation);
					if (!contains(relationList,newrelation.toString())) relationList.add(newrelation.toString());				
				}
			}
		});
		btnCreateNewPart.setText("New");
		btnCreateNewPart.setBounds(43, 67, 43, 25);
		
		setVisible(false);
		
		btnDeletePart = new Button(this, SWT.NONE);
		btnDeletePart.setText("Delete");
		btnDeletePart.setBounds(89, 67, 43, 25);
		
		relationList = new List(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		relationList.setBounds(138, 67, 256, 71);
		
		btnDeletePart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				removeRelation(relationList.getItem(relationList.getSelectionIndex()));
				
				relationList.remove(relationList.getSelectionIndex());
			}
		});
	}		
	
	public ArrayList<RelationElement> getRelations()
	{
		return relations;
	}
	
	public void enableCreation(boolean value)
	{
		enableAll(value);
		btnCreateNewPart.setEnabled(value);
		btnDeletePart.setEnabled(value);
		relationList.setEnabled(value);
		setVisible(value);
	}
	
	public boolean contains(List list, String relation)
	{
		for(int i=0;i< list.getItemCount();i++) 
		{
			if (relationList.getItem(i).compareToIgnoreCase(relation)==0){
				return true;
			}
		}
		return false;
	}
	
	public void removeRelation(String relation)
	{
		ArrayList<RelationElement> deletion = new ArrayList<RelationElement>();
		for(RelationElement p: relations) {
			if (p.toString().compareToIgnoreCase(relation)==0){
				deletion.add(p);
			}
		}
		relations.remove(relation);
	}			

}
