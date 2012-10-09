package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.eclipse.emf.edit.command.AddCommand;

import RefOntoUML.Class;
import RefOntoUML.DataType;
import RefOntoUML.Property;
import RefOntoUML.impl.DataTypeImpl;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.DiagramManager;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;
import br.ufes.inf.nemo.oled.util.ModelHelper;
import br.ufes.inf.nemo.oled.util.IconLoader.IconType;


public class EditClassDialog extends JDialog {

	private static final long serialVersionUID = -6707497708908105957L;
	private JButton attributeAddButton;
	private JCheckBox attributeVisibilityCheck;
	private JButton cancelButton;
	private JButton attributeDeleteButton;
	private JButton okButton;
	private JLabel classNameLabel;
	private JTextField classNameText;
	private JPanel classAttributesPanel;
	private JScrollPane attributeScroll;
	private JButton attributeMoveDownButton;
	private JButton attributeMoveUpButton;
	private JTable attributesTable;
	private JPanel attributesPanel;
	private ClassElement classElement;
	private AttributeTableModel attributesTableModel;
	private DiagramManager diagramManager;
	private Map<String, DataType> modelDataTypes; 
	private JButton clsAnttButton;
	private JCheckBox abstractCheck;
	private JFrame parent;

	public EditClassDialog(JFrame parent, DiagramManager diagramManager, ClassElement classElement, boolean modal) {
		super(parent, modal);
		
		this.diagramManager = diagramManager;
		this.classElement = classElement;
		this.parent = parent;
		
		initGUI();
		myPostInit();
	}
	
	private void initGUI() {
		try {
			{
				{
					setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
					setTitle(ApplicationResources.getInstance().getString("dialog.classproperties.title"));
				}
				
				GroupLayout layout = new GroupLayout((JComponent) getContentPane());
				getContentPane().setLayout(layout);
				
				this.setResizable(false);
				{
					classAttributesPanel = new JPanel();
					GroupLayout propertiesPanelLayout = new GroupLayout((JComponent)classAttributesPanel);
					classAttributesPanel.setLayout(propertiesPanelLayout);
					classAttributesPanel.setBorder(BorderFactory.createTitledBorder(ApplicationResources.getInstance().getString("dialog.class.attributes")));
					classAttributesPanel.setEnabled(false);
					{
						attributeVisibilityCheck = new JCheckBox();
						attributeVisibilityCheck.setText(ApplicationResources.getInstance().getString("dialog.class.showattributes"));
						attributeVisibilityCheck.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
						attributeVisibilityCheck.setMargin(new java.awt.Insets(0, 0, 0, 0));
						attributeVisibilityCheck.setSelected(true);

					}
					{
						attributesPanel = new JPanel();
						BorderLayout attributesPanelLayout = new BorderLayout();
						attributesPanel.setLayout(attributesPanelLayout);
						{
							attributeScroll = new JScrollPane();
							attributesPanel.add(attributeScroll,
									BorderLayout.CENTER);
							attributeScroll.setEnabled(false);
							{

								attributesTableModel = new AttributeTableModel(classElement.getClassifier());
								attributesTable = new JTable();
								attributesTable.setRowHeight(23);
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
								
								attributesTable.setColumnModel(columnModel);
								attributeScroll.setViewportView(attributesTable);
								attributesTable.setModel(attributesTableModel);
							}
						}
					}
					{
						attributeMoveUpButton = new JButton();
						attributeMoveUpButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								moveUpAttribute();
							}
						});
						attributeMoveUpButton.setText(ApplicationResources
								.getInstance().getString("stdcaption.moveup"));
					}
					{
						attributeMoveDownButton = new JButton();
						attributeMoveDownButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								moveDownAttribute();
							}
						});
						attributeMoveDownButton
								.setText(ApplicationResources.getInstance()
										.getString("stdcaption.movedown"));
					}
					{
						attributeAddButton = new JButton();
						attributeAddButton.setText(ApplicationResources.getInstance().getString("stdcaption.add"));
						attributeAddButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								attributeAddButtonActionPerformed(evt);
							}
						});
					}
					{
						attributeDeleteButton = new JButton();
						attributeDeleteButton.setText(ApplicationResources.getInstance().getString("stdcaption.delete"));
						attributeDeleteButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								attributeDeleteButtonActionPerformed(evt);
							}
						});
					}
					propertiesPanelLayout.setHorizontalGroup(propertiesPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(propertiesPanelLayout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, propertiesPanelLayout.createSequentialGroup()
						        .addGroup(propertiesPanelLayout.createParallelGroup()
						            .addGroup(GroupLayout.Alignment.LEADING, propertiesPanelLayout.createSequentialGroup()
						                .addComponent(attributeDeleteButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						                .addComponent(attributeAddButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						                .addGap(0, 0, Short.MAX_VALUE))
						            .addGroup(GroupLayout.Alignment.LEADING, propertiesPanelLayout.createSequentialGroup()
						                .addComponent(attributeVisibilityCheck, 0, 157, Short.MAX_VALUE)
						                .addGap(14)))
						        .addGap(24)
						        .addComponent(attributeMoveDownButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						        .addComponent(attributeMoveUpButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
						    .addComponent(attributesPanel, GroupLayout.Alignment.LEADING, 0, 386, Short.MAX_VALUE))
						.addContainerGap());
					propertiesPanelLayout.setVerticalGroup(propertiesPanelLayout.createSequentialGroup()
						.addContainerGap(18, 18)
						.addComponent(attributeVisibilityCheck, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(attributesPanel, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(propertiesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(attributeDeleteButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(attributeAddButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(attributeMoveDownButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(attributeMoveUpButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(24, 24));
				}
				{
					classNameText = new JTextField();
				}
				{
					classNameLabel = new JLabel();
					classNameLabel.setText(ApplicationResources.getInstance().getString("stdcaption.name"));
				}
				{
					abstractCheck = new JCheckBox();
					abstractCheck.setText(ApplicationResources.getInstance().getString("stdcaption.abstract"));
				}
				{
					cancelButton = new JButton();
					cancelButton.setText(ApplicationResources.getInstance().getString("stdcaption.cancel"));
					cancelButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									cancelButtonActionPerformed(evt);
								}
							});
				}
				{
					okButton = new JButton();
					okButton.setText(ApplicationResources.getInstance().getString("stdcaption.ok"));
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							okButtonActionPerformed(evt);
						}
					});
				}
				{
					clsAnttButton = new JButton();
					clsAnttButton.setToolTipText("Annotations for this element");
					clsAnttButton.setIcon(IconLoader.getInstance().getIcon(IconType.ANNOTATION));
					clsAnttButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							EditAnnotationDialog antDialog = new EditAnnotationDialog(parent, diagramManager, classElement.getClassifier(), true);
							antDialog.setVisible(true);
						}
					});
				}
				layout.setVerticalGroup(layout.createSequentialGroup()
					.addContainerGap(19, 19)
					.addGroup(layout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					        .addComponent(classNameLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					        .addComponent(abstractCheck, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					    .addGroup(GroupLayout.Alignment.LEADING, layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					        .addComponent(classNameText, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addComponent(clsAnttButton, GroupLayout.Alignment.BASELINE, 0, 16, Short.MAX_VALUE)))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(classAttributesPanel, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 16, GroupLayout.PREFERRED_SIZE)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(okButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(17, 17));
				layout.setHorizontalGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
					        .addComponent(classNameLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					        .addGroup(layout.createParallelGroup()
					            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
					                .addComponent(classNameText, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
					                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					                .addComponent(clsAnttButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					                .addGap(9))
					            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
					                .addGap(0, 202, GroupLayout.PREFERRED_SIZE)
					                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
					        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					        .addGroup(layout.createParallelGroup()
					            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
					                .addComponent(abstractCheck, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE))
					            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
					                .addPreferredGap(abstractCheck, okButton, LayoutStyle.ComponentPlacement.INDENT)
					                .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))))
					    .addComponent(classAttributesPanel, GroupLayout.Alignment.LEADING, 0, 430, Short.MAX_VALUE))
					.addContainerGap());

			}
			this.setSize(460, 380);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void myPostInit() {
			
		attributeVisibilityCheck.setSelected(classElement.showAttributes());
						
		modelDataTypes = new HashMap<String, DataType>();
		List<DataType> dataTypes = ModelHelper.getModelDataTypes(diagramManager.getCurrentEditor().getProject());
		for (DataType item : dataTypes) {
			modelDataTypes.put(item.getName(), item);
		}
		
		TableColumn typeColumn = attributesTable.getColumnModel().getColumn(1);	
		typeColumn.setCellEditor(createEditor(modelDataTypes.keySet().toArray()));

		attributesTable.setSurrendersFocusOnKeystroke(true);
        		
		classNameText.setText(classElement.getClassifier().getName());
		
		abstractCheck.setSelected(classElement.getClassifier().isIsAbstract());
		
		if(classElement.getClassifier() instanceof DataTypeImpl)
		{
			DataType dataType = (DataType) classElement.getClassifier();
			
			for (Property attribute : dataType.getAttribute()) {
				attributesTableModel.addEntry(attribute);
			}
		}
		else 
		{
			Class umlclass = (Class) classElement.getClassifier();
			for (Property attribute : umlclass.getAttribute()) {
				attributesTableModel.addEntry(attribute);
			}
		}
		
		getRootPane().setDefaultButton(okButton);
	}
		
	private void transferDataToClass()
	{
				
		if(classNameText.getText().trim().length() > 0)
			classElement.getClassifier().setName(classNameText.getText().trim());
		else
			classElement.getClassifier().setName(null);
		
		classElement.getClassifier().setIsAbstract(abstractCheck.isSelected());
		
		classElement.setShowAttributes(attributeVisibilityCheck.isSelected());
		List<Property> classAttributes = attributesTableModel.getEntries();
		for (Property property : classAttributes) {
			//If the property has name or type set
			if(property.getName().trim().length() > 0 || property.getType().getName().trim().length() > 0)
			{
				DataType existingType = modelDataTypes.get(property.getType().getName().trim());
				if(existingType != null)
				{
					property.setType(existingType);
				}
				if(classElement.getClassifier() instanceof DataTypeImpl)
					((DataType)classElement.getClassifier()).getOwnedAttribute().add(property);
				else
					((Class)classElement.getClassifier()).getOwnedAttribute().add(property);
			}
		}
	}
	
	private void okButtonActionPerformed(ActionEvent evt) {
		transferDataToClass();
		addDataTypesToModel();
		dispose();
	}

	private void addDataTypesToModel() {
		List<Property> classAttributes = attributesTableModel.getEntries();
		for (Property property : classAttributes) {
			//Avoid the creation of duplicate types
			if(modelDataTypes.keySet().contains(property.getType().getName().trim()) == false)
			{
				
				UmlProject project = diagramManager.getCurrentProject();
				
				AddCommand cmd = new AddCommand(project.getEditingDomain(), project.getModel().getPackagedElement(), property.getType());
				project.getEditingDomain().getCommandStack().execute(cmd);
			}
		}	
	}

	protected void attributeAddButtonActionPerformed(ActionEvent evt) {
		attributesTableModel.addEmptyEntry();		
	}
	
	private void cancelButtonActionPerformed(ActionEvent evt) {
		dispose();
	}

	protected void attributeDeleteButtonActionPerformed(ActionEvent evt) {
		int selectedRow = attributesTable.getSelectedRow();
		if (selectedRow >= 0 && selectedRow < attributesTableModel.getRowCount()) {
			attributesTableModel.removeEntryAt(selectedRow);
		}
	}

	private void moveUpAttribute() {
		int row = attributesTable.getSelectedRow();
		if (row >=0  && row < attributesTable.getRowCount()) {
			attributesTableModel.moveUpEntry(row);
			attributesTable.setRowSelectionInterval(row - 1, row - 1);
		}
	}

	private void moveDownAttribute() {
		int row = attributesTable.getSelectedRow();
		if (row >=0  && row < attributesTable.getRowCount()) {
			attributesTableModel.moveDownEntry(row);
			attributesTable.setRowSelectionInterval(row + 1, row + 1);
		}
	}
	
	private TableCellEditor createEditor(Object[] objects) {
        @SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox combo = new JComboBox(objects) {

        	private static final long serialVersionUID = 1L;
			
			@Override
			protected boolean processKeyBinding(KeyStroke ks, KeyEvent e,
					int condition, boolean pressed) {
				
				boolean retValue = super.processKeyBinding(ks, e, condition,pressed);

                if (!retValue && isStartingCellEdit() && editor != null) {
                    // this is where the magic happens
                    // not quite right; sets the value, but doesn't advance the
                    // cursor position for AC
                    editor.setItem(String.valueOf(ks.getKeyChar()));
                }
                return retValue;
			}
			
            private boolean isStartingCellEdit() {
                JTable table = (JTable) SwingUtilities.getAncestorOfClass(JTable.class, this);
                return table != null
                        && table.isFocusOwner()
                        && !Boolean.FALSE.equals((Boolean) table
                                .getClientProperty("JTable.autoStartsEdit"));
            }
        };
        
        //AutoCompleteDecorator.decorate(combo);
        combo.setEditable(true);
        return new DefaultCellEditor(combo);
    }
	
}
