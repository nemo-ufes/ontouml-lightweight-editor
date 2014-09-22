package br.ufes.inf.nemo.antipattern.wizard.relrig;

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;


public class RigidMediatedComposite extends Composite {
	private Text nameText;
	private Text stereotypeText;
	private Label titleLabel;
	private Label lblNewLabel;
	private Text endNameText;
	private Label mediatedMultLabel;
	private Text mediatedMultText;
	private Label relatorMultLabel;
	private Text relatorMultText;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @param rigid TODO
	 */
	public RigidMediatedComposite(Composite parent, int style, Property rigidEnd, int current, int total) {
		super(parent, style);
		
		Label lblRigid = new Label(this, SWT.NONE);
		lblRigid.setText("Type Name:");
		
		Label lblStereotype = new Label(this, SWT.NONE);
		lblStereotype.setText("Stereotype:");
		
		nameText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		nameText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		nameText.setText(rigidEnd.getType().getName());
		
		stereotypeText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		stereotypeText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		stereotypeText.setText(OntoUMLNameHelper.getTypeName(rigidEnd.getType()));
		
		titleLabel = new Label(this, SWT.NONE);
		titleLabel.setText("Rigid Mediated Type ("+current+" of "+total+")");
		
		FontDescriptor boldDescriptor = FontDescriptor.createFrom(titleLabel.getFont()).setStyle(SWT.BOLD);
		Font boldFont = boldDescriptor.createFont(titleLabel.getDisplay());
		titleLabel.setFont( boldFont );
		
		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("End Name:");
		
		endNameText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		endNameText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		endNameText.setText(rigidEnd.getName());
		
		mediatedMultLabel = new Label(this, SWT.NONE);
		mediatedMultLabel.setText("Mediated Mult.:");
		
		mediatedMultText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		mediatedMultText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		mediatedMultText.setText(OntoUMLNameHelper.getMultiplicity(rigidEnd, true, ".."));
		
		relatorMultLabel = new Label(this, SWT.NONE);
		relatorMultLabel.setText("Relator Mult.");
		
		relatorMultText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		relatorMultText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		relatorMultText.setText(OntoUMLNameHelper.getMultiplicity(rigidEnd.getOpposite(), true, ".."));
	
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
					.add(titleLabel, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
					.add(groupLayout.createSequentialGroup()
						.add(groupLayout.createParallelGroup(GroupLayout.TRAILING, false)
							.add(GroupLayout.LEADING, lblNewLabel, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
							.add(GroupLayout.LEADING, lblRigid, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
							.add(GroupLayout.LEADING, lblStereotype, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
							.add(GroupLayout.LEADING, mediatedMultLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(LayoutStyle.RELATED)
						.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
							.add(nameText, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
							.add(endNameText, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
							.add(GroupLayout.TRAILING, stereotypeText, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
							.add(groupLayout.createSequentialGroup()
								.add(mediatedMultText, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
								.add(18)
								.add(relatorMultLabel)
								.addPreferredGap(LayoutStyle.RELATED)
								.add(relatorMultText, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(titleLabel)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(lblNewLabel)
						.add(endNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(6)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(lblRigid)
						.add(nameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(stereotypeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(lblStereotype))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(mediatedMultLabel)
						.add(mediatedMultText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(relatorMultLabel)
						.add(relatorMultText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		setLayout(groupLayout);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
