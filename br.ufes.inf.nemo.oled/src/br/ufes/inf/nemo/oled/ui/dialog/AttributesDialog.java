package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Property;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.util.ColorPalette;
import br.ufes.inf.nemo.oled.util.ColorPalette.ThemeColor;
import br.ufes.inf.nemo.oled.util.ModelHelper;

public class AttributesDialog extends JDialog {

private static final long serialVersionUID = 1L;
	
	private ClassElement classElement;	
	private Classifier element;
	private DiagramManager diagramManager;
	@SuppressWarnings("unused")
	private JFrame parent;
		
	private Map<String, DataType> modelDataTypes; 
	
	private JButton btnConfirm;
	private JButton btnCancel;	
	private JButton btnDelete;
	private JButton btnCreate;
	private JButton btnUp;
	private JButton btnDown;
	private JScrollPane scrollpane;
	private JTable table;
	private AttributeTableModel attributesTableModel;
		
	@SuppressWarnings({ })
	public AttributesDialog(JFrame parent, DiagramManager diagramManager, ClassElement classElement, boolean modal) 
	{
		super(parent, modal);
		
		this.diagramManager = diagramManager;
		this.classElement = classElement;
		this.element = classElement.getClassifier();
		this.parent = parent;
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Attributes"+" - "+getStereotype(classElement.getClassifier())+" "+classElement.getClassifier().getName());
				
		attributesTableModel = new AttributeTableModel(element);
				
		scrollpane = new JScrollPane();		
        scrollpane.setMinimumSize(new Dimension(0, 0));
	    setMinimumSize(new Dimension(0, 0));
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		table = new JTable();		
		scrollpane.setViewportView(table);
		table.setModel(attributesTableModel);

		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.setFillsViewportHeight(true);
		table.setGridColor(Color.LIGHT_GRAY);		
	    table.setSelectionBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_MEDIUM));
	    table.setSelectionForeground(Color.BLACK);
	    table.setFocusable(false);	    
	    table.setRowHeight(23);
	    
	    TableColumn column = null;
	    for (int i = 0; i < 5; i++) {
	        column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(100);
	    }
	    
		btnConfirm = new JButton("Ok");
		btnConfirm.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
						
			}
		});
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteAttribute(arg0);
			}
		});
		
		btnCreate = new JButton("Add");
		btnCreate.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addAttribute(arg0);
			}
		});
		
		btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				moveUpAttribute();
			}
		});
		
		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				moveDownAttribute();
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDelete)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollpane, GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
								.addComponent(separator_1)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(254)
							.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreate)
						.addComponent(btnDelete)
						.addComponent(btnDown)
						.addComponent(btnUp))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnConfirm))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		setSize(677,320);
		
		myPostInit();
	}	

	private void myPostInit() 
	{						
		modelDataTypes = new HashMap<String, DataType>();
		List<DataType> dataTypes = ModelHelper.getModelDataTypes(diagramManager.getCurrentEditor().getProject());
		for (DataType item : dataTypes) {			
			modelDataTypes.put(item.getName(), item);
		}
		
		TableColumn typeColumn = table.getColumnModel().getColumn(1);	
		typeColumn.setCellEditor(createEditor(modelDataTypes.keySet().toArray()));

		table.setSurrendersFocusOnKeystroke(true);
		
		if(classElement.getClassifier() instanceof DataType)
		{
			DataType dataType = (DataType) classElement.getClassifier();			
			for (Property attribute : dataType.getAttribute()) {
				attributesTableModel.addEntry(attribute);
			}
		} else {
			Class umlclass = (Class) classElement.getClassifier();
			for (Property attribute : umlclass.getAttribute()) {
				attributesTableModel.addEntry(attribute);
			}			
		}
	}
	
	private TableCellEditor createEditor(Object[] objects) 
	{
        @SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox combo = new JComboBox(objects) {
        	private static final long serialVersionUID = 1L;			
			@Override
			protected boolean processKeyBinding(KeyStroke ks, KeyEvent e, int condition, boolean pressed) 
			{
				boolean retValue = super.processKeyBinding(ks, e, condition,pressed);
                if (!retValue && isStartingCellEdit() && editor != null) {
                    // this is where the magic happens
                    // not quite right; sets the value, but doesn't advance the
                    // cursor position for AC
                    editor.setItem(String.valueOf(ks.getKeyChar()));
                }
                return retValue;
			}			
            private boolean isStartingCellEdit() 
            {
                JTable table = (JTable) SwingUtilities.getAncestorOfClass(JTable.class, this);
                return table != null && table.isFocusOwner() && !Boolean.FALSE.equals((Boolean)table.getClientProperty("JTable.autoStartsEdit"));
            }
        };        
        //AutoCompleteDecorator.decorate(combo);
        combo.setEditable(true);
        return new DefaultCellEditor(combo);
    }
	
	private void moveUpAttribute() 
	{
		int row = table.getSelectedRow();
		if (row >=0  && row < table.getRowCount()) 
		{
			attributesTableModel.moveUpEntry(row);
			table.setRowSelectionInterval(row - 1, row - 1);
		}
	}

	private void moveDownAttribute() 
	{
		int row = table.getSelectedRow();
		if (row >=0  && row < table.getRowCount()) 
		{
			attributesTableModel.moveDownEntry(row);
			table.setRowSelectionInterval(row + 1, row + 1);
		}
	}
	
	protected void deleteAttribute(ActionEvent evt) 
	{
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0 && selectedRow < attributesTableModel.getRowCount()) 
		{
			attributesTableModel.removeEntryAt(selectedRow);
		}
	}
	
	protected void addAttribute(ActionEvent evt) 
	{
		attributesTableModel.addEmptyEntry();		
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
}
