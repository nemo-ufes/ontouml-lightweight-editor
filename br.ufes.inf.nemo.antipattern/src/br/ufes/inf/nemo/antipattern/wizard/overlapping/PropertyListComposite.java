package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingGroup;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;

public class PropertyListComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public PropertyListComposite(Composite parent, int style, OverlappingGroup group) {
		super(parent, style);
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setText(((OverlappingOccurrence) group.getAntipattern().getOccurrences().get(0)).getGroupTypeLine());
		
		List list = new List(this, SWT.BORDER | SWT.V_SCROLL);
		
		for (Property p : group.getOverlappingProperties()) {
			list.add(OntoUMLNameHelper.getNameAndType(p));
		}
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, list, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.add(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(lblNewLabel_1)
					.add(9)
					.add(list, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
