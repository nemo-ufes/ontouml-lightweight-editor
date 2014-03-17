package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Relationship;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;

public class AssociationDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private AssociationElement assocElement;
	private Relationship relationship;
	private DiagramManager diagramManager;
	@SuppressWarnings("unused")
	private JFrame parent;
	
	private JTabbedPane tabbedPane;
	private JButton btnConfirm;
	private JButton btnCancel;
	private AssociationEditionPanel assocEdition;
	private PropertyEditionPanel end1Edition;
	private PropertyEditionPanel end2Edition;
	private CommentsEditionPanel commentsEdition;
	
	public void selectTab (int index)
	{
		tabbedPane.setSelectedIndex(index);
	}
	
	public AssociationDialog(final JFrame parent, final DiagramManager diagramManager, final AssociationElement assocElement, RefOntoUML.Relationship relationship, boolean modal) 
	{
		super(parent, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClassDialog.class.getResource("/resources/icons/x16/cog.png")));
		
//		Image icon = new BufferedImage(1, 1,BufferedImage.TYPE_INT_ARGB_PRE);
//		setIconImage(icon);
		
		this.diagramManager = diagramManager;
		this.assocElement = assocElement;
		this.relationship = relationship;
		this.parent = parent;
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(""+""+getStereotype(relationship)+" "+ ((Classifier)relationship).getName());
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setPreferredSize(new Dimension(100, 50));
		
		btnConfirm = new JButton("Ok");		
		btnConfirm.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				okActionPerformed(e);
				dispose();
			}
		});
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(158)
					.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addGap(162))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnConfirm))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		assocEdition = new AssociationEditionPanel (diagramManager,assocElement,(Classifier)relationship,modal);
		end1Edition = new PropertyEditionPanel(this,diagramManager,assocElement,(Classifier)relationship,((Association)relationship).getMemberEnd().get(0));
		end2Edition = new PropertyEditionPanel(this,diagramManager,assocElement,(Classifier)relationship,((Association)relationship).getMemberEnd().get(1));
		commentsEdition = new CommentsEditionPanel (diagramManager,assocElement,(Classifier)relationship);
//		constraintsEdition = new ConstraintEditionPanel(diagramManager,assocElement);
//		relatedElements = new RelatedElementsPanel(diagramManager,assocElement);
				
		tabbedPane.addTab("Association",assocEdition);
		tabbedPane.addTab("Source", end1Edition);
		tabbedPane.addTab("Target", end2Edition);
		tabbedPane.addTab("Comments",commentsEdition);
//		tabbedPane.addTab("Constraints",constraintsEdition);
//		tabbedPane.addTab("Related Elements",relatedElements);
				
		setSize(new Dimension(470, 410));		
	}
		
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
		
	public void okActionPerformed(ActionEvent arg0)
	{
		assocEdition.transferAssocData();
		end1Edition.transferPropertyData();
		end2Edition.transferPropertyData();
		commentsEdition.transferCommentsData();
		if(getStereotype(relationship).compareTo((String) assocEdition.stereoCombo.getSelectedItem())!=0)
			diagramManager.changeRelationStereotype(relationship, (String) assocEdition.stereoCombo.getSelectedItem());
//		constraintsEdition.transferConstraintsData();
	}	
}

