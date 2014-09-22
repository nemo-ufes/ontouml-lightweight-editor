package br.ufes.inf.nemo.antipattern.wizard.relspec;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;

public class AssociationComposite extends Composite{
	private Text parentText;
	private Text parentSourceText;
	private Text parentTargetText;
	private Text childText;
	private Text childSourceText;
	private Text childTargetText;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AssociationComposite(Composite parent, int style, RelSpecOccurrence	relSpec) {
		super(parent, style);
		
		Label lblName = new Label(this, SWT.NONE);
		lblName.setText("PARENT:");

		parentText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		parentText.setText(OntoUMLNameHelper.getTypeAndName(relSpec.getGeneral(), true, false));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("Source:");
		
		parentSourceText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		parentSourceText.setText(OntoUMLNameHelper.getNameAndType(relSpec.getGeneralSourceEnd(), true));
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setText("Target:");
		
		parentTargetText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		parentTargetText.setText(OntoUMLNameHelper.getNameAndType(relSpec.getGeneralTargetEnd(), true));
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setText("CHILD:");
		
		childText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		childText.setText(OntoUMLNameHelper.getTypeAndName(relSpec.getSpecific(), true, false));
		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setText("Source");
		
		childSourceText = new Text(this, SWT.BORDER| SWT.READ_ONLY);
		childSourceText.setText(OntoUMLNameHelper.getNameAndType(relSpec.getAlignedSpecificSourceEnd(), true));
		
		Label lblNewLabel_4 = new Label(this, SWT.NONE);
		lblNewLabel_4.setText("Target:");
		
		childTargetText = new Text(this, SWT.BORDER| SWT.READ_ONLY);
		childTargetText.setText(OntoUMLNameHelper.getNameAndType(relSpec.getAlignedSpecificTargetEnd(), true));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(groupLayout.createParallelGroup(GroupLayout.TRAILING)
						.add(groupLayout.createSequentialGroup()
							.add(56)
							.add(childText, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
						.add(GroupLayout.LEADING, groupLayout.createSequentialGroup()
							.add(1)
							.add(groupLayout.createParallelGroup(GroupLayout.TRAILING, false)
								.add(GroupLayout.LEADING, lblNewLabel_3, 0, 0, Short.MAX_VALUE)
								.add(GroupLayout.LEADING, lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.add(GroupLayout.LEADING, lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.add(GroupLayout.LEADING, lblName, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.RELATED)
							.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
								.add(GroupLayout.TRAILING, parentText, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
								.add(groupLayout.createSequentialGroup()
									.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
										.add(childSourceText, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
										.add(parentSourceText, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
									.addPreferredGap(LayoutStyle.RELATED)
									.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
										.add(lblNewLabel_4)
										.add(lblNewLabel_1))
									.add(6)
									.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
										.add(childTargetText, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
										.add(parentTargetText, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))))))
					.add(1))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(lblName)
						.add(parentText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(lblNewLabel)
						.add(parentSourceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(lblNewLabel_1)
						.add(parentTargetText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(18)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(lblNewLabel_2)
						.add(childText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(lblNewLabel_3)
						.add(childSourceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(lblNewLabel_4)
						.add(childTargetText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
