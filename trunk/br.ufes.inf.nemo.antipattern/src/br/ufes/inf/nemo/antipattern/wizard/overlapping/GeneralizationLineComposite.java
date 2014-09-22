package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.overlapping.GeneralizationLine;

public class GeneralizationLineComposite extends Composite {
	private Text text;
	private Text text_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public GeneralizationLineComposite(Composite parent, int style, GeneralizationLine group) {
		super(parent, style);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("Parent:");
		
		text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text.setText(OntoUMLNameHelper.getNameAndType(group.getParent()));
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setText("Child:");
		
		text_1 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_1.setText(OntoUMLNameHelper.getNameAndType(group.getChild()));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.addContainerGap()
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING, false)
						.add(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.add(lblNewLabel, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(text_1, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
						.add(text, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
					.add(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(lblNewLabel)
						.add(text, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.add(16)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(text_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(lblNewLabel_1))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
