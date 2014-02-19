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
	@SuppressWarnings("unused")
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
	private ConstraintEditionPanel constraintsEdition;
	private JButton btnApply;
	
	public AssociationDialog(final JFrame parent, final DiagramManager diagramManager, final AssociationElement assocElement, boolean modal) 
	{
		super(parent, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClassDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/settings.png")));
		
//		Image icon = new BufferedImage(1, 1,BufferedImage.TYPE_INT_ARGB_PRE);
//		setIconImage(icon);
		
		this.diagramManager = diagramManager;
		this.assocElement = assocElement;
		this.relationship = assocElement.getRelationship();
		this.parent = parent;
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Settings"+" - "+getStereotype(relationship)+" "+ ((Classifier)relationship).getName());
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
		
		assocEdition = new AssociationEditionPanel (diagramManager,assocElement,modal);
		end1Edition = new PropertyEditionPanel(this,diagramManager,assocElement,((Association)relationship).getMemberEnd().get(0));
		end2Edition = new PropertyEditionPanel(this,diagramManager,assocElement,((Association)relationship).getMemberEnd().get(1));
		commentsEdition = new CommentsEditionPanel (diagramManager,assocElement);
		constraintsEdition = new ConstraintEditionPanel(diagramManager,assocElement);
		
		tabbedPane.addTab("Association",assocEdition);
		tabbedPane.addTab("Source", end1Edition);
		tabbedPane.addTab("Target", end2Edition);
		tabbedPane.addTab("Comments",commentsEdition);
		tabbedPane.addTab("Constraints",constraintsEdition);
		
		tabbedPane.setIconAt(0, new ImageIcon(getClass().getClassLoader().getResource("resources/br/ufes/inf/nemo/oled/ui/tree/association.png")));
		tabbedPane.setIconAt(1, new ImageIcon(getClass().getClassLoader().getResource("resources/br/ufes/inf/nemo/oled/ui/tree/property.gif")));
		tabbedPane.setIconAt(2, new ImageIcon(getClass().getClassLoader().getResource("resources/br/ufes/inf/nemo/oled/ui/tree/property.gif")));
		tabbedPane.setIconAt(3, new ImageIcon(getClass().getClassLoader().getResource("resources/br/ufes/inf/nemo/oled/ui/note.png")));
		tabbedPane.setIconAt(4, new ImageIcon(getClass().getClassLoader().getResource("resources/br/ufes/inf/nemo/oled/ui/ocleditor.png")));
		
		setSize(new Dimension(470, 430));		
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
		assocEdition.transferAssocData();
		end1Edition.transferPropertyData();
		end2Edition.transferPropertyData();
		commentsEdition.transferCommentsData();
		constraintsEdition.transferConstraintsData();
	}	
}

