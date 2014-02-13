package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;

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
	private JButton btnSave;
	private JLabel lblcomments;

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
		descriptionPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnShowAttributes)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAttributes))
								.addComponent(classPropPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(descriptionPanel, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(156)
							.addComponent(btnOk)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel)))
					.addContainerGap(16, Short.MAX_VALUE))
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
					.addComponent(descriptionPanel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(btnCancel))
					.addGap(15))
		);
		
		descriptionText = new JTextArea();	
		
		scrollPaneText = new JScrollPane();
		scrollPaneText.setViewportView(descriptionText);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createCommentActionPerformed();				
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteCommentActionPerformed();
			}
		});
		
		commentCombo = new JComboBox();
		commentCombo.setEditable(false);
		commentCombo.setFocusable(false);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveCommentActionPerformed();
			}
		});
		
		lblcomments = new JLabel("Comments:");
		
		GroupLayout gl_descriptionPanel = new GroupLayout(descriptionPanel);
		gl_descriptionPanel.setHorizontalGroup(
			gl_descriptionPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_descriptionPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_descriptionPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_descriptionPanel.createSequentialGroup()
							.addComponent(lblcomments, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(commentCombo, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPaneText, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_descriptionPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnCreate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
						.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_descriptionPanel.setVerticalGroup(
			gl_descriptionPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_descriptionPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_descriptionPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(commentCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblcomments))
					.addGap(18)
					.addGroup(gl_descriptionPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_descriptionPanel.createSequentialGroup()
							.addComponent(btnCreate)
							.addGap(7)
							.addComponent(btnSave)
							.addGap(8)
							.addComponent(btnDelete))
						.addComponent(scrollPaneText))
					.addContainerGap(39, Short.MAX_VALUE))
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
		
		setSize(new Dimension(468, 382));
		
		commentCombo.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				CommentElement ce = (CommentElement) commentCombo.getSelectedItem();
				if(ce!=null) descriptionText.setText(ce.getComment().getBody());
			}
		});
	}
	
	/** Private Class: Comment Element */
	private class CommentElement 
	{
		RefOntoUML.Comment c;
		public CommentElement(RefOntoUML.Comment c){
			this.c = c;
		}
		@Override
		public String toString(){
			String result = new String();
			if(c.getBody().length()>30)
				result += c.getBody().substring(0,30) + " (...)";
			else 
				result += c.getBody();			
			return result;
		}
		public Comment getComment(){
			return c;
		}
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
			commentCombo.addItem(new CommentElement(c));			
		}		
		if (commentCombo.getItemCount()>0) {
			commentCombo.setSelectedIndex(0);
			descriptionText.setText(((CommentElement)commentCombo.getSelectedItem()).getComment().getBody()+"\n\n");
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
	
	@SuppressWarnings("unchecked")
	public void createCommentActionPerformed()
	{
		Comment c = diagramManager.getElementFactory().createComment();
		CommentElement ce = new CommentElement(c);		
		commentCombo.addItem(ce);
		descriptionText.setText(c.getBody());		
	}
	
	public void saveCommentActionPerformed()
	{
		Comment c = ((CommentElement)commentCombo.getSelectedItem()).getComment();
		c.setBody(descriptionText.getText());
	}
	
	public void deleteCommentActionPerformed()
	{
		commentCombo.removeItem(commentCombo.getSelectedItem());
		commentCombo.invalidate();	
		descriptionText.setText("");
	}
	
	public ArrayList<Comment> getComments()
	{
		ArrayList<Comment> result = new ArrayList<Comment>();
		for(int i=0; i<commentCombo.getItemCount();i++){
			CommentElement ce = (CommentElement)commentCombo.getItemAt(i);
			if (ce!=null) result.add(ce.getComment());
		}
		return result;
	}
	
	public void transferComments()
	{
		for(Comment c: getComments()){
			if (!element.getOwnedComment().contains(c)){
				
			}
		}
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
