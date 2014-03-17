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

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

public class ClassDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private ClassElement classElement;
	private Classifier element;
	private DiagramManager diagramManager;
	@SuppressWarnings("unused")
	private JFrame parent;
	
	private JTabbedPane tabbedPane;
	private JButton btnConfirm;
	private JButton btnCancel;
	private ClassEditionPanel classEdition;
	private CommentsEditionPanel commentsEdition;
	private AttributesEditionPanel attributesEdition;
	private ConstraintEditionPanel constraintsEdition;
	private RelatedElementsPanel relatedElements;
	private JButton btnApply;
	
	public void selectTab (int index)
	{
		tabbedPane.setSelectedIndex(index);
	}
	
	public ClassDialog(final JFrame parent, final DiagramManager diagramManager, final ClassElement classElement, Classifier element, boolean modal) 
	{
		super(parent, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClassDialog.class.getResource("/resources/icons/x16/cog.png")));
		
		this.diagramManager = diagramManager;
		this.classElement = classElement;		
		this.element = element;
		this.parent = parent;
		
//		Image icon = new BufferedImage(1, 1,BufferedImage.TYPE_INT_ARGB_PRE);
//		setIconImage(icon);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(""+""+getStereotype(element)+" "+element.getName());
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
			
		btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				okActionPerformed(e);				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(117)
					.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnApply, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(127))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnApply)
						.addComponent(btnConfirm))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		classEdition = new ClassEditionPanel (diagramManager,classElement,element);
		commentsEdition = new CommentsEditionPanel (diagramManager,classElement,element);		
		attributesEdition = new AttributesEditionPanel(this,diagramManager,classElement,element);
		constraintsEdition = new ConstraintEditionPanel(diagramManager,classElement,element);
		relatedElements = new RelatedElementsPanel(diagramManager,classElement,element);
		
		JPanel classPanel = new JPanel();
		classPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		classPanel.setLayout(new BorderLayout(0,0));
		classPanel.add(classEdition,BorderLayout.NORTH);
		classPanel.add(attributesEdition,BorderLayout.CENTER);
		
		tabbedPane.addTab("Class",classPanel);		
		tabbedPane.addTab("Comments",commentsEdition);
		tabbedPane.addTab("Constraints",constraintsEdition);
		tabbedPane.addTab("Related Elements",relatedElements);
				
		setSize(new Dimension(470, 460));
		
		classEdition.selectNameText();
	}
		
	public void refreshAttributesData()
	{		
		attributesEdition.refreshData();		
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
		classEdition.transferClassData();
		commentsEdition.transferCommentsData();
		attributesEdition.transferAttributesData();		
		constraintsEdition.transferConstraintsData();
		if(getStereotype(element).compareTo((String) classEdition.stereoCombo.getSelectedItem())!=0)
			diagramManager.changeClassStereotype(element, (String) classEdition.stereoCombo.getSelectedItem());
	}
}
