package br.ufes.inf.nemo.oled.dialog.properties;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.PackageableElement;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.explorer.OntoUMLElement;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;

public class GeneralizationEditionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private GeneralizationElement genElement;	
	private Generalization element;
	private DiagramManager diagramManager;
	@SuppressWarnings("unused")
	private Component parent;
	
	@SuppressWarnings("rawtypes")
	private JComboBox generalCombo;
	private JScrollPane scrollPane;
	@SuppressWarnings("rawtypes")
	private JComboBox specificCombo;
	private JList<OntoUMLElement> genSetList;
	private JButton btnRemove;
	private JButton btnAdd;
	@SuppressWarnings("rawtypes")
	private DefaultListModel genSetModel;

	private JButton btnNew; 
	private JButton btnEdit;
	
	public GeneralizationEditionPanel(JDialog parent, final DiagramManager diagramManager, final GeneralizationElement genElement, final Generalization element) 
	{		
		initData(parent,diagramManager,genElement,element);
		initGUI();		
	}
	
	/**
	 * @wbp.parser.constructor 
	 */
	public GeneralizationEditionPanel(JFrame parent, final DiagramManager diagramManager, final GeneralizationElement genElement, final Generalization element) 
	{		
		initData(parent,diagramManager,genElement,element);
		initGUI();		
	}		
	
	public GeneralizationEditionPanel(final Component parent, final DiagramManager diagramManager, final GeneralizationElement genElement, final Generalization element) 
	{
		initData(parent,diagramManager,genElement,element);
		initGUI();
	}
	
	public void initData(final Component parent, final DiagramManager diagramManager, final GeneralizationElement genElement, final Generalization element) 
	{
		this.diagramManager = diagramManager;
		this.genElement = genElement;
		this.element = element;
		this.parent=parent;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initGUI()
	{
		JPanel generalPanel = new JPanel();
		generalPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		JPanel genSetPanel = new JPanel();
		genSetPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		JPanel specificPanel = new JPanel();
		specificPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(specificPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(genSetPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
						.addComponent(generalPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(specificPanel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(generalPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(genSetPanel, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(156, Short.MAX_VALUE))
		);
		
		JLabel lblSpecific = new JLabel("Specific:");
		specificPanel.add(lblSpecific);
		
		specificCombo = new JComboBox();
		specificPanel.add(specificCombo);		
		specificCombo.setPreferredSize(new Dimension(350, 20));
		specificCombo.addItem(getStereotype(element.getSpecific())+" "+element.getSpecific().getName());
		
		JLabel lblThisGeneralizationParticipates = new JLabel("Participating generalization sets :");
		
		genSetModel = new DefaultListModel();
		for(GeneralizationSet gs: element.getGeneralizationSet())
		{
			genSetModel.addElement(new OntoUMLElement(gs,""));
		}		
		genSetList= new JList<OntoUMLElement>(genSetModel);
		
		scrollPane = new JScrollPane(genSetList);
		
		btnRemove = new JButton("");
		btnRemove.setIcon(new ImageIcon(GeneralizationEditionPanel.class.getResource("/resources/icons/x16/cross.png")));
		btnRemove.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (genSetModel.size()>0){
					OntoUMLElement genSet = (OntoUMLElement)genSetList.getSelectedValue();
					
					((GeneralizationSet)genSet.getElement()).getGeneralization().remove(element);
					element.getGeneralizationSet().remove(genSet.getElement());
					
					genSetModel.removeElement(genSetList.getSelectedValue());				
				}
			}
		});
		
		btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon(GeneralizationEditionPanel.class.getResource("/resources/icons/x16/add.png")));
		btnAdd.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OntoUMLParser refparser = ProjectBrowser.getParserFor(diagramManager.getCurrentProject());
				ArrayList<OntoUMLElement> genSetList = new ArrayList<OntoUMLElement>();
				for(GeneralizationSet gs: refparser.getAllInstances(GeneralizationSet.class))
				{
					if (!(element.getGeneralizationSet().contains(gs))) genSetList.add(new OntoUMLElement(gs,""));
				}				
				if (genSetList.size()==0) {
					JOptionPane.showMessageDialog(GeneralizationEditionPanel.this, "No generalization set left in the model.", "Add", JOptionPane.INFORMATION_MESSAGE);
				}else{
					OntoUMLElement genSet = (OntoUMLElement) JOptionPane.showInputDialog(GeneralizationEditionPanel.this, 
					        "To which generalization set do you want to include "+element.getSpecific().getName()+"->"+element.getGeneral().getName(),
					        "Add",
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        genSetList.toArray(), 
					        genSetList.toArray()[0]
					);
					if(genSet!=null){
						genSetModel.addElement(genSet);
					
						((GeneralizationSet)genSet.getElement()).getGeneralization().add(element);
						element.getGeneralizationSet().add((GeneralizationSet)genSet.getElement());
					}
				}				
			}
		});
		
		btnNew = new JButton("");
		btnNew.setIcon(new ImageIcon(GeneralizationEditionPanel.class.getResource("/resources/icons/x16/new.png")));
		btnNew.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int response = JOptionPane.showConfirmDialog(GeneralizationEditionPanel.this, "Are you sure you want to create a new generalization set?", "Creating Generalization Set", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response==JOptionPane.OK_OPTION){
					PackageableElement genSet = (PackageableElement)diagramManager.addElement(ElementType.GENERALIZATIONSET,(RefOntoUML.Package)element.eContainer().eContainer());
					genSet.setName("gs");
					((GeneralizationSet)genSet).setIsCovering(true);
					((GeneralizationSet)genSet).setIsDisjoint(true);
					((GeneralizationSet)genSet).getGeneralization().add(element);
					element.getGeneralizationSet().add((GeneralizationSet)genSet);					
					DialogCaller.callGeneralizationSetDialog(diagramManager.getFrame(), diagramManager, (GeneralizationSet)genSet,true);
					genSetModel.addElement(new OntoUMLElement(genSet,""));				
				}
			}
		});
		
		btnEdit = new JButton("");
		btnEdit.setIcon(new ImageIcon(GeneralizationEditionPanel.class.getResource("/resources/icons/x16/pencil.png")));
		btnEdit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(genSetModel.size()>0){
					GeneralizationSet genSet = (GeneralizationSet)((OntoUMLElement)genSetList.getSelectedValue()).getElement();
					DialogCaller.callGeneralizationSetDialog(diagramManager.getFrame(), diagramManager,genSet,true);
				}
			}
		});
		
		GroupLayout gl_genSetPanel = new GroupLayout(genSetPanel);
		gl_genSetPanel.setHorizontalGroup(
			gl_genSetPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_genSetPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_genSetPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.LEADING, gl_genSetPanel.createSequentialGroup()
							.addComponent(lblThisGeneralizationParticipates, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_genSetPanel.setVerticalGroup(
			gl_genSetPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_genSetPanel.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_genSetPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEdit)
						.addComponent(btnNew)
						.addComponent(btnRemove)
						.addComponent(btnAdd)
						.addComponent(lblThisGeneralizationParticipates, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		genSetPanel.setLayout(gl_genSetPanel);
		
		JLabel lblGeneral = new JLabel("General:");
		generalPanel.add(lblGeneral);
		
		generalCombo = new JComboBox();
		generalCombo.setFocusable(false);
		generalCombo.setPreferredSize(new Dimension(350, 20));
		generalPanel.add(generalCombo);
		
		setLayout(groupLayout);
		
		setInitialData();
	}
	
	public class CustomComparator implements Comparator<OntoUMLElement> 
    {
        @Override
        public int compare(OntoUMLElement o1, OntoUMLElement o2) {
            return o1.toString().compareToIgnoreCase(o2.toString());
        }
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setInitialData()
	{
		ArrayList<OntoUMLElement> generallist = new ArrayList<OntoUMLElement>();
		ArrayList<OntoUMLElement> specificlist = new ArrayList<OntoUMLElement>();
		OntoUMLElement generalValue = null;
		OntoUMLElement specificValue = null;
		OntoUMLParser refparser = ProjectBrowser.getParserFor(diagramManager.getCurrentProject());
		if (element.getGeneral()!=null) generalValue = new OntoUMLElement(element.getGeneral(),"");
		else generalValue = new OntoUMLElement(null,"");
		if (element.getSpecific()!=null) specificValue = new OntoUMLElement(element.getSpecific(),"");
		else specificValue = new OntoUMLElement(null,"");
    	for(RefOntoUML.Type t: refparser.getAllInstances(RefOntoUML.Type.class))
    	{
			if(t instanceof RefOntoUML.Class || t instanceof RefOntoUML.DataType || t instanceof RefOntoUML.Association)
			{
				if (((OntoUMLElement) generalValue).getElement()!=null && t.equals(((OntoUMLElement) generalValue).getElement())) generallist.add((OntoUMLElement)generalValue);				
    			else generallist.add(new OntoUMLElement(t,""));
				if (((OntoUMLElement) specificValue).getElement()!=null && t.equals(((OntoUMLElement) specificValue).getElement())) specificlist.add((OntoUMLElement)specificValue);				
    			else specificlist.add(new OntoUMLElement(t,""));
    		}	    					
    	}
    	if (((OntoUMLElement) generalValue).getElement()==null) generallist.add((OntoUMLElement)generalValue);
    	else if (!refparser.getAllInstances(RefOntoUML.Type.class).contains(element.getGeneral())) generallist.add((OntoUMLElement)generalValue);
    	if (((OntoUMLElement) specificValue).getElement()==null) specificlist.add((OntoUMLElement)specificValue);
    	else if (!refparser.getAllInstances(RefOntoUML.Type.class).contains(element.getSpecific())) specificlist.add((OntoUMLElement)specificValue);
    	Collections.sort(generallist,new CustomComparator());	    	
    	Collections.sort(specificlist,new CustomComparator());
    	generalCombo.setModel(new DefaultComboBoxModel(generallist.toArray()));
    	generalCombo.setSelectedItem(generalValue);	
    	specificCombo.setModel(new DefaultComboBoxModel(specificlist.toArray()));
    	specificCombo.setSelectedItem(specificValue);	
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	public void transferGenData()
	{
		boolean redesign = false;
		
		RefOntoUML.Type general = (RefOntoUML.Type)((OntoUMLElement)generalCombo.getSelectedItem()).getElement();			
		if (general!=null && !general.equals(element.getGeneral())) redesign = true;
		element.setGeneral((Classifier)general);
		
		RefOntoUML.Type specific = (RefOntoUML.Type)((OntoUMLElement)specificCombo.getSelectedItem()).getElement();			
		if (specific!=null && !specific.equals(element.getSpecific())) redesign = true;
		element.setSpecific((Classifier)specific);
		
		diagramManager.updateOLEDFromModification(element, redesign);
	}
}
