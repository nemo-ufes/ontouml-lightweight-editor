package br.ufes.inf.nemo.antipattern.wizard.binover;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CCombo;

public class BinOverRefactoringPage extends RefactoringPage {

	protected BinOverOccurrence binOver;
	
	public BinOverRefactoringPage(BinOverOccurrence binOver) {
		super();
		this.binOver = binOver;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		Label label = new Label(container, SWT.NONE);
		label.setText("Choose the appropriate refactoring options:");
		label.setBounds(10, 10, 552, 15);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(10, 74, 64, 21);
		lblNewLabel.setText("Reflexivity: ");
		
		CCombo combo = new CCombo(container, SWT.BORDER);
		combo.setItems(new String[] {"Reflexive", "Irreflexive", "Non Reflexive"});
		combo.setBounds(80, 74, 122, 21);
		
		CCombo combo_1 = new CCombo(container, SWT.BORDER);
		combo_1.setItems(new String[] {"Symmetric", "Asymmetric", "Non Symmetric"});
		combo_1.setBounds(80, 101, 122, 21);
		
		Label lblSymmetry = new Label(container, SWT.NONE);
		lblSymmetry.setText("Symmetry: ");
		lblSymmetry.setBounds(10, 101, 64, 21);
		
		CCombo combo_2 = new CCombo(container, SWT.BORDER);
		combo_2.setItems(new String[] {"Transitive", "Intransitive", "Non Transitive"});
		combo_2.setBounds(80, 128, 122, 21);
		
		Label lblTransitivity = new Label(container, SWT.NONE);
		lblTransitivity.setText("Transitivity: ");
		lblTransitivity.setBounds(10, 128, 64, 21);
		
		Label lblCiclicity = new Label(container, SWT.NONE);
		lblCiclicity.setText("Ciclicity: ");
		lblCiclicity.setBounds(10, 155, 64, 21);
		
		CCombo combo_3 = new CCombo(container, SWT.BORDER);
		combo_3.setItems(new String[] {"Cyclic", "Acyclic", "Non Cyclic"});
		combo_3.setBounds(80, 155, 122, 21);
		
		Label lblIsDisjoint = new Label(container, SWT.NONE);
		lblIsDisjoint.setText("Is Disjoint: ");
		lblIsDisjoint.setBounds(10, 47, 64, 21);
		
		CCombo combo_4 = new CCombo(container, SWT.BORDER);
		combo_4.setItems(new String[] {"True", "False"});
		combo_4.setBounds(80, 47, 122, 21);
	}
		
	public BinOverWizard getBinOverWizard(){
		return (BinOverWizard)getWizard();
	}
}
