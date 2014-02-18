package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import RefOntoUML.Classifier;
import RefOntoUML.Constraintx;
import RefOntoUML.StringExpression;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.ProjectBrowser;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;


public class ConstraintEditionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private ClassElement classElement;
	private Classifier element;
	private DiagramManager diagramManager;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboConstraintType;
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnParse;
	@SuppressWarnings("rawtypes")
	private JComboBox comboConstraint;
	private JScrollPane scrollPaneText;
	private JTextArea constraintTextArea;
	
	private ArrayList<Constraintx> constraintList = new ArrayList<Constraintx>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ConstraintEditionPanel(DiagramManager diagramManager, ClassElement classElement, boolean modal) 
	{
		this.diagramManager = diagramManager;
		this.classElement = classElement;
		this.element = classElement.getClassifier();
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(""));
		
		comboConstraint = new JComboBox();
		comboConstraint.setToolTipText("This is the name of your constraint");
		comboConstraint.setPreferredSize(new Dimension(350, 20));
		comboConstraint.setFocusable(false);
		comboConstraint.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				ConstraintElement ce = (ConstraintElement) comboConstraint.getSelectedItem();
				if(ce!=null) constraintTextArea.setText(((StringExpression)ce.getConstraint().getSpecification()).getSymbol());
			}
		});
		
		
		btnAdd = new JButton("");
		btnAdd.setFocusable(false);
		btnAdd.setIcon(new ImageIcon(ConstraintEditionPanel.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/cross.png")));
		btnAdd.setToolTipText("Add a new constraint to this class");
		btnAdd.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createConstraintActionPerformed();				
			}
		});
		
		btnSave = new JButton("");
		btnSave.setFocusable(false);
		btnSave.setIcon(new ImageIcon(ConstraintEditionPanel.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/save.png")));
		btnSave.setToolTipText("Save selected constraint");
		btnSave.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveConstraintActionPerformed();				
			}
		});
		
		btnDelete = new JButton("");
		btnDelete.setFocusable(false);
		btnDelete.setIcon(new ImageIcon(ConstraintEditionPanel.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/delete.png")));
		btnDelete.setToolTipText("Delete seletected constraint");
		btnDelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteConstraintActionPerformed();				
			}
		});
		
		constraintTextArea = new JTextArea();	
		constraintTextArea.setToolTipText("Click here to start writing constraints");
		
		scrollPaneText = new JScrollPane();
		scrollPaneText.setToolTipText("Click here to start writing constraints");
		scrollPaneText.setViewportView(constraintTextArea);
		
		comboConstraintType = new JComboBox();
		comboConstraintType.setToolTipText("The type of constraint to be created");
		comboConstraintType.setModel(new DefaultComboBoxModel(new String[] {"invariant", "derivation"}));
//		((JLabel)comboConstraintType.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		
		JLabel lblNewLabel = new JLabel("Selected constraint:");
		
		btnParse = new JButton("");
		btnParse.setFocusable(false);
		btnParse.setEnabled(false);
		btnParse.setToolTipText("Parse selected constraint");
		btnParse.setIcon(new ImageIcon(ConstraintEditionPanel.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/validate.png")));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneText, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboConstraint, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboConstraintType, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnParse, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addGap(10))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboConstraint, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneText, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnParse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(comboConstraintType, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(127, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(152, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(139, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		setInitialData();
	}
	
	/** Private Class: Constraint Element */
	private class ConstraintElement 
	{
		RefOntoUML.Constraintx c;
		public ConstraintElement(RefOntoUML.Constraintx c){
			this.c = c;
		}
		@Override
		public String toString(){
			String result = new String();
			result = c.getName();			
			return result;
		}
		public RefOntoUML.Constraintx getConstraint(){
			return c;
		}
	}
		
	@SuppressWarnings("unchecked")
	public void setInitialData()
	{		
		OntoUMLParser refparser = ProjectBrowser.getParserFor(diagramManager.getCurrentProject());
		for(Constraintx c: refparser.getAllInstances(RefOntoUML.Constraintx.class)){		
			for(RefOntoUML.Element elem: c.getConstrainedElement()) {
				if (elem.equals(element)) comboConstraint.addItem(new ConstraintElement(c));
			}
			constraintList.add(c);
		}		
		if (comboConstraint.getItemCount()>0) {
			comboConstraint.setSelectedIndex(0);
			Constraintx c= ((ConstraintElement)comboConstraint.getSelectedItem()).getConstraint();
			constraintTextArea.setText(((StringExpression)c.getSpecification()).getSymbol()+"\n\n");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void createConstraintActionPerformed()
	{
		Constraintx c = diagramManager.getElementFactory().createConstraintx();		
		c.getConstrainedElement().add(element);
		String selectedItem = ((String)comboConstraintType.getSelectedItem());
		if(selectedItem.compareToIgnoreCase("invariant")==0) c.setName("Invariant");
		else c.setName("Derivation");
		ConstraintElement ce = new ConstraintElement(c);		
		comboConstraint.addItem(ce);				
		comboConstraint.setSelectedIndex(comboConstraint.getItemCount()-1);		
		if(selectedItem.compareToIgnoreCase("invariant")==0){
			constraintTextArea.setText("context <Type> \ninv: true");
			((StringExpression)c.getSpecification()).setSymbol("context <Type> \ninv: true");
		}else{
			constraintTextArea.setText("context <Type>::<Property>::<propertyType> \nderive: true");
			((StringExpression)c.getSpecification()).setSymbol("context <Type>::<Property>::<propertyType> \nderive: true");
		}
	}

	public void saveConstraintActionPerformed()
	{
		if((ConstraintElement)comboConstraint.getSelectedItem()!=null){
			Constraintx c = ((ConstraintElement)comboConstraint.getSelectedItem()).getConstraint();
			((StringExpression)c.getSpecification()).setSymbol(constraintTextArea.getText());
			comboConstraint.repaint();
			comboConstraint.validate();
		}		
	}

	public void deleteConstraintActionPerformed()
	{
		if((ConstraintElement)comboConstraint.getSelectedItem()!=null){
			comboConstraint.removeItem(comboConstraint.getSelectedItem());
			comboConstraint.invalidate();	
			constraintTextArea.setText("");
		}			
	}
	
	public ArrayList<Constraintx> getConstraints()
	{
		ArrayList<Constraintx> result = new ArrayList<Constraintx>();
		for(int i=0; i<comboConstraint.getItemCount();i++){
			ConstraintElement ce = (ConstraintElement)comboConstraint.getItemAt(i);
			if (ce!=null) result.add(ce.getConstraint());
		}
		return result;
	}
	
	public ArrayList<Constraintx> getConstraintx(RefOntoUML.Element element)
	{
		ArrayList<Constraintx> result = new ArrayList<Constraintx>();
		for(Constraintx c: constraintList){
			for(RefOntoUML.Element elem: c.getConstrainedElement()) {
				if (elem.equals(element)) result.add(c);
			}							
		}
		return result;
	}
	
	public void transferConstraintsData()
	{
		// added
		ArrayList<Constraintx> toBeAdded = new ArrayList<Constraintx>();
		for(Constraintx c: getConstraints()){			
			if (!getConstraintx(element).contains(c)){				
				toBeAdded.add(c);
			}
		}
		for(Constraintx cmt: toBeAdded) { diagramManager.addConstraintx(cmt, (RefOntoUML.Element)element.eContainer()); } 
			
		//removed
		ArrayList<Constraintx> toBeDeleted = new ArrayList<Constraintx>();
		for(Constraintx c: getConstraintx(element)){
			if (!getConstraints().contains(c)){
				toBeDeleted.add(c);
			}
		}
		for(Constraintx cmt: toBeDeleted) { diagramManager.delete(cmt); }	
		
	}
}
