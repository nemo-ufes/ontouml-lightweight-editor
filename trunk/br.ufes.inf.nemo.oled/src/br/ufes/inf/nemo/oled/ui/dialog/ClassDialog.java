package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.text.Normalizer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

public class ClassDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private ClassElement classElement;
	private Classifier element;
	private DiagramManager diagramManager;
	private JFrame parent;
	
	private JTabbedPane tabbedPane;
	private JButton btnConfirm;
	private JButton btnCancel;
	private ClassEditionPanel classEdition;
	
	public ClassDialog(final JFrame parent, final DiagramManager diagramManager, final ClassElement classElement, boolean modal) 
	{
		super(parent, modal);
		
		this.diagramManager = diagramManager;
		this.classElement = classElement;
		this.element = classElement.getClassifier();
		this.parent = parent;
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Properties"+" - "+getStereotype(classElement.getClassifier())+" "+classElement.getClassifier().getName());
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setPreferredSize(new Dimension(100, 50));
		
		btnConfirm = new JButton("Confirm");
		panel.add(btnConfirm);
		
		btnCancel = new JButton("Cancel");
		panel.add(btnCancel);
		
		classEdition = new ClassEditionPanel (diagramManager,classElement,modal);
		
		tabbedPane.addTab("Class",classEdition);
		
		setSize(new Dimension(542, 359));
	}
		
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
		
	public void okActionPerformed(ActionEvent arg0)
	{
			
	}
}
