package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;

import RefOntoUML.Constraintx;
import RefOntoUML.NamedElement;
import RefOntoUML.PackageableElement;
import RefOntoUML.impl.ConstraintxImpl;
import RefOntoUML.impl.PropertyImpl;
import br.ufes.inf.nemo.oled.ui.DiagramManager;
import br.ufes.inf.nemo.oled.ui.ModelTree;
import br.ufes.inf.nemo.oled.util.ApplicationResources;


public class EditAnnotationDialog extends JDialog {

	private static final long serialVersionUID = -6707497708908105957L;
	private JButton cancelButton;
	private JButton okButton;
	private JLabel elementNameLabel;
	private JScrollPane annotationScroll;
	private CommentTableModel annotationsTableModel;
	private JTable annotationsTable;
	private NamedElement element;
	private JButton addAnttButton;
	private JButton delAnttButton;
	private JButton oclButton;
	private DiagramManager diagramManager;
	
	public EditAnnotationDialog(JFrame parent, DiagramManager aDiagramManager, NamedElement element, boolean modal) {
		super(parent, modal);
		
		diagramManager = aDiagramManager;
		this.element = element;
		
		initGUI();
		myPostInit();
	}
	
	private void initGUI() {
		try {
			{
				setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
				setTitle(ApplicationResources.getInstance().getString("dialog.classproperties.title"));

				GroupLayout layout = new GroupLayout((JComponent)getContentPane());
				getContentPane().setLayout(layout);
				this.setResizable(false);
				{
					elementNameLabel = new JLabel();
					elementNameLabel.setText(ApplicationResources.getInstance().getString("stdcaption.name"));
				}
				{
					addAnttButton = new JButton();
					addAnttButton.setText(ApplicationResources.getInstance().getString("stdcaption.add"));
					addAnttButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							addAnttButtonActionPerformed(evt);
						}
					});
				}
				{
					delAnttButton = new JButton();
					delAnttButton.setText(ApplicationResources.getInstance().getString("stdcaption.delete"));
					delAnttButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							delAnttButtonActionPerformed(evt);
						}
					});
				}
				{
					cancelButton = new JButton();
					cancelButton.setText(ApplicationResources.getInstance().getString("stdcaption.cancel"));
					cancelButton
							.addActionListener(new java.awt.event.ActionListener() {
								public void actionPerformed(
										java.awt.event.ActionEvent evt) {
									cancelButtonActionPerformed(evt);
								}
							});
				}
				{
					okButton = new JButton();
					okButton.setText(ApplicationResources.getInstance().getString("stdcaption.ok"));
					okButton.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(
								java.awt.event.ActionEvent evt) {
							okButtonActionPerformed(evt);
						}
					});
				}
				{
					annotationScroll = new JScrollPane();
					annotationScroll.setEnabled(false);
					{
						
						annotationsTableModel = new CommentTableModel(element);
						annotationsTable = new JTable();
						annotationsTable.setRowHeight(23);
						TableColumnModel columnModel =  new DefaultTableColumnModel() {
							
							private static final long serialVersionUID = -3175335869494224089L;
							
							public void moveColumn(
									int columnIndex,
									int newIndex) {
								if (columnIndex == 0
										|| newIndex == 0)
									return; // don't allow
									super.moveColumn(columnIndex,
											newIndex);
							}
						};
						
						annotationsTable.setColumnModel(columnModel);
						annotationScroll.setViewportView(annotationsTable);
						annotationsTable.setModel(annotationsTableModel);
					}
				}
				{
					oclButton = new JButton();
					oclButton.setText("Parse");
					oclButton.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(
								java.awt.event.ActionEvent evt) {
							parseSelected();
						}
					});
				}
				layout.setVerticalGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(elementNameLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(annotationScroll, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					        .addComponent(addAnttButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addComponent(delAnttButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					    .addComponent(oclButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(0, 29, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(okButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(17, 17));
				layout.setHorizontalGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
					        .addComponent(addAnttButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					        .addComponent(delAnttButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 113, GroupLayout.PREFERRED_SIZE)
					        .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					        .addGroup(layout.createParallelGroup()
					            .addComponent(okButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
					                .addGap(0, 17, Short.MAX_VALUE)
					                .addComponent(oclButton, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, GroupLayout.PREFERRED_SIZE))))
					    .addComponent(elementNameLabel, GroupLayout.Alignment.LEADING, 0, 455, Short.MAX_VALUE)
					    .addComponent(annotationScroll, GroupLayout.Alignment.LEADING, 0, 455, Short.MAX_VALUE))
					.addGap(7));

			}
			this.setSize(480, 370);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void myPostInit() {
			
		//Try to set the element name
		//Annotations of Kind1
		//Annotations of Property Property1 of Kind1 ()
		//Annotations of property [unnamed] of Kind1 
		
		String lblTxt = "Annotations of ";
		
		if (element instanceof PropertyImpl == false)
		{
			lblTxt += element.getName();
		}
		else
		{
			lblTxt += "property ";
			if(element.getName() == null || element.getName().equals(""))
				lblTxt += "[unnamed] of ";
			else
				lblTxt += element.getName() + " of ";
			
			lblTxt += ((NamedElement)element.eContainer()).getName();
		}
			
		elementNameLabel.setText(lblTxt);
		
		for (PackageableElement elm : diagramManager.getCurrentProject().getModel().getPackagedElement()) {
			
			if(elm instanceof ConstraintxImpl)
			{
				Constraintx cnx = (Constraintx) elm;
				if(cnx.getConstrainedElement().contains(element))
					annotationsTableModel.addEntry(cnx);
			}
		}
		
		getRootPane().setDefaultButton(okButton);
		
		setLocationRelativeTo(null);
	}
		
	private void parseSelected()
	{
		String expr = (String) annotationsTableModel.getValueAt(annotationsTable.getSelectedRow(), annotationsTable.getSelectedColumn());
		final OCL OCL_ENV = OCL.newInstance();
		OCL.Helper helper = OCL_ENV.createOCLHelper(); //RefOntoUMLPackage.eINSTANCE.getClass_()
		helper.setContext((EClassifier) element);
		try {
			Constraint const1 = helper.createInvariant(expr);
			
			Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(const1);
			
			if (!query.check(element))
				System.out.println("Válido");
			else
				System.out.println("Válido");
			
		} catch (ParserException e) {
			System.out.println("Inválido");
		}
	}
	
	private void transferDataToElement()
	{
		/*for (Constraintx cnx : annotationsTableModel.getEntries()) {
			//cnx.setContext((Namespace) element);
			cnx.getConstrainedElement().add(element);
			diagramManager.getCurrentProject().getModel().getPackagedElement().add(cnx);
		}*/		
	}
	
	private void okButtonActionPerformed(ActionEvent evt) {
		transferDataToElement();
		//update model tree
		ModelTree.updateModelTree(diagramManager.getCurrentProject());
		dispose();
	}
	
	private void cancelButtonActionPerformed(ActionEvent evt) {
		dispose();
	}

	private void addAnttButtonActionPerformed(ActionEvent evt) {
		annotationsTableModel.addEmptyEntry();
	}
	
	private void delAnttButtonActionPerformed(ActionEvent evt) {
		int selectedRow = annotationsTable.getSelectedRow();
		if (selectedRow >= 0 && selectedRow < annotationsTableModel.getRowCount()) {
			annotationsTableModel.removeEntryAt(selectedRow);
		}
	}
}
