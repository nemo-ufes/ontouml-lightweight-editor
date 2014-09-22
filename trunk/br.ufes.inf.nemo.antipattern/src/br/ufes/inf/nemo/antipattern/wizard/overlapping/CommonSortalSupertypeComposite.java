package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.overlapping.CommonSortalSupertype;

public class CommonSortalSupertypeComposite extends Composite {
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CommonSortalSupertypeComposite(Composite parent, int style, CommonSortalSupertype group) {
		super(parent, SWT.BORDER);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("Identity Provider:");
		
		text = new Text(this, SWT.BORDER);
		text.setText(OntoUMLNameHelper.getTypeAndName(group.getIdentityProvider(), true, false));
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setText("Closest Supertype(s): ");
		
		List list = new List(this, SWT.BORDER);
		for (Classifier c : group.getClosestSupertypes()) {
			list.add(OntoUMLNameHelper.getTypeAndName(c, true, true));
		}
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.addContainerGap()
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING, false)
						.add(lblNewLabel, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
						.add(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
					.addPreferredGap(LayoutStyle.UNRELATED)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(list, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.add(text, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
					.add(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.addContainerGap()
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(lblNewLabel))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(list, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.add(10))
						.add(groupLayout.createSequentialGroup()
							.add(lblNewLabel_1)
							.addContainerGap())))
		);
		setLayout(groupLayout);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
