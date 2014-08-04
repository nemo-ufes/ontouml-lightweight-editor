package br.ufes.inf.nemo.oled.dialog.properties;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Enumeration;
import RefOntoUML.EnumerationLiteral;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.palette.ColorPalette;
import br.ufes.inf.nemo.oled.palette.ColorPalette.ThemeColor;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

public class EnumLiteralEditionPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private ClassElement classElement;	
	private Classifier element;
	private DiagramManager diagramManager;
	@SuppressWarnings("unused")
	private Component parent;
		 
	private JButton btnDelete;
	private JButton btnCreate;
	private JButton btnUp;
	private JButton btnDown;
	private JScrollPane scrollpane;
	private JTable table;
	private EnumLiteralTableModel enumLiteralTableModel;
	private JPanel panel;
	private JCheckBox cbxVisible;
			
	public EnumLiteralEditionPanel(final Component parent, final DiagramManager diagramManager, final ClassElement classElement, final Classifier element) 
	{
		this.diagramManager = diagramManager;
		this.classElement = classElement;
		this.element = element;
		this.parent=parent;
						
		enumLiteralTableModel = new EnumLiteralTableModel(element);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(""));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
		);
		
		scrollpane = new JScrollPane();		
		scrollpane.setMinimumSize(new Dimension(0, 0));
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		table = new JTable();		
		scrollpane.setViewportView(table);
		table.setModel(enumLiteralTableModel);
		
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.setFillsViewportHeight(true);
		table.setGridColor(Color.LIGHT_GRAY);		
		table.setSelectionBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_MEDIUM));
		table.setSelectionForeground(Color.BLACK);
		table.setFocusable(false);	    
		table.setRowHeight(23);
		
		btnCreate = new JButton("");
		btnCreate.setFocusable(false);
		btnCreate.setToolTipText("Add new value to this enumeration");
		btnCreate.setIcon(new ImageIcon(AttributesEditionPanel.class.getResource("/resources/icons/x16/new.png")));
		btnCreate.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addLiteral(arg0);
			}
		});
		
		btnDelete = new JButton("");
		btnDelete.setFocusable(false);
		btnDelete.setToolTipText("Delete selected values");
		btnDelete.setIcon(new ImageIcon(AttributesEditionPanel.class.getResource("/resources/icons/x16/cross.png")));
		btnDelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteLiteral(arg0);
			}
		});
		
		btnUp = new JButton("");
		btnUp.setFocusable(false);
		btnUp.setToolTipText("Move up selected value");
		btnUp.setIcon(new ImageIcon(AttributesEditionPanel.class.getResource("/resources/icons/x16/arrow_up.png")));
		btnUp.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				moveUpLiteral();
			}
		});
		
		btnDown = new JButton("");
		btnDown.setFocusable(false);
		btnDown.setToolTipText("Move down selected value");
		btnDown.setIcon(new ImageIcon(AttributesEditionPanel.class.getResource("/resources/icons/x16/arrow_down.png")));
		btnDown.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				moveDownLiteral();
			}
		});
		
		cbxVisible = new JCheckBox("Turn literals visible");
		cbxVisible.setPreferredSize(new Dimension(140, 20));
		cbxVisible.setHorizontalAlignment(SwingConstants.LEFT);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollpane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxVisible, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxVisible, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollpane, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
					.addGap(10))
		);
		panel.setLayout(gl_panel);
		this.setLayout(groupLayout);
		
		setSize(450,221);
		
		if (classElement !=null) { cbxVisible.setSelected(classElement.showAttributes());cbxVisible.setEnabled(true); }
		else { cbxVisible.setSelected(false); cbxVisible.setEnabled(false); }
		
		myPostInit();
	}	

	public void refreshData()
	{
		enumLiteralTableModel.fireTableDataChanged();
	}
	
	private void myPostInit() 
	{				
		if(element instanceof Enumeration)
		{
			Enumeration enumeration = (Enumeration)element;			
			for (EnumerationLiteral literal : enumeration.getOwnedLiteral()) {
				enumLiteralTableModel.addEntry(literal);
			}
		}
	}
	
	private void moveUpLiteral() 
	{
		int row = table.getSelectedRow();
		if (row >=0  && row < table.getRowCount()) 
		{
			enumLiteralTableModel.moveUpEntry(row);
			table.setRowSelectionInterval(row - 1, row - 1);
		}
	}

	private void moveDownLiteral() 
	{
		int row = table.getSelectedRow();
		if (row >=0  && row < table.getRowCount()) 
		{
			enumLiteralTableModel.moveDownEntry(row);
			table.setRowSelectionInterval(row + 1, row + 1);
		}
	}
	
	protected void deleteLiteral(ActionEvent evt) 
	{
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0 && selectedRow < enumLiteralTableModel.getRowCount()) 
		{
			enumLiteralTableModel.removeEntryAt(selectedRow);
		}
	}
	
	protected void addLiteral(ActionEvent evt) 
	{
		enumLiteralTableModel.addEmptyEntry();		
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	public void transferLiteralData()
	{
		List<EnumerationLiteral> enumLiterals = enumLiteralTableModel.getEntries();
		
		if(cbxVisible.isSelected()==false){
			if (enumLiterals.size()>0) {
				cbxVisible.setSelected(true);
			}
		}
		if (classElement !=null) classElement.setShowAttributes(cbxVisible.isSelected());
		diagramManager.updateOLEDFromInclusion(element);
		
		deleteLiterals(enumLiterals);
		transferAddedLiterals(enumLiterals);
		
		classElement.reinitAttributesCompartment();
		classElement.invalidate();
	}
	
	private void deleteLiterals(List<EnumerationLiteral> enumLiterals )
	{
		ArrayList<EnumerationLiteral> literals = new ArrayList<EnumerationLiteral>();
		
		if(element instanceof Enumeration){
			literals.addAll(((Enumeration)element).getOwnedLiteral());
			for(EnumerationLiteral p: literals){
				if(!enumLiterals.contains(p)) {					
					((Enumeration)element).getOwnedLiteral().remove(p);
					diagramManager.updateOLEDFromDeletion(p);
				}
			}
		}		
	}
	
	private void transferAddedLiterals(List<EnumerationLiteral> enumLiterals )
	{
		for (EnumerationLiteral literal : enumLiterals) 
		{			
			if(!literal.getName().isEmpty())
			{				
				if(element instanceof Enumeration){
					((Enumeration)element).getOwnedLiteral().add(literal);
					
					diagramManager.updateOLEDFromInclusion(literal);
				}				
			}
		}
	}	
}

