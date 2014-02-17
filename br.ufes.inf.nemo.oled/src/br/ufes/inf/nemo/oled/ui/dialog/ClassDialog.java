package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;

import javax.swing.ImageIcon;
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
	
	@SuppressWarnings("unused")
	private ClassElement classElement;
	@SuppressWarnings("unused")
	private Classifier element;
	@SuppressWarnings("unused")
	private DiagramManager diagramManager;
	@SuppressWarnings("unused")
	private JFrame parent;
	
	private JTabbedPane tabbedPane;
	private JButton btnConfirm;
	private JButton btnCancel;
	private ClassEditionPanel classEdition;
	private CommentsEditionPanel commentsEdition;
	private AttributesEditionPanel attributesEdition;
	private JButton btnApply;
	
	public ClassDialog(final JFrame parent, final DiagramManager diagramManager, final ClassElement classElement, boolean modal) 
	{
		super(parent, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClassDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/settings.png")));
		
		this.diagramManager = diagramManager;
		this.classElement = classElement;
		this.element = classElement.getClassifier();
		this.parent = parent;
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Settings"+" - "+getStereotype(classElement.getClassifier())+" "+classElement.getClassifier().getName());
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
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
		
		panel.add(btnConfirm);
		panel.add(btnCancel);
		panel.add(btnApply);
		
		classEdition = new ClassEditionPanel (diagramManager,classElement,modal);
		commentsEdition = new CommentsEditionPanel (diagramManager,classElement,modal);
		attributesEdition = new AttributesEditionPanel(diagramManager,classElement,modal);
		
		tabbedPane.addTab("Class",classEdition);		
		tabbedPane.addTab("Attributes",attributesEdition);
		tabbedPane.addTab("Comments",commentsEdition);
		
		tabbedPane.setIconAt(0, new ImageIcon(getClass().getClassLoader().getResource("resources/br/ufes/inf/nemo/oled/ui/tree/class.png")));
		tabbedPane.setIconAt(1, new ImageIcon(getClass().getClassLoader().getResource("resources/br/ufes/inf/nemo/oled/ui/tree/property.gif")));
		tabbedPane.setIconAt(2, new ImageIcon(getClass().getClassLoader().getResource("resources/br/ufes/inf/nemo/oled/ui/note.png")));
		
		setSize(new Dimension(470, 399));
		
		classEdition.selectNameText();
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
		classEdition.transferClassData();
		commentsEdition.transferCommentsData();
		attributesEdition.transferAttributesData();		
	}
}
