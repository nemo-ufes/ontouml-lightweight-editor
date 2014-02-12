package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Element;
import RefOntoUML.Generalization;
import RefOntoUML.NamedElement;
import RefOntoUML.Property;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.InfoManager;
import br.ufes.inf.nemo.oled.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.PropertyTableModel;

public class FeatureListDialog extends JDialog {

	private static final long serialVersionUID = 1805193414767775141L;

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollLeft;
	private JButton btnArrowRight;
	private JButton btnArrowLeft;
	private JScrollPane scrollRight;
	private JList<FeatureElement> leftList;
	private JList<FeatureElement> rightList;
	@SuppressWarnings("rawtypes")
	private DefaultListModel rightListModel;
	@SuppressWarnings("rawtypes")
	private DefaultListModel leftListModel;
	
	private PropertyTableModel tablemodel;
	private OntoUMLParser refparser;
	private int row;
	private int column;
	
	private Element element;
	private String attributeName;
	private ArrayList<Object> featureList = new ArrayList<Object>();
	private JButton okButton;
	private JButton cancelButton;
		
	/**
	 * Launch the Dialog.
	 */
	public static void open(PropertyTableModel tablemodel, int row, int column, OntoUMLParser refparser) 
	{
		try {
			
			FeatureListDialog dialog = new FeatureListDialog(ProjectBrowser.frame, tablemodel, refparser, row, column);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(ProjectBrowser.frame);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Constructor */
	public FeatureListDialog(JFrame parent, PropertyTableModel tablemodel, OntoUMLParser refparser,int row,int column) 
	{
		super(parent);
		this.refparser = refparser;
		this.row=row;
		this.column=column;
		this.tablemodel = tablemodel;
		
		this.element = InfoManager.getProperties().getElement();
		this.attributeName = (String) InfoManager.getProperties().getTable().getValueAt(row,0);	
		
		if(element instanceof RefOntoUML.Property)
		{
			if(attributeName.trim().compareToIgnoreCase("Redefined")==0)
			{
				this.featureList.addAll(((RefOntoUML.Property)element).getRedefinedProperty());				
			}
			if(attributeName.trim().compareToIgnoreCase("Subsetted")==0)
			{
				this.featureList.addAll(((RefOntoUML.Property)element).getSubsettedProperty());				
			}
		}

		initGUI();		
	}
	
	/** Feature Element class */
	private class FeatureElement {
		RefOntoUML.Element element;
		
		public FeatureElement(RefOntoUML.Element element) 
		{
			this.element = element;
		}
		
		public Element getElement() { return element; }
		
		@Override
		public String toString(){
			String result = new String();
			
			if (element instanceof RefOntoUML.Property)
			{
				Property p = (Property)element;
				String owner = new String();
				if(p.getAssociation()==null){
					owner = ""+getStereotype(p.getOwner())+" "+((NamedElement)p.getOwner()).getName();
				}else{
					owner = ""+getStereotype(p.getAssociation())+" "+((NamedElement)p.getAssociation()).getName();
				}
				result += "Property "+p.getType().getName()+": ("+p.getName()+") ["+p.getLower()+","+p.getUpper()+"] "+" (owner: "+owner+")";						  				
			}
			
			return result;
		}
	}
		
	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initGUI() 
	{
//		Image icon = new BufferedImage(1, 1,BufferedImage.TYPE_INT_ARGB_PRE);
//		setIconImage(icon);
		
		//Title
		if (element instanceof Property)
			setTitle(attributeName+" -- "+refparser.getStereotype(element)+" "+((NamedElement)element).getName()+": "+((Property)element).getType().getName());
		else if (element instanceof Generalization) 
			setTitle(attributeName+" -- "+refparser.getStereotype(element)+" "+((Generalization)element).getGeneral().getName()+" -> "+((Generalization)element).getSpecific().getName());			
		else
			setTitle(attributeName+" -- "+refparser.getStereotype(element)+" "+((NamedElement)element).getName());
		
		setBounds(100, 100, 760, 407);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
					
		leftListModel = new DefaultListModel();  
		rightListModel = new DefaultListModel();
		
		for(RefOntoUML.Property p : refparser.getAllInstances(RefOntoUML.Property.class)) 
		{
			if(!featureList.contains(p) && !p.equals(element)){
				FeatureElement elem = new FeatureElement(p);
				leftListModel.addElement(elem);
			}
		}
		leftList = new JList<FeatureElement>(leftListModel);
		
		for(Object obj: featureList){
			FeatureElement elem = new FeatureElement((Element)obj);
			rightListModel.addElement(elem);
		}
		rightList = new JList<FeatureElement>(rightListModel);
		
		scrollLeft = new JScrollPane();	
		scrollLeft.setViewportView(leftList);
		
		scrollRight = new JScrollPane();
		scrollRight.setViewportView(rightList);
		
		btnArrowRight = new JButton("->");	
		btnArrowRight.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{				
				for(FeatureElement n: leftList.getSelectedValuesList())
				{
					if(!rightListModel.contains(n)) {						
						rightListModel.addElement(n); 
						rightList.setSelectedIndex(rightListModel.indexOf(n)); 
					} 					
					if(leftList.getSelectedIndex()>=0) { 
						int prev = leftList.getSelectedIndex()-1;
						leftListModel.remove(leftList.getSelectedIndex());
						leftList.setSelectedIndex(prev); 
					}	
				}
			}
		});
	
		btnArrowLeft = new JButton("<-");		
		btnArrowLeft.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(FeatureElement n: rightList.getSelectedValuesList()){
					if(!leftListModel.contains(n)) { 
						leftListModel.addElement(n); 
						leftList.setSelectedIndex(leftListModel.indexOf(n)); 
					} 					
					if(rightList.getSelectedIndex()>=0) { 
						int prev = rightList.getSelectedIndex()-1;
						rightListModel.remove(rightList.getSelectedIndex());
						rightList.setSelectedIndex(prev); 
					}	
				}
			}
		});
				
		JLabel lblChoices = new JLabel("Choices:");		
		JLabel lblFeature = new JLabel("Features:");
		
		JLabel lblChooseWhichProperties = new JLabel();
		if (attributeName.trim().compareToIgnoreCase("redefined")==0)
			lblChooseWhichProperties.setText("Choose which properties redefine "+refparser.getStereotype(element)+" "+((NamedElement)element).getName()+": "+((Property)element).getType().getName());
		else if (attributeName.trim().compareToIgnoreCase("subsetted")==0){
			lblChooseWhichProperties.setText("Choose which properties subset "+refparser.getStereotype(element)+" "+((NamedElement)element).getName()+": "+((Property)element).getType().getName());
		}else{
			lblChooseWhichProperties.setText("");
		}			
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblChooseWhichProperties, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblChoices, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollLeft, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnArrowRight)
								.addComponent(btnArrowLeft))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblFeature, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollRight, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblChooseWhichProperties, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblFeature)
						.addComponent(lblChoices))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(11)
							.addComponent(btnArrowRight)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnArrowLeft))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollLeft, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollRight, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Confirm");
				okButton.addActionListener(new ActionListener() 
				{
		       		public void actionPerformed(ActionEvent event) 
		       		{
		       			dispose();
		       			
		       			String str = new String();
		       			if(attributeName.trim().compareToIgnoreCase("Redefined")==0 || attributeName.trim().compareToIgnoreCase("Subsetted")==0)
		    			{
			    	    	int i=0;
			    	    	for(Object p: getFeatures()){
			    	    		if(p instanceof Property){
			    	    			Property p2 = (Property)p;
			    	    			if (i==getFeatures().size()-1) str += "<"+getStereotype(p2)+"> "+p2.getName()+": "+p2.getType().getName()+"";
			    	    			else str += "<"+getStereotype(p2)+"> "+p2.getName()+": "+p2.getType().getName()+", ";	
			    	    			
			    	    			if (attributeName.trim().compareToIgnoreCase("Redefined")==0) ((Property)element).getRedefinedProperty().add(p2);
			    	    			else if (attributeName.trim().compareToIgnoreCase("Subsetted")==0) ((Property)element).getSubsettedProperty().add(p2);
			    	    		}
			    	    		i++;
			    	    	}		    	    			       				
		    			}
		    	    	tablemodel.setValueAt(str, row, column);
		    	    	tablemodel.fireTableCellUpdated(row, column);
		       		}
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() 
				{
		       		public void actionPerformed(ActionEvent event) 
		       		{
		       			dispose();
		       		}
				});
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(309)
						.addComponent(okButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cancelButton)
						.addGap(317))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(okButton)
							.addComponent(cancelButton))
						.addContainerGap(21, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}		
	}
	
	public ArrayList<Object> getFeatures()
	{
		ArrayList<Object> result = new ArrayList<Object>();
		for (int i=0;i<rightListModel.getSize();i++) {
			result.add(((FeatureElement)rightListModel.get(i)).getElement());
		}
		return result;
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
