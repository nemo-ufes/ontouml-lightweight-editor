package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AttributeDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private ClassElement classElement;
	private Classifier element;
	private DiagramManager diagramManager;
	private Property attribute;
	private Component parent;
		
	private PropertyEditionPanel propertyEdition;
	private JButton btnOk; 
	private JButton btnCancel;
	
	public AttributeDialog(final JFrame parent, final DiagramManager diagramManager, final ClassElement classElement, Classifier element, Property attribute, boolean modal)
	{
		super(parent, modal);				
		init(parent,diagramManager,classElement,element,attribute);
		initGUI();		
	}
	
	/**
	 * @wbp.parser.constructor 
	 */
	public AttributeDialog(final JDialog parent, final DiagramManager diagramManager, final ClassElement classElement, Classifier element, Property attribute, boolean modal)
	{
		super(parent, modal);				
		init(parent,diagramManager,classElement,element,attribute);
		initGUI();		
	}

	public void init(final Component parent, final DiagramManager diagramManager, final ClassElement classElement, Classifier element, Property attribute)
	{
		this.diagramManager = diagramManager;
		this.classElement = classElement;		
		this.element = element;
		this.parent = parent;
		this.attribute = attribute;
	}

	public void initGUI()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClassDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/settings.png")));
		
		setTitle(getStereotype(attribute)+" "+attribute.getName()+": "+attribute.getType().getName());
		
		if (parent instanceof JFrame)
			propertyEdition = new PropertyEditionPanel((JFrame)parent, diagramManager, classElement, element, attribute);
		else if (parent instanceof JDialog)
			propertyEdition = new PropertyEditionPanel((JDialog)parent, diagramManager, classElement, element, attribute);	
		
		getContentPane().add(propertyEdition, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100,50));
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				propertyEdition.transferPropertyData();
				if (parent instanceof ClassDialog){
					ClassDialog parentDialog = (ClassDialog)parent;
					parentDialog.refreshAttributesData();
				}
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
					.addGap(153)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addGap(148))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		setSize(new Dimension(450, 330));
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
