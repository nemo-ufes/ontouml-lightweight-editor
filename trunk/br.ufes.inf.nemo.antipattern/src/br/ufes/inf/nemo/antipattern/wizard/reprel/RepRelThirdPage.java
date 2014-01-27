package br.ufes.inf.nemo.antipattern.wizard.reprel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;

public class RepRelThirdPage extends RepRelPage {

	public 	RepRelTable rrtable;
	
	public RepRelThirdPage(RepRelOccurrence rr) 
	{
		super(rr);
		setDescription("Page 3");
	}

	@Override
	public void createControl(Composite parent) {
	
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		
		rrtable = new RepRelTable (container,SWT.BORDER, repRel.getMediations(),repRel.getRelator().getName());
		rrtable.getTable().setBounds(10, 96, 554, 99);
				
		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setText("Since you marked that it is not possible for two relators mediate the same instances at the same time as many times as desired...\r\n\r\nThere must be a limit for the maximum number of instances of these \"repeated\" relators. Provide the limit N of instances of the Relator for each pair of the mediated types that you judge necessary.");
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBounds(10, 10, 554, 80);
		
		Button btnAddLine = new Button(container, SWT.NONE);
		btnAddLine.setBounds(489, 201, 75, 25);
		btnAddLine.setText("Add Line");
		
		btnAddLine.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 rrtable.addLine();
				 
//				 for (ArrayList<Mediation> line : rrtable.getSelections()) {
//					 System.out.print("Line: ");
//					 for (Mediation m : line) {
//						System.out.print(m.getType().getName()+", ");
//					 }
//					 System.out.println();
//				}
			 }
		});		
		
	}
}
