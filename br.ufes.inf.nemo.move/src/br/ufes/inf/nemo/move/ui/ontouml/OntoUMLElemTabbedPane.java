package br.ufes.inf.nemo.move.ui.ontouml;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.emf.ecore.EObject;

public class OntoUMLElemTabbedPane extends JTabbedPane implements TreeSelectionListener{

	private static final long serialVersionUID = 1L;
	
	private JPanel classPanel;
	private JTextField nameField;
	private JTextField aliasField;
	@SuppressWarnings("rawtypes")
	private JComboBox abstractCombo;

	public void setData (EObject obj, String alias)
	{		
		
		if (obj instanceof RefOntoUML.Classifier)
		{
			RefOntoUML.Classifier c = (RefOntoUML.Classifier)obj;
			nameField.setText(c.getName());
			aliasField.setText(alias);
			if (c.isIsAbstract()) abstractCombo.setSelectedIndex(1); else abstractCombo.setSelectedIndex(0);	
		}
		
		repaint();
		validate();
	}
	
	@SuppressWarnings({ })
	public OntoUMLElemTabbedPane ()
	{
		createClassPanel();
		add(classPanel);
		setTitleAt(0, "Class");
	}
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createClassPanel()
	{		
		classPanel = new JPanel();
		
		JLabel lblName = new JLabel("Name");		
		JLabel lblAlias = new JLabel("Alias");		
		JLabel lblAbstract = new JLabel("Abstract");
		
		nameField = new JTextField();
		nameField.setBackground(Color.WHITE);
		nameField.setEditable(false);
		nameField.setColumns(10);
		
		aliasField = new JTextField();
		aliasField.setBackground(Color.WHITE);
		aliasField.setEditable(false);
		aliasField.setColumns(10);
		
		abstractCombo = new JComboBox();
		abstractCombo.setModel(new DefaultComboBoxModel(new String[] {"false", "true"}));
		
		GroupLayout gl_classPanel = new GroupLayout(classPanel);
		gl_classPanel.setHorizontalGroup(
			gl_classPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_classPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_classPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblAlias, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
						.addComponent(lblAbstract, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
						.addComponent(lblName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_classPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(aliasField)
						.addComponent(abstractCombo, 0, 217, Short.MAX_VALUE)
						.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
					.addGap(20))
		);
		gl_classPanel.setVerticalGroup(
			gl_classPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_classPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_classPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_classPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(aliasField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAlias, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_classPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(abstractCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAbstract, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(178, Short.MAX_VALUE))
		);
		classPanel.setLayout(gl_classPanel);		
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) 
	{
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
		OntoUMLTreeNodeElem chckNode = (OntoUMLTreeNodeElem) node.getUserObject();
		
		String alias = chckNode.getUniqueName();
		EObject obj = chckNode.getElement();
		setData(obj,alias);
	}				
}
