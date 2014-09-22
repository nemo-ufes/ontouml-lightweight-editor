package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.overlapping.CommonMixinSupertype;

public class CommonMixinSupertypeComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CommonMixinSupertypeComposite(Composite parent, int style, CommonMixinSupertype group) {
		super(parent, style);
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setText("Closest Common Supertype(s):");
		
		List list = new List(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		for (Classifier c : group.getClosestSupertypes()) {
			list.add(OntoUMLNameHelper.getTypeAndName(c, true, true));
		}
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(list, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
					.add(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(list, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.add(lblNewLabel_1))
					.add(10))
		);
		setLayout(groupLayout);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
