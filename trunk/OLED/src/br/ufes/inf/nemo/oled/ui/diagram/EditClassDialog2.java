package br.ufes.inf.nemo.oled.ui.diagram;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.edit.command.AddCommand;

import RefOntoUML.Class;
import RefOntoUML.Comment;
import RefOntoUML.DataType;
import RefOntoUML.Property;
import RefOntoUML.impl.DataTypeImpl;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.DiagramManager;
import br.ufes.inf.nemo.oled.ui.diagram.commands.ChangeNodeCommand;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;
import br.ufes.inf.nemo.oled.util.IconLoader.IconType;
import br.ufes.inf.nemo.oled.util.ModelHelper;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class EditClassDialog2 extends javax.swing.JDialog {

	private static final long serialVersionUID = -3657091452272785229L;
	private JPanel cntPanel;
	private JTextField nmeText;
	private JCheckBox absCheck;
	private JPanel atrPanel;
	private JTable cnxTable;
	private JScrollPane cnxScroll;
	private JButton remCnxButton;
	private JButton addCnxButton;
	private JTable atrTable;
	private JButton cncButton;
	private JButton okButton;
	private JPanel cnxPanel;
	private JButton addAtrButton;
	private JButton dwnAttButton;
	private JButton upAtrButton;
	private JButton remAtrButton;
	private JScrollPane atrScroll;
	private JCheckBox shwAtrCheck;
	private JEditorPane docText;
	private JPanel docPanel;
	private JLabel nmeLabel;

	private DiagramManager manager;
	private ClassElement element;
	private ClassElement snapshot;
	private Map<String, DataType> dataTypes;
	private AttributeTableModel atrModel;
	private ConstraintxTableModel cnxModel;
	private ChangeRecorder rec;
	
	public EditClassDialog2(JFrame frame, DiagramManager manager, ClassElement element, boolean modal) {
		super(frame, modal);
		
		this.manager = manager;
		this.element = element;
		snapshot = (ClassElement) element.clone();
		
		initGUI();
		loadData();
	}
	
	private void initGUI() {
		try {
			{
				
				setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
				setTitle(ApplicationResources.getInstance().getString("dialog.classproperties.title"));
				
				setResizable(false);
				setName("edtClsDialog");
				setPreferredSize(new java.awt.Dimension(455, 605));
			}
			{
				cntPanel = new JPanel();
				FormLayout contentPanelLayout = new FormLayout(
						"max(p;10px), max(p;100px), max(p;10px), max(p;100px), max(p;10px), max(p;100px), max(p;10px), max(p;100px), 121dlu", 
						"10px, max(p;25px), max(p;10px), max(p;100px), max(p;10px), max(p;200px), max(p;10px), max(p;165px), max(p;10px), max(p;30px)");
				cntPanel.setLayout(contentPanelLayout);
				getContentPane().add(cntPanel, BorderLayout.CENTER);
				{
					nmeLabel = new JLabel();
					cntPanel.add(nmeLabel, new CellConstraints("2, 2, 1, 1, fill, fill"));
					nmeLabel.setText("Name");
					nmeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					nmeText = new JTextField();
					cntPanel.add(nmeText, new CellConstraints("4, 2, 3, 1, fill, fill"));
					nmeText.setText("Name");
				}
				{
					docPanel = new JPanel();
					BorderLayout docPanelLayout = new BorderLayout();
					docPanel.setLayout(docPanelLayout);
					cntPanel.add(docPanel, new CellConstraints("2, 4, 7, 1, fill, fill"));
					docPanel.setBorder(BorderFactory.createTitledBorder("Documentation"));
					{
						docText = new JEditorPane();
						docPanel.add(docText, BorderLayout.CENTER);
						docText.setBorder(BorderFactory.createCompoundBorder(
								new LineBorder(new java.awt.Color(128,128,128), 1, true), 
								BorderFactory.createEmptyBorder(2, 2, 2, 1)));
					}
				}
				{
					absCheck = new JCheckBox();
					cntPanel.add(absCheck, new CellConstraints("8, 2, 1, 1, fill, fill"));
					absCheck.setText("abstract");
				}
				{
					atrPanel = new JPanel();
					FormLayout atrPanelLayout = new FormLayout(
							"max(p;10px), max(p;45px), max(p;10px), max(p;45px), 185px, max(p;45px), max(p;10px), max(p;45px), max(p;10px)", 
							"max(p;25px), max(p;5px), max(p;100px), max(p;10px), max(p;25px)");
					atrPanel.setLayout(atrPanelLayout);
					cntPanel.add(atrPanel, new CellConstraints("2, 6, 7, 1, fill, fill"));
					atrPanel.setBorder(BorderFactory.createTitledBorder(ApplicationResources.getInstance().getString("dialog.class.attributes")));
					{
						shwAtrCheck = new JCheckBox();
						atrPanel.add(shwAtrCheck, new CellConstraints("2, 1, 4, 1, fill, fill"));
						shwAtrCheck.setText(ApplicationResources.getInstance().getString("dialog.class.showattributes"));
					}
					{
						atrScroll = new JScrollPane();
						atrPanel.add(atrScroll, new CellConstraints("2, 3, 7, 1, fill, fill"));
						atrScroll.setPreferredSize(new java.awt.Dimension(400, 100));
						atrScroll.setMaximumSize(new java.awt.Dimension(400, 100));
						{
							atrModel = new AttributeTableModel(element.getClassifier());
							atrTable = new JTable(atrModel);
							atrTable.setRowHeight(23);
							
							atrTable.setSurrendersFocusOnKeystroke(true);
							atrTable.getColumnModel().getColumn(0).setMaxWidth(200);
							atrTable.getColumnModel().getColumn(0).setPreferredWidth(200);
							atrTable.getColumnModel().getColumn(0).setResizable(false);
							
							//When the focus is lost on cell editor, we want to commit the changes to the model
							atrTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
							
							atrScroll.setViewportView(atrTable);
						}
					}
					{
						addAtrButton = new JButton();
						atrPanel.add(addAtrButton, new CellConstraints("2, 5, 1, 1, fill, fill"));
						addAtrButton.setIcon(IconLoader.getInstance().getIcon(IconType.ADD));
						addAtrButton.setToolTipText(ApplicationResources.getInstance().getString("stdcaption.add"));
						addAtrButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								addItem(atrTable);	
							}
						});
					}
					{
						remAtrButton = new JButton();
						atrPanel.add(remAtrButton, new CellConstraints("4, 5, 1, 1, fill, fill"));
						remAtrButton.setIcon(IconLoader.getInstance().getIcon(IconType.REMOVE));
						remAtrButton.setToolTipText(ApplicationResources.getInstance().getString("stdcaption.remove"));
						remAtrButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								removeItem(atrTable);
							}
						});
					}
					{
						upAtrButton = new JButton();
						atrPanel.add(upAtrButton, new CellConstraints("6, 5, 1, 1, fill, fill"));
						upAtrButton.setIcon(IconLoader.getInstance().getIcon(IconType.ARROW_UP));
						upAtrButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								moveUpSelected(atrTable);
							}
						});
					}
					{
						dwnAttButton = new JButton();
						atrPanel.add(dwnAttButton, new CellConstraints("8, 5, 1, 1, fill, fill"));
						dwnAttButton.setIcon(IconLoader.getInstance().getIcon(IconType.ARROW_DOWN));
						dwnAttButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								moveDownSelected(atrTable);
							}
						});
					}
				}
				{
					cnxPanel = new JPanel();
					FormLayout cnxPanelLayout = new FormLayout(
							"max(p;10px), 45px, max(p;10px), max(p;45px), 185px, max(p;45px), max(p;10px), max(p;45px), max(p;10px)", 
							"max(p;95px), max(p;10px), max(p;25px)");
					cnxPanel.setLayout(cnxPanelLayout);
					cntPanel.add(cnxPanel, new CellConstraints("2, 8, 7, 1, fill, fill"));
					cnxPanel.setBorder(BorderFactory.createTitledBorder("Constraints"));
					{
						addCnxButton = new JButton();
						cnxPanel.add(addCnxButton, new CellConstraints("2, 3, 1, 1, fill, fill"));
						addCnxButton.setIcon(IconLoader.getInstance().getIcon(IconType.ADD));
						addCnxButton.setToolTipText(ApplicationResources.getInstance().getString("stdcaption.add"));
						addCnxButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								addItem(cnxTable);
							}
						});
					}
					{
						remCnxButton = new JButton();
						cnxPanel.add(remCnxButton, new CellConstraints("4, 3, 1, 1, fill, fill"));
						remCnxButton.setIcon(IconLoader.getInstance().getIcon(IconType.REMOVE));
						remCnxButton.setToolTipText(ApplicationResources.getInstance().getString("stdcaption.delete"));
						remCnxButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								removeItem(cnxTable);
							}
						});
					}
					{
						cnxScroll = new JScrollPane();
						cnxPanel.add(cnxScroll, new CellConstraints("2, 1, 7, 1, fill, fill"));
						cnxScroll.setMaximumSize(new java.awt.Dimension(399, 80));
						cnxScroll.setMinimumSize(new java.awt.Dimension(399, 80));
						cnxScroll.setPreferredSize(new java.awt.Dimension(399, 80));
						{
							cnxModel = new ConstraintxTableModel(element.getClassifier(), manager.getCurrentProject().getModel());
							cnxTable = new JTable(cnxModel);
							cnxTable.setRowHeight(23);
							
							cnxTable.setSurrendersFocusOnKeystroke(true);
							cnxTable.getColumnModel().getColumn(0).setMaxWidth(80);
							cnxTable.getColumnModel().getColumn(0).setPreferredWidth(80);
							cnxTable.getColumnModel().getColumn(0).setResizable(false);
							cnxTable.getColumnModel().getColumn(2).setMaxWidth(24);
							cnxTable.getColumnModel().getColumn(2).setPreferredWidth(24);
							cnxTable.getColumnModel().getColumn(2).setResizable(false);
							
							cnxTable.setDefaultRenderer(String.class, new StringRenderer(true));
							cnxTable.setDefaultRenderer(Boolean.class, new FlagRenderer(true));
							
							//When the focus is lost on cell editor, we want to commit the changes to the model
							cnxTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
														
							cnxScroll.setViewportView(cnxTable);
						}
					}
				}
				{
					okButton = new JButton();
					cntPanel.add(okButton, new CellConstraints("8, 10, 1, 1, fill, fill"));
					okButton.setText("okButton");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							transferData();
							addDataTypesToModel();
							dispose();
						}
					});
				}
				{
					cncButton = new JButton();
					cntPanel.add(cncButton, new CellConstraints("6, 10, 1, 1, fill, fill"));
					cncButton.setText("cncButton");
					cncButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							dispose();
						}
					});
				}
			}
			
			getRootPane().setDefaultButton(okButton);
			setSize(455, 605);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadData() {
		
		//Load general data
		nmeText.setText(element.getClassifier().getName());
		absCheck.setSelected(element.getClassifier().isIsAbstract());
		shwAtrCheck.setSelected(element.showAttributes());
				
		if(element.getClassifier().getOwnedComment().size() > 0)
		{
			Comment cmt = element.getClassifier().getOwnedComment().get(0);
			docText.setText(cmt.getBody());
		}
		
		//Load attribute data
		dataTypes = new HashMap<String, DataType>();
		List<DataType> mdlDataTypes = ModelHelper.getModelDataTypes(manager.getCurrentEditor().getProject());
		for (DataType item : mdlDataTypes) {
			dataTypes.put(item.getName(), item);
		}
		
		TableColumn dtyCol = atrTable.getColumnModel().getColumn(1);	
		dtyCol.setCellEditor(createComboEditor(dataTypes.keySet().toArray()));
						
		if(element.getClassifier() instanceof DataTypeImpl)
		{
			DataType dataType = (DataType) element.getClassifier();
			for (Property prp : dataType.getAttribute()) {
				atrModel.addEntry(prp);
			}
		}
		else 
		{
			Class cls = (Class) element.getClassifier();
			for (Property prp : cls.getAttribute()) {
				atrModel.addEntry(prp);
			}
		}
		
		//Load constraints data
		TableColumn cnxCol = cnxTable.getColumnModel().getColumn(0);	
		cnxCol.setCellEditor(createComboEditor(cnxModel.getConstrainxTypes()));

		//Start recording right the way because there are changes
		//that need to be recorded (ex in the table models)
		rec = ModelHelper.getChangeRecorder();
	}
		
	private void transferData()
	{		
		//If there are any changes, transfer them
		//The attributes and constraints are already transferred in the table models
		//for the sake of simplicity 
		
		String nmeNew = nmeText.getText().trim();
		String nmeOld = element.getClassifier().getName();
		
		if(!nmeNew.equals(nmeOld))
		{
			if(nmeNew.length() > 0)
				element.getClassifier().setName(nmeNew);
			else
				element.getClassifier().setName(null);
		}
		
		Comment cmt = null;
		String doc = (String) docText.getText().trim();
		
		if(doc.length() > 0)
		{
			if(element.getClassifier().getOwnedComment().size() > 0)
			{
				cmt = element.getClassifier().getOwnedComment().get(0);
				if(!cmt.getBody().equals(doc))
					cmt.setBody(doc);
			}
			else
			{
				cmt = ModelHelper.getFactory().createComment();
				cmt.setBody(doc);
				element.getClassifier().getOwnedComment().add(cmt);
			}
		}
		else
		{
			if(element.getClassifier().getOwnedComment().size() > 0)
			{
				element.getClassifier().getOwnedComment().remove(0);
			}
		}
					
		if(element.getClassifier().isIsAbstract() != absCheck.isSelected())
			element.getClassifier().setIsAbstract(absCheck.isSelected());
		
		
		if(element.showAttributes() != shwAtrCheck.isSelected())
			element.setShowAttributes(shwAtrCheck.isSelected());
		
		//End the change recording in the model elements
		ChangeDescription desc = rec.endRecording();
		
		//If there is any change in the model elements or in the diagram (graphical) element, fire the undoable change command
		if(desc.getObjectChanges().size() > 0 || !element.compareTo(snapshot))
		{
			ChangeNodeCommand cmd = new ChangeNodeCommand(manager.getCurrentEditor(), element, snapshot, manager.getCurrentProject(), desc);
			manager.getCurrentEditor().execute(cmd);
		}
	}

	protected void addItem(JTable table) {
		((BaseTableModel) table.getModel()).addEmptyEntry();
	}
	
	protected void removeItem(JTable table) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0 && selectedRow < ((BaseTableModel) table.getModel()).getRowCount()) {
			((BaseTableModel) table.getModel()).removeEntryAt(selectedRow);
		}
		
	}
	
	protected void moveUpSelected(JTable table) {
		int row = table.getSelectedRow();
		if (row > 0 && row < table.getRowCount()) {
			((BaseTableModel) table.getModel()).moveUpEntry(row);
			table.setRowSelectionInterval(row - 1, row - 1);
		}
	}
	
	protected void moveDownSelected(JTable table) {
		int row = table.getSelectedRow();
		if (row > 0 && row < ((BaseTableModel) table.getModel()).getRowCount()) {
			((BaseTableModel) table.getModel()).moveUpEntry(row);
			table.setRowSelectionInterval(row + 1, row + 1);
		}
	}
	
	private void addDataTypesToModel() {
		for (Property property : atrModel.getEntries()) {
			//Avoid the creation of duplicate types
			if(dataTypes.keySet().contains(property.getType().getName().trim()) == false)
			{
				UmlProject project = manager.getCurrentProject();
				AddCommand cmd = new AddCommand(project.getEditingDomain(), project.getModel().getPackagedElement(), property.getType());
				project.getEditingDomain().getCommandStack().execute(cmd);
			}
		}	
	}
	
	private TableCellEditor createComboEditor(Object[] objects) {
        
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

	class FlagRenderer extends JLabel implements TableCellRenderer {

		private static final long serialVersionUID = 1L;
		Border unselectedBorder = null;
		Border selectedBorder = null;
		boolean isBordered = true;

		public FlagRenderer(boolean isBordered) {
			this.isBordered = isBordered;
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			
			if (isBordered) {
				if (isSelected) {
					if (selectedBorder == null) {
						selectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5, table.getSelectionBackground());
					}
					setBorder(selectedBorder);
				} else {
					if (unselectedBorder == null) {
						unselectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5, table.getBackground());
					}
					setBorder(unselectedBorder);
				}
			}
			
			//Make the cell the same color as its row
			if(isSelected)  
				setBackground(table.getSelectionBackground());  
	        else  
	        	setBackground(table.getBackground());    
			
			Icon icon = null;
			if(value == null)
			{
				icon = IconLoader.getInstance().getIcon(IconType.GREY_LIGHT);
			}
			else if(value instanceof Boolean)
			{
				Boolean flag = (Boolean) value;
				if(flag)
					icon = IconLoader.getInstance().getIcon(IconType.GREEN_LIGHT);
				else
					icon = IconLoader.getInstance().getIcon(IconType.RED_LIGHT);
			}
			
			setIcon(icon);
			
			return this;
		}		
	}

	class StringRenderer extends JLabel implements TableCellRenderer
	{
		private static final long serialVersionUID = 1L;
		Border unselectedBorder = null;
		Border selectedBorder = null;
		boolean isBordered = true;

		public StringRenderer(boolean isBordered) {
			this.isBordered = isBordered;
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object text, boolean isSelected, boolean hasFocus, int row, int column) {
			
			if (isBordered) {
				if (isSelected) {
					if (selectedBorder == null) {
						selectedBorder = BorderFactory.createMatteBorder(2, 5,
								2, 5, table.getSelectionBackground());
					}
					setBorder(selectedBorder);
				} else {
					if (unselectedBorder == null) {
						unselectedBorder = BorderFactory.createMatteBorder(2,
								5, 2, 5, table.getBackground());
					}
					setBorder(unselectedBorder);
				}
			}
			
			//Make the cell the same color as its row
			if(isSelected)  
				setBackground(table.getSelectionBackground());  
	        else  
	        	setBackground(table.getBackground());    
			
			setText((String) text);
			
			return this;
		}
	}
	
}
