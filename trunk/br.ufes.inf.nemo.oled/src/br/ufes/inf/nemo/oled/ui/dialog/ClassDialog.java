package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Comment;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

public class ClassDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private ClassElement classElement;
	private Classifier element;
	private DiagramManager diagramManager;
	@SuppressWarnings("unused")
	private JFrame parent;
	
	private JCheckBox btnAbstract;		
	private JCheckBox btnExtensional;
	private JTextField nameField;
	private JPanel classPropPanel;
	private JCheckBox btnShowAttributes;
	private JLabel lblStereo;
	@SuppressWarnings("rawtypes")
	private JComboBox stereoCombo;
	private JButton btnAttributes;
	private JButton btnCancel;
	private JButton btnOk;
	private JTextArea descriptionText;
	private JScrollPane scrollPaneText;
	@SuppressWarnings("rawtypes")
	private JComboBox commentCombo;
	private JButton btnCreate;		
	private JButton btnDelete;
	private JPanel descriptionPanel;
			
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ClassDialog(JFrame parent, DiagramManager diagramManager, ClassElement classElement, boolean modal) 
	{
		super(parent, modal);
		
		this.diagramManager = diagramManager;
		this.classElement = classElement;
		this.element = classElement.getClassifier();
		this.parent = parent;
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Properties"+" - "+getStereotype(classElement.getClassifier())+" "+classElement.getClassifier().getName());
		
		classPropPanel = new JPanel();
		classPropPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		btnAttributes = new JButton("Attributes...");
		
		btnShowAttributes = new JCheckBox("Turn attributes visible");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnOk = new JButton("Confirm");
		btnOk.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				okActionPerformed(arg0);
				dispose();
			}
		});
		
		JSeparator separator2 = new JSeparator();		
		
		descriptionPanel = new JPanel();
		descriptionPanel.setBorder(BorderFactory.createTitledBorder("Description"));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnShowAttributes)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAttributes))
						.addComponent(classPropPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(16, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator2, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
					.addGap(16))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(descriptionPanel, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
					.addGap(16))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(154)
					.addComponent(btnOk)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addContainerGap(158, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(classPropPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAttributes)
						.addComponent(btnShowAttributes, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(descriptionPanel, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(btnCancel))
					.addGap(18))
		);
		
		descriptionText = new JTextArea();	
		
		scrollPaneText = new JScrollPane();
		scrollPaneText.setViewportView(descriptionText);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				commentCombo.addItem("<empty comment>");				
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				commentCombo.remove(commentCombo.getSelectedIndex());	
			}
		});
		
		commentCombo = new JComboBox();
		commentCombo.setEditable(false);
		commentCombo.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String str = (String) commentCombo.getSelectedItem();
				if(str!=null){
					str = str.replace("Description", "");
					Comment c = element.getOwnedComment().get(Integer.parseInt(str));
					descriptionText.setText(c.getBody());
				}
			}
		});
		GroupLayout gl_descriptionPanel = new GroupLayout(descriptionPanel);
		gl_descriptionPanel.setHorizontalGroup(
			gl_descriptionPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_descriptionPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_descriptionPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneText, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_descriptionPanel.createSequentialGroup()
							.addComponent(commentCombo, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCreate)))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		gl_descriptionPanel.setVerticalGroup(
			gl_descriptionPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_descriptionPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_descriptionPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(commentCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete)
						.addComponent(btnCreate))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneText, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		descriptionPanel.setLayout(gl_descriptionPanel);
		
		JLabel lblName = new JLabel("Name:");
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		btnAbstract = new JCheckBox("Abstract");
		btnExtensional = new JCheckBox("Extensional");
		
		lblStereo = new JLabel("Stereo:");
		
		stereoCombo = new JComboBox();
		stereoCombo.setModel(new DefaultComboBoxModel(new String[] {"Kind", "SubKind", "Collective", "Quantity", "Role", "Phase", "Category", "Mixin", "RoleMixin", "Relator", "Mode", "DataType"}));
		
		GroupLayout gl_classPropPanel = new GroupLayout(classPropPanel);
		gl_classPropPanel.setHorizontalGroup(
			gl_classPropPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_classPropPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_classPropPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblStereo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_classPropPanel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_classPropPanel.createSequentialGroup()
							.addComponent(stereoCombo, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAbstract)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExtensional))
						.addComponent(nameField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_classPropPanel.setVerticalGroup(
			gl_classPropPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_classPropPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_classPropPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_classPropPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExtensional)
						.addComponent(btnAbstract)
						.addComponent(lblStereo)
						.addComponent(stereoCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		classPropPanel.setLayout(gl_classPropPanel);
		getContentPane().setLayout(groupLayout);		
		
		setInitialData();
		
		setSize(new Dimension(468, 407));
	}
	
	@SuppressWarnings("unchecked")
	public void setInitialData()
	{
		btnShowAttributes.setSelected(classElement.showAttributes());
		nameField.setText(element.getName());
		if (element instanceof Collective) btnExtensional.setEnabled(true);
		else btnExtensional.setEnabled(false);
		btnAbstract.setSelected(element.isIsAbstract());
		stereoCombo.setSelectedItem(getStereotype(element).trim());
		stereoCombo.setEnabled(false);
		for(Comment c: element.getOwnedComment()){
			commentCombo.addItem("Description"+element.getOwnedComment().indexOf(c));			
		}		
		if (commentCombo.getItemCount()>0) {
			commentCombo.setSelectedIndex(0);
			descriptionText.setText(element.getOwnedComment().get(0).getBody()+"\n\n");
		}
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
	
	public void createCommentActionPerformed()
	{
		
	}
	
	public void deleteCommentActionPerformed()
	{
		
	}
	
	public void okActionPerformed(ActionEvent arg0)
	{
		element.setName(nameField.getText());
		if (element instanceof Collective) ((Collective) element).setIsExtensional(btnExtensional.isSelected());
		element.setIsAbstract(btnAbstract.isSelected());
		classElement.setShowAttributes(btnShowAttributes.isSelected());
		
		diagramManager.updateOLED(element);		
	}
}
